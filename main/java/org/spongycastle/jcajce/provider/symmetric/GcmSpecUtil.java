package org.spongycastle.jcajce.provider.symmetric;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.cms.GCMParameters;
import org.spongycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.spongycastle.util.Integers;

/* loaded from: classes3.dex */
class GcmSpecUtil {
    static final Class gcmSpecClass = ClassUtil.loadClass(GcmSpecUtil.class, "javax.crypto.spec.GCMParameterSpec");

    GcmSpecUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean gcmSpecExists() {
        return gcmSpecClass != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isGcmSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        Class cls = gcmSpecClass;
        return cls != null && cls.isInstance(algorithmParameterSpec);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isGcmSpec(Class cls) {
        return gcmSpecClass == cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameterSpec extractGcmSpec(ASN1Primitive aSN1Primitive) throws InvalidParameterSpecException {
        try {
            GCMParameters gCMParameters = GCMParameters.getInstance(aSN1Primitive);
            return (AlgorithmParameterSpec) gcmSpecClass.getConstructor(Integer.TYPE, byte[].class).newInstance(Integers.valueOf(gCMParameters.getIcvLen() * 8), gCMParameters.getNonce());
        } catch (NoSuchMethodException unused) {
            throw new InvalidParameterSpecException("No constructor found!");
        } catch (Exception e) {
            throw new InvalidParameterSpecException("Construction failed: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GCMParameters extractGcmParameters(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        try {
            Class cls = gcmSpecClass;
            return new GCMParameters((byte[]) cls.getDeclaredMethod("getIV", new Class[0]).invoke(algorithmParameterSpec, new Object[0]), ((Integer) cls.getDeclaredMethod("getTLen", new Class[0]).invoke(algorithmParameterSpec, new Object[0])).intValue() / 8);
        } catch (Exception unused) {
            throw new InvalidParameterSpecException("Cannot process GCMParameterSpec");
        }
    }
}
