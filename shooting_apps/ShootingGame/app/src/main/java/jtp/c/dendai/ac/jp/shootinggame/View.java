package jtp.c.dendai.ac.jp.shootinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;

import jtp.c.dendai.ac.jp.shootinggame.mono.Anata;
import jtp.c.dendai.ac.jp.shootinggame.mono.Haikei;
import jtp.c.dendai.ac.jp.shootinggame.mono.Mikata;
import jtp.c.dendai.ac.jp.shootinggame.mono.Mono;
import jtp.c.dendai.ac.jp.shootinggame.mono.Shootable;

public class View extends SurfaceView {
    final static long tic = 10;
    public volatile boolean shutdown;
    private int width;
    private int height;
    private DrawList drawList;
    private Mikata mikata;
    private HanteiList<Mono> tekiList;
    private HanteiList<Shootable> tamaList;
    private Context context;
    private DrawThread drawThread;
    private MoveThread moveThread;
    private Score score;
    private TekiLogic tekiLogic;
    private final Object lock;
    private int za = 2;

    private GameActivity gameActivity;

    public View(Context context, Point p,GameActivity gameActivity) {
        super(context);
        this.context = context;
        width = p.x;
        height = p.y;
        lock = new Object();

        this.gameActivity = gameActivity;
    }

    public void init() {
        drawList = new DrawList();
        score = new Score();
        drawList.addScore(score);
        drawList.add(new Haikei(context));

        tamaList = new HanteiList<>();
        mikata = new Anata(context, tamaList);
        mikata.set(width / 2, height * 3 / 4);
        drawList.add(mikata);

        tekiList = new HanteiList<>();
        tekiLogic = new TekiLogic(context, tekiList);

        drawList.addList(tekiList);
        drawList.addList(tamaList);
        //    drawList.addScore(score);
        destroyThread(drawThread);
        destroyThread(moveThread);
        drawThread = new DrawThread();
        moveThread = new MoveThread();
    }

    public void start() {
        shutdown = false;
        drawThread.start();
        moveThread.start();
    }

    private void destroyThread(Thread t) {
        if (t != null) {
            shutdown = true;
            while (t.isAlive()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private void draw() {
        synchronized (lock) {
            Canvas canvas = getHolder().lockCanvas();
            if (canvas == null) return;
            drawList.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas); // 描画を終了
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mikata.setDirection(event, width, height);
                break;
            case MotionEvent.ACTION_MOVE:
                //mikata.setDrag(event, width, height);
                break;
            case MotionEvent.ACTION_UP:
                mikata.stop();
                if (shutdown) {
                    gameActivity.toResult();
                    //init();
                    //start();
                }
                performClick();
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public int getScore() {
        return score.getScore();
    }

    class MoveThread extends Thread {
        @Override
        public void run() {
            double previous = (double) System.currentTimeMillis();
            double now;
            while (!shutdown) {
                //   Debug.append("tamasize", "" + tamaList.size());
                synchronized (lock) {
                    now = System.currentTimeMillis();
                    double tstep = (now - previous) / tic;
                    //    Debug.append("tstep",""+tstep);
                    drawList.step(tstep, width, height);
                    tekiLogic.step(tstep, width, height);
                    tekiLogic.step2(tstep, width, height);
                    tekiLogic.stepSaka(tstep, width, height); //hennkoutenn
                    tekiLogic.stepMths(tstep, width, height);
                    tekiLogic.stephase(tstep, width, height);
                    //  tekiLogic.step(tstep, width, height); //hennkoutenn
                }
                previous = now;

                for (Shootable s : tamaList) {
                    Mono m = tekiList.atari(s.getRect());
                    if (m != null) {
                        score.add(m.getScore());
                        s.dead();
                        m.dead();
                    }
                }
                synchronized (lock) {
                    drawList.update();
                }
                if (tekiList.atari(mikata.getRect()) != null) { //当たり判定
                    Anata.Zanki();
                    za = Anata.getz();
                    mikata.set(width / 2, height * 3 / 4);//スタート地点に飛ばす(これをやらないと永遠にzankiが減り続ける)

                    if (za <= 0) {//残りが０
                        Anata.Rlive();
                        drawList.stop();
                        tekiLogic.relive(); //ボスが生き返るよ
                        shutdown = true;
                        break;
                    }
                }
                try {
                    sleep((long) tic);
                } catch (InterruptedException e) {
                    shutdown = true;
                }
            }
        }
    }

    class DrawThread extends Thread {
        @Override
        public void run() {
            while (!shutdown) {
                draw();
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    shutdown = true;
                }
            }
        }
    }
}