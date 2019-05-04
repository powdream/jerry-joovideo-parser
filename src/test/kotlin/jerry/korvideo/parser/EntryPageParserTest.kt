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
                .getResourceAsStream("entry_page_sample_20190504.html")
                .bufferedReader()
                .use(Reader::readText)
    }

    @Test
    fun testParse_drama() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.DRAMA)

        val firstSixPrograms = listOf(
                Program(
                        title = "자백",
                        link = "viewlink.aspx?Num=2451919683652812813",
                        description = "13회 05/04 Sat"
                ),
                Program(
                        title = "이몽",
                        link = "viewlink.aspx?Num=2477252380023062529",
                        description = "1회 05/04 Sat",
                        optionalDescription = "* [첫방송] 1 - 2회"
                ),
                Program(
                        title = "세상에서 제일 예쁜 내 딸",
                        link = "viewlink.aspx?Num=2451638208676036621",
                        description = "13회 05/04 Sat",
                        optionalDescription = "* 25 - 26회"
                ),
                Program(
                        title = "녹두꽃",
                        link = "viewlink.aspx?Num=2470496993465335812",
                        description = "4회 05/04 Sat"
                ),
                Program(
                        title = "독립영화관",
                        link = "viewlink.aspx?Num=1111254725750095968",
                        description = "96회 05/04 Sat",
                        optionalDescription = "* 어른도감"
                ),
                Program(
                        title = "시크릿 가든",
                        link = "viewlink.aspx?Num=1854629795818635280",
                        description = "16회 05/04 Sat"
                )
        )
        assertEquals(firstSixPrograms, programList.take(6))

        val lastProgram = Program(
                title = "시크릿 가든",
                link = "viewlink.aspx?Num=1854629782933733389",
                description = "13회 04/27 Sat"
        )
        assertEquals(lastProgram, programList.last())
    }

    @Test
    fun testParse_entertainment() {
        val testInstance = EntryPageParser(html)
        val programList = testInstance.parse(ProgramCategory.ENTERTAINMENT)

        val firstThreePrograms = listOf(
                Program(
                        title = "아는 형님",
                        link = "viewlink.aspx?Num=1471824540367585462",
                        description = "05/04 Sat",
                        optionalDescription = "* 아이즈원"
                ),
                Program(
                        title = "정글의 법칙",
                        link = "viewlink.aspx?Num=681724744413282594",
                        description = "05/04 Sat",
                        optionalDescription = "* in 태즈먼"
                ),
                Program(
                        title = "불후의 명곡 - 전설을 노래...",
                        link = "viewlink.aspx?Num=28984762895565204",
                        description = "05/04 Sat",
                        optionalDescription = "* 스타들의 대단한 도전! 반전을 노래하다 - 반전 무대 특집"
                )
        )
        assertEquals(firstThreePrograms, programList.take(3))

        val lastThreePrograms = listOf(
                Program(
                        title = "알토란",
                        link = "viewlink.aspx?Num=978117637260574950",
                        description = "04/28 Sun",
                        optionalDescription = "* 오감 만족! 집밥의 진수"
                ),
                Program(
                        title = "이제 만나러 갑니다",
                        link = "viewlink.aspx?Num=342547805419864449",
                        description = "04/28 Sun"
                ),
                Program(
                        title = "더 지니어스 - 게임의 법칙",
                        link = "viewlink.aspx?Num=494824122883309570",
                        description = "04/26 Fri",
                        optionalDescription = "* 다시보기"
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
                link = "viewlink.aspx?Num=413477850300022785",
                description = "05/04 Sat"
        )
        assertEquals(firstProgram, programList.first())

        val lastProgram = Program(
                title = "MBC 뉴스투데이",
                link = "viewlink.aspx?Num=1154606459198702732",
                description = "05/02 Thu"
        )
        assertEquals(lastProgram, programList.last())
    }
}
