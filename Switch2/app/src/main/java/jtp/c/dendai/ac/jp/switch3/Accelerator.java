package jtp.c.dendai.ac.jp.switch3;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Button;


/*
 * Created on 2005/07/03
 *
 */

/**
 * 加速アイテム
 * @author mori
 *
 */
public class Accelerator extends Sprite {


   // private SoundPool mSoundPool;
   // private int mSoundId;
    // アイテムをとったときの音
  //  private AudioClip sound;

    private AudioAttributes audioAttributes;
    SoundPool soundPool;

     int soundOne, soundTwo;
    private Button button1, button2;

    public Accelerator(double x, double y, String fileName, Map map,Context context) {

        super(x, y, 1, map);
        soundPool = new SoundPool( 1,AudioManager.STREAM_MUSIC, 0 );
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


    //インスタンス作成
//    soundPool = new SoundPool( 1,AudioManager.STREAM_MUSIC, 0 );
 //   soundOne = soundPool.load(this, R.raw.se01, 1 );



        // one.wav をロードしておく
  //      soundOne = soundPool.load(this, R.raw.one, 1);

        // two.wav をロードしておく
 //       soundTwo = soundPool.load(this, R.raw.two, 1);

        // load が終わったか確認する場合





    public void update() {
    }
    
    /**
     * サウンドを再生
     */
    public void play() {
       // soundOne.play();
    }

    public void play2(){
        // one.wav の再生
        // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
        soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
    }
    
    /**
     * アイテムを使う
     */
    public void use(Player player) {
        // プレイヤーのスピードがアップ！
        player.setSpeed(player.getSpeed() * 2);
    }
}
