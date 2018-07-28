package jtp.c.dendai.ac.jp.switch3;

/*public class MyApplication extends Application {
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
 }*/

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication instance = null;
    private Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        onCreateApplication(getApplicationContext());
    }


    // publicをつけないのは意図的
    // MyApplicationと同じパッケージにして、このメソッドのアクセスレベルはパッケージローカルとする
    // 念のため意図しないところで呼び出されることを防ぐため
    static void onCreateApplication(Context applicationContext) {
        // Application#onCreateのタイミングでシングルトンが生成される
        instance = new MyApplication(applicationContext);
    }

    private MyApplication(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            // こんなことは起きないはず
            //throw new RuntimeException("MyApplication should be initialized!");
        }
        return instance;
    }

    public static Context getContext() {
        return instance;
    }
//    Context con = MyApplication.getInstance().getApplicationContext();

}
