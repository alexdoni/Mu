package com.google.gson.internal.bind.util;

import io.jsonwebtoken.JwtParser;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append(JwtParser.SEPARATOR_CHAR);
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append(Matrix.MATRIX_TYPE_ZERO);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00d4 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b8, NumberFormatException -> 0x01ba, IndexOutOfBoundsException -> 0x01bc, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b8, blocks: (B:3:0x000a, B:5:0x001c, B:6:0x001e, B:8:0x002a, B:9:0x002c, B:11:0x003b, B:13:0x0041, B:18:0x0055, B:20:0x0065, B:21:0x0067, B:23:0x0073, B:24:0x0075, B:26:0x007b, B:30:0x0085, B:35:0x0095, B:37:0x009d, B:43:0x00ce, B:45:0x00d4, B:47:0x00db, B:48:0x017e, B:55:0x00ea, B:56:0x0100, B:57:0x0101, B:60:0x011d, B:62:0x012a, B:65:0x0133, B:67:0x014d, B:70:0x015c, B:71:0x0179, B:73:0x017c, B:74:0x010c, B:75:0x01b0, B:76:0x01b7, B:77:0x00b6, B:78:0x00b9), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01b0 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b8, NumberFormatException -> 0x01ba, IndexOutOfBoundsException -> 0x01bc, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b8, blocks: (B:3:0x000a, B:5:0x001c, B:6:0x001e, B:8:0x002a, B:9:0x002c, B:11:0x003b, B:13:0x0041, B:18:0x0055, B:20:0x0065, B:21:0x0067, B:23:0x0073, B:24:0x0075, B:26:0x007b, B:30:0x0085, B:35:0x0095, B:37:0x009d, B:43:0x00ce, B:45:0x00d4, B:47:0x00db, B:48:0x017e, B:55:0x00ea, B:56:0x0100, B:57:0x0101, B:60:0x011d, B:62:0x012a, B:65:0x0133, B:67:0x014d, B:70:0x015c, B:71:0x0179, B:73:0x017c, B:74:0x010c, B:75:0x01b0, B:76:0x01b7, B:77:0x00b6, B:78:0x00b9), top: B:2:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Date parse(java.lang.String r19, java.text.ParsePosition r20) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = -digit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = (i3 * 10) - digit2;
            i4 = i5;
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
