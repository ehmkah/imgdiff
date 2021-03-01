package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.tools.binary.ThreesideBinaryDiffViewer
import com.intellij.diff.tools.util.ThreeDiffSplitter
import com.intellij.openapi.ui.DialogBuilder
import org.jetbrains.annotations.NotNull
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.BoxLayout


/**
 * Experimentel code.
 *
 * Remove elements which are not needed for Blinking and replacing with elements which are needed.
 * Hopefully this implementation does not break to often :-P
 *
 */
class ImgDiffViewer(context: @NotNull DiffContext, request: @NotNull DiffRequest) : ThreesideBinaryDiffViewer(context, request) {


    fun showBlinkingDiff(imageData: ByteArray) {
        val component: ThreeDiffSplitter = myContentPanel.getComponent(0) as ThreeDiffSplitter
        val contentPanel = component.getComponent(3) as JPanel
        while (contentPanel.componentCount > 0) {
            contentPanel.remove(0)
        }
        addBlinkingDiff(contentPanel, imageData)
    }


    fun addBlinkingDiff(panel: JPanel, imageData: ByteArray) {
        panel.layout = BorderLayout()
        val label = JLabel("If you can't see the complete diff, just click on the (hopefully blinking image). ")
        val icon: Icon = ImageIcon(imageData)
        val button = JButton(icon)
        val actionListener = createActionListener(icon)
        panel.layout = BoxLayout(panel, BoxLayout.PAGE_AXIS)
        button.addActionListener(actionListener)
        panel.add(label)
        panel.add(button)
    }

    private fun createActionListener(icon: Icon): ActionListener {
        val result = object : ActionListener {

            override fun actionPerformed(e: ActionEvent?) {
                val panel = JPanel()
                panel.layout = BorderLayout()
                val label = JLabel("", icon, JLabel.CENTER)
                panel.add(label)
                val builder = DialogBuilder(project)
                builder.setCenterPanel(panel)
                builder.setDimensionServiceKey("FrameSwitcherCloseProjects")
                builder.setTitle("Details View For Image")
                builder.removeAllActions()
                builder.show()
            }

        }
        return result
    }
}