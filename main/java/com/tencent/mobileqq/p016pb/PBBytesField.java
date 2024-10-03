package com.tencent.mobileqq.p016pb;

import java.io.IOException;

/* loaded from: classes3.dex */
public final class PBBytesField extends PBPrimitiveField<ByteStringMicro> {
    public static final PBBytesField __repeatHelper__ = new PBBytesField(ByteStringMicro.EMPTY, false);
    private ByteStringMicro value = ByteStringMicro.EMPTY;

    public PBBytesField(ByteStringMicro byteStringMicro, boolean z) {
        set(byteStringMicro, z);
    }

    public ByteStringMicro get() {
        return this.value;
    }

    public void set(ByteStringMicro byteStringMicro, boolean z) {
        this.value = byteStringMicro;
        setHasFlag(z);
    }

    public void set(ByteStringMicro byteStringMicro) {
        set(byteStringMicro, true);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSize(int i) {
        if (has()) {
            return CodedOutputStreamMicro.computeBytesSize(i, this.value);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSizeDirectly(int i, ByteStringMicro byteStringMicro) {
        return CodedOutputStreamMicro.computeBytesSize(i, byteStringMicro);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeBytes(i, this.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, ByteStringMicro byteStringMicro) throws IOException {
        codedOutputStreamMicro.writeBytes(i, byteStringMicro);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        this.value = codedInputStreamMicro.readBytes();
        setHasFlag(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public ByteStringMicro readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return codedInputStreamMicro.readBytes();
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void clear(Object obj) {
        if (obj instanceof ByteStringMicro) {
            this.value = (ByteStringMicro) obj;
        } else {
            this.value = ByteStringMicro.EMPTY;
        }
        setHasFlag(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void copyFrom(PBField<ByteStringMicro> pBField) {
        PBBytesField pBBytesField = (PBBytesField) pBField;
        set(pBBytesField.value, pBBytesField.has());
    }
}
