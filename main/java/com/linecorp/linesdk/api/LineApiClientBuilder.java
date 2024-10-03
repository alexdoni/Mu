package com.linecorp.linesdk.api;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.linecorp.linesdk.BuildConfig;
import com.linecorp.linesdk.api.internal.AutoRefreshLineApiClientProxy;
import com.linecorp.linesdk.api.internal.LineApiClientImpl;
import com.linecorp.linesdk.internal.AccessTokenCache;
import com.linecorp.linesdk.internal.EncryptorHolder;
import com.linecorp.linesdk.internal.nwclient.LineAuthenticationApiClient;
import com.linecorp.linesdk.internal.nwclient.TalkApiClient;
import com.linecorp.linesdk.utils.ObjectUtils;

/* loaded from: classes2.dex */
public class LineApiClientBuilder {
    private Uri apiBaseUri;
    private final String channelId;
    private final Context context;
    private boolean isEncryptorPreparationDisabled;
    private boolean isTokenAutoRefreshDisabled;
    private Uri openidDiscoveryDocumentUrl;

    public LineApiClientBuilder(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("channel id is empty");
        }
        this.context = context.getApplicationContext();
        this.channelId = str;
        this.openidDiscoveryDocumentUrl = Uri.parse(BuildConfig.OPENID_DISCOVERY_DOCUMENT_URL);
        this.apiBaseUri = Uri.parse(BuildConfig.API_SERVER_BASE_URI);
    }

    public LineApiClientBuilder disableTokenAutoRefresh() {
        this.isTokenAutoRefreshDisabled = true;
        return this;
    }

    public LineApiClientBuilder disableEncryptorPreparation() {
        this.isEncryptorPreparationDisabled = true;
        return this;
    }

    LineApiClientBuilder openidDiscoveryDocumentUrl(Uri uri) {
        this.openidDiscoveryDocumentUrl = (Uri) ObjectUtils.defaultIfNull(uri, Uri.parse(BuildConfig.OPENID_DISCOVERY_DOCUMENT_URL));
        return this;
    }

    LineApiClientBuilder apiBaseUri(Uri uri) {
        this.apiBaseUri = (Uri) ObjectUtils.defaultIfNull(uri, Uri.parse(BuildConfig.API_SERVER_BASE_URI));
        return this;
    }

    public LineApiClient build() {
        if (!this.isEncryptorPreparationDisabled) {
            EncryptorHolder.initializeOnWorkerThread(this.context);
        }
        LineApiClientImpl lineApiClientImpl = new LineApiClientImpl(this.channelId, new LineAuthenticationApiClient(this.context, this.openidDiscoveryDocumentUrl, this.apiBaseUri), new TalkApiClient(this.context, this.apiBaseUri), new AccessTokenCache(this.context, this.channelId));
        return this.isTokenAutoRefreshDisabled ? lineApiClientImpl : AutoRefreshLineApiClientProxy.newProxy(lineApiClientImpl);
    }
}
