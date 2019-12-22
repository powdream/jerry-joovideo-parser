package jerry.video.v3.parser

import jerry.video.common.ParserTemplate
import jerry.video.v3.model.ProgramPlayer
import jerry.video.v3.model.ProgramViewer
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.net.URL
import java.net.URLDecoder

class ProgramViewerPageParser(html: String) : ParserTemplate<ProgramViewer>(html) {
    private val doPostBackRegex = """.*\('(.*)','(.*)'\)""".toRegex()

    override fun parse(document: Document): ProgramViewer {
        val commonParameters = document.hiddenInputs()
        return ProgramViewer(
            url = document.pageUrl(),
            playerList = document.playerList(),
            prevKey = document.findLinkPage("<<")
                ?.extractParameters()
                ?.let { it + commonParameters },
            nextKey = document.findLinkPage(">>")
                ?.extractParameters()
                ?.let { it + commonParameters }
        )
    }

    private fun Document.findLinkPage(text: String): Element? =
        select("a")
            .firstOrNull {
                val id = it.attr("id")
                id?.startsWith("ctl00_ContentPlaceHolder1_epList1_rptPager") == true &&
                        it.hasText() && it.text() == text
            }

    private fun Element.extractParameters(): Map<String, String> {
        val href = attr("href") ?: return emptyMap()
        val matchResult = doPostBackRegex.matchEntire(href) ?: return emptyMap()
        val arguments = matchResult.groupValues.takeIf { it.size == 3 } ?: return emptyMap()
        return mapOf("__EVENTTARGET" to arguments[1], "__EVENTARGUMENT" to arguments[2])
    }

    private fun Document.hiddenInputs(): Map<String, String> =
        select("input[type=hidden]")
            .asSequence()
            .mapNotNull {
                val name = it.attr("name")
                val value = it.attr("value").orEmpty()
                if (name.isNullOrEmpty()) null else name to value
            }
            .toMap()

    private fun Document.pageUrl(): String {
        val form: Element? = selectFirst("form[name=aspnetForm]")
        return form?.attr("action").orEmpty()
    }

    private fun Document.playerList(): List<ProgramPlayer> =
        selectFirst("table[id=ctl00_ContentPlaceHolder1_epList1_ctlMediaList]")
            ?.select("span[id=lblEpisode]")
            ?.asSequence()
            ?.mapNotNull { it.selectFirst("a") }
            ?.map {
                val title = it.text()
                val href = it.attr("href")
                val url = URL("https://localhost/$href")
                val qlink = extractQlink(url.query.orEmpty())
                ProgramPlayer(title = title, url = qlink.orEmpty())
            }
            ?.toList().orEmpty()

    private fun extractQlink(queryParameter: String): String? {
        val startIndex = queryParameter
            .indexOf("qlink=")
            .takeIf { it >= 0 }
            ?.let { it + "qlink=".length }
            ?: return null
        val endIndex = queryParameter
            .indexOf("&", startIndex = startIndex + 1)
            .takeIf { it >= 0 }
            ?: queryParameter.length
        return URLDecoder.decode(
            queryParameter.substring(startIndex until endIndex),
            charset("UTF-8")
        )
    }
}
