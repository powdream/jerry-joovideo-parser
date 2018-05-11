package jerry.korvideo.model

data class Program(
    val title: String,
    val link: String,
    val description: String,
    val optionalDescription: String? = null
) {
    val uniqueId: String get() = link
}
