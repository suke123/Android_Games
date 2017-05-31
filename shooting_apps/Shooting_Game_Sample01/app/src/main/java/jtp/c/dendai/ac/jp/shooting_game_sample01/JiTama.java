package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/30.
 */


/*
 * 弾の種類で後方や横など飛び方が異なる
 * 飛び方もさせたいので、飛ぶ方向を角度
 * で設定。それに合わせて画像の角度も変化
 * させるようにしました
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class JiTama extends Object{
    /*
     * 弾と画像の角度
     */
    public int tamakakudo;

    public JiTama(){}
    public JiTama(float dw,float dh){
        super(dw,dh);
    }
    public void ODraw(Canvas c){
		/*
		 * 画像を回転させるにあたり、rotateのみでやろうとすると
		 * 別の画像も一緒に回転してしまいおかしくなります。
		 * そこでsave()でいったん以前の画像を保存しておき、表示
		 * し終わったらrestore()で開放すると手法で、ちゃんと表示
		 * するようになります
		 *
		 * 画像を表示させていいかどうかdead状態を調べて表示
		 * cx,cyは中心座標のため、画像の中心にちゃんとcx,cyがくるように調整
		 */
        if(dead == false){
            c.save();
            img.setBounds((int)(cx-imgw/2),(int)(cy-imgh/2),
                    (int)(cx+imgw/2),(int)(cy+imgh/2));
            c.rotate(tamar, cx, cy);
            img.draw(c);
            ms.OdrawRect(atarir,c);
            c.restore();
        }
    }
    /*
     * 角度で弾を飛ばすようにしています
     * 画像角度と移動角度は-９０度ほどの誤差があるので
     * 調整
     * 例えば角度０度でここもそのままの角度０度にすると
     * 画像表示は正常だが飛ぶ方向が右方向に・・・
     */
    public void OMove() {
        cx += (float) Math.cos(ms.toRadian(tamar-90)) * spx;
        cy += (float) Math.sin(ms.toRadian(tamar-90)) * spy;
        //当たり判定更新
        atarir = new Rect((int)cx-10,(int)cy-10,(int)cx+10,(int)cy+10);
		/*
		 * 範囲外にでたら弾消してくださいの合図
		 */
        if(OsotoX(-imgw/2)==true) dead = true;
        if(OsotoY(-imgh/2)==true) dead = true;
    }

    public void OMove(int x, int y) {}
    public Rect OgetTapRect() {return null;}
    //初期設定
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,int h, int r) {
        img = new BitmapDrawable(imgb);
        cx = ms.setSizeX(disp_w, x);
        cy = ms.setSizeY(disp_h, y);
        spx = sx;
        spy = sy;
        imgw = w;
        imgh = h;
        dead = false;
        tamar = r;
        atarir = new Rect((int)cx-10,(int)cy-10,(int)cx+10,(int)cy+10);
        obsyurui = 0;
    }
    @Override
    public void Oint(Bitmap imgb, float x, float y, float sx, float sy, int w,int h) {}
    @Override
    public void Oint(Bitmap[] imgb, float x, float y, float sx, float sy,int w, int h, int tj) {}

}


