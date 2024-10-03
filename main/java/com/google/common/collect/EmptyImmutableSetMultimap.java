package com.google.common.collect;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
class EmptyImmutableSetMultimap extends ImmutableSetMultimap<Object, Object> {
    static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableSetMultimap() {
        super(ImmutableMap.m382of(), 0, null);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
