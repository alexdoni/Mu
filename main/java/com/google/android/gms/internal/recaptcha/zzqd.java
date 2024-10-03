package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzqd implements zzsz {
    private final zzqc zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzqd(zzqc zzqcVar) {
        zzrp.zzf(zzqcVar, "input");
        this.zza = zzqcVar;
        zzqcVar.zzc = this;
    }

    private final Object zzQ(zzuh zzuhVar, Class<?> cls, zzqr zzqrVar) throws IOException {
        zzuh zzuhVar2 = zzuh.DOUBLE;
        switch (zzuhVar) {
            case DOUBLE:
                return Double.valueOf(zza());
            case FLOAT:
                return Float.valueOf(zzb());
            case INT64:
                return Long.valueOf(zzl());
            case UINT64:
                return Long.valueOf(zzo());
            case INT32:
                return Integer.valueOf(zzg());
            case FIXED64:
                return Long.valueOf(zzk());
            case FIXED32:
                return Integer.valueOf(zzf());
            case BOOL:
                return Boolean.valueOf(zzO());
            case STRING:
                return zzu();
            case GROUP:
            default:
                throw new RuntimeException("unsupported field type.");
            case MESSAGE:
                zzU(2);
                return zzS(zzsw.zza().zzb(cls), zzqrVar);
            case BYTES:
                return zzp();
            case UINT32:
                return Integer.valueOf(zzj());
            case ENUM:
                return Integer.valueOf(zze());
            case SFIXED32:
                return Integer.valueOf(zzh());
            case SFIXED64:
                return Long.valueOf(zzm());
            case SINT32:
                return Integer.valueOf(zzi());
            case SINT64:
                return Long.valueOf(zzn());
        }
    }

    private final <T> T zzR(zzta<T> zztaVar, zzqr zzqrVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T zzc = zztaVar.zzc();
            zztaVar.zzf(zzc, this, zzqrVar);
            zztaVar.zzd(zzc);
            if (this.zzb == this.zzc) {
                return zzc;
            }
            throw zzrr.zzg();
        } finally {
            this.zzc = i;
        }
    }

    private final <T> T zzS(zzta<T> zztaVar, zzqr zzqrVar) throws IOException {
        int zzn = this.zza.zzn();
        zzqc zzqcVar = this.zza;
        if (zzqcVar.zza >= zzqcVar.zzb) {
            throw new zzrr("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zze = zzqcVar.zze(zzn);
        T zzc = zztaVar.zzc();
        this.zza.zza++;
        zztaVar.zzf(zzc, this, zzqrVar);
        zztaVar.zzd(zzc);
        this.zza.zzz(0);
        r5.zza--;
        this.zza.zzA(zze);
        return zzc;
    }

    private final void zzT(int i) throws IOException {
        if (this.zza.zzd() != i) {
            throw zzrr.zzj();
        }
    }

    private final void zzU(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzrr.zza();
        }
    }

    private static final void zzV(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzrr.zzg();
        }
    }

    private static final void zzW(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzrr.zzg();
        }
    }

    public static zzqd zzq(zzqc zzqcVar) {
        zzqd zzqdVar = zzqcVar.zzc;
        return zzqdVar != null ? zzqdVar : new zzqd(zzqcVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzA(List<Long> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            int i = this.zzb & 7;
            if (i != 1) {
                if (i == 2) {
                    int zzn = this.zza.zzn();
                    zzW(zzn);
                    int zzd = this.zza.zzd() + zzn;
                    do {
                        zzsbVar.zzf(this.zza.zzo());
                    } while (this.zza.zzd() < zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzsbVar.zzf(this.zza.zzo());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 1) {
            if (i2 == 2) {
                int zzn2 = this.zza.zzn();
                zzW(zzn2);
                int zzd2 = this.zza.zzd() + zzn2;
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzd() < zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzo()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzB(List<Float> list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzqy)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zzn = this.zza.zzn();
                zzV(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                } while (this.zza.zzd() < zzd);
                return;
            }
            if (i != 5) {
                throw zzrr.zza();
            }
            do {
                list.add(Float.valueOf(this.zza.zzc()));
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm = this.zza.zzm();
                }
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        zzqy zzqyVar = (zzqy) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzV(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzqyVar.zze(this.zza.zzc());
            } while (this.zza.zzd() < zzd2);
            return;
        }
        if (i2 != 5) {
            throw zzrr.zza();
        }
        do {
            zzqyVar.zze(this.zza.zzc());
            if (this.zza.zzC()) {
                return;
            } else {
                zzm2 = this.zza.zzm();
            }
        } while (zzm2 == this.zzb);
        this.zzd = zzm2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final <T> void zzC(List<T> list, zzta<T> zztaVar, zzqr zzqrVar) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) != 3) {
            throw zzrr.zza();
        }
        do {
            list.add(zzR(zztaVar, zzqrVar));
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == i);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzD(List<Integer> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzrhVar.zzh(this.zza.zzh());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzrhVar.zzh(this.zza.zzh());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzh()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzE(List<Long> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzsbVar.zzf(this.zza.zzp());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzsbVar.zzf(this.zza.zzp());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzp()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x005c, code lost:
    
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0064, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K, V> void zzF(java.util.Map<K, V> r8, com.google.android.gms.internal.recaptcha.zzsf<K, V> r9, com.google.android.gms.internal.recaptcha.zzqr r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzU(r0)
            com.google.android.gms.internal.recaptcha.zzqc r1 = r7.zza
            int r1 = r1.zzn()
            com.google.android.gms.internal.recaptcha.zzqc r2 = r7.zza
            int r1 = r2.zze(r1)
            K r2 = r9.zzb
            V r3 = r9.zzd
        L14:
            int r4 = r7.zzc()     // Catch: java.lang.Throwable -> L65
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L5c
            com.google.android.gms.internal.recaptcha.zzqc r5 = r7.zza     // Catch: java.lang.Throwable -> L65
            boolean r5 = r5.zzC()     // Catch: java.lang.Throwable -> L65
            if (r5 == 0) goto L26
            goto L5c
        L26:
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L47
            if (r4 == r0) goto L3a
            boolean r4 = r7.zzP()     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            if (r4 == 0) goto L34
            goto L14
        L34:
            com.google.android.gms.internal.recaptcha.zzrr r4 = new com.google.android.gms.internal.recaptcha.zzrr     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            r4.<init>(r6)     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            throw r4     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
        L3a:
            com.google.android.gms.internal.recaptcha.zzuh r4 = r9.zzc     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            V r5 = r9.zzd     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            java.lang.Class r5 = r5.getClass()     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            java.lang.Object r3 = r7.zzQ(r4, r5, r10)     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            goto L14
        L47:
            com.google.android.gms.internal.recaptcha.zzuh r4 = r9.zza     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            r5 = 0
            java.lang.Object r2 = r7.zzQ(r4, r5, r5)     // Catch: com.google.android.gms.internal.recaptcha.zzrq -> L4f java.lang.Throwable -> L65
            goto L14
        L4f:
            boolean r4 = r7.zzP()     // Catch: java.lang.Throwable -> L65
            if (r4 == 0) goto L56
            goto L14
        L56:
            com.google.android.gms.internal.recaptcha.zzrr r8 = new com.google.android.gms.internal.recaptcha.zzrr     // Catch: java.lang.Throwable -> L65
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L65
            throw r8     // Catch: java.lang.Throwable -> L65
        L5c:
            r8.put(r2, r3)     // Catch: java.lang.Throwable -> L65
            com.google.android.gms.internal.recaptcha.zzqc r8 = r7.zza
            r8.zzA(r1)
            return
        L65:
            r8 = move-exception
            com.google.android.gms.internal.recaptcha.zzqc r9 = r7.zza
            r9.zzA(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzqd.zzF(java.util.Map, com.google.android.gms.internal.recaptcha.zzsf, com.google.android.gms.internal.recaptcha.zzqr):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final <T> void zzG(List<T> list, zzta<T> zztaVar, zzqr zzqrVar) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) != 2) {
            throw zzrr.zza();
        }
        do {
            list.add(zzS(zztaVar, zzqrVar));
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == i);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzH(List<Integer> list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzrh)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zzn = this.zza.zzn();
                zzV(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                } while (this.zza.zzd() < zzd);
                return;
            }
            if (i != 5) {
                throw zzrr.zza();
            }
            do {
                list.add(Integer.valueOf(this.zza.zzk()));
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm = this.zza.zzm();
                }
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        zzrh zzrhVar = (zzrh) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzV(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzrhVar.zzh(this.zza.zzk());
            } while (this.zza.zzd() < zzd2);
            return;
        }
        if (i2 != 5) {
            throw zzrr.zza();
        }
        do {
            zzrhVar.zzh(this.zza.zzk());
            if (this.zza.zzC()) {
                return;
            } else {
                zzm2 = this.zza.zzm();
            }
        } while (zzm2 == this.zzb);
        this.zzd = zzm2;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzI(List<Long> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            int i = this.zzb & 7;
            if (i != 1) {
                if (i == 2) {
                    int zzn = this.zza.zzn();
                    zzW(zzn);
                    int zzd = this.zza.zzd() + zzn;
                    do {
                        zzsbVar.zzf(this.zza.zzt());
                    } while (this.zza.zzd() < zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzsbVar.zzf(this.zza.zzt());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 1) {
            if (i2 == 2) {
                int zzn2 = this.zza.zzn();
                zzW(zzn2);
                int zzd2 = this.zza.zzd() + zzn2;
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                } while (this.zza.zzd() < zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzt()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzJ(List<Integer> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzrhVar.zzh(this.zza.zzl());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzrhVar.zzh(this.zza.zzl());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzl()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzl()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzK(List<Long> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzsbVar.zzf(this.zza.zzu());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzsbVar.zzf(this.zza.zzu());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzu()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzM(List<Integer> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzrhVar.zzh(this.zza.zzn());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzrhVar.zzh(this.zza.zzn());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzn()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzN(List<Long> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzsbVar.zzf(this.zza.zzv());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzsbVar.zzf(this.zza.zzv());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzv()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzv()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final boolean zzO() throws IOException {
        zzU(0);
        return this.zza.zzD();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final boolean zzP() throws IOException {
        int i;
        if (this.zza.zzC() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzE(i);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final double zza() throws IOException {
        zzU(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final float zzb() throws IOException {
        zzU(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzm();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zze() throws IOException {
        zzU(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zzf() throws IOException {
        zzU(5);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zzg() throws IOException {
        zzU(0);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zzh() throws IOException {
        zzU(5);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zzi() throws IOException {
        zzU(0);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final int zzj() throws IOException {
        zzU(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final long zzk() throws IOException {
        zzU(1);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final long zzl() throws IOException {
        zzU(0);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final long zzm() throws IOException {
        zzU(1);
        return this.zza.zzt();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final long zzn() throws IOException {
        zzU(0);
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final long zzo() throws IOException {
        zzU(0);
        return this.zza.zzv();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final zzpy zzp() throws IOException {
        zzU(2);
        return this.zza.zzw();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final <T> T zzr(zzta<T> zztaVar, zzqr zzqrVar) throws IOException {
        zzU(3);
        return (T) zzR(zztaVar, zzqrVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final <T> T zzs(zzta<T> zztaVar, zzqr zzqrVar) throws IOException {
        zzU(2);
        return (T) zzS(zztaVar, zzqrVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final String zzt() throws IOException {
        zzU(2);
        return this.zza.zzx();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final String zzu() throws IOException {
        zzU(2);
        return this.zza.zzy();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzv(List<Boolean> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzpm) {
            zzpm zzpmVar = (zzpm) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzpmVar.zze(this.zza.zzD());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzpmVar.zze(this.zza.zzD());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Boolean.valueOf(this.zza.zzD()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Boolean.valueOf(this.zza.zzD()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzw(List<zzpy> list) throws IOException {
        int zzm;
        if ((this.zzb & 7) != 2) {
            throw zzrr.zza();
        }
        do {
            list.add(zzp());
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzx(List<Double> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzql) {
            zzql zzqlVar = (zzql) list;
            int i = this.zzb & 7;
            if (i != 1) {
                if (i == 2) {
                    int zzn = this.zza.zzn();
                    zzW(zzn);
                    int zzd = this.zza.zzd() + zzn;
                    do {
                        zzqlVar.zze(this.zza.zzb());
                    } while (this.zza.zzd() < zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzqlVar.zze(this.zza.zzb());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 1) {
            if (i2 == 2) {
                int zzn2 = this.zza.zzn();
                zzW(zzn2);
                int zzd2 = this.zza.zzd() + zzn2;
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                } while (this.zza.zzd() < zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Double.valueOf(this.zza.zzb()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzy(List<Integer> list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzd = this.zza.zzd() + this.zza.zzn();
                    do {
                        zzrhVar.zzh(this.zza.zzf());
                    } while (this.zza.zzd() < zzd);
                    zzT(zzd);
                    return;
                }
                throw zzrr.zza();
            }
            do {
                zzrhVar.zzh(this.zza.zzf());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm2 = this.zza.zzm();
                }
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzd2 = this.zza.zzd() + this.zza.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzd() < zzd2);
                zzT(zzd2);
                return;
            }
            throw zzrr.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzf()));
            if (this.zza.zzC()) {
                return;
            } else {
                zzm = this.zza.zzm();
            }
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsz
    public final void zzz(List<Integer> list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzrh)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zzn = this.zza.zzn();
                zzV(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzd() < zzd);
                return;
            }
            if (i != 5) {
                throw zzrr.zza();
            }
            do {
                list.add(Integer.valueOf(this.zza.zzg()));
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm = this.zza.zzm();
                }
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        zzrh zzrhVar = (zzrh) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzV(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzrhVar.zzh(this.zza.zzg());
            } while (this.zza.zzd() < zzd2);
            return;
        }
        if (i2 != 5) {
            throw zzrr.zza();
        }
        do {
            zzrhVar.zzh(this.zza.zzg());
            if (this.zza.zzC()) {
                return;
            } else {
                zzm2 = this.zza.zzm();
            }
        } while (zzm2 == this.zzb);
        this.zzd = zzm2;
    }

    public final void zzL(List<String> list, boolean z) throws IOException {
        int zzm;
        int zzm2;
        if ((this.zzb & 7) != 2) {
            throw zzrr.zza();
        }
        if (!(list instanceof zzrw) || z) {
            do {
                list.add(z ? zzu() : zzt());
                if (this.zza.zzC()) {
                    return;
                } else {
                    zzm = this.zza.zzm();
                }
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        zzrw zzrwVar = (zzrw) list;
        do {
            zzrwVar.zzi(zzp());
            if (this.zza.zzC()) {
                return;
            } else {
                zzm2 = this.zza.zzm();
            }
        } while (zzm2 == this.zzb);
        this.zzd = zzm2;
    }
}
