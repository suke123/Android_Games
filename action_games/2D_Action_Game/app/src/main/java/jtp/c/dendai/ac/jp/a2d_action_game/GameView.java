package jtp.c.dendai.ac.jp.a2d_action_game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import jtp.c.dendai.ac.jp.a2d_action_game.UnderView;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameView
        extends SurfaceView  implements Droid.Callback,
        SurfaceHolder.Callback
{

    private static final int START_GROUND_HEIGHT = 50;
    private static final int GROUND_MOVE_TO_LEFT = 10;

    private static final int MAX_TOUCH_TIME = 700; // msec
    private static final long FPS = 60;

    private static final int ADD_GROUND_COUNT = 5;

    private static final int GROUND_WIDTH = 400;
    private static final int GROUND_BLOCK_HEIGHT = 100;

    private static final float POWER_GAUGE_HEIGHT = 30;//ゲージの高さ
    private static final Paint PAINT_POWER_GAUGE = new Paint();//ゲージの色

    static {
        PAINT_POWER_GAUGE.setColor(Color.RED);//ゲージの色
    }

    private Droid droid;
    private final List<Ground> groundList = new ArrayList<Ground>();

    private long touchDownStartTime;

    private DrawThread drawThread;

    private Ground lastGround;

    private final Random rand = new Random();

    //public boolean isDead = false;
    private GameActivity gameActivity;

    public interface Callback {
        public void onGameOver();
    }




    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }



    private final Handler handler;

    private boolean isGameOver;

    public GameView(Context context, GameActivity gameAvtivity) {
        super(context);

        handler = new Handler();

        getHolder().addCallback(this);

        this.gameActivity = gameAvtivity;
    }

    public static Point getDisplaySize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }




    public void drawBG(Canvas canvas,Bitmap bm){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        boolean u = true;

        Paint paint = new Paint();

        canvas.drawColor(Color.WHITE);
            //  Canvas canvas1 = holder.lockCanvas();

        canvas.drawBitmap(bm, 0, 0, paint);
            u=false;

    }

    public void drawGame(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();


     //   canvas.drawColor(Color.WHITE);
        if (droid == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shika);
            droid = new Droid(bitmap, 0, 0, this);

            // 開始時に表示される地面
            lastGround = new Ground(0, height - START_GROUND_HEIGHT, width, height);
            groundList.add(lastGround);
        }

        if (lastGround.isShown(width, height)) {
            for (int i = 0; i < ADD_GROUND_COUNT; i++) {
                int left = lastGround.rect.right;


                if (i % 2 == 0) {
                    int groundHeight = rand.nextInt(height / GROUND_BLOCK_HEIGHT) * GROUND_BLOCK_HEIGHT / 2 + START_GROUND_HEIGHT;
                    lastGround = new Ground(left, height - groundHeight, left + GROUND_WIDTH, height);
                } else {
                    lastGround = new Blank(left, height - 1, left + GROUND_WIDTH, height);
                }
                groundList.add(lastGround);
            }
        }

        for (int i = 0; i < groundList.size(); i++) {
            Ground ground = groundList.get(i);

            if (ground.isAvailable()) {
                ground.move(GROUND_MOVE_TO_LEFT);

                if (ground.isShown(width, height)) {
                    ground.draw(canvas);
                }
            } else {
                groundList.remove(ground);
                i--;
            }
        }

        /*
        * touchDownStartTimeの値が0を越えると(画面がタッチされていれば)
        * 経過時間に応じてゲージを表示する
        * */
        if (touchDownStartTime > 0) {
            float elapsedTime = System.currentTimeMillis() - touchDownStartTime;
            canvas.drawRect(0, 0, width * (elapsedTime / MAX_TOUCH_TIME), POWER_GAUGE_HEIGHT,
                    PAINT_POWER_GAUGE);
        }

        droid.move();

        droid.draw(canvas);
    }

    @Override
    public int getDistanceFromGround(Droid droid) {
        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < groundList.size(); i++) {
            Ground ground = groundList.get(i);

            if (!ground.isShown(width, height)) {
                continue;
            }

            boolean horizontal = !(droid.rect.left >= ground.rect.right || droid.rect.right <= ground.rect.left);
            if (horizontal) {
                if (!ground.isSolid()) {
                    return Integer.MAX_VALUE;
                }

                int distanceFromGround = ground.rect.top - droid.rect.bottom;
                if (distanceFromGround < 0) {
                    gameOver();
                    return Integer.MAX_VALUE;
                }
                return distanceFromGround;
            }
        }

        return Integer.MAX_VALUE;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownStartTime = System.currentTimeMillis();
                if(gameActivity.getIsDead()){
                    gameActivity.ToTitle();
                }
                return true;
            case MotionEvent.ACTION_UP:
                jumpDroid();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void jumpDroid() {
        float time = System.currentTimeMillis() - touchDownStartTime;
        touchDownStartTime = 0;

        if (getDistanceFromGround(droid) != 0) {
            return;
        }

        if (time > MAX_TOUCH_TIME) {
            time = MAX_TOUCH_TIME;
        }

        droid.jump(time / MAX_TOUCH_TIME);
    }





    private void gameOver() {
        if (isGameOver) {
            return;
        }

        isGameOver = true;

        droid.shutdown();


        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onGameOver();
            }
        });

    }

    private class DrawThread extends Thread {
        boolean isFinished;

        @Override
        public void run() {
         boolean v = true;
            SurfaceHolder holder = getHolder();

            Bitmap robot;

            robot = BitmapFactory.decodeResource(getResources(), R.drawable.sougen);


            while (!isFinished) {
                Canvas  canvas = holder.lockCanvas();
                if (canvas != null) {

                //    if (v){
                        drawBG(canvas,robot);
                  //      v = false;
                 //   }
                    drawGame(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }

                try {
                    sleep(1000 / FPS);
                } catch (InterruptedException e) {
                }
            }
        }
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

    public void startDrawThread() {
        stopDrawThread();

        drawThread = new DrawThread();
        drawThread.start();
    }

    public boolean stopDrawThread() {
        if (drawThread == null) {
            return false;
        }
        drawThread.isFinished = true;
        drawThread = null;
        return true;
    }
}
