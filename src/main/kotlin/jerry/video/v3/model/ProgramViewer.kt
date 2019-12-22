package jerry.video.v3.model

data class ProgramViewer(
    val url: String,
    val playerList: List<ProgramPlayer>,
    val prevKey: Map<String, String>?,
    val nextKey: Map<String, String>?
)
