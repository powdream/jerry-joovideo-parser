package jerry.video.common

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

abstract class ParserTemplate<out T : Any>(private val html: String) {
    private val document: Document by lazy { Jsoup.parse(html) }

    fun parse(): T = parse(document)

    protected abstract fun parse(document: Document): T
}
