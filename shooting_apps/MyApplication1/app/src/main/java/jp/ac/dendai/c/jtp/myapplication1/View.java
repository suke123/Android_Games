package jp.ac.dendai.c.jtp.myapplication1;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;

import jp.ac.dendai.c.jtp.myapplication1.mono.Anata;
import jp.ac.dendai.c.jtp.myapplication1.mono.Haikei;
import jp.ac.dendai.c.jtp.myapplication1.mono.Mikata;
import jp.ac.dendai.c.jtp.myapplication1.mono.Mono;
import jp.ac.dendai.c.jtp.myapplication1.mono.Shootable;
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
    public View(Context context, Point p) {
        super(context);
        this.context = context;
        width = p.x;
        height = p.y;
        lock = new Object();
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

        destroyThread(drawThread);
        destroyThread(moveThread);
        drawThread = new DrawThread();
        moveThread = new MoveThread();
    }
    public void start(){
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
                    init();
                    start();
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
                    tekiLogic.step2(tstep, width ,height); //hennkoutenn
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
                if (tekiList.atari(mikata.getRect()) != null) {
                    drawList.stop();
                    shutdown = true;
                    break;
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