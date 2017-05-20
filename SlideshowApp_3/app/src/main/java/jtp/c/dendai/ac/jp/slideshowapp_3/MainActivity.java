package jtp.c.dendai.ac.jp.slideshowapp_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ViewFlipper viewFlipper;
    private float firstTouch;
    private boolean isFlip = false;
    ImageView imageView;
    Field[] fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getImageResources();    //画像リソース取得
        //TextView tv = (TextView)findViewById(R.id.text_view);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //isImageTouched();
        readImageResources();   //画像リソース読み込み
        setImageView();         //ImageViewを
        int x = (int) event.getRawX();
        //imageVisibility();
        switch (v.getId()) {
            //case R.id.layout_first:
            case R.id.slideshow_imageview:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        firstTouch = event.getRawX();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (!isFlip) {
                            if (x - firstTouch > 50) {
                                isFlip = true;
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_left));
                                //readImageResources();   //画像リソース読み込み
                                //viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_out_right));
//                                textView.setText(files.length);
                                viewFlipper.showNext();
                            } else if (firstTouch - x > 50) {
                                isFlip = true;
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_right));
                                //readImageResources();   //画像リソース読み込み
                                //viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_out_left));
                                viewFlipper.showPrevious();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isFlip = false;
                        break;
                }
        }

        return false;
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
