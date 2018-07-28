package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/26.
 */

public class Ending extends AbstractScene {
    @Override
    public GameState next(int no) {
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.ending_b;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniend;
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
    public int getMusicId() { return R.raw.result;}
    @Override
    public String getSceneName() { return "Ending";}
}