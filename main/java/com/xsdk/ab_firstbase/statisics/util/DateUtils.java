package com.xsdk.ab_firstbase.statisics.util;

import android.text.TextUtils;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes3.dex */
public class DateUtils {
    private static String[] iMillis = new String[4];
    private static String timedifference;

    public static String getStrTime_ymd_hm(String str) {
        return (TextUtils.isEmpty(str) || JsonSerializer.Null.equals(str)) ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getStrTime_ymd_hms(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getStrTime_ymd(String str) {
        return new SimpleDateFormat("yyyy.MM.dd").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getStrTime_y(String str) {
        return new SimpleDateFormat("yyyy").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getStrTime_md(String str) {
        return new SimpleDateFormat("MM-dd").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getStrTime_hm(String str) {
        return new SimpleDateFormat("HH:mm").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getStrTime_hms(String str) {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getNewsDetailsDate(String str) {
        return new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getTime() {
        long currentTimeMillis = System.currentTimeMillis();
        new SimpleDateFormat("yyyy.MM.dd");
        return String.valueOf(new Date(currentTimeMillis).getTime()).substring(0, 10);
    }

    public static String getSection(String str) {
        return new SimpleDateFormat("yyyy.MM.dd  EEEE").format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public static String getTodayDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String gettimedifference(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long time = simpleDateFormat.parse(str).getTime() - simpleDateFormat.parse(getStrTime_ymd_hms(getTime())).getTime();
            long j = time / 86400000;
            Long.signum(j);
            long j2 = time - (86400000 * j);
            long j3 = j2 / 3600000;
            long j4 = j2 - (3600000 * j3);
            long j5 = j4 / 60000;
            timedifference = "" + j + "天" + j3 + "小时" + j5 + "分" + ((j4 - ((60 * j5) * 1000)) / 1000) + "秒";
        } catch (Exception unused) {
        }
        return timedifference;
    }

    public static String differenceMillisecond2Data(String str) {
        try {
            long parseLong = Long.parseLong(str);
            long j = parseLong / 86400000;
            Long.signum(j);
            long j2 = parseLong - (86400000 * j);
            long j3 = j2 / 3600000;
            long j4 = (j2 - (3600000 * j3)) / 60000;
            if (j != 0) {
                timedifference = "" + j + "天" + j3 + "小时" + j4 + "分";
            } else if (j3 == 0) {
                timedifference = "" + j4 + "分";
            } else {
                timedifference = "" + j3 + "小时" + j4 + "分";
            }
        } catch (Exception unused) {
        }
        return timedifference;
    }

    public static String Millisecond2Data(String str) {
        try {
            long parseLong = Long.parseLong(str);
            long j = parseLong / 86400000;
            long j2 = (parseLong % 86400000) / 3600000;
            long j3 = (parseLong % 3600000) / 60000;
            if (j != 0) {
                timedifference = "" + j + "d " + j2 + "h:" + j3 + "m";
            } else if (j2 == 0) {
                timedifference = "" + j3 + "m";
            } else {
                timedifference = "" + j2 + "h:" + j3 + "m";
            }
        } catch (Exception unused) {
        }
        return timedifference;
    }
}
