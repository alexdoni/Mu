package androidx.datastore.preferences.protobuf;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
