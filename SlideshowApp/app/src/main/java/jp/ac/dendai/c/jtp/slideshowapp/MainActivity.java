package jp.ac.dendai.c.jtp.slideshowapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ViewFlipper viewFlipper;
    private float firstTouch;
    private boolean isFlip = false;
    private ImageView firstImageView;
    private ImageView secondImageView;
    Button[] btn = new Button[2];
    ImageButton[] ibtn = new ImageButton[2];
    boolean isTouched = false;

    Bitmap bitmap[];
    TextView textView;
    //String pathName = getPath();

    //File dir = new File(pathName);
    //File[] files = dir.listFiles();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        //firstImageView = (ImageView) findViewById(R.id.imageview_first);
        //secondImageView = (ImageView) findViewById(R.id.imageview_second);
        setImageId();
        //firstImageView.setImageResource(this.getResources().getIdentifier(&quot;img&quot;+));
        setButtonID();
//        textView = (TextView) findViewById(R.id.textView);
        //Resources res = getResources();

        /*for (int i = 0; i < 5; i++) {
            int rid = getResources().getIdentifier("sample" + i, "drawable", getApplicationContext().getPackageName());
            bitmap[i] = BitmapFactory.decodeResource(res, rid);
        }*/

        findViewById(R.id.layout_first).setOnTouchListener(this);
        findViewById(R.id.layout_second).setOnTouchListener(this);

    }

    private void setImageId() {
        for (int i = 0; i < 5; i++) {
            int viewId = getResources().getIdentifier("iv" + (i + 1), "id", getPackageName());
            ImageView iv = (ImageView) findViewById(viewId);

            int imageId = getResources().getIdentifier("sample" + (i + 1), "drawable", getPackageName());
            iv.setImageResource(imageId);

        }
    }

    private void setButtonID() {
        btn[0] = (Button) findViewById(R.id.prevButton);
        btn[1] = (Button) findViewById(R.id.nextButton);
        ibtn[0] = (ImageButton) findViewById(R.id.stopButton);
        ibtn[1] = (ImageButton) findViewById(R.id.startButton);

        for (int i = 0; i < btn.length; i++) {
            btn[i].setOnTouchListener(this);
        }

        for (int i = 0; i < ibtn.length; i++) {
            ibtn[i].setOnTouchListener(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //isImageTouched();
        int x = (int) event.getRawX();
        //imageVisibility();
        switch (v.getId()) {
            case R.id.layout_first:
            case R.id.layout_second:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        firstTouch = event.getRawX();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (!isFlip) {
                            if (x - firstTouch > 50) {
                                isFlip = true;
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_left));
                                //viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_out_right));
//                                textView.setText(files.length);
                                viewFlipper.showNext();
                            } else if (firstTouch - x > 50) {
                                isFlip = true;
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_right));
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

    /*private void isImageTouched() {
        if (!isTouched) {
            isTouched = true;
        } else {
            isTouched = false;
        }
    }*/

    /*private void imageVisibility() {
        if (btn[0].getVisibility() != View.VISIBLE) {
            for (int i = 0; i < btn.length; i++) {
                btn[i].setVisibility(View.VISIBLE);
                ibtn[i].setVisibility(View.VISIBLE);
            }
        } else {
            for (int i = 0; i < btn.length; i++) {
                btn[i].setVisibility(View.INVISIBLE);
                ibtn[i].setVisibility(View.INVISIBLE);
            }
        }
    }*/

    /*@Override
    public boolean isTouchButton(View.v, MotionEvent event) {

    }*/
}