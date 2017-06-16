package jtp.c.dendai.ac.jp.myapplication1.mono;

import jtp.c.dendai.ac.jp.myapplication1.Vect;


public interface Shootable extends Mono {
    Shootable getInstance();
    void init(Vect p, Vect dp);
}