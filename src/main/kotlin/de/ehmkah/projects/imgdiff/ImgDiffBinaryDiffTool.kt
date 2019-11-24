package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.contents.DiffContent
import com.intellij.diff.contents.EmptyContent
import com.intellij.diff.contents.FileContentImpl
import com.intellij.diff.requests.ContentDiffRequest
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.diff.tools.binary.BinaryDiffTool
import com.intellij.diff.tools.binary.ThreesideBinaryDiffViewer
import com.intellij.openapi.vfs.VirtualFile
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

class ImgDiffBinaryDiffTool : BinaryDiffTool() {

    private val diffedImageCreator = DiffedImageCreator()

    companion object {
        val instance = ImgDiffBinaryDiffTool()
    }

    override fun createComponent(context: DiffContext, request: DiffRequest): ThreesideBinaryDiffViewer {
        if (canShow(context, request) && (request is ContentDiffRequest)) {
            val diffContent0 = request.contents[0]
            val diffContent1 = request.contents[1]
            if (diffContent0 is FileContentImpl && diffContent1 is FileContentImpl) {
                return createImgDiffDiffViewer(request, context,diffContent0.file, diffContent1.file, request.contentTitles[0], request.contentTitles[1])
            }

        }
        throw IllegalArgumentException(request.toString())
    }

    private fun createImgDiffDiffViewer(request: ContentDiffRequest, context: DiffContext, filecontent0:VirtualFile, filecontent1: VirtualFile, contentTitle0: String, contentTitle1: String): ThreesideBinaryDiffViewer {
        val bufferedImage0 = ImageIO.read(ByteArrayInputStream(filecontent0.contentsToByteArray()))
        val bufferedImage1 = ImageIO.read(ByteArrayInputStream(filecontent1.contentsToByteArray()))
        val differenceImage = diffedImageCreator.getDifferenceImage(bufferedImage0, bufferedImage1)
        val diffContent0 = FileContentImpl(null, filecontent0)
        val diffContent1 = FileContentImpl(null, filecontent1)

        val diffContentDifference = FileContentImpl(null, ImgDiffVirtualFile(differenceImage, filecontent0))

        val myRequest = SimpleDiffRequest(request.title,
                diffContent0, diffContentDifference, diffContent1,
                contentTitle0, "Diff Image", contentTitle1)

        return ThreesideBinaryDiffViewer(context, myRequest)
    }

    override fun canShow(context: DiffContext, request: DiffRequest): Boolean {
        if (request is ContentDiffRequest) {
            return request.contents.size == 2 &&
                    isValidImage(request.contents[0]) &&
                    isValidImage(request.contents[1])
        }

        return false
    }

    private fun isValidImage(diffContent: DiffContent): Boolean {
        return diffContent !is EmptyContent && diffContent.contentType.toString().equals("Image")
    }

    override fun getName(): String {
        return "ImageDiff"
    }
}