package kotlin.io.path;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.nio.file.Path;
import kotlin.Metadata;

/* compiled from: CopyActionContext.kt */
@Metadata(m1394d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m1395d2 = {"Lkotlin/io/path/CopyActionContext;", "", "copyToIgnoringExistingDirectory", "Lkotlin/io/path/CopyActionResult;", "Ljava/nio/file/Path;", TypedValues.AttributesType.S_TARGET, "followLinks", "", "kotlin-stdlib-jdk7"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
public interface CopyActionContext {
    CopyActionResult copyToIgnoringExistingDirectory(Path path, Path path2, boolean z);
}
