package com.appsflyer.internal;

import io.jsonwebtoken.JwtParser;
import java.util.Map;
import kotlin.text.Typography;
import org.spongycastle.i18n.LocalizedMessage;

/* loaded from: classes.dex */
public class AFc1iSDK {
    public static final byte[] $$a = null;
    public static final int $$b = 0;
    private static int $10 = 0;
    private static int $11 = 1;
    private static byte[] AFLogger$LogLevel;
    private static byte[] AFVersionDeclaration;
    private static int AppsFlyer2dXConversionCallback;
    public static final Map<Integer, Object> afErrorLog;
    private static Object afErrorLogForExcManagerOnly;
    private static final Map<String, Object> afLogForce;
    private static Object getLevel;
    private static int onAppOpenAttributionNative;

    private static String $$c(byte b, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = $11 + 121;
        int i7 = i6 % 128;
        $10 = i7;
        int i8 = i6 % 2;
        int i9 = ((b | 1) << 1) - (b ^ 1);
        byte[] bArr = $$a;
        int i10 = (i ^ 4) + ((i & 4) << 1);
        int i11 = -i2;
        int i12 = (i11 ^ 119) + ((i11 & 119) << 1);
        byte[] bArr2 = new byte[i9];
        int i13 = i9 - 1;
        if (bArr != null) {
            i4 = 0;
            i5 = i12;
            i3 = i13;
        } else {
            int i14 = (i7 & 115) + (i7 | 115);
            $11 = i14 % 128;
            int i15 = i14 % 2;
            i3 = i13;
            i4 = 0;
            int i16 = ((i13 | i10) << 1) - (i13 ^ i10);
            i5 = (i16 ^ (-2)) + ((i16 & (-2)) << 1);
            i10 = i10;
        }
        while (true) {
            bArr2[i4] = (byte) i5;
            int i17 = (i4 & 1) + (i4 | 1);
            int i18 = (i10 + 2) - 1;
            if (i4 == i3) {
                break;
            }
            byte b2 = bArr[i18];
            int i19 = $11 + 91;
            $10 = i19 % 128;
            int i20 = i19 % 2;
            i4 = i17;
            int i21 = ((i5 | b2) << 1) - (i5 ^ b2);
            i5 = (i21 ^ (-2)) + ((i21 & (-2)) << 1);
            i10 = i18;
        }
        String str = new String(bArr2, 0);
        int i22 = $11 + 125;
        $10 = i22 % 128;
        if ((i22 % 2 != 0 ? Typography.amp : '.') == '.') {
            return str;
        }
        throw null;
    }

    public static Object AFInAppEventParameterName(int i, char c, int i2) {
        int i3 = $10 + 107;
        $11 = i3 % 128;
        if (i3 % 2 == 0) {
            throw null;
        }
        Object obj = afErrorLogForExcManagerOnly;
        try {
            Object[] objArr = {Integer.valueOf(i), Character.valueOf(c), Integer.valueOf(i2)};
            byte[] bArr = $$a;
            byte b = bArr[281];
            Class<?> cls = Class.forName($$c(b, (short) ((b ^ 85) | (b & 85)), bArr[70]), true, (ClassLoader) getLevel);
            byte b2 = bArr[89];
            Object invoke = cls.getMethod($$c(b2, (short) ((b2 ^ 591) | (b2 & 591)), bArr[4]), Integer.TYPE, Character.TYPE, Integer.TYPE).invoke(obj, objArr);
            int i4 = $11 + 115;
            $10 = i4 % 128;
            int i5 = i4 % 2;
            return invoke;
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static int AFInAppEventType(Object obj) {
        int i = $10;
        int i2 = ((i | 29) << 1) - (i ^ 29);
        $11 = i2 % 128;
        if ((i2 % 2 == 0 ? JwtParser.SEPARATOR_CHAR : 'S') != 'S') {
            Object obj2 = null;
            obj2.hashCode();
            throw null;
        }
        Object obj3 = afErrorLogForExcManagerOnly;
        int i3 = ((i | 21) << 1) - (i ^ 21);
        $11 = i3 % 128;
        int i4 = i3 % 2;
        try {
            Object[] objArr = {obj};
            byte[] bArr = $$a;
            byte b = bArr[281];
            Class<?> cls = Class.forName($$c(b, (short) ((b ^ 85) | (b & 85)), bArr[70]), true, (ClassLoader) getLevel);
            byte b2 = bArr[89];
            return ((Integer) cls.getMethod($$c(b2, (short) (b2 | 591), bArr[4]), Object.class).invoke(obj3, objArr)).intValue();
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void init$0() {
        int i;
        int i2 = $11;
        int i3 = (i2 & 59) + (i2 | 59);
        $10 = i3 % 128;
        if (!(i3 % 2 != 0)) {
            byte[] bArr = new byte[1026];
            System.arraycopy("H;pû6\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ7Ëù\u0017íÏ=\bÁ\u00165ó\u0002\u0001\u000fõ\u0001ç'\u0004\u0007\u0001á#\u0000õþ\u000eù\u0017íÏ@÷\u000fûÉ'(ü\u0003ó\n\u0014ÿ\u0002õ\u000b\bÏ1\u0002þÿü\u0000\u0015÷\b\u0001\u000fø\u0010ÿüýÌC\u0006½$$ÿö\u0004\u0010\u0002ù\u0017íÏBý\bÁ\u00169ýóß5ó\u0002\u0001\u000f\u000e\u0000Ã5\u0011\u0002\u0005õ\b\u000fî\u000f¾=\u0007\bó\u000fþõ\rÄ\u0015\u0007\u001fÐ7ïó\tÛ\u0002\u000fø\u0010ÿüýÌC\u0006½\u00165ö\u0005úÂ4ñ\u000f\u0003ø\b\u0001ù\u0017íÏ=\bÁ\u00169ýóÞ3\u0002ñ\u001aÒ'\u0004\u0007\u0001á#\u0000õþ\u000eÿ\r\fõ\u0004ÅG\bü\u0003ó\nÃ\u001d'á\u001a\n\u0004\u0005\tÍ#\u0015Í+÷\u0014\u0002Û%ô\u0005\u0003\u000fõþ\u0005ä1ù\u0002\u000f\u0003\u0005ýö\rÿ\u0013íê\u001a\u0011ïô#ï\u0015ó\u0000\u0011Ô%\u0005û\u0010Ó'\u000býù\ró\u0000\u0011Ñ \u0004\u0007ÿá'\u000bõþ\u0005ÿ\r\fõ\u0004ÅG\bü\u0003ó\nÃ\u0018#\u0015Ð%\u0005û\u000e÷\u0003ü\u0006\u0000\u0004\u0007ÿÿ\r\fõ\u0004ÅG\bü\u0003ó\nÃ\u0016!\u0014ôá#\u0015Í+÷\u0014\u0002Û%ô\u0005\u0003\u000f\u0000\u0011Ñ.÷\u0003à \u0004\u0007ÿá'\u000b\b÷÷í\të\nH\u0003³Hý\r\u0002ø\u0001\u0004\n\n¯Nû\u0003\u0010·í\bì\ní\u0006î\ní\nê\nù\u0017íÏ@÷\u000fûÉ\u0017+÷\u0014\u0002Û%ô\u0005\u0003\u000f\u000fø\u0010ÿüýÌ5\u0011\u0002À\u00151\u0002Ù'\u0005õ\u0001\r\t\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ\u0014\u0007\u001fÐ7ïó\tÛ\u0002ô\u0011à\u0015þ\u0005í!\u0000\u000fù\u0017íÏ=\bÁ\u0018\u001f\u0015ïê'\u0004\u0007\u0001á#\u0000õþ\u000e\u0005ô\u0005â#\u0015\u000fø\u0010ÿüýÌJõþ\u0014¹\u001f\u001c\u0016Î3ñ\u000b\bù\u0017íÏ=\bÁ\u00169ýóÞ3\u0002ñ\u001aØ(\u0001þ\u0007\u0001á#\u0000õþ\u000e\u0000\u0011Ó)÷\u0011\u0005ô\u0001á/\u0007á\u0017\u0006úú\u0000\t\u0000\u0011Ñ+÷\u0014\u0002Û%ô\u0005\u0003\u000f7ÿ\u0015ïÐ7ÿ\u0015ïÐ\u0003\tû\u0011ó\u0000\u0011Ô\u0000\u0007\u0007\u001c\u0016ü\u0003ý\u0005õô\u001dñ\u0011\u0002÷\u000fó\t\u0016ì\u0007\bõ\u0015÷\u000fñé\u001fû\u0012ñ\u0013\tÙ\u0013\u0015öß)û\nú\u000b\b\u0001\u000b\u0005\u0006ô\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ\u0014\u0007\u001fÐ:ìó\tÛQ6\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ6Ìù\u0017íÏ=\bÁHû\u0005ö\u0007\u000b\u0000\u0011Þ$ÿ\u0003÷\u000f\u0004\u0007\u0000\u0011Ï#\u0013þÿ\u000b\u0001óí\u0013\u0015ö\u000fø\u0010ÿüýÌ5\u0011\u0002À\u0015$\u0013÷\u000fõ\r\u0007Ý\u0016\fõþ\u0005í\r\u000bòì\u0019ù\u0017íÏ=\bÁ\u001a%\u0005ûì(\u0001þ\u0007\u0001á#\u0000õþ\u000e\u0000\u000fë\u001dù\róù\u0017íÏ=\bÁ\u001d\bø5í\u0004\r\u0006÷\b\u0001ó\u0015öæ\u001f\f\u0003\u0000\u0011×'ý\r÷ú\r×1\u0002þÿü\u0000\u0015÷\b\u0001ù\u0017íÏ=\bÁ\u001a%\u0005û\u000e\u0000\u0005þû\u0015Þ\u001dÙ.ñ\u0004\u0013÷\b\u0001í\u0005ï\n\u000fø\u0010ÿüýÌI\u0001÷\u0005Ä('ù÷\u0001ô\r\r\u000bò\u0014ÿ\u0002õ\u000b\bâ\u0017\u0006úê\u001e\u0005\u0002ÿ\fù\u0017íÏ=\bÁ\u001a%\u0005ûá#\u0010ò\u0011ù\t\u0006ý\u0005ýÕ9ýóÞ3\u0002ñ\u001a\u0005ô\u0005á+÷\u0014\u0002\u000e\u0000Ã5\u0011\u0002\u0005õ\b\u000fî\u000fýñ\u0011õ\u0015÷\u000fñé\u001fû\u0012ñ\u0013\tÍ'\u000bõ\u0000\u0013ý\u0005ùþ\u0010ù\u0017íÏ=\bÁ\u001d'\u0004\u0007\u0001á#\u0000õþ\u000e".getBytes(LocalizedMessage.DEFAULT_ENCODING), 0, bArr, 0, 1026);
            $$a = bArr;
            i = 217;
        } else {
            byte[] bArr2 = new byte[1026];
            System.arraycopy("H;pû6\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ7Ëù\u0017íÏ=\bÁ\u00165ó\u0002\u0001\u000fõ\u0001ç'\u0004\u0007\u0001á#\u0000õþ\u000eù\u0017íÏ@÷\u000fûÉ'(ü\u0003ó\n\u0014ÿ\u0002õ\u000b\bÏ1\u0002þÿü\u0000\u0015÷\b\u0001\u000fø\u0010ÿüýÌC\u0006½$$ÿö\u0004\u0010\u0002ù\u0017íÏBý\bÁ\u00169ýóß5ó\u0002\u0001\u000f\u000e\u0000Ã5\u0011\u0002\u0005õ\b\u000fî\u000f¾=\u0007\bó\u000fþõ\rÄ\u0015\u0007\u001fÐ7ïó\tÛ\u0002\u000fø\u0010ÿüýÌC\u0006½\u00165ö\u0005úÂ4ñ\u000f\u0003ø\b\u0001ù\u0017íÏ=\bÁ\u00169ýóÞ3\u0002ñ\u001aÒ'\u0004\u0007\u0001á#\u0000õþ\u000eÿ\r\fõ\u0004ÅG\bü\u0003ó\nÃ\u001d'á\u001a\n\u0004\u0005\tÍ#\u0015Í+÷\u0014\u0002Û%ô\u0005\u0003\u000fõþ\u0005ä1ù\u0002\u000f\u0003\u0005ýö\rÿ\u0013íê\u001a\u0011ïô#ï\u0015ó\u0000\u0011Ô%\u0005û\u0010Ó'\u000býù\ró\u0000\u0011Ñ \u0004\u0007ÿá'\u000bõþ\u0005ÿ\r\fõ\u0004ÅG\bü\u0003ó\nÃ\u0018#\u0015Ð%\u0005û\u000e÷\u0003ü\u0006\u0000\u0004\u0007ÿÿ\r\fõ\u0004ÅG\bü\u0003ó\nÃ\u0016!\u0014ôá#\u0015Í+÷\u0014\u0002Û%ô\u0005\u0003\u000f\u0000\u0011Ñ.÷\u0003à \u0004\u0007ÿá'\u000b\b÷÷í\të\nH\u0003³Hý\r\u0002ø\u0001\u0004\n\n¯Nû\u0003\u0010·í\bì\ní\u0006î\ní\nê\nù\u0017íÏ@÷\u000fûÉ\u0017+÷\u0014\u0002Û%ô\u0005\u0003\u000f\u000fø\u0010ÿüýÌ5\u0011\u0002À\u00151\u0002Ù'\u0005õ\u0001\r\t\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ\u0014\u0007\u001fÐ7ïó\tÛ\u0002ô\u0011à\u0015þ\u0005í!\u0000\u000fù\u0017íÏ=\bÁ\u0018\u001f\u0015ïê'\u0004\u0007\u0001á#\u0000õþ\u000e\u0005ô\u0005â#\u0015\u000fø\u0010ÿüýÌJõþ\u0014¹\u001f\u001c\u0016Î3ñ\u000b\bù\u0017íÏ=\bÁ\u00169ýóÞ3\u0002ñ\u001aØ(\u0001þ\u0007\u0001á#\u0000õþ\u000e\u0000\u0011Ó)÷\u0011\u0005ô\u0001á/\u0007á\u0017\u0006úú\u0000\t\u0000\u0011Ñ+÷\u0014\u0002Û%ô\u0005\u0003\u000f7ÿ\u0015ïÐ7ÿ\u0015ïÐ\u0003\tû\u0011ó\u0000\u0011Ô\u0000\u0007\u0007\u001c\u0016ü\u0003ý\u0005õô\u001dñ\u0011\u0002÷\u000fó\t\u0016ì\u0007\bõ\u0015÷\u000fñé\u001fû\u0012ñ\u0013\tÙ\u0013\u0015öß)û\nú\u000b\b\u0001\u000b\u0005\u0006ô\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ\u0014\u0007\u001fÐ:ìó\tÛQ6\u000e\u0000Ä4\u0011\u0002\u0005õ\b\u000fî\u000f¿<\u0007\bó\u000fþõ\rÅ6Ìù\u0017íÏ=\bÁHû\u0005ö\u0007\u000b\u0000\u0011Þ$ÿ\u0003÷\u000f\u0004\u0007\u0000\u0011Ï#\u0013þÿ\u000b\u0001óí\u0013\u0015ö\u000fø\u0010ÿüýÌ5\u0011\u0002À\u0015$\u0013÷\u000fõ\r\u0007Ý\u0016\fõþ\u0005í\r\u000bòì\u0019ù\u0017íÏ=\bÁ\u001a%\u0005ûì(\u0001þ\u0007\u0001á#\u0000õþ\u000e\u0000\u000fë\u001dù\róù\u0017íÏ=\bÁ\u001d\bø5í\u0004\r\u0006÷\b\u0001ó\u0015öæ\u001f\f\u0003\u0000\u0011×'ý\r÷ú\r×1\u0002þÿü\u0000\u0015÷\b\u0001ù\u0017íÏ=\bÁ\u001a%\u0005û\u000e\u0000\u0005þû\u0015Þ\u001dÙ.ñ\u0004\u0013÷\b\u0001í\u0005ï\n\u000fø\u0010ÿüýÌI\u0001÷\u0005Ä('ù÷\u0001ô\r\r\u000bò\u0014ÿ\u0002õ\u000b\bâ\u0017\u0006úê\u001e\u0005\u0002ÿ\fù\u0017íÏ=\bÁ\u001a%\u0005ûá#\u0010ò\u0011ù\t\u0006ý\u0005ýÕ9ýóÞ3\u0002ñ\u001a\u0005ô\u0005á+÷\u0014\u0002\u000e\u0000Ã5\u0011\u0002\u0005õ\b\u000fî\u000fýñ\u0011õ\u0015÷\u000fñé\u001fû\u0012ñ\u0013\tÍ'\u000bõ\u0000\u0013ý\u0005ùþ\u0010ù\u0017íÏ=\bÁ\u001d'\u0004\u0007\u0001á#\u0000õþ\u000e".getBytes(LocalizedMessage.DEFAULT_ENCODING), 0, bArr2, 0, 1026);
            $$a = bArr2;
            i = 9552;
        }
        $$b = i;
    }

    public static int valueOf(int i) {
        int i2 = ($11 + 36) - 1;
        int i3 = i2 % 128;
        $10 = i3;
        int i4 = i2 % 2;
        Object obj = afErrorLogForExcManagerOnly;
        int i5 = i3 + 47;
        int i6 = i5 % 128;
        $11 = i6;
        int i7 = i5 % 2;
        int i8 = ((i6 | 109) << 1) - (i6 ^ 109);
        $10 = i8 % 128;
        int i9 = i8 % 2;
        try {
            Object[] objArr = {Integer.valueOf(i)};
            byte[] bArr = $$a;
            byte b = bArr[281];
            Class<?> cls = Class.forName($$c(b, (short) ((b ^ 85) | (b & 85)), bArr[70]), true, (ClassLoader) getLevel);
            byte b2 = bArr[89];
            return ((Integer) cls.getMethod($$c(b2, (short) ((b2 ^ 591) | (b2 & 591)), bArr[4]), Integer.TYPE).invoke(obj, objArr)).intValue();
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    private AFc1iSDK() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(35:(35:1225|1226|(0)(0)|20|(0)|1223|(0)|25|(0)(0)|28|(0)|43|(0)(0)|(0)(0)|48|(0)|63|64|65|66|67|(0)(0)|70|(0)|1195|75|76|(0)(0)|79|(0)(0)|82|83|(0)|1189|1190)|17|(0)(0)|20|(0)|1223|(0)|25|(0)(0)|28|(0)|43|(0)(0)|(0)(0)|48|(0)|63|64|65|66|67|(0)(0)|70|(0)|1195|75|76|(0)(0)|79|(0)(0)|82|83|(0)|1189|1190) */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x03d9, code lost:
    
        if (((java.lang.Boolean) java.lang.Class.forName($$c(r11[74], r7, r11[25])).getMethod($$c(r11[19], (short) 805, r11[70]), null).invoke(r12, null)).booleanValue() != false) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1198:0x034b, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00a4, code lost:
    
        if ((r2 == null) != false) goto L1115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x1a02, code lost:
    
        r1 = r31[r8];
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x1a05, code lost:
    
        throw null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1192:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:1193:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:1196:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:1199:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:1200:0x01bb A[Catch: Exception -> 0x1ace, TRY_LEAVE, TryCatch #59 {Exception -> 0x1ace, blocks: (B:3:0x001d, B:9:0x0046, B:32:0x0168, B:38:0x01ae, B:40:0x01b4, B:42:0x01b5, B:52:0x0238, B:59:0x0285, B:61:0x028b, B:62:0x028c, B:63:0x028d, B:66:0x02f7, B:76:0x0336, B:79:0x033f, B:82:0x0348, B:91:0x0372, B:240:0x19ec, B:250:0x1a13, B:252:0x1a97, B:254:0x1a21, B:262:0x1a6b, B:264:0x1a71, B:265:0x1a72, B:242:0x19f2, B:268:0x1a02, B:271:0x1a05, B:1200:0x01bb, B:1207:0x1abb, B:1209:0x1ac2, B:1210:0x1ac3, B:1213:0x1ac5, B:1215:0x1acc, B:1216:0x1acd, B:1236:0x0056, B:257:0x1a36, B:258:0x1a68, B:55:0x024c, B:34:0x0188, B:1204:0x01fe, B:1202:0x01c8), top: B:2:0x001d, inners: #42, #61, #80, #85, #89 }] */
    /* JADX WARN: Removed duplicated region for block: B:1217:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:1218:0x0130 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1224:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:1225:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x19da  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x19df  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x1a0c  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x1a12  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x1a21 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:266:0x1a0e  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x1a09 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x19dc  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x1467  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x15ac  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x15f2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x15b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:472:0x15ae  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x1559  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x1439 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:731:0x145a  */
    /* JADX WARN: Removed duplicated region for block: B:734:0x1463 A[Catch: all -> 0x1242, TRY_ENTER, TRY_LEAVE, TryCatch #18 {all -> 0x1242, blocks: (B:650:0x123a, B:652:0x1240, B:653:0x1241, B:772:0x12fd, B:774:0x1303, B:775:0x1304, B:700:0x1439, B:734:0x1463, B:780:0x12c2, B:782:0x12c8, B:783:0x12c9), top: B:699:0x1439 }] */
    /* JADX WARN: Removed duplicated region for block: B:735:0x145d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:887:0x18cb A[Catch: all -> 0x19d1, TryCatch #94 {all -> 0x19d1, blocks: (B:710:0x179a, B:719:0x17e9, B:721:0x17ef, B:722:0x17f0, B:744:0x17fa, B:746:0x1807, B:747:0x1808, B:761:0x181f, B:763:0x1826, B:764:0x1827, B:787:0x1833, B:789:0x1841, B:790:0x1842, B:800:0x1848, B:802:0x1857, B:803:0x1858, B:809:0x185a, B:811:0x186b, B:812:0x186c, B:815:0x186e, B:817:0x187f, B:818:0x1880, B:828:0x1884, B:830:0x1897, B:831:0x1898, B:885:0x18c4, B:887:0x18cb, B:888:0x18cc, B:906:0x18fa, B:908:0x1904, B:909:0x1905, B:925:0x192d, B:927:0x1934, B:928:0x1935, B:938:0x194e, B:940:0x1958, B:941:0x1959, B:947:0x195b, B:949:0x1970, B:950:0x1971, B:953:0x1973, B:955:0x1988, B:956:0x1989, B:1177:0x19af, B:1178:0x19d0, B:663:0x1262, B:616:0x1151, B:122:0x08dd, B:613:0x1113, B:119:0x08a0, B:160:0x0b8d, B:713:0x17b4, B:714:0x17e6), top: B:662:0x1262, inners: #7, #30, #32, #44, #48, #60, #104 }] */
    /* JADX WARN: Removed duplicated region for block: B:888:0x18cc A[Catch: all -> 0x19d1, TryCatch #94 {all -> 0x19d1, blocks: (B:710:0x179a, B:719:0x17e9, B:721:0x17ef, B:722:0x17f0, B:744:0x17fa, B:746:0x1807, B:747:0x1808, B:761:0x181f, B:763:0x1826, B:764:0x1827, B:787:0x1833, B:789:0x1841, B:790:0x1842, B:800:0x1848, B:802:0x1857, B:803:0x1858, B:809:0x185a, B:811:0x186b, B:812:0x186c, B:815:0x186e, B:817:0x187f, B:818:0x1880, B:828:0x1884, B:830:0x1897, B:831:0x1898, B:885:0x18c4, B:887:0x18cb, B:888:0x18cc, B:906:0x18fa, B:908:0x1904, B:909:0x1905, B:925:0x192d, B:927:0x1934, B:928:0x1935, B:938:0x194e, B:940:0x1958, B:941:0x1959, B:947:0x195b, B:949:0x1970, B:950:0x1971, B:953:0x1973, B:955:0x1988, B:956:0x1989, B:1177:0x19af, B:1178:0x19d0, B:663:0x1262, B:616:0x1151, B:122:0x08dd, B:613:0x1113, B:119:0x08a0, B:160:0x0b8d, B:713:0x17b4, B:714:0x17e6), top: B:662:0x1262, inners: #7, #30, #32, #44, #48, #60, #104 }] */
    /* JADX WARN: Removed duplicated region for block: B:908:0x1904 A[Catch: all -> 0x19d1, TryCatch #94 {all -> 0x19d1, blocks: (B:710:0x179a, B:719:0x17e9, B:721:0x17ef, B:722:0x17f0, B:744:0x17fa, B:746:0x1807, B:747:0x1808, B:761:0x181f, B:763:0x1826, B:764:0x1827, B:787:0x1833, B:789:0x1841, B:790:0x1842, B:800:0x1848, B:802:0x1857, B:803:0x1858, B:809:0x185a, B:811:0x186b, B:812:0x186c, B:815:0x186e, B:817:0x187f, B:818:0x1880, B:828:0x1884, B:830:0x1897, B:831:0x1898, B:885:0x18c4, B:887:0x18cb, B:888:0x18cc, B:906:0x18fa, B:908:0x1904, B:909:0x1905, B:925:0x192d, B:927:0x1934, B:928:0x1935, B:938:0x194e, B:940:0x1958, B:941:0x1959, B:947:0x195b, B:949:0x1970, B:950:0x1971, B:953:0x1973, B:955:0x1988, B:956:0x1989, B:1177:0x19af, B:1178:0x19d0, B:663:0x1262, B:616:0x1151, B:122:0x08dd, B:613:0x1113, B:119:0x08a0, B:160:0x0b8d, B:713:0x17b4, B:714:0x17e6), top: B:662:0x1262, inners: #7, #30, #32, #44, #48, #60, #104 }] */
    /* JADX WARN: Removed duplicated region for block: B:909:0x1905 A[Catch: all -> 0x19d1, TryCatch #94 {all -> 0x19d1, blocks: (B:710:0x179a, B:719:0x17e9, B:721:0x17ef, B:722:0x17f0, B:744:0x17fa, B:746:0x1807, B:747:0x1808, B:761:0x181f, B:763:0x1826, B:764:0x1827, B:787:0x1833, B:789:0x1841, B:790:0x1842, B:800:0x1848, B:802:0x1857, B:803:0x1858, B:809:0x185a, B:811:0x186b, B:812:0x186c, B:815:0x186e, B:817:0x187f, B:818:0x1880, B:828:0x1884, B:830:0x1897, B:831:0x1898, B:885:0x18c4, B:887:0x18cb, B:888:0x18cc, B:906:0x18fa, B:908:0x1904, B:909:0x1905, B:925:0x192d, B:927:0x1934, B:928:0x1935, B:938:0x194e, B:940:0x1958, B:941:0x1959, B:947:0x195b, B:949:0x1970, B:950:0x1971, B:953:0x1973, B:955:0x1988, B:956:0x1989, B:1177:0x19af, B:1178:0x19d0, B:663:0x1262, B:616:0x1151, B:122:0x08dd, B:613:0x1113, B:119:0x08a0, B:160:0x0b8d, B:713:0x17b4, B:714:0x17e6), top: B:662:0x1262, inners: #7, #30, #32, #44, #48, #60, #104 }] */
    /* JADX WARN: Removed duplicated region for block: B:927:0x1934 A[Catch: all -> 0x19d1, TryCatch #94 {all -> 0x19d1, blocks: (B:710:0x179a, B:719:0x17e9, B:721:0x17ef, B:722:0x17f0, B:744:0x17fa, B:746:0x1807, B:747:0x1808, B:761:0x181f, B:763:0x1826, B:764:0x1827, B:787:0x1833, B:789:0x1841, B:790:0x1842, B:800:0x1848, B:802:0x1857, B:803:0x1858, B:809:0x185a, B:811:0x186b, B:812:0x186c, B:815:0x186e, B:817:0x187f, B:818:0x1880, B:828:0x1884, B:830:0x1897, B:831:0x1898, B:885:0x18c4, B:887:0x18cb, B:888:0x18cc, B:906:0x18fa, B:908:0x1904, B:909:0x1905, B:925:0x192d, B:927:0x1934, B:928:0x1935, B:938:0x194e, B:940:0x1958, B:941:0x1959, B:947:0x195b, B:949:0x1970, B:950:0x1971, B:953:0x1973, B:955:0x1988, B:956:0x1989, B:1177:0x19af, B:1178:0x19d0, B:663:0x1262, B:616:0x1151, B:122:0x08dd, B:613:0x1113, B:119:0x08a0, B:160:0x0b8d, B:713:0x17b4, B:714:0x17e6), top: B:662:0x1262, inners: #7, #30, #32, #44, #48, #60, #104 }] */
    /* JADX WARN: Removed duplicated region for block: B:928:0x1935 A[Catch: all -> 0x19d1, TryCatch #94 {all -> 0x19d1, blocks: (B:710:0x179a, B:719:0x17e9, B:721:0x17ef, B:722:0x17f0, B:744:0x17fa, B:746:0x1807, B:747:0x1808, B:761:0x181f, B:763:0x1826, B:764:0x1827, B:787:0x1833, B:789:0x1841, B:790:0x1842, B:800:0x1848, B:802:0x1857, B:803:0x1858, B:809:0x185a, B:811:0x186b, B:812:0x186c, B:815:0x186e, B:817:0x187f, B:818:0x1880, B:828:0x1884, B:830:0x1897, B:831:0x1898, B:885:0x18c4, B:887:0x18cb, B:888:0x18cc, B:906:0x18fa, B:908:0x1904, B:909:0x1905, B:925:0x192d, B:927:0x1934, B:928:0x1935, B:938:0x194e, B:940:0x1958, B:941:0x1959, B:947:0x195b, B:949:0x1970, B:950:0x1971, B:953:0x1973, B:955:0x1988, B:956:0x1989, B:1177:0x19af, B:1178:0x19d0, B:663:0x1262, B:616:0x1151, B:122:0x08dd, B:613:0x1113, B:119:0x08a0, B:160:0x0b8d, B:713:0x17b4, B:714:0x17e6), top: B:662:0x1262, inners: #7, #30, #32, #44, #48, #60, #104 }] */
    /* JADX WARN: Type inference failed for: r14v81, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r2v163, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v230, types: [java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r38v13 */
    /* JADX WARN: Type inference failed for: r38v14 */
    /* JADX WARN: Type inference failed for: r38v15 */
    /* JADX WARN: Type inference failed for: r3v231, types: [java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r4v144 */
    /* JADX WARN: Type inference failed for: r4v145 */
    /* JADX WARN: Type inference failed for: r4v248 */
    /* JADX WARN: Type inference failed for: r5v149, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v200, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r8v123 */
    /* JADX WARN: Type inference failed for: r8v125 */
    /* JADX WARN: Type inference failed for: r8v126 */
    /* JADX WARN: Type inference failed for: r8v127 */
    /* JADX WARN: Type inference failed for: r8v132 */
    /* JADX WARN: Type inference failed for: r8v133 */
    /* JADX WARN: Type inference failed for: r8v134, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r8v137, types: [int, byte] */
    /* JADX WARN: Type inference failed for: r8v143 */
    /* JADX WARN: Type inference failed for: r8v146 */
    /* JADX WARN: Type inference failed for: r8v147 */
    /* JADX WARN: Type inference failed for: r8v170 */
    /* JADX WARN: Type inference failed for: r8v207 */
    static {
        /*
            Method dump skipped, instructions count: 6870
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFc1iSDK.<clinit>():void");
    }
}
