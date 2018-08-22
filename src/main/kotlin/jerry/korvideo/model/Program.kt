package jerry.korvideo.model

import java.util.Locale

data class Program(
    val title: String,
    val link: String,
    val description: String,
    val optionalDescription: String? = null
) {
    private val linkMatchResultGroupValues: List<String>? by lazy(LazyThreadSafetyMode.PUBLICATION) {
        REGEX_LINK.matchEntire(link)?.groupValues
    }

    val host: String?
        get() = linkMatchResultGroupValues?.getOrNull(3)?.toLowerCase(Locale.ROOT)?.takeIf(String::isNotBlank)

    val path: String
        get() = linkMatchResultGroupValues?.getOrNull(4) ?: link

    val uniqueId: String
        get() = linkMatchResultGroupValues?.lastOrNull() ?: link

    companion object {
        private val REGEX_LINK =
            Regex("(http(s)?://([^/]+).*)?(ViewLink[.]aspx[?]Num=(\\d+))", RegexOption.IGNORE_CASE)
    }
}
