package com.xsdk.ab_firstbase.gson.internal.bind;

import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.JsonSyntaxException;
import com.xsdk.ab_firstbase.gson.TypeAdapter;
import com.xsdk.ab_firstbase.gson.TypeAdapterFactory;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;
import com.xsdk.ab_firstbase.gson.stream.JsonReader;
import com.xsdk.ab_firstbase.gson.stream.JsonToken;
import com.xsdk.ab_firstbase.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes3.dex */
public final class TimeTypeAdapter extends TypeAdapter<Time> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.xsdk.ab_firstbase.gson.internal.bind.TimeTypeAdapter.1
        @Override // com.xsdk.ab_firstbase.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Time.class) {
                return new TimeTypeAdapter();
            }
            return null;
        }
    };
    private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

    @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
    public synchronized Time read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new Time(this.format.parse(jsonReader.nextString()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
    public synchronized void write(JsonWriter jsonWriter, Time time) throws IOException {
        jsonWriter.value(time == null ? null : this.format.format((Date) time));
    }
}
