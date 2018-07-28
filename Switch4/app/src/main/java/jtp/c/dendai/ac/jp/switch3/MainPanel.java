package jtp.c.dendai.ac.jp.switch3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Iterator;
import java.util.LinkedList;

import jtp.c.dendai.ac.jp.switch3.stage.Stage1;

/*
 * Created on 2005/06/06
 *
 */

/**
 * @author mori
 *  
 */
public class MainPanel extends SurfaceView implements SurfaceHolder.Callback{
    // パネルサイズ
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private final Paint paint = new Paint();

    DrawThread drawThread;
    MoveThread moveThread;

    private Activity gameActivity;
    // マップ
    private MapView mapView;

    // プレイヤー
    private Player player;
    private Player2 pl2;

    //入れ替え数

    Score sc = new Score();
    Context con ;


    public static int cnum = 0; //1:pl1が上 2:pl2が上

    private int ire = 3;


    public static int pnum = 0;

 //   private GameActivity gameActivity;

    public interface Callback {
        public void onGameOver2();
    }


    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    // アクションキー
    private ActionKey goLeftKey;
    private ActionKey goRightKey;
    private ActionKey jumpKey;
    private ActionKey kirikaeKey;
    private final Handler handler;
    Stage1 stage1;
    // ゲームループ用スレッド
    private Thread gameLoop;

    public MainPanel(Context context, Activity gameActivity) {
        super(context);

        this.con = context;
        handler = new Handler();
        getHolder().addCallback(this);
        this.gameActivity = gameActivity;
        SurfaceHolder holder = null;

        // パネルの推奨サイズを設定、pack()するときに必要
        //   setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // パネルがキー入力を受け付けるようにする

        setFocusable(true);

        // アクションキーを作成
        goLeftKey = new ActionKey();
        goRightKey = new ActionKey();

        kirikaeKey = new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);

        // ジャンプだけはキーを押し続けても1回だけしかジャンプしないようにする
        jumpKey = new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);

        // マップを作成
        if(stage1 == null){
            //stage1 = new Stage1("map01.dat", MyApplication.getContext(), gameActivity);
        }
        //stage1 = new Stage1("map01.dat", MyApplication.getContext(), gameActivity);

        // プレイヤーを作成
        //       player = new Player(192, 32, "player.gif", map);
        player = new Player(128, 32, "player.gif", stage1);
        pl2 = new Player2(32, 32, "girl.gif", stage1);
        // キーイベントリスナーを登録
        //   addKeyListener(this);

        // ゲームループ開始
  //      gameLoop = new Thread(this);
  //      gameLoop.start();
    }

/*
* BUTTON
*
*
* */



    /**
     * ゲームオーバー
     */
    public void gameOver() {
        // マップを作成
        Stage1 stage1 = new Stage1("map01.dat", con, gameActivity);

        // プレイヤーを作成
        player = new Player(128, 32, "player.gif", stage1);
        pl2 = new Player2(64, 32, "girl.gif", stage1);
    }


    public boolean iscol2() {
        int pl2x = (int) pl2.getX(), pl2y = (int) pl2.getY(), pl1x = (int) player.getX(), pl1y = (int) player.getY();

        Rect prect2 = new Rect(pl2x, pl2y, pl2x + pl2.getWidth(), pl2y + pl2.getHeight());
        Rect prect1 = new Rect(pl1x, pl1y, pl1x + player.getWidth(), pl1y + player.getHeight());
        // 自分の矩形と相手の矩形が重なっているか調べる
        if (prect2.intersect(prect1)) {
            return true;
        }

        return false;
    }


    public void repaint() {
        SurfaceHolder holder = getHolder();
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            synchronized (holder) {
                draw(canvas);
            }
        } catch (Exception e) {
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startDrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopDrawThread();
    }


    /**
     * ゲームループ
     */

    private class MoveThread extends Thread {
        boolean isFinished;

        @Override
        public void run() {
            SurfaceHolder holder = getHolder();
            Canvas canvas = holder.lockCanvas();

            sc.setIre(10);

            while (true) {
                if (goLeftKey.isPressed()) {
                    switch (pnum) {
                        case 0:
                            if (cnum == 2) {
                                player.accelerateLeft();
                                pl2.accelerateLeft();
                                break;
                            } else {
                                player.accelerateLeft();
                                break;
                            }
                        case 1:
                            if (cnum == 1) {
                                player.accelerateLeft();
                                pl2.accelerateLeft();
                                break;
                            } else {
                                pl2.accelerateLeft();
                                break;
                            }
                    }
                    // 左キーが押されていれば左向きに加速


                } else if (goRightKey.isPressed()) {
                    switch (pnum) {
                        case 0:
                            if (cnum == 2) {
                                player.accelerateRight();
                                pl2.accelerateRight();
                                break;
                            } else {
                                player.accelerateRight();
                                break;
                            }
                        case 1:
                            if (cnum == 1) {
                                player.accelerateRight();
                                pl2.accelerateRight();
                                break;
                            } else {
                                pl2.accelerateRight();
                                break;
                            }
                    }
                    // 右キーが押されていれば右向きに加速


                } else {
                    // 何も押されてないときは停止
                    player.stop();
                    pl2.stop();
                }

                if (jumpKey.isPressed()) {
                    // ジャンプする
                    switch (pnum) {
                        case 0:
                            if (cnum == 2) {
                                player.jump();
                                pl2.jump2();
                                break;
                            } else {
                                player.jump();
                                break;
                            }
                        case 1:
                            if (cnum == 1) {
                                player.jump2();
                                pl2.jump();
                                break;
                            } else {
                                pl2.jump();
                                break;
                            }
                    }
                    //    player.jump();
                    //    pl2.jump();
                }

                if (kirikaeKey.isPressed()) {
                    //System.out.println("YES TAKASU CLINIC");
                    if (sc.getIre() > 0) {
                        sc.setIre(sc.getIre() - 1);
                    }

                    switch (pnum) {
                        case 0:
                            pnum = 1;

                            break;
                        case 1:
                            pnum = 0;
                            break;
                    }
                }

                // プレイヤーの状態を更新
                if (pnum == 1 && cnum == 1)
                    player.update2(pl2);
                else
                    player.update();

                if (pnum == 0 && cnum == 2)
                    pl2.update2(player);
                else
                    pl2.update();

                // マップにいるスプライトを取得
                LinkedList sprites = stage1.getSprites();
                Iterator iterator = sprites.iterator();
                while (iterator.hasNext()) {
                    Sprite sprite = (Sprite) iterator.next();

                    // スプライトの状態を更新する
                    sprite.update();

                    if (iscol2()) {

                        if ((int) player.getY() < (int) pl2.getY()) {
                            player.humu();

                            cnum = 1;
                        }

                        if ((int) pl2.getY() < (int) player.getY()) {
                            pl2.humu();
                            cnum = 2;
                        }

                    } else {
                        cnum = 0;
                        //   System.out.println("fff*");
                    }


                    // プレイヤーと接触してたら
                    if (player.isCollision(sprite)) {
                        if (sprite instanceof Coin) {  // コイン
                            Coin coin = (Coin) sprite;
                            // コインは消える
                            sprites.remove(coin);
                            // ちゃり～ん
                            coin.play();
                            // spritesから削除したので
                            // breakしないとiteratorがおかしくなる
                            break;
                        } else if (sprite instanceof Kuribo) {  // 栗ボー
                            Kuribo kuribo = (Kuribo) sprite;
                            // 上から踏まれてたら
                            if ((int) player.getY() < (int) kuribo.getY()) {
                                // 栗ボーは消える
                                sprites.remove(kuribo);
                                // サウンド
                                kuribo.play();
                                // 踏むとプレイヤーは再ジャンプ
                                player.setForceJump(true);
                                player.jump();
                                break;
                            } else {
                                // ゲームオーバー
                                gameOver();
                            }
                        } else if (sprite instanceof Accelerator) {  // 加速アイテム
                            // アイテムは消える
                            sprites.remove(sprite);
                            Accelerator accelerator = (Accelerator) sprite;
                            // サウンド
                            accelerator.play();
                            // アイテムをその場で使う
                            accelerator.use(player);
                            break;
                        }
                    }
                }

                // 再描画
                //   repaint();
                paintComponent(canvas);

                // 休止
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private class DrawThread extends Thread {
        boolean isFinished;

        @Override
        public void run() {
            SurfaceHolder holder = getHolder();



            while (!isFinished) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    paintComponent(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }


    /**
     * 描画処理
     *
     * @param
     */
    public void paintComponent(Canvas canvas) {
        Paint pei = new Paint();
        Paint peint = new Paint();
        //  super.paintComponent(g);

        // 背景を黒で塗りつぶす
        pei.setColor(Color.BLACK);
        //   pei.fillRect(0, 0, getWidth(), getHeight());

        // X方向のオフセットを計算
        int offsetX = MainPanel.WIDTH / 2 - (int) player.getX();
        int offsetX2 = MainPanel.WIDTH / 2 - (int) pl2.getX();
        // マップの端ではスクロールしないようにする
        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, MainPanel.WIDTH - stage1.getWidth());
        offsetX2 = Math.min(offsetX2, 0);
        offsetX2 = Math.max(offsetX2, MainPanel.WIDTH - stage1.getWidth());
        // Y方向のオフセットを計算
        int offsetY = MainPanel.HEIGHT / 2 - (int) player.getY();
        int offsetY2 = MainPanel.HEIGHT / 2 - (int) pl2.getY();
        // マップの端ではスクロールしないようにする
        offsetY = Math.min(offsetY, 0);
        offsetY = Math.max(offsetY, MainPanel.HEIGHT - stage1.getHeight());
        offsetY2 = Math.min(offsetY2, 0);
        offsetY2 = Math.max(offsetY2, MainPanel.HEIGHT - stage1.getHeight());

        // マップを描画
        stage1.draw(canvas, offsetX, offsetY);

        // プレイヤーを描画
        player.draw(canvas, offsetX, offsetY);

        pl2.draw(canvas, offsetX, offsetY);

        // スプライトを描画
        // マップにいるスプライトを取得
        LinkedList sprites = stage1.getSprites();
        Iterator iterator = sprites.iterator();
        while (iterator.hasNext()) {
            Sprite sprite = (Sprite) iterator.next();
            sprite.draw(canvas, offsetX, offsetY, 1);
        }

        sc.draw(canvas, sc.getIre());

    }

    public void startDrawThread() {
        stopDrawThread();
        stopMoveThread();

        moveThread = new MoveThread();
        drawThread = new DrawThread();
        moveThread.start();
        drawThread.start();

    }

    public boolean stopDrawThread() {
        if (drawThread == null) {
            return false;
        }
        drawThread.isFinished = true;
        drawThread = null;
        return true;
    }
    public boolean stopMoveThread() {
        if (moveThread == null) {
            return false;
        }
        moveThread.isFinished = true;
        moveThread = null;
        return true;
    }



}


    /**
     * キーが押されたらキーの状態を「押された」に変える
     * 
     * @param e キーイベント
     */
 /*
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            goLeftKey.press();
        }
        if (key == KeyEvent.VK_RIGHT) {
            goRightKey.press();
        }
        if (key == KeyEvent.VK_UP) {
            jumpKey.press();
        }
        if (key == KeyEvent.VK_Z) {
            kirikaeKey.press();
        }
    }
*/
    /**
     * キーが離されたらキーの状態を「離された」に変える
     * 
     * @param e キーイベント
     */

    /*
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            goLeftKey.release();
        }
        if (key == KeyEvent.VK_RIGHT) {
            goRightKey.release();
        }
        if (key == KeyEvent.VK_UP) {
            jumpKey.release();
        }
         if (key == KeyEvent.VK_Z) {
            kirikaeKey.release();
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
*/