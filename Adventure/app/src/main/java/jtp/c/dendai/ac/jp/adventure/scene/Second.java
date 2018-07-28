package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;
public class Second extends AbstractScene {
    private int a = 0;

    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.aniX1;
            case 1:
                return GameState.aniY1;
            case 2:
                return GameState.aniZ1;

        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.second;
    }
    @Override
    public int getMessageId() {
        return R.array.message2;
    }
    @Override
    public int getQuestionId() {
        return R.array.question2;
    }

    @Override
    public int getDateId() {
        return R.string.date1;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "Second";}
}