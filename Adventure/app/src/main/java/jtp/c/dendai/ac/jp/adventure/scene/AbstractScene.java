package jtp.c.dendai.ac.jp.adventure.scene;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import jtp.c.dendai.ac.jp.adventure.Handler;
import jtp.c.dendai.ac.jp.adventure.R;

public abstract class AbstractScene implements Scene {
    private static android.os.Handler osHandler;

    public static void setHandler(android.os.Handler _osHandler) {
        osHandler = _osHandler;
    }

    private static String log;
    private static boolean isVisible = false;
    private static boolean isAuto = false;
    private boolean release;
    private MediaPlayer mp;
    private static SoundPool pool;
    private static int sound;
    private static int sound2;
    private static int aaa = 0;
    private static int koukan = 0;
    private static int countS = 0;

    private String[] savedataKeyName = {"save_1", "save_2", "save_3"};
    private String[] saveText = {"セーブ１", "セーブ２", "セーブ３"};

    private int index;
    private Handler handler;
    private static Activity activity;

    protected int size() {
        return getMessage().length;
    }



    public int Getkoukan() {
        int i = koukan;
        return i;
    }

    public int Getkaisuu() {
        int i = countS;
        return i;
    }

    public void Setkoukan(int i) {
        koukan = i;

    }

    public void Setkaisuu(int i) {
        countS = i;

    }

    public int Getsta() {
        SharedPreferences prefer = activity.getPreferences(activity.MODE_PRIVATE);
        int q = prefer.getInt("IRUKA", 1);

        return q;
    }

    public void Setsta(int r) {
        SharedPreferences prefer2 = activity.getPreferences(activity.MODE_PRIVATE);
        int q = prefer2.getInt("IRUKA", 1);
        // SharedPreferences prefer2 = activity.getPreferences(activity.MODE_PRIVATE);
        Editor editor2 = prefer2.edit();
        //  String className = new Object(){}.getClass().getEnclosingClass().getName();
        int m = q % r == 0 ? q : q * r;
        editor2.putInt("IRUKA", m);
        //editor2.putString("qsave",getSceneName() + "<>" + index + "<>" + log);
        editor2.commit();

    }


    public static void setActivity(Activity _activity) {
        activity = _activity;
        pool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        sound = pool.load(activity, R.raw.prompt, 1);
        sound2 = pool.load(activity, R.raw.start, 1);
    }

    @Override
    public void onClick(View v) {
        ToggleButton autoButton = ((ToggleButton) activity.findViewById(R.id.autoButton));
        isAuto = false;
        autoButton.setChecked(isAuto);

        TextView textView = (TextView) activity.findViewById(R.id.textarea);
        String[] item = getMessage()[index].split(":", 2);
        String message = item[item.length - 1];

        if (textView.getText().length() < message.length()) textView.setText(message);
        else nextPage();
    }

    private void askQuestion() {
        Builder builder = new Builder(activity, R.style.MyAlertDialogStyle);
        builder.setCancelable(false);
        builder.setTitle(activity.getString(R.string.question_1));
        builder.setItems(getQuestion(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {
                log += "\n\t⇒ " + getQuestion()[witch] + "\n";
                handler.step(next(witch).getScene());
                mp.stop();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        //MyDialog dialog = new MyDialog();

    }

    private void askQuestion2() {
        Builder builder = new Builder(activity, R.style.MyAlertDialogStyle);
        builder.setCancelable(false);
        builder.setTitle(activity.getString(R.string.question_2));
        builder.setItems(getQuestion(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {
                log += "\n\t⇒ " + getQuestion()[witch] + "\n";
                handler.step(next(witch).getScene());
                mp.stop();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        //MyDialog dialog = new MyDialog();

    }

    private void askQuestion3() {
        Builder builder = new Builder(activity, R.style.MyAlertDialogStyle);
        builder.setCancelable(false);
        builder.setTitle(activity.getString(R.string.question_2));
        builder.setItems(getQuestion(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {
                log += "\n\t⇒ " + getQuestion()[witch] + "\n";
                handler.step(next(witch).getScene());
                mp.stop();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        //MyDialog dialog = new MyDialog();

    }

    /*セーブボタンを押した時の表示
    * セーブデータ３つのうちからどれに保管するかを選ぶ画面
    * セーブデータが格納されているものは「セーブデータ番号　シーン名」を表示
    * 格納されていないものは「セーブデータ番号　データがありません」を表示*/
    private void saveMessage() {
        Builder builder = new Builder(activity, R.style.MyAlertDialogStyle);
        builder.setCancelable(false);
        builder.setTitle(activity.getString(R.string.save_text));
        builder.setItems(saveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences prefer = activity.getPreferences(activity.MODE_PRIVATE);
                Editor editor = prefer.edit();
                String className = new Object() {
                }.getClass().getEnclosingClass().getName();
                editor.putString(savedataKeyName[which], getSceneName() + "/" + index + "/" + Getkoukan() + "/" + Getkaisuu() + "/" + log);
                editor.commit();
                Toast.makeText(activity, activity.getString(R.string.save_complete), Toast.LENGTH_SHORT).show();
            }
        }).setPositiveButton("close", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void setPoint(int _koukan, int _countS) {
        koukan = _koukan;
        countS = _countS;
    }

    @Override
    //public void start(Handler h, int _index, int _koukan, int _countS) {
    public void start(Handler h, int _index) {

        //setPoint(koukan, countS);

        handler = h;
        index = _index;
        //koukan = _koukan;
        //countS = _countS;
        activity.setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) activity.findViewById(R.id.imageView1);
        imageView.setOnClickListener(this);
        imageView.setImageResource(getImageId());

        imageView.setScaleType(ScaleType.FIT_XY);

        final TextView logView = ((TextView) activity.findViewById(R.id.log));
        logView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (isVisible) logView.setVisibility(View.VISIBLE);
        else logView.setVisibility(View.INVISIBLE);

        ToggleButton logButton = ((ToggleButton) activity.findViewById(R.id.logButton));
        logButton.setChecked(isVisible);
        logButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isVisible = isChecked;
                if (isVisible) logView.setVisibility(View.VISIBLE);
                else logView.setVisibility(View.INVISIBLE);
                pool.play(sound2, 0.5f, 0.5f, 0, 0, 1);
            }
        });

        final TextView textView = ((TextView) activity.findViewById(R.id.textarea));
        ToggleButton autoButton = ((ToggleButton) activity.findViewById(R.id.autoButton));
        autoButton.setChecked(isAuto);
        autoButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAuto = isChecked;
                String[] item = getMessage()[index].split(":", 2);
                String message = item[item.length - 1];
                if (textView.getText().length() == message.length()) nextPage();
                pool.play(sound2, 0.5f, 0.5f, 0, 0, 1);
            }
        });

        (activity.findViewById(R.id.exit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.step(null);
                mp.stop();
                mp.release();
                mp = null;
                release = true;
                pool.play(sound2, 0.5f, 0.5f, 0, 0, 1);
            }
        });

        /*セーブボタンを押した時の処理
        * saveMessageメソッドを呼び出し、１〜３のどれにセーブをするかを決める
        * それぞれキー値を変えることで、３つのセーブデータを可能とする
        * */
        (activity.findViewById(R.id.save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMessage();
            }
        });

        release = false;
        writeMessage();
        writeDate();
        writeName();
        writeKoukan();
        writeLog();
        mp = MediaPlayer.create(activity, getMusicId());
        mp.setLooping(true);
        mp.start();
    }

    public static void setLog(String text) {
        log = text;
    }

    public static void setIsAuto(Boolean bool) {
        isAuto = bool;
    }

    public static void setIsVisible(Boolean bool) {
        isVisible = bool;
    }

    private void nextPage() {
        log += getMessage()[index] + "\n";
        writeLog();
        index++;
        if (index < size()) {
            writeName();
            writeMessage();
        } else {
            /* if (getQuestionId() != 0) {
                ImageView imageView = (ImageView) activity.findViewById(R.id.imageView1);
                imageView.setOnClickListener(null);
                askQuestion();
            } */
            if (getQuestionId() == R.array.question2) {
                ImageView imageView = (ImageView) activity.findViewById(R.id.imageView1);
                imageView.setOnClickListener(null);
                askQuestion2();
            } else if (getQuestionId() != 0) {
                ImageView imageView = (ImageView) activity.findViewById(R.id.imageView1);
                imageView.setOnClickListener(null);
                askQuestion();
            } else {
                GameState n = next(0);
                Scene scene = n == null ? null : n.getScene();
                handler.step(scene);
                mp.stop();
                mp.release();
                mp = null;
            }
        }
    }

    private void writeLog() {
        TextView logView = (TextView) activity.findViewById(R.id.log);
        logView.setText(log);
        if (logView.getLineCount() * logView.getLineHeight() > logView.getHeight())
            logView.scrollTo(0, (logView.getLineCount() * logView.getLineHeight()) - logView.getHeight());
    }

    private void writeMessage() {
        final TextView textView = (TextView) activity.findViewById(R.id.textarea);
        textView.setText(null);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (release || index == size()) return;
                String[] item = getMessage()[index].split(":", 2);
                String message = item[item.length - 1];
                if (textView.getText().length() < message.length()) {
                    textView.setText(message.substring(0, textView.getText().length() + 1));
                    pool.play(sound, 0.5f, 0.5f, 0, 0, 1);
                    osHandler.postDelayed(this, 100);
                } else if (isAuto) {
                    osHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nextPage();
                        }
                    }, 2000);
                }
            }
        };
        osHandler.post(runnable);
    }

    private void writeName() {
        TextView nameView = (TextView) activity.findViewById(R.id.name);
        String[] item = getMessage()[index].split(":", 2);
        if (item.length == 2) nameView.setText(item[0]);
        else nameView.setText(null);
    }

    private void writeDate() {
        TextView textdate = (TextView) activity.findViewById(R.id.textdate);
        textdate.setText(activity.getResources().getString(getDateId()));
        log += "\n" + activity.getResources().getString(getDateId()) + "\n\n";
    }

    private void writeKoukan(){
        TextView textkoukan = (TextView) activity.findViewById(R.id.koukan);
        textkoukan.setText("好感度：　" + Getkoukan());
    }

    protected String[] getMessage() {
        return getStringArrayById(getMessageId());
    }

    protected String[] getQuestion() {
        return getStringArrayById(getQuestionId());
    }

    protected String[] getStringArrayById(int id) {
        return activity.getResources().getStringArray(id);
    }


}
