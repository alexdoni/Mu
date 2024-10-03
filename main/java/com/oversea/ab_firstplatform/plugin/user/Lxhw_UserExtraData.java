package com.oversea.ab_firstplatform.plugin.user;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class Lxhw_UserExtraData {
    public static final int TYPE_CHOOSE_SERVER = 1;
    public static final int TYPE_CREATE_ROLE = 2;
    public static final int TYPE_ENTER_GAME = 3;
    public static final int TYPE_EXIT_GAME = 5;
    public static final int TYPE_JOIN_UNION = 7;
    public static final int TYPE_LEVEL_UP = 4;
    public static final int TYPE_VIP_UP = 6;
    private int dataType;
    private int vipLevel;
    private String roleID = "";
    private String roleName = "";
    private String roleLevel = "";
    private String serverId = "";
    private String serverName = "";
    private String remain_coin = "";

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public String getRoleID() {
        if (TextUtils.isEmpty(this.roleID)) {
            this.roleID = "";
        }
        return this.roleID;
    }

    public void setRoleID(String str) {
        this.roleID = str;
    }

    public String getRoleName() {
        if (TextUtils.isEmpty(this.roleName)) {
            this.roleName = "";
        }
        return this.roleName;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }

    public String getRoleLevel() {
        if (TextUtils.isEmpty(this.roleLevel)) {
            this.roleLevel = "";
        }
        return this.roleLevel;
    }

    public void setRoleLevel(String str) {
        this.roleLevel = str;
    }

    public String getServerId() {
        if (TextUtils.isEmpty(this.serverId)) {
            this.serverId = "";
        }
        return this.serverId;
    }

    public void setServerId(String str) {
        this.serverId = str;
    }

    public int getVipLevel() {
        return this.vipLevel;
    }

    public void setVipLevel(int i) {
        this.vipLevel = i;
    }

    public String getServerName() {
        if (TextUtils.isEmpty(this.serverName)) {
            this.serverName = "";
        }
        return this.serverName;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public String getRemain_coin() {
        if (TextUtils.isEmpty(this.remain_coin)) {
            this.remain_coin = "";
        }
        return this.remain_coin;
    }

    public void setRemain_coin(String str) {
        this.remain_coin = str;
    }
}
