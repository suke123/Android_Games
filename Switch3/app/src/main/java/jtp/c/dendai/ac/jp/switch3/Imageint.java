package jtp.c.dendai.ac.jp.switch3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by DE on 2017/10/17.
 */

public  class Imageint implements Idealist {
    Context con;
    // Context con =
    private Bitmap[] gazou;
    private int cycle;
    private int clock;
    protected final int width;
    protected final int height;
    protected double period;

    public Imageint( int[] ids) {

        con = MyApplication.getInstance().getApplicationContext();
        cycle = ids.length;
        gazou = new Bitmap[cycle];
        for (int i = 0; i < cycle; i++) {
            gazou[i] = BitmapFactory.decodeResource(con.getResources(), ids[i]);
        }
        width = gazou[0].getWidth();
        height = gazou[0].getHeight();
        clock = 0;
        period = 0;
    //    alive = true;
    }


  //  Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.blockB);
    //Bitmap bug = BitmapFactory.decodeResource(con.getResources(), R.drawable.block_r);

    @Override
    public Bitmap getBit(int i){
        Bitmap bug = BitmapFactory.decodeResource(con.getResources(), R.drawable.block_r);
        if(i<gazou.length)
            return gazou[i];

        return bug;
    }
  //  Bitmap b = getB(3);

}
