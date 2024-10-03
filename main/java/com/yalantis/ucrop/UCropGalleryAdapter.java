package com.yalantis.ucrop;

import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* loaded from: classes3.dex */
public class UCropGalleryAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int currentSelectPosition;
    private final List<String> list;
    private OnItemClickListener listener;

    /* loaded from: classes3.dex */
    public interface OnItemClickListener {
        void onItemClick(int i, View view);
    }

    public UCropGalleryAdapter(List<String> list) {
        this.list = list;
    }

    public void setCurrentSelectPosition(int i) {
        this.currentSelectPosition = i;
    }

    public int getCurrentSelectPosition() {
        return this.currentSelectPosition;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C2880R.layout.ucrop_gallery_adapter_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        ColorFilter createBlendModeColorFilterCompat;
        String str = this.list.get(i);
        if (UCropDevelopConfig.imageEngine != null) {
            UCropDevelopConfig.imageEngine.loadImage(viewHolder.itemView.getContext(), str, viewHolder.mIvPhoto);
        }
        if (this.currentSelectPosition == i) {
            viewHolder.mViewCurrentSelect.setVisibility(0);
            createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(ContextCompat.getColor(viewHolder.itemView.getContext(), C2880R.color.ucrop_color_80), BlendModeCompat.SRC_ATOP);
        } else {
            createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(ContextCompat.getColor(viewHolder.itemView.getContext(), C2880R.color.ucrop_color_20), BlendModeCompat.SRC_ATOP);
            viewHolder.mViewCurrentSelect.setVisibility(8);
        }
        viewHolder.mIvPhoto.setColorFilter(createBlendModeColorFilterCompat);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yalantis.ucrop.UCropGalleryAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UCropGalleryAdapter.this.listener != null) {
                    UCropGalleryAdapter.this.listener.onItemClick(viewHolder.getAbsoluteAdapterPosition(), view);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.list;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* loaded from: classes3.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvPhoto;
        View mViewCurrentSelect;

        public ViewHolder(View view) {
            super(view);
            this.mIvPhoto = (ImageView) view.findViewById(C2880R.id.iv_photo);
            this.mViewCurrentSelect = view.findViewById(C2880R.id.view_current_select);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}
