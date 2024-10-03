package com.tencent.p014av.net;

import com.facebook.internal.security.CertificateUtil;
import com.tencent.p014av.utils.QLog;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class NetInfo {
    private static final int MAX_TRACE_COUNT = 30;
    private static final String PING_FROM = "from";
    private static final String PING_PAREN_THESE_CLOSE = ")";
    private static final String PING_PAREN_THESE_OPEN = "(";
    private static final String tagString = "NetInfo";

    private NetInfo() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static List<String> getDNSServers() {
        int indexOf;
        int indexOf2;
        ArrayList arrayList = new ArrayList();
        try {
            Process exec = Runtime.getRuntime().exec("/system/bin/getprop");
            InputStreamReader inputStreamReader = new InputStreamReader(exec.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            while (true) {
                try {
                    exec.waitFor();
                    break;
                } catch (InterruptedException e) {
                    QLog.m600e("GMENetDiagnoseHelper|DoSSONetDiagno", e.toString());
                }
            }
            int i = 0;
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (readLine.startsWith("[net.dns1]") || readLine.startsWith("[net.dns2]")) {
                    int indexOf3 = readLine.indexOf(58);
                    if (indexOf3 > 0 && (indexOf2 = readLine.indexOf(93, indexOf3)) > (indexOf = readLine.indexOf(91, indexOf3))) {
                        String substring = readLine.substring(indexOf + 1, indexOf2);
                        if (!isNullOrEmpty(substring)) {
                            arrayList.add(substring);
                        }
                        i++;
                        if (i >= 2) {
                            break;
                        }
                    }
                }
            }
            lineNumberReader.close();
            inputStreamReader.close();
            return arrayList;
        } catch (IOException e2) {
            QLog.m600e(tagString, e2.toString());
            return null;
        }
    }

    public static void tracerout(String str, ArrayList<String> arrayList) {
        for (int i = 1; i <= 30; i++) {
            long[] jArr = {-1, -1, -1};
            String str2 = null;
            for (int i2 = 0; i2 < 3; i2++) {
                String format = String.format("/system/bin/ping -i 0.2 -c 1 -s 0 -t %d -W 3 %s", Integer.valueOf(i), str);
                long currentTimeMillis = System.currentTimeMillis();
                String doDetect = doDetect(format);
                long currentTimeMillis2 = System.currentTimeMillis();
                if (doDetect != null) {
                    jArr[i2] = currentTimeMillis2 - currentTimeMillis;
                    str2 = doDetect;
                }
            }
            if (jArr[0] + jArr[1] + jArr[2] == -3 || isNullOrEmpty(str2)) {
                arrayList.add(String.format("trace   ttl:#%2d            **********", Integer.valueOf(i)));
            } else {
                String replace = str2.replace(" ", "");
                String format2 = String.format("trace   ttl:#%2d  %20s cost:   ", Integer.valueOf(i), replace);
                for (int i3 = 0; i3 < 3; i3++) {
                    long j = jArr[i3];
                    if (j == -1) {
                        format2 = format2 + "   *    ";
                    } else if (j <= 1) {
                        format2 = format2 + "  <1  ms ";
                    } else {
                        format2 = format2 + "  " + jArr[i3] + "ms  ";
                    }
                }
                arrayList.add(format2);
                if (replace.equals(str)) {
                    return;
                }
            }
        }
    }

    private static String doDetect(String str) {
        try {
            Process exec = Runtime.getRuntime().exec(str);
            InputStreamReader inputStreamReader = new InputStreamReader(exec.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            while (true) {
                try {
                    exec.waitFor();
                    break;
                } catch (InterruptedException e) {
                    QLog.m600e(tagString, e.toString());
                }
            }
            String str2 = null;
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine != null) {
                    String lowerCase = readLine.toLowerCase();
                    if (lowerCase.contains("from")) {
                        str2 = parseIp(lowerCase);
                    }
                } else {
                    lineNumberReader.close();
                    inputStreamReader.close();
                    return str2;
                }
            }
        } catch (IOException e2) {
            QLog.m600e("GMENetDiagnoseHelper|GenTraceResult:", e2.toString());
            return null;
        }
    }

    private static String parseIp(String str) {
        int indexOf;
        String lowerCase = str.toLowerCase();
        try {
            if (!lowerCase.contains("from")) {
                return null;
            }
            String substring = lowerCase.substring(lowerCase.indexOf("from") + 5);
            if (substring.contains(PING_PAREN_THESE_OPEN)) {
                return substring.substring(substring.indexOf(PING_PAREN_THESE_OPEN) + 1, substring.indexOf(PING_PAREN_THESE_CLOSE));
            }
            if (substring.contains(CertificateUtil.DELIMITER)) {
                indexOf = substring.indexOf(CertificateUtil.DELIMITER);
            } else {
                indexOf = substring.indexOf(" ");
            }
            return substring.substring(0, indexOf);
        } catch (Exception unused) {
            return "parseIp error";
        }
    }
}
