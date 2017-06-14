package jp.ac.dendai.c.jtp.myapplication1.mono;
import jp.ac.dendai.c.jtp.myapplication1.Vect;
public interface Shootable extends Mono {
    Shootable getInstance();
    void init(Vect p, Vect dp);
}