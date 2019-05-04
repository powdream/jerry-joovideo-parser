package jerry.joovideo.parser

import jerry.joovideo.model.ProgramViewerPage
import jerry.joovideo.model.Supplier
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class ProgramViewerPageParserV2(private val html: String) {
    private val document: Document by lazy {
        Jsoup.parse(html)
    }

    fun parse(): ProgramViewerPage {
        val baseFormElements = mapOf("__EVENTTARGET" to "", "__EVENTARGUMENT" to "") + extractFormArguments()
        val supplierList = extractSupplierList()
        return ProgramViewerPage(baseFormElements, supplierList)
    }

    private fun extractFormArguments(): Map<String, String> =
        document.select("input[type=hidden]")
            .associate {
                val name = it.attr("name")
                val value = it.attr("value")
                name to value
            }

    private fun extractSupplierList(): List<Supplier> {
        val updatePanel = document.selectFirst("div[id=ctl00_ContentPlaceHolder1_viewlink1_UpdatePanel]")
        val aList = updatePanel.select("a[id=btn]").map(::parseATag)
        return aList.map { Supplier(it.text, it.postBack) }
    }

    private fun parseATag(aTagElement: Element): ATag =
        ATag(aTagElement.text(), parseJavascriptCall(aTagElement.attr("href")))

    private fun parseJavascriptCall(javaScript: String): Map<String, String> =
        REGEX_JAVASCRIPT_CALL.find(javaScript)
            ?.groupValues
            ?.let { mapOf("__EVENTTARGET" to it[1], "__EVENTARGUMENT" to it[2]) }
            .orEmpty()

    private data class ATag(
        val text: String,
        val postBack: Map<String, String>
    )

    companion object {
        private val REGEX_JAVASCRIPT_CALL: Regex =
            "javascript:__doPostBack\\('([^']*)','([^']*)'\\)".toRegex(RegexOption.IGNORE_CASE)
    }
}
