package de.ehmkah.projects.diffdeluxe;

import de.ehmkah.projects.imgdiff.DiffedImageCreator;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
    ImageIO.write(actual, "PNG", new File("output.png"));
    BufferedImage expected = readImage("/expected.png");

    Assert.assertEquals(expected, actual);

  }

  private BufferedImage readImage(String resourcePath) throws IOException {
    return ImageIO.read(DiffedImageCreatorTest.class.getResourceAsStream(resourcePath));
  }
}
