package jtp.c.dendai.ac.jp.shootinggame;
import android.graphics.Rect;
public class Vect {
    private double x;
    private double y;
    public Vect(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }
    public Vect() {
    }
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void set(Vect v) {
        this.x = v.x;
        this.y = v.y;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public int getIntX() {
        return (int) x;
    }
    public int getIntY() {
        return (int) y;
    }
    public void add(Vect delta) {
        x += delta.x;
        y += delta.y;
    }
    public void add(double t, Vect delta) {
        x += delta.x * t;
        y += delta.y * t;
    }
    public void restrict(double c) {
        x = x < -c ? -c : x > c ? c : x;
        y = y < -c ? -c : y > c ? c : y;
    }
    public void reflectX() {
        x = -x;
    }
    public void reflectY() {
        y = -y;
    }
    public Vect front(Rect r) {
        Vect v = new Vect();
        v.set(this);
        v.setY(y - r.height());
        Debug.append("tamaheight", "" + r.height());
        return v;
    }
}