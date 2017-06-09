package jtp.c.dendai.ac.jp.shooting_sample02;

import android.graphics.Canvas;

/**
 * Created by taka on 2017/06/01.
 */

public abstract class BaseObject {
    //状態を示す定数
    static final int STATE_NORMAL = -1;
    static final int STATE_DESTROYED = 0;

    int state = STATE_NORMAL;

    //タイプを示す列挙型
    enum Type {
        MyFighter,
        MyBullet,
        Missie,
    }

    public abstract Type getType();

    float xPosition;
    float yPosition;

    public abstract void draw(Canvas canvas);

    public boolean isAvailable(int width, int height) {
        if (yPosition < 0 || xPosition < 0 || yPosition > height || xPosition > width) {
            return false;
        }
        if (state == STATE_DESTROYED) {
            return false;
        }

        return true;
    }

    public abstract void move();

    //引数のオブジェクトと衝突したかどうかを判定
    public abstract boolean isHit(BaseObject object);

    //Hitメソッドが呼ばれると、状態をDESTROYEDにする
    public void hit() {
        state = STATE_DESTROYED;
    }

    //２つのオブジェクトの距離を計算する
    static double calcDistance(BaseObject obj1, BaseObject obj2) {
        float distX = obj1.xPosition - obj2.xPosition;
        float distY = obj1.yPosition - obj2.yPosition;
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }
}
