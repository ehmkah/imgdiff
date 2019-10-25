package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.FrameDiffTool
import com.intellij.diff.contents.FileContentImpl
import com.intellij.diff.requests.ContentDiffRequest
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.diff.tools.binary.BinaryDiffTool
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

class ImgDiffBinaryDiffTool : BinaryDiffTool() {


    private val diffedImageCreator = DiffedImageCreator()

    companion object {
        val instance = ImgDiffBinaryDiffTool()
    }

    override fun createComponent(context: DiffContext, request: DiffRequest): FrameDiffTool.DiffViewer {
        if (canShowRequest(context, request) && (request is ContentDiffRequest)) {
            val diffContent0 = request.contents[0]
            val diffContent1 = request.contents[1]
            if (diffContent0 is FileContentImpl && diffContent1 is FileContentImpl) {
                val bufferedImage0 = ImageIO.read(ByteArrayInputStream(diffContent0.file.contentsToByteArray()))
                val bufferedImage1 = ImageIO.read(ByteArrayInputStream(diffContent1.file.contentsToByteArray()))
                val differenceImage = diffedImageCreator.getDifferenceImage(bufferedImage0, bufferedImage1)
                val imgDiffVirtualFile = ImgDiffVirtualFile(differenceImage)
                val diffContent = FileContentImpl(null, imgDiffVirtualFile)
                val myRequest = SimpleDiffRequest(request.title, request.contents[0], diffContent, request.contents[1],
                        request.contentTitles[0], "diff", request.contentTitles[1])
                return ImgDiffDiffViewer(context, myRequest)
            }
        }
        throw IllegalArgumentException(request.toString())
    }

    override fun canShow(context: DiffContext, request: DiffRequest): Boolean {
        return super.canShow(context, request) || canShowRequest(context, request)
    }

    override fun getName(): String {
        return "ImageDiff"
    }
}