package kotlin.io;

import com.facebook.share.internal.ShareInternalUtility;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Utils.kt */
@Metadata(m1394d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m1395d2 = {"Lkotlin/io/TerminateException;", "Lkotlin/io/FileSystemException;", ShareInternalUtility.STAGING_PARAM, "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
final class TerminateException extends FileSystemException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TerminateException(File file) {
        super(file, null, null, 6, null);
        Intrinsics.checkNotNullParameter(file, "file");
    }
}
