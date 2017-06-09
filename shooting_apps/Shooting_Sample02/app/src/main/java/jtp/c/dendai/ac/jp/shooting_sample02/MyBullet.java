package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.TimerTask;

/**
 * Created by taka on 2017/06/02.
 */

public class MyBullet extends BaseObject {
    private static final float MOVE_WEIGHT = 3.0f;
    private final Paint paint = new Paint();

    public Bitmap bulletBitmap;
    public Rect rect, bulletRect;
    private static final float SIZE = 15f;

    public final float alignX;

    /*Bullet(Rect rect, float alignXValue) {
        xPosition = rect.centerX();
        yPosition = rect.centerY() - rect.height() / 2 - 20; //弾の出現ポイント
        alignX = alignXValue;
    }*/

    MyBullet(Bitmap bulletBitmap, Rect rect, float alignXValue) {
        this.bulletBitmap = bulletBitmap;

        xPosition = rect.centerX() - bulletBitmap.getWidth() / 2;                     //弾の出現ポイント(x)
        yPosition = rect.centerY() - rect.height() / 2 - bulletBitmap.getHeight();  //弾の出現ポイント(y)
        alignX = alignXValue;
    }

    @Override
    public void move() {
        yPosition -= 1 * MOVE_WEIGHT;
        //xPosition += alignX * MOVE_WEIGHT;
    }

    //引数のBaseObjectのタイプがMissileで、
    // 距離が自身のSIZE未満であれば当たったと判定する
    @Override
    public boolean isHit(BaseObject object) {
        if (object.getType() != Type.Missie) {
            return false;
        }

        return (calcDistance(this, object) < SIZE);
    }

    //タイプとしてMyBulletを返す
    @Override
    public Type getType() {
        return Type.MyBullet;
    }

    @Override
    public void draw(Canvas canvas) {

        //状態がNORMALでなければ描画しない
        if (state != STATE_NORMAL) {
            return;
        }

        //canvas.drawCircle(xPosition, yPosition, SIZE, paint);

        //drawBitmap(bitmap,画像の左上のX座標,画像の左上のY座標)
        canvas.drawBitmap(bulletBitmap, xPosition, yPosition, paint);
    }
}
