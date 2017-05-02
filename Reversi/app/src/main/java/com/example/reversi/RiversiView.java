package com.example.reversi;

/**
 * Created by taka on 2017/04/22.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

class ReversiView extends View {
    public ReversiView(Context context){
        super(context);
    }

    //描写処理
    @Override
    public void onDraw(Canvas c){

    }

    //タッチ入力処理
    public boolean onTouchEvent(MotionEvent me){
        return true;
    }
}
