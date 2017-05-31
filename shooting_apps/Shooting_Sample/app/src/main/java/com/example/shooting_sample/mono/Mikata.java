package com.example.shooting_sample.mono;

import android.view.MotionEvent;

/**
 * Created by taka on 2017/05/02.
 */

public interface Mikata extends Shooter {
    void setDirection(MotionEvent event, int width, int height);
}
