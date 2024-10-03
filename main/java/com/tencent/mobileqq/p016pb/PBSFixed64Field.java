package com.tencent.mobileqq.p016pb;

import java.io.IOException;

/* loaded from: classes3.dex */
public final class PBSFixed64Field extends PBPrimitiveField<Long> {
    public static final PBSFixed64Field __repeatHelper__ = new PBSFixed64Field(0, false);
    private long value = 0;

    public PBSFixed64Field(long j, boolean z) {
        set(j, z);
    }

    public long get() {
        return this.value;
    }

    public void set(long j, boolean z) {
        this.value = j;
        setHasFlag(z);
    }

    public void set(long j) {
        set(j, true);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSize(int i) {
        if (has()) {
            return CodedOutputStreamMicro.computeSFixed64Size(i, this.value);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSizeDirectly(int i, Long l) {
        return CodedOutputStreamMicro.computeSFixed64Size(i, l.longValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeSFixed64(i, this.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, Long l) throws IOException {
        codedOutputStreamMicro.writeSFixed64(i, l.longValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        this.value = codedInputStreamMicro.readSFixed64();
        setHasFlag(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public Long readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return Long.valueOf(codedInputStreamMicro.readSFixed64());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void clear(Object obj) {
        if (obj instanceof Long) {
            this.value = ((Long) obj).longValue();
        } else {
            this.value = 0L;
        }
        setHasFlag(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void copyFrom(PBField<Long> pBField) {
        PBSFixed64Field pBSFixed64Field = (PBSFixed64Field) pBField;
        set(pBSFixed64Field.value, pBSFixed64Field.has());
    }
}
