package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/30.
 */


/*
 * 自機クラス
 * Objectクラスをextendsしています
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class Jiki extends Object{

    public Jiki(){}
    public Jiki(float dw,float dh){
        super(dw,dh);
    }
    //初期設定
    public void Oint(Bitmap imgb,float x,float y, float sx,float sy,int w,int h,int tj){
        img = new BitmapDrawable(imgb);
        cx = ms.setSizeX(disp_w, x);
        cy = ms.setSizeY(disp_h, y);
        spx = sx;
        spy = sy;
        imgw = w;
        imgh = h;
        dead = false;
        //弾の初期状態を受け取ります
        tamajoutai = tj;
        atarir = new Rect((int)cx-30,(int)cy-30,(int)cx+30,(int)cy+30);
        obsyurui = 0;
    }
    public void ODraw(Canvas c){
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
     * 今回はここは使用していません
     */
    public void OMove(int x, int y) {
        float cxx = cx;
        float cyy = cy;
        cx = x;
        cy = y;
        //当たり判定更新
        atarir = new Rect((int)cx-30,(int)cy-30,(int)cx+30,(int)cy+30);

        if(OsotoX(imgw/2)==true) cx = cxx;
        if(OsotoY(imgh/2)==true) cy = cyy;
    }
    public void OMove() {}

    /*
     * タップ範囲にオブジェクトがあると移動できるようにしてます。。
     * しかしタップして動かしてみると、指の動きにオブジェクトが
     * ついてこれない場合があります。そのためオブジェクトの大きさを
     * タップ時のみ大きくしてある程度オブジェクトから離れても
     * ついてこれるようにごまかしています。
     */
    public Rect OgetTapRect(){
        Rect taprect = new Rect(
                img.getBounds().left-50,img.getBounds().top-50,
                img.getBounds().right+50,img.getBounds().bottom+50);
        return taprect;
    }
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,int h) {}
    @Override
    public void Oint(Bitmap[] imgb, float x, float y, float sx, float sy,
                     int w, int h, int tj) {}
}

