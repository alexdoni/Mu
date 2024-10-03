package org.spongycastle.math.field;

/* loaded from: classes3.dex */
public interface ExtensionField extends FiniteField {
    int getDegree();

    FiniteField getSubfield();
}
