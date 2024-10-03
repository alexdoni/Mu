package org.spongycastle.asn1;

import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;

/* loaded from: classes3.dex */
public abstract class ASN1BitString extends ASN1Primitive implements ASN1String {
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected final byte[] data;
    protected final int padBits;

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getPadBits(int i) {
        int i2;
        int i3 = 3;
        while (true) {
            if (i3 < 0) {
                i2 = 0;
                break;
            }
            if (i3 != 0) {
                int i4 = i >> (i3 * 8);
                if (i4 != 0) {
                    i2 = i4 & 255;
                    break;
                }
                i3--;
            } else {
                if (i != 0) {
                    i2 = i & 255;
                    break;
                }
                i3--;
            }
        }
        if (i2 == 0) {
            return 0;
        }
        int i5 = 1;
        while (true) {
            i2 <<= 1;
            if ((i2 & 255) == 0) {
                return 8 - i5;
            }
            i5++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] getBytes(int i) {
        if (i == 0) {
            return new byte[0];
        }
        int i2 = 4;
        for (int i3 = 3; i3 >= 1 && ((255 << (i3 * 8)) & i) == 0; i3--) {
            i2--;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i >> (i4 * 8)) & 255);
        }
        return bArr;
    }

    public ASN1BitString(byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException("data cannot be null");
        }
        if (bArr.length == 0 && i != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        }
        if (i > 7 || i < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.data = Arrays.clone(bArr);
        this.padBits = i;
    }

    @Override // org.spongycastle.asn1.ASN1String
    public String getString() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).writeObject(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                char[] cArr = table;
                stringBuffer.append(cArr[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(cArr[byteArray[i] & Ascii.f555SI]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    public int intValue() {
        byte[] bArr = this.data;
        int i = this.padBits;
        if (i > 0 && bArr.length <= 4) {
            bArr = derForm(bArr, i);
        }
        int i2 = 0;
        for (int i3 = 0; i3 != bArr.length && i3 != 4; i3++) {
            i2 |= (bArr[i3] & 255) << (i3 * 8);
        }
        return i2;
    }

    public byte[] getOctets() {
        if (this.padBits != 0) {
            throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
        }
        return Arrays.clone(this.data);
    }

    public byte[] getBytes() {
        return derForm(this.data, this.padBits);
    }

    public int getPadBits() {
        return this.padBits;
    }

    public String toString() {
        return getString();
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return this.padBits ^ Arrays.hashCode(getBytes());
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    protected boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1BitString)) {
            return false;
        }
        ASN1BitString aSN1BitString = (ASN1BitString) aSN1Primitive;
        return this.padBits == aSN1BitString.padBits && Arrays.areEqual(getBytes(), aSN1BitString.getBytes());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] derForm(byte[] bArr, int i) {
        byte[] clone = Arrays.clone(bArr);
        if (i > 0) {
            int length = bArr.length - 1;
            clone[length] = (byte) ((255 << i) & clone[length]);
        }
        return clone;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1BitString fromInputStream(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        int read = inputStream.read();
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        if (i2 != 0) {
            if (Streams.readFully(inputStream, bArr) != i2) {
                throw new EOFException("EOF encountered in middle of BIT STRING");
            }
            if (read > 0 && read < 8) {
                byte b = bArr[i2 - 1];
                if (b != ((byte) ((255 << read) & b))) {
                    return new DLBitString(bArr, read);
                }
            }
        }
        return new DERBitString(bArr, read);
    }

    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DERBitString(this.data, this.padBits);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return new DLBitString(this.data, this.padBits);
    }
}
