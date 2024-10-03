package com.xsdk.ab_firstbase.statisics.util.languagelib;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.Log;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.Locale;

/* loaded from: classes3.dex */
public class MultiLanguageUtil {
    public static final String SAVE_LANGUAGE = "save_language";
    private static final String TAG = "MultiLanguageUtil";
    private static MultiLanguageUtil instance;
    private static Context mContext;
    private String lang = LanguageType.SERVER_EN;

    public static void init(Context context) {
        if (instance == null) {
            synchronized (MultiLanguageUtil.class) {
                if (instance == null) {
                    instance = new MultiLanguageUtil(context);
                }
            }
        }
    }

    public static MultiLanguageUtil getInstance() {
        Context context;
        if (instance == null && (context = mContext) != null) {
            instance = new MultiLanguageUtil(context);
        }
        return instance;
    }

    private MultiLanguageUtil(Context context) {
        mContext = context;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String str) {
        this.lang = str;
        Util.setSystemLang(str);
    }

    public void setConfiguration() {
        Locale languageLocale = getLanguageLocale();
        Configuration configuration = mContext.getResources().getConfiguration();
        configuration.setLocale(languageLocale);
        Resources resources = mContext.getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        Log.e(TAG, "setConfiguration= finish");
    }

    private void localeCheckLange(Locale locale) {
        if (locale != null) {
            String language = locale.getLanguage();
            Log.v(TAG, "localeCheckLange= " + language);
            boolean contains = language.contains("zh");
            int i = 3;
            String str = LanguageType.SERVER_CHINESE_TRADITIONAL;
            if (contains) {
                if (locale.toLanguageTag().contains("zh-Hans-CN") || ("CN".equals(locale.getCountry()) && "中国".equals(locale.getDisplayCountry()))) {
                    str = LanguageType.SERVER_CHINESE_SIMPLIFIED;
                    i = 2;
                } else if (!locale.toLanguageTag().contains("zh-Hant-HK")) {
                    locale.toLanguageTag().contains("zh-Hant-TW");
                }
            } else if (!language.contains("tw")) {
                str = LanguageType.SERVER_EN;
                if (language.contains(LanguageType.SERVER_EN)) {
                    i = 1;
                } else {
                    String str2 = LanguageType.SERVER_TH;
                    if (language.contains(LanguageType.SERVER_TH)) {
                        i = 4;
                    } else {
                        str2 = LanguageType.SERVER_VI;
                        if (language.contains(LanguageType.SERVER_VI)) {
                            i = 5;
                        } else {
                            str2 = LanguageType.SERVER_JA;
                            if (language.contains(LanguageType.SERVER_JA)) {
                                i = 7;
                            } else {
                                str2 = LanguageType.SERVER_KO;
                                if (language.contains(LanguageType.SERVER_KO)) {
                                    i = 6;
                                } else {
                                    str2 = LanguageType.SERVER_DE;
                                    if (language.contains(LanguageType.SERVER_DE)) {
                                        i = 8;
                                    } else {
                                        str2 = LanguageType.SERVER_RU;
                                        if (language.contains(LanguageType.SERVER_RU)) {
                                            i = 9;
                                        } else {
                                            str2 = LanguageType.SERVER_ES;
                                            if (language.contains(LanguageType.SERVER_ES)) {
                                                i = 10;
                                            } else {
                                                str2 = LanguageType.SERVER_FR;
                                                if (language.contains(LanguageType.SERVER_FR)) {
                                                    i = 11;
                                                } else {
                                                    str2 = LanguageType.SERVER_IN;
                                                    if (language.contains(LanguageType.SERVER_IN)) {
                                                        i = 12;
                                                    } else {
                                                        i = 0;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    str = str2;
                }
            }
            setLang(str);
            CommSharedUtil.getInstance(mContext).putInt(SAVE_LANGUAGE, i);
        }
    }

    private Locale getLanguageLocale() {
        int i = CommSharedUtil.getInstance(mContext).getInt(SAVE_LANGUAGE, 0);
        Log.e(TAG, "0getLanguageLocale" + i);
        if (i == 0) {
            Locale sysLocale = getSysLocale();
            localeCheckLange(sysLocale);
            return sysLocale;
        }
        if (i == 1) {
            return Locale.ENGLISH;
        }
        if (i == 2) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        if (i == 3) {
            return Locale.TRADITIONAL_CHINESE;
        }
        if (i == 4) {
            return new Locale(LanguageType.SERVER_TH, "TH", "TH");
        }
        if (i == 6) {
            return Locale.KOREA;
        }
        if (i == 5) {
            return new Locale(LanguageType.SERVER_VI, "VN", "VN");
        }
        if (i == 8) {
            return new Locale(LanguageType.SERVER_DE, "DE", "DE");
        }
        if (i == 9) {
            return new Locale(LanguageType.SERVER_RU, "RU", "RU");
        }
        if (i == 10) {
            return new Locale(LanguageType.SERVER_ES, "ES", "ES");
        }
        if (i == 11) {
            return new Locale(LanguageType.SERVER_FR, "FR", "FR");
        }
        if (i == 12) {
            return new Locale(LanguageType.SERVER_IN, "IN", "IN");
        }
        Log.e(TAG, "1getLanguageLocale" + i);
        return Locale.ENGLISH;
    }

    private String getSystemLanguage(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    public Locale getSysLocale() {
        if (Build.VERSION.SDK_INT >= 24) {
            return LocaleList.getDefault().get(0);
        }
        return Locale.getDefault();
    }

    public void updateLanguage(int i) {
        Log.e(TAG, "updateLanguage=" + i);
        getInstance().getLocalTypeToServer(i);
        CommSharedUtil.getInstance(mContext).putInt(SAVE_LANGUAGE, i);
        getInstance().setConfiguration();
    }

    public int getLanguageType(String str) {
        int i;
        if (TextUtils.isEmpty(str) || str.equals(LanguageType.SERVER_EN)) {
            i = 1;
        } else {
            i = 0;
            if (!str.equals(LanguageType.SERVER_FOLLOW_SYSTEM)) {
                if (str.equals(LanguageType.SERVER_CHINESE_TRADITIONAL)) {
                    i = 3;
                } else if (str.equals(LanguageType.SERVER_CHINESE_SIMPLIFIED)) {
                    i = 2;
                } else if (str.equals(LanguageType.SERVER_TH)) {
                    i = 4;
                } else if (str.equals(LanguageType.SERVER_KO)) {
                    i = 6;
                } else if (str.equals(LanguageType.SERVER_JA)) {
                    i = 7;
                } else if (str.equals(LanguageType.SERVER_VI)) {
                    i = 5;
                } else if (str.equals(LanguageType.SERVER_DE)) {
                    i = 8;
                } else if (str.equals(LanguageType.SERVER_RU)) {
                    i = 9;
                } else if (str.equals(LanguageType.SERVER_ES)) {
                    i = 10;
                } else if (str.equals(LanguageType.SERVER_FR)) {
                    i = 11;
                } else if (str.equals(LanguageType.SERVER_IN)) {
                    i = 12;
                }
            }
        }
        setLang(str);
        Log.e(TAG, "getLanguageType=" + i + " tempLang=" + str);
        return i;
    }

    public String getLocalTypeToServer(int i) {
        String str;
        if (i != 1) {
            if (i == 0) {
                str = LanguageType.SERVER_FOLLOW_SYSTEM;
            } else if (i == 3) {
                str = LanguageType.SERVER_CHINESE_TRADITIONAL;
            } else if (i == 2) {
                str = LanguageType.SERVER_CHINESE_SIMPLIFIED;
            } else if (i == 4) {
                str = LanguageType.SERVER_TH;
            } else if (i == 5) {
                str = LanguageType.SERVER_VI;
            } else if (i == 6) {
                str = LanguageType.SERVER_KO;
            } else if (i == 7) {
                str = LanguageType.SERVER_JA;
            } else if (i == 8) {
                str = LanguageType.SERVER_DE;
            } else if (i == 9) {
                str = LanguageType.SERVER_RU;
            } else if (i == 10) {
                str = LanguageType.SERVER_ES;
            } else if (i == 11) {
                str = LanguageType.SERVER_FR;
            } else if (i == 12) {
                str = LanguageType.SERVER_IN;
            }
            setLang(str);
            Log.e(TAG, "lang=" + str + " localType=" + i);
            return str;
        }
        str = LanguageType.SERVER_EN;
        setLang(str);
        Log.e(TAG, "lang=" + str + " localType=" + i);
        return str;
    }

    public String getCurrentSystemLauguage() {
        String str = "";
        try {
            Locale sysLocale = getSysLocale();
            str = sysLocale.getLanguage();
            Log.v(TAG, "getCurrentSystemLauguage= " + str);
            if (!str.contains("zh")) {
                return str;
            }
            if (sysLocale.toLanguageTag().contains("zh-Hans-CN")) {
                return LanguageType.SERVER_CHINESE_SIMPLIFIED;
            }
            if (!sysLocale.toLanguageTag().contains("zh-Hant-HK")) {
                sysLocale.toLanguageTag().contains("zh-Hant-TW");
            }
            return LanguageType.SERVER_CHINESE_TRADITIONAL;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static Context attachBaseContext(Context context, int i) {
        mContext = context;
        int i2 = CommSharedUtil.getInstance(context).getInt(SAVE_LANGUAGE, -1);
        Log.v(TAG, "attachBaseContext languageType =" + i2 + " lang=" + i);
        if (i2 == -1) {
            CommSharedUtil.getInstance(context).putInt(SAVE_LANGUAGE, i);
        } else {
            i = i2;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            getInstance().getLocalTypeToServer(i);
            return createConfigurationResources(context);
        }
        getInstance().setConfiguration();
        return context;
    }

    private static Context createConfigurationResources(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(getInstance().getLanguageLocale());
        return context.createConfigurationContext(configuration);
    }
}
