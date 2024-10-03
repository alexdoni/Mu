package com.facebook.login;

import android.os.Bundle;
import android.util.Base64;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.internal.ServerProtocol;
import io.jsonwebtoken.JwtParser;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: PKCEUtil.kt */
@Metadata(m1394d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0007J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\b\u0010\f\u001a\u00020\u0006H\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u000f"}, m1395d2 = {"Lcom/facebook/login/PKCEUtil;", "", "()V", "createCodeExchangeRequest", "Lcom/facebook/GraphRequest;", "authorizationCode", "", "redirectUri", "codeVerifier", "generateCodeChallenge", "codeChallengeMethod", "Lcom/facebook/login/CodeChallengeMethod;", "generateCodeVerifier", "isValidCodeVerifier", "", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class PKCEUtil {
    public static final PKCEUtil INSTANCE = new PKCEUtil();

    private PKCEUtil() {
    }

    @JvmStatic
    public static final boolean isValidCodeVerifier(String codeVerifier) {
        String str = codeVerifier;
        if ((str == null || str.length() == 0) || codeVerifier.length() < 43 || codeVerifier.length() > 128) {
            return false;
        }
        return new Regex("^[-._~A-Za-z0-9]+$").matches(str);
    }

    @JvmStatic
    public static final String generateCodeVerifier() {
        int random = RangesKt.random(new IntRange(43, 128), Random.INSTANCE);
        List plus = CollectionsKt.plus((Collection<? extends char>) CollectionsKt.plus((Collection<? extends char>) CollectionsKt.plus((Collection<? extends Character>) CollectionsKt.plus((Collection<? extends char>) CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', 'z'), (Iterable) new CharRange('A', Matrix.MATRIX_TYPE_ZERO)), (Iterable) new CharRange('0', '9')), '-'), Character.valueOf(JwtParser.SEPARATOR_CHAR)), '_'), '~');
        ArrayList arrayList = new ArrayList(random);
        for (int i = 0; i < random; i++) {
            arrayList.add(Character.valueOf(((Character) CollectionsKt.random(plus, Random.INSTANCE)).charValue()));
        }
        return CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
    }

    @JvmStatic
    public static final String generateCodeChallenge(String codeVerifier, CodeChallengeMethod codeChallengeMethod) throws FacebookException {
        Intrinsics.checkNotNullParameter(codeVerifier, "codeVerifier");
        Intrinsics.checkNotNullParameter(codeChallengeMethod, "codeChallengeMethod");
        if (!isValidCodeVerifier(codeVerifier)) {
            throw new FacebookException("Invalid Code Verifier.");
        }
        if (codeChallengeMethod == CodeChallengeMethod.PLAIN) {
            return codeVerifier;
        }
        try {
            byte[] bytes = codeVerifier.getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256);
            messageDigest.update(bytes, 0, bytes.length);
            String encodeToString = Base64.encodeToString(messageDigest.digest(), 11);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "{\n      // try to generate challenge with S256\n      val bytes: ByteArray = codeVerifier.toByteArray(Charsets.US_ASCII)\n      val messageDigest = MessageDigest.getInstance(\"SHA-256\")\n      messageDigest.update(bytes, 0, bytes.size)\n      val digest = messageDigest.digest()\n\n      Base64.encodeToString(digest, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)\n    }");
            return encodeToString;
        } catch (Exception e) {
            throw new FacebookException(e);
        }
    }

    @JvmStatic
    public static final GraphRequest createCodeExchangeRequest(String authorizationCode, String redirectUri, String codeVerifier) {
        Intrinsics.checkNotNullParameter(authorizationCode, "authorizationCode");
        Intrinsics.checkNotNullParameter(redirectUri, "redirectUri");
        Intrinsics.checkNotNullParameter(codeVerifier, "codeVerifier");
        Bundle bundle = new Bundle();
        bundle.putString("code", authorizationCode);
        FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
        bundle.putString("client_id", FacebookSdk.getApplicationId());
        bundle.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, redirectUri);
        bundle.putString("code_verifier", codeVerifier);
        GraphRequest newGraphPathRequest = GraphRequest.INSTANCE.newGraphPathRequest(null, "oauth/access_token", null);
        newGraphPathRequest.setHttpMethod(HttpMethod.GET);
        newGraphPathRequest.setParameters(bundle);
        return newGraphPathRequest;
    }
}
