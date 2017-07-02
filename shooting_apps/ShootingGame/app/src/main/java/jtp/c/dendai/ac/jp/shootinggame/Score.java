package jtp.c.dendai.ac.jp.shootinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Score {
    private int score;
    private Paint paint = new Paint();
    private int highscore;

    public Score() {
        paint.setColor(Color.WHITE);
    }

    public void decrease(int point) {
        score -= point;
    }

    public void draw(Canvas canvas) {
        String sc = String.valueOf(score);
        //String highscoreText = String.valueOf(highscore);
        paint.setTextSize(30);
        //canvas.drawText("TOP：" + highscoreText, 0, paint.getTextSize(), paint);
        canvas.drawText(sc, 10, paint.getTextSize(), paint);
    }

    public void add(int point) {
        score += point;
        if (score > highscore) {
            highscore = score;
        }
    }

    public int getScore() {
        return score;
    }

    public int calcHighscore(int highscore) {
        this.highscore = highscore;
        if (score > highscore) {
            this.highscore = score;
        }
        return this.highscore;
    }
}