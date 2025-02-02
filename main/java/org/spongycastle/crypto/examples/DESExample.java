package org.spongycastle.crypto.examples;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.generators.DESedeKeyGenerator;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class DESExample {
    private PaddedBufferedBlockCipher cipher;
    private boolean encrypt;

    /* renamed from: in */
    private BufferedInputStream f2368in;
    private byte[] key;
    private BufferedOutputStream out;

    public static void main(String[] strArr) {
        String str;
        boolean z = true;
        if (strArr.length < 2) {
            DESExample dESExample = new DESExample();
            System.err.println("Usage: java " + dESExample.getClass().getName() + " infile outfile [keyfile]");
            System.exit(1);
        }
        String str2 = strArr[0];
        String str3 = strArr[1];
        if (strArr.length > 2) {
            str = strArr[2];
            z = false;
        } else {
            str = "deskey.dat";
        }
        new DESExample(str2, str3, str, z).process();
    }

    public DESExample() {
        this.encrypt = true;
        this.cipher = null;
        this.f2368in = null;
        this.out = null;
        this.key = null;
    }

    public DESExample(String str, String str2, String str3, boolean z) {
        SecureRandom secureRandom;
        SecureRandom secureRandom2 = null;
        this.cipher = null;
        this.f2368in = null;
        this.out = null;
        this.key = null;
        this.encrypt = z;
        try {
            this.f2368in = new BufferedInputStream(new FileInputStream(str));
        } catch (FileNotFoundException unused) {
            System.err.println("Input file not found [" + str + "]");
            System.exit(1);
        }
        try {
            this.out = new BufferedOutputStream(new FileOutputStream(str2));
        } catch (IOException unused2) {
            System.err.println("Output file not created [" + str2 + "]");
            System.exit(1);
        }
        if (z) {
            try {
                try {
                    secureRandom = new SecureRandom();
                    try {
                        secureRandom.setSeed("www.bouncycastle.org".getBytes());
                    } catch (Exception unused3) {
                        secureRandom2 = secureRandom;
                        System.err.println("Hmmm, no SHA1PRNG, you need the Sun implementation");
                        System.exit(1);
                        secureRandom = secureRandom2;
                        KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(secureRandom, 192);
                        DESedeKeyGenerator dESedeKeyGenerator = new DESedeKeyGenerator();
                        dESedeKeyGenerator.init(keyGenerationParameters);
                        this.key = dESedeKeyGenerator.generateKey();
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
                        byte[] encode = Hex.encode(this.key);
                        bufferedOutputStream.write(encode, 0, encode.length);
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        return;
                    }
                } catch (IOException unused4) {
                    System.err.println("Could not decryption create key file [" + str3 + "]");
                    System.exit(1);
                    return;
                }
            } catch (Exception unused5) {
            }
            KeyGenerationParameters keyGenerationParameters2 = new KeyGenerationParameters(secureRandom, 192);
            DESedeKeyGenerator dESedeKeyGenerator2 = new DESedeKeyGenerator();
            dESedeKeyGenerator2.init(keyGenerationParameters2);
            this.key = dESedeKeyGenerator2.generateKey();
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str3));
            byte[] encode2 = Hex.encode(this.key);
            bufferedOutputStream2.write(encode2, 0, encode2.length);
            bufferedOutputStream2.flush();
            bufferedOutputStream2.close();
            return;
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str3));
            int available = bufferedInputStream.available();
            byte[] bArr = new byte[available];
            bufferedInputStream.read(bArr, 0, available);
            this.key = Hex.decode(bArr);
        } catch (IOException unused6) {
            System.err.println("Decryption key file not found, or not valid [" + str3 + "]");
            System.exit(1);
        }
    }

    private void process() {
        this.cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));
        if (this.encrypt) {
            performEncrypt(this.key);
        } else {
            performDecrypt(this.key);
        }
        try {
            this.f2368in.close();
            this.out.flush();
            this.out.close();
        } catch (IOException e) {
            System.err.println("exception closing resources: " + e.getMessage());
        }
    }

    private void performEncrypt(byte[] bArr) {
        this.cipher.init(true, new KeyParameter(bArr));
        byte[] bArr2 = new byte[47];
        byte[] bArr3 = new byte[this.cipher.getOutputSize(47)];
        while (true) {
            try {
                int read = this.f2368in.read(bArr2, 0, 47);
                if (read <= 0) {
                    try {
                        break;
                    } catch (CryptoException unused) {
                        return;
                    }
                }
                int processBytes = this.cipher.processBytes(bArr2, 0, read, bArr3, 0);
                if (processBytes > 0) {
                    byte[] encode = Hex.encode(bArr3, 0, processBytes);
                    this.out.write(encode, 0, encode.length);
                    this.out.write(10);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int doFinal = this.cipher.doFinal(bArr3, 0);
        if (doFinal > 0) {
            byte[] encode2 = Hex.encode(bArr3, 0, doFinal);
            this.out.write(encode2, 0, encode2.length);
            this.out.write(10);
        }
    }

    private void performDecrypt(byte[] bArr) {
        this.cipher.init(false, new KeyParameter(bArr));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f2368in));
        byte[] bArr2 = null;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    try {
                        break;
                    } catch (CryptoException unused) {
                        return;
                    }
                }
                byte[] decode = Hex.decode(readLine);
                bArr2 = new byte[this.cipher.getOutputSize(decode.length)];
                int processBytes = this.cipher.processBytes(decode, 0, decode.length, bArr2, 0);
                if (processBytes > 0) {
                    this.out.write(bArr2, 0, processBytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int doFinal = this.cipher.doFinal(bArr2, 0);
        if (doFinal > 0) {
            this.out.write(bArr2, 0, doFinal);
        }
    }
}
