package jtp.c.dendai.ac.jp.myapplication1.mono;

import android.content.Context;

import jtp.c.dendai.ac.jp.myapplication1.R;
public class Haikei extends AbstractMono {
    private static final int[] ids = {R.drawable.saka1};
    public Haikei(Context context){
        super(context,ids);
        p.set(0,12);
    }
    @Override
    public void move(int width, int height) {
    }
}