package de.ehmkah.projects.imgdiff

import com.intellij.diff.contents.FileContent
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.diff.tools.util.DiffDataKeys
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.IOException

/**
 * @author Michael Krausse (ehmkah)
 */
class ImgDiffAction : DumbAwareAction() {

    private val diffedImageCreator = DiffedImageCreator()

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        try {
            val bufferedImage = diffedImageCreator.getDifferenceImage(readImage(0, anActionEvent), readImage(1, anActionEvent))
            ImdDiffDialogWrapper(bufferedImage).show()
        } catch (e: Exception) {
            println("Bad luck, my friend$e")
        }

    }

    @Throws(IOException::class)
    private fun readImage(index: Int, anActionEvent: AnActionEvent): BufferedImage {
        val inputstream = ByteArrayInputStream(((anActionEvent.dataContext.getData<DiffRequest>(DiffDataKeys.DIFF_REQUEST) as SimpleDiffRequest).contents[index] as FileContent).file.contentsToByteArray())
        return ImageIO.read(inputstream)
    }

}
