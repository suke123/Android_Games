package jtp.c.dendai.ac.jp.switch3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by DE on 2017/10/17.
 */

public  class Imageint implements Idealist {
    //Context con;// = MyApplication.getInstance();
    private Bitmap[] gazou;
    private int cycle;
    private int clock;
    protected final int width;
    protected final int height;
    protected double period;
    private Bitmap bug;
    private Context context;

    public Imageint( int[] ids, Context context) {

        //con = MyApplication.getContext();
        this.context = context;
        cycle = ids.length;
        gazou = new Bitmap[cycle];
        for (int i = 0; i < cycle; i++) {
            gazou[i] = BitmapFactory.decodeResource(context.getResources(), ids[i]);
        }
        width = gazou[0].getWidth();
        height = gazou[0].getHeight();
        clock = 0;
        period = 0;

        bug = BitmapFactory.decodeResource(context.getResources(), R.drawable.block_r);
        //    alive = true;
    }

    public Imageint( int[] ids) {

        //con = MyApplication.getContext();
        //this.context = context;
        cycle = ids.length;
        gazou = new Bitmap[cycle];
        for (int i = 0; i < cycle; i++) {
            gazou[i] = BitmapFactory.decodeResource(context.getResources(), ids[i]);
        }
        width = gazou[0].getWidth();
        height = gazou[0].getHeight();
        clock = 0;
        period = 0;

        bug = BitmapFactory.decodeResource(context.getResources(), R.drawable.block_r);
        //    alive = true;
    }


    // Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.blockb);
    //Bitmap bug = BitmapFactory.decodeResource(con.getResources(), R.drawable.block_r);

    @Override
    public Bitmap getBit(int i){
        if(i<gazou.length)
            return gazou[i];

        return bug;
    }
  //  Bitmap b = getB(3);

}
