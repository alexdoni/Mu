package org.spongycastle.math.ec.tools;

import com.facebook.internal.security.CertificateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeSet;
import org.spongycastle.asn1.p020x9.ECNamedCurveTable;
import org.spongycastle.asn1.p020x9.X9ECParameters;
import org.spongycastle.crypto.ec.CustomNamedCurves;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECFieldElement;

/* loaded from: classes3.dex */
public class F2mSqrtOptimizer {
    public static void main(String[] strArr) {
        TreeSet<String> treeSet = new TreeSet(enumToList(ECNamedCurveTable.getNames()));
        treeSet.addAll(enumToList(CustomNamedCurves.getNames()));
        for (String str : treeSet) {
            X9ECParameters byName = CustomNamedCurves.getByName(str);
            if (byName == null) {
                byName = ECNamedCurveTable.getByName(str);
            }
            if (byName != null && ECAlgorithms.isF2mCurve(byName.getCurve())) {
                System.out.print(str + CertificateUtil.DELIMITER);
                implPrintRootZ(byName);
            }
        }
    }

    public static void printRootZ(X9ECParameters x9ECParameters) {
        if (!ECAlgorithms.isF2mCurve(x9ECParameters.getCurve())) {
            throw new IllegalArgumentException("Sqrt optimization only defined over characteristic-2 fields");
        }
        implPrintRootZ(x9ECParameters);
    }

    private static void implPrintRootZ(X9ECParameters x9ECParameters) {
        ECFieldElement fromBigInteger = x9ECParameters.getCurve().fromBigInteger(BigInteger.valueOf(2L));
        ECFieldElement sqrt = fromBigInteger.sqrt();
        System.out.println(sqrt.toBigInteger().toString(16).toUpperCase());
        if (!sqrt.square().equals(fromBigInteger)) {
            throw new IllegalStateException("Optimized-sqrt sanity check failed");
        }
    }

    private static ArrayList enumToList(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }
}
