package org.spongycastle.x509.util;

import java.util.Collection;

/* loaded from: classes3.dex */
public interface StreamParser {
    Object read() throws StreamParsingException;

    Collection readAll() throws StreamParsingException;
}
