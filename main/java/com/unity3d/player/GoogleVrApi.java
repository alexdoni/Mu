package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* loaded from: classes3.dex */
public class GoogleVrApi {

    /* renamed from: a */
    private static AtomicReference f1614a = new AtomicReference();

    private GoogleVrApi() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1231a() {
        f1614a.set(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1232a(InterfaceC2710f interfaceC2710f) {
        UByte$$ExternalSyntheticBackport0.m1410m(f1614a, null, new GoogleVrProxy(interfaceC2710f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static GoogleVrProxy m1233b() {
        return (GoogleVrProxy) f1614a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) f1614a.get();
    }
}
