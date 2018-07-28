package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

public class DeadEnd extends AbstractScene {
    @Override
    public GameState next(int no) {
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.deadend;
    }
    @Override
    public int getDateId(){
        return R.string.dateending;
    }
    @Override
    public int getMessageId() {
        return R.array.messageend_dead;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getMusicId() { return R.raw.fantasy09;}
    @Override
    public String getSceneName() { return "DeadEnd";}
}
