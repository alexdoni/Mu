package com.xsdk.ab_firstbase.gson.internal;

import com.xsdk.ab_firstbase.gson.stream.JsonReader;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class JsonReaderInternalAccess {
    public static JsonReaderInternalAccess INSTANCE;

    public abstract void promoteNameToValue(JsonReader jsonReader) throws IOException;
}
