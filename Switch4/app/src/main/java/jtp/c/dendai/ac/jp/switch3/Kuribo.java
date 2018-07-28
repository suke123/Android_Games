package jtp.c.dendai.ac.jp.switch3;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Button;
import android.graphics.Point;



/*
 * Created on 2005/06/27
 *
 */

/**
 * @author mori
 *
 */
public class Kuribo extends Sprite {
    // スピード
    private static final double SPEED = 1;

    // 速度
    protected double vx;
    protected double vy;

    private AudioAttributes audioAttributes;
    SoundPool soundPool;

    int soundOne, soundTwo;
    private Button button1, button2;

    // 踏まれたときの音
   // private AudioClip sound;

    public Kuribo(double x, double y, String filename, MapView mapView, Context context) {
        super(x, y, 1, mapView);
        
        // 左に移動を続ける
        vx = -SPEED;
        vy = 0;
        
        // サウンドをロード
        soundPool = new SoundPool( 1, AudioManager.STREAM_MUSIC, 0 );
        soundOne = soundPool.load(context, R.raw.coin03, 1 );

        // サウンドをロード
        //      sound = Applet.newAudioClip(getClass().getResource("/se/chari13_c.wav"));
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        });
    }

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
            // 移動方向を反転
            vx = -vx;
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
        } else {
            // 衝突するタイルがある場合
            if (vy > 0) { // 下へ移動中なので下のブロックと衝突（着地）
                // 位置調整
                y = MapView.tilesToPixels(tile.y) - height;
                // 着地したのでy方向速度を0に
                vy = 0;
            } else if (vy < 0) { // 上へ移動中なので上のブロックと衝突（天井ごん！）
                // 位置調整
                y = MapView.tilesToPixels(tile.y + 1);
                // 天井にぶつかったのでy方向速度を0に
                vy = 0;
            }
        }
    }
    
    /**
     * サウンドを再生
     */
    public void play() {
        soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
    }
}
