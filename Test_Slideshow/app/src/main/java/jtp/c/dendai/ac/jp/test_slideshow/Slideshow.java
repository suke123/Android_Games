package jtp.c.dendai.ac.jp.test_slideshow;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by taka on 2017/05/22.
 */

public class Slideshow {
    private int i = 0;
    private String[] files;
    private ImageView imageView;
    private AssetManager assetManager;
    private Activity activity;
    private Button prev_btn, next_btn;
    private ImageButton start_btn, stop_btn;
    private Timer timer;
    private SlideTimerTask slideTimerTask;
    private Handler handler;
    private boolean hasClickedStartButton = false;     //StartButtonが押されたかどうか


    public Slideshow(Activity a) {
        activity = a;
        View.OnClickListener myListener = new MyListener();
        handler = new Handler();        //widgetの属するスレッドにてHandlerクラスのインスタンスを生成

        prev_btn = (Button) a.findViewById(R.id.button);
        next_btn = (Button) a.findViewById(R.id.button2);
        prev_btn.setOnClickListener(myListener);
        next_btn.setOnClickListener(myListener);

        start_btn = (ImageButton) a.findViewById(R.id.start_button);
        stop_btn = (ImageButton) a.findViewById(R.id.stop_button);
        start_btn.setOnClickListener(myListener);
        stop_btn.setOnClickListener(myListener);

        imageView = (ImageView) a.findViewById(R.id.image_view);
    }

    //assetsの中のpicturesのファイルパスを取得
    public void Find_Dir(String dir) {
        assetManager = activity.getResources().getAssets();     //assetsフォルダ内のファイルを読み込む
        try {
            files = assetManager.list(dir);     //picturesファイルに含まれる全ての子のパスを取得し、String型配列filesに格納する
        } catch (IOException e) {               //例外処理
            Log.d("Search_path()", "Error");
        }
    }

    //画像を表示させるメソッド
    public void Image() {
        try {
            //assetsフォルダ内のpicturesフォルダの画像を読み込み、ImageViewに表示させる
            //getAssets で AssetManager を取得し、open メソッドで画像の InputStream を開く
            InputStream istream = activity.getResources().getAssets().open("pictures/" + files[i]);
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.d("Image()", "Error");
        }
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //previousボタンの処理
                case R.id.button:
                    if (i == 0) {               //1枚目の画像なら
                        i = files.length - 1;   //最後の画像を表示させる。（配列の最後の要素に設定する）
                        Image();
                    } else {
                        i--;                    //1枚目以外なら配列の前の要素に戻す
                        Image();
                    }
                    break;

                //NEXTボタンの処理
                case R.id.button2:
                    if (i < files.length - 1) {         //最後の画像以外なら
                        i++;                            //配列の順番が次の画像を表示させる
                        Image();
                    } else {                            //最後の画像なら
                        i = 0;                          //１番目の画像を表示する
                        Image();
                    }
                    break;

                //スタートボタンの処理
                case R.id.start_button:
                    if (!hasClickedStartButton) {       //hasClickedStartButtonがfalseならスライドショー起動
                        timer = new Timer();
                        slideTimerTask = new SlideTimerTask();      //NEXTボタンが2秒間隔で押されているのと同様の処理を行う
                        timer.schedule(slideTimerTask, 2000, 2000); //スライドショーの遷移時間
                        hasClickedStartButton = true;          //スライドしている状態にする(hasClickedStartButtonをtrueにする)
                    } else {
                        hasClickedStartButton = false;          //スライドしていない状態に戻す(hasClickedStartButtonをfalseにする)
                    }
                    break;

                //ストップボタンの処理
                case R.id.stop_button:
                    if (hasClickedStartButton) {           //スライドしているなら(hasClickedStartButton　が　true)
                        hasClickedStartButton = false;     //スライドしていない状態に戻す(hasClickedStartButtonをfalseにする)
                        slideTimerTask.cancel();        //slideTimerTaskの処理を取り消す(スライドショーを停止させる)
                        slideTimerTask = null;          //slideTimerTaskを初期化する
                    }
                    break;

            }
        }
    }

    //TimerTask:一定時間に同じ処理を繰り返す
    //Startボタンを押された時の処理を行うクラス
    //繰り返し画像を切り替えるためスレッドを用いる
    private class SlideTimerTask extends TimerTask {
        @Override
        public void run() {                     //スレッドからUIを操作する
            //普通別スレッドからwidgetに直接アクセつできないが、Handlerクラスのpostメソッドを使用することで、解決する。
            //widgetの属するスレッドにてHandlerクラスのインスタンスを生成して、このインスタンスの postメソッドの引数に、
            //widgetにアクセスするコードを記述したRunnableクラスを指定する。
            handler.post(new Runnable() {
                public void run() {
                    next_btn.performClick();    //performClick():ボタンを押さずにonClickイベントを発生
                }
            });
        }
    }

}
