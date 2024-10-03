package com.muglobal.p011ld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PackageStateManager extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            File externalFilesDir = context.getExternalFilesDir("Version.json");
            if (externalFilesDir.exists()) {
                try {
                    JSONObject jSONObject = new JSONObject(readFileContent(externalFilesDir));
                    if (jSONObject.getInt("curPreDownloadedUpdateId") != 0) {
                        jSONObject.put("curPreDownloadedUpdateId", 0);
                    }
                    writeFileContent(jSONObject.toString(), externalFilesDir);
                } catch (Exception unused) {
                }
            }
        }
    }

    public String readFileContent(File file) throws Exception {
        byte[] bArr = new byte[Long.valueOf(file.length()).intValue()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bArr);
        fileInputStream.close();
        return new String(bArr, "UTF-8");
    }

    public void writeFileContent(String str, File file) throws Exception {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("");
        fileWriter.write(str);
        fileWriter.flush();
        fileWriter.close();
    }
}
