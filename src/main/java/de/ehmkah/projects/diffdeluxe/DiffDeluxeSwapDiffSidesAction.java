package de.ehmkah.projects.diffdeluxe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.popup.util.MasterDetailPopupBuilder;
import org.intellij.images.editor.ImageEditor;
import org.intellij.images.editor.ImageFileEditor;
import org.intellij.images.editor.impl.ImageEditorManagerImpl;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Michael Krausse (ehmkah)
 */
public class DiffDeluxeSwapDiffSidesAction extends DumbAwareAction {


  private JBPopup jbPopup;

  @Override
  public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    System.out.println("ds");

    JTextField textField = new JTextField("huu");
    try {
      BufferedImage bufferedImage = ImageIO.read(new File("/Users/michi/IdeaProjects/untitled10/src/2019022101.JPG"));
      JPanel jPanel = ImageEditorManagerImpl.createImageEditorUI(bufferedImage);

      //jPanel.add(textField);

      ComponentPopupBuilder componentPopupBuilder = JBPopupFactory.getInstance().createComponentPopupBuilder(jPanel, null);
      jbPopup = componentPopupBuilder.createPopup();
      jbPopup.setMinimumSize(new Dimension(200, 300));
      AnAction okAction = new DumbAwareAction() {
        @Override
        public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
          jbPopup.closeOk(anActionEvent.getInputEvent());
        }
      };


      JComponent source = (JComponent) anActionEvent.getInputEvent().getSource();
      jbPopup.showUnderneathOf(source);
    } catch(Exception e) {
      System.out.println("Pech gehabt");
    }

  }
}
