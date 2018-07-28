package jtp.c.dendai.ac.jp.switch3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by DE on 2017/10/17.
 */

public abstract class Imageint extends Activity implements Imagelist{
    //Context con = MyApplication.getInstance().getApplicationContext();
    private Bitmap[] gazou;
    private int cycle;
    private int clock;
    protected final int width;
    protected final int height;
    protected double period;
    Bitmap bug;

    public Imageint( int[] ids) {

        cycle = ids.length;
        gazou = new Bitmap[cycle];
        for (int i = 0; i < cycle; i++) {
            gazou[i] = BitmapFactory.decodeResource(this.getResources(), ids[i]);
        }
        width = gazou[0].getWidth();
        height = gazou[0].getHeight();
        clock = 0;
        period = 0;
        //alive = true;
        bug = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.block_g);
    }


    //  Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.blockB);

    @Override
    public Bitmap getBit(int i){
        if(i<gazou.length)
            return gazou[i];

        return bug;
    }
    //  Bitmap b = getB(3);

}
