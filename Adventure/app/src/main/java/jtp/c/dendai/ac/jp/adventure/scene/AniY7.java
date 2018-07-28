package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

/**
 * Created by DE on 2017/09/25.
 */

public class AniY7 extends AbstractScene {
    @Override
    public GameState next(int no) {
        return GameState.ending;
    }
    @Override
    public int getImageId() {
        return R.drawable.m2;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniY9;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_ben1;
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