package jtp.c.dendai.ac.jp.shootinggame;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
public class Score {
    private int score;
    private Paint paint = new Paint();
    public Score() {
        paint.setColor(Color.WHITE);

    }
    public void decrease(int point) {
        score -= point;
    }
    public void draw(Canvas canvas) {
        String sc = String.valueOf(score);
        paint.setTextSize(40);
        canvas.drawText(sc, 0, paint.getTextSize(), paint);
    }
    public void add(int point) {
        score += point;
    }
    public int getScore() {
        return score;
    }
}