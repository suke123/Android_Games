package jtp.c.dendai.ac.jp.switch_test;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by taka on 2017/10/25.
 */

public class Map{// extends Activity{
    private final Paint paint = new Paint();
    private Context context;

    //private static final int[] ids = {R.drawable.block_b,R.drawable.block_r,R.drawable.go, R.drawable.al};

    // パネルサイズ
    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;

    private int windowWidth = 0;
    //private int windowHeight = 0;


    public int TILE_SIZE = 0;
    // 重力
    public static final double GRAVITY = 0.6;
    // マップ
    private char[][] map;

    // 行数
    private int row;
    // 列数
    private int col;
    // 幅
    //private int width = 60;
    // 高さ
    //private int height= 60;

    private Bitmap block_B, block_R, block_GO, block_AL;
    private Bitmap boy, girl;

    private int firstTileX, lastTileX, firstTileY, lastTileY;

    private String fileName;

    //private ImageView backGroundImg;

    public Map(Context context, String fileName){
        //a.setContentView(game);
        this.context = context;

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        windowWidth = dm.widthPixels;
        //windowHeight = dm.heightPixels;

        //Log.i("Map : ", "画面幅 = " + windowWidth);
        //Log.i("Map : ", "画面高さ = " + windowHeight);

        TILE_SIZE = windowWidth / 16;

        this.fileName = fileName;

        loadMapFile();
        setBlockBitmap();
    }

    private void setBlockBitmap() {
        block_B = BitmapFactory.decodeResource(context.getResources(), R.drawable.block_b);
        block_R = BitmapFactory.decodeResource(context.getResources(), R.drawable.block_r);
        block_GO = BitmapFactory.decodeResource(context.getResources(), R.drawable.go);
        block_AL = BitmapFactory.decodeResource(context.getResources(), R.drawable.al);
        //boy = BitmapFactory.decodeResource(context.getResources(), R.drawable.boy1);
        //girl = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl1);
        //imageBG = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_back);
    }

    // マップデータ読み込み
    // そのデータをmap[行数][列数]に格納
    private void loadMapFile() {
        AssetManager assetManager = context.getResources().getAssets();
        InputStream is;
        try {
            is = assetManager.open(fileName + ".dat");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // 行数を読み込む
            String line = br.readLine();
            row = Integer.parseInt(line);
            // 列数を読み込む
            line = br.readLine();
            col = Integer.parseInt(line);
            // マップを作成
            map = new char[row][col];
            for (int i = 0; i < row; i++) {
                line = br.readLine();
                for (int j = 0; j < col; j++) {
                    map[i][j] = line.charAt(j);
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //マップの描画
    public void draw(Canvas canvas, int offsetX, int offsetY) {
        // オフセットを元に描画範囲を求める
        firstTileX = pixelsToTiles(-offsetX);
        lastTileX = firstTileX + pixelsToTiles(WIDTH);
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileX = Math.min(lastTileX, col);

        firstTileY = pixelsToTiles(-offsetY);
        lastTileY = firstTileY + pixelsToTiles(HEIGHT);
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileY = Math.min(lastTileY, row);

        //canvas.drawBitmap(imageBG, 0, 0, paint);//背景

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // mapの値に応じて画像を描く
                switch (map[i][j]) {
                    case 'B' : // 青ブロック
                        canvas.drawBitmap(block_B, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case 'R' : // 赤ブロック
                        canvas.drawBitmap(block_R, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case 'O' : // GOALの「GO」ブロック
                        canvas.drawBitmap(block_GO, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case 'A' : // GOALの「AL」ブロック
                        canvas.drawBitmap(block_AL, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case '0':  //空白
                        break;
                    //case 'b' :
                        //canvas.drawBitmap(boy, tilesToPixels(j), tilesToPixels(i), paint);
                        //break;
                    //case 'g' :
                        //canvas.drawBitmap(girl, tilesToPixels(j), tilesToPixels(i), paint);
                        //break;
                }
            }
        }
    }

    public int getTILE_SIZE(){
        return TILE_SIZE;
    }


    public int pixelsToTiles(double pixels) {
        return (int)Math.floor(pixels / TILE_SIZE);
    }

    public int tilesToPixels(int tiles) {
        return tiles * TILE_SIZE;
    }


}
