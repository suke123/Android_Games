package jtp.c.dendai.ac.jp.shooting_sample02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by taka on 2017/06/01.
 */

public class GameView extends View {
    private static final int MISSILE_LAUNCH_WEIGHT = 50;
    private Droid droid;
    private final List<BaseObject> missileList = new ArrayList<>();
    private final Random rand = new Random(System.currentTimeMillis());

    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (droid == null) {
            Bitmap droidBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jibun);
            droid = new Droid(droidBitmap, width, height);
        }

        if (rand.nextInt(MISSILE_LAUNCH_WEIGHT) == 0) {
            Missile missile = launchMissile(width, height);
            missileList.add(missile);
        }
        drawObjectList(canvas, missileList, width, height);

        droid.draw(canvas);
        invalidate();
    }

    private static void drawObjectList(
            Canvas canvas, List<BaseObject> objectList, int width, int height) {
        for (int i = 0; i < objectList.size(); i++) {
            BaseObject object = objectList.get(i);
            if (object.isAvailable(width, height)) {
                object.move();
                object.draw(canvas);
            } else {
                objectList.remove(object);
                i--;
            }
        }
    }

    private Missile launchMissile(int width, int height) {
        int fromX = rand.nextInt(width);
        int toX = rand.nextInt(height);

        float alignX = (toX - fromX) / (float) height;
        return new Missile(fromX, alignX);
    }
}
