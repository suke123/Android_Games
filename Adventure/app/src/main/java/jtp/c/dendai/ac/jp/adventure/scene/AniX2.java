package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class AniX2 extends AbstractScene {
    @Override
    public GameState next(int no) {

        int i = Getkaisuu();
        int k = Getkoukan();
        Setkoukan(k+20);

        switch(i){
            case 0:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 1:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 2:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 3:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 4:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 5:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 6:
                Setkaisuu(i+1);
                return GameState.aniX1;
            case 7:
                return GameState.aniX5;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.ending_s;
    }
    @Override
    public int getMessageId() {
        return R.array.message_aniX2;
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
    public int getMusicId() { return R.raw.goodend;}
    @Override
    public String getSceneName() { return "AniX2";}
}