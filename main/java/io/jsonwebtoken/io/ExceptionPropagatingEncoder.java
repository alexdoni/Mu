package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Assert;

/* loaded from: classes3.dex */
class ExceptionPropagatingEncoder<T, R> implements Encoder<T, R> {
    private final Encoder<T, R> encoder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExceptionPropagatingEncoder(Encoder<T, R> encoder) {
        Assert.notNull(encoder, "Encoder cannot be null.");
        this.encoder = encoder;
    }

    @Override // io.jsonwebtoken.io.Encoder
    public R encode(T t) throws EncodingException {
        Assert.notNull(t, "Encode argument cannot be null.");
        try {
            return this.encoder.encode(t);
        } catch (EncodingException e) {
            throw e;
        } catch (Exception e2) {
            throw new EncodingException("Unable to encode input: " + e2.getMessage(), e2);
        }
    }
}
