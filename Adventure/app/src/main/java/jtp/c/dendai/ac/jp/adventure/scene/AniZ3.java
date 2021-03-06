package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniZ3 extends AbstractScene {
    @Override
    public GameState next(int no) {
        int i = Getkaisuu();
        int k = Getkoukan();
        Setkoukan(k+10);

        switch(i){
            case 0:
                Setkaisuu(i+1);
                return GameState.aniZ1;
            case 1:
                Setkaisuu(i+1);
                return GameState.aniZ1;
            case 2:
                Setkaisuu(i+1);
                return GameState.aniZ1;
            case 3:
                Setkaisuu(i+1);
                return GameState.aniZ1;
            case 4:
                Setkaisuu(i+1);
                return GameState.aniZ1;
            case 5:
                Setkaisuu(i+1);
                return GameState.aniZ1;
            case 6:
                Setkaisuu(i+1);
                return GameState.aniZ1;
            case 7:
                return GameState.aniZ5;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.second;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniZ3;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.date6;
    }
    @Override
    public int getMusicId() { return R.raw.piano17;}
    @Override
    public String getSceneName() { return "AniZ3";}
}