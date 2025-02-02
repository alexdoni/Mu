package com.facebook;

import android.content.SharedPreferences;
import com.google.android.gms.common.Scopes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProfileCache.kt */
@Metadata(m1394d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m1395d2 = {"Lcom/facebook/ProfileCache;", "", "()V", "sharedPreferences", "Landroid/content/SharedPreferences;", "clear", "", "load", "Lcom/facebook/Profile;", "save", Scopes.PROFILE, "Companion", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class ProfileCache {
    public static final String CACHED_PROFILE_KEY = "com.facebook.ProfileManager.CachedProfile";
    public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    private final SharedPreferences sharedPreferences;

    public ProfileCache() {
        FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
        SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "FacebookSdk.getApplicationContext()\n            .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)");
        this.sharedPreferences = sharedPreferences;
    }

    public final Profile load() {
        String string = this.sharedPreferences.getString(CACHED_PROFILE_KEY, null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public final void save(Profile profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        JSONObject jSONObject = profile.toJSONObject();
        if (jSONObject != null) {
            this.sharedPreferences.edit().putString(CACHED_PROFILE_KEY, jSONObject.toString()).apply();
        }
    }

    public final void clear() {
        this.sharedPreferences.edit().remove(CACHED_PROFILE_KEY).apply();
    }
}
