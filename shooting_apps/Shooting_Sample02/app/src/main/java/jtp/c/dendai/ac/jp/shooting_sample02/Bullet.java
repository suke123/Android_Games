package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by taka on 2017/06/02.
 */

public class Bullet extends BaseObject {
    private static final float MOVE_WEIGHT = 3.0f;
    private final Paint paint = new Paint();

    private static final float SIZE = 15f;

    public final float alignX;

    Bullet(Rect rect, float alignXValue) {
        xPosition = rect.centerX();
        yPosition = rect.centerY();
        alignX = alignXValue;
    }

    @Override
    public void move() {
        yPosition -= 1 * MOVE_WEIGHT;
        xPosition += alignX * MOVE_WEIGHT;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(xPosition, yPosition, SIZE, paint);
    }
}
