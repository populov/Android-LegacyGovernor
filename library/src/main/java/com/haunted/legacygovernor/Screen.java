package com.haunted.legacygovernor;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Screen {
    private static float density;

    public static void setCanRotate(Activity activity) {
        boolean noRotate;
        Configuration configuration = activity.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2) {
            noRotate = getNoRotateNew(configuration);
        } else {
            int screenSize = configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
            int noRotateSize = Configuration.SCREENLAYOUT_SIZE_NORMAL | Configuration.SCREENLAYOUT_SIZE_SMALL;
            noRotate = (screenSize & noRotateSize) > 0;
        }
        if (noRotate)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private static boolean getNoRotateNew(Configuration configuration) {
        return configuration.smallestScreenWidthDp < 600;
    }

    public static int getScreenWidth(Context context, boolean dp) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2)
            width = getScreenWidthOld(display);
        else
            width = getScreenWidthNew(display);
        if (dp) {
            width = Math.round(width / getDensity(context));
        }
        return width;
    }

    @SuppressWarnings("deprecation")
    private static int getScreenWidthOld(Display display) {
        return display.getWidth();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private static int getScreenWidthNew(Display display) {
        int width;Point size = new Point();
        display.getSize(size);
        width =  size.x;
        return width;
    }

    public static float getDensity(Context context) {
        if (density != 0)
            return density;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        density = metrics.density;
        return density;
    }
}
