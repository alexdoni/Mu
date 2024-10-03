package com.linecorp.linesdk;

import android.text.TextUtils;
import com.facebook.appevents.integrity.IntegrityManager;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class Scope {
    private static final String SCOPE_DELIMITER = " ";
    private final String code;
    private static final Map<String, Scope> scopeInstanceMap = new HashMap();
    public static final Scope PROFILE = new Scope(Scopes.PROFILE);
    public static final Scope FRIEND = new Scope(NativeProtocol.AUDIENCE_FRIENDS);
    public static final Scope GROUP = new Scope("groups");
    public static final Scope MESSAGE = new Scope("message.write");
    public static final Scope OPENID_CONNECT = new Scope("openid");
    public static final Scope OC_EMAIL = new Scope("email");
    public static final Scope OC_PHONE_NUMBER = new Scope("phone");
    public static final Scope OC_GENDER = new Scope("gender");
    public static final Scope OC_BIRTHDATE = new Scope("birthdate");
    public static final Scope OC_ADDRESS = new Scope(IntegrityManager.INTEGRITY_TYPE_ADDRESS);
    public static final Scope OC_REAL_NAME = new Scope("real_name");
    public static final Scope ONE_TIME_SHARE = new Scope("onetime.share");

    protected Scope(String str) {
        Map<String, Scope> map = scopeInstanceMap;
        if (map.containsKey(str)) {
            throw new IllegalArgumentException("Scope code already exists: " + str);
        }
        this.code = str;
        map.put(str, this);
    }

    public static Scope findScope(String str) {
        return scopeInstanceMap.get(str);
    }

    public static String join(List<Scope> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return TextUtils.join(SCOPE_DELIMITER, convertToCodeList(list));
    }

    public static List<Scope> parseToList(String str) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        return convertToScopeList(Arrays.asList(str.split(SCOPE_DELIMITER)));
    }

    public static List<Scope> convertToScopeList(List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Scope findScope = findScope(it.next());
            if (findScope != null) {
                arrayList.add(findScope);
            }
        }
        return arrayList;
    }

    public static List<String> convertToCodeList(List<Scope> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Scope> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().code);
        }
        return arrayList;
    }

    public String getCode() {
        return this.code;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.code.equals(((Scope) obj).code);
    }

    public int hashCode() {
        return this.code.hashCode();
    }

    public String toString() {
        return "Scope{code='" + this.code + "'}";
    }
}
