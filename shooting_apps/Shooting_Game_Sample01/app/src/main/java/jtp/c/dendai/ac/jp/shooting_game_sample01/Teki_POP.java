package jtp.c.dendai.ac.jp.shooting_game_sample01;

/**
 * Created by taka on 2017/05/30.
 */

/*
 * 将来的にはいらなくなるかもですが
 * 一時的に敵の動きを記憶させておくクラスです
 */

import java.util.ArrayList;

public class Teki_POP {
    public int tpc;
    public ArrayList<Integer> tms = new ArrayList();
    public ArrayList<Integer> tmr = new ArrayList();
    public ArrayList<Integer> tmc = new ArrayList();

    Teki_POP(int tpc,ArrayList tms,ArrayList tmr,ArrayList tmc){
        this.tpc = tpc;
        this.tms = tms;
        this.tmr = tmr;
        this.tmc = tmc;
    }

}

