package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.contents.FileContentImpl
import com.intellij.diff.requests.ContentDiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.diff.tools.binary.ThreesideBinaryDiffViewer
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class ImgDiffBlinkingDiffTool : ImgDiffBaseBinaryDiffTool() {

    private val diffedImageCreator = DiffedImageCreator()

    companion object {
        val instance = ImgDiffBlinkingDiffTool()
    }

    override fun createImgDiffDiffViewer(request: ContentDiffRequest, project: Project?, context: DiffContext, filecontent0: VirtualFile, filecontent1: VirtualFile, contentTitle0: String?, contentTitle1: String?): ThreesideBinaryDiffViewer {
        val bufferedImage0 = ImageIO.read(filecontent0.inputStream)
        val bufferedImage1 = ImageIO.read(filecontent1.inputStream)
        val differenceImage = bufferedImage0
        val diffContent0 = FileContentImpl(project, filecontent0)
        val diffContent1 = FileContentImpl(project, filecontent1)

        val diffContentDifference = FileContentImpl(project, ImgDiffVirtualFile(differenceImage, filecontent0))

        val myRequest = SimpleDiffRequest(request.title,
                diffContent0, diffContentDifference, diffContent1,
                contentTitle0, "Diff Image", contentTitle1)

        val result = ImgDiffViewer(context, myRequest)
        val outputStream = ByteArrayOutputStream()

        diffedImageCreator.createGifImage(bufferedImage0, bufferedImage1, outputStream)

        result.showBlinkingDiff(outputStream.toByteArray())

        return result
    }

    override fun createDiffImage(bufferedImage0: BufferedImage, bufferedImage1: BufferedImage): BufferedImage {
        return bufferedImage0

    }

    override fun getName(): String {
        return "ImageDiff - Blinking Diff (experimental)"
    }
}