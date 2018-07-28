package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/26.
 */

public class AniD6 extends AbstractScene {
    @Override
    public GameState next(int no) {

        switch(no){
            case 0:
                return GameState.aniD7;
            case 1:
                return GameState.aniD4;
            case 2:
                return GameState.aniD8;
        }
        return null;

    }
    @Override
    public int getImageId() {
        return R.drawable.bridge;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniD2;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_aniD2;
    }
    @Override
    public int getDateId() {
        return R.string.date3;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "AniD3";}
}