package com.oversea.ab_firstarea.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.facebook.share.internal.ShareConstants;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_WebFullScreenDialog extends Lxhw_BaseFullScreenDialog {
    private static final String TAG = "WebFullScreenDialog";
    private ImageView mCloseBtn;
    private WebView mWebView;
    private String mUrl = "";
    private String type = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseFullScreenDialog
    public String getLayoutId() {
        return "drhw_xweb_common";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseFullScreenDialog
    public void initView(View view) {
        Bundle arguments = getArguments();
        this.mUrl = arguments.getString("url");
        this.type = arguments.getString(ShareConstants.MEDIA_TYPE);
        Log.i(TAG, "web type: " + this.type);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_iv_close_pay_way"));
        this.mCloseBtn = imageView;
        DealTouchDelegate.expandViewTouchDelegateCom(imageView, 15);
        this.mCloseBtn.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_WebFullScreenDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Log.i(Lxhw_WebFullScreenDialog.TAG, "******onClick");
                Lxhw_WebFullScreenDialog.this.dismiss();
            }
        });
        this.mCloseBtn.setVisibility(0);
        WebView webView = (WebView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_web_view_id"));
        this.mWebView = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.requestFocus();
        this.mWebView.setVerticalScrollbarOverlay(true);
        this.mWebView.setScrollBarStyle(0);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setSavePassword(false);
        this.mWebView.getSettings().setSaveFormData(false);
        this.mWebView.getSettings().setCacheMode(2);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_WebFullScreenDialog.2
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView2, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView2, webResourceRequest, webResourceError);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView2, final SslErrorHandler sslErrorHandler, SslError sslError) {
                super.onReceivedSslError(webView2, sslErrorHandler, sslError);
                AlertDialog.Builder builder = new AlertDialog.Builder(Lxhw_WebFullScreenDialog.this.mContext);
                builder.setMessage("ssl Validation failed");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_WebFullScreenDialog.2.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sslErrorHandler.proceed();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_WebFullScreenDialog.2.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sslErrorHandler.cancel();
                    }
                });
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_WebFullScreenDialog.2.3
                    @Override // android.content.DialogInterface.OnKeyListener
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (keyEvent.getAction() != 1 || i != 4) {
                            return false;
                        }
                        sslErrorHandler.cancel();
                        dialogInterface.dismiss();
                        return true;
                    }
                });
                builder.create().show();
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                return super.shouldOverrideUrlLoading(webView2, str);
            }
        });
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_WebFullScreenDialog.3
            @Override // android.webkit.WebChromeClient
            public void onCloseWindow(WebView webView2) {
                super.onCloseWindow(webView2);
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                super.onProgressChanged(webView2, i);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView2, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView2, str, str2, jsResult);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsConfirm(WebView webView2, String str, String str2, JsResult jsResult) {
                return super.onJsConfirm(webView2, str, str2, jsResult);
            }
        });
        this.mWebView.loadUrl(this.mUrl);
    }
}
