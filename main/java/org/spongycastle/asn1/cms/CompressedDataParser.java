package org.spongycastle.asn1.cms;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes3.dex */
public class CompressedDataParser {
    private AlgorithmIdentifier _compressionAlgorithm;
    private ContentInfoParser _encapContentInfo;
    private ASN1Integer _version;

    public CompressedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this._version = (ASN1Integer) aSN1SequenceParser.readObject();
        this._compressionAlgorithm = AlgorithmIdentifier.getInstance(aSN1SequenceParser.readObject().toASN1Primitive());
        this._encapContentInfo = new ContentInfoParser((ASN1SequenceParser) aSN1SequenceParser.readObject());
    }

    public ASN1Integer getVersion() {
        return this._version;
    }

    public AlgorithmIdentifier getCompressionAlgorithmIdentifier() {
        return this._compressionAlgorithm;
    }

    public ContentInfoParser getEncapContentInfo() {
        return this._encapContentInfo;
    }
}
