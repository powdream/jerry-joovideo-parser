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

    fun parse(programCategory: ProgramCategory): List<Program> =
        document.find(programCategory)
            ?.select("td")
            ?.mapNotNull(Element::toProgramOrNull)
            ?: emptyList()
}

private fun Document.find(programCategory: ProgramCategory): Element? {
    var elements: List<Element> = select("table[id=${programCategory.tableId}]")
    if (elements.isEmpty()) {
        elements = select("table[id=${ProgramCategory.DRAMA.tableId}]")
    }

    return when (elements.size) {
        0 -> null
        1 -> elements.first()
        else -> elements.firstOrNull { element ->
            element.selectFirst("td")?.text() == programCategory.categoryName
        }
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
