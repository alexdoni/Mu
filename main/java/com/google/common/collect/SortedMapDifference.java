package com.google.common.collect;

import com.android.tools.r8.annotations.SynthesizedClassV2;
import com.google.common.collect.MapDifference;
import java.util.SortedMap;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public interface SortedMapDifference<K, V> extends MapDifference<K, V> {
    @Override // com.google.common.collect.MapDifference
    SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering();

    @Override // com.google.common.collect.MapDifference
    SortedMap<K, V> entriesInCommon();

    @Override // com.google.common.collect.MapDifference
    SortedMap<K, V> entriesOnlyOnLeft();

    @Override // com.google.common.collect.MapDifference
    SortedMap<K, V> entriesOnlyOnRight();

    @SynthesizedClassV2(kind = 8, versionHash = "7a5b85d3ee2e0991ca3502602e9389a98f55c0576b887125894a7ec03823f8d3")
    /* renamed from: com.google.common.collect.SortedMapDifference$-CC, reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC<K, V> {
    }
}
