package com.example.shooting_sample.mono;

import android.content.Context;
import com.example.shooting_sample.R;

/**
 * Created by taka on 2017/05/02.
 */

public class Tama extends AbstractShootable {
    private static final int[] ids = {R.drawable.tama1, R.drawable.tama2};
    private Context context;
    public Tama(Context context) {
        super(context, ids);
        this.context = context;
        dp.set(0, -1);
    }
    @Override
    public void move(int width, int height) {
        if (p.getX() > width) alive = false;
        else if (p.getX() < -this.width) alive = false;
        if (p.getY() > height) alive = false;
        else if (p.getY() < -this.height) alive = false;
    }
    @Override
    public Shootable getInstance() {
        return new Tama(context);
    }
    @Override
    public double getInterval() {
        return 10; //弾の画像を入れ替えるインターバル？
    }
}
