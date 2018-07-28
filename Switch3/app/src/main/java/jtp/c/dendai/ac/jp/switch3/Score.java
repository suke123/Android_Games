/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtp.c.dendai.ac.jp.switch3;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *
 * @author kie
 */
public class Score {

    private Paint paint = new Paint();
    private int irekae=0;
    
    public int getIre(){
    return irekae;
    }
    
    public void setIre(int a){
        irekae = a;
    
    }
    
    public void draw(Canvas canvas, int i) {
        String s= String.valueOf(i);
        /*
           Font font = new Font("Arial", Font.BOLD, 12);
           g.setFont(font);
           g.setColor(new Color(55, 139, 55));
           */
        //draw("change: "+s, 20, 25);
        canvas.drawText("change: "+s, 0, paint.getTextSize(), paint);

    }
    
    
}
