package jtp.c.dendai.ac.jp.adventure;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Title {
    public void init(AppCompatActivity activity, OnClickListener onStartButtonClickListener, OnClickListener onContinueButtonClickListener) {
       // Button startButton = (Button) activity.findViewById(R.id.startbutton);
       // Button continueButton = (Button) activity.findViewById(R.id.continuebutton);
        ImageButton startButton = (ImageButton)activity.findViewById(R.id.startbutton);
        ImageButton continueButton = (ImageButton)activity.findViewById(R.id.continuebutton);
        startButton.setOnClickListener(onStartButtonClickListener);
        continueButton.setOnClickListener(onContinueButtonClickListener);
    }
    public int getContentView() {
        return R.layout.title;
    }
}