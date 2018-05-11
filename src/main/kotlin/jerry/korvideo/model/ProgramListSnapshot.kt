package jerry.korvideo.model

data class ProgramListSnapshot(
    val programCategoryList: List<ProgramCategory>,
    private val programs: Map<ProgramCategory, List<Program>>
) {
    operator fun get(programCategory: ProgramCategory): List<Program> =
        programs[programCategory] ?: emptyList()

    companion object {
        val EMPTY_SNAPSHOT: ProgramListSnapshot =
            ProgramListSnapshot(ProgramCategory.allValueList, emptyMap())
    }
}
