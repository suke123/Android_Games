package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.Handler;
import android.view.View.OnClickListener;
public interface Scene extends OnClickListener {
    GameState next(int no);
    //void start(Handler hand, int _index, int _koukan, int _countS);
    void start(Handler hand, int _index);
    int getMessageId();
    int getImageId();
    int getQuestionId();
    int getDateId();
    int getMusicId();
    String getSceneName();

    void setPoint(int koukan, int countS);
}
