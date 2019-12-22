package jerry.video.v3.parser

import jerry.video.common.ParserTemplate
import jerry.video.v3.model.ProgramViewer
import org.jsoup.nodes.Document

class ProgramViewerPageParser(html: String) : ParserTemplate<ProgramViewer>(html) {
    override fun parse(document: Document): ProgramViewer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
