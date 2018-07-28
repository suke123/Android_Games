package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/26.
 */

public class AniD8 extends AbstractScene {
    @Override
    public GameState next(int no) {

        return null;

    }
    @Override
    public int getImageId() {
        return R.drawable.bridge;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniD8;
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
    public String getSceneName() { return "AniD3";}
}