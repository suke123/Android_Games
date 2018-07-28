package jtp.c.dendai.ac.jp.switch_test;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class LongClickRepeatAdapter {

    /**
     * 連続してボタンを押す間隔のデフォルト値 (ms)
     */
    private static final int REPEAT_INTERVAL = 100;

    /**
     * Viewに長押し時のリピート処理を付加する。 リピート間隔は100ms。
     */
    public static void bless(View view) {
        bless(REPEAT_INTERVAL, view);
    }

    /**
     * リピート間隔を指定して、Viewに長押しリピート処理を付加する
     */
    private static void bless(final int repeatInterval, final View view) {
        final Handler handler = new Handler();
        final BooleanWrapper isContinue = new BooleanWrapper(false);

        final Runnable repeatRunnable = new Runnable() {
            @Override
            public void run() {
                // 連打フラグをみて処理を続けるか判断する
                if (!isContinue.value) {
                    return;
                }

                // クリック処理を実行する
                view.performClick();

                // 連打間隔を過ぎた後に、再び自分を呼び出す
                handler.postDelayed(this, repeatInterval);
            }
        };

        view.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isContinue.value = true;

                // 長押しをきっかけに連打を開始する
                handler.post(repeatRunnable);

                return true;
            }
        });

        // タッチイベントを乗っ取る
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // キーから指が離されたら連打をオフにする
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    isContinue.value = false;
                }
                return false;
            }
        });
    }

    private static class BooleanWrapper {
        public boolean value;

        public BooleanWrapper(boolean value) {
            this.value = value;
        }
    }
}