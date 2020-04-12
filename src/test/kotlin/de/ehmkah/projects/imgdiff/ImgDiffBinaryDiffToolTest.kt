package de.ehmkah.projects.imgdiff

import com.intellij.diff.contents.DiffContent
import com.intellij.openapi.fileTypes.FileType
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertTrue

internal class ImgDiffBinaryDiffToolTest {

    private val sut = ImgDiffBinaryDiffTool()

    @Test
    fun testIsImage() {
        val contentTypeMock = mock<FileType> {
            on { name } doReturn "Image"
        }

        val request = mock<DiffContent> {
            on { contentType } doReturn contentTypeMock
        }
        assertTrue(sut.isValidImage(request))
    }

}
