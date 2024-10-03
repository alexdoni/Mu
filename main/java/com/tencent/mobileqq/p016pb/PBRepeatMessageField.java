package com.tencent.mobileqq.p016pb;

import com.tencent.mobileqq.p016pb.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class PBRepeatMessageField<T extends MessageMicro<T>> extends PBField<List<T>> {
    private final Class<T> helper;
    private List<T> value = Collections.emptyList();

    public PBRepeatMessageField(Class<T> cls) {
        this.helper = cls;
    }

    public List<T> get() {
        if (this.value == Collections.emptyList()) {
            this.value = new ArrayList();
        }
        return this.value;
    }

    public T get(int i) {
        return this.value.get(i);
    }

    public void set(List<T> list) {
        this.value = list;
    }

    public void set(int i, T t) {
        this.value.set(i, t);
    }

    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    public boolean has() {
        return isEmpty();
    }

    public int size() {
        return this.value.size();
    }

    public void add(T t) {
        get().add(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addAll(Collection<T> collection) {
        get().addAll(collection);
    }

    public void remove(int i) {
        get().remove(i);
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSize(int i) {
        return computeSizeDirectly(i, (List) this.value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public int computeSizeDirectly(int i, List<T> list) {
        int i2 = 0;
        for (T t : list) {
            i2 += t.computeSizeDirectly(i, t);
        }
        return i2;
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        writeToDirectly(codedOutputStreamMicro, i, (List) this.value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, List<T> list) throws IOException {
        for (T t : list) {
            t.writeToDirectly(codedOutputStreamMicro, i, t);
        }
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        try {
            T newInstance = this.helper.newInstance();
            codedInputStreamMicro.readMessage(newInstance);
            add(newInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public List<T> readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        throw new RuntimeException("PBRepeatField not support readFromDirectly method.");
    }

    @Override // com.tencent.mobileqq.p016pb.PBField
    public void clear(Object obj) {
        this.value = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.mobileqq.p016pb.PBField
    public void copyFrom(PBField<List<T>> pBField) {
        PBRepeatMessageField pBRepeatMessageField = (PBRepeatMessageField) pBField;
        if (pBRepeatMessageField.isEmpty()) {
            this.value = Collections.emptyList();
            return;
        }
        List<T> list = get();
        Class<?> cls = pBRepeatMessageField.get(0).getClass();
        int size = pBRepeatMessageField.value.size() - list.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                try {
                    list.add((MessageMicro) cls.newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (size < 0) {
            list.subList(-size, list.size()).clear();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((MessageMicro) list.get(i2)).copyFrom(pBRepeatMessageField.value.get(i2));
        }
    }
}
