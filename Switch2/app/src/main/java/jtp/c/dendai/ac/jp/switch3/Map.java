package jtp.c.dendai.ac.jp.switch3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

//import java.awt.Graphics;
//import java.awt.Image;

//import javax.swing.Bitmap;

/*
 * Created on 2005/06/16
 *
 */

/**
 * @author mori
 *
 */
public  class Map extends Imageint{
    //Context con = MyApplication.getInstance().getApplicationContext();
    // タイルサイズ
    public static final int TILE_SIZE = 32;
    // 重力
    public static final double GRAVITY = 0.6;

    // マップ
    private char[][] map;

    // 行数
    private int row;
    // 列数
    private int col;
    // 幅
    private int width = 32;
    // 高さ
    private int height= 32;

    // ブロックの画像
    //   Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.block);
    Bitmap b = getBit(3);

    private final Paint paint = new Paint();

    private Bitmap bbbb;
    Bitmap blockImage;
    Bitmap blockImage2;
    //Bitmap blockImageG;
    //Bitmap blockImageO;
    //Bitmap blockImageA;
    //Bitmap blockImageL;
    Bitmap blockImageGO;
    Bitmap blockImageAL;

    private Bitmap ImageR;
    private Bitmap ImageL;
    private Bitmap ImageA;
    private Bitmap ImageC;
    private Bitmap ImageBG;

    // スプライトリスト
    private LinkedList sprites;

    //private static final int[] ids = {R.drawable.block_b,R.drawable.block_g,R.drawable.g,
    //        R.drawable.o,R.drawable.a,R.drawable.l};

    //GOALのGOとALを１つずつにしたから書き直しました。
    private static final int[] ids = {R.drawable.block_b,R.drawable.block_g,R.drawable.go,R.drawable.al};



    public Map(String filename) {
        super(ids);
        sprites = new LinkedList();
        // マップをロードする
        load(filename);

        width = TILE_SIZE * col;
        height = TILE_SIZE * row;

        // イメージをロードする
        loadImage();
        // loadImage2();


    }

    /**
     * マップを描画する
     *
     *
     * @param offsetX X方向オフセット
     * @param offsetY Y方向オフセット
     */
    public void draw(Canvas canvas, int offsetX, int offsetY) {
        // オフセットを元に描画範囲を求める
        int firstTileX = pixelsToTiles(-offsetX);
        int lastTileX = firstTileX + pixelsToTiles(MainPanel.WIDTH) + 1;
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileX = Math.min(lastTileX, col);

        int firstTileY = pixelsToTiles(-offsetY);
        int lastTileY = firstTileY + pixelsToTiles(MainPanel.HEIGHT) + 1;
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileY = Math.min(lastTileY, row);


        canvas.drawBitmap(ImageBG, 0, 0, paint);//背景


        for (int i = firstTileY; i < lastTileY; i++) {
            for (int j = firstTileX; j < lastTileX; j++) {
                // mapの値に応じて画像を描く
                switch (map[i][j]) {
                    /*
                    case 'B' : // ブロック
                        canvas.drawBitmap(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'R' : // ブロック2
                        canvas.drawBitmap(blockImage2, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'G' : // ブロック2
                        canvas.drawBitmap(blockImageG, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'O' : // ブロック2
                        canvas.drawBitmap(blockImageO, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'A' : // ブロック2
                        canvas.drawBitmap(blockImageA, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'L' : // ブロック2
                        canvas.drawBitmap(blockImageL, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;

                    */

                    case 'b' : // ブロック
                        canvas.drawBitmap(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'r' : // ブロック2
                        canvas.drawBitmap(blockImage2, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'o' : // ブロック2
                        canvas.drawBitmap(blockImageGO, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;
                    case 'a' : // ブロック2
                        canvas.drawBitmap(blockImageAL, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, paint);
                        break;

                }
            }
        }
/*
        canvas.drawBitmap(ImageL, 20, 420, null);
        canvas.drawBitmap(ImageR, 130, 420, null);
        canvas.drawBitmap(ImageA, 570, 420, null);
        canvas.drawBitmap(ImageC, 500, 420, null);
  */
    }

    /**
     * (newX, newY)で衝突するブロックの座標を返す
     * @param sprite スプライトへの参照
     * @param newX X座標
     * @param newY Y座標
     * @return 衝突するブロックの座標
     */
    public Point getTileCollision(Sprite sprite, double newX, double newY) {
        // 小数点以下切り上げ
        // 浮動小数点の関係で切り上げしないと衝突してないと判定される場合がある
        newX = Math.ceil(newX);
        newY = Math.ceil(newY);

        double fromX = Math.min(sprite.getX(), newX);
        double fromY = Math.min(sprite.getY(), newY);
        double toX = Math.max(sprite.getX(), newX);
        double toY = Math.max(sprite.getY(), newY);

        int fromTileX = pixelsToTiles(fromX);
        int fromTileY = pixelsToTiles(fromY);
        int toTileX = pixelsToTiles(toX + sprite.getWidth() - 1);
        int toTileY = pixelsToTiles(toY + sprite.getHeight() - 1);

        // 衝突しているか調べる
        for (int x = fromTileX; x <= toTileX; x++) {
            for (int y = fromTileY; y <= toTileY; y++) {
                // 画面外は衝突
                if (x < 0 || x >= col) {
                    return new Point(x, y);
                }
                if (y < 0 || y >= row) {
                    return new Point(x, y);
                }
                // ブロックがあったら衝突
                if (map[y][x] == 'b') {
                    //   int i = MainPanel.pnum;

                    return new Point(x, y);

                }
                if (map[y][x] == 'r') {
                    //   int i = MainPanel.pnum;
                 /*   switch(i){
                        case 0:
                            break;

                        case 1:
                            return new Point(x, y);

                    }
                    */
                    //     return new Point(x, y);

                }
                /*
                if (map[y][x] == 'o') {

                    return new Point(x, y);

                }*/
            }
        }

        return null;
    }



    public Point getTileCollisionR(Sprite sprite, double newX, double newY) {
        // 小数点以下切り上げ
        // 浮動小数点の関係で切り上げしないと衝突してないと判定される場合がある
        newX = Math.ceil(newX);
        newY = Math.ceil(newY);

        double fromX = Math.min(sprite.getX(), newX);
        double fromY = Math.min(sprite.getY(), newY);
        double toX = Math.max(sprite.getX(), newX);
        double toY = Math.max(sprite.getY(), newY);

        int fromTileX = pixelsToTiles(fromX);
        int fromTileY = pixelsToTiles(fromY);
        int toTileX = pixelsToTiles(toX + sprite.getWidth() - 1);
        int toTileY = pixelsToTiles(toY + sprite.getHeight() - 1);

        // 衝突しているか調べる
        for (int x = fromTileX; x <= toTileX; x++) {
            for (int y = fromTileY; y <= toTileY; y++) {
                // 画面外は衝突
                if (x < 0 || x >= col) {
                    return new Point(x, y);
                }
                if (y < 0 || y >= row) {
                    return new Point(x, y);
                }
                // ブロックがあったら衝突
                if (map[y][x] == 'b') {
                    //   int i = MainPanel.pnum;

                    //      return new Point(x, y);

                }
                if (map[y][x] == 'r') {
                    //   int i = MainPanel.pnum;
                 /*   switch(i){
                        case 0:
                            break;

                        case 1:
                            return new Point(x, y);

                    }
                    */
                    return new Point(x, y);

                }
                /*
                if (map[y][x] == 'o') {

                    return new Point(x, y);

                }*/
            }
        }

        return null;
    }



    /**
     * ピクセル単位をタイル単位に変更する
     * @param pixels ピクセル単位
     * @return タイル単位
     */
    public static int pixelsToTiles(double pixels) {
        return (int)Math.floor(pixels / TILE_SIZE);
    }

    /**
     * タイル単位をピクセル単位に変更する
     * @param tiles タイル単位
     * @return ピクセル単位
     */
    public static int tilesToPixels(int tiles) {
        return tiles * TILE_SIZE;
    }

    /**
     * イメージをロードする
     */
    private void loadImage() {
        blockImage = getBit(0); //BitmapFactory.decodeResource(getResources(), R.drawable.blockB);
        blockImage2 = getBit(1); //BitmapFactory.decodeResource(getResources(), R.drawable.blockG);
/*
        blockImageG = getBit(2);//BitmapFactory.decodeResource(getResources(), R.drawable.block);
        blockImageO = getBit(3);//BitmapFactory.decodeResource(getResources(), R.drawable.block);
        blockImageA = getBit(4);//BitmapFactory.decodeResource(getResources(), R.drawable.block);
        blockImageL = getBit(5);//BitmapFactory.decodeResource(getResources(), R.drawable.block);
*/
        blockImageGO = getBit(2);//BitmapFactory.decodeResource(getResources(), R.drawable.block);
        blockImageAL = getBit(3);//BitmapFactory.decodeResource(getResources(), R.drawable.block);
    }





    /**
     * マップをロードする
     *
     * @param filename マップファイル
     */

    private void load(String filename) {
        try {
            // ファイルを開く
            //BufferedReader br = new BufferedReader(new InputStreamReader(
            //        getClass().getResourceAsStream("/map/" + filename)));
            //AssetsフォルダにあるMapデータを開く
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getAssets().open(filename)));

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
                    /*
                    switch (map[i][j]) {
                        case 'o':  // コイン
                            //sprites.add(new Coin(tilesToPixels(j), tilesToPixels(i), "coin.gif", this,con));
                            break;
                        case 'k':  // 栗ボー
                            //sprites.add(new Kuribo(tilesToPixels(j), tilesToPixels(i), "kuribo.gif", this,con));
                            break;
                        case 'a':  // 加速アイテム
                            //sprites.add(new Accelerator(tilesToPixels(j), tilesToPixels(i), "accelerator.gif", this,con));
                            break;
                    }
                    */
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Returns the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Returns the height.
     */
    public int getHeight() {
        return height;
    }
    /**
     * @return Returns the sprites.
     */
    public LinkedList getSprites() {
        return sprites;
    }
}
