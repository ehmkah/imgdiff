package de.ehmkah.projects.imgdiff;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

import java.awt.image.BufferedImage;

/**
 *
 * stolen from https://stackoverflow.com/questions/25022578/highlight-differences-between-images and modified.
 * 
 * @author Michael Krausse (ehmkah)
 */
public class DiffedImageCreator {

  private final int PIXEL_HAVE_SAME_VALUE =  16777215;
  private final int PIXEL_HAVE_DIFFERENT_VALUE = 13294074;

  public BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
    int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
    int width2 = img2.getWidth();
    int height1 = img1.getHeight();
    int height2 = img2.getHeight();
    if ((width1 != width2) || (height1 != height2)) {
      Notifications.Bus.notify(new Notification("imgdif", "mismatch", "size of images do not match", NotificationType.ERROR));
    }

    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

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
        result = result == 0 ? PIXEL_HAVE_SAME_VALUE : PIXEL_HAVE_DIFFERENT_VALUE;
        outImg.setRGB(j, i, result);
      }
    }
    return outImg;
  }

  
}
