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
                .getResourceAsStream("entry_page_sample.html")
                .bufferedReader()
                .use(Reader::readText)
    }

    @Test
    fun testParse_drama() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.DRAMA)

        val firstSixPrograms = listOf(
                Program(
                        title = "로봇이 아니야",
                        link = "ViewLink.aspx?Num=2101700065911177226",
                        description = "10회 12/20 Wed"
                ),
                Program(
                        title = "이판사판",
                        link = "ViewLink.aspx?Num=2081715372624838673",
                        description = "17회 12/20 Wed"
                ),
                Program(
                        title = "로봇이 아니야",
                        link = "ViewLink.aspx?Num=2101700061616209929",
                        description = "9회 12/20 Wed"
                ),
                Program(
                        title = "이판사판",
                        link = "ViewLink.aspx?Num=2081715376919805970",
                        description = "18회 12/20 Wed"
                ),
                Program(
                        title = "흑기사",
                        link = "ViewLink.aspx?Num=2096070544900816901",
                        description = "5회 12/20 Wed"
                ),
                Program(
                        title = "슬기로운 감빵생활",
                        link = "ViewLink.aspx?Num=2081433863288324105",
                        description = "9회 12/20 Wed"
                )
        )
        assertEquals(firstSixPrograms, programList.take(6))

        val lastSixPrograms = listOf(
                Program(
                        title = "내 남자의 비밀",
                        link = "ViewLink.aspx?Num=1998961901251330105",
                        description = "57회 12/14 Thu"
                ),
                Program(
                        title = "미워도 사랑해",
                        link = "ViewLink.aspx?Num=2072989674114580503",
                        description = "23회 12/14 Thu"
                ),
                Program(
                        title = "너의 등짝에 스매싱",
                        link = "ViewLink.aspx?Num=2101137107367690248",
                        description = "8회 12/14 Thu"
                ),
                Program(
                        title = "해피시스터즈",
                        link = "ViewLink.aspx?Num=2091566962452267017",
                        description = "9회 12/14 Thu"
                ),
                Program(
                        title = "TV소설 꽃피어라 달순아",
                        link = "ViewLink.aspx?Num=1971096011694473304",
                        description = "88회 12/14 Thu"
                ),
                Program(
                        title = "역류",
                        link = "ViewLink.aspx?Num=2044560705755152408",
                        description = "24회 12/14 Thu"
                )
        )
        assertEquals(lastSixPrograms, programList.takeLast(6))
    }

    @Test
    fun testParse_entertainment() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.ENTERTAINMENT)

        val firstSixPrograms = listOf(
                Program(
                        title = "황금어장 라디오스타",
                        link = "ViewLink.aspx?Num=129687989229912449",
                        description = "12/20 Wed",
                        optionalDescription = "* 누가 내 귀에 꿀 발랐어요? 특집-이문세, 이적"
                ),
                Program(
                        title = "한끼줍쇼",
                        link = "ViewLink.aspx?Num=1723960866120859709",
                        description = "12/20 Wed",
                        optionalDescription = "* 경주-김아중, 한혜연"
                ),
                Program(
                        title = "강적들",
                        link = "ViewLink.aspx?Num=640282862662516950",
                        description = "12/20 Wed",
                        optionalDescription = "* 한중 정상회담, 그 결과는?"
                ),
                Program(
                        title = "주간 아이돌",
                        link = "ViewLink.aspx?Num=170220184022221138",
                        description = "12/20 Wed",
                        optionalDescription = "* EXID"
                ),
                Program(
                        title = "수요미식회",
                        link = "ViewLink.aspx?Num=1097679420750954645",
                        description = "12/20 Wed",
                        optionalDescription = "* 초콜릿"
                ),
                Program(
                        title = "내 방을 여행하는 낯선 이를...",
                        link = "ViewLink.aspx?Num=2023168547390619658",
                        description = "12/20 Wed"
                )
        )
        assertEquals(firstSixPrograms, programList.take(6))

        val lastSixPrograms = listOf(
                Program(
                        title = "SBS 애니갤러리",
                        link = "ViewLink.aspx?Num=1376902696497184940",
                        description = "12/14 Thu"
                ),
                Program(
                        title = "올댓뮤직",
                        link = "ViewLink.aspx?Num=839004153316835532",
                        description = "12/14 Thu"
                ),
                Program(
                        title = "보니 하니",
                        link = "ViewLink.aspx?Num=1204653002986228861",
                        description = "12/14 Thu"
                ),
                Program(
                        title = "뽀롱뽀롱 뽀로로",
                        link = "ViewLink.aspx?Num=1825855151311224973",
                        description = "12/14 Thu"
                ),
                Program(
                        title = "수학이 야호",
                        link = "ViewLink.aspx?Num=2013316914613518344",
                        description = "12/14 Thu"
                ),
                Program(
                        title = "딩동댕 유치원",
                        link = "ViewLink.aspx?Num=1204358969525076241",
                        description = "12/14 Thu"
                )
        )
        assertEquals(lastSixPrograms, programList.takeLast(6))
    }
}
