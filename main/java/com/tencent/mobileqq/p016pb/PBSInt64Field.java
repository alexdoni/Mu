package com.tencent.mobileqq.p016pb;

import java.io.IOException;

/* loaded from: classes3.dex */
public final class PBSInt64Field extends PBPrimitiveField<Long> {
    public static final PBSInt64Field __repeatHelper__ = new PBSInt64Field(0, false);
    private long value = 0;

    public PBSInt64Field(long j, boolean z) {
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
            return CodedOutputStreamMicro.computeSInt64Size(i, this.value);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSizeDirectly(int i, Long l) {
        return CodedOutputStreamMicro.computeSInt64Size(i, l.longValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeSInt64(i, this.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, Long l) throws IOException {
        codedOutputStreamMicro.writeSInt64(i, l.longValue());
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        this.value = codedInputStreamMicro.readSInt64();
        setHasFlag(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public Long readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return Long.valueOf(codedInputStreamMicro.readSInt64());
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
        PBSInt64Field pBSInt64Field = (PBSInt64Field) pBField;
        set(pBSInt64Field.value, pBSInt64Field.has());
    }
}
