package jtp.c.dendai.ac.jp.switch3.stage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import jtp.c.dendai.ac.jp.switch3.Imageint;
import jtp.c.dendai.ac.jp.switch3.MapView;

/**
 * Created by DE on 2017/10/23.
 */

public class Stage1 extends MapView {
    Activity gameActivity;
    Imageint imageint;

    Bitmap b = imageint.getBit(2);

    public Stage1(String filename, Context context ,Activity gameActivity) {
        super(filename, context,gameActivity);
        this.gameActivity=gameActivity;
    }


}
