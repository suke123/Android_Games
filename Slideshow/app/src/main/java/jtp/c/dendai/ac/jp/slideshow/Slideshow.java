package jtp.c.dendai.ac.jp.slideshow;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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

    public Slideshow(Activity a){
        activity = a;
        imageView=(ImageView)a.findViewById(R.id.image_view);
    }

    public void Find_Path(String dir){
        assetManager = activity.getResources().getAssets();     //assetsフォルダ内のファイルを読み込む
        try {
            files = assetManager.list(dir);     //picturesファイルに含まれる全ての子のパスを取得し、String型配列filesに格納する
        } catch (IOException e) {               //例外処理
            Log.d("Search_path()", "Error");
        }
    }

    //画像を表示させるメソッド
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
}
