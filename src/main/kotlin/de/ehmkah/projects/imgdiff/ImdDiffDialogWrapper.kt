package de.ehmkah.projects.imgdiff

import com.intellij.openapi.ui.DialogWrapper
import org.intellij.images.editor.impl.ImageEditorManagerImpl

import javax.swing.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.image.BufferedImage

/**
 * Displays the diff as a dialog.
 *
 * @author Michael Krausse (ehmkah)
 */
internal class ImdDiffDialogWrapper(private val bufferedImage: BufferedImage) : DialogWrapper(true) {

    init {
        init()
        title = "ImgDiff"
    }// use current window as parent

    override fun createCenterPanel(): JComponent? {
        val result = JPanel(BorderLayout())
        val jPanel = ImageEditorManagerImpl.createImageEditorUI(bufferedImage)
        //val width = calculateWidth(bufferedImage.width)
        //val height = calculateHeight(bufferedImage.height)
        //jPanel.setPreferredSize(Dimension(width, height))
        result.add(jPanel, BorderLayout.CENTER)

        return result
    }

    private fun calculateHeight(height: Int): Int {
        return if (height > MAX_HEIGHT) MAX_HEIGHT else height + MARGIN_HEIGHT
    }

    private fun calculateWidth(width: Int): Int {
        return if (width > MAX_WIDTH) MAX_WIDTH else width + MARGIN_WIDTH
    }

    companion object {

        private val MAX_WIDTH = 1024
        private val MAX_HEIGHT = 800
        private val MARGIN_HEIGHT = 50
        private val MARGIN_WIDTH = 50
    }
}
