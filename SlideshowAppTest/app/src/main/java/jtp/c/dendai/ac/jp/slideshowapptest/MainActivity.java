package jtp.c.dendai.ac.jp.slideshowapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Slideshow slideshow = new Slideshow(this);
        slideshow.search_path("images");
        
    }
}
