package jtp.c.dendai.ac.jp.test_slideshow;

import android.app.Activity;
import android.content.res.AssetManager;
import android.widget.ImageView;

/**
 * Created by taka on 2017/05/22.
 */

public class Slideshow {
    private int i=0;
    private String[] files;
    private ImageView imageView;
    private AssetManager assetManager;
    private Activity activity;
    
    public Slideshow(Activity a){
        activity=a;
        imageView=(ImageView)a.findViewById(R.id.image_view);
    }

    public void Find_Dir(String dir) {

    }

    public void Image() {
    }
}
