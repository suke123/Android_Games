package jtp.c.dendai.ac.jp.slideshowapp_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity implements View.OnTouchListener {

    private ViewFlipper viewFlipper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        setContentView(R.layout.activity_view_flipper);

        viewFlipper.setAutoStart(true);         //自動でスライドショー開始
        viewFlipper.setFlipInterval(1000);      //更新間隔(ms単位)
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.startButton:
                viewFlipper.startFlipping();    //スライドショーを手動で開始する際に必要
                break;

            case R.id.prevButton:
                viewFlipper.showPrevious();     //前の画像を表示する
                break;

            case R.id.nextButton:
                viewFlipper.showNext();         //次の画像を表示する
                break;

            case R.id.stopButton:
                viewFlipper.stopFlipping();     //スライドショーを手動で停止させる際に必要
                break;

            default:
                break;
        }
    }
}
