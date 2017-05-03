package com.example.shooting_sample;

import android.content.Context;
import com.example.shooting_sample.mono.Mono;
import com.example.shooting_sample.mono.Teki;

/**
 * Created by taka on 2017/05/02.
 */

public class TekiLogic {
    private static double period = 100; //敵の出現時間
    private final Context context;
    private final HanteiList<Mono> list;
    private double tic;
    public TekiLogic(Context context, HanteiList<Mono> list) {
        this.context = context;
        this.list = list;
        tic = 0;
        list.add(createTeki());
    }
    private Mono createTeki() {
        return new Teki(context, 0, 0);
    }
    public void step(double tstep, int width, int height) {
        tic += tstep;
        while (tic > period) {
            list.add(createTeki());
            tic -= period;
        }
    }
}
