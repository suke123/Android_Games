package jp.ac.dendai.c.jtp.myapplication1.mono;
import android.content.Context;
import jp.ac.dendai.c.jtp.myapplication1.R;
import jp.ac.dendai.c.jtp.myapplication1.Vect;
public class Teki extends AbstractMono {
    private static final int[] ids = {R.drawable.teki1, R.drawable.teki2};
    private int dpindex;
    private Vect[] dps = {new Vect(1, 2), new Vect(-1, -1)};
    private double dpcycle = 900;
    private double dpcounter;
    public Teki(Context context) {
        super(context, ids);
    }
    public Teki(Context context, int x, int y) {
        super(context, ids);
        set(x, y);
        dp.set(dps[0]);
        dpindex = 0;
        dpcounter = 0;
    }
    @Override
    public void move(int width, int height) {
        if (p.getX() > width) p.setX(-this.width);
        else if (p.getX() < -this.width) p.setX(width);
        if (p.getY() > height) p.setY(-this.height);
        else if (p.getY() < -this.height) p.setY(height);
    }
    @Override
    public double getInterval() {
        return 23;
    }
    @Override
    public int getScore() {
        return 100;
    }
    @Override
    public void step(double t, int width, int height) {
        period += t;
        if (dpcounter + t > dpcycle) {
            p.add(dpcycle - dpcounter, dps[dpindex]);
            dpindex = (dpindex + 1) % dps.length;
            dpcounter = dpcounter + t - dpcycle;
            p.add(dpcounter, dps[dpindex]);
        } else {
            p.add(t, dps[dpindex]);
            dpcounter += t;
        }
        move(width, height);
        setRect();
    }
}