package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/25.
 */

public class AniZ5 extends AbstractScene {
    @Override
    public GameState next(int no) {
        Setsta(13);
        int k = Getkoukan();

        if(k>=90) {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniZ6;
        }
        else if(k>=60) {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniZ7;
        }
        else {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniZ8;
        }

    }
    @Override
    public int getImageId() {
        return R.drawable.m2;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniZ7;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.date3;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "AniD1";}
}