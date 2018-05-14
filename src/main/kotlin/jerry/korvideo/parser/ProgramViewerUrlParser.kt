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
            document.selectFirst("button[id=copylink]")
        return element?.attr("onclick")
            ?.replace("setClipboard('", "", ignoreCase = true)
            ?.replace("')", "", ignoreCase = true)
            ?.trim()
    }
}
