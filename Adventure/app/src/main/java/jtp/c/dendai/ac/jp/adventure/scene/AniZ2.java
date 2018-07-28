package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniZ2 extends AbstractScene {
    @Override
    public GameState next(int no) {
        Setsta(13);
        int i = Getkaisuu();
        int k = Getkoukan();
        Setkoukan(k+20);

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
        return R.drawable.ending_c;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniZ2;
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
    public String getSceneName() { return "AniZ2";}
}