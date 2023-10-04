package de.ehmkah.projects.imgdiff

import com.intellij.diff.contents.DiffContent
import com.intellij.diff.contents.FileContentImpl
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

internal class ImgDiffBaseBinaryDiffToolTest {

    @Test
    fun testIsImage() {
        val request = prepareMocks("/original.png")

        assertTrue(isValidImage(request))
    }

    @Test
    fun testDetectContentIsCorruptImage() {
        val request = prepareMocks("/NotAnImage.png")

        assertFalse(isValidImage(request))
    }

    private fun prepareMocks(imageFile: String): FileContentImpl {
        val contentTypeMock = mock<FileType> {
            on { name } doReturn "Image"
        }

        val inputStream = ImgDiffBaseBinaryDiffToolTest::class.java.getResourceAsStream(imageFile)
        val mockedFile = mock<VirtualFile> {
            on { getInputStream() } doReturn inputStream
        }

        val request = mock<FileContentImpl> {
            on { contentType } doReturn contentTypeMock
            on { file } doReturn mockedFile
        }
        return request
    }

}
