package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public abstract class XMSSAddress {
    private final int keyAndMask;
    private final int layerAddress;
    private final long treeAddress;
    private final int type;

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSAddress(Builder builder) {
        this.layerAddress = builder.layerAddress;
        this.treeAddress = builder.treeAddress;
        this.type = builder.type;
        this.keyAndMask = builder.keyAndMask;
    }

    /* loaded from: classes3.dex */
    protected static abstract class Builder<T extends Builder> {
        private final int type;
        private int layerAddress = 0;
        private long treeAddress = 0;
        private int keyAndMask = 0;

        protected abstract XMSSAddress build();

        protected abstract T getThis();

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(int i) {
            this.type = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public T withLayerAddress(int i) {
            this.layerAddress = i;
            return getThis();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public T withTreeAddress(long j) {
            this.treeAddress = j;
            return getThis();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public T withKeyAndMask(int i) {
            this.keyAndMask = i;
            return getThis();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] toByteArray() {
        byte[] bArr = new byte[32];
        Pack.intToBigEndian(this.layerAddress, bArr, 0);
        Pack.longToBigEndian(this.treeAddress, bArr, 4);
        Pack.intToBigEndian(this.type, bArr, 12);
        Pack.intToBigEndian(this.keyAndMask, bArr, 28);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getLayerAddress() {
        return this.layerAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long getTreeAddress() {
        return this.treeAddress;
    }

    public final int getType() {
        return this.type;
    }

    public final int getKeyAndMask() {
        return this.keyAndMask;
    }
}
