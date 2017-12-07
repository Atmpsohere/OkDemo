package com.myworld.android.myanim.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/12/6.
 */

@SuppressLint("AppCompatCustomView")
public class CircleView extends ImageView {
    private Bitmap mBitmap;
    private Matrix matrix = new Matrix();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private BitmapShader shader;

    public CircleView(Context context) {
        super(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Bitmap bitmap = getBitmap(drawable);
        if (bitmap != null) {
            int width = getWidth();
            int height = getHeight();
            int minSize = Math.min(width, height);
            float rawWidth = minSize;
            float rawHeight = minSize;
            if (shader == null || !bitmap.equals(mBitmap)) {
                mBitmap = bitmap;
                shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (shader != null) {
                matrix.setScale(rawWidth / mBitmap.getWidth(), rawHeight / mBitmap.getHeight());
                shader.setLocalMatrix(matrix);
            }
            paint.setShader(shader);
            float radius = minSize / 2;
            canvas.drawCircle(radius, radius, radius, paint);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            //将ColorDrawable转换成Bitmap
            //获取绘制图片的宽度和高度
            Rect bounds = drawable.getBounds();
            int mheight = bounds.height();
            int mwidth = bounds.width();
            Bitmap bitmap = Bitmap.createBitmap(mwidth, mheight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas();
            int color = ((ColorDrawable) drawable).getColor();
            canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        }
        return null;
    }
}
