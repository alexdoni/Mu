package com.linecorp.linesdk.dialog.internal;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.linecorp.linesdk.C2028R;
import com.linecorp.linesdk.dialog.internal.TargetUser;
import com.squareup.picasso.Picasso;

/* loaded from: classes2.dex */
public class UserThumbnailView extends ConstraintLayout {
    private ImageView imageView;
    private TextView targetUserName;

    public UserThumbnailView(Context context) {
        super(context);
        init();
    }

    public void setTargetUser(TargetUser targetUser) {
        this.targetUserName.setText(targetUser.getDisplayName());
        Picasso.get().load(targetUser.getPictureUri()).placeholder(targetUser.getType() == TargetUser.Type.FRIEND ? C2028R.drawable.friend_thumbnail : C2028R.drawable.group_thumbnail).into(this.imageView);
    }

    private void init() {
        inflate(getContext(), C2028R.layout.target_user_thumbnail, this);
        this.targetUserName = (TextView) findViewById(C2028R.id.textViewDisplayName);
        this.imageView = (ImageView) findViewById(C2028R.id.imageViewTargetUser);
    }
}
