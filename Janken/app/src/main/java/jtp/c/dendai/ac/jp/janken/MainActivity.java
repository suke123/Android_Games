package jtp.c.dendai.ac.jp.janken;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int counter = 0;
    Button btn;
    TextView txt01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //リソースIDの設定
        btn = (Button)findViewById(R.id.button);
        txt01 = (TextView)findViewById(R.id.text01);

        //Listenerの設定
        btn.setOnClickListener(this);

        //TextViewの初期化
        txt01.setText(getString(R.string.text_1, counter));
    }

    @Override
    public void onClick(View view){
        //ボタンを押した時の動作
        switch (view.getId()){
            case R.id.button:{
                counter++;
                break;
            }
        }

        //表示更新
        txt01.setText(getString(R.string.text_1, counter));
    }
}
