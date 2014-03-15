package br.com.rads.awesomenauts.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by rafael_2 on 04/03/14.
 */
public class Utils {
    public static int getPadding(float density, int dp) {
        return (int) (density * dp + 0.5f);
    }

    public static Drawable resizeDrawable(Context context, int imageResource, float percent) {
        BitmapDrawable bd = (BitmapDrawable) context.getResources().getDrawable(imageResource);
        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(),
                (int) (bd.getIntrinsicHeight() * percent),
                (int) (bd.getIntrinsicWidth() * percent),
                false);
        return new BitmapDrawable(context.getResources(),b);
    }

}
