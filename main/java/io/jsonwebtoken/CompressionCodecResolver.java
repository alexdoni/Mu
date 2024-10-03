package io.jsonwebtoken;

/* loaded from: classes3.dex */
public interface CompressionCodecResolver {
    CompressionCodec resolveCompressionCodec(Header header) throws CompressionException;
}
