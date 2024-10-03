package okio.internal;

import com.liulishuo.filedownloader.model.FileDownloadModel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: -FileSystem.kt */
@Metadata(m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "okio.internal._FileSystemKt", m1415f = "-FileSystem.kt", m1416i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, m1417l = {113, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA}, m1418m = "collectRecursively", m1419n = {"$this$collectRecursively", "fileSystem", "stack", FileDownloadModel.PATH, "followSymlinks", "postorder", "$this$collectRecursively", "fileSystem", "stack", FileDownloadModel.PATH, "followSymlinks", "postorder"}, m1420s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1"})
/* loaded from: classes3.dex */
public final class _FileSystemKt$collectRecursively$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public _FileSystemKt$collectRecursively$1(Continuation<? super _FileSystemKt$collectRecursively$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return _FileSystemKt.collectRecursively(null, null, null, null, false, false, this);
    }
}
