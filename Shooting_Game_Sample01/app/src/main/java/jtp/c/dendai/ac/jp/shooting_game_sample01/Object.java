package jtp.c.dendai.ac.jp.shooting_game_sample01;


/*
 * ほとんどのメソッドをabstractにしました
 * これによって自機クラス弾クラスと違うオブジェクト
 * によってメソッド名は同じでそれぞれ違う固有の命令
 * を書くことができます
 *
 * このクラスは画面に表示させるオブジェクト
 * を作成させるためのクラスです
 * このクラス単体ではインスタンス（オブジェクト化、実体化）
 * はできず、このクラスをextendsさせて使うようにしています
 * 理由としてはこのゲームを作成していくうちにわかってきますが
 * コード表記がラクチンになります
 */

import android.graphics.Rect;
import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class Object {
    public Method ms = new Method();
    public float disp_w,disp_h;
    //オブジェクトの画像
    public Drawable img;
    //オブジェクトの中心座標
    public float cx,cy;
    //向かおうとしている座標
    public float vx,vy;
    //オブジェクトの移動スピード
    public float spx,spy;
    //オブジェクトの大きさ
    public int imgw,imgh;
    //オブジェクトを消去させるための変数
    public boolean dead;
    //弾の状態
    public int tamajoutai;
    //弾画像の角度
    public int tamar;
    //オブジェクト当たり範囲
    public Rect atarir;
    //オブジェクトの種類
    public int obsyurui;
    //爆発用
    public Drawable[] boms = new Drawable[4];
    public boolean bomsflg=false;
    public int bomindex=0;

    /*
     * 敵の出現時間
     * 敵の移動カウント（どれだけ移動するか時間的に）
     * 敵の移動場所
     */
    public int teki_pop_count;
    public ArrayList<Integer> teki_move_s = new ArrayList();
    public ArrayList<Integer> teki_move_r = new ArrayList();
    public ArrayList<Integer> teki_move_c = new ArrayList();
    public int teki_move_count;
    public int move_count,mcx,mcy;

    public Object(){}
    public Object(float dw,float dh){
        disp_w = dw;
        disp_h = dh;
    }
    /*
     * このクラスに各オブジェクトに必要なメソッドを書いても
     * いいのですが、どのオブジェクトにどのメソッドを使用して
     * いるかなどの管理も大変なので、こういうメソッドを持って
     * いますよ的なことだけかいておきます（abstract）
     * 作ろうとしている弾クラスには、タップ座標はいらないので
     * メソッドのオーバーライドで登録
     */
    //画像表示
    public abstract void ODraw(Canvas c);
    //初期設定
    public abstract void Oint(Bitmap imgb,float x,float y, float sx,float sy,int w,int h);
    public abstract void Oint(Bitmap[] imgb,float x,float y, float sx,float sy,int w,int h,int tj);
    public abstract void Oint(Bitmap imgb,float x,float y, float sx,float sy,int w,int h,int tj);
    public abstract void Oint(Bitmap imgb,float x,float y, float sx,float sy,int w,int h,int tj,
                              int tpc,ArrayList tmc, ArrayList tmx,ArrayList tmy);
    public abstract void OMove();
    public abstract void OMove(int x,int y);
    public abstract Rect OgetTapRect();
    /*
     * オブジェクトによって調べたい範囲が違うので引数で指定して調べるように変更
     */
    public boolean OsotoX(int ww){return (cx-ww<0 || cx+ww>disp_w);}
    public boolean OsotoY(int hh){return (cy-hh<0 || cy+hh>disp_h);}
    public boolean Ogetdead(){return dead;}//表示していいかどうかを返す

}
