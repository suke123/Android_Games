package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by taka on 2017/06/01.
 */

public class MyFighter extends BaseObject {
    private final Paint paint = new Paint();

    public final Bitmap bitmap;
    public final Rect rect;

    public MyFighter(Bitmap bitmap, int width, int height) {
        this.bitmap = bitmap;

        int left = (width - bitmap.getWidth()) / 2;
        int top = height - bitmap.getHeight();
        int right = left + bitmap.getWidth();
        int bottom = top + bitmap.getHeight();
        rect = new Rect(left, top, right, bottom);
    }

    //引数のオブジェクトの位置が自身のrect内に含まれるかで当たったかを判定する
    @Override
    public boolean isHit(BaseObject object) {
        if (object.getType() != Type.Missie) {
            return false;
        }

        int x = Math.round(object.xPosition);
        int y = Math.round(object.yPosition);
        return rect.contains(x, y);
    }

    //タイプとしてMyFighterを返す
    @Override
    public Type getType() {
        return Type.MyFighter;
    }

    @Override
    public void draw(Canvas canvas) {
        //状態がNORMALでなければ描画しない
        if (state != STATE_NORMAL) {
            return;
        }
        canvas.drawBitmap(bitmap, rect.left, rect.top, paint);
    }

    @Override
    public void move() {

    }
}
