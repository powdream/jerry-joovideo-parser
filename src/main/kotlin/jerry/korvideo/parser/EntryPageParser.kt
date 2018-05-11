package jerry.korvideo.parser

import jerry.korvideo.model.Program
import jerry.korvideo.model.ProgramCategory
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class EntryPageParser(private val html: String) {
    private val document: Document by lazy {
        Jsoup.parse(html)
    }

    fun parse(programCategory: ProgramCategory): List<Program> {
        return document
            .selectFirst("table[id=${programCategory.tableId}]")
            ?.select("td")
            ?.mapNotNull(Element::toProgramOrNull)
                ?: emptyList()
    }
}

private fun Element.toProgramOrNull(): Program? {
    val optionalDescription: String? = textNodes()
        ?.lastOrNull()
        ?.text()
        ?.takeIf(String::isNotBlank)
        ?.trim()
    val a = selectFirst("a") ?: return null
    return Program(
        title = a.text() ?: return null,
        link = a.attr("href") ?: return null,
        description = selectFirst("span[style=color:Black;]")?.text() ?: return null,
        optionalDescription = optionalDescription
    )
}
