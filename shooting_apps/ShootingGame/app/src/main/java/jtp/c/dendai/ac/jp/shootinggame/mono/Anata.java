package jtp.c.dendai.ac.jp.shootinggame.mono;
import android.content.Context;
import android.view.MotionEvent;
import jtp.c.dendai.ac.jp.shootinggame.Debug;
import jtp.c.dendai.ac.jp.shootinggame.HanteiList;
import jtp.c.dendai.ac.jp.shootinggame.R;
import jtp.c.dendai.ac.jp.shootinggame.Vect;
public class Anata extends AbstractShooter implements Mikata {
    private static final int[] ids = {R.drawable.jiki};
    private final static double shootperiod = 100;
    private static final Vect tamadp = new Vect(0, -5);
    private double shoottic;
    private static int zanki = 3;

    public Anata(Context context, HanteiList<Shootable> tamalist) {
        super(context, ids, tamalist, new Tama(context));
        shoottic = 0;
    }
    @Override
    public void move(int width, int height) {
        if (p.getX() > width) p.setX(width);
        if (p.getX() < -this.width) p.setX(0);
    }

 //   @Override
    public static void Zanki() {
        if(zanki>0)
            zanki--;
    }

    public static void Rlive() {
            zanki= 3;
    }

    public static int getz() {
        return zanki;
    }



    @Override
    public void setDirection(MotionEvent event, int width, int height) {
        final double delta = 2;
        float px = event.getX();
        float py = event.getY();
        Debug.append("position", "" + width + " " + px);

        int g ;
        g = houkou(px,py,width ,height);

      //  int ff = Zanki();


        switch (g){
            case 0:
                dp.setX(0);
                dp.setY(-delta);
                break;
            case 1:
                dp.setX(0);
                dp.setY(delta);
                break;
            case 2:
                dp.setX(-delta);
                dp.setY(0);
                break;
            case 3:
                dp.setX(delta);
                dp.setY(0);
                break;
        }

/*
        switch (g){
            case 0:
                dp.setX(-delta);
                dp.setY(-delta);
                break;
            case 1:
                dp.setX(-delta);
                dp.setY(delta);
                break;
            case 2:
                dp.setX(delta);
                dp.setY(-delta);
                break;
            case 3:
                dp.setX(delta);
                dp.setY(delta);
                break;
        }
        */

    /*    if (px < width / 2) {
            dp.setX(-delta);
            dp.setY(-delta);
        } else {
            dp.setX(delta);
            dp.setY(delta);
        }
        */
    }

    @Override
    public void setDrag(MotionEvent event, int width, int height){
        float px = event.getX();
        float py = event.getY();
        final double delta = 2;
        //    int dx = imageView.getLeft() + (newDx - preDx);
        //    int dy = imageView.getTop() + (newDy - preDy);
        Debug.append("dragand", "" + width + " " + px);

        dp.set(delta,delta);

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