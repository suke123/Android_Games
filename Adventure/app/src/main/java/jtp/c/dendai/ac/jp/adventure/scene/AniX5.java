package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/25.
 */

public class AniX5 extends AbstractScene {
    @Override
    public GameState next(int no) {
        Setsta(5);
        int k = Getkoukan();

        if(k>=90) {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniX6;
        }
        else if(k>=60) {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniX7;
        }
        else {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniX8;
        }

    }
    @Override
    public int getImageId() {
        return R.drawable.m2;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniX6;
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