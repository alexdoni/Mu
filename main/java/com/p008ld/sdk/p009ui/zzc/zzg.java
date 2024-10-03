package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: BaseStackView.java */
/* loaded from: classes2.dex */
public class zzg {
    public Activity zza;
    public View zzb;
    private SpannableStringBuilder zzc = new SpannableStringBuilder();

    public void zza() {
    }

    public zzg(Activity activity, String str) {
        this.zza = activity;
        zza(activity, str);
    }

    private void zza(Activity activity, String str) {
        this.zzb = zzt.zza((Context) activity, str, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean zza(Activity activity, View.OnClickListener onClickListener) {
        zza((Context) activity, onClickListener);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zza(LDUser lDUser, LDException lDException, View.OnClickListener onClickListener) {
        if (lDException == null) {
            if (lDUser.getMustBindEmail().booleanValue()) {
                View view = new View(this.zza);
                view.setTag(42);
                view.setOnClickListener(onClickListener);
                view.performClick();
            } else {
                this.zza.finish();
            }
            UserAccountMgr.zza().zza(true, lDUser.getSpaceUserId(), lDUser.getCpToken(), "");
            return;
        }
        LDUtil.toast(lDException.toString());
        UserAccountMgr.zza().zza(false, "", "", lDException.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean zza(boolean z, String str, String str2, String str3) {
        if (!z) {
            LDUtil.toast(str3);
            UserAccountMgr.zza().zza(false, "", "", str3);
            return false;
        }
        UserAccountMgr.zza().zza(true, str, str2, str3);
        this.zza.finish();
        return true;
    }

    /* compiled from: AutoLoginView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzg$2 */
    /* loaded from: classes2.dex */
    class ViewOnClickListenerC19642 implements View.OnClickListener {
        final /* synthetic */ View.OnClickListener zza;

        ViewOnClickListenerC19642(View.OnClickListener onClickListener) {
            this.zza = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            zzg.this.zzc();
            this.zza.onClick(view);
        }
    }

    protected void zza(Context context, View.OnClickListener onClickListener) {
        View view = new View(context);
        view.setTag(13);
        view.setOnClickListener(onClickListener);
        view.performClick();
    }

    public void zza(Context context, View.OnClickListener onClickListener, int i) {
        View view = new View(context);
        view.setTag(Integer.valueOf(i));
        view.setOnClickListener(onClickListener);
        view.performClick();
    }

    public int zza(String str) {
        return zzt.zze(this.zzb.getContext(), str);
    }

    public void zza(TextView textView) {
        textView.getPaint().setAntiAlias(true);
    }

    /* compiled from: AutoLoginView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzg$3 */
    /* loaded from: classes2.dex */
    class C19653 implements LDQueryCallback<LDUser> {
        C19653() {
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            zzg.zza(zzg.this).stop();
            if (lDException == null) {
                if (zzg.this.zza(true, lDUser.getSpaceUserId(), lDUser.getCpToken(), "")) {
                    return;
                }
                zzg.this.zzb();
            } else {
                zzg.this.zza(false, "", "", lDException.toString());
                View view = new View(zzg.this.zza);
                view.setTag(11);
                view.setOnClickListener(zzg.zzb(zzg.this));
                view.performClick();
            }
        }
    }

    public void zza(TextView textView, View.OnClickListener onClickListener) {
        String charSequence = textView.getText().toString();
        int indexOf = charSequence.indexOf("%1");
        int lastIndexOf = charSequence.lastIndexOf("%1") - 2;
        String replace = charSequence.replace("%1", "");
        int indexOf2 = replace.indexOf("%2");
        int lastIndexOf2 = replace.lastIndexOf("%2") - 2;
        String replace2 = replace.replace("%2", "");
        this.zzc.append((CharSequence) replace2);
        zza(replace2, 33, indexOf, lastIndexOf, onClickListener);
        zza(replace2, 35, indexOf2, lastIndexOf2, onClickListener);
        textView.setText(this.zzc);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void zza(String str, final int i, int i2, int i3, final View.OnClickListener onClickListener) {
        this.zzc.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), i2, i3, 33);
        Matcher matcher = Pattern.compile(str.substring(i2, i3)).matcher(str);
        while (matcher.find()) {
            ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.ld.sdk.ui.zzc.zzg.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    View view2 = new View(view.getContext());
                    view2.setOnClickListener(onClickListener);
                    view2.setTag(Integer.valueOf(i));
                    view2.performClick();
                }
            };
            int start = matcher.start();
            int end = matcher.end();
            this.zzc.setSpan(clickableSpan, start, end, 33);
            this.zzc.setSpan(new zza(), start, end, 33);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BaseStackView.java */
    /* loaded from: classes2.dex */
    public static class zza extends UnderlineSpan {
        zza() {
        }

        @Override // android.text.style.UnderlineSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(Color.parseColor("#499DFF"));
            textPaint.setUnderlineText(false);
        }
    }
}
