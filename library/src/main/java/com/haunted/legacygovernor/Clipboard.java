package com.haunted.legacygovernor;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.ClipboardManager;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

public class Clipboard {
    public static void copyText(String text, Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            copyToClipboardOld(text, context);
        else
            copyToClipboardNew(text, context);
    }

    @SuppressWarnings("deprecation")
    private static void copyToClipboardOld(String text, Context context) {
        android.text.ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setText(text);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static void copyToClipboardNew(String text, Context context) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(text, text);
        clipboard.setPrimaryClip(clip);
    }

    public static void paste(EditText tEdit) {
        Context context = tEdit.getContext();
        if (context == null)
            return;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            pasteOld(tEdit, context);
        else
            pasteNew(tEdit, context);
    }

    @SuppressWarnings("deprecation")
    public static void pasteOld(EditText tEdit, @NotNull Context context) {
        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard.hasText())
            tEdit.setText(clipboard.getText());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void pasteNew(EditText tEdit, @NotNull Context context) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (!clipboard.hasPrimaryClip())
            return;
        ClipData clipData = clipboard.getPrimaryClip();
        if (clipData == null)
            return;
        ClipData.Item item = clipData.getItemAt(0);
        if (item == null)
            return;
        CharSequence pasteStr = item.coerceToText(context);
        if (pasteStr != null && pasteStr.length() > 0)
            tEdit.setText(pasteStr);
    }
}
