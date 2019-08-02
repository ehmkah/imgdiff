package de.ehmkah.projects.imgdiff

import java.awt.image.BufferedImage

/**
 * stolen from https://stackoverflow.com/questions/25022578/highlight-differences-between-images and modified.
 *
 * @author Michael Krausse (ehmkah)
 */
class DiffedImageCreator {

    private val PIXEL_HAVE_SAME_VALUE = 16777215
    private val PIXEL_HAVE_DIFFERENT_VALUE = 13294074
    private val PIXELD_OUT_OF_BOUNDS_VALUE = 16711680

    fun getDifferenceImage(img1: BufferedImage, img2: BufferedImage): BufferedImage {
        val width1 = img1.width
        val width2 = img2.width
        val height1 = img1.height
        val height2 = img2.height
        val targetWidth = Math.max(width1, width2)
        val targetHeight = Math.max(height1, height2)

        val result = BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB)

        for (currentHeight in 0 until targetHeight) {
            for (currentWidth in 0 until targetWidth) {
                var diff: Int
                var diffPixel: Int
                if (pixelOutOfBounds(currentHeight, currentWidth, img1, img2)) {
                    diffPixel = PIXELD_OUT_OF_BOUNDS_VALUE
                } else {
                    val rgb1 = img1.getRGB(currentWidth, currentHeight)
                    val rgb2 = img2.getRGB(currentWidth, currentHeight)
                    val r1 = rgb1 shr 16 and 0xff
                    val g1 = rgb1 shr 8 and 0xff
                    val b1 = rgb1 and 0xff
                    val r2 = rgb2 shr 16 and 0xff
                    val g2 = rgb2 shr 8 and 0xff
                    val b2 = rgb2 and 0xff
                    diff = Math.abs(r1 - r2)
                    diff += Math.abs(g1 - g2)
                    diff += Math.abs(b1 - b2)
                    diff /= 3

                    diffPixel = diff shl 16 or (diff shl 8) or diff
                    diffPixel = if (diffPixel == 0) PIXEL_HAVE_SAME_VALUE else PIXEL_HAVE_DIFFERENT_VALUE
                }
                result.setRGB(currentWidth, currentHeight, diffPixel)
            }
        }

        return result
    }

    private fun pixelOutOfBounds(currentHeight: Int, currentWidth: Int, img1: BufferedImage, img2: BufferedImage): Boolean {
        return currentHeight > img1.height - 1 || currentHeight > img2.height - 1 ||
                currentWidth > img1.width - 1 || currentWidth > img2.width - 1
    }

}
