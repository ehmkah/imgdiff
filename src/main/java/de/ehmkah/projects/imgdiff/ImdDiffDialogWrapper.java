package de.ehmkah.projects.imgdiff;

import com.intellij.openapi.ui.DialogWrapper;
import org.intellij.images.editor.impl.ImageEditorManagerImpl;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * Displays the diff as a dialog.
 *
 * @author Michael Krausse (ehmkah)
 */
class ImdDiffDialogWrapper extends DialogWrapper {

    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 800;
    private static final int MARGIN_HEIGHT = 50;
    private static final int MARGIN_WIDTH = 50;

    private BufferedImage bufferedImage;

    ImdDiffDialogWrapper(BufferedImage bufferedImage) {
        super(true); // use current window as parent
        this.bufferedImage = bufferedImage;
        init();
        setTitle("ImgDiff");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel result = new JPanel(new BorderLayout());
        JPanel jPanel = ImageEditorManagerImpl.createImageEditorUI(bufferedImage);
        int width = calculateWidth(bufferedImage.getWidth());
        int height = calculateHeight(bufferedImage.getHeight());
        jPanel.setPreferredSize(new Dimension(width, height));
        result.add(jPanel, BorderLayout.CENTER);

        return result;
    }

    private int calculateHeight(int height) {
        return height > MAX_HEIGHT ? MAX_HEIGHT : height + MARGIN_HEIGHT;
    }

    private int calculateWidth(int width) {
        return width > MAX_WIDTH ? MAX_WIDTH : width + MARGIN_WIDTH;
    }
}
