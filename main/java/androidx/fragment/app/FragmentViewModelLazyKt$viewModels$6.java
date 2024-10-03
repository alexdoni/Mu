package androidx.fragment.app;

import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: FragmentViewModelLazy.kt */
@Metadata(m1394d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m1395d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelStore;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = CipherSuite.TLS_PSK_WITH_NULL_SHA256)
/* loaded from: classes.dex */
public final class FragmentViewModelLazyKt$viewModels$6 extends Lambda implements Function0<ViewModelStore> {
    final /* synthetic */ Lazy<ViewModelStoreOwner> $owner$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FragmentViewModelLazyKt$viewModels$6(Lazy<? extends ViewModelStoreOwner> lazy) {
        super(0);
        this.$owner$delegate = lazy;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ViewModelStore invoke() {
        ViewModelStoreOwner m1593viewModels$lambda1;
        m1593viewModels$lambda1 = FragmentViewModelLazyKt.m1593viewModels$lambda1(this.$owner$delegate);
        ViewModelStore viewModelStore = m1593viewModels$lambda1.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
        return viewModelStore;
    }
}
