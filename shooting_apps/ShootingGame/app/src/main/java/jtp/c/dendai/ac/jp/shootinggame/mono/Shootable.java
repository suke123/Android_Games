package jtp.c.dendai.ac.jp.shootinggame.mono;
import jtp.c.dendai.ac.jp.shootinggame.Vect;
public interface Shootable extends Mono {
    Shootable getInstance();
    void init(Vect p, Vect dp);
}