package jtp.c.dendai.ac.jp.switch3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;


/*
 * Created on 2005/06/24
 *
 */

/**
 * @author mori
 *
 */
public abstract class Sprite extends Imageint {

    private static final int[] ids = {R.drawable.boy1,R.drawable.boy2,R.drawable.boy3, R.drawable.boy4};
    private static final int[] ids2 = {R.drawable.girl1,R.drawable.girl2,R.drawable.girl3, R.drawable.girl4};
    private static final int[] ids3 = {R.drawable.block,R.drawable.block};
    private static final int[] ids4 = {R.drawable.boy1,R.drawable.boy2,R.drawable.boy3, R.drawable.boy4
    ,R.drawable.girl1,R.drawable.girl2,R.drawable.girl3, R.drawable.girl4,
            R.drawable.block,R.drawable.block};

    private ArrayList<Integer[]> list =new ArrayList<Integer[]>();


    public ArrayList<Integer[]> getList() {
        return list;
    }

    Paint paint = new Paint();
    // 位置
    protected double x;
    protected double y;
    protected int id;
    
    // 幅
    protected int width;
    // 高さ
    protected int height;
    
    // スプライト画像
    //protected Image image;
    protected Bitmap bitmap ;

    // アニメーション用カウンタ
    protected int count;

    // マップへの参照
    protected Map map;

    public Sprite(double x, double y, int id, Map map) {

        super(ids4);

        this.x = x;
        this.y = y;
        this.map = map;
        this.id = id;

        width = 32;
        height = 32;

        // イメージをロードする
       // loadImage(fileName);

        count = 0;
        
        // アニメーション用スレッドを開始
        AnimationThread thread = new AnimationThread();
        thread.start();
    }

    /**
     * スプライトの状態を更新する
     */
    public abstract void update();

    private Bitmap bit = getBit(id);

    /**
     * スプライトを描画
     * 
     *
     * @param offsetX X方向オフセット
     * @param offsetY Y方向オフセット
     */
    public void draw(Canvas canvas, int offsetX, int offsetY,int idd) {
         Bitmap bits =getBit(idd);
        canvas.drawBitmap(bits,
                (int) x + offsetX, (int) y + offsetY, paint);
    }

    /**
     * 他のスプライトと接触しているか
     * @param sprite スプライト
     */
    public boolean isCollision(Sprite sprite) {
        Rect playerRect = new Rect((int)x, (int)y, (int)x + width, (int)y + height);
        Rect spriteRect = new Rect((int)sprite.getX(), (int)sprite.getY(), sprite.getWidth(), sprite.getHeight());
        // 自分の矩形と相手の矩形が重なっているか調べる
        if (playerRect.intersect(spriteRect)) {
            return true;
        }
        
        return false;
    }




    /**
     * @return Returns the x.
     */
    public double getX() {
        return x;
    }
    /**
     * @return Returns the y.
     */
    public double getY() {
        return y;
    }
    /**
     * @return Returns the width.
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return Returns the height.
     */
    public int getHeight() {
        return height;
    }



    // アニメーション用スレッド
    private class AnimationThread extends Thread {
        public void run() {
            while (true) {
                // countを切り替える
                if (count == 0) {
                    count = 1;
                } else if (count == 1) {
                    count = 0;
                }

                // 300ミリ秒休止＝300ミリ秒おきに勇者の絵を切り替える
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
