package com.example.myapp;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
public class exercise1_graphicview extends View{

    public exercise1_graphicview(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Drawing commands go here
        Rect r = new Rect(40, 40, 400, 200);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(r, paint);
        invalidate();
    }

}



