package com.example.myapp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class exercise3_graphicview extends View{

    Bitmap[] frames = new Bitmap[4]; //4 frames
    int i = 0;
    public exercise3_graphicview(Context context) {
        super(context);

        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.win_1);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.win_2);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.win_3);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.win_4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Drawing commands go here
        if(i < 4){
            canvas.drawBitmap(frames[i], 40, 40, new Paint());
        }
        else{
            i = 0;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        i++;
        return true;
    }

}



