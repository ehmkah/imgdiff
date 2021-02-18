package de.ehmkah.projects.imgdiff

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import javax.swing.JPanel
import java.awt.BorderLayout
import javax.swing.ImageIcon
import com.intellij.openapi.ui.DialogBuilder
import javax.swing.Icon
import javax.swing.JLabel

class MotivateAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val panel = JPanel()
        panel.layout = BorderLayout()
        val icon: Icon = ImageIcon(javaClass.getResource("/example3.gif"))
        val label = JLabel("", icon, JLabel.CENTER)
        panel.add(label)
        val builder = DialogBuilder(getEventProject(e))
        builder.setCenterPanel(panel)
        builder.setDimensionServiceKey("FrameSwitcherCloseProjects")
        builder.setTitle("Motivational GIF")
        builder.removeAllActions()
        builder.show()
    }
}