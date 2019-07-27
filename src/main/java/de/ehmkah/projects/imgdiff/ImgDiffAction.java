package de.ehmkah.projects.imgdiff;

import com.intellij.diff.contents.FileContent;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.diff.tools.util.DiffDataKeys;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
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
            new ImdDiffDialogWrapper(bufferedImage).show();
        } catch (Exception e) {
            System.out.println("Bad luck, my friend" + e);
        }
    }

    private BufferedImage readImage(int index, AnActionEvent anActionEvent) throws IOException {
        ByteArrayInputStream inputstream = new ByteArrayInputStream(((FileContent) ((SimpleDiffRequest) anActionEvent.getDataContext().getData(DiffDataKeys.DIFF_REQUEST)).getContents().get(index)).getFile().contentsToByteArray());
        return ImageIO.read(inputstream);
    }

}
