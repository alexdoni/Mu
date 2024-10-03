package com.linecorp.linesdk.dialog.internal;

import android.net.Uri;
import com.linecorp.linesdk.LineGroup;
import com.linecorp.linesdk.LineProfile;

/* loaded from: classes2.dex */
public class TargetUser {
    private String displayName;

    /* renamed from: id */
    private String f692id;
    private Boolean isSelected = false;
    private Uri pictureUri;
    private Type type;

    /* loaded from: classes2.dex */
    public enum Type {
        FRIEND,
        GROUP
    }

    public TargetUser(Type type, String str, String str2, Uri uri) {
        this.type = type;
        this.f692id = str;
        this.displayName = str2;
        this.pictureUri = uri;
    }

    public Type getType() {
        return this.type;
    }

    public String getId() {
        return this.f692id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Uri getPictureUri() {
        return this.pictureUri;
    }

    public Boolean getSelected() {
        return this.isSelected;
    }

    public void setSelected(Boolean bool) {
        this.isSelected = bool;
    }

    public static TargetUser createInstance(LineProfile lineProfile) {
        return new TargetUser(Type.FRIEND, lineProfile.getUserId(), lineProfile.getDisplayName(), lineProfile.getPictureUrl());
    }

    public static TargetUser createInstance(LineGroup lineGroup) {
        return new TargetUser(Type.GROUP, lineGroup.getGroupId(), lineGroup.getGroupName(), lineGroup.getPictureUrl());
    }

    public static int getTargetTypeCount() {
        return Type.values().length;
    }
}
