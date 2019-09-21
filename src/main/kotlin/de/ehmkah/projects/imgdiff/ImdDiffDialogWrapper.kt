package de.ehmkah.projects.imgdiff

import com.intellij.openapi.Disposable
import com.intellij.openapi.ui.DialogWrapper
import org.intellij.images.editor.impl.ImageEditorManagerImpl
import java.awt.BorderLayout
import java.awt.image.BufferedImage
import javax.swing.JComponent
import javax.swing.JPanel


/**
 * Displays the diff as a dialog.
 *
 * @author Michael Krausse (ehmkah)
 */
class ImdDiffDialogWrapper(val bufferedImage: BufferedImage) : DialogWrapper(true) {

    lateinit var editorForCleanup: Disposable

    init {
        init()
        title = "ImgDiff"
    }

    override fun createCenterPanel(): JComponent? {
        val result = JPanel(BorderLayout())
        val jPanel = ImageEditorManagerImpl.createImageEditorUI(bufferedImage)
        editorForCleanup = jPanel
        result.add(jPanel, BorderLayout.CENTER)

        return result
    }

    override fun dispose() {
        super.dispose()
        editorForCleanup.dispose()
    }
}

