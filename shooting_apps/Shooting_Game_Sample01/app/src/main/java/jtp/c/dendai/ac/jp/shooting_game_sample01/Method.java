package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/30.
 */

/*
 * 無理やり作った汎用メソッド群
 * なんども使用するようなメソッドをかためて置いて
 * おきたかったので無理やりつくったクラス
 */

import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

public class Method {
	/*
	 * わたくしの環境がXPERIAでその画面幅でアプリを作成
	 * しているのでそれから各アプリの画面幅に合うように調整
	 * させるための変数
	 */

    static public final float XPERIA_W = 480f;
    static public final float XPERIA_H = 854f;
    //せっかくなので０も固定値に
    static public final float ZERO = 0f;
    private static final double PIE = 3.1415926;


    /*
     * Objectクラスから移動してきた当たり範囲表示メソッド
     *
     * オブジェクトすべてに、オブジェクトごとの当たり判定
     * を作成しました。それはゲーム中は見えないもののため
     * わかりやすくするために可視化しています。
     */
    public void OdrawRect(Rect rec,Canvas c){
        Paint p=new Paint();
        p.setColor(Color.RED);
        //c.drawRect(rec, p);
    }
    /*
     * 種類の違うオブジェクト自機、弾、敵機、爆発、アイテム
     * をそれぞれ区別できるように変更したのでちょっと改造
     * 弾と敵機の判定（敵機と弾の順番では判定なし）と自機と
     * アイテムの判定の２つの場合のみの判定になっています。
     */
    public void Atarihantei(ArrayList<Object> object,int i){
        for(int j=0;j<object.size()-1;j++){
            //弾と敵機の当たり判定
            if(i!=j &&
                    object.get(i).obsyurui == 1 &&
                    object.get(j).obsyurui == 2){
                //当たっていればオブジェクトを消す準備＆爆発呼び出し準備
                if(RectRect(object.get(i).atarir, object.get(j).atarir)==true){
                    object.get(i).dead=true;
                    object.get(i).bomsflg=true;
                    object.get(j).dead=true;
                }
            }
            //自機とアイテムの判定
            if(i!=j &&
                    object.get(i).obsyurui == 0 &&
                    object.get(j).obsyurui == 4){
                //当たっていれば自機の弾状態を変化0-2を周回＆アイテム消去準備
                if(RectRect(object.get(i).atarir, object.get(j).atarir)==true){
                    object.get(j).dead = true;
                    object.get(i).tamajoutai = object.get(i).tamajoutai + 1;
                    object.get(i).tamajoutai = (object.get(i).tamajoutai + 3)%3;
                }
            }

        }
    }
    //渡された短形と短形の当たり判定。重なっていればtrue
    public boolean RectRect(Rect oa,Rect ob){
        return oa.left < ob.right && ob.left < oa.right && oa.top < ob.bottom && ob.top < oa.bottom;
    }
    //サウンド再生用
    public void playSound( MediaPlayer mp){
        mp.seekTo(0);
        mp.start();
    }
    /*
     * sin,cosなどを使用するときに入れ込む数値は
     * 3.14を半周とした数値を180で割ったラジアン値
     * というものを使用しなければなりません。
     * （１周3.14×2=6.28を360で割った数値）
     * 角度設定などは度数で出したほうが簡単なので設定は
     * 度数でして、使用するときにここのメソッドでラジアン値
     * に変換しています
     */
    public double toRadian(double deg){return (deg * PIE / 180);}
    /*
     * 受け取ったxy座標と調べたい短形範囲が重なっているかいないか
     */
    public boolean RectTap(int x,int y,Rect gazou){
        return gazou.left < x && gazou.top < y && gazou.right > x && gazou.bottom > y;
    }
    /*
     * この２行で各座標を実装機種の画面比に合わせます
     */
    public int setSizeX(float disp_w,float zahyou){return (int) (zahyou * (disp_w / XPERIA_W));}
    public int setSizeY(float disp_h,float zahyou){return (int) (zahyou * (disp_h / XPERIA_H));}
}


