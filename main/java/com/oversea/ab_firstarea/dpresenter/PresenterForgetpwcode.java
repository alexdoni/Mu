package com.oversea.ab_firstarea.dpresenter;

/* loaded from: classes.dex */
public interface PresenterForgetpwcode extends PresenterBasse {
    void changePasswordByEmailSendCode(int i, String str, String str2);

    void changePasswordByPhoneSendCode(int i, String str, String str2, String str3);

    void doChangePasswordByEmailCheckCode(int i, String str, String str2, String str3);

    void doChangePasswordByPhoneCheckCode(int i, String str, String str2, String str3, String str4);

    void doUnbindEmail(String str, String str2);

    void doUnbindEmailSendCode(String str);

    void doUnbindPhone(String str, String str2, String str3);

    void doUnbindPhoneSendCode(String str, String str2);
}
