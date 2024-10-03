package org.spongycastle.crypto;

/* loaded from: classes3.dex */
public interface CharToByteConverter {
    byte[] convert(char[] cArr);

    String getType();
}
