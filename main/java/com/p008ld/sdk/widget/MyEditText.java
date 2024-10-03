package com.p008ld.sdk.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import com.muglobal.p011ld.BuildConfig;
import com.p008ld.sdk.util.zzt;
import java.util.Locale;

/* loaded from: classes2.dex */
public class MyEditText extends EditText {
    private boolean isSetSize;
    private int length;
    private String mLan;
    private TextWatcherListener mTextWatcherListener;
    public TextWatcher textWatcher;

    /* loaded from: classes2.dex */
    public interface TextWatcherListener {
        void afterTextChanged(EditText editText, String str, int i);
    }

    public MyEditText(Context context) {
        super(context);
        this.textWatcher = new TextWatcher() { // from class: com.ld.sdk.widget.MyEditText.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                MyEditText.this.setEditTextSize(obj);
                MyEditText.this.length = obj.length();
                if (MyEditText.this.mTextWatcherListener != null) {
                    TextWatcherListener textWatcherListener = MyEditText.this.mTextWatcherListener;
                    MyEditText myEditText = MyEditText.this;
                    textWatcherListener.afterTextChanged(myEditText, obj, myEditText.length);
                }
            }
        };
        initView();
    }

    public MyEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textWatcher = new TextWatcher() { // from class: com.ld.sdk.widget.MyEditText.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                MyEditText.this.setEditTextSize(obj);
                MyEditText.this.length = obj.length();
                if (MyEditText.this.mTextWatcherListener != null) {
                    TextWatcherListener textWatcherListener = MyEditText.this.mTextWatcherListener;
                    MyEditText myEditText = MyEditText.this;
                    textWatcherListener.afterTextChanged(myEditText, obj, myEditText.length);
                }
            }
        };
        initView();
    }

    public MyEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textWatcher = new TextWatcher() { // from class: com.ld.sdk.widget.MyEditText.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i22, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i22, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                MyEditText.this.setEditTextSize(obj);
                MyEditText.this.length = obj.length();
                if (MyEditText.this.mTextWatcherListener != null) {
                    TextWatcherListener textWatcherListener = MyEditText.this.mTextWatcherListener;
                    MyEditText myEditText = MyEditText.this;
                    textWatcherListener.afterTextChanged(myEditText, obj, myEditText.length);
                }
            }
        };
        initView();
    }

    private void initView() {
        this.mLan = Locale.getDefault().getLanguage();
        setTypeface(Typeface.DEFAULT);
        addTextChangedListener(this.textWatcher);
        String str = this.mLan;
        if (str != null && str.equals("zh")) {
            setTextSize(0, (int) getContext().getResources().getDimension(zzt.zzc(getContext(), "ld_sp_13")));
        } else {
            setTextSize(0, (int) getContext().getResources().getDimension(zzt.zzc(getContext(), "ld_sp_12")));
        }
    }

    @Override // android.widget.TextView
    public void addTextChangedListener(TextWatcher textWatcher) {
        super.addTextChangedListener(textWatcher);
    }

    public void setTextWatcherListener(TextWatcherListener textWatcherListener) {
        this.mTextWatcherListener = textWatcherListener;
    }

    public void setEditTextSize(String str) {
        if (str.length() >= 1 && !this.isSetSize) {
            this.isSetSize = true;
            String str2 = this.mLan;
            if (str2 != null && str2.equals("zh")) {
                setTextSize(0, (int) getContext().getResources().getDimension(zzt.zzc(getContext(), "ld_sp_13")));
            }
        } else if (str.length() == 0) {
            this.isSetSize = false;
            String str3 = this.mLan;
            if (str3 != null && str3.equals("zh")) {
                setTextSize(0, (int) getContext().getResources().getDimension(zzt.zzc(getContext(), "ld_sp_11")));
            }
        }
        setTypeface(Typeface.DEFAULT);
    }

    public int getTextLength() {
        return this.length;
    }

    public void seePwdClick(ImageView imageView, boolean z) {
        if (getInputType() == 129 && !z) {
            imageView.setImageResource(zzt.zzd(getContext(), "ld_show_pwd_icon"));
            setInputType(1);
        } else {
            setInputType(BuildConfig.VERSION_CODE);
            imageView.setImageResource(zzt.zzd(getContext(), "ld_org_see_pwd_selector"));
        }
        if (this.length >= 1) {
            setSelection(getText().length());
        }
        setTypeface(Typeface.DEFAULT);
    }
}
