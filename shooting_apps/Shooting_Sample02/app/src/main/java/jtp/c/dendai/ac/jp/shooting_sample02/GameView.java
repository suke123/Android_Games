package jtp.c.dendai.ac.jp.shooting_sample02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by taka on 2017/06/01.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final long DRAW_INTERVAL = 1000 / 100;   //描画間隔

    private static final int MISSILE_LAUNCH_WEIGHT = 50;
    private MyFighter myFighter;        //自機クラス
    private MyBullet bullet;            //自弾クラスkurasu
    //private int bullet_type = 1;
    private final List<BaseObject> missileList = new ArrayList<>();
    private final Random rand = new Random(System.currentTimeMillis());
    private final List<BaseObject> bulletList = new ArrayList<>();

    private DrawThread drawThread;

    private class DrawThread extends Thread {
        private final AtomicBoolean isFinished = new AtomicBoolean();

        public void finish() {
            isFinished.set(true);
        }

        @Override
        public void run() {
            SurfaceHolder holder = getHolder();
            while (!isFinished.get()) {
                if (holder.isCreating()) {
                    continue;
                }

                Canvas canvas = holder.lockCanvas();
                if (canvas == null) {
                    continue;
                }

                drawGame(canvas);
                holder.unlockCanvasAndPost(canvas);

                synchronized (this) {
                    try {
                        wait(DRAW_INTERVAL);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
    }

    public void startDrawThread() {
        stopDrawThread();

        drawThread = new DrawThread();
        drawThread.start();
    }

    public boolean stopDrawThread() {
        if (drawThread == null) {
            return false;
        }

        drawThread.finish();
        drawThread = null;
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startDrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopDrawThread();
    }

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    protected void drawGame(Canvas canvas) {
        canvas.drawColor(Color.WHITE);      //背景：白

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (myFighter == null) {
            Bitmap myFighterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jibun);
            myFighter = new MyFighter(myFighterBitmap, width, height);

        }

        /*if(bullet==null){
            Bitmap myFighterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mybullet);
            Bullet bullet = new Bullet(myFighterBitmap, width, height);
        }*/

        if (rand.nextInt(MISSILE_LAUNCH_WEIGHT) == 0) {
            Missile missile = launchMissile(width, height);
            missileList.add(missile);
        }
        drawObjectList(canvas, missileList, width, height);

        drawObjectList(canvas, bulletList, width, height);

        for (int i = 0; i < missileList.size(); i++) {
            //ミサイルと自機が当たったかどうかを判定
            BaseObject missile = missileList.get(i);
            if (myFighter.isHit(missile)) {
                missile.hit();
                myFighter.hit();
                break;
            }

            //弾とミサイルが当たったかどうかを判定
            for (int j = 0; j < bulletList.size(); j++) {
                BaseObject bullet = bulletList.get(j);

                if (bullet.isHit(missile)) {
                    missile.hit();
                    bullet.hit();
                }
            }
        }

        myFighter.draw(canvas);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fire(event.getX(), event.getY());
                break;
        }

        return super.onTouchEvent(event);
    }

    private void fire(float x, float y) {
        float alignX = (x - myFighter.rect.centerX()) / Math.abs(y - myFighter.rect.centerY());
        Bitmap bulletBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mybullet);

        bullet = new MyBullet(bulletBitmap, myFighter.rect, alignX);
        bulletList.add(0, bullet);
    }

    private Missile launchMissile(int width, int height) {


        int fromX = rand.nextInt(width);
        int toX = rand.nextInt(height);

        float alignX = (toX - fromX) / (float) height;
        return new Missile(fromX, alignX);
    }
}
