package de.ehmkah.projects.imgdiff

import java.awt.image.BufferedImage

class ImgDiffWhiteBackgroundBinaryDiffTool : ImgDiffBaseBinaryDiffTool() {

    private val diffedImageCreator = DiffedImageCreator()

    companion object {
        val instance = ImgDiffWhiteBackgroundBinaryDiffTool()
    }

    override fun createDiffImage(bufferedImage0: BufferedImage, bufferedImage1: BufferedImage): BufferedImage {
        return diffedImageCreator.getDifferenceImageWhiteAsBackground(bufferedImage0, bufferedImage1)
    }

    override fun getName(): String {
        return "ImageDiff - White Background"
    }
}