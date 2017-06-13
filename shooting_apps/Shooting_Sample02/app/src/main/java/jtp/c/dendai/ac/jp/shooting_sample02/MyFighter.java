package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by taka on 2017/06/01.
 */

public class MyFighter extends BaseObject implements View.OnTouchListener {
    private final Paint paint = new Paint();

    private ImageView imageView;

    public final Bitmap bitmap;
    public final Rect rect;
    private int preDx = 0, preDy = 0, newDx = 0, newDy = 0;

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
    public boolean onTouch(View v, MotionEvent event) {
        // x,y 位置取得
        newDx = (int) event.getRawX();
        newDy = (int) event.getRawY();

        switch (event.getAction()) {
            // タッチダウンでdragされた
            case MotionEvent.ACTION_MOVE:
                // ACTION_MOVEでの位置
                int dx = imageView.getLeft() + (newDx - preDx);
                int dy = imageView.getTop() + (newDy - preDy);

                // 画像の位置を設定する
                imageView.layout(dx, dy, dx + imageView.getWidth(), dy + imageView.getHeight());

                Log.d("onTouch", "ACTION_MOVE: dx=" + dx + ", dy=" + dy);
                break;
        }

        // タッチした位置を古い位置とする
        preDx = newDx;
        preDy = newDy;

        return true;
    }

    @Override
    public void move() {

    }
}
