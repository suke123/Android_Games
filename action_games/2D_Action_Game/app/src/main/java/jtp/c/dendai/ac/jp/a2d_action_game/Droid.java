package jtp.c.dendai.ac.jp.a2d_action_game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Droid {

    private static final float GRAVITY = 0.8f;
    private static final float WEIGHT = GRAVITY * 60;

    private static final int COLLISION_MARGIN_LEFT = 20;
    private static final int COLLISION_MARGIN_RIGHT = 20;

    private final Paint paint = new Paint();

    private Bitmap bitmap;

    final Rect rect;
    private final Rect drawRect = new Rect();

    private static final int BLOCK_SIZE = 230;

    private static final Rect BITMAP_SRC_JUMPING = new Rect(
            BLOCK_SIZE, 0,BLOCK_SIZE * 2, BLOCK_SIZE);
    private static final Rect BITMAP_SRC_RUNNING = new Rect(
            0, 0, BLOCK_SIZE, BLOCK_SIZE);

    public interface Callback {

        public int getDistanceFromGround(Droid droid);
    }

    private final Callback callback;

    private float acceleration = 0;

    public Droid(Bitmap bitmap, int left, int top, Callback callback) {
        int rectLeft = left + COLLISION_MARGIN_LEFT;
        int rectRight = left + BLOCK_SIZE - COLLISION_MARGIN_RIGHT;
        this.rect = new Rect(rectLeft, top, rectRight, top + BLOCK_SIZE);

        this.bitmap = bitmap;
        this.callback = callback;
    }

    public void draw(Canvas canvas) {
        //通常時はBITMAP_SRC_RUNNINGが示すブロックを表示する
        Rect src = BITMAP_SRC_RUNNING;

        if (acceleration != 0) {
            //ジャンプ中または落下中(velocityが0)の場合はBITMAP_SRC_JUMPINGが示すブロックを表示する
            src = BITMAP_SRC_JUMPING;
        }

        drawRect.set(rect);
        drawRect.left -= COLLISION_MARGIN_LEFT;

        canvas.drawBitmap(bitmap, src, drawRect, paint);
    }

    public void jump(float time) {
        acceleration = time * WEIGHT;
    }

    public void move() {

        acceleration -= GRAVITY;

        int distanceFromGround = callback.getDistanceFromGround(this);
        if (acceleration < 0 && acceleration < -distanceFromGround) {
            acceleration = -distanceFromGround;
        }

        rect.offset(0, -Math.round(acceleration));
    }

    public void shutdown() {
        acceleration = 0;
    }

}
