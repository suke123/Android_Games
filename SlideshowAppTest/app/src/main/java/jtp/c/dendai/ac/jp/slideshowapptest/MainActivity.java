package jtp.c.dendai.ac.jp.slideshowapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Slideshowクラス呼び出し
        Slideshow slideshow = new Slideshow(this);

        //SlideshowクラスのFind_Dir_Pathメソッド呼び出し
        // 引数：画像を入れているassets中のフォルダ名「pictures」
        slideshow.Fine_Dir_Path("pictures");
        
        //SlideshowクラスのImageメソッド呼び出し
        slideshow.Image();
    }
}
