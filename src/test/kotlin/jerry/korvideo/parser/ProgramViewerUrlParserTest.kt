package jerry.korvideo.parser

import org.junit.Before
import org.junit.Test
import java.io.Reader
import kotlin.test.assertEquals

class ProgramViewerUrlParserTest {
    private lateinit var html: String

    @Before
    fun setUp() {
        html = ClassLoader
            .getSystemClassLoader()
            .getResourceAsStream("program_viewer_page_sample.htm")
            .bufferedReader()
            .use(Reader::readText)
    }

    @Test
    fun parse() {
        val testInstance = ProgramViewerUrlParser(html)
        assertEquals(
            "http://www.dailymotion.com/embed/video/k7KSd4IPvh4pHCqcs5k",
            testInstance.parse()
        )
    }
}
