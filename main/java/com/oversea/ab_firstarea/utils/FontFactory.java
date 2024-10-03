package com.oversea.ab_firstarea.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/* loaded from: classes.dex */
public class FontFactory implements LayoutInflater.Factory {
    private Typeface typeface;

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        try {
            if (this.typeface == null) {
                this.typeface = Typeface.createFromAsset(context.getAssets(), "font/tahoma.TTF");
            }
            if (str.equals("TextView")) {
                TextView textView = new TextView(context, attributeSet);
                textView.setTypeface(this.typeface);
                return textView;
            }
            if (str.equals("Button")) {
                Button button = new Button(context, attributeSet);
                button.setTypeface(this.typeface);
                return button;
            }
            if (!str.equals("EditText")) {
                return null;
            }
            EditText editText = new EditText(context, attributeSet);
            editText.setTypeface(this.typeface);
            return editText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
