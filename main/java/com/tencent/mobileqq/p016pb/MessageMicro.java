package com.tencent.mobileqq.p016pb;

import com.tencent.mobileqq.p016pb.MessageMicro;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

/* loaded from: classes3.dex */
public abstract class MessageMicro<T extends MessageMicro<T>> extends PBPrimitiveField<T> {
    private FieldMap _fields = null;
    private int cachedSize = -1;

    public static void main(String[] strArr) throws Exception {
    }

    public T get() {
        return this;
    }

    public final byte[] toByteArray() {
        int serializedSize = getSerializedSize();
        byte[] bArr = new byte[serializedSize];
        toByteArray(bArr, 0, serializedSize);
        return bArr;
    }

    public final void toByteArray(byte[] bArr, int i, int i2) {
        try {
            CodedOutputStreamMicro newInstance = CodedOutputStreamMicro.newInstance(bArr, i, i2);
            writeTo(newInstance);
            newInstance.checkNoSpaceLeft();
        } catch (IOException unused) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }

    public final T mergeFrom(byte[] bArr) throws InvalidProtocolBufferMicroException {
        return mergeFrom(bArr, 0, bArr.length);
    }

    public final T mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferMicroException {
        try {
            CodedInputStreamMicro newInstance = CodedInputStreamMicro.newInstance(bArr, i, i2);
            mergeFrom(newInstance);
            newInstance.checkLastTagWas(0);
            return this;
        } catch (InvalidProtocolBufferMicroException e) {
            throw e;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    protected boolean parseUnknownField(CodedInputStreamMicro codedInputStreamMicro, int i) throws IOException {
        return codedInputStreamMicro.skipField(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static final class FieldMap {
        private Object[] defaultValues;
        private Field[] fields;
        private int[] tags;

        FieldMap(int[] iArr, String[] strArr, Object[] objArr, Class<?> cls) {
            this.tags = iArr;
            this.defaultValues = objArr;
            this.fields = new Field[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                try {
                    this.fields[i] = cls.getField(strArr[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Field get(int i) {
            int binarySearch = Arrays.binarySearch(this.tags, i);
            if (binarySearch < 0) {
                return null;
            }
            return this.fields[binarySearch];
        }

        void clear(MessageMicro<?> messageMicro) throws IllegalArgumentException, IllegalAccessException {
            for (int i = 0; i < this.tags.length; i++) {
                ((PBField) this.fields[i].get(messageMicro)).clear(this.defaultValues[i]);
            }
        }

        int getSerializedSize(MessageMicro<?> messageMicro) throws IllegalArgumentException, IllegalAccessException {
            int i = 0;
            int i2 = 0;
            while (true) {
                int[] iArr = this.tags;
                if (i >= iArr.length) {
                    return i2;
                }
                i2 += ((PBField) this.fields[i].get(messageMicro)).computeSize(WireFormatMicro.getTagFieldNumber(iArr[i]));
                i++;
            }
        }

        void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, MessageMicro<?> messageMicro) throws IllegalArgumentException, IllegalAccessException, IOException {
            int i = 0;
            while (true) {
                int[] iArr = this.tags;
                if (i >= iArr.length) {
                    return;
                }
                ((PBField) this.fields[i].get(messageMicro)).writeTo(codedOutputStreamMicro, WireFormatMicro.getTagFieldNumber(iArr[i]));
                i++;
            }
        }

        public boolean readFieldFrom(CodedInputStreamMicro codedInputStreamMicro, int i, MessageMicro<?> messageMicro) throws IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
            int binarySearch = Arrays.binarySearch(this.tags, i);
            if (binarySearch < 0) {
                return false;
            }
            ((PBField) this.fields[binarySearch].get(messageMicro)).readFrom(codedInputStreamMicro);
            return true;
        }

        <U extends MessageMicro<U>> void copyFields(U u, U u2) throws IllegalArgumentException, IllegalAccessException {
            for (int i = 0; i < this.tags.length; i++) {
                Field field = this.fields[i];
                ((PBField) field.get(u)).copyFrom((PBField) field.get(u2));
            }
        }
    }

    private final FieldMap getFieldMap() {
        if (this._fields == null) {
            try {
                Field declaredField = getClass().getDeclaredField("__fieldMap__");
                declaredField.setAccessible(true);
                this._fields = (FieldMap) declaredField.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
            } catch (SecurityException e4) {
                e4.printStackTrace();
            }
        }
        return this._fields;
    }

    protected static FieldMap initFieldMap(int[] iArr, String[] strArr, Object[] objArr, Class<?> cls) {
        return new FieldMap(iArr, strArr, objArr, cls);
    }

    public final int getCachedSize() {
        return getSerializedSize();
    }

    public final int getSerializedSize() {
        int i;
        try {
            i = getFieldMap().getSerializedSize(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            i = -1;
            this.cachedSize = i;
            return i;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            i = -1;
            this.cachedSize = i;
            return i;
        }
        this.cachedSize = i;
        return i;
    }

    public final void writeTo(CodedOutputStreamMicro codedOutputStreamMicro) throws IOException {
        try {
            getFieldMap().writeTo(codedOutputStreamMicro, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    public final T mergeFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        FieldMap fieldMap = getFieldMap();
        setHasFlag(true);
        while (true) {
            int readTag = codedInputStreamMicro.readTag();
            try {
                if (!fieldMap.readFieldFrom(codedInputStreamMicro, readTag, this) && (readTag == 0 || !parseUnknownField(codedInputStreamMicro, readTag))) {
                    return this;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSize(int i) {
        if (has()) {
            return CodedOutputStreamMicro.computeMessageSize(i, this);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSizeDirectly(int i, T t) {
        return CodedOutputStreamMicro.computeMessageSize(i, t);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeMessage(i, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, T t) throws IOException {
        codedOutputStreamMicro.writeMessage(i, t);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        codedInputStreamMicro.readMessage(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public T readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        try {
            T t = (T) getClass().newInstance();
            codedInputStreamMicro.readMessage(t);
            return t;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void clear(Object obj) {
        try {
            getFieldMap().clear(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
        setHasFlag(false);
    }

    public void set(T t, boolean z) {
        copyFrom(t);
        setHasFlag(z);
        this.cachedSize = -1;
    }

    public void set(T t) {
        set(t, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void copyFrom(PBField<T> pBField) {
        try {
            getFieldMap().copyFields(this, (MessageMicro) pBField);
            setHasFlag(((MessageMicro) pBField).has());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }
}
