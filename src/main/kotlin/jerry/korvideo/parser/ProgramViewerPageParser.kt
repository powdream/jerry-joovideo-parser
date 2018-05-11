package jerry.korvideo.parser

import jerry.korvideo.model.ProgramViewerData
import jerry.korvideo.model.ProgramViewerFormData
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ProgramViewerPageParser(private val html: String) {
    private val document: Document by lazy {
        Jsoup.parse(html)
    }

    fun parse(): List<ProgramViewerData> {
        val baseFormDataMap = document.select("input[type=hidden]").associate {
            it.attr("name") to it.attr("value")
        }

        val buttonElements = document.select("a[id=btn]")
        return buttonElements.mapIndexed { index, element ->
            val videoSupplierName = element.text()
            val formDataMap =
                baseFormDataMap + generateEventTarget(index) + (KEY_EVENT_ARGUMENT to "")
            ProgramViewerData(videoSupplierName, ProgramViewerFormData(formDataMap))
        }
    }

    private fun generateEventTarget(index: Int): Pair<String, String> {
        val correctedIndex = (index % 6) + 2
        return Pair(
            KEY_EVENT_TARGET,
            "ctl00\$ContentPlaceHolder1\$viewlink1\$GridView1\$ctl0$correctedIndex\$linkBtnHost"
        )
    }

    companion object {
        private const val KEY_EVENT_TARGET = "__EVENTTARGET"
        private const val KEY_EVENT_ARGUMENT = "__EVENTARGUMENT"
    }
}
