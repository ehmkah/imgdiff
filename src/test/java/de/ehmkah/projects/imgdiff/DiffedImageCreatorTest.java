package de.ehmkah.projects.imgdiff;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * @author Michael Krausse (ehmkah)
 */
public class DiffedImageCreatorTest {

    private DiffedImageCreator sut = new DiffedImageCreator();

    @Test
    public void testDiff() throws Exception {
        BufferedImage original = readImage("/original.png");
        BufferedImage changed = readImage("/modified.png");

        BufferedImage actual = sut.getDifferenceImage(original, changed);
        BufferedImage expected = readImage("/expected.png");

        assertTrue(compareImages(expected, actual));
    }

    @Test
    public void testDiffDiffer() throws Exception {
        BufferedImage original = readImage("/original.png");
        BufferedImage changed = readImage("/modified.png");

        BufferedImage actual = sut.getDifferenceImage(original, changed);
        BufferedImage expected = readImage("/expected.png");

        assertTrue(compareImages(expected, actual));
    }

    @Test
    public void testDiffDiffer2() throws Exception {
        BufferedImage original = readImage("/smallBlack.png");
        BufferedImage changed = readImage("/smallBlackWithBorder.png");

        BufferedImage actual = sut.getDifferenceImage(original, changed);
        BufferedImage expected = readImage("/smallBlackExpected.png");

        assertTrue(compareImages(expected, actual));
    }

    @Test
    public void testDifferentSize() throws Exception {
        BufferedImage original = readImage("/original.png");
        BufferedImage changed = readImage("/modifiedDifferentSize.png");

        BufferedImage actual = sut.getDifferenceImage(original, changed);
        BufferedImage expected = readImage("/expectedDifferentSize.png");

        assertTrue(compareImages(expected, actual));
    }

    @Test
    public void testImagesHaveNoDiff() throws IOException {
        BufferedImage original = readImage("/identical.png");
        BufferedImage changed = readImage(("/identical.png"));
        BufferedImage expected = readImage("/identical.png");

        BufferedImage actual = sut.getDifferenceImage(original, changed);

        assertTrue(compareImages(expected, actual));

    }

    private BufferedImage readImage(String resourcePath) throws IOException {
        BufferedImage result = ImageIO.read(DiffedImageCreatorTest.class.getResourceAsStream(resourcePath));
        return result;
    }

    /**
     * stolen from https://stackoverflow.com/questions/11006394/is-there-a-simple-way-to-compare-bufferedimage-instances
     */
    private boolean compareImages(BufferedImage imgA, BufferedImage imgB) throws IOException {

        ImageIO.write(imgA, "png", new File("imgA.png"));
        ImageIO.write(imgB, "png", new File("imgB.png"));

        // The images must be the same size.
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        int width = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {

                    return false;
                }
            }
        }

        return true;
    }
}