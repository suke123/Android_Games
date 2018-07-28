package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniY5 extends AbstractScene {
    @Override
    public GameState next(int no) {
        Setsta(7);
        int k = Getkoukan();

        if(k>=90) {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniY6;
        }
        else if(k>=60) {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniY7;
        }
        else {
            Setkoukan(0);
            Setkaisuu(0);
            return GameState.aniY8;
        }

    }
    @Override
    public int getImageId() {
        return R.drawable.ending_b;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniY7;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.dateending;
    }
    @Override
    public int getMusicId() { return R.raw.piano22;}
    @Override
    public String getSceneName() { return "AniY5";}
}