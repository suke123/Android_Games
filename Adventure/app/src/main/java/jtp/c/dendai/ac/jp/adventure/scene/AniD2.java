package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniD2 extends AbstractScene {
    @Override
    public GameState next(int no) {

            switch(no){
                case 0:
                    return GameState.aniD3;
                case 1:
                    return GameState.aniD4;
                case 2:
                    return GameState.aniD5;
            }
            return null;

    }
    @Override
    public int getImageId() {
        return R.drawable.ending_s;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniD2;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_aniD1;
    }
    @Override
    public int getDateId() {
        return R.string.dateending;
    }
    @Override
    public int getMusicId() { return R.raw.goodend;}
    @Override
    public String getSceneName() { return "AniD2";}
}