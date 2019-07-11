package de.ehmkah.projects.diffdeluxe;

import de.ehmkah.projects.imgdiff.DiffedImageCreator;
import org.junit.Assert;
import org.junit.Test;
import sun.awt.image.ByteInterleavedRaster;
import sun.awt.image.IntegerInterleavedRaster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

/**
 * @author Michael Krausse (ehmkah)
 */
public class DiffedImageCreatorTest {

  private DiffedImageCreator sut = new DiffedImageCreator();

  @Test
  public void testDiff() throws Exception {
    BufferedImage original= readImage("/original.png");
    BufferedImage changed = readImage("/modified.png");

    BufferedImage actual = sut.getDifferenceImage(original, changed);
    BufferedImage expected = readImage("/expected.png");

    Assert.assertTrue(compareImages(expected, actual));
  }

  private BufferedImage readImage(String resourcePath) throws IOException {
    BufferedImage result = ImageIO.read(DiffedImageCreatorTest.class.getResourceAsStream(resourcePath));
    return result;
  }

  /**
   * stolen from https://stackoverflow.com/questions/11006394/is-there-a-simple-way-to-compare-bufferedimage-instances
   */
  private boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
    // The images must be the same size.
    if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
      return false;
    }

    int width  = imgA.getWidth();
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
