package jerry.joovideo.model

data class ProgramViewerPage(
    val basePostData: Map<String, String>,
    val suppliersInCurrentPage: List<Supplier>,
    val pages: List<Page>
) {
    val currentPageIndex: Int
        get() = pages.indexOfFirst { it.postData.isEmpty() }.takeIf { it in pages.indices } ?: 0

    val numberOfPages: Int
        get() = pages.size

    constructor(
        basePostData: Map<String, String>,
        suppliersInCurrentPage: List<Supplier>
    ) : this(basePostData, suppliersInCurrentPage, listOf(SINGLE_PAGE))

    init {
        check(pages.isNotEmpty())
    }

    companion object {
        private val SINGLE_PAGE: Page = Page(1, emptyMap())
    }
}

data class Supplier(
    val supplierName: String,
    val postData: Map<String, String>
)

data class Page(
    val index: Int,
    val postData: Map<String, String>
)
