package com.example.shooting_sample.mono;

import android.content.Context;
import com.example.shooting_sample.Vect;

/**
 * Created by taka on 2017/05/02.
 */

public abstract class AbstractShootable extends AbstractMono implements Shootable {
    public AbstractShootable(Context context, int[] ids) {
        super(context, ids);
    }
    @Override
    public void init(Vect p, Vect dp) {
        this.p.set(p.getX(), p.getY());
        this.dp.set(dp.getX(), dp.getY());
        setRect();
    }
}
