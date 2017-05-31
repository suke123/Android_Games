package com.example.shooting_sample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taka on 2017/05/02.
 */

public class Debug {
    private static boolean mode = false;
    private static Map<String, String> pr;
    private static Paint paint = new Paint();
    private Debug() {
        init();
    }
    public static void draw(Canvas canvas) {
        if (mode) return;
        if (pr == null || canvas == null) return;
        paint.setColor(Color.WHITE);
        canvas.drawText(pr.toString(), 0, 100, paint);
    }
    public static void init() {
        pr = new HashMap<>();
    }

    public static void append(String key, String value) {
        if (pr == null) init();
        pr.put(key, value);
    }
}
