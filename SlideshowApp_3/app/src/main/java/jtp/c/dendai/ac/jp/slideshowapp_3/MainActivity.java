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
    String imageName = null;
    int fieldValue=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImageView();         //ImageViewを
        //getImageResources();    //画像リソース取得
        //readImageResources();   //画像リソース読み込みyomikomi
        //setText(imageName);
        //fieldText.setText(fieldValue);
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

    /*private void readImageResources() {
        for (Field field : fields) {
            try {
                //フィールド名取得
                imageName = field.getName();
                //フィールドの値を取得する
                //fieldValue = (Integer) field.get(imageName);
                //画像読み込み
                //imageView.setImageResource(value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } /*catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }*/

    public void setText(String name) {
        if(name == null){
            fieldText.setText(null);
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
