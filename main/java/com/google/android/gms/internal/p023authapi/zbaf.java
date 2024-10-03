package com.google.android.gms.internal.p023authapi;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth@@20.6.0 */
/* loaded from: classes2.dex */
public interface zbaf extends IInterface {
    void zbb(Status status, SaveAccountLinkingTokenResult saveAccountLinkingTokenResult) throws RemoteException;
}
