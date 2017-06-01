package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Canvas;

/**
 * Created by taka on 2017/06/01.
 */

public abstract class BaseObject {
    float xPosition;
    float yPosition;

    public abstract void draw(Canvas canvas);

    public boolean isAvailable(int width, int height) {
        if (yPosition < 0 || xPosition < 0 || yPosition > height || xPosition > width) {
            return false;
        }
        return true;
    }

    public abstract void move();
}
