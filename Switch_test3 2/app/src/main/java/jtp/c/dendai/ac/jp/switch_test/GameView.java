package jtp.c.dendai.ac.jp.switch_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by taka on 2017/10/25.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final Paint PAINT = new Paint();
    //private Map map;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private static final long FPS = 100;
    private int cycle;
    private int clock;
    protected final int width;
    protected final int height;
    protected double period;
    //private Bitmap boy,girl;
    private Map map;
    private Bitmap backGroundImg;
    private Bitmap[] Bits;
    private int offsetX=0,offsetY=0,offsetX2=0,offsetY2=0;
    public static  Point VIEW_SIZE = new Point(0,0);
    private Score score = new Score();
    public static int cnum = 0; //1:pl1が上 2:pl2が上
    private Context context;
    public boolean isBoy = false;

    private String fileName;

    private boolean isCleared = false;
    private boolean isGameOvered = false;

    //private Game game;

    //public static String DEBUG = new String();

    private int zahyouIndex = 0;

    public static int pnum = 0;

    //private Intent resultIntent;


    private static final int[] ids = {R.drawable.boy1,R.drawable.boy2,R.drawable.boy3, R.drawable.boy4,
            R.drawable.girl1,R.drawable.girl2,R.drawable.girl3, R.drawable.girl4,
            R.drawable.coinnnn};

    private double[][] coordinate = new double[][]{
        {12, 5, 3, 5},
        {1, 1, 0, 4},
        {0, 3, 3, 3}
    };


    // private static final int[] ids2 = {R.drawable.girl1,R.drawable.girl2,R.drawable.girl3, R.drawable.girl4};

    Activity activity;
    private Boy boy;
    private Girl girl;
    //private Coin coin;

    private int bx = 1500, by = 500, gx = 500, gy = 500;

    private class DrawThread extends Thread{
        boolean isFinished;

        @Override
        public void run(){

            SurfaceHolder holder = getHolder();

            while (!isFinished){

                Canvas canvas = holder.lockCanvas();

                if (canvas != null){
                    Move2();
                    paintComponent(canvas);
                    holder.unlockCanvasAndPost(canvas);

                }
                if(getIsCleared()){
                    stopDrawThread();
                    stopMoveThread();
                    try {
                        Thread.sleep(200);

                        Intent resultIntent = new Intent(activity.getApplication(), Result.class);
                        resultIntent.putExtra("Stagename", fileName);
                        resultIntent.putExtra("IsGoal", true);
                        resultIntent.putExtra("ChangeNum", getScore());
                        activity.startActivity(resultIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if(getIsGameovered()){
                    stopDrawThread();
                    stopMoveThread();
                    try {
                        Thread.sleep(200);

                        Intent resultIntent = new Intent(activity.getApplication(), Result.class);
                        resultIntent.putExtra("Stagename", fileName);
                        resultIntent.putExtra("IsGoal", false);
                        activity.startActivity(resultIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                try {
                    sleep(1000/FPS);
                }catch (InterruptedException e){

                }
            }
        }
    }

    private class MoveThread extends Thread{
        boolean isFinished;

        @Override
        public void run(){

            SurfaceHolder holder = getHolder();

            while (!isFinished){

                Canvas canvas = holder.lockCanvas();
                if (canvas != null){
                    boygirl();
                    SetBG();
                    if (!getIsBoy() && cnum == 1) {
                        //DEBUG = "BOY";
                        boy.update2(girl);
                    }
                    else
                        boy.update();

                    if (getIsBoy() && cnum == 2) {
                        //DEBUG = "GIRL";
                        girl.update2(boy);
                    }
                    else {
                        //DEBUG = "BOY3333";
                        girl.update();
                    }

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

    private MoveThread moveThread;

    public void startMoveThread(){
        stopMoveThread();

        moveThread = new MoveThread();
        moveThread.start();
    }

    private boolean stopMoveThread() {
        if(drawThread == null){
            return false;
        }
        drawThread.isFinished = true;
        drawThread = null;
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        startMoveThread();
        startDrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        stopMoveThread();
        stopDrawThread();
    }


    //こんすとらくた
    public GameView(Context context, Activity a,String filename, int zahyouIndex){
        super(context);
        this.context=context;
        getHolder().addCallback(this);
        //score.setIre(10);
        this.activity = a;
        this.fileName = filename;
        this.zahyouIndex = zahyouIndex;

        VIEW_SIZE = getDisplaySize(activity);
        VIEW_SIZE = new Point(1776,999);

        if(map == null){
            map = new Map(context, filename);
        }

        cycle = ids.length;
        Bits = new Bitmap[cycle];
        for (int i = 0; i < cycle; i++) {
            Bits[i] = BitmapFactory.decodeResource(getResources(), ids[i]);
        }
        width = Bits[0].getWidth();
        height = Bits[0].getHeight();
        clock = 0;
        period = 0;

        isCleared = false;

    }

    public static Point getDisplaySize(Activity activ){
        Display display = activ.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }

    //public void boygirl(Double x1,Double y1,Double x2,Double y2){
    public void boygirl(){

        if(boy == null){
            int aa = VIEW_SIZE.x<100 ? 100 :VIEW_SIZE.x;
            // Bitmap boyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy1);
            boy = new Boy(coordinate[zahyouIndex][0] * map.TILE_SIZE,
                    coordinate[zahyouIndex][1] * map.TILE_SIZE,Bits[0], bx, by,map);
        }
        if (girl == null){
            // Bitmap girlBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl1);
            girl = new Girl(coordinate[zahyouIndex][2] * map.TILE_SIZE,
                    coordinate[zahyouIndex][3] * map.TILE_SIZE,Bits[4], gx, gy,map);
        }
        //if (coin == null){
            // Bitmap girlBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl1);
            // coin = new Coin(400.0,20.0,Bits[4], map,context);
        //}

    }

    public Point GetSizeView(){
        return VIEW_SIZE;
    }


    public void drawDebug(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setTextSize(40);

        //canvas.drawText("A:"+girl.getX()+"X:"+cnum+" Y:"+DEBUG, 600, 100, paint);
        //canvas.drawText("boyOnGoal:"+boy.getGoal()+"\n girlOnGoal:"+girl.getGoal(), 600, 200, paint);
    }
/*
    public  void drawClearDebug(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setTextSize(100);

        canvas.drawText("CLEAR", 600, 600, paint);
    }

    public  void drawGameOverDebug(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setTextSize(100);

        canvas.drawText("GAMEOVER", 600, 600, paint);
    }
*/


    public void paintComponent(Canvas canvas) {
        if(isBoy){
            canvas.drawColor(Color.rgb(76,86,230));
        }
        else{
            canvas.drawColor(Color.rgb(230,90,115));
        }

        //drawDebug(canvas);

        if(boy == null){
            //    Bitmap boyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy1);
            boy = new Boy(coordinate[zahyouIndex][0] * map.TILE_SIZE,
                    coordinate[zahyouIndex][1] * map.TILE_SIZE,Bits[0], bx, by,map);
        }
        if (girl == null){
            //       Bitmap girlBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl1);
            girl = new Girl(coordinate[zahyouIndex][2] * map.TILE_SIZE,
                    coordinate[zahyouIndex][3] * map.TILE_SIZE,Bits[4], gx, gy,map);
        }

        // マップを描画
        map.draw(canvas, offsetX, offsetY);

        // プレイヤーを描画
        boy.draw(canvas, offsetX, offsetY);

        girl.draw(canvas, offsetX2, offsetY2);

    }



    public void Move2(){
        if(boy == null){
            //    Bitmap boyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy1);
            boy = new Boy(coordinate[zahyouIndex][0] * map.TILE_SIZE,
                    coordinate[zahyouIndex][1] * map.TILE_SIZE,Bits[0], bx, by,map);
        }
        if (girl == null){
           //       Bitmap girlBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl1);
           girl = new Girl(coordinate[zahyouIndex][2] * map.TILE_SIZE,
                   coordinate[zahyouIndex][3] * map.TILE_SIZE,Bits[4], gx, gy,map);
        }
        // X方向のオフセットを計算
        offsetX = VIEW_SIZE.x / 2 - (int) boy.getX();
        offsetX2 = VIEW_SIZE.x / 2 - (int) girl.getX();
        // マップの端ではスクロールしないようにする
        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, VIEW_SIZE.x - map.getWidth2());
        offsetX2 = Math.min(offsetX2, 0);
        offsetX2 = Math.max(offsetX2, VIEW_SIZE.x - map.getWidth2());


        // Y方向のオフセットを計算
        offsetY = VIEW_SIZE.y / 2 - (int) boy.getY();
        offsetY2 = VIEW_SIZE.y / 2 - (int) girl.getY();
        // マップの端ではスクロールしないようにする
        offsetY = Math.min(offsetY, 0);
        offsetY = Math.max(offsetY, VIEW_SIZE.y - map.getHeight2());
        offsetY2 = Math.min(offsetY2, 0);
        offsetY2 = Math.max(offsetY2, VIEW_SIZE.y - map.getHeight2());
    }


    public void SetBG(){

        if (iscol2()) {

            if ((int) boy.getY() < (int) girl.getY()) {
                boy.humu();

                cnum = 1;
            }

            if ((int) girl.getY() < (int) boy.getY()) {
                girl.humu();
                cnum = 2;
            }

        } else {
            cnum = 0;
            //   System.out.println("fff*");
        }

    }

    //今どっちが使用できるのか
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
    public boolean iscol2() {
        int ii = 0;
        int pl2x = (int) girl.getX(), pl2y = (int) girl.getY(),
                pl1x = (int) boy.getX(), pl1y = (int) boy.getY();

        Rect prect2 = new Rect(pl2x, pl2y, pl2x + girl.getWidth()+ii, pl2y + girl.getHeight()+ii);
        Rect prect1 = new Rect(pl1x, pl1y, pl1x + boy.getWidth()+ii, pl1y + boy.getHeight()+ii);
        // 自分の矩形と相手の矩形が重なっているか調べる
        if (prect2.intersect(prect1)) {
            girl.setjFalse(true);
            boy.setjFalse(true);
            return true;
        }

        return false;
    }

    public void boyMoveRight(){
        if (cnum == 2) {
            boy.accelerateRight();
            girl.accelerateRight();
            // break;
        } else {
            boy.accelerateRight();
            //    break;
        }
        //boy.accelerateRight();
    }

    public void boyMoveLeft(){
        if (cnum == 2) {
            boy.accelerateLeft();
            girl.accelerateLeft();

        } else {
            boy.accelerateLeft();

        }
        // boy.accelerateLeft();
    }

    public void boyJump(){
        if (cnum == 2) {
            //boy.jump();
            //girl.jump2();
            //  break;
        } else {
            boy.jump();
            //    break;
        }
        //boy.jump();
    }

    public void girlMoveRight(){
        if (cnum == 1) {
            boy.accelerateRight();
            girl.accelerateRight();
            // break;
        } else {
            girl.accelerateRight();
            //  break;
        }
        //girl.accelerateRight();
    }

    public void girlMoveLeft(){
        //  girl.accelerateLeft();
        if (cnum == 1) {
            boy.accelerateLeft();
            girl.accelerateLeft();
            //  break;
        } else {
            girl.accelerateLeft();
            //   break;
        }
    }

    public void girlJump(){
        if (cnum == 1) {
            //boy.jump2();
            //girl.jump();
            //break;
        } else {
            girl.jump();
            //break;
        }
        //girl.jump();
    }

    public int getScore(){
        return score.getIre();
    }

    public void setScore(){
        score.addIre();
    }

    private boolean getIsCleared(){
        if(boy.getGoal() && girl.getGoal()){
            isCleared = true;
        }

        return isCleared;
    }

    private boolean getIsGameovered(){
        if(boy.getIsGameOver() || girl.getIsGameOver()){
            isGameOvered = true;
        }
        return isGameOvered;
    }

}




