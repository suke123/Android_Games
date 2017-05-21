package jtp.c.dendai.ac.jp.slideshowapp_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.Integer;

public class MainActivity extends AppCompatActivity {

    ImageButton startButton;
    ImageButton stopButton;
    ImageView imageView;
    Field[] fields;
    //boolean isClick = false;
    TextView fieldText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImageView();         //ImageViewを
        getImageResources();    //画像リソース取得
        readImageResources();   //画像リソース読み込みyomikomi
        //setButtonId();
        setTextView();

        /*startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(){

            }
        });*/

        //TextView tv = (TextView)findViewById(R.id.text_view);

    }

    private void setTextView() {
        fieldText = (TextView) findViewById(R.id.fieldText);
    }

    /*private void setButtonId() {
        startButton = (ImageButton) findViewById(R.id.btn_start);
        stopButton = (ImageButton) findViewById(R.id.btn_stop);
    }*/

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
                //int value = (Integer) field.get(name);
                setText(name);
                //fieldText.setText(value);
                //画像読み込み
                //imageView.setImageResource(value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } /*catch (IllegalAccessException e) {
                e.printStackTrace();
            }*/
        }
    }

    private void setText(String name) {
        if(name == null){
            fieldText.setText("name is null !!");
        }else{
            fieldText.setText(name);
        }
    }

    public void setImageView() {
        imageView = (ImageView) findViewById(R.id.slideshow_imageview);
    }

    public void getImageResources() {
        fields = R.drawable.class.getFields();
    }
}
