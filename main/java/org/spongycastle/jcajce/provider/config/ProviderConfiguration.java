package org.spongycastle.jcajce.provider.config;

import java.util.Map;
import java.util.Set;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.jce.spec.ECParameterSpec;

/* loaded from: classes3.dex */
public interface ProviderConfiguration {
    Set getAcceptableNamedCurves();

    Map getAdditionalECParameters();

    DHParameterSpec getDHDefaultParameters(int i);

    ECParameterSpec getEcImplicitlyCa();
}
