package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class Fre1 extends AbstractScene {
    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.aniX1;
            case 1:
                return GameState.aniY1;
            case 2:
                return GameState.aniZ1;
            case 3:
                return GameState.aniD4;
            case 4:
                return GameState.deadend;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.bridge;
    }
    @Override
    public int getMessageId() {
        return R.array.message_fre1;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_fre1;
    }
    @Override
    public int getDateId() {
        return R.string.date1;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "Fre1";}
}