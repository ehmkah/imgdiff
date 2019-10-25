package de.ehmkah.projects.imgdiff;


import com.intellij.diff.DiffContext
import com.intellij.diff.contents.DiffContent
import com.intellij.diff.contents.EmptyContent
import com.intellij.diff.requests.ContentDiffRequest
import org.junit.Assert
import org.junit.Test;

class ImgDiffDiffViewerTest {

    @Test
    fun testCanHandle() {
        // GIVEN
        val context = TestDiffContext()
        val request = TestContentDiffRequest();
        // WHEN
        val actualCanShowRequest = canShowRequest(context, request)

        // THEN
        Assert.assertTrue(actualCanShowRequest)
    }

}

