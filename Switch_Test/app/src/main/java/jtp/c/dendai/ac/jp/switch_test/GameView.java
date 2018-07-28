package jtp.c.dendai.ac.jp.switch_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by taka on 2017/10/25.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Boy.Callback, Girl.Callback {

    private static final Paint PAINT = new Paint();
    //private Map map;

    private static final long FPS = 150;

    //private Bitmap boy,girl;
    private Map map;
    private Bitmap backGroundImg;
    Activity activity;
    private Boy boy;
    private Girl girl;

    private String fileName;

    private int TILE_SIZE = 0;// map.getTILE_SIZE();

    // bx:少年のx座標,
    // by:少年のy座標,
    // gx:少女のx座標,
    // gy:少女のy座標
    private int bx = 12, by = 5, gx = 3, gy = 5;
    Bitmap boyBitmap, girlBitmap;

    private boolean isBoy = true; //true:boy, false:girl

    private class DrawThread extends Thread{
        boolean isFinished;

        @Override
        public void run(){
            SurfaceHolder holder = getHolder();

            while (!isFinished){
                Canvas canvas = holder.lockCanvas();
                if (canvas != null){
                    drawGame(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }

                try {
                    sleep(1000/FPS);
                }catch (InterruptedException e){

                }
            }
        }
    }

    private DrawThread drawThread;

    public void startDrawThread(){
        stopDrawThread();

        drawThread = new DrawThread();
        drawThread.start();
    }

    private boolean stopDrawThread() {
        if(drawThread == null){
            return false;
        }
        drawThread.isFinished = true;
        drawThread = null;
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        startDrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int widt, int height){
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        stopDrawThread();
    }

    public GameView(Context context, Activity a, String fileName){
        super(context);

        //this.fileName = fileName;

        getHolder().addCallback(this);
        this.activity = a;

        if(map == null){
            map = new Map(context, fileName);
            TILE_SIZE = map.getTILE_SIZE();
        }

        boyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy4);
        girlBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl1);
    }

    public void drawGame(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        if(boy == null){
            boy = new Boy(boyBitmap, bx * TILE_SIZE, by * TILE_SIZE, this);
        }
        if (girl == null){
            girl = new Girl(girlBitmap, gx * TILE_SIZE, gy * TILE_SIZE, this);
        }

        //boy.move();
        girl.move();
        map.draw(canvas, 0, 0);
        boy.draw(canvas);
        girl.draw(canvas);

        //invalidate();
    }

    public void boyMoveRight(){
        boy.moveRight();
    }
    public void boyMoveLeft(){
        boy.moveLeft();
    }
    public void boyMoveUp(){
        boy.jump();
    }

    public void girlMoveRight(){
        girl.moveRight();
    }
    public void girlMoveLeft(){
        girl.moveLeft();
    }
    public void girlMoveUp(){
        girl.jump();
    }

    @Override
    public int getDistanceFromBlueBlock(Boy boy){
        //return map.tileTop - boy.rect.bottom;
        return 1;
    }

    @Override
    public int getDistanceFromGoal(Boy boy){
        return 1;
    }

    @Override
    public int getDistanceFromRedBlock(Girl girl){
        return 1;
    }

    @Override
    public int getDistanceFromGoal(Girl girl){
        return 1;
    }

    public boolean getIsBoy(){
        return isBoy;
    }

    public void setIsBoy(int i){
        if(i == 1){ //男操作可
            isBoy = true;
        }
        else { //女操作可
            isBoy = false;
        }
    }


}
