package com.example.shooting_sample;

import android.graphics.Rect;
import java.util.ArrayList;
import com.example.shooting_sample.mono.Mono;

/**
 * Created by taka on 2017/05/02.
 */

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
