package com.kevicsalazar.colorpalette

import android.graphics.Color

/**
 * Created by Kevin.
 */
class ColorUtils {

    companion object {

        fun palette(color: String): ColorPalette {
            return palette(color.substring(1).toLong(16))
        }

        fun palette(color: Int): ColorPalette {
            return palette(color.toLong() and 0xFFFFFF)
        }

        fun palette(f: Long): ColorPalette {
            return ColorPalette(
                    shadeColor(f, 0.9),
                    shadeColor(f, 0.7),
                    shadeColor(f, 0.5),
                    shadeColor(f, 0.333),
                    shadeColor(f, 0.166),
                    shadeColor(f, 0.0),
                    shadeColor(f, -0.125),
                    shadeColor(f, -0.25),
                    shadeColor(f, -0.375),
                    shadeColor(f, -0.5),
                    shadeColor(f, 0.7),
                    shadeColor(f, 0.5),
                    shadeColor(f, 0.166),
                    shadeColor(f, -0.25)
            )
        }
        fun shadeColor(f: Long, percent: Double): Int {
            val t = (if (percent < 0) 0 else 255).toDouble()
            val p = if (percent < 0) percent * -1 else percent
            val R = f shr 16
            val G = f shr 8 and 0x00FF
            val B = f and 0x0000FF
            val red = (Math.round((t - R) * p) + R).toInt()
            val green = (Math.round((t - G) * p) + G).toInt()
            val blue = (Math.round((t - B) * p) + B).toInt()
            return Color.rgb(red, green, blue)
        }

    }

}