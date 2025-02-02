package com.luck.picture.lib.language;

import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.util.Locale;

/* loaded from: classes2.dex */
public class LocaleTransform {
    public static Locale getLanguage(int i) {
        switch (i) {
            case 1:
                return Locale.TRADITIONAL_CHINESE;
            case 2:
                return Locale.ENGLISH;
            case 3:
                return Locale.KOREA;
            case 4:
                return Locale.GERMANY;
            case 5:
                return Locale.FRANCE;
            case 6:
                return Locale.JAPAN;
            case 7:
                return new Locale(LanguageType.SERVER_VI);
            case 8:
                return new Locale(LanguageType.SERVER_ES, "ES");
            case 9:
                return new Locale("pt", "PT");
            case 10:
                return new Locale("ar", "AE");
            case 11:
                return new Locale(LanguageType.SERVER_RU, "rRU");
            case 12:
                return new Locale("cs", "rCZ");
            case 13:
                return new Locale("kk", "rKZ");
            default:
                return Locale.CHINESE;
        }
    }
}
