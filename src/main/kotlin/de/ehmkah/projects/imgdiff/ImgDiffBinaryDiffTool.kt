package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.FrameDiffTool
import com.intellij.diff.contents.EmptyContent
import com.intellij.diff.contents.FileContentImpl
import com.intellij.diff.requests.ContentDiffRequest
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.diff.tools.binary.BinaryDiffTool
import com.intellij.diff.tools.binary.ThreesideBinaryDiffViewer
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

class ImgDiffBinaryDiffTool : BinaryDiffTool() {

    private val diffedImageCreator = DiffedImageCreator()

    companion object {
        val instance = ImgDiffBinaryDiffTool()
    }

    override fun createComponent(context: DiffContext, request: DiffRequest): FrameDiffTool.DiffViewer {
        if (canShowRequest(request) && (request is ContentDiffRequest)) {
            val diffContent0 = request.contents[0]
            val diffContent1 = request.contents[1]
            if (diffContent0 is FileContentImpl && diffContent1 is FileContentImpl) {
                val bufferedImage0 = ImageIO.read(ByteArrayInputStream(diffContent0.file.contentsToByteArray()))
                val bufferedImage1 = ImageIO.read(ByteArrayInputStream(diffContent1.file.contentsToByteArray()))
                return createImgDiffDiffViewer(request, context, bufferedImage0, bufferedImage1, request.contentTitles[0], request.contentTitles[1])
            }

        }
        throw IllegalArgumentException(request.toString())
    }

    private fun createImgDiffDiffViewer(request: ContentDiffRequest, context: DiffContext, bufferedImage0: BufferedImage, bufferedImage1: BufferedImage, contentTitle0: String, contentTitle1: String): ThreesideBinaryDiffViewer {
        val differenceImage = diffedImageCreator.getDifferenceImage(bufferedImage0, bufferedImage1)
        val diffContent0 = FileContentImpl(null, ImgDiffVirtualFile(bufferedImage0))
        val diffContentDifference = FileContentImpl(null, ImgDiffVirtualFile(differenceImage))
        val diffContent1 = FileContentImpl(null, ImgDiffVirtualFile(bufferedImage1))
        val myRequest = SimpleDiffRequest(request.title,
                diffContent0, diffContentDifference, diffContent1,
                contentTitle0, "Diff Image", contentTitle1)

        return ThreesideBinaryDiffViewer(context, myRequest)
    }

    override fun canShow(context: DiffContext, request: DiffRequest): Boolean {
        return canShowRequest(request)
    }

    fun canShowRequest(request: DiffRequest): Boolean {
        if (request is ContentDiffRequest) {
            return request.contents.size == 2 && request.contents[0] !is EmptyContent && request.contents[1] !is EmptyContent
        }

        return false
    }

    override fun getName(): String {
        return "ImageDiff"
    }
}