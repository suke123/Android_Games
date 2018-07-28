package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniX1 extends AbstractScene {
    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.aniX2;
            case 1:
                return GameState.aniX3;
            case 2:
                return GameState.aniX4;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.m2;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniX1;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_aniX;
    }
    @Override
    public int getDateId() {
        return R.string.date3;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "AniX1";}
}