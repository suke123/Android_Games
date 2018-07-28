/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtp.c.dendai.ac.jp.switch_test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 * @author kie
 */
public class Score {

    private int irekae = 0;
    private Paint paint = new Paint();

    public Score() {
        paint.setColor(Color.BLACK);

    }

    public int getIre(){
        return irekae;
    }

    public void addIre(){
        irekae += 1;
    }

    public void draw(Canvas canvas) {
        String s= String.valueOf(irekae);
        paint.setTextSize(100);
        canvas.drawText("CHANGE："+s, 50, paint.getTextSize(), paint);
    }
    
    
}
