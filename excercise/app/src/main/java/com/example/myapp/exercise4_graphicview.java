package com.example.myapp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class exercise4_graphicview extends View{
    int i = 0;
    long last_tick = 0;
    long period = 200;
    Context ctext;
    MediaPlayer mPlayer;

    Bitmap[] frames = new Bitmap[4]; //4 frames

    public exercise4_graphicview(Context context) {
        super(context);
        ctext = context;
        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.win_1);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.win_2);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.win_3);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.win_4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // Drawing commands go here
        if(i < 4){
            long time = (System.currentTimeMillis() - last_tick);
            if (time >= period) { //the delay time has passed. set next frame
                last_tick = System.currentTimeMillis();
                canvas.drawBitmap(frames[i], 40, 40, new Paint());
                i++;
                // Again call onDraw method
                postInvalidate();
            } else { //still within delay. redraw current frame
                canvas.drawBitmap(frames[i], 40, 40, new Paint());
                // Again call onDraw method
                postInvalidate();
            }
        } else {
            i = 0;
            postInvalidate();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        i++;
        return true;
    }

}



