package jtp.c.dendai.ac.jp.shootinggame.mono;
import android.content.Context;

import jtp.c.dendai.ac.jp.shootinggame.R;
public class Haikei extends AbstractMono {
    private static final int[] ids = {R.drawable.haikei2};
    public Haikei(Context context){
        super(context,ids);
        p.set(0,50);
    }
    @Override
    public void move(int width, int height) {
    }
}