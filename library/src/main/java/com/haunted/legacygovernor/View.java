package com.haunted.legacygovernor;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class View {
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(android.view.View view, Drawable resource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(resource);
        else
            view.setBackgroundDrawable(resource);
    }
}
