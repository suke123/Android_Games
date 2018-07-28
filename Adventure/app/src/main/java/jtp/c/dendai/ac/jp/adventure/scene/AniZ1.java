package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniZ1 extends AbstractScene {
    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.aniZ2;
            case 1:
                return GameState.aniZ3;
            case 2:
                return GameState.aniZ4;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.m2;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniZ1;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_aniZ;
    }
    @Override
    public int getDateId() {
        return R.string.date5;
    }
    @Override
    public int getMusicId() { return R.raw.osodate;}
    @Override
    public String getSceneName() { return "AniZ1";}
}