package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;
public class BadEnd extends AbstractScene {
    @Override
    public GameState next(int no) {
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.ending_d;
    }
    @Override
    public int getDateId(){
        return R.string.dateending;
    }
    @Override
    public int getMessageId() {
        return R.array.message_d1;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getMusicId() { return R.raw.badend;}
    @Override
    public String getSceneName() { return "BadEnd";}
}