package org.spongycastle.util.io.pem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Base64;

/* loaded from: classes3.dex */
public class PemWriter extends BufferedWriter {
    private static final int LINE_LENGTH = 64;
    private char[] buf;
    private final int nlLength;

    public PemWriter(Writer writer) {
        super(writer);
        this.buf = new char[64];
        String lineSeparator = Strings.lineSeparator();
        if (lineSeparator != null) {
            this.nlLength = lineSeparator.length();
        } else {
            this.nlLength = 2;
        }
    }

    public int getOutputSize(PemObject pemObject) {
        int length = ((pemObject.getType().length() + 10 + this.nlLength) * 2) + 6 + 4;
        if (!pemObject.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : pemObject.getHeaders()) {
                length += pemHeader.getName().length() + 2 + pemHeader.getValue().length() + this.nlLength;
            }
            length += this.nlLength;
        }
        return length + (((pemObject.getContent().length + 2) / 3) * 4) + ((((r5 + 64) - 1) / 64) * this.nlLength);
    }

    public void writeObject(PemObjectGenerator pemObjectGenerator) throws IOException {
        PemObject generate = pemObjectGenerator.generate();
        writePreEncapsulationBoundary(generate.getType());
        if (!generate.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : generate.getHeaders()) {
                write(pemHeader.getName());
                write(": ");
                write(pemHeader.getValue());
                newLine();
            }
            newLine();
        }
        writeEncoded(generate.getContent());
        writePostEncapsulationBoundary(generate.getType());
    }

    private void writeEncoded(byte[] bArr) throws IOException {
        char[] cArr;
        int i;
        byte[] encode = Base64.encode(bArr);
        int i2 = 0;
        while (i2 < encode.length) {
            int i3 = 0;
            while (true) {
                cArr = this.buf;
                if (i3 != cArr.length && (i = i2 + i3) < encode.length) {
                    cArr[i3] = (char) encode[i];
                    i3++;
                }
            }
            write(cArr, 0, i3);
            newLine();
            i2 += this.buf.length;
        }
    }

    private void writePreEncapsulationBoundary(String str) throws IOException {
        write("-----BEGIN " + str + "-----");
        newLine();
    }

    private void writePostEncapsulationBoundary(String str) throws IOException {
        write("-----END " + str + "-----");
        newLine();
    }
}
