package jtp.c.dendai.ac.jp.myapplication1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Score {
    private int score;
    private Paint paint = new Paint();
    private static final float SCORE_TEXT_SIZE = 30.0f;
    public Score() {
        paint.setColor(Color.WHITE);
    }
    public void decrease(int point) {
        score -= point;
    }
    public void draw(Canvas canvas) {
        String sc = "000000000" + score;
        paint.setTextSize(SCORE_TEXT_SIZE);
        canvas.drawText(sc.substring(sc.length() - 10), 0, paint.getTextSize(), paint);
    }
    public void add(int point) {
        score += point;
    }
}