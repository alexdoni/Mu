package com.p017xx.commom.glide.manager;

import com.p017xx.commom.glide.RequestManager;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes3.dex */
final class EmptyRequestManagerTreeNode implements RequestManagerTreeNode {
    @Override // com.p017xx.commom.glide.manager.RequestManagerTreeNode
    public Set<RequestManager> getDescendants() {
        return Collections.emptySet();
    }
}
