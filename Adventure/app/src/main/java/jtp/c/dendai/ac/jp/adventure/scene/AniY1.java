package jtp.c.dendai.ac.jp.adventure.scene;
import java.util.Random;

import jtp.c.dendai.ac.jp.adventure.R;

public class AniY1 extends AbstractScene {
    Random ran1 = new Random();
    int a = ran1.nextInt(3);

    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.aniY2;
            case 1:
                return GameState.aniY3;
            case 2:
                return GameState.aniY4;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.first;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniY1;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_aniY;
    }
    @Override
    public int getDateId() {
        return R.string.date3;
    }
    @Override
    public int getMusicId() { return R.raw.osodate;}
    @Override
    public String getSceneName() { return "AniY1";}
}