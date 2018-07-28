/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtp.c.dendai.ac.jp.switch3;

import android.graphics.Canvas;

//import java.awt.Font;

//import java.awt.Color;

/**
 *
 * @author kie
 */
public class Score {
    
    private int irekae=0;
    
    public int getIre(){
    return irekae;
    }
    
    public void setIre(int a){
        irekae = a;
    }
    
    public void draw(Canvas canvas, int i) {
        String s= String.valueOf(i);
            
        //Font font = new Font("Arial", Font.BOLD, 12);
        //g.setFont(font);
        //g.setColor(new Color(55, 139, 55));
        //g.drawString("change: "+s, 20, 25);
    }
    
    
}
