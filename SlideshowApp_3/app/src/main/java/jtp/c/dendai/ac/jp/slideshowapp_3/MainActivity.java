package jtp.c.dendai.ac.jp.slideshowapp_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    ImageButton startButton;
    ImageButton stopButton;
    ImageView imageView;
    Field[] fields;
    boolean isClick = false;
    TextView fieldsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImageView();         //ImageViewを
        getImageResources();    //画像リソース取得
        readImageResources();   //画像リソース読み込みyomikomi
        setButtonId();
        setTextView();

        /*startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(){

            }
        });*/

        //TextView tv = (TextView)findViewById(R.id.text_view);

    }

    private void setTextView() {
        fieldsText = (TextView) findViewById(R.id.fieldsText);
    }

    private void setButtonId() {
        startButton = (ImageButton) findViewById(R.id.btn_start);
        stopButton = (ImageButton) findViewById(R.id.btn_stop);
    }

    /*@Override
    public boolean onTouch(View v, MotionEvent event){

        return false;
    }*/

    private void readImageResources() {
        for (Field field : fields) {
            try {
                //フィールド名取得
                String name = field.getName();
                //フィールドの値を取得する
                int value = (Integer) field.get(name);
                fieldsText.setText(name);
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
