package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            i2 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = 0;
        } else {
            i2 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i3 = 2;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (r2.mHorizontalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004e, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x004c, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x004a, code lost:
    
        if (r2.mVerticalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x052d  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03df A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01bc  */
    /* JADX WARN: Type inference failed for: r2v63, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v46 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r38, androidx.constraintlayout.core.LinearSystem r39, int r40, int r41, androidx.constraintlayout.core.widgets.ChainHead r42) {
        /*
            Method dump skipped, instructions count: 1418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Chain.applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead):void");
    }
}
