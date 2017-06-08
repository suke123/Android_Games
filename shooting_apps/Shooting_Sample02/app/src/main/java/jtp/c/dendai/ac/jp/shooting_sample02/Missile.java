package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by taka on 2017/06/01.
 */

public class Missile extends BaseObject {
    private static final float MOVE_WEIGHT = 3.0f;
    private static final float SIZE = 10f;
    private final Paint paint = new Paint();

    int count;

    private Random rand = new Random();
    private int missile_move_type = rand.nextInt(3);

    public final float alignX;

    double missile_move_x, getMissile_move_y;

    Missile(int fromX, float alignX) {
        yPosition = 0;
        xPosition = fromX;
        this.alignX = alignX;

        paint.setColor(Color.RED);
    }

    @Override
    public void move() {
        //yPosition += 0.1 * MOVE_WEIGHT;
        //xPosition += alignX * MOVE_WEIGHT;
        count++;
        double theta = Math.toRadians(count) * 5;
        /*if (missile_move_type == 0) {
            yPosition += 0.5 * MOVE_WEIGHT;
        } else if (missile_move_type == 1) {
            yPosition += 0.5 * MOVE_WEIGHT;
            xPosition -= 0.5 * alignX * MOVE_WEIGHT;
        } else {*/
            xPosition += Math.cos(theta) * 5;// - theta * Math.sin(theta);
            yPosition += 1;// += Math.cos(theta);// + theta * Math.cos(theta) + rand.nextDouble();
        //}
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(xPosition, yPosition, SIZE, paint);
    }
}
