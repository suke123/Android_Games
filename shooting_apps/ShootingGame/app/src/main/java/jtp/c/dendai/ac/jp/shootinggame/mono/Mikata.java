package jtp.c.dendai.ac.jp.shootinggame.mono;
import android.view.MotionEvent;
public interface Mikata extends Shooter {
    void setDirection(MotionEvent event, int width, int height);
    void setDrag(MotionEvent event, int width, int height);
}