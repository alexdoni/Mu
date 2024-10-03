package org.spongycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;

/* loaded from: classes3.dex */
public class ASN1EncodableVector {

    /* renamed from: v */
    private final Vector f1987v = new Vector();

    public void add(ASN1Encodable aSN1Encodable) {
        this.f1987v.addElement(aSN1Encodable);
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        Enumeration elements = aSN1EncodableVector.f1987v.elements();
        while (elements.hasMoreElements()) {
            this.f1987v.addElement(elements.nextElement());
        }
    }

    public ASN1Encodable get(int i) {
        return (ASN1Encodable) this.f1987v.elementAt(i);
    }

    public int size() {
        return this.f1987v.size();
    }
}
