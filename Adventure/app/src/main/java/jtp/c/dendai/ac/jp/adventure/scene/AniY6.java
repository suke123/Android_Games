package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniY6 extends AbstractScene {
    @Override
    public GameState next(int no) {
        return GameState.ending;
    }
    @Override
    public int getImageId() {
        return R.drawable.pachi;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniY8;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.date6;
    }
    @Override
    public int getMusicId() { return R.raw.casino01;}
    @Override
    public String getSceneName() { return "AniY6";}
}