package com.mu.data;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MuGameData {
    public long createRoleTime;
    public int dataType;
    public String extToken;
    public String friendList;
    public String gangID;
    public String gangLeaderName;
    public String gangLevel;
    public String gangName;
    public String gender;
    public String loginExt;
    public String loginTime;
    public int moneyNum;
    public String profession;
    public int professionID;
    public String professionRoleID;
    public String professionRoleName;
    public String roleID;
    public String roleLevel;
    public String roleName;
    public int rolePower;
    public int serverID;
    public String serverName;
    public String token;
    public long updateRoleTime;
    public String userID;
    public int vipExp;
    public int vipLevel;

    public static MuGameData ParseGameData(String str) {
        MuGameData muGameData = new MuGameData();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("dataType")) {
                muGameData.dataType = jSONObject.getInt("dataType");
            }
            if (jSONObject.has("userID")) {
                muGameData.userID = jSONObject.getString("userID");
            }
            if (jSONObject.has("roleID")) {
                muGameData.roleID = jSONObject.getString("roleID");
            }
            if (jSONObject.has("roleName")) {
                muGameData.roleName = jSONObject.getString("roleName");
            }
            if (jSONObject.has("roleLevel")) {
                muGameData.roleLevel = jSONObject.getString("roleLevel");
            }
            if (jSONObject.has("serverID")) {
                muGameData.serverID = jSONObject.getInt("serverID");
            }
            if (jSONObject.has("serverName")) {
                muGameData.serverName = jSONObject.getString("serverName");
            }
            if (jSONObject.has("moneyNum")) {
                muGameData.moneyNum = jSONObject.getInt("moneyNum");
            }
            if (jSONObject.has("vipLevel")) {
                muGameData.vipLevel = jSONObject.getInt("vipLevel");
            }
            if (jSONObject.has("vipExp")) {
                muGameData.vipExp = jSONObject.getInt("vipExp");
            }
            if (jSONObject.has("token")) {
                muGameData.token = jSONObject.getString("token");
            }
            if (jSONObject.has("createRoleTime")) {
                muGameData.createRoleTime = jSONObject.getLong("createRoleTime");
            }
            if (jSONObject.has("updateRoleTime")) {
                muGameData.updateRoleTime = jSONObject.getLong("updateRoleTime");
            }
            if (jSONObject.has("gangLevel")) {
                muGameData.gangLevel = jSONObject.getString("gangLevel");
            }
            if (jSONObject.has("gangID")) {
                muGameData.gangID = jSONObject.getString("gangID");
            }
            if (jSONObject.has("gangName")) {
                muGameData.gangName = jSONObject.getString("gangName");
            }
            if (jSONObject.has("gangLeaderName")) {
                muGameData.gangLeaderName = jSONObject.getString("gangLeaderName");
            }
            if (jSONObject.has("rolePower")) {
                muGameData.rolePower = jSONObject.getInt("rolePower");
            }
            if (jSONObject.has("professionID")) {
                muGameData.professionID = jSONObject.getInt("professionID");
            }
            if (jSONObject.has("profession")) {
                muGameData.profession = jSONObject.getString("profession");
            }
            if (jSONObject.has("professionRoleID")) {
                muGameData.professionRoleID = jSONObject.getString("professionRoleID");
            }
            if (jSONObject.has("professionRoleName")) {
                muGameData.professionRoleName = jSONObject.getString("professionRoleName");
            }
            if (jSONObject.has("gender")) {
                muGameData.gender = jSONObject.getString("gender");
            }
            if (jSONObject.has("friendList")) {
                muGameData.friendList = jSONObject.getString("friendList");
            }
            if (jSONObject.has("extToken")) {
                muGameData.extToken = jSONObject.getString("extToken");
            }
            if (jSONObject.has("loginExt")) {
                muGameData.loginExt = jSONObject.getString("loginExt");
            }
            if (jSONObject.has("loginTime")) {
                muGameData.loginTime = jSONObject.getString("loginTime");
            }
        } catch (JSONException unused) {
        }
        return muGameData;
    }
}
