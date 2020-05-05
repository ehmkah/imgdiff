package de.ehmkah.projects.imgdiff

import org.junit.Assert.assertTrue
import org.junit.Test
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class DiffedImageCreatorTest {

    private val sut = DiffedImageCreator()

    @Test
    fun testDiff() {
        val original = readImage("/original.png")
        val changed = readImage("/modified.png")

        val actual = sut.getDifferenceImage(original, changed)
        val expected = readImage("/expected.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testDiffDiffer() {
        val original = readImage("/original.png")
        val changed = readImage("/modified.png")

        val actual = sut.getDifferenceImage(original, changed)
        val expected = readImage("/expected.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testDiffDiffer2() {
        val original = readImage("/smallBlack.png")
        val changed = readImage("/smallBlackWithBorder.png")

        val actual = sut.getDifferenceImage(original, changed)
        val expected = readImage("/expectedSmallBlack.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testDifferentSize() {
        val original = readImage("/original.png")
        val changed = readImage("/modifiedDifferentSize.png")

        val actual = sut.getDifferenceImage(original, changed)
        val expected = readImage("/expectedDifferentSize.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testImagesHaveNoDiff() {
        val original = readImage("/identical.png")
        val changed = readImage("/identical.png")
        val expected = readImage("/identical.png")

        val actual = sut.getDifferenceImage(original, changed)

        assertTrue(compareImages(expected, actual))

    }

    private fun readImage(resourcePath: String): BufferedImage {
        return ImageIO.read(DiffedImageCreatorTest::class.java.getResourceAsStream(resourcePath))
    }

    /**
     * stolen from https://stackoverflow.com/questions/11006394/is-there-a-simple-way-to-compare-bufferedimage-instances
     */
    @Throws(IOException::class)
    private fun compareImages(imgA: BufferedImage, imgB: BufferedImage): Boolean {

        ImageIO.write(imgA, "png", File("imgA.png"))
        ImageIO.write(imgB, "png", File("imgB.png"))

        // The images must be the same size.
        if (imgA.width != imgB.width || imgA.height != imgB.height) {
            return false
        }

        val width = imgA.width
        val height = imgA.height

        // Loop over every pixel.
        for (y in 0 until height) {
            for (x in 0 until width) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {

                    return false
                }
            }
        }

        return true
    }
}
