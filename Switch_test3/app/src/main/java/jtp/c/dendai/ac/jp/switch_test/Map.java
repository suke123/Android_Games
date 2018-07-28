package jtp.c.dendai.ac.jp.switch_test;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by taka on 2017/10/25.
 */

public class Map{
    private final Paint paint = new Paint();
    private Context context;

    private Point vsize = GameView.VIEW_SIZE;

    public static final int TILE_SIZE = 111;
    // 重力
    public static final double GRAVITY = 1.0f;
    // マップ
    private char[][] map;

    // 行数
    private int row;
    // 列数
    private int col;
    // 幅60
    private int width ;
    // 高さ
    private int height;

    Bitmap block_B, block_R, block_GO, block_AL;

    private int firstTileX, lastTileX, firstTileY, lastTileY;

    public Map(Context context, String filename){
        this.context = context;
        loadMapFile(filename);
        setBlockBitmap();
        width = TILE_SIZE * col;
        height = TILE_SIZE * row;
    }

    private void setBlockBitmap() {
        block_B = BitmapFactory.decodeResource(context.getResources(), R.drawable.block_b);
        block_R = BitmapFactory.decodeResource(context.getResources(), R.drawable.block_r);
        block_GO = BitmapFactory.decodeResource(context.getResources(), R.drawable.go);
        block_AL = BitmapFactory.decodeResource(context.getResources(), R.drawable.al);
    }

    // マップデータ読み込み
    // そのデータをmap[行数][列数]に格納
    private void loadMapFile(String filename) {
        AssetManager assetManager = context.getResources().getAssets();
        InputStream is;
        try {
            is = assetManager.open(filename + ".dat");
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
        lastTileX = firstTileX + pixelsToTiles(vsize.x);
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileX = Math.min(lastTileX, col);

        firstTileY = pixelsToTiles(-offsetY);
        lastTileY = firstTileY + pixelsToTiles(vsize.y);
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileY = Math.min(lastTileY, row);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // mapの値に応じて画像を描く
                switch (map[i][j]) {
                    case 'B' : // ブロック
                        canvas.drawBitmap(block_B, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case 'R' : // ブロック2
                        canvas.drawBitmap(block_R, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case 'G' : // ブロック2
                        canvas.drawBitmap(block_GO, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case 'A' : // ブロック2
                        canvas.drawBitmap(block_AL, tilesToPixels(j), tilesToPixels(i), paint);
                        break;
                    case '0':
                        break;
                }
            }
        }
    }


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
                if (y < 0) {
                    return new Point(x, y);
                }
                if (y >= row) {
                    Boy.gameOver();
                    return new Point(x, y);
                }
                // ブロックがあったら衝突
                if (map[y][x] == 'B') {
                    Boy.resetOnGoal();
                    return new Point(x, y);
                }
                if (map[y][x] == 'R') {
                    Boy.resetOnGoal();

                }
                if (map[y][x] == 'G') {

                    if(fromTileY <= toTileY){
                        Boy.goal();
                    }
                    return new Point(x, y);

                }
                if (map[y][x] == 'A') {
                    if(fromTileY <= toTileY && fromTileX < toTileX){
                        Boy.goal();
                    }

                    return new Point(x, y);

                }
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
                if (y < 0) {
                    return new Point(x, y);
                }
                if (y >= row) {
                    Girl.gameOver();
                    return new Point(x, y);
                }
                // ブロックがあったら衝突
                if (map[y][x] == 'B') {
                    Girl.resetOnGoal();
                }
                if (map[y][x] == 'R') {
                    Girl.resetOnGoal();
                    return new Point(x, y);

                }
                if (map[y][x] == 'G') {

                    if(fromTileY <= toTileY){
                        Girl.goal();
                    }

                    return new Point(x, y);

                }
                if (map[y][x] == 'A') {
                    if(fromTileY <= toTileY){
                        Girl.goal();
                    }

                    return new Point(x, y);
                }
            }
        }

        return null;
    }




    public static int pixelsToTiles(double pixels) {
        return (int)Math.floor(pixels / TILE_SIZE);
    }

    public static int tilesToPixels(int tiles) {
        return tiles * TILE_SIZE;
    }

    public int getWidth2() {
        return width;
    }

    public int getHeight2() {
        return height;
    }

}
