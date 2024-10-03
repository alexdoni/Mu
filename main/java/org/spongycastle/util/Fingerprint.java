package org.spongycastle.util;

import com.facebook.internal.security.CertificateUtil;
import com.google.common.base.Ascii;
import org.spongycastle.crypto.digests.SHA512tDigest;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes3.dex */
public class Fingerprint {
    private static char[] encodingTable = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final byte[] fingerprint;

    public Fingerprint(byte[] bArr) {
        this.fingerprint = calculateFingerprint(bArr);
    }

    public byte[] getFingerprint() {
        return Arrays.clone(this.fingerprint);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i != this.fingerprint.length; i++) {
            if (i > 0) {
                stringBuffer.append(CertificateUtil.DELIMITER);
            }
            stringBuffer.append(encodingTable[(this.fingerprint[i] >>> 4) & 15]);
            stringBuffer.append(encodingTable[this.fingerprint[i] & Ascii.f555SI]);
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Fingerprint) {
            return Arrays.areEqual(((Fingerprint) obj).fingerprint, this.fingerprint);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.fingerprint);
    }

    public static byte[] calculateFingerprint(byte[] bArr) {
        SHA512tDigest sHA512tDigest = new SHA512tDigest(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256);
        sHA512tDigest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[sHA512tDigest.getDigestSize()];
        sHA512tDigest.doFinal(bArr2, 0);
        return bArr2;
    }
}
