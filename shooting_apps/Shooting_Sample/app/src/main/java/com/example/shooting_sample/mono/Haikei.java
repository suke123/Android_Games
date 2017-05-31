package com.example.shooting_sample.mono;

import com.example.shooting_sample.R;
import com.example.shooting_sample.Vect;
import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by taka on 2017/05/02.
 */

public class Haikei extends AbstractMono {
    private static final int[] ids = {R.drawable.haikei};
    public Haikei(Context context){
        super(context,ids);
        p.set(0,20);
    }
    @Override
    public void move(int width, int height) {
    }
}
