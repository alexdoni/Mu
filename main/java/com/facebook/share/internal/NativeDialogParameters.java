package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.Utility;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideoContent;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeDialogParameters.kt */
@Metadata(m1394d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00132\u0006\u0010\b\u001a\u00020\tH\u0002J*\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001e2\u0006\u0010\u001f\u001a\u00020\tH\u0007J \u0010 \u001a\u00020\u00042\u000e\u0010!\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001e2\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\""}, m1395d2 = {"Lcom/facebook/share/internal/NativeDialogParameters;", "", "()V", "create", "Landroid/os/Bundle;", "cameraEffectContent", "Lcom/facebook/share/model/ShareCameraEffectContent;", "attachmentUrlsBundle", "dataErrorsFatal", "", "linkContent", "Lcom/facebook/share/model/ShareLinkContent;", "mediaContent", "Lcom/facebook/share/model/ShareMediaContent;", "mediaInfos", "", "photoContent", "Lcom/facebook/share/model/SharePhotoContent;", "imageUrls", "", "storyContent", "Lcom/facebook/share/model/ShareStoryContent;", "mediaInfo", "stickerInfo", "videoContent", "Lcom/facebook/share/model/ShareVideoContent;", "videoUrl", "callId", "Ljava/util/UUID;", "shareContent", "Lcom/facebook/share/model/ShareContent;", "shouldFailOnDataError", "createBaseParameters", FirebaseAnalytics.Param.CONTENT, "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class NativeDialogParameters {
    public static final NativeDialogParameters INSTANCE = new NativeDialogParameters();

    private NativeDialogParameters() {
    }

    @JvmStatic
    public static final Bundle create(UUID callId, ShareContent<?, ?> shareContent, boolean shouldFailOnDataError) {
        Intrinsics.checkNotNullParameter(callId, "callId");
        Intrinsics.checkNotNullParameter(shareContent, "shareContent");
        if (shareContent instanceof ShareLinkContent) {
            return INSTANCE.create((ShareLinkContent) shareContent, shouldFailOnDataError);
        }
        if (shareContent instanceof SharePhotoContent) {
            ShareInternalUtility shareInternalUtility = ShareInternalUtility.INSTANCE;
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            List<String> photoUrls = ShareInternalUtility.getPhotoUrls(sharePhotoContent, callId);
            if (photoUrls == null) {
                photoUrls = CollectionsKt.emptyList();
            }
            return INSTANCE.create(sharePhotoContent, photoUrls, shouldFailOnDataError);
        }
        if (shareContent instanceof ShareVideoContent) {
            ShareInternalUtility shareInternalUtility2 = ShareInternalUtility.INSTANCE;
            ShareVideoContent shareVideoContent = (ShareVideoContent) shareContent;
            return INSTANCE.create(shareVideoContent, ShareInternalUtility.getVideoUrl(shareVideoContent, callId), shouldFailOnDataError);
        }
        if (shareContent instanceof ShareMediaContent) {
            ShareInternalUtility shareInternalUtility3 = ShareInternalUtility.INSTANCE;
            ShareMediaContent shareMediaContent = (ShareMediaContent) shareContent;
            List<Bundle> mediaInfos = ShareInternalUtility.getMediaInfos(shareMediaContent, callId);
            if (mediaInfos == null) {
                mediaInfos = CollectionsKt.emptyList();
            }
            return INSTANCE.create(shareMediaContent, mediaInfos, shouldFailOnDataError);
        }
        if (shareContent instanceof ShareCameraEffectContent) {
            ShareInternalUtility shareInternalUtility4 = ShareInternalUtility.INSTANCE;
            ShareCameraEffectContent shareCameraEffectContent = (ShareCameraEffectContent) shareContent;
            return INSTANCE.create(shareCameraEffectContent, ShareInternalUtility.getTextureUrlBundle(shareCameraEffectContent, callId), shouldFailOnDataError);
        }
        if (!(shareContent instanceof ShareStoryContent)) {
            return null;
        }
        ShareInternalUtility shareInternalUtility5 = ShareInternalUtility.INSTANCE;
        ShareStoryContent shareStoryContent = (ShareStoryContent) shareContent;
        Bundle backgroundAssetMediaInfo = ShareInternalUtility.getBackgroundAssetMediaInfo(shareStoryContent, callId);
        ShareInternalUtility shareInternalUtility6 = ShareInternalUtility.INSTANCE;
        return INSTANCE.create(shareStoryContent, backgroundAssetMediaInfo, ShareInternalUtility.getStickerUrl(shareStoryContent, callId), shouldFailOnDataError);
    }

    private final Bundle create(ShareCameraEffectContent cameraEffectContent, Bundle attachmentUrlsBundle, boolean dataErrorsFatal) {
        Bundle createBaseParameters = createBaseParameters(cameraEffectContent, dataErrorsFatal);
        Utility utility = Utility.INSTANCE;
        Utility.putNonEmptyString(createBaseParameters, ShareConstants.EFFECT_ID, cameraEffectContent.getEffectId());
        if (attachmentUrlsBundle != null) {
            createBaseParameters.putBundle(ShareConstants.EFFECT_TEXTURES, attachmentUrlsBundle);
        }
        try {
            CameraEffectJSONUtility cameraEffectJSONUtility = CameraEffectJSONUtility.INSTANCE;
            JSONObject convertToJSON = CameraEffectJSONUtility.convertToJSON(cameraEffectContent.getArguments());
            if (convertToJSON != null) {
                Utility utility2 = Utility.INSTANCE;
                Utility.putNonEmptyString(createBaseParameters, ShareConstants.EFFECT_ARGS, convertToJSON.toString());
            }
            return createBaseParameters;
        } catch (JSONException e) {
            throw new FacebookException(Intrinsics.stringPlus("Unable to create a JSON Object from the provided CameraEffectArguments: ", e.getMessage()));
        }
    }

    private final Bundle create(ShareLinkContent linkContent, boolean dataErrorsFatal) {
        Bundle createBaseParameters = createBaseParameters(linkContent, dataErrorsFatal);
        Utility utility = Utility.INSTANCE;
        Utility.putNonEmptyString(createBaseParameters, ShareConstants.QUOTE, linkContent.getQuote());
        Utility utility2 = Utility.INSTANCE;
        Utility.putUri(createBaseParameters, ShareConstants.MESSENGER_URL, linkContent.getContentUrl());
        Utility utility3 = Utility.INSTANCE;
        Utility.putUri(createBaseParameters, ShareConstants.TARGET_DISPLAY, linkContent.getContentUrl());
        return createBaseParameters;
    }

    private final Bundle create(SharePhotoContent photoContent, List<String> imageUrls, boolean dataErrorsFatal) {
        Bundle createBaseParameters = createBaseParameters(photoContent, dataErrorsFatal);
        createBaseParameters.putStringArrayList(ShareConstants.PHOTOS, new ArrayList<>(imageUrls));
        return createBaseParameters;
    }

    private final Bundle create(ShareVideoContent videoContent, String videoUrl, boolean dataErrorsFatal) {
        Bundle createBaseParameters = createBaseParameters(videoContent, dataErrorsFatal);
        Utility utility = Utility.INSTANCE;
        Utility.putNonEmptyString(createBaseParameters, ShareConstants.TITLE, videoContent.getContentTitle());
        Utility utility2 = Utility.INSTANCE;
        Utility.putNonEmptyString(createBaseParameters, ShareConstants.DESCRIPTION, videoContent.getContentDescription());
        Utility utility3 = Utility.INSTANCE;
        Utility.putNonEmptyString(createBaseParameters, ShareConstants.VIDEO_URL, videoUrl);
        return createBaseParameters;
    }

    private final Bundle create(ShareMediaContent mediaContent, List<Bundle> mediaInfos, boolean dataErrorsFatal) {
        Bundle createBaseParameters = createBaseParameters(mediaContent, dataErrorsFatal);
        createBaseParameters.putParcelableArrayList(ShareConstants.MEDIA, new ArrayList<>(mediaInfos));
        return createBaseParameters;
    }

    private final Bundle create(ShareStoryContent storyContent, Bundle mediaInfo, Bundle stickerInfo, boolean dataErrorsFatal) {
        Bundle createBaseParameters = createBaseParameters(storyContent, dataErrorsFatal);
        if (mediaInfo != null) {
            createBaseParameters.putParcelable(ShareConstants.STORY_BG_ASSET, mediaInfo);
        }
        if (stickerInfo != null) {
            createBaseParameters.putParcelable(ShareConstants.STORY_INTERACTIVE_ASSET_URI, stickerInfo);
        }
        List<String> backgroundColorList = storyContent.getBackgroundColorList();
        if (!(backgroundColorList == null || backgroundColorList.isEmpty())) {
            createBaseParameters.putStringArrayList(ShareConstants.STORY_INTERACTIVE_COLOR_LIST, new ArrayList<>(backgroundColorList));
        }
        Utility utility = Utility.INSTANCE;
        Utility.putNonEmptyString(createBaseParameters, ShareConstants.STORY_DEEP_LINK_URL, storyContent.getAttributionLink());
        return createBaseParameters;
    }

    private final Bundle createBaseParameters(ShareContent<?, ?> content, boolean dataErrorsFatal) {
        Bundle bundle = new Bundle();
        Utility utility = Utility.INSTANCE;
        Utility.putUri(bundle, ShareConstants.CONTENT_URL, content.getContentUrl());
        Utility utility2 = Utility.INSTANCE;
        Utility.putNonEmptyString(bundle, ShareConstants.PLACE_ID, content.getPlaceId());
        Utility utility3 = Utility.INSTANCE;
        Utility.putNonEmptyString(bundle, ShareConstants.PAGE_ID, content.getPageId());
        Utility utility4 = Utility.INSTANCE;
        Utility.putNonEmptyString(bundle, ShareConstants.REF, content.getRef());
        Utility utility5 = Utility.INSTANCE;
        Utility.putNonEmptyString(bundle, ShareConstants.REF, content.getRef());
        bundle.putBoolean(ShareConstants.DATA_FAILURES_FATAL, dataErrorsFatal);
        List<String> peopleIds = content.getPeopleIds();
        if (!(peopleIds == null || peopleIds.isEmpty())) {
            bundle.putStringArrayList(ShareConstants.PEOPLE_IDS, new ArrayList<>(peopleIds));
        }
        Utility utility6 = Utility.INSTANCE;
        ShareHashtag shareHashtag = content.getShareHashtag();
        Utility.putNonEmptyString(bundle, ShareConstants.HASHTAG, shareHashtag == null ? null : shareHashtag.getHashtag());
        return bundle;
    }
}
