package jtp.c.dendai.ac.jp.slideshowapptest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

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
import android.widget.TextView;

/**
 * Created by taka on 2017/05/21.
 */

public class Slideshow {
    private int i, j = 0;
    private String[] files;
    private Button prev_btn, next_btn;
    private ImageButton start_btn, stop_btn;
    private ImageView imageView;
    private TextView textView;
    private Timer timer;
    private SlideTimerTask slideTimerTask;
    private Handler handler;
    private AssetManager assetManager;
    private Activity activity;

    public Slideshow(Activity a) {
        activity = a;
        handler = new Handler();
        View.OnClickListener myListener = new MyListener();

        //Button, ImageButton, ImageView, TextViewのIDを設定
        prev_btn = (Button) a.findViewById(R.id.button);
        start_btn = (ImageButton) a.findViewById(R.id.imageButton);
        stop_btn = (ImageButton) a.findViewById(R.id.imageButton2);
        next_btn = (Button) a.findViewById(R.id.button2);
        prev_btn.setOnClickListener(myListener);
        start_btn.setOnClickListener(myListener);
        stop_btn.setOnClickListener(myListener);
        next_btn.setOnClickListener(myListener);
        imageView = (ImageView) a.findViewById(R.id.image_view);
        textView = (TextView) a.findViewById(R.id.textView);
        //textView2 = (TextView) a.findViewById(R.id.textView2);
    }

    //assetsの中のpicturesのファイルパスを取得
    public void Fine_Dir_Path(String dir) {
        assetManager = activity.getResources().getAssets();     //assetsフォルダ内のファイルを読み込む
        try {
            files = assetManager.list(dir);     //picturesファイルに含まれる全ての子のパスを取得し、String型配列filesに格納する
        } catch (IOException e) {               //例外処理
            Log.d("Search_path()", "Error");
        }
    }

    //画像を表示させるメソッド
    public void Image() {
        textView.setText((i + 1) + " / " + (files.length));     //何枚目の画像を表示しているのかを示す
        //textView2.setText(files[i]);
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

    //TimerTask:一定時間に同じ処理を繰り返す
    //Startボタンを押された時の処理を行うクラス
    public class SlideTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    next_btn.performClick();    //performClick():ボタンを押さずにonClickイベントを発生
                }
            });
        }
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //previousボタンの処理
                case R.id.button:
                    if (i == 0) {
                        i = files.length - 1;
                        Image();
                    } else {
                        i--;
                        Image();
                    }
                    break;

                //スタートボタンの処理
                case R.id.imageButton:
                    if (j == 0) {
                        timer = new Timer();
                        slideTimerTask = new SlideTimerTask();
                        timer.schedule(slideTimerTask, 2000, 2000); //スライドショーの遷移時間
                        j = 1;
                    } else {
                        j = 0;
                    }
                    break;

                //ストップボタンの処理
                case R.id.imageButton2:
                    j = 0;
                    slideTimerTask.cancel();
                    slideTimerTask = null;
                    break;

                //NEXTボタンの処理
                case R.id.button2:
                    if (i < files.length - 1) {
                        i++;
                        Image();
                    } else {
                        i = 0;
                        Image();
                    }
                    break;
            }
        }
    }
}
