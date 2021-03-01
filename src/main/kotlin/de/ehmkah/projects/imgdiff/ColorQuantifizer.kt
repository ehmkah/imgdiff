package de.ehmkah.projects.imgdiff

import com.squareup.gifencoder.Color
import com.squareup.gifencoder.ColorQuantizer
import com.squareup.gifencoder.Multiset
import com.squareup.gifencoder.UniformQuantizer

/**
 * Transforms all colors which are used in the original image to gif-compatible subset.
 * Its enforced that colors which are used by imagediff (pixel have same value, pixel out of bound, and pixel have different value) are contained.
 */
class ColorQuantifizer : ColorQuantizer {

    override fun quantize(originalColors: Multiset<Color>?, maxColorCount: Int): MutableSet<Color> {
        val result = HashSet<Color>()
        result.addAll(UniformQuantizer.INSTANCE.quantize(originalColors, maxColorCount - 3))
        addImageDiffColorsToGifColorMap(result)

        return result
    }

    private fun addImageDiffColorsToGifColorMap(quantize: HashSet<Color>) {
        quantize.add(Color.fromRgbInt(PIXEL_HAVE_SAME_VALUE))
        quantize.add(Color.fromRgbInt(PIXEL_HAVE_DIFFERENT_VALUE))
        quantize.add(Color.fromRgbInt(PIXEL_OUT_OF_BOUNDS_VALUE))
    }
}