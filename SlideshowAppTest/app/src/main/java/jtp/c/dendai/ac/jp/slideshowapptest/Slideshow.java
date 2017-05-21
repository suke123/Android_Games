package jtp.c.dendai.ac.jp.slideshowapptest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by taka on 2017/05/21.
 */

public class Slideshow {
    private int i,j = 0;
    private String[] files;
    private Button previous_button,slideshow_button,next_button;
    private ImageView picture;
    private TextView textView,textView2;
    private Timer timer;
    private SlideTimerTask slideTimerTask;
    private Handler handler;
    private AssetManager assetManager;
    private Activity activity;

    public Slideshow(Activity a){
        activity = a;
        handler = new Handler();
        View.OnClickListener myListener = new MyListener();
        previous_button=(Button) a.findViewById(R.id.button);
        slideshow_button=(Button) a.findViewById(R.id.toggleButton);
        next_button=(Button) a.findViewById(R.id.button2);
        previous_button.setOnClickListener(myListener);
        slideshow_button.setOnClickListener(myListener);
        next_button.setOnClickListener(myListener);
        picture = (ImageView) a.findViewById(R.id.image_view);
        textView = (TextView) a.findViewById(R.id.textView);
        textView2 = (TextView) a.findViewById(R.id.textView2);
    }

    public void Fine_Dir_Path(String dir) {
        assetManager = activity.getResources().getAssets();
        try {
            files = assetManager.list(dir);
        } catch (IOException e) {
            Log.d("Search_path()","Error");
        }
    }

    public void Image() {
        textView.setText((i+1)+" / "+(files.length));
        textView2.setText(files[i]);
        try {
            InputStream istream = activity.getResources().getAssets().open("images/"+files[i]);
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            picture.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.d("Image()","Error");
        }
    }

    public class SlideTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.post( new Runnable() {
                public void run() {
                    next_button.performClick();
                }
            });
        }
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    if (i == 0) {
                        i = files.length - 1;
                        Image();
                    } else {
                        i--;
                        Image();
                    }
                    break;

                case R.id.toggleButton:
                    if (j == 0) {
                        timer = new Timer();
                        slideTimerTask = new SlideTimerTask();
                        timer.schedule(slideTimerTask, 1500, 1500);
                        j++;
                    } else {
                        j--;
                        slideTimerTask.cancel();
                        slideTimerTask = null;
                    }
                    break;

                case R.id.button2:
                    if (i < files.length - 1) {
                        i++;
                        Image();
                    } else {
                        i = 0;
                        Image();
                    }
                    break;
            }
        }
    }
}