package de.ehmkah.projects.imgdiff

import com.squareup.gifencoder.*

class ColorQuantifizer : ColorQuantizer {


    override fun quantize(originalColors: Multiset<Color>?, maxColorCount: Int): MutableSet<Color> {
        val quantize = HashSet<Color>()
        quantize.addAll(MedianCutQuantizer.INSTANCE.quantize(originalColors, maxColorCount - 3))
        quantize.add(Color.fromRgbInt(PIXEL_HAVE_SAME_VALUE))
        quantize.add(Color.fromRgbInt(PIXEL_HAVE_DIFFERENT_VALUE))
        quantize.add(Color.fromRgbInt(PIXEL_OUT_OF_BOUNDS_VALUE))

        return quantize;
    }
}