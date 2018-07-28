package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;
public class First extends AbstractScene {
    private boolean u = true;
    @Override
    public int getImageId() {
        return R.drawable.first;
    }
    @Override
    public int getMessageId() {
        return R.array.message1;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public GameState next(int no) {
        int a = Getsta();
        switch (a){
            case 1:
            return GameState.second;
            case 5:
            return GameState.second;
            case 7:
                return GameState.second;
            case 455:
                return GameState.second2;//5,7,13
        }
        return GameState.second;
    }
    @Override
    public int getDateId() {
        return R.string.date1;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "First";}
}