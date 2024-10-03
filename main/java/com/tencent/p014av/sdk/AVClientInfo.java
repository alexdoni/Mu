package com.tencent.p014av.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.p014av.config.Common;
import com.tencent.p014av.utils.AVDeviceHelper;
import com.tencent.p014av.utils.QLog;
import com.tencent.p014av.wrapper.GMEJavaInstance;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class AVClientInfo {
    public static final int CHIP_ARM_V5 = 1;
    public static final int CHIP_ARM_V6 = 2;
    public static final int CHIP_ARM_V7_NENO = 4;
    public static final int CHIP_ARM_V7_NO_NENO = 3;
    public static final int CHIP_ARM_V8 = 5;
    public static final int CHIP_MIPS = 6;
    public static final int CHIP_UNKNOW = 0;
    public static final int CHIP_X86 = 7;
    static final String TAG = "AVClientInfo";
    static int mChip = 1;
    static int mCoreNumber = 1;
    static int mCpuArchitecture = 5;
    static String mFeature = "";
    static String mHardware = null;
    static boolean mIsMarvell = false;
    static boolean mIsSupportSharpAudio = true;
    static boolean mIsSupportSharpVideo = true;
    static long mMaxCpuFreq = 0;
    static int mOpenGLVersion = 2;
    static String mProcessorName = "";
    static String mVendorId = null;
    public static Context m_Context = null;
    static boolean mfReadCpuInfo = false;
    static long mgMaxCpuFreq;
    static int mgNumCores;

    public static int getDeviceType() {
        return 101;
    }

    public int getCameraFacing() {
        return 2;
    }

    public static void Init(Context context) {
        m_Context = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void getCpuInfo() {
        /*
            Method dump skipped, instructions count: 523
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.sdk.AVClientInfo.getCpuInfo():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0092 A[LOOP:0: B:5:0x0010->B:25:0x0092, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0096 A[EDGE_INSN: B:26:0x0096->B:27:0x0096 BREAK  A[LOOP:0: B:5:0x0010->B:25:0x0092], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static long readMaxCpuFreq() {
        /*
            int r0 = com.tencent.p014av.sdk.AVClientInfo.mCoreNumber
            r1 = 1
            if (r0 >= r1) goto L9
            r0 = 8
            com.tencent.p014av.sdk.AVClientInfo.mCoreNumber = r0
        L9:
            r0 = 0
            r1 = 0
            r3 = 0
            r5 = r1
            r4 = r3
            r3 = r0
        L10:
            int r7 = com.tencent.p014av.sdk.AVClientInfo.mCoreNumber
            if (r4 >= r7) goto L96
            java.lang.String r7 = ""
            java.io.FileReader r8 = new java.io.FileReader     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            r9.<init>()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            java.lang.String r10 = "/sys/devices/system/cpu/cpu"
            r9.append(r10)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            r9.append(r4)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            java.lang.String r10 = "/cpufreq/cpuinfo_max_freq"
            r9.append(r10)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7b
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            r0.<init>(r8)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            java.lang.String r3 = r0.readLine()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            boolean r9 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            if (r9 != 0) goto L44
            java.lang.String r7 = r3.trim()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
        L44:
            if (r7 == 0) goto L50
            int r3 = r7.length()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            if (r3 <= 0) goto L50
            long r5 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
        L50:
            r0.close()     // Catch: java.lang.Exception -> L57
            r8.close()     // Catch: java.lang.Exception -> L57
            goto L5b
        L57:
            r3 = move-exception
            r3.printStackTrace()
        L5b:
            r3 = r0
            r0 = r8
            goto L8d
        L5e:
            r1 = move-exception
            r3 = r0
            goto L64
        L61:
            r3 = r0
            goto L66
        L63:
            r1 = move-exception
        L64:
            r0 = r8
            goto L69
        L66:
            r0 = r8
            goto L7b
        L68:
            r1 = move-exception
        L69:
            if (r3 == 0) goto L71
            r3.close()     // Catch: java.lang.Exception -> L6f
            goto L71
        L6f:
            r0 = move-exception
            goto L77
        L71:
            if (r0 == 0) goto L7a
            r0.close()     // Catch: java.lang.Exception -> L6f
            goto L7a
        L77:
            r0.printStackTrace()
        L7a:
            throw r1
        L7b:
            if (r3 == 0) goto L83
            r3.close()     // Catch: java.lang.Exception -> L81
            goto L83
        L81:
            r5 = move-exception
            goto L89
        L83:
            if (r0 == 0) goto L8c
            r0.close()     // Catch: java.lang.Exception -> L81
            goto L8c
        L89:
            r5.printStackTrace()
        L8c:
            r5 = r1
        L8d:
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 <= 0) goto L92
            goto L96
        L92:
            int r4 = r4 + 1
            goto L10
        L96:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.sdk.AVClientInfo.readMaxCpuFreq():long");
    }

    public static long getCurrentCpuFreq() {
        FileReader fileReader;
        long j = 0;
        BufferedReader bufferedReader = null;
        try {
            try {
                fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(fileReader);
                    try {
                        String readLine = bufferedReader2.readLine();
                        String trim = TextUtils.isEmpty(readLine) ? "" : readLine.trim();
                        if (trim != null && trim.length() > 0) {
                            j = Long.parseLong(trim);
                        }
                        bufferedReader2.close();
                        fileReader.close();
                    } catch (FileNotFoundException unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return j;
                    } catch (IOException unused2) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return j;
                    } catch (NumberFormatException unused3) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return j;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw th;
                            }
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        throw th;
                    }
                } catch (FileNotFoundException unused4) {
                } catch (IOException unused5) {
                } catch (NumberFormatException unused6) {
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException unused7) {
                fileReader = null;
            } catch (IOException unused8) {
                fileReader = null;
            } catch (NumberFormatException unused9) {
                fileReader = null;
            } catch (Throwable th3) {
                th = th3;
                fileReader = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j;
    }

    static int readNumCores() {
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() { // from class: com.tencent.av.sdk.AVClientInfo.1CpuFilter
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]", file.getName());
                }
            });
            if (listFiles == null) {
                return 0;
            }
            return listFiles.length;
        } catch (Exception unused) {
            return 0;
        }
    }

    static int readCpuArchitecture() {
        if (mProcessorName.contains("ARMv6")) {
            return 2;
        }
        if (Build.CPU_ABI.equalsIgnoreCase("armeabi-v7a")) {
            return 4;
        }
        if (Build.CPU_ABI.equalsIgnoreCase("armeabi")) {
            return 2;
        }
        if (mCpuArchitecture == 7 && mFeature.indexOf("neon") < 0) {
            long maxCpuFreq = getMaxCpuFreq();
            int numCores = getNumCores();
            if (maxCpuFreq < 1100000 || numCores < 2) {
                return 3;
            }
        }
        if (Build.CPU_ABI.equalsIgnoreCase("x86")) {
            return 7;
        }
        int i = mCpuArchitecture;
        if (i == 5) {
            return 1;
        }
        if (i == 6) {
            return 2;
        }
        if (i == 7) {
            return 4;
        }
        if (i == 8) {
            return 5;
        }
        String str = mVendorId;
        if (str != null) {
            return (str.equalsIgnoreCase("AuthenticAMD") || mVendorId.equalsIgnoreCase("GenuineIntel")) ? 7 : 0;
        }
        return 0;
    }

    public static boolean isLowLevelDevice() {
        if (mgNumCores == 0) {
            mgNumCores = getNumCores();
        }
        if (mgMaxCpuFreq == 0) {
            mgMaxCpuFreq = getMaxCpuFreq();
        }
        return mgNumCores <= 1 && mgMaxCpuFreq <= 1025000;
    }

    public static String getDeviceName() {
        return Build.MODEL;
    }

    public static String getDeviceNameForConfigSystem() {
        return Build.MANUFACTURER + "_" + Build.MODEL;
    }

    public static long getMaxCpuFreq() {
        getCpuInfo();
        return mMaxCpuFreq;
    }

    public static int getNumCores() {
        getCpuInfo();
        return mCoreNumber;
    }

    public static int getCpuArchitecture() {
        getCpuInfo();
        return mChip;
    }

    public static String getCPUName() {
        getCpuInfo();
        return mProcessorName;
    }

    public static String getCpuReport() {
        getCpuInfo();
        return "prcs(" + mProcessorName + ") arch(" + mCpuArchitecture + ") hard(" + mHardware + ") chip(" + mChip + ") freq(" + mMaxCpuFreq + ") num(" + mCoreNumber + ")";
    }

    public static String getModelReport() {
        getCpuInfo();
        return "model(" + Build.MODEL + ") Mnfc(" + Build.MANUFACTURER + ") dev(" + Build.VERSION.INCREMENTAL + ") sdk(" + Build.VERSION.SDK_INT + ") gl(" + mOpenGLVersion + ")";
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getAppVersion() {
        return Common.getVersion(m_Context);
    }

    public static int getOsType() {
        try {
            return Build.VERSION.RELEASE.equals("L") ? 118 : 200;
        } catch (Exception unused) {
            return 200;
        }
    }

    public static String getDeviceIdentifier() {
        try {
            m_Context = GMEJavaInstance.getmActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AVDeviceHelper.getDeviceIdentifier(m_Context).length() != 0 ? AVDeviceHelper.getDeviceIdentifier(m_Context) : "UNKOWN";
    }

    public static String getPackageName() {
        try {
            m_Context = GMEJavaInstance.getmActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "UNKOWN";
        if (m_Context == null) {
            QLog.m600e(TAG, "getPackageName context is null");
        } else {
            int myPid = Process.myPid();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) m_Context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == myPid) {
                        str = runningAppProcessInfo.processName;
                    }
                }
            }
            QLog.m600e(TAG, "getPackageName context is not null");
        }
        return str;
    }

    private static void sendBroadcast(String str, String str2) {
        if (m_Context != null) {
            Intent intent = new Intent();
            intent.setAction("com.tencent.av.floattipsview.ACTION_UPDATE_TIPS");
            intent.putExtra(str, str2);
            m_Context.sendBroadcast(intent);
        }
    }
}
