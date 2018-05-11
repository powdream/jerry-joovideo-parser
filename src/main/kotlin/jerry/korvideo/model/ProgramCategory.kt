package jerry.korvideo.model

import java.util.Collections

enum class ProgramCategory(val tableId: String, val categoryName: String) {
    DRAMA("tblDrama", "드라마"),
    ENTERTAINMENT("tblEnt", "오락"),
    DOCUMENTARY("tblDoc", "다큐"),
    EVENT("tblEven", "시사"),
    NEWS("tblNews", "뉴스 / 스포츠");

    companion object {
        val allValueList: List<ProgramCategory> = Collections.unmodifiableList(values().toList())
    }
}
