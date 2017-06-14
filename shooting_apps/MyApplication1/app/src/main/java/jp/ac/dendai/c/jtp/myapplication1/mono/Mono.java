package jp.ac.dendai.c.jtp.myapplication1.mono;
import android.graphics.Canvas;
import android.graphics.Rect;
public interface Mono {
    void set(int i, int j);
    int getScore();
    void draw(Canvas canvas);
    void stop();
    void move(int width, int height);
    void step(double t, int width, int height);
    Rect getRect();
    boolean intersect(Mono m);
    int houkou(float w, float x, int y, int z);
    boolean isAlive();
    void dead();
    double getInterval();
    void setRect();
}