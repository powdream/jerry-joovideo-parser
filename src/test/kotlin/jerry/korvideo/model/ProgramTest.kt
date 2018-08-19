package jerry.korvideo.model

import org.junit.Test
import kotlin.test.assertEquals

class ProgramTest {
    @Test
    fun getUniqueId() {
        assertEquals(
            "2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "ViewLink.aspx?Num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).uniqueId
        )

        assertEquals(
            "2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "viewlink.aspx?num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).uniqueId
        )
    }
}
