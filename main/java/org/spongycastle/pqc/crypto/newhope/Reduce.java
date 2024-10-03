package org.spongycastle.pqc.crypto.newhope;

import com.tencent.p014av.ptt.PttError;
import kotlin.UShort;

/* loaded from: classes3.dex */
class Reduce {
    static final int QInv = 12287;
    static final int RLog = 18;
    static final int RMask = 262143;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short barrett(short s) {
        int i = s & UShort.MAX_VALUE;
        return (short) (i - (((i * 5) >>> 16) * PttError.VOICE_DOWNLOAD_FILE_ACCESSERROR));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short montgomery(int i) {
        return (short) (((((i * QInv) & RMask) * PttError.VOICE_DOWNLOAD_FILE_ACCESSERROR) + i) >>> 18);
    }

    Reduce() {
    }
}
