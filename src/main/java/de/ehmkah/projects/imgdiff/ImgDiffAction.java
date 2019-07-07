package de.ehmkah.projects.imgdiff;

import com.intellij.diff.contents.FileContent;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.diff.tools.util.DiffDataKeys;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.intellij.images.editor.impl.ImageEditorManagerImpl;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Michael Krausse (ehmkah)
 */
public class ImgDiffAction extends DumbAwareAction {


  private DiffedImageCreator diffedImageCreator = new DiffedImageCreator();

  @Override
  public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    try {
      BufferedImage bufferedImage = diffedImageCreator.getDifferenceImage(readImage(0, anActionEvent), readImage(1, anActionEvent));
      JPanel jPanel = ImageEditorManagerImpl.createImageEditorUI(bufferedImage);

      ComponentPopupBuilder componentPopupBuilder = JBPopupFactory.getInstance().createComponentPopupBuilder(jPanel, null);
      JBPopup jbPopup = componentPopupBuilder.createPopup();
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
      System.out.println("Bad luck, my friend"+e);
    }
  }

  private BufferedImage readImage(int index, AnActionEvent anActionEvent) throws IOException {
    ByteArrayInputStream inputstream = new ByteArrayInputStream(((FileContent) ((SimpleDiffRequest) anActionEvent.getDataContext().getData(DiffDataKeys.DIFF_REQUEST)).getContents().get(index)).getFile().contentsToByteArray());
    return ImageIO.read(inputstream);
  }

}
