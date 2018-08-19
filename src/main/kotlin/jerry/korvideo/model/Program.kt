package jerry.korvideo.model

data class Program(
    val title: String,
    val link: String,
    val description: String,
    val optionalDescription: String? = null
) {
    val uniqueId: String
        get() {
            val groups = REGEX_UNIQUE_ID.matchEntire(link)?.groupValues
            return groups?.last() ?: link
        }

    companion object {
        private val REGEX_UNIQUE_ID = Regex("ViewLink[.]aspx[?]Num=(\\d+)", RegexOption.IGNORE_CASE)
    }
}
