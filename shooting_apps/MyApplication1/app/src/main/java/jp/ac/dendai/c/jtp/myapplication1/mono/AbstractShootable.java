package jp.ac.dendai.c.jtp.myapplication1.mono;
import android.content.Context;
import jp.ac.dendai.c.jtp.myapplication1.Vect;
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