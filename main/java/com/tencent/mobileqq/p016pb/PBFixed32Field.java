package com.tencent.mobileqq.p016pb;

import java.io.IOException;

/* loaded from: classes3.dex */
public final class PBFixed32Field extends PBPrimitiveField<Integer> {
    public static final PBFixed32Field __repeatHelper__ = new PBFixed32Field(0, false);
    private int value = 0;

    public PBFixed32Field(int i, boolean z) {
        set(i, z);
    }

    public int get() {
        return this.value;
    }

    public void set(int i, boolean z) {
        this.value = i;
        setHasFlag(z);
    }

    public void set(int i) {
        set(i, true);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSize(int i) {
        if (has()) {
            return CodedOutputStreamMicro.computeFixed32Size(i, this.value);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSizeDirectly(int i, Integer num) {
        return CodedOutputStreamMicro.computeFixed32Size(i, num.intValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeFixed32(i, this.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, Integer num) throws IOException {
        codedOutputStreamMicro.writeFixed32(i, num.intValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        this.value = codedInputStreamMicro.readFixed32();
        setHasFlag(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public Integer readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return Integer.valueOf(codedInputStreamMicro.readFixed32());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void clear(Object obj) {
        if (obj instanceof Integer) {
            this.value = ((Integer) obj).intValue();
        } else {
            this.value = 0;
        }
        setHasFlag(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void copyFrom(PBField<Integer> pBField) {
        PBFixed32Field pBFixed32Field = (PBFixed32Field) pBField;
        set(pBFixed32Field.value, pBFixed32Field.has());
    }
}
