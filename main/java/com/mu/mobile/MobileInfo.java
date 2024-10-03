package com.mu.mobile;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.mu.mobile.notchfamily.NotchAndroidP;
import com.mu.mobile.notchfamily.NotchBase;
import com.mu.mobile.notchfamily.NotchHuaWei;
import com.mu.mobile.notchfamily.NotchOppo;
import com.mu.mobile.notchfamily.NotchVivo;
import com.mu.mobile.notchfamily.NotchXiaoMi;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class MobileInfo {
    public static Activity targetAct;
    String cpuStr;

    /* loaded from: classes2.dex */
    public enum NotchType {
        NONE,
        VIVO,
        OPPO,
        XiaoMi,
        HuaWei,
        AndroidP
    }

    public void Init(Activity activity) {
        targetAct = activity;
    }

    public boolean hasLightSensorManager() {
        try {
            return ((SensorManager) targetAct.getSystemService("sensor")).getDefaultSensor(5) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public String getCpuName() {
        try {
            String[] split = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
            for (int i = 0; i < split.length; i++) {
            }
            return split[1];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readCpuInfo() {
        String str = "";
        this.cpuStr = "";
        String[] strArr = {"", ""};
        try {
            Process start = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
                if (readLine.toLowerCase().indexOf("hardware") != -1) {
                    str = readLine;
                    break;
                }
            }
            strArr[1] = Build.HARDWARE;
            bufferedReader.close();
            this.cpuStr = stringBuffer.toString().toLowerCase();
        } catch (Exception unused) {
        }
        return str;
    }

    private boolean checkIfIsPhoneCpu() {
        String readCpuInfo = readCpuInfo();
        if (readCpuInfo.contains("intel") || readCpuInfo.contains("amd")) {
            this.cpuStr = null;
            return false;
        }
        this.cpuStr = null;
        return true;
    }

    public boolean isEmulator() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        boolean z = intent.resolveActivity(targetAct.getPackageManager()) != null;
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("MuMu") || Build.MODEL.contains("virtual") || Build.SERIAL.equalsIgnoreCase("android") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion")) {
            return true;
        }
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT) || ((TelephonyManager) targetAct.getSystemService("phone")).getNetworkOperatorName().toLowerCase().equals("android") || !z || !hasLightSensorManager() || !checkIfIsPhoneCpu();
    }

    /* renamed from: com.mu.mobile.MobileInfo$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C22401 {
        static final /* synthetic */ int[] $SwitchMap$com$mu$mobile$MobileInfo$NotchType;

        static {
            int[] iArr = new int[NotchType.values().length];
            $SwitchMap$com$mu$mobile$MobileInfo$NotchType = iArr;
            try {
                iArr[NotchType.AndroidP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$mu$mobile$MobileInfo$NotchType[NotchType.VIVO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$mu$mobile$MobileInfo$NotchType[NotchType.OPPO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$mu$mobile$MobileInfo$NotchType[NotchType.XiaoMi.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$mu$mobile$MobileInfo$NotchType[NotchType.HuaWei.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static int GetNotchHeight() {
        NotchBase notchAndroidP;
        int i = C22401.$SwitchMap$com$mu$mobile$MobileInfo$NotchType[getNotchType().ordinal()];
        if (i == 1) {
            notchAndroidP = new NotchAndroidP();
        } else if (i == 2) {
            notchAndroidP = new NotchVivo();
        } else if (i == 3) {
            notchAndroidP = new NotchOppo();
        } else if (i == 4) {
            notchAndroidP = new NotchXiaoMi();
        } else {
            notchAndroidP = i != 5 ? null : new NotchHuaWei();
        }
        if (notchAndroidP == null) {
            return 0;
        }
        Log.i("Notch", "notchInfo:111111");
        return notchAndroidP._notchHeight;
    }

    private static NotchType getNotchType() {
        if (Build.VERSION.SDK_INT >= 28) {
            return NotchType.AndroidP;
        }
        String upperCase = Build.MANUFACTURER.toUpperCase();
        if (upperCase.contains("HUAWEI")) {
            return NotchType.HuaWei;
        }
        if (upperCase.contains("XIAOMI")) {
            return NotchType.XiaoMi;
        }
        if (upperCase.contains("OPPO")) {
            return NotchType.OPPO;
        }
        if (upperCase.contains("VIVO")) {
            return NotchType.VIVO;
        }
        return NotchType.NONE;
    }
}
