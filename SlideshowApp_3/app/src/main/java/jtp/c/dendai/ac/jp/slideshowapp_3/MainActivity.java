package jtp.c.dendai.ac.jp.slideshowapp_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Field[] fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImageView();         //ImageViewを
        getImageResources();    //画像リソース取得
        readImageResources();   //画像リソース読み込みyomikomi
        //TextView tv = (TextView)findViewById(R.id.text_view);

    }

    private void readImageResources() {
        for (Field field : fields) {
            try {
                //フィールド名取得
                String name = field.getName();
                //フィールドの値を取得する
                int value = (Integer) field.get(name);
                //画像読み込み
                imageView.setImageResource(value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void setImageView() {
        imageView = (ImageView) findViewById(R.id.slideshow_imageview);
    }

    private void getImageResources() {
        fields = R.drawable.class.getFields();
    }
}
