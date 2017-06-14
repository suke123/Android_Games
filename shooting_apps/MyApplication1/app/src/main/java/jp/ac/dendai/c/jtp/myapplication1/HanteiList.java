package jp.ac.dendai.c.jtp.myapplication1;
import android.graphics.Rect;
import java.util.ArrayList;
import jp.ac.dendai.c.jtp.myapplication1.mono.Mono;
public class HanteiList<E extends Mono> extends ArrayList<E> {
    private static final long serialVersionUID = -3775499867397182898L;
    public HanteiList() {
        super();
    }
    public E atari(Rect r) {
        for (E m : this) {
            if (r.intersect(m.getRect())) return m;
        }
        return null;
    }
}