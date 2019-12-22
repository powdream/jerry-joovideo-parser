package jerry.korvideo.parser

import jerry.korvideo.model.Program
import jerry.korvideo.model.ProgramCategory
import org.junit.Before
import org.junit.Test
import java.io.Reader
import kotlin.test.assertEquals

class EntryPageParserTest {
    private lateinit var html: String

    @Before
    fun setUp() {
        html = ClassLoader
            .getSystemClassLoader()
            .getResourceAsStream("entry_page_sample_20191221.html")
            .bufferedReader()
            .use(Reader::readText)
    }

    @Test
    fun testParse_drama() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.DRAMA)

        val firstThreePrograms = listOf(
            Program(
                title = "[웹드라마] 너 말고 연애",
                link = "epList.aspx?q=IwIGgdjiy6HlD2BWz%2b56xGDiBEL1Ps0kl7G50CAv%2f6k%3d",
                description = "2회 12/21 Sat",
                optionalDescription = "* 시즌2"
            ),
            Program(
                title = "초콜릿",
                link = "epList.aspx?q=cb5bd%2bionJAJW8FpNYJF3LljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
                description = "7회 12/20 Fri"
            ),
            Program(
                title = "스토브리그",
                link = "epList.aspx?q=IF577TGNapRw9Ab9hQVaJrljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
                description = "3회 12/20 Fri"
            )
        )
        assertEquals(firstThreePrograms, programList.take(3))

        val lastProgram = Program(
            title = "사랑은 뷰티풀 인생은 원더풀",
            link = "epList.aspx?q=wu5u0jr1v2OTf1EHnTSIR7ljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
            description = "24회 12/15 Sun",
            optionalDescription = "* 47 - 48회"
        )
        assertEquals(lastProgram, programList.last())
    }

    @Test
    fun testParse_entertainment() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.ENTERTAINMENT)

        val firstThreePrograms = listOf(
            Program(
                title = "영화가 좋다",
                link = "epList.aspx?q=RMxVm02CPdrbqCPzoykE%2bLljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
                description = "12/21 Sat"
            ),
            Program(
                title = "접속 무비월드",
                link = "epList.aspx?q=S7f75P3VzMy8OuLoTPSAYLljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
                description = "12/21 Sat"
            ),
            Program(
                title = "옛능 - 라디오스타",
                link = "epList.aspx?q=vvS4vXhchI15Oq%2b%2bKxg4co6zDyKfkyU01WzwUHtuB4SYtZcRT8BTIJOeVV75UJ5h",
                description = "12/21 Sat",
                optionalDescription = "* 압구정에서 나무를 털면 몇명쯤 떨어질 스타일? 소녀시대"
            )
        )
        assertEquals(firstThreePrograms, programList.take(3))

        val lastThreePrograms = listOf(
            Program(
                title = "무엇이든 물어보살",
                link = "epList.aspx?q=5CNkSbuzrETcEDk5XwANtLljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
                description = "12/15 Sun",
                optionalDescription = "* 방송인 사유리 예능 12년 차에 위기가 왔어요"
            ),
            Program(
                title = "최신유행 프로그램",
                link = "epList.aspx?q=L44uES0iAmabLCtoCra6mbljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
                description = "12/15 Sun"
            ),
            Program(
                title = "주x말의영화 잠은행",
                link = "epList.aspx?q=C1LO6spVX%2fkFG4z955rY1LljVQ4qzRlnVw1G4VXDbgVBmKtAgUh8ITocgrxAStfI",
                description = "12/15 Sun"
            )
        )
        assertEquals(lastThreePrograms, programList.takeLast(3))
    }

    @Test
    fun testParse_news_sports() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.NEWS)

        val firstProgram = Program(
            title = "YTN 24시",
            link = "epList.aspx?q=KR%2fl0wqRttDGHAR7XhubtWDiBEL1Ps0kl7G50CAv%2f6k%3d",
            description = "12/21 Sat"
        )
        assertEquals(firstProgram, programList.first())

        val lastProgram = Program(
            title = "SBS 나이트라인",
            link = "epList.aspx?q=yHbG%2fF4e9b3Bl2ZvF65TiY6zDyKfkyU01WzwUHtuB4SYtZcRT8BTIJOeVV75UJ5h",
            description = "12/17 Tue"
        )
        assertEquals(lastProgram, programList.last())
    }
}
