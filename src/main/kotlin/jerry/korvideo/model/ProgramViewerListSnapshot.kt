package jerry.korvideo.model

data class ProgramViewerListSnapshot(
    val program: Program,
    val viewerDataList: List<ProgramViewerData>
)
