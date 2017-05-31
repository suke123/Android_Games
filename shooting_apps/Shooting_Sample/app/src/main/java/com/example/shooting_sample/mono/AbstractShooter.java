package com.example.shooting_sample.mono;

import android.content.Context;
import com.example.shooting_sample.HanteiList;
import com.example.shooting_sample.Vect;

/**
 * Created by taka on 2017/05/02.
 */

public abstract class AbstractShooter extends AbstractMono implements Shooter {
    private HanteiList<Shootable> list;
    private Shootable tama;
    public AbstractShooter(Context context, int[] ids, HanteiList<Shootable> list, Shootable tama) {
        super(context, ids);
        this.list = list;
        this.tama = tama;
        tama.setRect();
    }
    @Override
    public void shoot(Vect dp) {
        Shootable newtama = tama.getInstance();
        newtama.init(p.front(tama.getRect()), dp);
        list.add(newtama);
    }
}
