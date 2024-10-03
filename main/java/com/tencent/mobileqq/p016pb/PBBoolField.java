package com.tencent.mobileqq.p016pb;

import java.io.IOException;

/* loaded from: classes3.dex */
public final class PBBoolField extends PBPrimitiveField<Boolean> {
    public static final PBBoolField __repeatHelper__ = new PBBoolField(false, false);
    private boolean value = false;

    public PBBoolField(boolean z, boolean z2) {
        set(z, z2);
    }

    public boolean get() {
        return this.value;
    }

    public void set(boolean z, boolean z2) {
        this.value = z;
        setHasFlag(z2);
    }

    public void set(boolean z) {
        set(z, true);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSize(int i) {
        if (has()) {
            return CodedOutputStreamMicro.computeBoolSize(i, this.value);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSizeDirectly(int i, Boolean bool) {
        return CodedOutputStreamMicro.computeBoolSize(i, bool.booleanValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeBool(i, this.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, Boolean bool) throws IOException {
        codedOutputStreamMicro.writeBool(i, bool.booleanValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        this.value = codedInputStreamMicro.readBool();
        setHasFlag(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public Boolean readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return Boolean.valueOf(codedInputStreamMicro.readBool());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void clear(Object obj) {
        if (obj instanceof Boolean) {
            this.value = ((Boolean) obj).booleanValue();
        } else {
            this.value = false;
        }
        setHasFlag(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void copyFrom(PBField<Boolean> pBField) {
        PBBoolField pBBoolField = (PBBoolField) pBField;
        set(pBBoolField.value, pBBoolField.has());
    }
}
