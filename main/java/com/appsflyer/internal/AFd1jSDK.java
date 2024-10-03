package com.appsflyer.internal;

import android.content.Context;
import android.util.Base64;
import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFd1aSDK;
import com.appsflyer.internal.AFd1fSDK;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes.dex */
public final class AFd1jSDK implements AFd1gSDK {
    private final AFd1kSDK valueOf;

    public AFd1jSDK(AFd1kSDK aFd1kSDK) {
        Intrinsics.checkNotNullParameter(aFd1kSDK, "");
        this.valueOf = aFd1kSDK;
    }

    private final File valueOf() {
        Context context = this.valueOf.valueOf;
        if (context == null) {
            return null;
        }
        File file = new File(context.getFilesDir(), "AFExceptionsCache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    @Override // com.appsflyer.internal.AFd1gSDK
    public final String valueOf(Throwable th, String str) {
        String str2;
        File file;
        Intrinsics.checkNotNullParameter(th, "");
        Intrinsics.checkNotNullParameter(str, "");
        synchronized (this) {
            File valueOf = valueOf();
            str2 = null;
            if (valueOf != null) {
                file = new File(valueOf, "6.13.1");
                if (!file.exists()) {
                    file.mkdirs();
                }
            } else {
                file = null;
            }
            if (file != null) {
                try {
                    Intrinsics.checkNotNullParameter(th, "");
                    Intrinsics.checkNotNullParameter(str, "");
                    StringBuilder sb = new StringBuilder();
                    Intrinsics.checkNotNullParameter(th, "");
                    String name = th.getClass().getName();
                    Intrinsics.checkNotNullExpressionValue(name, "");
                    sb.append(name);
                    sb.append(": ");
                    sb.append(str);
                    String obj = sb.toString();
                    Intrinsics.checkNotNullParameter(th, "");
                    Intrinsics.checkNotNullParameter(th, "");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(th);
                    sb2.append('\n');
                    sb2.append(CollectionsKt.joinToString$default(AFd1aSDK.AFInAppEventParameterName(th), "\n", null, null, 0, null, AFd1aSDK.C06983.valueOf, 30, null));
                    String obj2 = sb2.toString();
                    Intrinsics.checkNotNullParameter(obj2, "");
                    AFd1fSDK aFd1fSDK = new AFd1fSDK(obj, AFe1zSDK.AFKeystoreWrapper(obj2, McElieceCCA2KeyGenParameterSpec.SHA256), ExceptionsKt.stackTraceToString(th), 0, 8, null);
                    String str3 = aFd1fSDK.AFInAppEventParameterName;
                    File file2 = new File(file, str3);
                    if (file2.exists()) {
                        AFd1fSDK.Companion companion = AFd1fSDK.INSTANCE;
                        AFd1fSDK AFInAppEventParameterName = AFd1fSDK.Companion.AFInAppEventParameterName(FilesKt.readText$default(file2, null, 1, null));
                        if (AFInAppEventParameterName != null) {
                            AFInAppEventParameterName.values++;
                            aFd1fSDK = AFInAppEventParameterName;
                        }
                    }
                    StringBuilder sb3 = new StringBuilder("label=");
                    String str4 = aFd1fSDK.AFInAppEventType;
                    Intrinsics.checkNotNullParameter(str4, "");
                    byte[] bytes = str4.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "");
                    sb3.append(Base64.encodeToString(bytes, 2));
                    sb3.append("\nhashName=");
                    String str5 = aFd1fSDK.AFInAppEventParameterName;
                    Intrinsics.checkNotNullParameter(str5, "");
                    byte[] bytes2 = str5.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes2, "");
                    sb3.append(Base64.encodeToString(bytes2, 2));
                    sb3.append("\nstackTrace=");
                    String str6 = aFd1fSDK.valueOf;
                    Intrinsics.checkNotNullParameter(str6, "");
                    byte[] bytes3 = str6.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes3, "");
                    sb3.append(Base64.encodeToString(bytes3, 2));
                    sb3.append("\nc=");
                    sb3.append(aFd1fSDK.values);
                    FilesKt.writeText$default(file2, sb3.toString(), null, 2, null);
                    str2 = str3;
                } catch (Exception e) {
                    AFLogger aFLogger = AFLogger.INSTANCE;
                    AFg1gSDK aFg1gSDK = AFg1gSDK.EXCEPTION_MANAGER;
                    StringBuilder sb4 = new StringBuilder("Could not cache exception\n ");
                    sb4.append(e.getMessage());
                    AFg1hSDK.v$default(aFLogger, aFg1gSDK, sb4.toString(), false, 4, null);
                }
            }
        }
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0072 A[Catch: all -> 0x0088, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0008, B:7:0x000e, B:9:0x001a, B:24:0x0072, B:26:0x0075, B:31:0x0050, B:33:0x0078, B:35:0x0082, B:11:0x001c, B:13:0x0022, B:15:0x0032, B:17:0x0046, B:19:0x0049, B:22:0x004c), top: B:2:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0075 A[SYNTHETIC] */
    @Override // com.appsflyer.internal.AFd1gSDK
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.appsflyer.internal.AFd1fSDK> AFInAppEventType() {
        /*
            r14 = this;
            monitor-enter(r14)
            java.io.File r0 = r14.valueOf()     // Catch: java.lang.Throwable -> L88
            r1 = 0
            if (r0 == 0) goto L80
            java.io.File[] r0 = r0.listFiles()     // Catch: java.lang.Throwable -> L88
            if (r0 == 0) goto L80
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L88
            r2.<init>()     // Catch: java.lang.Throwable -> L88
            java.util.Collection r2 = (java.util.Collection) r2     // Catch: java.lang.Throwable -> L88
            int r3 = r0.length     // Catch: java.lang.Throwable -> L88
            r4 = 0
            r5 = r4
        L18:
            if (r5 >= r3) goto L78
            r6 = r0[r5]     // Catch: java.lang.Throwable -> L88
            java.io.File[] r6 = r6.listFiles()     // Catch: java.lang.Throwable -> L4f
            if (r6 == 0) goto L6f
            java.lang.String r7 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.lang.Throwable -> L4f
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L4f
            r7.<init>()     // Catch: java.lang.Throwable -> L4f
            java.util.Collection r7 = (java.util.Collection) r7     // Catch: java.lang.Throwable -> L4f
            int r8 = r6.length     // Catch: java.lang.Throwable -> L4f
            r9 = r4
        L30:
            if (r9 >= r8) goto L4c
            r10 = r6[r9]     // Catch: java.lang.Throwable -> L4f
            com.appsflyer.internal.AFd1fSDK$AFa1uSDK r11 = com.appsflyer.internal.AFd1fSDK.INSTANCE     // Catch: java.lang.Throwable -> L4f
            java.lang.String r11 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)     // Catch: java.lang.Throwable -> L4f
            r11 = 1
            java.lang.String r10 = kotlin.io.FilesKt.readText$default(r10, r1, r11, r1)     // Catch: java.lang.Throwable -> L4f
            com.appsflyer.internal.AFd1fSDK r10 = com.appsflyer.internal.AFd1fSDK.Companion.AFInAppEventParameterName(r10)     // Catch: java.lang.Throwable -> L4f
            if (r10 == 0) goto L49
            r7.add(r10)     // Catch: java.lang.Throwable -> L4f
        L49:
            int r9 = r9 + 1
            goto L30
        L4c:
            java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L4f
            goto L70
        L4f:
            r6 = move-exception
            com.appsflyer.AFLogger r7 = com.appsflyer.AFLogger.INSTANCE     // Catch: java.lang.Throwable -> L88
            r8 = r7
            com.appsflyer.internal.AFg1hSDK r8 = (com.appsflyer.internal.AFg1hSDK) r8     // Catch: java.lang.Throwable -> L88
            com.appsflyer.internal.AFg1gSDK r9 = com.appsflyer.internal.AFg1gSDK.EXCEPTION_MANAGER     // Catch: java.lang.Throwable -> L88
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            java.lang.String r10 = "Could not get stored exceptions\n "
            r7.<init>(r10)     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L88
            r7.append(r6)     // Catch: java.lang.Throwable -> L88
            java.lang.String r10 = r7.toString()     // Catch: java.lang.Throwable -> L88
            r11 = 0
            r12 = 4
            r13 = 0
            com.appsflyer.internal.AFg1hSDK.v$default(r8, r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L88
        L6f:
            r7 = r1
        L70:
            if (r7 == 0) goto L75
            r2.add(r7)     // Catch: java.lang.Throwable -> L88
        L75:
            int r5 = r5 + 1
            goto L18
        L78:
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> L88
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch: java.lang.Throwable -> L88
            java.util.List r1 = kotlin.collections.CollectionsKt.flatten(r2)     // Catch: java.lang.Throwable -> L88
        L80:
            if (r1 != 0) goto L86
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()     // Catch: java.lang.Throwable -> L88
        L86:
            monitor-exit(r14)
            return r1
        L88:
            r0 = move-exception
            monitor-exit(r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFd1jSDK.AFInAppEventType():java.util.List");
    }

    @Override // com.appsflyer.internal.AFd1gSDK
    public final int AFKeystoreWrapper() {
        Iterator<T> it = AFInAppEventType().iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((AFd1fSDK) it.next()).values;
        }
        return i;
    }

    @Override // com.appsflyer.internal.AFd1gSDK
    public final boolean AFInAppEventParameterName() {
        return valueOf(new String[0]);
    }

    @Override // com.appsflyer.internal.AFd1gSDK
    public final boolean valueOf(String... strArr) {
        boolean z;
        Intrinsics.checkNotNullParameter(strArr, "");
        synchronized (this) {
            File valueOf = valueOf();
            z = true;
            if (valueOf != null) {
                if (strArr.length == 0) {
                    AFg1hSDK.v$default(AFLogger.INSTANCE, AFg1gSDK.EXCEPTION_MANAGER, "delete all exceptions", false, 4, null);
                    z = FilesKt.deleteRecursively(valueOf);
                } else {
                    AFLogger aFLogger = AFLogger.INSTANCE;
                    AFg1gSDK aFg1gSDK = AFg1gSDK.EXCEPTION_MANAGER;
                    StringBuilder sb = new StringBuilder("delete all exceptions except for: ");
                    sb.append(ArraysKt.joinToString$default(strArr, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                    AFg1hSDK.v$default(aFLogger, aFg1gSDK, sb.toString(), false, 4, null);
                    File[] listFiles = valueOf.listFiles();
                    if (listFiles != null) {
                        Intrinsics.checkNotNullExpressionValue(listFiles, "");
                        ArrayList arrayList = new ArrayList();
                        for (File file : listFiles) {
                            if (!ArraysKt.contains(strArr, file.getName())) {
                                arrayList.add(file);
                            }
                        }
                        ArrayList<File> arrayList2 = arrayList;
                        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                        for (File file2 : arrayList2) {
                            Intrinsics.checkNotNullExpressionValue(file2, "");
                            arrayList3.add(Boolean.valueOf(FilesKt.deleteRecursively(file2)));
                        }
                        Set set = CollectionsKt.toSet(arrayList3);
                        if (set.isEmpty()) {
                            set = SetsKt.setOf(Boolean.TRUE);
                        }
                        Set set2 = set;
                        if (set2.size() != 1 || !((Boolean) CollectionsKt.first(set2)).booleanValue()) {
                            z = false;
                        }
                    }
                }
            }
        }
        return z;
    }

    @Override // com.appsflyer.internal.AFd1gSDK
    public final void AFKeystoreWrapper(int i, int i2) {
        File[] listFiles;
        synchronized (this) {
            File valueOf = valueOf();
            if (valueOf != null && (listFiles = valueOf.listFiles()) != null) {
                Intrinsics.checkNotNullExpressionValue(listFiles, "");
                ArrayList arrayList = new ArrayList();
                for (File file : listFiles) {
                    String name = file.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "");
                    int valueOf2 = AFc1tSDK.valueOf(name);
                    if (!(i <= valueOf2 && valueOf2 <= i2)) {
                        arrayList.add(file);
                    }
                }
                ArrayList<File> arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                for (File file2 : arrayList2) {
                    Intrinsics.checkNotNullExpressionValue(file2, "");
                    arrayList3.add(Boolean.valueOf(FilesKt.deleteRecursively(file2)));
                }
                ArrayList arrayList4 = arrayList3;
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
