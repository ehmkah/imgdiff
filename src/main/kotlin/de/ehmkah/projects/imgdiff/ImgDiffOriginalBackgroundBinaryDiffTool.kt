package de.ehmkah.projects.imgdiff

import java.awt.image.BufferedImage

class ImgDiffOriginalBackgroundBinaryDiffTool : ImgDiffBaseBinaryDiffTool() {

    private val diffedImageCreator = DiffedImageCreator()

    companion object {
        val instance = ImgDiffOriginalBackgroundBinaryDiffTool()
    }

    override fun createDiffImage(bufferedImage0: BufferedImage, bufferedImage1: BufferedImage): BufferedImage {
        return diffedImageCreator.getDifferenceImageOriginalAsBackground(bufferedImage0, bufferedImage1)
    }

    override fun getName(): String {
        return "ImageDiff - Original background"
    }
}