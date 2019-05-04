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
            .getResourceAsStream("program_viewer_top_page_multiple.htm")
            .bufferedReader()
            .use(Reader::readText)
    }

    @Test
    fun parse() {
        val testInstance = ProgramViewerUrlParser(html)
        assertEquals(
            "https://jetload.net/e/x4SrDAuvDQYn",
            testInstance.parse()
        )
    }
}
