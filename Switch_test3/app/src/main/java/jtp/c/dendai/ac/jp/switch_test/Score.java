package jtp.c.dendai.ac.jp.switch_test;

import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 * @author kie
 */
public class Score {

    private int irekae = 0;
    private Paint paint = new Paint();

    public Score() {
        paint.setColor(Color.WHITE);

    }

    public int getIre(){
        return irekae;
    }

    public void addIre(){
        irekae += 1;
    }

}
