package de.ehmkah.projects.imgdiff

import com.intellij.openapi.Disposable
import com.intellij.openapi.ui.DialogWrapper
import org.intellij.images.editor.impl.ImageEditorManagerImpl
import java.awt.BorderLayout
import java.awt.image.BufferedImage
import java.util.*
import javax.swing.JComponent
import javax.swing.JPanel


/**
 * Displays the diff as a dialog.
 *
 * @author Michael Krausse (ehmkah)
 */
class ImdDiffDialogWrapper(val bufferedImage: BufferedImage) : DialogWrapper(true) {

    val result = LinkedList<JPanel>()

    init {
        init()
        title = "ImgDiff"
    }

    override fun createCenterPanel(): JComponent? {
        result.add(JPanel(BorderLayout()))
        val jPanel = ImageEditorManagerImpl.createImageEditorUI(bufferedImage)
        result.get(0).add(jPanel, BorderLayout.CENTER)
        return result.get(0)
    }

    // ImageEditorUI
    override fun dispose() {
        super.dispose()
        val component = result.get(0).getComponent(0)
        if (component is Disposable) {
            component.dispose();
        }
        result.get(0).removeAll()
    }
}

