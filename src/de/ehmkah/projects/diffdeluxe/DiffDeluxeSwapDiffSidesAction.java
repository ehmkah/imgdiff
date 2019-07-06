package de.ehmkah.projects.diffdeluxe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.popup.util.MasterDetailPopupBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author Michael Krausse (ehmkah)
 */
public class DiffDeluxeSwapDiffSidesAction extends DumbAwareAction {


  private JBPopup jbPopup;

  @Override
  public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    System.out.println("ds");
    JTextField textField = new JTextField("huu");
    JPanel jPanel = new JPanel(new BorderLayout());
    jPanel.add(textField);

    ComponentPopupBuilder componentPopupBuilder = JBPopupFactory.getInstance().createComponentPopupBuilder(jPanel, textField);
    jbPopup = componentPopupBuilder.createPopup();
              jbPopup.setMinimumSize(new Dimension(200,300));
    AnAction okAction = new DumbAwareAction() {
      @Override
      public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        jbPopup.closeOk(anActionEvent.getInputEvent());
      }
    };


    JComponent source = (JComponent)anActionEvent.getInputEvent().getSource();
    jbPopup.showUnderneathOf(source);

  }
}
