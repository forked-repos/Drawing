package edu.cascadia.brianb.mydrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Edited by Brian Bansenauer on 10/18/15.
 */
public class SimplyDrawnView extends View {

    private Paint mPaint;
    private Path mPath;
    private float mWidth, mHeight;

    public SimplyDrawnView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        mPaint = new Paint();
        mPath = new Path();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE); //draw background

        //Draw line
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(mWidth, 0, 200, mHeight, mPaint);

        //Draw red line
        mPaint.setColor(Color.RED); mPaint.setStrokeWidth(10);

        //api: drawLine(float startX, float startY, float stopX, float stopY, Paint paint)
        canvas.drawLine(mWidth/2, mHeight/2, mWidth, mHeight, mPaint);
        //Draw green lines
        //draw five green lines

        int alpha = 255;
        for(int i=0; i < 5; i++){
            mPaint.setColor(Color.GREEN); mPaint.setStrokeWidth(10);
            //canvas.drawLine(0, 0, mWidth, 0, mPaint);
            mPaint.setAlpha(alpha);
            alpha = 255 - 30;
            canvas.drawLine(20, 20+i*50, mWidth, 20+i*50, mPaint);
        }

        //Draw Text
        canvas.drawText("This is drawing text", 100f, 100f, mPaint);

        //Draw Text on a Path
        mPath.addArc(new RectF(100f, 150f, 400f, 300f), 0f, 230f);
        canvas.drawTextOnPath("some text on path", mPath, 0, 0, mPaint);

        //draw a circle
        //mWidth/2, mHeight/2, mWidth, mHeight
        float radius = mWidth/3;
        canvas.drawCircle(mWidth/2, mHeight/2, radius, mPaint);

        //Draw filled, opaque, and open ovals
        ShapeDrawable s = new ShapeDrawable(new OvalShape());
        s.setBounds(0, 0, 200, 300);
        s.draw(canvas);

        //Draw bee bitmap
        //cannot use getDrawable my device does not support api 21
//        Drawable b = getResources().getDrawable(R.drawable.bee,null);
//
//        int x=50, y=200;
//        b.setBounds(x, y, x+b.getBounds().right, y+b.getBounds().bottom);
//        b.draw(canvas);
        int x=50, y=200;
//        b.setBounds(x, y, x+b.getBounds().right, y+b.getBounds().bottom);
        Bitmap beeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bee);
        canvas.drawBitmap(beeBitmap, x, y, mPaint);
    }
}
