package jtp.c.dendai.ac.jp.shootinggame;
import android.graphics.Canvas;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jtp.c.dendai.ac.jp.shootinggame.mono.Mono;
public class DrawList extends ArrayList<Mono> {
    private static final long serialVersionUID = 6330699380650402372L;
    private Score score;
    private List<HanteiList<? extends Mono>> list;
    public DrawList() {
        super();
        list = new ArrayList<>();
    }
    public void addScore(Score s) {
        score = s;
    }
    public void addList(HanteiList<? extends Mono> list) {
        this.list.add(list);
    }
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        score.draw(canvas);
        for (Mono m : this) {
            m.draw(canvas);
        }
        for (HanteiList<? extends Mono> l : list) {
            for (Mono m : l) {
                m.draw(canvas);
            }
        }
        Debug.draw(canvas);
    }
    public void step(double t, int width, int height) {
        for (Mono m : this) {
            m.step(t, width, height);
        }
        for (HanteiList<? extends Mono> l : list) {
            for (Mono m : l) {
                m.step(t, width, height);
            }
        }
    }
    public void stop() {
        for (Mono m : this) {
            m.stop();
        }
        for (HanteiList<? extends Mono> l : list) {
            for (Mono m : l) {
                m.stop();
            }
        }
    }
    public void update() {
        Iterator<Mono> i = this.iterator();
        while (i.hasNext()) {
            if (!i.next().isAlive()) {
                i.remove();
            }
        }
        Iterator<HanteiList<? extends Mono>> j = list.iterator();
        while (j.hasNext()) {
            Iterator<? extends Mono> k = j.next().iterator();
            while (k.hasNext()) {
                if (!k.next().isAlive()) {
                    k.remove();
                }
            }
        }
    }
}