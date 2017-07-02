package jtp.c.dendai.ac.jp.shootinggame.mono;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import jtp.c.dendai.ac.jp.shootinggame.Vect;
public abstract class AbstractMono implements Mono {
    protected final int width;
    protected final int height;
    protected Vect p;
    protected Vect dp;
    protected double period;
    protected Rect rect;
    protected boolean alive;
    private int cycle;
    private int clock;
    private Bitmap[] gazou;
    //  public int zanki;


    public AbstractMono(Context context, int[] ids) {
        p = new Vect();
        dp = new Vect();
        rect = new Rect();
        cycle = ids.length;
        gazou = new Bitmap[cycle];
        for (int i = 0; i < cycle; i++) {
            gazou[i] = BitmapFactory.decodeResource(context.getResources(), ids[i]);
        }
        width = gazou[0].getWidth();
        height = gazou[0].getHeight();
        clock = 0;
        period = 0;
        alive = true;
    }
    @Override
    public void set(int i, int j) {
        p.set(i, j);
    }
    @Override
    public int getScore() {
        return 0;
    }
    @Override
    public void draw(Canvas canvas) {
        int delta = (int) (period / getInterval());
        clock = (clock + delta) % cycle;
        period -= delta * getInterval();
        canvas.drawBitmap(gazou[clock], p.getIntX(), p.getIntY(), null);
    }
    @Override
    public double getInterval() {
        return 1;
    }
    @Override
    public void step(double t, int width, int height) {
        period += t;
        p.add(t, dp);
        move(width, height);
        setRect();
    }
    @Override
    public void stop() {
        dp.set(0, 0);
    }


  /*  @Override
    public int houkou(float w,float x,int y,int z) {//px py width height
        if((w < y / 2)&&(x < z / 2))
            return 0;
        else if((w < y / 2)&&(x > z / 2))
            return 1;
        else if((w > y / 2)&&(x < z / 2))
            return 2;
        else
            return 3;
    }
    */

    @Override
    public int houkou(float w,float x,int y,int z) {//px py width height
        float c = 3/2;

        float a = c*z/y;

        float b = -1*c*z/y;

        if((x < a*w)&&(x < b*w+z))//down
            return 0;
        else if((x >= a*w)&&(x >= b*w+z))//up
            return 1;
        else if((x >= a*w)&&(x < b*w+z))//left
            return 2;
        else//right
            return 3;

    }

    @Override
    public int rand(int x,boolean u){
        double b;
        double db=1.0;
        if(u){
            db = Math.random();
            if(db<0.5)
                db = 1;
            else
                db = -1;
        }
        int z=0;
        b = Math.random();
        b = (b+1)*x*db;
        z = (int)b ;
        return z;
    }


    @Override
    public void setRect() {
        rect.set(p.getIntX(),
                p.getIntY(),
                p.getIntX() + width,
                p.getIntY() + height);
    }
    @Override
    public Rect getRect() {
        return rect;
    }
    @Override
    public boolean intersect(Mono m) {
        return rect.intersect(m.getRect());
    }
    @Override
    public boolean isAlive() {
        return alive;
    }


    @Override
    public void dead() {
        alive = false;
    }
}