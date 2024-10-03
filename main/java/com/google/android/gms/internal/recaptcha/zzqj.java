package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzqj extends zzpn {
    private static final Logger zza = Logger.getLogger(zzqj.class.getName());
    private static final boolean zzb = zzub.zzx();
    zzqk zze;

    private zzqj() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzqj(zzqe zzqeVar) {
    }

    public static int zzA(zzpy zzpyVar) {
        int zzd = zzpyVar.zzd();
        return zzK(zzd) + zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzB(int i, zzsn zzsnVar, zzta zztaVar) {
        int zzK = zzK(i << 3);
        int i2 = zzK + zzK;
        zzph zzphVar = (zzph) zzsnVar;
        int zzo = zzphVar.zzo();
        if (zzo == -1) {
            zzo = zztaVar.zza(zzphVar);
            zzphVar.zzr(zzo);
        }
        return i2 + zzo;
    }

    @Deprecated
    public static int zzC(zzsn zzsnVar) {
        return zzsnVar.zzt();
    }

    public static int zzD(int i) {
        if (i >= 0) {
            return zzK(i);
        }
        return 10;
    }

    public static int zzE(zzru zzruVar) {
        int zza2 = zzruVar.zza();
        return zzK(zza2) + zza2;
    }

    public static int zzF(zzsn zzsnVar) {
        int zzt = zzsnVar.zzt();
        return zzK(zzt) + zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzG(zzsn zzsnVar, zzta zztaVar) {
        zzph zzphVar = (zzph) zzsnVar;
        int zzo = zzphVar.zzo();
        if (zzo == -1) {
            zzo = zztaVar.zza(zzphVar);
            zzphVar.zzr(zzo);
        }
        return zzK(zzo) + zzo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzH(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int zzI(String str) {
        int length;
        try {
            length = zzug.zzc(str);
        } catch (zzuf unused) {
            length = str.getBytes(zzrp.zza).length;
        }
        return zzK(length) + length;
    }

    public static int zzJ(int i) {
        return zzK(i << 3);
    }

    public static int zzK(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzL(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzqj zzM(byte[] bArr) {
        return new zzqg(bArr, 0, bArr.length);
    }

    public static zzqj zzN(OutputStream outputStream, int i) {
        return new zzqi(outputStream, i);
    }

    public static int zzz(byte[] bArr) {
        int length = bArr.length;
        return zzK(length) + length;
    }

    public final void zzO() {
        if (zzb() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzP(String str, zzuf zzufVar) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzufVar);
        byte[] bytes = str.getBytes(zzrp.zza);
        try {
            int length = bytes.length;
            zzv(length);
            zza(bytes, 0, length);
        } catch (zzqh e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzqh(e2);
        }
    }

    public abstract void zzT() throws IOException;

    public abstract void zzU(byte b) throws IOException;

    public abstract void zzV(int i, boolean z) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzW(byte[] bArr, int i, int i2) throws IOException;

    @Override // com.google.android.gms.internal.recaptcha.zzpn
    public abstract void zza(byte[] bArr, int i, int i2) throws IOException;

    public abstract int zzb();

    public abstract void zzh(int i, zzpy zzpyVar) throws IOException;

    public abstract void zzi(zzpy zzpyVar) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(int i, long j) throws IOException;

    public abstract void zzm(long j) throws IOException;

    public abstract void zzn(int i, int i2) throws IOException;

    public abstract void zzo(int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzp(int i, zzsn zzsnVar, zzta zztaVar) throws IOException;

    public abstract void zzq(zzsn zzsnVar) throws IOException;

    public abstract void zzr(int i, String str) throws IOException;

    public abstract void zzs(String str) throws IOException;

    public abstract void zzt(int i, int i2) throws IOException;

    public abstract void zzu(int i, int i2) throws IOException;

    public abstract void zzv(int i) throws IOException;

    public abstract void zzw(int i, long j) throws IOException;

    public abstract void zzx(long j) throws IOException;
}
