package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.tools.binary.ThreesideBinaryDiffViewer
import com.intellij.diff.tools.util.ThreeDiffSplitter
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NotNull
import java.awt.BorderLayout
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class ImgDiffViewer(context: @NotNull DiffContext, request: @NotNull DiffRequest) : ThreesideBinaryDiffViewer(context, request) {

    fun foo() {
        val component: ThreeDiffSplitter = myContentPanel.getComponent(0) as ThreeDiffSplitter
        val aContentPanel = component.getComponent(3) as JPanel;
        while (aContentPanel.componentCount>0) {
            aContentPanel.remove(0)
        }
        fooIntern(aContentPanel)
    }


    fun fooIntern(panel: JPanel) {
        panel.layout = BorderLayout()
        val icon: Icon = ImageIcon(javaClass.getResource("/example3.gif"))
        val label = JLabel("", icon, JLabel.CENTER)

        panel.add(label)
    }
}