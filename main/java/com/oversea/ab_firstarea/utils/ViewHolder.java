package com.oversea.ab_firstarea.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.oversea.ab_firstplatform.statistics.utils.image.MyImageView;
import com.p017xx.commom.glide.Glide;

/* loaded from: classes.dex */
public class ViewHolder {
    private CustomizedProgressBar customizedProgressBar;
    private Context mContext;
    private View mConvertView;
    private int mLayoutId;
    private int mPosition;
    private SparseArray<View> mViews = new SparseArray<>();

    public ViewHolder(Context context, ViewGroup viewGroup, int i, int i2) {
        this.mContext = context;
        this.mLayoutId = i;
        this.mPosition = i2;
        View inflate = LayoutInflater.from(context).inflate(i, viewGroup, false);
        this.mConvertView = inflate;
        inflate.setTag(this);
    }

    public static ViewHolder get(Context context, View view, ViewGroup viewGroup, int i, int i2) {
        if (view == null) {
            return new ViewHolder(context, viewGroup, i, i2);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mPosition = i2;
        return viewHolder;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public <T extends View> T getView(int i) {
        T t = (T) this.mViews.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.mConvertView.findViewById(i);
        this.mViews.put(i, t2);
        return t2;
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public CustomizedProgressBar getCustomizedProgressBar(int i, int i2) {
        if (this.customizedProgressBar == null) {
            this.customizedProgressBar = (CustomizedProgressBar) this.mConvertView.findViewById(i);
        }
        CustomizedProgressBar customizedProgressBar = this.customizedProgressBar;
        if (customizedProgressBar != null) {
            customizedProgressBar.setMaxCount(100.0f);
            this.customizedProgressBar.setCurrentCount(i2);
        }
        return this.customizedProgressBar;
    }

    public ViewHolder setText(int i, String str) {
        ((TextView) getView(i)).setText(str);
        return this;
    }

    public ViewHolder setTextSize(int i, int i2) {
        ((TextView) getView(i)).setTextSize(i2);
        return this;
    }

    public ViewHolder setTextDrawableLeft(int i, int i2) {
        TextView textView = (TextView) getView(i);
        if (i2 != 0) {
            Drawable drawable = this.mContext.getApplicationContext().getResources().getDrawable(i2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
        } else {
            textView.setCompoundDrawables(null, null, null, null);
        }
        return this;
    }

    public ViewHolder setText(int i, Spanned spanned) {
        ((TextView) getView(i)).setText(spanned);
        return this;
    }

    public ViewHolder setSize(int i, int i2, int i3) {
        ImageView imageView = (ImageView) getView(i);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        imageView.setLayoutParams(layoutParams);
        return this;
    }

    public ViewHolder setImageResource(int i, int i2) {
        ((ImageView) getView(i)).setImageResource(i2);
        return this;
    }

    public ViewHolder setImageUrl(int i, String str) {
        ((MyImageView) getView(i)).setImageURL(str);
        return this;
    }

    public ViewHolder setImageUrl2(int i, String str) {
        Glide.with(this.mContext).load(str).into((ImageView) getView(i));
        return this;
    }

    public ViewHolder setImageBitmap(int i, Bitmap bitmap) {
        ((ImageView) getView(i)).setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(int i, Drawable drawable) {
        ((ImageView) getView(i)).setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setBackgroundColor(int i, int i2) {
        getView(i).setBackgroundColor(i2);
        return this;
    }

    public ViewHolder setBackground(int i, Drawable drawable) {
        getView(i).setBackgroundDrawable(drawable);
        return this;
    }

    public ViewHolder setBackgroundRes(int i, int i2) {
        getView(i).setBackgroundResource(i2);
        return this;
    }

    public ViewHolder setTextColor(int i, int i2) {
        ((TextView) getView(i)).setTextColor(i2);
        return this;
    }

    public ViewHolder setTextColorRes(int i, int i2) {
        ((TextView) getView(i)).setTextColor(this.mContext.getApplicationContext().getResources().getColorStateList(i2));
        return this;
    }

    public ViewHolder setAlpha(int i, float f) {
        getView(i).setAlpha(f);
        return this;
    }

    public ViewHolder setVisible(int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 8);
        return this;
    }

    public ViewHolder setInVisible(int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 4);
        return this;
    }

    public ViewHolder linkify(int i) {
        Linkify.addLinks((TextView) getView(i), 15);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... iArr) {
        for (int i : iArr) {
            TextView textView = (TextView) getView(i);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | 128);
        }
        return this;
    }

    public ViewHolder setProgress(int i, int i2) {
        ((ProgressBar) getView(i)).setProgress(i2);
        return this;
    }

    public ViewHolder setProgress(int i, int i2, int i3) {
        ProgressBar progressBar = (ProgressBar) getView(i);
        progressBar.setMax(i3);
        progressBar.setProgress(i2);
        return this;
    }

    public ViewHolder setMax(int i, int i2) {
        ((ProgressBar) getView(i)).setMax(i2);
        return this;
    }

    public ViewHolder setRating(int i, float f) {
        ((RatingBar) getView(i)).setRating(f);
        return this;
    }

    public ViewHolder setRating(int i, float f, int i2) {
        RatingBar ratingBar = (RatingBar) getView(i);
        ratingBar.setMax(i2);
        ratingBar.setRating(f);
        return this;
    }

    public ViewHolder setRatingNum(int i, float f, int i2) {
        RatingBar ratingBar = (RatingBar) getView(i);
        ratingBar.setNumStars(i2);
        ratingBar.setRating(f);
        return this;
    }

    public ViewHolder setTag(int i, Object obj) {
        getView(i).setTag(obj);
        return this;
    }

    public ViewHolder setTag(int i, int i2, Object obj) {
        getView(i).setTag(i2, obj);
        return this;
    }

    public ViewHolder setChecked(int i, boolean z) {
        ((Checkable) getView(i)).setChecked(z);
        return this;
    }

    public ViewHolder setClickable(int i, boolean z) {
        getView(i).setClickable(z);
        return this;
    }

    public ViewHolder setEnable(int i, boolean z) {
        getView(i).setEnabled(z);
        return this;
    }

    public ViewHolder setOnClickListener(int i, View.OnClickListener onClickListener) {
        getView(i).setOnClickListener(onClickListener);
        return this;
    }

    public ViewHolder setOnItemClickListener(int i, AdapterView.OnItemClickListener onItemClickListener) {
        ((AdapterView) getView(i)).setOnItemClickListener(onItemClickListener);
        return this;
    }

    public ViewHolder setOnTouchListener(int i, View.OnTouchListener onTouchListener) {
        getView(i).setOnTouchListener(onTouchListener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int i, View.OnLongClickListener onLongClickListener) {
        getView(i).setOnLongClickListener(onLongClickListener);
        return this;
    }

    public ViewHolder setAdapter(int i, CommonAdapter commonAdapter) {
        ((GridView) getView(i)).setAdapter((ListAdapter) commonAdapter);
        return this;
    }
}
