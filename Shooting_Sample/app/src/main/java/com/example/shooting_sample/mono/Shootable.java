package com.example.shooting_sample.mono;

import com.example.shooting_sample.Vect;

/**
 * Created by taka on 2017/05/02.
 */

public interface Shootable extends Mono {
    Shootable getInstance();
    void init(Vect p, Vect dp);
}
