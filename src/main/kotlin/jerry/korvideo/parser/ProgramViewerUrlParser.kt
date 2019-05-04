package jerry.korvideo.parser

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class ProgramViewerUrlParser(private val html: String) {
    private val document: Document by lazy {
        Jsoup.parse(html)
    }

    fun parse(): String? {
        val updatePanel = document.selectFirst("div[id=ctl00_ContentPlaceHolder1_viewlink1_UpdatePanel]")
        val iframe = updatePanel.selectFirst("iframe[id=frameFlashId]")
        return iframe.attr("src")
    }
}
