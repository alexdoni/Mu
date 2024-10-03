package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tencent.p014av.ptt.PttError;

/* renamed from: com.unity3d.player.l */
/* loaded from: classes3.dex */
public final class DialogC2716l extends Dialog implements TextWatcher, View.OnClickListener {

    /* renamed from: d */
    private static int f1823d = 1627389952;

    /* renamed from: e */
    private static int f1824e = -1;

    /* renamed from: a */
    boolean f1825a;

    /* renamed from: b */
    private Context f1826b;

    /* renamed from: c */
    private UnityPlayer f1827c;

    /* renamed from: f */
    private int f1828f;

    public DialogC2716l(Context context, UnityPlayer unityPlayer, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2, boolean z4, boolean z5) {
        super(context);
        this.f1826b = context;
        this.f1827c = unityPlayer;
        Window window = getWindow();
        this.f1825a = z5;
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        final View createSoftInputView = createSoftInputView();
        setContentView(createSoftInputView);
        window.setLayout(-1, -2);
        window.clearFlags(2);
        window.clearFlags(134217728);
        window.clearFlags(67108864);
        if (!this.f1825a) {
            window.addFlags(32);
            window.addFlags(262144);
        }
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        m1339a(editText, str, i, z, z2, z3, str2, i2);
        button.setOnClickListener(this);
        this.f1828f = editText.getCurrentTextColor();
        m1349a(z4);
        this.f1827c.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.unity3d.player.l.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                if (createSoftInputView.isShown()) {
                    Rect rect = new Rect();
                    DialogC2716l.this.f1827c.getWindowVisibleDisplayFrame(rect);
                    int[] iArr = new int[2];
                    DialogC2716l.this.f1827c.getLocationOnScreen(iArr);
                    Point point = new Point(rect.left - iArr[0], rect.height() - createSoftInputView.getHeight());
                    Point point2 = new Point();
                    DialogC2716l.this.getWindow().getWindowManager().getDefaultDisplay().getSize(point2);
                    int height = DialogC2716l.this.f1827c.getHeight() - point2.y;
                    int height2 = DialogC2716l.this.f1827c.getHeight() - point.y;
                    if (height2 != height + createSoftInputView.getHeight()) {
                        DialogC2716l.this.f1827c.reportSoftInputIsVisible(true);
                    } else {
                        DialogC2716l.this.f1827c.reportSoftInputIsVisible(false);
                    }
                    DialogC2716l.this.f1827c.reportSoftInputArea(new Rect(point.x, point.y, createSoftInputView.getWidth(), height2));
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.unity3d.player.l.2
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z6) {
                if (z6) {
                    DialogC2716l.this.getWindow().setSoftInputMode(5);
                }
            }
        });
        editText.requestFocus();
    }

    /* renamed from: a */
    private static int m1337a(int i, boolean z, boolean z2, boolean z3) {
        int i2 = (z ? 32768 : 524288) | (z2 ? 131072 : 0) | (z3 ? 128 : 0);
        if (i < 0 || i > 11) {
            return i2;
        }
        int i3 = new int[]{1, 16385, PttError.VOICE_DOWNLOAD_SIGN_CHECK_FAIL, 17, 2, 3, 8289, 33, 1, 16417, 17, 8194}[i];
        return (i3 & 2) != 0 ? i3 : i3 | i2;
    }

    /* renamed from: a */
    private void m1339a(EditText editText, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2) {
        editText.setImeOptions(6);
        editText.setText(str);
        editText.setHint(str2);
        editText.setHintTextColor(f1823d);
        editText.setInputType(m1337a(i, z, z2, z3));
        editText.setImeOptions(33554432);
        if (i2 > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        }
        editText.addTextChangedListener(this);
        editText.setSelection(editText.getText().length());
        editText.setClickable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1341a(String str, boolean z) {
        ((EditText) findViewById(1057292289)).setSelection(0, 0);
        this.f1827c.reportSoftInputStr(str, 1, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public String m1342b() {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText == null) {
            return null;
        }
        return editText.getText().toString().trim();
    }

    /* renamed from: a */
    public final String m1345a() {
        InputMethodSubtype currentInputMethodSubtype = ((InputMethodManager) this.f1826b.getSystemService("input_method")).getCurrentInputMethodSubtype();
        if (currentInputMethodSubtype == null) {
            return null;
        }
        String locale = currentInputMethodSubtype.getLocale();
        if (locale != null && !locale.equals("")) {
            return locale;
        }
        return currentInputMethodSubtype.getMode() + " " + currentInputMethodSubtype.getExtraValue();
    }

    /* renamed from: a */
    public final void m1346a(int i) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            if (i > 0) {
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
            } else {
                editText.setFilters(new InputFilter[0]);
            }
        }
    }

    /* renamed from: a */
    public final void m1347a(int i, int i2) {
        int i3;
        EditText editText = (EditText) findViewById(1057292289);
        if (editText == null || editText.getText().length() < (i3 = i2 + i)) {
            return;
        }
        editText.setSelection(i, i3);
    }

    /* renamed from: a */
    public final void m1348a(String str) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
    }

    /* renamed from: a */
    public final void m1349a(boolean z) {
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        View findViewById = findViewById(1057292291);
        if (!z) {
            editText.setBackgroundColor(f1824e);
            editText.setTextColor(this.f1828f);
            editText.setCursorVisible(true);
            editText.setOnClickListener(null);
            editText.setLongClickable(true);
            editText.setTextIsSelectable(true);
            button.setClickable(true);
            button.setTextColor(this.f1828f);
            findViewById.setBackgroundColor(f1824e);
            return;
        }
        editText.setBackgroundColor(0);
        editText.setTextColor(0);
        editText.setCursorVisible(false);
        editText.setOnClickListener(this);
        editText.setHighlightColor(0);
        editText.setLongClickable(false);
        editText.setTextIsSelectable(false);
        button.setTextColor(0);
        findViewById.setBackgroundColor(0);
        findViewById.setOnClickListener(this);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.f1827c.reportSoftInputStr(editable.toString(), 0, false);
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected final View createSoftInputView() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f1826b);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(f1824e);
        relativeLayout.setId(1057292291);
        EditText editText = new EditText(this.f1826b) { // from class: com.unity3d.player.l.3
            @Override // android.widget.TextView, android.view.View
            public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
                if (i == 4) {
                    DialogC2716l dialogC2716l = DialogC2716l.this;
                    dialogC2716l.m1341a(dialogC2716l.m1342b(), true);
                    return true;
                }
                if (i == 84) {
                    return true;
                }
                return super.onKeyPreIme(i, keyEvent);
            }

            @Override // android.widget.TextView
            protected final void onSelectionChanged(int i, int i2) {
                DialogC2716l.this.f1827c.reportSoftInputSelection(i, i2 - i);
            }

            @Override // android.widget.TextView, android.view.View
            public final void onWindowFocusChanged(boolean z) {
                super.onWindowFocusChanged(z);
                if (z) {
                    ((InputMethodManager) DialogC2716l.this.f1826b.getSystemService("input_method")).showSoftInput(this, 0);
                }
            }
        };
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        editText.setLayoutParams(layoutParams);
        editText.setId(1057292289);
        relativeLayout.addView(editText);
        Button button = new Button(this.f1826b);
        button.setText(this.f1826b.getResources().getIdentifier("ok", TypedValues.Custom.S_STRING, "android"));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        button.setLayoutParams(layoutParams2);
        button.setId(1057292290);
        button.setBackgroundColor(0);
        relativeLayout.addView(button);
        ((EditText) relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.unity3d.player.l.4
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    DialogC2716l dialogC2716l = DialogC2716l.this;
                    dialogC2716l.m1341a(dialogC2716l.m1342b(), false);
                }
                return false;
            }
        });
        relativeLayout.setPadding(16, 16, 16, 16);
        return relativeLayout;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f1825a || motionEvent.getAction() != 4) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        m1341a(m1342b(), true);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        m1341a(m1342b(), false);
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
