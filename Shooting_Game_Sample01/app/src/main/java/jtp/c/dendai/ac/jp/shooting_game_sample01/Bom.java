package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/30.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class Bom extends Object{

    public Bom(){}
    public Bom(float dw,float dh){
        super(dw,dh);
    }
    @Override
    public void ODraw(Canvas c) {
        boms[bomindex].setBounds((int)(cx-40),(int)(cy-40),
                (int)(cx+40),(int)(cy+40));
        boms[bomindex].draw(c);
        ++bomindex;
        if(bomindex>3)dead=true;
    }

    @Override
    public void Oint(Bitmap[] imgb, float x, float y, float sx, float sy,
                     int w, int h, int tj) {
        for(int i=0;i<4;i++){
            boms[i] = new BitmapDrawable(imgb[i]);
        }
        cx = ms.setSizeX(disp_w, x);
        cy = ms.setSizeY(disp_h, y);
        spx = sx;
        spy = sy;
        imgw = w;
        imgh = h;
        dead = false;
        //爆発の初期状態を受け取ります
        tamajoutai = tj;
        atarir = new Rect((int)cx-30,(int)cy-30,(int)cx+30,(int)cy+30);
        obsyurui = 1;
    }
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,int h, int tj) {}
    public void OMove() {}
    public void OMove(int x, int y) {}
    public Rect OgetTapRect() {return null;}
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,int h) {}
}


