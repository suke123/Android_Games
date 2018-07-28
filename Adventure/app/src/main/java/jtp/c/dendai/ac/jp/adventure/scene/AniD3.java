package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniD3 extends AbstractScene {
    @Override
    public GameState next(int no) {

                return GameState.aniD6;

    }
    @Override
    public int getImageId() {
        return R.drawable.bridge;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniD3;
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