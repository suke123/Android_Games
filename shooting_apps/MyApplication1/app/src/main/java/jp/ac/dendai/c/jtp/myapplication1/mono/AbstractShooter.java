package jp.ac.dendai.c.jtp.myapplication1.mono;
import android.content.Context;
import jp.ac.dendai.c.jtp.myapplication1.HanteiList;
import jp.ac.dendai.c.jtp.myapplication1.Vect;
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