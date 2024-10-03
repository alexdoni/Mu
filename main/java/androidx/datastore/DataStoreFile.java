package androidx.datastore;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataStoreFile.kt */
@Metadata(m1394d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m1395d2 = {"dataStoreFile", "Ljava/io/File;", "Landroid/content/Context;", "fileName", "", "datastore_release"}, m1396k = 2, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class DataStoreFile {
    public static final File dataStoreFile(Context context, String fileName) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new File(context.getApplicationContext().getFilesDir(), Intrinsics.stringPlus("datastore/", fileName));
    }
}
