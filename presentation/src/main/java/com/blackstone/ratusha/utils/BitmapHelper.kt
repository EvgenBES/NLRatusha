package com.blackstone.ratusha.utils

import android.content.Context
import android.graphics.*

/**
 * @author Evgeny Butov
 * @created 05.09.2019
 */
object BitmapHelper {

   private const val radius = 15f
   private const val margin = 0f

    fun setCornersRadius(context: Context, drawable: Int): Bitmap {
        val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, drawable)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(newBitmap)
        canvas.drawRoundRect(RectF(margin,margin,bitmap.width.toFloat(), bitmap.height.toFloat()), radius, radius, paint)

        if (bitmap != newBitmap)  bitmap.recycle()

        return newBitmap
    }

}