package de.ehmkah.projects.imgdiff

import org.junit.Assert.assertTrue
import org.junit.Test
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO

class DiffedImageCreatorTest {

    private val sut = DiffedImageCreator()

    @Test
    fun testDiff() {
        val original = readImage("/original.png")
        val changed = readImage("/modified.png")

        val actual = sut.getDifferenceImageWhiteAsBackground(original, changed)
        val expected = readImage("/expected.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testDiffDiffer() {
        val original = readImage("/original.png")
        val changed = readImage("/modified.png")

        val actual = sut.getDifferenceImageWhiteAsBackground(original, changed)
        val expected = readImage("/expected.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testDiffDiffer2() {
        val original = readImage("/smallBlack.png")
        val changed = readImage("/smallBlackWithBorder.png")

        val actual = sut.getDifferenceImageWhiteAsBackground(original, changed)
        val expected = readImage("/expectedSmallBlack.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testDiffOriginalAsBackround() {
        val original = readImage("/smallBlack.png")
        val changed = readImage("/smallBlackWithBorder.png")

        val actual = sut.getDifferenceImageOriginalAsBackground(original, changed)
        val expected = readImage("/expectedOriginalAsBackgroundDiff.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testDifferentSize() {
        val original = readImage("/original.png")
        val changed = readImage("/modifiedDifferentSize.png")

        val actual = sut.getDifferenceImageWhiteAsBackground(original, changed)
        val expected = readImage("/expectedDifferentSize.png")

        assertTrue(compareImages(expected, actual))
    }

    @Test
    fun testImagesHaveNoDiff() {
        val original = readImage("/two.png")
        val changed = readImage("/one.png")
        val expected = readImage("/identical.png")

        val actual = sut.getDifferenceImageWhiteAsBackground(original, changed)

        assertTrue(compareImages(expected, actual))

    }

    @Test
    fun testGifCreation() {
        val outputStream = ByteArrayOutputStream()
        val original = readImage("/one.png")
        val changed = readImage("/two.png")
        sut.createGifImage(original, changed, outputStream)
        outputStream.close()
        Files.write(Paths.get("actual.gif"), outputStream.toByteArray())
    }

    private fun readImage(resourcePath: String): BufferedImage {
        return ImageIO.read(DiffedImageCreatorTest::class.java.getResourceAsStream(resourcePath))
    }

    /**
     * stolen from https://stackoverflow.com/questions/11006394/is-there-a-simple-way-to-compare-bufferedimage-instances
     */
    @Throws(IOException::class)
    private fun compareImages(expectedImage: BufferedImage, actualImage: BufferedImage): Boolean {

        ImageIO.write(expectedImage, "png", File("expectedImage.png"))
        ImageIO.write(actualImage, "png", File("actualImage.png"))

        // The images must be the same size.
        if (expectedImage.width != actualImage.width || expectedImage.height != actualImage.height) {
            return false
        }

        val width = expectedImage.width
        val height = expectedImage.height

        // Loop over every pixel.
        for (y in 0 until height) {
            for (x in 0 until width) {
                // Compare the pixels for equality.
                if (expectedImage.getRGB(x, y) != actualImage.getRGB(x, y)) {
                    return false
                }
            }
        }

        return true
    }
}
