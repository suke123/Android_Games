package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniD1 extends AbstractScene {
    @Override
    public GameState next(int no) {
                return GameState.aniD2;

    }
    @Override
    public int getImageId() {
        return R.drawable.m2;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniD1;
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
    public int getMusicId() { return R.raw.osodate;}
    @Override
    public String getSceneName() { return "AniD1";}
}