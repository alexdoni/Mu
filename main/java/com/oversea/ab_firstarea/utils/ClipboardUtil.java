package com.oversea.ab_firstarea.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.TextUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ClipboardUtil {
    public static String getClipboard(Activity activity) {
        ClipData primaryClip;
        try {
            ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
            if (clipboardManager == null || !clipboardManager.hasPrimaryClip() || (primaryClip = clipboardManager.getPrimaryClip()) == null || primaryClip.getItemCount() <= 0) {
                return "";
            }
            String charSequence = primaryClip.getItemAt(0).getText().toString();
            if (TextUtils.isEmpty(charSequence)) {
                return "";
            }
            LLog.i_noControl(charSequence + " 读取剪切板内容");
            return charSequence;
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isJson(String str) {
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            System.out.println("Invalid JSON");
            return false;
        }
    }

    public static String addJsonField(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put(str2, str3);
            return jSONObject.toString();
        } catch (JSONException unused) {
            System.out.println("Invalid JSON");
            return "";
        }
    }
}
