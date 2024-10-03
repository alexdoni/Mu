package com.xsdk.ab_firstbase.net.http;

import com.xsdk.ab_firstbase.net.http.PostFormBuilder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PostFormRequest extends HttpRequest {
    private List<PostFormBuilder.FileInput> files;

    public PostFormRequest(ParamsBean paramsBean, List<PostFormBuilder.FileInput> list) {
        super(paramsBean);
        new ArrayList();
        this.files = list;
    }
}
