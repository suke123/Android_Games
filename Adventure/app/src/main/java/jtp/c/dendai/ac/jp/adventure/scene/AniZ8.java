package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/26.
 */

public class AniZ8 extends AbstractScene {
    @Override
    public GameState next(int no) {

        return GameState.ending;

    }
    @Override
    public int getImageId() {
        return R.drawable.ending_b;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniZ10;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.dateending;
    }
    @Override
    public int getMusicId() { return R.raw.piano22;}
    @Override
    public String getSceneName() { return "AniY5";}
}