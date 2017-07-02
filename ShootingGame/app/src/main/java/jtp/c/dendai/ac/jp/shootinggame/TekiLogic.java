package jtp.c.dendai.ac.jp.shootinggame;

import android.content.Context;

import jtp.c.dendai.ac.jp.shootinggame.mono.Hase;
import jtp.c.dendai.ac.jp.shootinggame.mono.Mono;
import jtp.c.dendai.ac.jp.shootinggame.mono.Mths;
import jtp.c.dendai.ac.jp.shootinggame.mono.Saka;
import jtp.c.dendai.ac.jp.shootinggame.mono.Teki;
import jtp.c.dendai.ac.jp.shootinggame.mono.Teki2;
import jtp.c.dendai.ac.jp.shootinggame.mono.Zako;

public class TekiLogic {
    private static double period = 700;
    private final Context context;
    private final HanteiList<Mono> list;
    private double tic;
    private double tic1;
    private double tic2;
    private double tic3;
    private double tic4;

    static boolean us = true;
    static boolean um = true;
    static boolean uh = true;


    public TekiLogic(Context context, HanteiList<Mono> list) {
        this.context = context;
        this.list = list;
        tic = 0;
      tic1 = 0;
      tic2 = 0;
      tic3 = 0;
      tic4 = 0;

        list.add(createTeki());
    }

    public int appear(int x ){
        double b;
        int z=0;
        b = Math.random();
        b = b*x;
        z = (int)b;

        return z;
    }




    private Mono createTeki() {
        return new Teki(context, appear(350), 50);
    }

    private Mono createTeki2() {
        return new Teki2(context, appear(350), 50);
    }

    private Mono createZako() {
        return new Zako(context, 0, 50);
    }

    private Mono createSaka() {
        return new Saka(context, 200, 50);
    }

    private Mono createMoto() {
        return new Mths(context, 200, 50);
    }

    private Mono createHase() {
        return new Hase(context, 200, 50);
    }


    public void step(double tstep, int width, int height) {
        tic += tstep;
        while (tic > period) {
            list.add(createTeki());
            tic -= period;
        }
    }

    public void step2(double tstep, int width, int height) {
        tic += tstep;
        while (tic > period) {
            list.add(createZako());
            tic -= period;
        }
    }

    public void relive(){
        us = true;
        um = true;
        uh = true;
    }


    public boolean lastboss(){
        if(!us&&uh)
            return true;
        else
        return false;

    }


    public void stepSaka(double tstep, int width, int height) {

        tic1 += tstep;
        while (tic1 > period+5000 && us) {
            list.add(createSaka());
            tic -= period;
            us = false;
        }
    }

    public void stepMths(double tstep, int width, int height) {

        tic2 += tstep;
        while (tic2 > period+1000 && um) {
            list.add(createMoto());
            tic -= period;
            um = false;
        }
    }


    public void stephase(double tstep, int width, int height) {

        tic3 += tstep;
        while (tic3 > period+3000 && uh) {
                list.add(createHase());
            tic -= period;
            uh = false;
        }
    }


    public void step3(double tstep, int width, int height) {
        tic4 += tstep;
        for(;tic4 > period+1200;) {
            while (tic4 > period+1200) {
                list.add(createTeki2());
                tic4 -= period;
            }
        }
    }
}