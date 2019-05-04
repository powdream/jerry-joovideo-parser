package jerry.joovideo.model

data class ProgramViewerPage(
    val basePostData: Map<String, String>,
    val suppliers: List<Supplier>
)

data class Supplier(
    val supplierName: String,
    val postData: Map<String, String>
)
