package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/31.
 */

/*
 * SurfaceViewをextendsさせたクラス
 * メインループなどはここにあります
 * 基本的にSurfaceViewを使用した時には決まった変数の使用
 * などがあるので、そういう変数やメソッドの末尾に
 * ”お決まり”と書いておきます
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainLoop extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;//お決まり
    private Thread thread;//お決まり

    //どのActivityを使用しているかのための変数
    private MainActivity s2a;
    private Method ms;
    private float disp_w, disp_h;
    private Bitmap jikibit, tamabit;
    //弾用、連続で重ならないようにの変数
    private boolean tamaflg;
    private static final int TAMATIMES = 2;
    private int tamatime;
    //音源用３種&爆発音
    private MediaPlayer jitama1s, jitama2s, jitama3s, boms;

    //敵機画像用
    private Bitmap tekibit;

    //爆発画像用
    private Bitmap[] bombit = new Bitmap[4];
    private boolean bomflg = false;

    //写真撮影用ポーズボタン
    private Rect pose;
    private boolean poseflg;

    //背景画像用
    //背景をいくつで分割するか
    private static final int HAIKEI = 10;
    //背景スクロールスピード
    private static final int BUCKSCROOLSP = 1;
    //背景分割画像用
    private ArrayList<Drawable> buck = new ArrayList();
    //背景分割画像座標用
    private ArrayList<Integer> by = new ArrayList();

    //パワーアップアイテム用
    private Bitmap powbit;

    //タップ状態用
    private int tx, ty;
    private boolean tapstate;

    //敵動き用
    private static final int TEKIKAZU = 10;
    private int[] teki_pop_count = new int[TEKIKAZU];
    //一時的に動きを入れておくクラスを登録
    private Teki_POP[] tpop = new Teki_POP[TEKIKAZU];
    private ArrayList<Integer> tpr = new ArrayList();
    private ArrayList<Integer> tpc = new ArrayList();
    private ArrayList<Integer> tps = new ArrayList();
    private int teki_pop_kazu;

    //ゲーム自体の時間
    private int count;

    /*
     * 画面に表示させるオブジェクトは自機と弾、敵機、爆発です
     * 全然違うオブジェクトですがObjectクラスをextends
     * することで同じような変数として扱うことができます。
     * 自機は一つのオブジェクトだけですが、弾はたくさん
     * 表示します。しかし数は決まっていません。なので
     * ArrayListを使用することにしました。
     * 普通の配列変数では、いくつ配列を使用するかを決めないと
     * いけませんが、ArrayListの場合は使用したいときに配列的な
     * ものをいくつでも作成することができます。
     */
    private ArrayList<Object> object = new ArrayList();

    //コンストラクタが二つあるけど気にしないように
    //こちらのコンストラクタは、自前でViewを実装するときに
    //呼ばれるコンストラクタっぽい
    public MainLoop(Context context) {
        super(context);
        init(context);
    }

    //こちらはxml方式でViewを呼び出すときに呼ばれるぽい
    public MainLoop(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        holder = getHolder();//お決まり
        holder.addCallback(this);//お決まり
        holder.setFixedSize(getWidth(), getHeight());//お決まり
        /*
		 * s2a = (Shooting2Activity)context;
		 * よくわからないけども、私的には使用している（今現在
		 * 画面に表示させている）アプリの何か色々なものをcontext
		 * として受け取り、それがいまどのActivityのやつかとか
		 * そんな感じの雰囲気と思います。（キニシテナイ）
		 */
        s2a = (MainActivity) context;
        ms = new Method();
        disp_w = s2a.disp_w;
        disp_h = s2a.disp_h;

        Resources resources = context.getResources();//画像登録準備
        //ビットマップ方式で画像取り込み
        //ビットマップで取り込む理由として、使用したい大きさなどに変換できるので
        Bitmap img = BitmapFactory.decodeResource(resources, jtp.c.dendai.ac.jp.shooting_game_sample01.R.drawable.jiki);
        //ここで画像分割
        //わざわざ画像の大きさをgetWidthgetHeightを使用するのは
        //確実に大きさを図って分割するため
        jikibit = Bitmap.createBitmap(img, 0, 0, img.getWidth() / 4, img.getHeight());
        tamabit = Bitmap.createBitmap(img, img.getWidth() / 4, 0, img.getWidth() / 4, img.getHeight());

		/*
		 * Onjectクラスではインスタンス（実装）できないので
		 * ObjectクラスをextendsさせたJikiクラスを実装
		 * ArrayListを使用しているため、addでインスタンスしています
		 * メソッドなどを使用する場合はget(インデックス).でメソッドなど
		 * 色々呼び出したりします。
		 * 今回はArrayListの０番目の要素に自機が入っています
		 */
        object.add(new Jiki(disp_w, disp_h));
        object.get(0).Oint(jikibit, 240, 800, 0, 0, jikibit.getWidth(), jikibit.getHeight(), 0);

        tamaflg = true;
        tamatime = 1;


        //４種類の音源を取り込み使用準備
        jitama1s = MediaPlayer.create(context, R.raw.jitama1);
        jitama2s = MediaPlayer.create(context, R.raw.jitama2);
        jitama3s = MediaPlayer.create(context, R.raw.jitama3);
        boms = MediaPlayer.create(context, R.raw.bom);
        try {
            jitama1s.prepare();
            jitama2s.prepare();
            jitama3s.prepare();
            boms.prepare();
        } catch (Exception e) {
        }


        //爆発画像用意
        img = BitmapFactory.decodeResource(resources, jtp.c.dendai.ac.jp.shooting_game_sample01.R.drawable.bom);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                bombit[j + i * 2] = Bitmap.createBitmap(
                        img,
                        j * (img.getWidth() / 2), i * (img.getHeight() / 2),
                        img.getWidth() / 2, img.getHeight() / 2);
            }
        }

        //ポースボタン
        pose = new Rect(0, 0, (int) disp_w, 50);
        poseflg = false;

        //背景画像登録
        img = BitmapFactory.decodeResource(resources, jtp.c.dendai.ac.jp.shooting_game_sample01.R.drawable.buck);
        for (int i = 0; i < HAIKEI; i++) {
            Bitmap buckbit = Bitmap.createBitmap(img,
                    0, i * (img.getHeight() / HAIKEI), img.getWidth(), (img.getHeight() / HAIKEI));
            buck.add(new BitmapDrawable(buckbit));
            by.add(i * (int) (disp_h / (HAIKEI - 1)) - (int) (disp_h / (HAIKEI - 1)));
            buck.get(i).setBounds(0, by.get(i), (int) disp_w, (int) (by.get(i) + (disp_h / HAIKEI - 1)));
        }


        //パワーアップアイテム画像用意＆登録
        img = BitmapFactory.decodeResource(resources, jtp.c.dendai.ac.jp.shooting_game_sample01.R.drawable.jiki);
        powbit = Bitmap.createBitmap(img, img.getWidth() / 4 * 3, 0, img.getWidth() / 4, img.getHeight());

        tapstate = false;

        //敵機画像登録＆作成
        tekibit = Bitmap.createBitmap(img, img.getWidth() / 4 * 2, 0, img.getWidth() / 4, img.getHeight());

		/*
		 * 敵機作成
		 * 予定ではここでファイル読み込み
		 * 今回はテストなのでここで同じ動きをする敵を
		 * TEKIKAZU分だけ時間をずらして出現させるように
		 * しています
		 */
        tpr.add(180);
        tpr.add(45);
        tpr.add(270 + 45);
        tpc.add(15);
        tpc.add(50);
        tpc.add(80);
        tps.add(30);
        tps.add(10);
        tps.add(20);
        teki_pop_kazu = 0;
        for (int i = 0; i < TEKIKAZU; i++) {
            teki_pop_count[i] = i * 5;
            tpop[i] = new Teki_POP(teki_pop_count[i], tps, tpr, tpc);
        }

        //ゲーム内時間リセット
        count = 0;
    }

    //implements Runnableを実装するとこのメソッドが自動追加
    //ここがメインループとなります
    public void run() {//お決まり
        Canvas c;
        Paint p = new Paint();
        p.setAntiAlias(true);

        while (thread != null) {
            c = holder.lockCanvas();//お決まり

            //ポース用
            if (poseflg == false) {
                c.drawColor(Color.BLACK);

                Haikei(c);
                //タップ中なら弾発射
                if (tapstate == true) Tamahassya();
			/*
			 * 自機も弾も同じObjectの要素を持っているので
			 * インスタンス（実装）時に作成したいクラスを
			 * 指定しておけば、同じObjectクラスとして使用
			 * することができます。わざわざ各オブジェクトの
			 * メソッドを呼ぶのではなく、共通しているObjectの
			 * メソッドを呼ぶことで解決しています
			 */
                for (int i = 0; i < object.size(); i++) {
                    object.get(i).ODraw(c);
                    object.get(i).OMove();
                    ms.Atarihantei(object, i);
                    BomDraw(i);
                    PowDraw(i);
                    if (object.get(i).Ogetdead() == true) object.remove(i);
                }
                TamaTime();
                TekiSyutugen();
                //ゲーム内時間はずっと＋１にしていきます
                ++count;
            }
            holder.unlockCanvasAndPost(c);//お決まり

            try {
                Thread.sleep(50);//お決まり
            } catch (Exception e) {
            }
        }
    }


    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        tx = (int) event.getX();
        ty = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //タップ中ですよフラグオン
                tapstate = true;
                Pose();

                break;
            case MotionEvent.ACTION_UP:
                //タップ中ですよフラグオフ
                tapstate = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (ms.RectTap(tx, ty, object.get(0).OgetTapRect()) == true)
                    object.get(0).OMove(tx, ty);

                break;
        }
        return true;
    }

    /*
     * 敵出現メソッド
     * 一時的に動きを入れておいたクラスを使い
     * 複数のクラスなら最初の要素から調べ、カウントだけを調べます
     * ゲーム内時間と要素のカウントが同じになれば、敵を作成
     */
    public void TekiSyutugen() {
        if (teki_pop_kazu != TEKIKAZU) {
            if (tpop[teki_pop_kazu].tpc == count) {
                object.add(new Tekiki(disp_w, disp_h));
                object.get(object.size() - 1).
                        Oint(tekibit, 50, -60, 0, 0, tekibit.getWidth(), tekibit.getHeight(), 0,
                                0, tpop[teki_pop_kazu].tms, tpop[teki_pop_kazu].tmr, tpop[teki_pop_kazu].tmc);
                ++teki_pop_kazu;
            }
        }
    }

    /*
     * 弾を１發打ったら連続で打てないようになり、タイマー
     * が０になったら打てるようにしています。
     */
    private void TamaTime() {
        if (tamaflg == false) {
            --tamatime;
            if (tamatime < 0) {
                tamatime = TAMATIMES;
                tamaflg = true;
            }
        }
    }

    /*
     * 爆破オブジェクトであり、それが消える状態なら
     * 今度はパワーアップアイテムを出現させる
     */
    private void PowDraw(int i) {
        if (object.get(i).obsyurui == 3) {
            if (object.get(i).dead == true) {
                object.add(new Pow(disp_w, disp_h));
                object.get(object.size() - 1).Oint(
                        powbit, object.get(i).cx, object.get(i).cy,
                        0, 0, powbit.getWidth(), powbit.getHeight(), 0);
            }
        }
    }

    //敵機と弾が当たったら爆発オブジェクト生成
    private void BomDraw(int i) {
        if (object.get(i).bomsflg == true) {
            object.add(new Bom(disp_w, disp_h));
            object.get(object.size() - 1).Oint(
                    bombit, object.get(i).cx, object.get(i).cy,
                    0, 0, bombit[0].getWidth(), bombit[0].getHeight(), 0);
            //爆発音追加
            ms.playSound(boms);
        }
    }

    /*
     * 背景表示＆スクロール
     * setBoundsはセットさせたい画像を短形の範囲で表示させるメソッドです
     * 表示させたい座標にも表示もできますが、後半２つの引数(right,bottom)の
     * 設定で画像の大きさも変更させることができます。
     * 今回は背景画像を定数で割っているのでそのまま表示させるとただ表示するだけなので
     * スクロールさせるために割った画像の１つを画面外にだしたいために
     * 画面表示は残りの画像で表示させたいわけです。
     * なので本当の画像の大きさではなく、定数より１つ少なくした数で画像の大きさを
     * 調整しています。
     * 本当の画像よりちょっと縦に伸びた感じで表示されますが、たいした
     * 画像ではないのでちゃんと表示されます。
     *
     * 画像移動座標はArrayListのbyで設定しているのですが、直接座標を
     * 書き換えることができません。なのでbyset(index,書き換え内容)で
     * 設定しています。
     *
     * 座標(top)が画面外にでたら０座標から分割画像一つ分上の座標にセット
     */
    public void Haikei(Canvas c) {
        for (int i = 0; i < HAIKEI; i++) {
            buck.get(i).setBounds(0, by.get(i), (int) disp_w, (int) (by.get(i) + (disp_h / (HAIKEI - 1))));
            buck.get(i).draw(c);
            by.set(i, by.get(i) + BUCKSCROOLSP);
            if (by.get(i) > disp_h) by.set(i, (int) (0 - (disp_h / (HAIKEI - 1))));
        }
    }

    /*
     * ポーズボタン用
     * 今回から画面上部タップでポースするようにしました
     */
    private void Pose() {
        if (ms.RectTap(tx, ty, pose) == true) {
            if (poseflg == true) {
                poseflg = false;
            } else {
                poseflg = true;
            }
        }
    }

    private void Tamahassya() {
		/*
		 * 弾を打っていいよ状態であり自機画像範囲にタップしていれば
		 * Jitama(弾クラス)を作成する
		 */
        if (tamaflg == true && ms.RectTap(
                tx, ty, object.get(0).OgetTapRect()) == true) {
			/*
			 * 弾を出すような操作をすると
			 * どんな弾を作成するかのメソッドを
			 * 呼び出します
			 */
            Tamajoutai();
            tamaflg = false;
        }
    }


    /*
     * このメソッドも他のファイルに移したい所ですが
     * MainLoopで使用している変数を多く使用しているため
     * 渡す変数が多いならここでもいいんじゃないかな・・・とか
     * 思っちゃってます。どうしたものか
     *
     * Objectクラスに弾の状態を表す変数を用意しています
     * それを外部から操作して変化させることでここのif文
     * の分岐に割り当てられます
     */
    public void Tamajoutai() {
        //通常の１発だけ
        if (object.get(0).tamajoutai == 0) {
            object.add(new JiTama(disp_w, disp_h));
            object.get(object.size() - 1).Oint(
                    tamabit, object.get(0).cx, object.get(0).cy - jikibit.getHeight(),
                    0, 30, tamabit.getWidth(), tamabit.getHeight(), 0);
            //音源再生
            ms.playSound(jitama1s);
        }
        //２発並んで
        if (object.get(0).tamajoutai == 1) {

            object.add(new JiTama(disp_w, disp_h));
            object.get(object.size() - 1).Oint(
                    tamabit, object.get(0).cx - 20, object.get(0).cy - jikibit.getHeight(),
                    0, 30, tamabit.getWidth(), tamabit.getHeight(), 0);
            object.add(new JiTama(disp_w, disp_h));
            object.get(object.size() - 1).Oint(
                    tamabit, object.get(0).cx + 20, object.get(0).cy - jikibit.getHeight(),
                    0, 30, tamabit.getWidth(), tamabit.getHeight(), 0);
            //音源再生
            ms.playSound(jitama3s);
        }
		/*
		 * 36度づつ自機の周りに１０発一気に出ます
		 *
		 * ちょっと改造しました。自機を中心にちょっと離れた場所
		 * から弾が発射されるようにするために、弾の初期位置の角度
		 * （360/10）からその角度にsinでx座標cosでy座標に進む座標
		 * の最小値が出るので、一定の数値を掛ける（55）、そして
		 * 自機の中心座標にそれぞれ足したりすることで自機の中心から
		 * 少し離れた場所から発射されるようになりました。
		 */
        if (object.get(0).tamajoutai == 2) {
            for (int i = 0; i < 10; i++) {
                int r = i * (360 / 10);
                float rx = (float) (Math.sin(ms.toRadian(r)) * 55);
                float ry = (float) (Math.cos(ms.toRadian(r)) * 55);
                object.add(new JiTama(disp_w, disp_h));
                object.get(object.size() - 1).Oint(
                        tamabit, object.get(0).cx + rx, object.get(0).cy - ry,
                        30, 30, tamabit.getWidth(), tamabit.getHeight(), r);
            }
            //音源再生
            ms.playSound(jitama2s);
        }
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }//お決まり

    public void surfaceCreated(SurfaceHolder arg0) {
        thread = new Thread(this);
        thread.start();
    }//お決まり

    public void surfaceDestroyed(SurfaceHolder arg0) {
        thread = null;
    }//お決まり

}


