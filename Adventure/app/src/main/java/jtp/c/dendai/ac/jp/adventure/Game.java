package jtp.c.dendai.ac.jp.adventure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import jtp.c.dendai.ac.jp.adventure.scene.AbstractScene;
import jtp.c.dendai.ac.jp.adventure.scene.GameState;
import jtp.c.dendai.ac.jp.adventure.scene.Scene;

public class Game implements Handler {
    private AppCompatActivity activity;
    private Title title;
    private Scene scene;
    private SoundPool pool;
    private int sound;
    private MediaPlayer mp;

    private String[] savedataKeyName = {"save_1", "save_2", "save_3"};
    private String[] saveText = {"セーブ１", "セーブ２", "セーブ３"};

    public Game(AppCompatActivity mainActivity) {
        this.activity = mainActivity;
        title = new Title();
        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = pool.load(activity, R.raw.start, 1);
        mp = MediaPlayer.create(activity, R.raw.daily);
        mp.setLooping(true);
    }

    @Override
    public void step(Scene s) {
        scene = s;
        //start(0, 20, 3);
        start(0);
    }

    //public void start(int index, int koukan, int countS) {
    public void start(int index) {
        if (scene == null) {
            AbstractScene.setLog("");
            AbstractScene.setIsVisible(false);
            AbstractScene.setIsAuto(false);
            activity.setContentView(title.getContentView());
            title.init(activity, new OnStartButtonClickListener(true), new OnStartButtonClickListener(false));
            mp.start();
        } else {
            activity.setContentView(R.layout.activity_main);
            //scene.start(this, index, koukan, countS);
            scene.start(this, index);
            mp.stop();
        }
    }

    class OnStartButtonClickListener implements OnClickListener {
        private boolean intialize;

        public OnStartButtonClickListener(boolean b) {
            intialize = b;
        }

        @Override
        public void onClick(View v) {
            if (intialize || scene == null) {
                scene = GameState.getInitialScene();
            }
            pool.play(sound, 1, 1, 0, 0, 1);
            switch (v.getId()) {
                case R.id.startbutton:
                    //start(0, 0, 0)
                    scene.setPoint(0,0);
                    start(0);
                    break;
                case R.id.continuebutton:
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle);
                    builder.setCancelable(false);
                    builder.setTitle(activity.getString(R.string.save_text));
                    builder.setItems(saveText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefer = activity.getPreferences(activity.MODE_PRIVATE);
                            String save = prefer.getString(savedataKeyName[which], null);
                            int index = 0;
                            int koukan = 0;
                            int countS = 0;
                            if (save != null) {
                                String[] data = save.split("/");

                                for (String str : data) System.out.println(str);
                                switch (data[0]) {
                                    case "First":
                                        scene = GameState.first == null ? null : GameState.first.getScene();
                                        break;
                                    case "Second":
                                        scene = GameState.first == null ? null : GameState.second.getScene();
                                        break;
                                    case "Second2":
                                        scene = GameState.first == null ? null : GameState.second2.getScene();
                                        break;

                                    case "AniD1":
                                        scene = GameState.first == null ? null : GameState.aniD1.getScene();
                                        break;
                                    case "AniD2":
                                        scene = GameState.first == null ? null : GameState.aniD2.getScene();
                                        break;
                                    case "AniD3":
                                        scene = GameState.first == null ? null : GameState.aniD3.getScene();
                                        break;
                                    case "AniD4":
                                        scene = GameState.first == null ? null : GameState.aniD4.getScene();
                                        break;
                                    case "AniD5":
                                        scene = GameState.first == null ? null : GameState.aniD5.getScene();
                                        break;
                                    case "AniD6":
                                        scene = GameState.first == null ? null : GameState.aniD6.getScene();
                                        break;
                                    case "AniD7":
                                        scene = GameState.first == null ? null : GameState.aniD7.getScene();
                                        break;
                                    case "AniD8":
                                        scene = GameState.first == null ? null : GameState.aniD8.getScene();
                                        break;
                                    case "AniD9":
                                        scene = GameState.first == null ? null : GameState.aniD9.getScene();
                                        break;

                                    case "Fre1":
                                        scene = GameState.first == null ? null : GameState.fre1.getScene();
                                        break;
                                    case "AniX1":
                                        scene = GameState.first == null ? null : GameState.aniX1.getScene();
                                        break;
                                    case "AniX2":
                                        scene = GameState.first == null ? null : GameState.aniX2.getScene();
                                        break;
                                    case "AniX3":
                                        scene = GameState.first == null ? null : GameState.aniX3.getScene();
                                        break;
                                    case "AniX4":
                                        scene = GameState.first == null ? null : GameState.aniX4.getScene();
                                        break;
                                    case "AniX5":
                                        scene = GameState.first == null ? null : GameState.aniX5.getScene();
                                        break;
                                    case "AniX6":
                                        scene = GameState.first == null ? null : GameState.aniX6.getScene();
                                        break;
                                    case "AniX7":
                                        scene = GameState.first == null ? null : GameState.aniX7.getScene();
                                        break;
                                    case "AniX8":
                                        scene = GameState.first == null ? null : GameState.aniX8.getScene();
                                        break;

                                    case "AniY1":
                                        scene = GameState.first == null ? null : GameState.aniY1.getScene();
                                        break;
                                    case "AniY2":
                                        scene = GameState.first == null ? null : GameState.aniY2.getScene();
                                        break;
                                    case "AniY3":
                                        scene = GameState.first == null ? null : GameState.aniY3.getScene();
                                        break;
                                    case "AniY4":
                                        scene = GameState.first == null ? null : GameState.aniY4.getScene();
                                        break;
                                    case "AniY5":
                                        scene = GameState.first == null ? null : GameState.aniY5.getScene();
                                        break;
                                    case "AniY6":
                                        scene = GameState.first == null ? null : GameState.aniY6.getScene();
                                        break;
                                    case "AniY7":
                                        scene = GameState.first == null ? null : GameState.aniY7.getScene();
                                        break;
                                    case "AniY8":
                                        scene = GameState.first == null ? null : GameState.aniY8.getScene();
                                        break;

                                    case "AniZ1":
                                        scene = GameState.first == null ? null : GameState.aniZ1.getScene();
                                        break;
                                    case "AniZ2":
                                        scene = GameState.first == null ? null : GameState.aniZ2.getScene();
                                        break;
                                    case "AniZ3":
                                        scene = GameState.first == null ? null : GameState.aniZ3.getScene();
                                        break;
                                    case "AniZ4":
                                        scene = GameState.first == null ? null : GameState.aniZ4.getScene();
                                        break;
                                    case "AniZ5":
                                        scene = GameState.first == null ? null : GameState.aniZ5.getScene();
                                        break;
                                    case "AniZ6":
                                        scene = GameState.first == null ? null : GameState.aniZ6.getScene();
                                        break;
                                    case "AniZ7":
                                        scene = GameState.first == null ? null : GameState.aniZ7.getScene();
                                        break;
                                    case "AniZ8":
                                        scene = GameState.first == null ? null : GameState.aniZ8.getScene();
                                        break;

                                    case "Ending":
                                        scene = GameState.first == null ? null : GameState.ending.getScene();
                                        break;
                                    case "BadEnd":
                                        scene = GameState.first == null ? null : GameState.badend.getScene();
                                        break;
                                    case "DeadEnd":
                                        scene = GameState.first == null ? null : GameState.deadend.getScene();
                                        break;

                                }
                                index = Integer.parseInt(data[1]);
                                koukan = Integer.parseInt(data[2]);
                                countS = Integer.parseInt(data[3]);
                                AbstractScene.setLog(data[4]);
                                //AbstractScene.setLog(data[2]);
                            }
                            //start(index, koukan, countS);
                            scene.setPoint(koukan, countS);
                            start(index);
                            //break;
                        }
                    }).setPositiveButton("close", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        }
    }
}
