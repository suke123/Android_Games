package jtp.c.dendai.ac.jp.shooting_sample02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by taka on 2017/06/01.
 */

public class GameView extends View {
    private Droid droid;

    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (droid == null) {
            Bitmap droidBitmap = BitmapFactory.decodeResource(getResources().R.drawable.droid);
            droid = new Droid(droidBitmap, width, height);
        }

        droid.draw(canvas);
    }
}
