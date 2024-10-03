package com.p017xx.commom.glide.load.engine.cache;

import androidx.core.util.Pools;
import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.util.LruCache;
import com.p017xx.commom.glide.util.Preconditions;
import com.p017xx.commom.glide.util.Util;
import com.p017xx.commom.glide.util.pool.FactoryPools;
import com.p017xx.commom.glide.util.pool.StateVerifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes3.dex */
public class SafeKeyGenerator {
    private final LruCache<Key, String> loadIdToSafeHash = new LruCache<>(1000);
    private final Pools.Pool<PoolableDigestContainer> digestPool = FactoryPools.threadSafe(10, new FactoryPools.Factory<PoolableDigestContainer>() { // from class: com.xx.commom.glide.load.engine.cache.SafeKeyGenerator.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.xx.commom.glide.util.pool.FactoryPools.Factory
        public PoolableDigestContainer create() {
            try {
                return new PoolableDigestContainer(MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    });

    public String getSafeKey(Key key) {
        String str;
        synchronized (this.loadIdToSafeHash) {
            str = this.loadIdToSafeHash.get(key);
        }
        if (str == null) {
            str = calculateHexStringDigest(key);
        }
        synchronized (this.loadIdToSafeHash) {
            this.loadIdToSafeHash.put(key, str);
        }
        return str;
    }

    private String calculateHexStringDigest(Key key) {
        PoolableDigestContainer poolableDigestContainer = (PoolableDigestContainer) Preconditions.checkNotNull(this.digestPool.acquire());
        try {
            key.updateDiskCacheKey(poolableDigestContainer.messageDigest);
            return Util.sha256BytesToHex(poolableDigestContainer.messageDigest.digest());
        } finally {
            this.digestPool.release(poolableDigestContainer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PoolableDigestContainer implements FactoryPools.Poolable {
        final MessageDigest messageDigest;
        private final StateVerifier stateVerifier = StateVerifier.newInstance();

        PoolableDigestContainer(MessageDigest messageDigest) {
            this.messageDigest = messageDigest;
        }

        @Override // com.xx.commom.glide.util.pool.FactoryPools.Poolable
        public StateVerifier getVerifier() {
            return this.stateVerifier;
        }
    }
}
