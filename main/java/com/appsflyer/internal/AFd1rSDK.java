package com.appsflyer.internal;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.telephony.cdma.CdmaCellLocation;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.widget.ExpandableListView;
import com.appsflyer.AFLogger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFd1rSDK extends HashMap<String, Object> {
    private static int $10 = 0;
    private static int $11 = 1;
    private static int AFInAppEventParameterName = 0;
    private static long AFKeystoreWrapper = 0;
    private static int AFLogger = 1;

    /* renamed from: e */
    private static char f190e;
    private static int registerClient;
    private static long values;
    private final Map<String, Object> AFInAppEventType;
    private final Context valueOf;

    static {
        AFInAppEventParameterName();
        AudioTrack.getMinVolume();
        Color.rgb(0, 0, 0);
        TextUtils.lastIndexOf("", '0', 0);
        TextUtils.getOffsetBefore("", 0);
        Process.myTid();
        ExpandableListView.getPackedPositionForChild(0, 0);
        KeyEvent.getModifierMetaStateMask();
        ExpandableListView.getPackedPositionGroup(0L);
        SystemClock.elapsedRealtime();
        ExpandableListView.getPackedPositionForChild(0, 0);
        KeyEvent.getMaxKeyCode();
        int i = registerClient + 41;
        AFLogger = i % 128;
        if ((i % 2 == 0 ? '7' : 'D') != '7') {
        } else {
            throw null;
        }
    }

    static void AFInAppEventParameterName() {
        AFKeystoreWrapper = 5860418862183999110L;
        AFInAppEventParameterName = 686035784;
        f190e = (char) 4936;
        values = 5462414849992894330L;
    }

    public AFd1rSDK(Map<String, Object> map, Context context) {
        this.AFInAppEventType = map;
        this.valueOf = context;
        put(AFKeystoreWrapper(), AFInAppEventType());
    }

    private static StringBuilder AFKeystoreWrapper(String... strArr) throws Exception {
        ArrayList arrayList = new ArrayList();
        int length = strArr.length;
        int i = 0;
        while (i < 3) {
            arrayList.add(Integer.valueOf(strArr[i].length()));
            i++;
            int i2 = AFLogger + 109;
            registerClient = i2 % 128;
            int i3 = i2 % 2;
        }
        Collections.sort(arrayList);
        int intValue = ((Integer) arrayList.get(0)).intValue();
        StringBuilder sb = new StringBuilder();
        int i4 = AFLogger + 71;
        registerClient = i4 % 128;
        int i5 = i4 % 2;
        int i6 = 0;
        while (true) {
            if (i6 >= intValue) {
                return sb;
            }
            int length2 = strArr.length;
            Integer num = null;
            for (int i7 = 0; i7 < 3; i7++) {
                int charAt = strArr[i7].charAt(i6);
                if (num != null) {
                    charAt ^= num.intValue();
                }
                num = Integer.valueOf(charAt);
            }
            sb.append(Integer.toHexString(num.intValue()));
            i6++;
        }
    }

    private String AFKeystoreWrapper() {
        int i = AFLogger + 59;
        registerClient = i % 128;
        int i2 = i % 2;
        try {
            String num = Integer.toString(Build.VERSION.SDK_INT);
            Map<String, Object> map = this.AFInAppEventType;
            Object[] objArr = new Object[1];
            m81a("駙灧䪕ⓧ㼵ै\ue38bﷄ퐄깘룯錻", 59833 - (ViewConfiguration.getMinimumFlingVelocity() >> 16), objArr);
            String obj = map.get(((String) objArr[0]).intern()).toString();
            Map<String, Object> map2 = this.AFInAppEventType;
            Object[] objArr2 = new Object[1];
            m81a("駚ṏ雓ཙ蟈", 34693 - (TypedValue.complexToFraction(0, 0.0f, 0.0f) > 0.0f ? 1 : (TypedValue.complexToFraction(0, 0.0f, 0.0f) == 0.0f ? 0 : -1)), objArr2);
            String obj2 = map2.get(((String) objArr2[0]).intern()).toString();
            if (obj2 == null) {
                Object[] objArr3 = new Object[1];
                m82b((char) TextUtils.getOffsetBefore("", 0), (ViewConfiguration.getGlobalActionKeyTimeout() > 0L ? 1 : (ViewConfiguration.getGlobalActionKeyTimeout() == 0L ? 0 : -1)) - 1, "㐲䕁鸌\uf5c1", "\ud898좐㤲愑", "蠂尊梁엯崗刕쿅綻", objArr3);
                obj2 = ((String) objArr3[0]).intern();
            }
            StringBuilder sb = new StringBuilder(obj);
            sb.reverse();
            StringBuilder AFKeystoreWrapper2 = AFKeystoreWrapper(num, obj2, sb.toString());
            int length = AFKeystoreWrapper2.length();
            if (!(length <= 4)) {
                int i3 = registerClient + 71;
                AFLogger = i3 % 128;
                int i4 = i3 % 2;
                AFKeystoreWrapper2.delete(4, length);
            } else {
                while (true) {
                    if ((length < 4 ? (char) 11 : '7') != 11) {
                        break;
                    }
                    length++;
                    AFKeystoreWrapper2.append('1');
                }
            }
            Object[] objArr4 = new Object[1];
            m82b((char) (62579 - TextUtils.lastIndexOf("", '0', 0)), ExpandableListView.getPackedPositionGroup(0L) + 1872098710, "㐲䕁鸌\uf5c1", "陰闵瑯\uf0f4", "ꮐ촙ᤶ", objArr4);
            AFKeystoreWrapper2.insert(0, ((String) objArr4[0]).intern());
            String obj3 = AFKeystoreWrapper2.toString();
            int i5 = AFLogger + 49;
            registerClient = i5 % 128;
            if (i5 % 2 == 0) {
                return obj3;
            }
            Object obj4 = null;
            obj4.hashCode();
            throw null;
        } catch (Exception e) {
            Object[] objArr5 = new Object[1];
            m81a("馘岴ጋ즍豂䊝㥱ﰰ늙楛⿰\ue240\ud8d5龻切\u0899켨藄硛㼧\uf581ꠍ滫║ᯙ\udea5锁䯑\u0e67쓂뭔繭㒳\ueb16ꇷ搹嫃ᆦ퐮誝", 50539 - (CdmaCellLocation.convertQuartSecToDecDegrees(0) > 0.0d ? 1 : (CdmaCellLocation.convertQuartSecToDecDegrees(0) == 0.0d ? 0 : -1)), objArr5);
            AFLogger.afErrorLogForExcManagerOnly(((String) objArr5[0]).intern(), e);
            StringBuilder sb2 = new StringBuilder();
            Object[] objArr6 = new Object[1];
            m82b((char) (Gravity.getAbsoluteGravity(0, 0) + 23730), ExpandableListView.getPackedPositionChild(0L) + 1, "㐲䕁鸌\uf5c1", "叮既늎ⱜ", "釫虡땈쐿퀁\ue92f樔ⷶႥ瞘췟㹟옋\ue632颈鐼朢먝刻蛢汛榶ಓ顲\u09bbӎ儲ᘽ휛㐌䖶辨㓁䱧ቯꦙ⬕꠲ɑ냝ꑫꡔ", objArr6);
            sb2.append(((String) objArr6[0]).intern());
            sb2.append(e);
            AFLogger.afRDLog(sb2.toString());
            Object[] objArr7 = new Object[1];
            m81a("駓瞾䔘动\u200d㹮࿓", 61026 - TextUtils.indexOf((CharSequence) "", '0'), objArr7);
            return ((String) objArr7[0]).intern();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x023d, code lost:
    
        if (r0.contains(((java.lang.String) r3[0]).intern()) != false) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x033c A[Catch: Exception -> 0x039b, TRY_LEAVE, TryCatch #0 {Exception -> 0x039b, blocks: (B:6:0x0155, B:12:0x01bb, B:19:0x01d9, B:25:0x0242, B:27:0x033c, B:37:0x0366, B:41:0x036f, B:43:0x0376, B:44:0x0379, B:39:0x037c, B:53:0x0384, B:57:0x020e, B:61:0x0184), top: B:5:0x0155 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String AFInAppEventType() {
        /*
            Method dump skipped, instructions count: 1069
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFd1rSDK.AFInAppEventType():java.lang.String");
    }

    /* loaded from: classes.dex */
    public static class AFa1tSDK {
        static byte[] AFInAppEventType(String str) throws Exception {
            return str.getBytes(Charset.defaultCharset());
        }

        static byte[] AFInAppEventParameterName(byte[] bArr) throws Exception {
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (bArr[i] ^ ((i % 2) + 42));
            }
            return bArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v6, types: [char[]] */
    /* renamed from: a */
    private static void m81a(String str, int i, Object[] objArr) {
        int i2;
        if (!(str == 0)) {
            int i3 = $10 + 75;
            $11 = i3 % 128;
            if ((i3 % 2 == 0 ? (char) 29 : '4') == 29) {
                str.toCharArray();
                Object obj = null;
                obj.hashCode();
                throw null;
            }
            str = str.toCharArray();
            int i4 = $10 + 89;
            $11 = i4 % 128;
            int i5 = i4 % 2;
        }
        char[] cArr = (char[]) str;
        AFj1iSDK aFj1iSDK = new AFj1iSDK();
        aFj1iSDK.values = i;
        int length = cArr.length;
        long[] jArr = new long[length];
        aFj1iSDK.AFInAppEventType = 0;
        while (aFj1iSDK.AFInAppEventType < cArr.length) {
            jArr[aFj1iSDK.AFInAppEventType] = (cArr[aFj1iSDK.AFInAppEventType] ^ (aFj1iSDK.AFInAppEventType * aFj1iSDK.values)) ^ (AFKeystoreWrapper ^ 3448363977863888702L);
            aFj1iSDK.AFInAppEventType++;
        }
        char[] cArr2 = new char[length];
        aFj1iSDK.AFInAppEventType = 0;
        while (true) {
            if ((aFj1iSDK.AFInAppEventType < cArr.length ? 'B' : (char) 1) == 1) {
                objArr[0] = new String(cArr2);
                return;
            }
            int i6 = $11 + 77;
            $10 = i6 % 128;
            if (i6 % 2 != 0) {
                cArr2[aFj1iSDK.AFInAppEventType] = (char) jArr[aFj1iSDK.AFInAppEventType];
                i2 = aFj1iSDK.AFInAppEventType << 0;
            } else {
                cArr2[aFj1iSDK.AFInAppEventType] = (char) jArr[aFj1iSDK.AFInAppEventType];
                i2 = aFj1iSDK.AFInAppEventType + 1;
            }
            aFj1iSDK.AFInAppEventType = i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    private static void m82b(char c, int i, String str, String str2, String str3, Object[] objArr) {
        char[] cArr;
        char[] cArr2;
        int i2 = $10 + 59;
        int i3 = i2 % 128;
        $11 = i3;
        int i4 = i2 % 2;
        if (str3 != 0) {
            int i5 = i3 + 119;
            $10 = i5 % 128;
            int i6 = i5 % 2;
            cArr = str3.toCharArray();
        } else {
            cArr = str3;
        }
        char[] cArr3 = cArr;
        char[] charArray = str2 == null ? str2 : str2.toCharArray();
        if ((str != null ? (char) 22 : ';') != ';') {
            int i7 = $11 + 51;
            $10 = i7 % 128;
            int i8 = i7 % 2;
            cArr2 = str.toCharArray();
        } else {
            cArr2 = str;
        }
        char[] cArr4 = cArr2;
        AFj1jSDK aFj1jSDK = new AFj1jSDK();
        int length = charArray.length;
        char[] cArr5 = new char[length];
        int length2 = cArr4.length;
        char[] cArr6 = new char[length2];
        System.arraycopy(charArray, 0, cArr5, 0, length);
        System.arraycopy(cArr4, 0, cArr6, 0, length2);
        cArr5[0] = (char) (cArr5[0] ^ c);
        cArr6[2] = (char) (cArr6[2] + ((char) i));
        int length3 = cArr3.length;
        char[] cArr7 = new char[length3];
        aFj1jSDK.AFKeystoreWrapper = 0;
        while (aFj1jSDK.AFKeystoreWrapper < length3) {
            int i9 = (aFj1jSDK.AFKeystoreWrapper + 2) % 4;
            int i10 = (aFj1jSDK.AFKeystoreWrapper + 3) % 4;
            aFj1jSDK.valueOf = (char) (((cArr5[aFj1jSDK.AFKeystoreWrapper % 4] * 32718) + cArr6[i9]) % 65535);
            cArr6[i10] = (char) (((cArr5[i10] * 32718) + cArr6[i9]) / 65535);
            cArr5[i10] = aFj1jSDK.valueOf;
            cArr7[aFj1jSDK.AFKeystoreWrapper] = (char) ((((cArr5[i10] ^ cArr3[aFj1jSDK.AFKeystoreWrapper]) ^ (values ^ (-4751302755855625400L))) ^ ((int) (AFInAppEventParameterName ^ (-4751302755855625400L)))) ^ ((char) (f190e ^ (-4751302755855625400L))));
            aFj1jSDK.AFKeystoreWrapper++;
        }
        String str4 = new String(cArr7);
        int i11 = $11 + 1;
        $10 = i11 % 128;
        int i12 = i11 % 2;
        objArr[0] = str4;
    }
}
