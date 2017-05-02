package com.example.shooting_sample.mono;

import android.content.Context;
import android.view.MotionEvent;
import com.example.shooting_sample.Debug;
import com.example.shooting_sample.HanteiList;
import com.example.shooting_sample.R;
import com.example.shooting_sample.Vect;

/**
 * Created by taka on 2017/05/02.
 */

public class Anata extends AbstractShooter implements Mikata {
    private static final int[] ids = {R.drawable.anata};
    private final static double shootperiod = 500;
    private static final Vect tamadp = new Vect(0, -1);
    private double shoottic;
    public Anata(Context context, HanteiList<Shootable> tamalist) {
        super(context, ids, tamalist, new Tama(context));
        shoottic = 0;
    }
    @Override
    public void move(int width, int height) {
        if (p.getX() > width) p.setX(width);
        if (p.getX() < -this.width) p.setX(0);
    }
    @Override
    public void setDirection(MotionEvent event, int width, int height) {
        final double delta = 1;
        float px = event.getX();
        Debug.append("position", "" + width + " " + px);
        if (px < width / 2) {
            dp.setX(-delta);
        } else {
            dp.setX(delta);
        }
    }
    @Override
    public void step(double t, int width, int height) {
        super.step(t, width, height);
        shoottic += t;
        while (shoottic > shootperiod) {
            shoot(tamadp);
            shoottic -= shootperiod;
        }
    }
}
