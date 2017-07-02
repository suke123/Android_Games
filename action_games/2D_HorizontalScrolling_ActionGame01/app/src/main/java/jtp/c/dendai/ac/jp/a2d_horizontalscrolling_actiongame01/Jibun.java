package jtp.c.dendai.ac.jp.a2d_horizontalscrolling_actiongame01;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by taka on 2017/07/01.
 */

class Jibun {
    private ImageButton left_arrow, right_arrow, up_arrow, down_arrow, a_button, b_button;
    private ImageView imageView;
    Activity activity;
    Bitmap bitmap;

    private int left, right, top, bottom;
    private static final double GRAVITY = 0.6;
    private boolean onGround = true;

    private int counter = 0;

    RelativeLayout layout;

    private int SPEED = 6;

    public Jibun(Activity a) {
        activity = a;

        View.OnLongClickListener myLongClickListener = new MyLongClickListener();
        View.OnClickListener myClickLisner = new MyClickListener();

        setImageButtonID(activity);
        setImageButtonClickListener(myLongClickListener);
        setAB_ButtonClickListener(myClickLisner);
        setImageView(activity);
        setJibunArroundNumber();

        setJibunImage(activity);

        layout = (RelativeLayout) a.findViewById(R.id.layout);
    }

    private void setJibunImage(Activity a) {
        Bitmap jibunBitmap = BitmapFactory.decodeResource(a.getResources(), R.drawable.jibun);
        imageView.setImageBitmap(jibunBitmap);
    }


    private void setJibunArroundNumber() {
        left = imageView.getLeft();
        right = imageView.getRight();
        top = imageView.getTop();
        bottom = imageView.getBottom();
    }

    private void setImageButtonID(Activity a) {
        left_arrow = (ImageButton) a.findViewById(R.id.arrow_left_button);
        right_arrow = (ImageButton) a.findViewById(R.id.arrow_right);
        up_arrow = (ImageButton) a.findViewById(R.id.arrow_up_button);
        down_arrow = (ImageButton) a.findViewById(R.id.arrow_down_button);
        a_button = (ImageButton) a.findViewById(R.id.a_button);
        b_button = (ImageButton) a.findViewById(R.id.b_button);
    }

    private void setImageButtonClickListener(View.OnLongClickListener myListener) {
        left_arrow.setOnLongClickListener(myListener);
        right_arrow.setOnLongClickListener(myListener);
        up_arrow.setOnLongClickListener(myListener);
        down_arrow.setOnLongClickListener(myListener);
    }

    private void setAB_ButtonClickListener(View.OnClickListener myClickLisner) {
        left_arrow.setOnClickListener(myClickLisner);
        right_arrow.setOnClickListener(myClickLisner);
        up_arrow.setOnClickListener(myClickLisner);
        down_arrow.setOnClickListener(myClickLisner);

        a_button.setOnClickListener(myClickLisner);
        b_button.setOnClickListener(myClickLisner);
    }

    private void setImageView(Activity a) {
        imageView = (ImageView) a.findViewById(R.id.jibun_image);
    }

    private class MyLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                //leftボタンの処理
                case R.id.arrow_left_button:
                    //imageView.layout(imageView.getLeft() - 1, top, imageView.getRight() - 1, bottom);
                    imageView.setLeft(imageView.getLeft() - SPEED);
                    break;

                //rightボタンの処理
                case R.id.arrow_right:
                    //imageView.layout(1 + imageView.getLeft(), top, 1 + imageView.getRight(), bottom);
                    imageView.setLeft(imageView.getLeft() + SPEED);
                    break;

                //upボタンの処理
                case R.id.arrow_up_button:
                    break;

                //downボタンの処理
                case R.id.arrow_down_button:

                    break;

                //Aボタンの処理
                case R.id.a_button:
                    //imageView.layout(1 + imageView.getLeft(), top, 1 + imageView.getRight(), bottom);
                    break;

                //Bボタンの処理
                case R.id.b_button:

                    break;
            }
            return true;
        }
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //leftボタンの処理
                case R.id.arrow_left_button:
                    //imageView.layout(imageView.getLeft() - 1, top, imageView.getRight() - 1, bottom);
                    imageView.setLeft(imageView.getLeft() - SPEED);
                    break;

                //rightボタンの処理
                case R.id.arrow_right:
                    //imageView.layout(1 + imageView.getLeft(), top, 1 + imageView.getRight(), bottom);
                    imageView.setLeft(imageView.getLeft() + SPEED);
                    break;

                //upボタンの処理
                case R.id.arrow_up_button:

                    break;

                //downボタンの処理
                case R.id.arrow_down_button:

                    break;

                //Aボタンの処理
                case R.id.a_button:
                    if(onGround){
                        imageView.setTop(imageView.getTop() - 12);
                        onGround = false;
                    }
                    break;

                //Bボタンの処理
                case R.id.b_button:

                    break;
            }
        }
    }
}
