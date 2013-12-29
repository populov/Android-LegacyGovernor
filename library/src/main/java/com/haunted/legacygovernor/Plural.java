package com.haunted.legacygovernor;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import com.seppius.i18n.plurals.PluralResources;

import org.jetbrains.annotations.NotNull;

public class Plural {
    public static String getQuantityString(@NotNull Resources resources, int resourceId, int quantity, Object... formatArgs) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB)
            return resources.getQuantityString(resourceId, quantity, formatArgs);
        try {
            PluralResources pr = new PluralResources(resources);
            return pr.getQuantityString(resourceId, quantity, formatArgs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return resources.getQuantityString(resourceId, quantity, formatArgs);
        }
    }

    public static String getQuantityString(@NotNull Context context, int resourceId, int quantity, Object... formatArgs) {
        return getQuantityString(context.getResources(), resourceId, quantity, formatArgs);
    }
}
