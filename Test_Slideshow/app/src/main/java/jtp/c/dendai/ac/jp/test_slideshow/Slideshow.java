package jtp.c.dendai.ac.jp.test_slideshow;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

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


    public Slideshow(Activity a) {
        activity = a;
        View.OnClickListener myListener = new MyListener();

        prev_btn = (Button) a.findViewById(R.id.prev_button);
        next_btn = (Button) a.findViewById(R.id.next_button);

        prev_btn.setOnClickListener(myListener);
        next_btn.setOnClickListener(myListener);

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
        //textView.setText((i + 1) + " / " + (files.length));     //何枚目の画像を表示しているのかを示す
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
                case R.id.prev_button:
                    if (i == 0) {               //1枚目の画像なら
                        i = files.length - 1;   //最後の画像を表示させる。（配列の最後の要素に設定する）
                        Image();
                    } else {
                        i--;                    //1枚目以外なら配列の前の要素に戻す
                        Image();
                    }
                    break;

                //NEXTボタンの処理
                case R.id.next_button:
                    if (i < files.length - 1) {         //最後の画像以外なら
                        i++;                            //配列の順番が次の画像を表示させる
                        Image();
                    } else {                            //最後の画像なら
                        i = 0;                          //１番目の画像を表示する
                        Image();
                    }
                    break;
            }
        }
    }
}
