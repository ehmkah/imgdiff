package de.ehmkah.projects.imgdiff

import com.intellij.diff.contents.DiffContent
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.intellij.images.fileTypes.impl.ImageFileType
import org.junit.Test
import kotlin.test.assertTrue

internal class ImgDiffBinaryDiffToolTest {

    private val sut = ImgDiffBinaryDiffTool()

    @Test
    fun testIsImage() {
        val request = mock<DiffContent> {
            on { contentType } doReturn ImageFileType.INSTANCE
        }
        assertTrue(sut.isValidImage(request))
    }

}
