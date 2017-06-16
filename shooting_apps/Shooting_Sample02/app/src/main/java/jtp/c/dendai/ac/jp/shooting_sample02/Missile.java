package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.floor;
import static java.lang.Math.sin;

/**
 * Created by taka on 2017/06/01.
 */

public class Missile extends BaseObject {
    private static final float MOVE_WEIGHT = 1.0f;
    //private static final float SIZE = 10f;
    private final Paint paint = new Paint();

    public final Bitmap bitmap;
    public final Rect rect;

    int count;

    //private Random rand = new Random();
    //private int missile_move_type = rand.nextInt(3);

    //public final float alignX;

    //double missile_move_x, getMissile_move_y;

    Missile(Bitmap bitmap, int fromX, float alignX) {
        this.bitmap = bitmap;

        yPosition = 0;
        xPosition = fromX;
        //this.alignX = alignX;

        int left = (int) xPosition;
        int top = (int) yPosition;
        int right = left + bitmap.getWidth();
        int bottom = top + bitmap.getHeight();
        rect = new Rect(left, top, right, bottom);

        //paint.setColor(Color.RED);
    }

    @Override
    public void move() {
        //yPosition += 0.1 * MOVE_WEIGHT;
        //xPosition += alignX * MOVE_WEIGHT;
        //count++;
        //double theta = Math.toRadians(count) * 5;
        /*if (missile_move_type == 0) {
            yPosition += 0.5 * MOVE_WEIGHT;
        } else if (missile_move_type == 1) {
            yPosition += 0.5 * MOVE_WEIGHT;
            xPosition -= 0.5 * alignX * MOVE_WEIGHT;
        } else {*/
        //xPosition += Math.cos(theta) * 5;// - theta * Math.sin(theta);
        yPosition += 0.5 * MOVE_WEIGHT; // += Math.cos(theta);// + theta * Math.cos(theta) + rand.nextDouble();
        //}
    }

    //引数のGameObjectのタイプがMissie以外で、
    // 距離が自身のSIZE未満であれば当たったと判定する
    @Override
    public boolean isHit(BaseObject object) {
        if (object.getType() != Type.Missie) {
            return false;
        }
        int x = Math.round(object.xPosition);
        int y = Math.round(object.yPosition);
        return rect.contains(x, y);

        //return (calcDistance(this, object) < SIZE);
    }

    //タイプとしてMissileを返す
    @Override
    public Type getType() {
        return Type.Missie;
    }

    @Override
    //状態がNORMALでなければ描画しない
    public void draw(Canvas canvas) {
        if (state != STATE_NORMAL) {
            return;
        }
        canvas.drawBitmap(bitmap, xPosition, yPosition, paint);
        //canvas.drawBitmap(bitmap, xPosition, yPosition, paint);
        //canvas.drawCircle(xPosition, yPosition, SIZE, paint);
    }
}
