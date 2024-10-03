package com.google.android.gms.internal.recaptcha;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzsp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzsn zzsnVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzsnVar, sb, 0);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zztm.zza(zzpy.zzp((String) obj)));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzpy) {
            sb.append(": \"");
            sb.append(zztm.zza((zzpy) obj));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzrg) {
            sb.append(" {");
            zzd((zzrg) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
            sb.append(" {");
            Map.Entry entry = (Map.Entry) obj;
            int i4 = i + 2;
            zzb(sb, i4, "key", entry.getKey());
            zzb(sb, i4, "value", entry.getValue());
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        sb.append(": ");
        sb.append(obj.toString());
    }

    private static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    private static void zzd(zzsn zzsnVar, StringBuilder sb, int i) {
        boolean equals;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzsnVar.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String substring = str.startsWith("get") ? str.substring(3) : str;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List")) {
                String valueOf = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(substring.substring(1, substring.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzc(concat), zzrg.zzB(method2, zzsnVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(concat2), zzrg.zzB(method3, zzsnVar, new Object[0]));
                }
            }
            String valueOf5 = String.valueOf(substring);
            if (((Method) hashMap2.get(valueOf5.length() != 0 ? "set".concat(valueOf5) : new String("set"))) != null) {
                if (substring.endsWith("Bytes")) {
                    String valueOf6 = String.valueOf(substring.substring(0, substring.length() - 5));
                    if (!hashMap.containsKey(valueOf6.length() != 0 ? "get".concat(valueOf6) : new String("get"))) {
                    }
                }
                String valueOf7 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(substring.substring(1));
                String concat3 = valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7);
                String valueOf9 = String.valueOf(substring);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? "get".concat(valueOf9) : new String("get"));
                String valueOf10 = String.valueOf(substring);
                Method method5 = (Method) hashMap.get(valueOf10.length() != 0 ? "has".concat(valueOf10) : new String("has"));
                if (method4 != null) {
                    Object zzB = zzrg.zzB(method4, zzsnVar, new Object[0]);
                    if (method5 == null) {
                        if (zzB instanceof Boolean) {
                            if (((Boolean) zzB).booleanValue()) {
                                zzb(sb, i, zzc(concat3), zzB);
                            }
                        } else if (zzB instanceof Integer) {
                            if (((Integer) zzB).intValue() != 0) {
                                zzb(sb, i, zzc(concat3), zzB);
                            }
                        } else if (zzB instanceof Float) {
                            if (((Float) zzB).floatValue() != 0.0f) {
                                zzb(sb, i, zzc(concat3), zzB);
                            }
                        } else if (!(zzB instanceof Double)) {
                            if (zzB instanceof String) {
                                equals = zzB.equals("");
                            } else if (zzB instanceof zzpy) {
                                equals = zzB.equals(zzpy.zzb);
                            } else if (zzB instanceof zzsn) {
                                if (zzB != ((zzsn) zzB).zzO()) {
                                    zzb(sb, i, zzc(concat3), zzB);
                                }
                            } else {
                                if ((zzB instanceof Enum) && ((Enum) zzB).ordinal() == 0) {
                                }
                                zzb(sb, i, zzc(concat3), zzB);
                            }
                            if (!equals) {
                                zzb(sb, i, zzc(concat3), zzB);
                            }
                        } else if (((Double) zzB).doubleValue() != 0.0d) {
                            zzb(sb, i, zzc(concat3), zzB);
                        }
                    } else if (((Boolean) zzrg.zzB(method5, zzsnVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzc(concat3), zzB);
                    }
                }
            }
        }
        if (!(zzsnVar instanceof zzrd)) {
            zzts zztsVar = ((zzrg) zzsnVar).zzc;
            if (zztsVar != null) {
                zztsVar.zzg(sb, i);
                return;
            }
            return;
        }
        zzqw zzqwVar = ((zzrd) zzsnVar).zzb;
        throw null;
    }
}
