package jerry.joovideo.parser

import jerry.joovideo.model.Page
import jerry.joovideo.model.ProgramViewerPage
import jerry.joovideo.model.Supplier
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class ProgramViewerPageParserV2(private val html: String) {
    private val document: Document by lazy {
        Jsoup.parse(html)
    }

    fun parse(): ProgramViewerPage {
        val baseFormElements = extractFormArguments()
        val table = document.select("table[id=ctl00_ContentPlaceHolder1_ViewLink1_GridView1]")
        val trList = table.select("tr")

        val suppliersInCurrentPage = extractSuppliersInCurrentPage(trList)
        val pages = extractPages(trList)
        return ProgramViewerPage(baseFormElements, suppliersInCurrentPage, pages)
    }

    private fun extractPages(trList: Elements): List<Page> =
        trList.takeIf { it.size > 7 }
            ?.lastOrNull()
            ?.select("td")
            ?.mapNotNull<Element, Page> { tdTag ->
                val spanTag = tdTag.selectFirst("span")
                val aTag = tdTag.selectFirst("a")
                when {
                    spanTag != null -> Page(spanTag.text().trim().toIntOrNull() ?: 1, emptyMap())
                    aTag != null -> {
                        val (text, postBack) = parseATag(aTag)
                        Page(text.toInt(), postBack)
                    }
                    else -> null
                }
            }
            ?: listOf(Page(1, emptyMap()))

    private fun extractSuppliersInCurrentPage(trList: Elements): List<Supplier> =
        trList.subList(1, 7.coerceAtMost(trList.size))
            .asSequence()
            .map { parseATag(it.selectFirst("a")) }
            .map { (text, postBack) ->
                Supplier(text, postBack)
            }
            .toList()

    private fun extractFormArguments(): Map<String, String> =
        document.select("input[type=hidden]")
            .associate {
                it.attr("name") to it.attr("value")
            }

    private fun parseATag(aTagElement: Element): ATag =
        ATag(aTagElement.text(), parseJavascriptCall(aTagElement.attr("href")))

    private fun parseJavascriptCall(javaScript: String): Map<String, String> =
        REGEX_JAVASCRIPT_CALL.find(javaScript)
            ?.groupValues
            ?.let {
                mapOf(
                    "__EVENTTARGET" to it[1],
                    "__EVENTARGUMENT" to it[2]
                )
            }.orEmpty()

    private data class ATag(
        val text: String,
        val postBack: Map<String, String>
    )

    companion object {
        private val REGEX_JAVASCRIPT_CALL: Regex =
            "javascript:__doPostBack\\('([^']*)','([^']*)'\\)".toRegex(RegexOption.IGNORE_CASE)
    }
}
