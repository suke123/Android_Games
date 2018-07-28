package jtp.c.dendai.ac.jp.switch3;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Button;

/*
 * Created on 2005/06/24
 *
 */

/**
 * @author mori
 *
 */
public class Coin extends Sprite {
    // コインをとったときの音
    private AudioAttributes audioAttributes;
    SoundPool soundPool;

    int soundOne, soundTwo;
    private Button button1, button2;
   // private AudioClip sound;

    public Coin(double x, double y, String fileName, MapView mapView, Context context) {
        super(x, y, 1, mapView);
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
    }
    
    /**
     * サウンドを再生
     */
    public void play() {

        //sound.play();
    }

    public void play2(){
        // one.wav の再生
        // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
        soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
    }
}
