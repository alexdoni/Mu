package com.google.common.collect;

import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public interface RowSortedTable<R, C, V> extends Table<R, C, V> {
    @Override // com.google.common.collect.Table
    SortedSet<R> rowKeySet();

    @Override // com.google.common.collect.Table
    SortedMap<R, Map<C, V>> rowMap();

    @SynthesizedClassV2(kind = 8, versionHash = "7a5b85d3ee2e0991ca3502602e9389a98f55c0576b887125894a7ec03823f8d3")
    /* renamed from: com.google.common.collect.RowSortedTable$-CC, reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC<R, C, V> {
    }
}
