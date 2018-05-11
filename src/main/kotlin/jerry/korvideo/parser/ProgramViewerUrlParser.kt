package jerry.korvideo.parser

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class ProgramViewerUrlParser(private val html: String) {
    private val document: Document by lazy {
        Jsoup.parse(html)
    }

    fun parse(): String? {
        val element: Element? =
            document.selectFirst("span[id=ctl00_ContentPlaceHolder1_ViewLink1_lblError]")
        return element?.text()
            ?.replace("link: ", "", ignoreCase = true)
            ?.replace(" copy link", "", ignoreCase = true)
            ?.trim()
    }
}
