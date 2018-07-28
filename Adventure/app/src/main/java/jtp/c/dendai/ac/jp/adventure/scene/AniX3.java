package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/25.
 */

public class AniX3 extends AbstractScene {
    @Override
    public GameState next(int no) {
        int i = Getkaisuu();
        int k = Getkoukan();
        Setkoukan(k+10);

        switch(i){
            case 0:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 1:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 2:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 3:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 4:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 5:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 6:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 7:
                return GameState.aniX5;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.m2;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniX3;
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