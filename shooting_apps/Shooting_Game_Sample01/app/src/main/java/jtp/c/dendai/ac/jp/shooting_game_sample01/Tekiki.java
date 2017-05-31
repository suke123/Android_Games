package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/30.
 */


import android.graphics.Canvas;
import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class Tekiki extends Object{

    public Tekiki(){}
    public Tekiki(float dw,float dh){
        super(dw,dh);
    }

    @Override
    public void ODraw(Canvas c) {
		/*
		 * 画像を表示させていいかどうかdead状態を調べて表示
		 * cx,cyは中心座標のため、画像の中心にちゃんとcx,cyがくるように調整
		 */
        if(dead == false){
            img.setBounds((int)(cx-imgw/2),(int)(cy-imgh/2),
                    (int)(cx+imgw/2),(int)(cy+imgh/2));
            img.draw(c);
            ms.OdrawRect(atarir,c);


        }
    }

    /*
     * 敵の動きは角度、スピード、時間で決まっているので、
     * 指定されてた回数分の要素分移動を繰り返します
     * メモリがもったいないのでArrayListでなくてもいいのですが
     * テストなのでこのままで・・・
     */
    @Override
    public void OMove() {
        if(move_count != teki_move_s.size()){
            double tx = Math.cos(ms.toRadian(teki_move_r.get(move_count)-90)) * teki_move_s.get(move_count);
            double ty = Math.sin(ms.toRadian(teki_move_r.get(move_count)-90)) * teki_move_s.get(move_count);
            cx += tx;
            cy += ty;
            atarir = new Rect((int)cx-30,(int)cy-30,(int)cx+30,(int)cy+30);
            if(teki_pop_count == teki_move_c.get(move_count)) ++move_count;
        }
        ++teki_pop_count;
    }

    /*
     * 敵機クラス用初期化メソッド
     */
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,
                     int h, int tj, int tpc, ArrayList tms, ArrayList tmr, ArrayList tmc) {
        img = new BitmapDrawable(imgb);

        teki_pop_count = 0;
        teki_move_s = tms;
        teki_move_r = tmr;
        teki_move_c = tmc;
        move_count = 0;

        cx = ms.setSizeX(disp_w, x);
        cy = ms.setSizeY(disp_h, y);

        spx = sx;
        spy = sy;
        imgw = w;
        imgh = h;
        dead = false;
        tamajoutai = tj;
        atarir = new Rect((int)cx-30,(int)cy-30,(int)cx+30,(int)cy+30);
        obsyurui = 2;

    }
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,
                     int h, int tj) {}

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


