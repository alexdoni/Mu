package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.viewmodel.C0457R;

/* loaded from: classes.dex */
public class ViewTreeViewModelStoreOwner {
    private ViewTreeViewModelStoreOwner() {
    }

    public static void set(View view, ViewModelStoreOwner viewModelStoreOwner) {
        view.setTag(C0457R.id.view_tree_view_model_store_owner, viewModelStoreOwner);
    }

    public static ViewModelStoreOwner get(View view) {
        ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) view.getTag(C0457R.id.view_tree_view_model_store_owner);
        if (viewModelStoreOwner != null) {
            return viewModelStoreOwner;
        }
        Object parent = view.getParent();
        while (viewModelStoreOwner == null && (parent instanceof View)) {
            View view2 = (View) parent;
            viewModelStoreOwner = (ViewModelStoreOwner) view2.getTag(C0457R.id.view_tree_view_model_store_owner);
            parent = view2.getParent();
        }
        return viewModelStoreOwner;
    }
}
