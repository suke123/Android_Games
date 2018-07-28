package jtp.c.dendai.ac.jp.switch_test;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/*
 * Created on 2005/06/06
 *
 */

/**
 * @author mori
 *  
 */
public class Player extends Sprite {
    // 方向
    private static final int RIGHT = 0;
    private static final int LEFT = 1;

    // 速度
    protected double vx;
    protected double vy;

    // スピード
    private double speed;
    // ジャンプ力
    private double jumpSpeed;

    // 着地しているか
    private boolean onGround;
    // 再ジャンプできるか
    private boolean forceJump;

    // 向いている方向
    private int dir;

    public Player(double x, double y, String filename, MapView mapView) {
        super(x, y, 1);
        
        vx = 0;
        vy = 0;
        speed = 6.0;
        jumpSpeed = 12.0;
        onGround = false;
        forceJump = false;
        dir = RIGHT;
    }

    /**
     * 停止する
     */
    public void stop() {
        vx = 0;
    }
    
    	    //yes
    public void humu() {
        vx = 0;
        // 位置調整
             //   y = Map.tilesToPixels(tile.y) - height;
                // 着地したのでy方向速度を0に
                vy = -0.6;
                
              onGround = true;
    }

    /**
     * 左に加速する
     */
    public void accelerateLeft() {
        vx = -speed;
        dir = LEFT;
    }

    /**
     * 右に加速する
     */
    public void accelerateRight() {
        vx = speed;
        dir = RIGHT;
    }

    /**
     * ジャンプする
     */
    public void jump() {
        // 地上にいるか再ジャンプ可能なら
        if (onGround || forceJump) {
            // 上向きに速度を加える
            vy = -jumpSpeed;
            onGround = false;
            forceJump = false;
        }
    }
    
    
            public void jump2() {
        // 地上にいるか再ジャンプ可能なら
        if (onGround || forceJump) {
            // 上向きに速度を加える
            vy = -jumpSpeed -4.2;
            onGround = false;
            forceJump = false;
        }
    }
    

    /**
     * プレイヤーの状態を更新する
     */
    public void update() {
        // 重力で下向きに加速度がかかる
        vy += MapView.GRAVITY;

        // x方向の当たり判定
        // 移動先座標を求める
        double newX = x + vx;
        // 移動先座標で衝突するタイルの位置を取得
        // x方向だけ考えるのでy座標は変化しないと仮定
        Point tile = mapView.getTileCollision(this, newX, y);
        if (tile == null) {
            // 衝突するタイルがなければ移動
            x = newX;
        } else {
            // 衝突するタイルがある場合
            if (vx > 0) { // 右へ移動中なので右のブロックと衝突
                // ブロックにめりこむ or 隙間がないように位置調整
                x = MapView.tilesToPixels(tile.x) - width;
            } else if (vx < 0) { // 左へ移動中なので左のブロックと衝突
                // 位置調整
                x = MapView.tilesToPixels(tile.x + 1);
            }
            vx = 0;
        }

        // y方向の当たり判定
        // 移動先座標を求める
        double newY = y + vy;
        // 移動先座標で衝突するタイルの位置を取得
        // y方向だけ考えるのでx座標は変化しないと仮定
        tile = mapView.getTileCollision(this, x, newY);
        if (tile == null) {
            // 衝突するタイルがなければ移動
            y = newY;
            // 衝突してないということは空中
            onGround = false;
        } else {
            // 衝突するタイルがある場合
            if (vy > 0) { // 下へ移動中なので下のブロックと衝突（着地）
                // 位置調整
                y = MapView.tilesToPixels(tile.y) - height;
                // 着地したのでy方向速度を0に
                vy = 0;
                // 着地
                onGround = true;
            } else if (vy < 0) { // 上へ移動中なので上のブロックと衝突（天井ごん！）
                // 位置調整
                y = MapView.tilesToPixels(tile.y + 1);
                // 天井にぶつかったのでy方向速度を0に
                vy = 0;
            }
        }
    }
    
    
    //その２
    
    
     public void update2(Player2 p2) {
        // 重力で下向きに加速度がかかる
        vy += MapView.GRAVITY;

        // x方向の当たり判定
        // 移動先座標を求める
        double newX = x + vx*1.3;
        // 移動先座標で衝突するタイルの位置を取得
        // x方向だけ考えるのでy座標は変化しないと仮定
        Point tile = mapView.getTileCollisionR(this, newX, y);
        if (tile == null) {
            // 衝突するタイルがなければ移動
            x = newX;
        } else {
            // 衝突するタイルがある場合
            if (vx > 0) { // 右へ移動中なので右のブロックと衝突
                // ブロックにめりこむ or 隙間がないように位置調整
                x = MapView.tilesToPixels(tile.x) - width;
            } else if (vx < 0) { // 左へ移動中なので左のブロックと衝突
                // 位置調整
                x = MapView.tilesToPixels(tile.x + 1);
            }
            vx = 0;
        }

        // y方向の当たり判定
        // 移動先座標を求める
        double newY = y + vy;
        // 移動先座標で衝突するタイルの位置を取得
        // y方向だけ考えるのでx座標は変化しないと仮定
        tile = mapView.getTileCollisionR(this, x, newY);
        if (tile == null) {
            // 衝突するタイルがなければ移動
            //y = newY;
            y = p2.getY()-32;
            // 衝突してないということは空中
            onGround = false;
        } else {
            // 衝突するタイルがある場合
            if (vy > 0) { // 下へ移動中なので下のブロックと衝突（着地）
                // 位置調整
                y = MapView.tilesToPixels(tile.y) - height;
                // 着地したのでy方向速度を0に
                vy = 0;
                // 着地
                onGround = true;
            } else if (vy < 0) { // 上へ移動中なので上のブロックと衝突（天井ごん！）
                // 位置調整
                y = MapView.tilesToPixels(tile.y + 1);
                // 天井にぶつかったのでy方向速度を0に
                vy = 0;
            }
        }
    }
    
    Paint paint = new Paint();

    /**
     * プレイヤーを描画（オーバーライド）
     * 
     *
     * @param offsetX X方向オフセット
     * @param offsetY Y方向オフセット
     */
    public void draw(Canvas canvas, int offsetX, int offsetY) {

        canvas.drawBitmap(bitmap,
                (int) x + offsetX, (int) y + offsetY,
                paint);
    }

    /**
     * @param forceJump The forceJump to set.
     */
    public void setForceJump(boolean forceJump) {
        this.forceJump = forceJump;
    }
    
    /**
     * @return Returns the speed.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed to set.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}