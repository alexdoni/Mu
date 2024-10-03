package com.appsflyer.internal;

import org.spongycastle.asn1.cmc.BodyPartID;

/* loaded from: classes.dex */
public final class AFj1uSDK {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] valueOf(int i, int i2) {
        long[] jArr = new long[4];
        jArr[0] = (i2 & BodyPartID.bodyIdMax) | ((i & BodyPartID.bodyIdMax) << 32);
        for (int i3 = 1; i3 < 4; i3++) {
            long j = jArr[i3 - 1];
            jArr[i3] = ((j ^ (j >> 30)) * 1812433253) + i3;
        }
        return jArr;
    }
}
