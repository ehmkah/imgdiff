package de.ehmkah.projects.diffdeluxe;

import java.awt.image.BufferedImage;

/**
 * @author Michael Krausse (ehmkah)
 */
public class DiffedImageCreator {

  public BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
    int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
    int width2 = img2.getWidth(); // take no arguments
    int height1 = img1.getHeight();
    int height2 = img2.getHeight();
    if ((width1 != width2) || (height1 != height2)) {
      System.err.println("Error: Images dimensions mismatch");
      System.exit(1);
    }

    // NEW - Create output Buffered image of type RGB
    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

    // Modified - Changed to int as pixels are ints
    int diff;
    int result; // Stores output pixel
    for (int i = 0; i < height1; i++) {
      for (int j = 0; j < width1; j++) {
        int rgb1 = img1.getRGB(j, i);
        int rgb2 = img2.getRGB(j, i);
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >> 8) & 0xff;
        int b1 = (rgb1) & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >> 8) & 0xff;
        int b2 = (rgb2) & 0xff;
        diff = Math.abs(r1 - r2); // Change
        diff += Math.abs(g1 - g2);
        diff += Math.abs(b1 - b2);
        diff /= 3; // Change - Ensure result is between 0 - 255
        // Make the difference image gray scale
        // The RGB components are all the same
        result = (diff << 16) | (diff << 8) | diff;
        outImg.setRGB(j, i, result); // Set result
      }
    }
    return outImg;
  }

  
}
