package jerry.korvideo.model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ProgramTest {
    @Test
    fun `getUniqueId - relative path`() {
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

    @Test
    fun `getUniqueId - absolute path`() {
        assertEquals(
            "2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "https://korvideo.com/ViewLink.aspx?Num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).uniqueId
        )

        assertEquals(
            "2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "https://korvideo.com/viewlink.aspx?num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).uniqueId
        )
    }

    @Test
    fun `getHost - relative path`() {
        assertNull(
            Program(
                title = "로봇이 아니야",
                link = "ViewLink.aspx?Num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).host
        )

        assertNull(
            Program(
                title = "로봇이 아니야",
                link = "viewlink.aspx?num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).host
        )
    }

    @Test
    fun `getHost - absolute path`() {
        assertEquals(
            "korvideo.com",
            Program(
                title = "로봇이 아니야",
                link = "https://KORVIDEO.COM/ViewLink.aspx?Num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).host
        )

        assertEquals(
            "korvideo.com",
            Program(
                title = "로봇이 아니야",
                link = "https://KORVIDEO.COM/viewlink.aspx?num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).host
        )
    }

    @Test
    fun `getPath - relative path`() {
        assertEquals(
            "ViewLink.aspx?Num=2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "ViewLink.aspx?Num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).path
        )

        assertEquals(
            "viewlink.aspx?num=2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "viewlink.aspx?num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).path
        )
    }

    @Test
    fun `getPath - absolute path`() {
        assertEquals(
            "ViewLink.aspx?Num=2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "https://KORVIDEO.COM/ViewLink.aspx?Num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).path
        )

        assertEquals(
            "viewlink.aspx?num=2101700065911177226",
            Program(
                title = "로봇이 아니야",
                link = "https://KORVIDEO.COM/viewlink.aspx?num=2101700065911177226",
                description = "10회 12/20 Wed"
            ).path
        )
    }
}
