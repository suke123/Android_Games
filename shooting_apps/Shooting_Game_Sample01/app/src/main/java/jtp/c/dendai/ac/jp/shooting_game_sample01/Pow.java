package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/30.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class Pow extends Object{

    public Pow(){}
    public Pow(float dw,float dh){
        super(dw,dh);
    }
    @Override
    public void ODraw(Canvas c) {
        if(dead == false){
            img.setBounds((int)(cx-imgw/2),(int)(cy-imgh/2),
                    (int)(cx+imgw/2),(int)(cy+imgh/2));
            img.draw(c);
            ms.OdrawRect(atarir,c);
        }
    }

    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,
                     int h, int tj) {
        img = new BitmapDrawable(imgb);
        cx = ms.setSizeX(disp_w, x);
        cy = ms.setSizeY(disp_h, y);
        spx = sx;
        spy = sy;
        imgw = w;
        imgh = h;
        dead = false;
        tamajoutai = tj;
        atarir = new Rect((int)cx-30,(int)cy-30,(int)cx+30,(int)cy+30);
        obsyurui = 4;
    }
    @Override
    public void OMove() {
        ++cy;
        atarir = new Rect((int)cx-30,(int)cy-30,(int)cx+30,(int)cy+30);

        if(OsotoX(-imgw/2)==true) dead = true;
        if(OsotoY(-imgh/2)==true) dead = true;
    }
    @Override
    public void OMove(int x, int y) {}
    @Override
    public Rect OgetTapRect() {return null;}
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,
                     int h) {}
    @Override
    public void Oint(Bitmap[] imgb, float x, float y, float sx, float sy,
                     int w, int h, int tj) {}

}

