package jtp.c.dendai.ac.jp.switch_test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by taka on 2017/10/26.
 */

public class Girl {

    public interface Callback{
        public int getDistanceFromRedBlock(Girl girl);
        public int getDistanceFromGoal(Girl girl);
    }

    private final Callback callback;

    private final Paint paint = new Paint();
    private Bitmap bitmap;

    final Rect rect;

    public Girl(Bitmap bitmap, int left, int top, Callback callback){
        this.rect = new Rect(left, top, left + bitmap.getWidth(), top + bitmap.getHeight());
        this.bitmap = bitmap;
        this.callback = callback;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, rect.left, rect.top, paint);
    }

    public void moveRight(){
        int distanceFromRedBlock = callback.getDistanceFromRedBlock(this);
        if(distanceFromRedBlock <= 0){
            return;
        }

        rect.offset(20, 0);
    }

    public void moveLeft(){
        int distanceFromRedBlock = callback.getDistanceFromRedBlock(this);
        if(distanceFromRedBlock <= 0){
            return;
        }

        rect.offset(-20, 0);
    }

    public void jump(){
        int distanceFromRedBlock = callback.getDistanceFromRedBlock(this);
        if(distanceFromRedBlock <= 0){
            return;
        }

        rect.offset(0, -100);
    }

    public void move(){
        int distanceFromRedBlock = callback.getDistanceFromRedBlock(this);
        if(distanceFromRedBlock <= 0){
            return;
        }

        rect.offset(0, 5);
    }
}
