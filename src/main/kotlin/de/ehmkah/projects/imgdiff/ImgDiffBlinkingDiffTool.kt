package de.ehmkah.projects.imgdiff

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.ui.DialogBuilder
import java.awt.BorderLayout
import java.awt.Graphics
import java.awt.Image
import java.awt.image.BufferedImage
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class ImgDiffBlinkingDiffTool : ImgDiffBaseBinaryDiffTool() {

    private val diffedImageCreator = DiffedImageCreator()

    companion object {
        val instance = ImgDiffBlinkingDiffTool()
    }

    override fun createDiffImage(bufferedImage0: BufferedImage, bufferedImage1: BufferedImage): BufferedImage {
        val icon = ImageIcon(javaClass.getResource("/example3.gif"))
        val image: Image = icon.image

        // Create empty BufferedImage, sized to Image

        // Create empty BufferedImage, sized to Image
        val buffImage = BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB)

        // Draw Image into BufferedImage

        // Draw Image into BufferedImage
        val g: Graphics = buffImage.graphics
        g.drawImage(image, 0, 0, null)
        return buffImage
    }




    override fun getName(): String {
        return "ImageDiff - Blinking Diff"
    }
}