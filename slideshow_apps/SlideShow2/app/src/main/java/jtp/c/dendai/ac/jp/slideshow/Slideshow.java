package jtp.c.dendai.ac.jp.slideshow;

/**
 * Created by taka on 2017/05/23.
 */
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

public class Slideshow {
    private int i = 0;
    private String[] files;
    private Button prev_btn, next_btn;
    private ImageView imageView;
    private AssetManager assetManager;
    private Activity activity;

    public Slideshow(Activity a) {
        activity = a;
        View.OnClickListener myListener = new MyListener();

        //Button, ImageButton, ImageView, TextViewのIDを設定
        //prev_btn = (Button) a.findViewById(R.id.button);
        //next_btn = (Button) a.findViewById(R.id.button2);
        //prev_btn.setOnClickListener(myListener);
        //next_btn.setOnClickListener(myListener);
        imageView = (ImageView) a.findViewById(R.id.image_view);
    }

    public void Find_Dir(String dir) {
        assetManager = activity.getResources().getAssets();     //assetsフォルダ内のファイルを読み込む
        try {
            files = assetManager.list(dir);     //picturesファイルに含まれる全ての子のパスを取得し、String型配列filesに格納する
        } catch (IOException e) {               //例外処理
            Log.d("Search_path()", "Error");
        }
    }

    public void Image() {
        try {
            //assetsフォルダ内のpicturesフォルダの画像を読み込み、ImageViewに表示させる
            //getAssets で AssetManager を取得し、open メソッドで画像の InputStream を開く
            InputStream istream = activity.getResources().getAssets().open("pictures/"+files[i]);
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.d("Image()", "Error");
        }
    }
}
