package de.ehmkah.projects.imgdiff;

import java.awt.image.BufferedImage;

/**
 * stolen from https://stackoverflow.com/questions/25022578/highlight-differences-between-images and modified.
 *
 * @author Michael Krausse (ehmkah)
 */
public class DiffedImageCreator {

  private final int PIXEL_HAVE_SAME_VALUE = 16777215;
  private final int PIXEL_HAVE_DIFFERENT_VALUE = 13294074;
  private final int PIXELD_OUT_OF_BOUNDS_VALUE = 16711680;

  public BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
    int width1 = img1.getWidth();
    int width2 = img2.getWidth();
    int height1 = img1.getHeight();
    int height2 = img2.getHeight();
    int targetWidth = width1 > width2 ? width1 : width2;
    int targetHeight = height1 > height2 ? height1 : height2;

    BufferedImage result = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

    for (int currentHeight = 0; currentHeight < targetHeight; currentHeight++) {
      for (int currentWidth = 0; currentWidth < targetWidth; currentWidth++) {
        int diff;
        int diffPixel = 0;
        if (pixelOutOfBounds(currentHeight, currentWidth, img1, img2)) {
          diffPixel = PIXELD_OUT_OF_BOUNDS_VALUE;
        } else {
          int rgb1 = img1.getRGB(currentWidth, currentHeight);
          int rgb2 = img2.getRGB(currentWidth, currentHeight);
          int r1 = (rgb1 >> 16) & 0xff;
          int g1 = (rgb1 >> 8) & 0xff;
          int b1 = (rgb1) & 0xff;
          int r2 = (rgb2 >> 16) & 0xff;
          int g2 = (rgb2 >> 8) & 0xff;
          int b2 = (rgb2) & 0xff;
          diff = Math.abs(r1 - r2);
          diff += Math.abs(g1 - g2);
          diff += Math.abs(b1 - b2);
          diff /= 3;

          diffPixel = (diff << 16) | (diff << 8) | diff;
          diffPixel = diffPixel == 0 ? PIXEL_HAVE_SAME_VALUE : PIXEL_HAVE_DIFFERENT_VALUE;
        }
        result.setRGB(currentWidth, currentHeight, diffPixel);
      }
    }

    return result;
  }

  private boolean pixelOutOfBounds(int currentHeight, int currentWidth, BufferedImage img1, BufferedImage img2) {
    return currentHeight > img1.getHeight() - 1 || currentHeight > img2.getHeight() - 1 ||
            currentWidth > img1.getWidth() - 1 || currentWidth > img2.getWidth() - 1;
  }

}
