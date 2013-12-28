LegacyGovernor
==============
We like Android, and we like that it evolves rapidly, but we don't like to manually handle all those
obsolete APIs, right?

For example, when I want to copy text into clipboard, I don't want to write:

```java
if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
    android.text.ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    clipboard.setText(domainUrl);
else
    android.content.ClipboardManager clipboard = (android.content.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = ClipData.newPlainText(domainUrl, domainUrl);
    clipboard.setPrimaryClip(clip);
```

I'd like to write something simple, that works on all versions of API, like this:

```java
Clipboard.copyText("some text", context);
```