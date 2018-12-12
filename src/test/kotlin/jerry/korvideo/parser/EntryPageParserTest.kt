package jerry.korvideo.parser

import jerry.korvideo.model.Program
import jerry.korvideo.model.ProgramCategory
import org.junit.Before
import org.junit.Test
import java.io.Reader
import kotlin.test.assertEquals

@Suppress("FunctionName")
class EntryPageParserTest {
    private lateinit var html: String

    @Before
    fun setUp() {
        html = ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream("entry_page_sample_20181212.html")
                .bufferedReader()
                .use(Reader::readText)
    }

    @Test
    fun testParse_drama() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.DRAMA)

        val firstSixPrograms = listOf(
                Program(
                        title = "차달래 부인의 사랑",
                        link = "viewlink.aspx?Num=2313434252776964169",
                        description = "73회 12/12 Wed"
                ),
                Program(
                        title = "강남 스캔들",
                        link = "viewlink.aspx?Num=2372543740201926669",
                        description = "13회 12/12 Wed"
                ),
                Program(
                        title = "계룡선녀전",
                        link = "viewlink.aspx?Num=2362410636743016460",
                        description = "12회 12/11 Tue"
                ),
                Program(
                        title = "은주의 방",
                        link = "viewlink.aspx?Num=2368040110508736518",
                        description = "6회 12/11 Tue"
                ),
                Program(
                        title = "나쁜형사",
                        link = "viewlink.aspx?Num=2382958275687940100",
                        description = "4회 12/11 Tue",
                        optionalDescription = "* 7 - 8회"
                ),
                Program(
                        title = "복수가 돌아왔다",
                        link = "viewlink.aspx?Num=2382113842167676930",
                        description = "2회 12/11 Tue",
                        optionalDescription = "* 3 - 4회"
                )
        )
        assertEquals(firstSixPrograms, programList.take(6))

        val lastSixPrograms = listOf(
                Program(
                        title = "비밀과 거짓말",
                        link = "viewlink.aspx?Num=2249258061151207521",
                        description = "97회 12/05 Wed"
                ),
                Program(
                        title = "차달래 부인의 사랑",
                        link = "viewlink.aspx?Num=2313434231302127684",
                        description = "68회 12/05 Wed"
                ),
                Program(
                        title = "강남 스캔들",
                        link = "viewlink.aspx?Num=2372543718727090184",
                        description = "8회 12/05 Wed"
                )
        )
        assertEquals(lastSixPrograms, programList.takeLast(3))
    }

    @Test
    fun testParse_entertainment() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.ENTERTAINMENT)

        val firstThreePrograms = listOf(
                Program(
                        title = "랄랄라 뿌우",
                        link = "viewlink.aspx?Num=2194651816872378442",
                        description = "12/12 Wed"
                ),
                Program(
                        title = "딩동댕 유치원",
                        link = "viewlink.aspx?Num=1204143327807078929",
                        description = "12/12 Wed"
                ),
                Program(
                        title = "불타는 청춘",
                        link = "viewlink.aspx?Num=1181342372924489912",
                        description = "12/11 Tue",
                        optionalDescription = "* 월동준비 김장하는 날"
                )
        )
        assertEquals(firstThreePrograms, programList.take(3))

        val lastThreePrograms = listOf(
                Program(
                        title = "비상소집 - 전국이장회의",
                        link = "viewlink.aspx?Num=2364662397902520323",
                        description = "12/05 Wed"
                ),
                Program(
                        title = "인싸채널 체리블렛",
                        link = "viewlink.aspx?Num=2383521217051557890",
                        description = "12/05 Wed"
                ),
                Program(
                        title = "스타트업 빅뱅",
                        link = "viewlink.aspx?Num=2364380935810646022",
                        description = "12/05 Wed",
                        optionalDescription = "* 왕중왕전에 진출할 TOP10"
                )
        )
        assertEquals(lastThreePrograms, programList.takeLast(3))
    }
}
