package jtp.c.dendai.ac.jp.switch_test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by taka on 2017/10/26.
 */

public class Boy {

    public interface Callback{
        public int getDistanceFromBlueBlock(Boy boy);
        public int getDistanceFromGoal(Boy boy);
    }

    private final Callback callback;

    private final Paint paint = new Paint();
    private Bitmap bitmap;
    private boolean onBlock = false;//ブロックに接しているか
    private boolean onGoal = false;//ゴールの上に乗っているか

    final Rect rect;

    public Boy(Bitmap bitmap, int left, int top, Callback callback){
        this.rect = new Rect(left, top, left + bitmap.getWidth(), top + bitmap.getHeight());
        this.bitmap = bitmap;
        this.callback = callback;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, rect.left, rect.top, paint);
    }

    public void moveRight(){
        int distanceFromBlueBlock = callback.getDistanceFromBlueBlock(this);

        if(distanceFromBlueBlock <= 0){
            return;
        }
        rect.offset(5, 0);
    }

    public void moveLeft(){
        int distanceFromBlueBlock = callback.getDistanceFromBlueBlock(this);
        if(distanceFromBlueBlock <= 0){
            return;
        }
        rect.offset(-5, 0);
    }

    public void jump(){
        int distanceFromBlueBlock = callback.getDistanceFromBlueBlock(this);
        if(distanceFromBlueBlock <= 0){
            return;
        }
        rect.offset(0, -5);
    }

}
