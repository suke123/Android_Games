package jtp.c.dendai.ac.jp.adventure;

import android.app.Activity;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



public class LFActivity extends Activity {
    /** Called when the activity is first created. */
    private static final String LOCAL_FILE = "save.csv";

    private void sampleFileOutput(String sa){

        OutputStream out;
        try {
            out = openFileOutput(LOCAL_FILE,MODE_PRIVATE|MODE_APPEND);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));

            //追記する
            writer.append(sa);
            writer.close();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}