package de.ehmkah.projects.imgdiff;

import com.intellij.diff.contents.FileContent;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.diff.tools.util.DiffDataKeys;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.DialogWrapper;
import org.intellij.images.editor.impl.ImageEditorManagerImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
            new SampleDialogWrapper(bufferedImage).show();
        } catch (Exception e) {
            System.out.println("Bad luck, my friend" + e);
        }
    }

    private BufferedImage readImage(int index, AnActionEvent anActionEvent) throws IOException {
        ByteArrayInputStream inputstream = new ByteArrayInputStream(((FileContent) ((SimpleDiffRequest) anActionEvent.getDataContext().getData(DiffDataKeys.DIFF_REQUEST)).getContents().get(index)).getFile().contentsToByteArray());
        return ImageIO.read(inputstream);
    }

    private class SampleDialogWrapper extends DialogWrapper {

        private BufferedImage bufferedImage;

        public SampleDialogWrapper(BufferedImage bufferedImage) {
            super(true); // use current window as parent
            this.bufferedImage = bufferedImage;
            init();
            setTitle("Test DialogWrapper");
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            JPanel dialogPanel = new JPanel(new BorderLayout());
            JPanel jPanel = ImageEditorManagerImpl.createImageEditorUI(bufferedImage);
            jPanel.setPreferredSize(new Dimension(300, 200));
            dialogPanel.add(jPanel, BorderLayout.CENTER);

            return dialogPanel;
        }
    }

}
