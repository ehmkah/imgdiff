package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.FrameDiffTool
import com.intellij.diff.contents.FileContentImpl
import com.intellij.diff.requests.ContentDiffRequest
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.diff.tools.binary.BinaryDiffTool
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

class ImgDiffBinaryDiffTool : BinaryDiffTool() {

    private val diffedImageCreator = DiffedImageCreator()
    private val emptyImage = ImageIO.read(DiffedImageCreator::class.java.getResourceAsStream("/empty.png"))

    companion object {
        val instance = ImgDiffBinaryDiffTool()
    }

    override fun createComponent(context: DiffContext, request: DiffRequest): FrameDiffTool.DiffViewer {
        if (canShowRequest(request) && (request is ContentDiffRequest)) {
            val diffContent0 = request.contents[0]
            val diffContent1 = request.contents[1]
            var bufferedImage0 = emptyImage
            var bufferedImage1 = emptyImage

            if (diffContent0 is FileContentImpl) {
                bufferedImage0 = ImageIO.read(ByteArrayInputStream(diffContent0.file.contentsToByteArray()))
            }
            if (diffContent1 is FileContentImpl) {
                bufferedImage1 = ImageIO.read(ByteArrayInputStream(diffContent1.file.contentsToByteArray()))
            }
            return createImgDiffDiffViewer(request, context, bufferedImage0, bufferedImage1, request.contentTitles[0], request.contentTitles[1])
        }
        throw IllegalArgumentException(request.toString())
    }

    private fun createImgDiffDiffViewer(request: ContentDiffRequest, context: DiffContext, bufferedImage0: BufferedImage, bufferedImage1: BufferedImage, contentTitle0: String, contentTitle1: String): ImgDiffDiffViewer {
        var diffContent0 = FileContentImpl(null, ImgDiffVirtualFile(emptyImage))
        var diffContentDifference = FileContentImpl(null, ImgDiffVirtualFile(emptyImage))
        var diffContent1 = FileContentImpl(null, ImgDiffVirtualFile(emptyImage))

        if (bufferedImage0 == emptyImage) {
            diffContentDifference = FileContentImpl(null, ImgDiffVirtualFile(bufferedImage1))
        } else if (bufferedImage1 == emptyImage) {
            diffContentDifference = FileContentImpl(null, ImgDiffVirtualFile(bufferedImage0))
        } else {
            val differenceImage = diffedImageCreator.getDifferenceImage(bufferedImage0, bufferedImage1)
            diffContent0 = FileContentImpl(null, ImgDiffVirtualFile(bufferedImage0))
            diffContentDifference = FileContentImpl(null, ImgDiffVirtualFile(differenceImage))
            diffContent1 = FileContentImpl(null, ImgDiffVirtualFile(bufferedImage1))
        }
        val myRequest = SimpleDiffRequest(request.title,
                diffContent0, diffContentDifference, diffContent1,
                contentTitle0, "Diff Image", contentTitle1)

        return ImgDiffDiffViewer(context, myRequest)
    }

    override fun canShow(context: DiffContext, request: DiffRequest): Boolean {
        return super.canShow(context, request) || canShowRequest(request)
    }

    override fun getName(): String {
        return "ImageDiff"
    }
}