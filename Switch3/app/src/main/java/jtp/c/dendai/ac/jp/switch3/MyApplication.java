package jtp.c.dendai.ac.jp.switch3;

import android.app.Application;



public class MyApplication extends Application {
    private static MyApplication instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    //Context context = MyApplication.getInstance().getApplicationContext();
    public static MyApplication getInstance() {
        return instance;
    }
}