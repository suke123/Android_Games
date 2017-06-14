package jtp.c.dendai.ac.jp.shooting_sample02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import android.os.Handler;
import android.widget.ImageView;

import static android.graphics.Typeface.BOLD_ITALIC;

/**
 * Created by taka on 2017/06/01.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    //GameViewのイベントを外部に伝えるためのEventCallbackインターフェースを追加
    /*
    * EvantCallback始まり
    * */
    public interface EventCallback {
        void onGameOver(long score);
    }

    private EventCallback eventCallback;

    public void setEventCallback(EventCallback eventCallback) {
        this.eventCallback = eventCallback;
    }
    /*
    * EvantCallback終わり
    * */

    private Handler handler = new Handler();

    private static final long DRAW_INTERVAL = 1000 / 50;   //描画間隔
    private static final long BULLET_SHOOT_INTERVAL = 1000 / 20;   //弾の発射間隔
    private static final int MISSILE_LAUNCH_WEIGHT = 10;
    private static final float HIGHSCORE_TEXT_SIZE = 30.0f;
    private static final float SCORE_TEXT_SIZE = 70.0f;

    private MyFighter myFighter;        //自機クラス
    private MyBullet bullet;            //自弾クラスkurasu
    public ImageView myFighterImageView;
    private Bitmap myFighterBitmap;
    View.OnTouchListener myListener;

    private int preDx = 0, preDy = 0, newDx = 0, newDy = 0;

    //private int bullet_type = 1;
    private final List<BaseObject> missileList = new ArrayList<>();
    private final Random rand = new Random(System.currentTimeMillis());
    private final List<BaseObject> bulletList = new ArrayList<>();

    private long score, highScore = 0;
    private final Paint paintScore = new Paint();
    private final Paint paintHighscore = new Paint();

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
                shootBullet();
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

    private void shootBullet() {
        fire(getX(), getY());

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

        //myListener = new MyListener();

        paintHighscore.setColor(Color.BLACK);
        paintHighscore.setTypeface(Typeface.defaultFromStyle(BOLD_ITALIC));
        paintHighscore.setTextSize(HIGHSCORE_TEXT_SIZE);
        paintHighscore.setAntiAlias(true);

        paintScore.setColor(Color.BLACK);
        paintScore.setTypeface(Typeface.defaultFromStyle(BOLD_ITALIC));
        paintScore.setTextSize(HIGHSCORE_TEXT_SIZE + 10);
        paintScore.setAntiAlias(true);

        getHolder().addCallback(this);

        myFighterImageView = (ImageView) findViewById(R.id.image_view);
        myFighterBitmap = ((BitmapDrawable) myFighterImageView.getDrawable()).getBitmap();

    }

    protected void drawGame(Canvas canvas) {
        canvas.drawColor(Color.WHITE);      //背景：白

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (myFighter == null) {
            //myFighterBitmap = ((BitmapDrawable) myFighterImageView.getDrawable()).getBitmap();
            //myFighterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jibun);
            //myFighterImageView.setImageBitmap(myFighterBitmap);
            myFighter = new MyFighter(myFighterBitmap, width, height);
            //myFighterImageView.setImageBitmap(myFighterBitmap);
            //myFighterImageView.setOnTouchListener(myListener);

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

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        eventCallback.onGameOver(score);
                    }
                });

                break;
            }

            //弾とミサイルが当たったかどうかを判定
            for (int j = 0; j < bulletList.size(); j++) {
                BaseObject bullet = bulletList.get(j);

                if (bullet.isHit(missile)) {
                    missile.hit();
                    bullet.hit();

                    score += 10;
                    if (highScore < score) {
                        highScore = score;
                    }
                }
            }
        }

        myFighter.draw(canvas);

        canvas.drawText("TOP:" + String.valueOf(highScore), 10, HIGHSCORE_TEXT_SIZE, paintHighscore);
        canvas.drawText(String.valueOf(score), 10, SCORE_TEXT_SIZE, paintScore);
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
        //switch (event.getAction()) {
        //case MotionEvent.ACTION_DOWN:
        //fire(event.getX(), event.getY());
        // x,y 位置取得
        newDx = (int) event.getRawX();
        newDy = (int) event.getRawY();

        switch (event.getAction()) {
            // タッチダウンでdragされた
            case MotionEvent.ACTION_MOVE:
                // ACTION_MOVEでの位置
                int dx = myFighter.left + (newDx - preDx);
                int dy = myFighter.top + (newDy - preDy);

                // 画像の位置を設定する
                myFighterImageView.layout(dx, dy, dx + myFighter.getWidth(),
                        dy + myFighter.getHeight());

                Log.d("onTouch", "ACTION_MOVE: dx=" + dx + ", dy=" + dy);
                break;
        }

        // タッチした位置を古い位置とする
        preDx = newDx;
        preDy = newDy;

        return true;
        //}

        //return super.onTouchEvent(event);
    }

    private void fire(float x, float y) {
        float alignX = (x - myFighter.rect.centerX()) / Math.abs(y - myFighter.rect.centerY());
        Bitmap bulletBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mybullet);

        bullet = new MyBullet(bulletBitmap, myFighter.rect, alignX);
        bulletList.add(0, bullet);

        synchronized (this) {
            try {
                wait(50);
            } catch (InterruptedException e) {

            }
        }
    }


    private Missile launchMissile(int width, int height) {
        Bitmap enemyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.enemy01);

        int fromX = rand.nextInt(width);
        int toX = rand.nextInt(height);

        float alignX = (toX - fromX) / (float) height;
        return new Missile(enemyBitmap, fromX, alignX);
    }
/*
    private class MyListener implements OnTouchListener {
        public boolean onTouch(View v, MotionEvent event) {
            // x,y 位置取得
            newDx = (int) event.getRawX();
            newDy = (int) event.getRawY();

            switch (event.getAction()) {
                // タッチダウンでdragされた
                case MotionEvent.ACTION_MOVE:
                    // ACTION_MOVEでの位置
                    int dx = myFighterImageView.getLeft() + (newDx - preDx);
                    int dy = myFighterImageView.getTop() + (newDy - preDy);

                    // 画像の位置を設定する
                    myFighterImageView.layout(dx, dy, dx + myFighterImageView.getWidth(),
                            dy + myFighterImageView.getHeight());

                    Log.d("onTouch", "ACTION_MOVE: dx=" + dx + ", dy=" + dy);
                    break;
            }

            // タッチした位置を古い位置とする
            preDx = newDx;
            preDy = newDy;

            return true;
        }
    }
    */
}
