package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by taka on 2017/06/01.
 */

public class Missile extends BaseObject {
    private static final float MOVE_WEIGHT = 3.0f;
    private static final float SIZE = 10f;
    private final Paint paint = new Paint();

    public final float alignX;

    Missile(int fromX, float alignX) {
        yPosition = 0;
        xPosition = fromX;
        this.alignX = alignX;

        paint.setColor(Color.BLUE);
    }

    @Override
    public void move() {
        yPosition += 1 * MOVE_WEIGHT;
        xPosition += alignX * MOVE_WEIGHT;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(xPosition, yPosition, SIZE, paint);
    }
}
