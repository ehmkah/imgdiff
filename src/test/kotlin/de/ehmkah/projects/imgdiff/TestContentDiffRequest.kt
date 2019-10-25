package de.ehmkah.projects.imgdiff

import com.intellij.diff.contents.DiffContent
import com.intellij.diff.contents.EmptyContent
import com.intellij.diff.requests.ContentDiffRequest

class TestContentDiffRequest : ContentDiffRequest() {

    val contentsInternal: MutableList<DiffContent> = MutableList(2) { index -> EmptyContent() }

    override fun getTitle(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContents(): MutableList<DiffContent> {
        return contentsInternal;
    }

    override fun getContentTitles(): MutableList<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}