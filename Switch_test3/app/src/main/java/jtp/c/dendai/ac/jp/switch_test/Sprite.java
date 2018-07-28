package jtp.c.dendai.ac.jp.switch_test;

public abstract class Sprite {

    // 位置
    protected double x;
    protected double y;
    protected int id;

    // 幅
    protected int width;
    // 高さ
    protected int height;

    // アニメーション用カウンタ
    protected int count;

    public Sprite(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;

        width = 111;
        height = 111;

        count = 0;
    }

    /**
     * スプライトの状態を更新する
     */
    public abstract void update();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
