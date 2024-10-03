package com.appsflyer.internal;

import java.util.Map;
import kotlin.text.Typography;
import org.spongycastle.i18n.LocalizedMessage;

/* loaded from: classes.dex */
public class AFa1ySDK {
    public static final byte[] $$a = null;
    public static final int $$b = 0;
    private static int $10 = 0;
    private static int $11 = 1;
    private static byte[] AFLogger$LogLevel;
    private static Object AFVersionDeclaration;
    private static int AppsFlyer2dXConversionCallback;
    public static final Map<Integer, Object> afErrorLog;
    private static byte[] afErrorLogForExcManagerOnly;
    private static Object afLogForce;
    private static final Map<String, Object> getLevel;
    private static long init;
    private static int onAppOpenAttributionNative;
    private static byte[] onInstallConversionFailureNative;

    /* JADX WARN: Removed duplicated region for block: B:14:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String $$c(int r9, short r10, short r11) {
        /*
            Method dump skipped, instructions count: 188
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1ySDK.$$c(int, short, short):java.lang.String");
    }

    public static int AFInAppEventType(int i) {
        int i2 = $11;
        int i3 = i2 + 119;
        $10 = i3 % 128;
        int i4 = i3 % 2;
        Object obj = afLogForce;
        int i5 = ((i2 | 73) << 1) - (i2 ^ 73);
        $10 = i5 % 128;
        int i6 = i5 % 2;
        try {
            int intValue = ((Integer) Class.forName($$c((byte) (-$$a[350]), (short) 515, r8[11]), true, (ClassLoader) AFVersionDeclaration).getMethod($$c(r8[164], (short) 387, r8[91]), Integer.TYPE).invoke(obj, Integer.valueOf(i))).intValue();
            int i7 = $11 + 31;
            $10 = i7 % 128;
            if (i7 % 2 != 0) {
                throw null;
            }
            return intValue;
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static int AFInAppEventType(Object obj) {
        int i = $11;
        int i2 = (i + 90) - 1;
        $10 = i2 % 128;
        if ((i2 % 2 != 0 ? '\r' : Typography.quote) == '\r') {
            throw null;
        }
        Object obj2 = afLogForce;
        int i3 = i + 21;
        $10 = i3 % 128;
        int i4 = i3 % 2;
        try {
            Object[] objArr = {obj};
            byte[] bArr = $$a;
            Class<?> cls = Class.forName($$c((byte) (-bArr[350]), (short) 515, bArr[11]), true, (ClassLoader) AFVersionDeclaration);
            byte b = bArr[22];
            return ((Integer) cls.getMethod($$c(b, (short) ((b ^ 570) | (b & 570)), bArr[55]), Object.class).invoke(obj2, objArr)).intValue();
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static Object AFKeystoreWrapper(int i, char c, int i2) {
        int i3 = $10;
        int i4 = (i3 & 105) + (i3 | 105);
        $11 = i4 % 128;
        Object obj = null;
        if (i4 % 2 == 0) {
            throw null;
        }
        Object obj2 = afLogForce;
        int i5 = i3 + 81;
        $11 = i5 % 128;
        int i6 = i5 % 2;
        try {
            Object invoke = Class.forName($$c((byte) (-$$a[350]), (short) 515, r9[11]), true, (ClassLoader) AFVersionDeclaration).getMethod($$c(r9[89], (short) 647, r9[91]), Integer.TYPE, Character.TYPE, Integer.TYPE).invoke(obj2, Integer.valueOf(i), Character.valueOf(c), Integer.valueOf(i2));
            int i7 = $11;
            int i8 = ((i7 | 99) << 1) - (i7 ^ 99);
            $10 = i8 % 128;
            if (i8 % 2 == 0) {
                return invoke;
            }
            obj.hashCode();
            throw null;
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void init$0() {
        int i = $11 + 125;
        $10 = i % 128;
        int i2 = i % 2;
        byte[] bArr = new byte[1075];
        System.arraycopy("rÎ}é÷\u0015ëÍ;\u0006¿\u00143ñ\u0000ÿ\róÿå%\u0002\u0005ÿß!þóü\fó\u000eüý\nïê!ñ\u0002\u0006\u000b\u0005÷\u0015ëÍ>õ\rùÇ%&ú\u0001ñ\b\u0012ý\u0000ó\t\u0006Í/\u0000üýúþ\u0013õ\u0006ÿ\rö\u000eýúûÊ9\u000bï\u000fø\u0001ú\u0010»6\u000eï\u0016ê\u0001\nùÉ\u0016.ï\u0016ê\u0001\nù÷\u0015ëÍ@û\u0006¿\u00147ûñÝ3ñ\u0000ÿ\r\rö\u000eýúûÊA\u0004»\u00143ô\u0003øÀ2ï\r\u0001ö\u0006ÿ÷\u0015ëÍ;\u0006¿\u00147ûñÜ1\u0000ï\u0018Ð%\u0002\u0005ÿß!þóü\fý\u000b\nó\u0002ÃE\u0006ú\u0001ñ\bÁ\u001b%ß\u0018\b\u0002\u0003\u0007Ë!\u0013Ë)õ\u0012\u0000Ù#ò\u0003\u0001\róü\u0003â/÷\u0000\rþ\u000fÒ#\u0003ù\u000eÑ%\t\u0005ö\u0001\u0013×\u0017û÷\u000bñþ\u000fÏ\u001e\u0002\u0005ýß%\t\u0017ñ\nÓ,ýþæ!þ÷\u0005ùóü\u0003ý\u000b\nó\u0002ÃE\u0006ú\u0001ñ\bÁ\u0016!\u0013Î#\u0003ù\fõ\u0001ú\u0004þ\u0002\u0005ýý\u000b\nó\u0002ÃE\u0006ú\u0001ñ\bÁ\u0014\u001f\u0012òß!\u0013Ë)õ\u0012\u0000Ù#ò\u0003\u0001\rþ\u000fÏ,õ\u0001Þ\u001e\u0002\u0005ýß%\t\u0005\u0003%Ó/\u0000Õ1ï\t\u0006à%÷õ\u0006õõë\u0007é\bF\u0001±Fû\u000b\u0000öÿ\u0002\b\b\u00adLù\u0001\u000eµë\u0006ê\bë\u0004ì\bë\bè\b\u0005\u0003%Ó/\u0000Õ1ï\t\u0006Ü\u0011\u0011ï\fø\u000fñ\rÜ\u0013\fø÷\u0015ëÍ>õ\rùÇ\u0015)õ\u0012\u0000Ù#ò\u0003\u0001\r\rö\u000eýúûÊ3\u000f\u0000¾\u0013/\u0000×%\u0003óÿ\u000b\u0007ò\u000fÞ\u0013ü\u0003ë\u001fþ\r4\fþÂ2\u000f\u0000\u0003ó\u0006\rì\r½:\u0005\u0006ñ\rüó\u000bÃ3Ë÷\u0015ëÍ;\u0006¿\u0016\u001d\u0013íè%\u0002\u0005ÿß!þóü\f\u0003ò\u0003à!\u0013\fþÁ3\u000f\u0000\u0003ó\u0006\rì\r¼;\u0005\u0006ñ\rüó\u000bÂ\u0013\u0005\u001bÐDÞñ\u0007Ù\u0000÷\u0015ëÍ;\u0006¿\u00147ûñÜ1\u0000ï\u0018Ö&ÿü\u0005ÿß!þóü\fë\u000b\tð\u000eøþ\u0007þ\u000fÏ)õ\u0012\u0000Ù#ò\u0003\u0001\r5ý\u0013íÎ5ý\u0013íÎ\u0001\u0007ù\u000fñ\fþÂ2\u000f\u0000\u0003ó\u0006\rì\r½:\u0005\u0006ñ\rüó\u000bÃ\u0012\u0005\u001bÐHÚñ\u0007ÙSþ\u000fÒþ\u0005\u0005\u001a\u0014ú\u0001û\u0003óò\u001bï\u000f\u0000õ\r\fþÂ2\u000f\u0000\u0003ó\u0006\rì\r½:\u0005\u0006ñ\rüó\u000bÃ\u0012\u0005\u001bÐDÞñ\u0007Ù\u0000ñ\u0007\u0014ê\u0005\u0006\rö\u000eýúûÊHóü\u0012·\u001f\"\u0005õ\u0006ÿ×1ï\t\u0006ó\u0013õ\rïç\u001dù\u0010ï\u0011\u0007×\u0011\u0013ôÝ'ù\bø\t\u0006ÿ\t\u0003\u0004ò4\fþÂ2\u000f\u0000\u0003ó\u0006\rì\r½:\u0005\u0006ñ\rüó\u000bÃ2Ì÷\u0015ëÍ;\u0006¿Fù\u0003ô\u0005\tþ\u000fÜ\"ý\u0001õ\r\u0002\u0005þ\u000fÍ!\u0011üý\tÿñë\u0011\u0013ô\rö\u000eýúûÊ3\u000f\u0000¾\u0013\"\u0011õ\ró\u000b\u0005Û\u0014\nóü\u0003÷\u0015ëÍ;\u0006¿\u0018#\u0003ùê&ÿü\u0005ÿß!þóü\fþ\ré\u001b÷\u000bñ÷\u0015ëÍ;\u0006¿\u001b\u0006ö3ë\u0002\u000b\u0004õ\u0006ÿ\rö\u000eýúûÊFñ\u0013üº&\u0011\u0013üá\u001fõ\u0003\u0007ñ\u0013ôä\u001d\n\u0001þ\u000fÕ%û\u000bõø\u000bÕ/\u0000üýúþ\u0013õ\u0006ÿ÷\u0015ëÍ;\u0006¿\u0018#\u0003ùõ\u0012\u0001Õ%ö\u0001\u0013×\u0017ë\u0003í\b\u0012ý\u0000ó\t\u0006à\u0015\u0004øè\u001c\u0003\u0000ý\n÷\u0015ëÍ;\u0006¿\u0018#\u0003ùß!\u000eð\u000f÷\u0007\u0004û\u0003ûÓ7ûñÜ1\u0000ï\u0018\u0003ò\u0003ß)õ\u0012\u0000\fþÁ3\u000f\u0000\u0003ó\u0006\rì\rûï\u000fó\u0013õ\rïç\u001dù\u0010ï\u0011\u0007Ë%\tóþ\u0011û\u0003÷ü\u000e÷\u0015ëÍ;\u0006¿\u001b%\u0002\u0005ÿß!þóü\f".getBytes(LocalizedMessage.DEFAULT_ENCODING), 0, bArr, 0, 1075);
        $$a = bArr;
        $$b = 19;
        int i3 = $11;
        int i4 = (i3 ^ 77) + ((i3 & 77) << 1);
        $10 = i4 % 128;
        if ((i4 % 2 != 0 ? (char) 17 : ',') != 17) {
        } else {
            throw null;
        }
    }

    private AFa1ySDK() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(50:1|2|3|(1:5)(1:1227)|6|(1:8)(1:1226)|9|10|11|(1:13)(1:1223)|(38:15|(3:1211|1212|1213)|(1:18)(1:1210)|19|(34:1206|1207|(1:23)(1:1205)|(32:25|26|27|(28:1198|1199|(1:31)(1:1197)|32|(3:(1:35)(1:1187)|36|(1:38)(5:1172|1173|1174|(2:1176|1177)(1:1180)|1178))(2:1188|(3:1190|1191|1192))|(1:40)(1:1171)|41|(6:43|44|45|46|47|48)|(4:65|66|67|68)|76|77|78|79|(1:81)(1:1168)|82|(1:84)(1:1167)|85|(1:89)(1:1166)|90|91|(1:93)(1:1164)|94|(1:96)(1:1163)|97|98|(4:100|(1:102)(1:1159)|103|(4:105|(1:107)(1:1156)|108|(3:110|(24:115|116|117|(8:(5:173|174|175|176|177)(1:120)|121|122|123|124|125|126|128)(1:1143)|(13:947|948|949|950|951|952|953|954|955|956|957|(3:(1:959)(1:1116)|960|(12:(4:963|(1:965)(1:1108)|966|(1:968)(1:1107))(1:(1:1110)(1:(1:1112)(1:1113)))|969|970|971|972|973|(10:(1:975)(1:1098)|(1:1001)(5:977|(1:979)(1:1000)|980|(4:982|(5:984|985|986|(2:988|989)|993)(1:996)|990|991)(2:998|999)|992)|994|995|137|138|139|140|(4:141|(1:143)(1:172)|144|(2:170|171)(3:146|(1:148)(1:169)|(1:150)(2:151|152)))|(2:154|155)(6:156|157|158|159|160|161))|1002|(10:1004|1005|1006|1007|1008|1009|1010|1011|1012|1013)(2:1030|(4:1032|1033|1034|1035)(9:(4:1044|1045|1046|1047)(10:1055|1056|1057|1058|1059|1060|1061|1062|1063|1016)|994|995|137|138|139|140|(5:141|(0)(0)|144|(0)(0)|150)|(0)(0)))|1014|1015|1016)(1:1114))|1115)(1:180)|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|(14:199|200|201|202|203|204|(1:206)(1:914)|207|(13:209|210|211|212|213|214|215|216|217|218|219|220|221)(29:852|853|854|855|856|857|858|859|860|861|862|863|864|865|866|867|868|869|870|871|872|873|874|875|876|877|878|879|880)|222|223|224|(46:603|604|(1:606)(1:824)|607|(1:609)(1:823)|(2:611|(1:613)(3:819|820|821))(1:822)|614|615|616|(3:618|619|620)|669|670|671|672|(11:674|(3:676|677|678)(3:798|799|800)|679|680|681|682|683|684|685|(4:687|688|689|690)(0)|701)|804|805|701|702|703|704|705|706|707|708|709|710|711|712|713|714|715|716|717|718|719|720|721|722|723|724|725|(1:727)(1:742)|728|(1:730)(4:732|733|734|735)|731)(53:226|227|228|229|230|231|232|233|234|235|236|237|(7:238|239|240|241|242|243|(1:1)(9:249|250|251|252|253|254|255|256|257))|272|273|274|275|276|277|278|279|280|281|282|284|285|286|287|288|289|290|291|292|293|294|295|296|297|298|299|300|301|302|303|304|305|306|307|308|(3:(1:310)(1:522)|311|(1:336)(4:313|314|315|316))|337|338|(2:340|341))|(22:343|344|345|346|347|348|349|350|351|352|353|354|355|356|357|358|359|360|361|(7:363|364|365|366|367|(1:369)(1:443)|370)(10:453|454|455|456|457|458|459|460|(1:462)(1:465)|463)|372|(21:374|375|376|377|378|379|380|(1:382)(1:416)|383|(1:385)(1:415)|386|387|388|389|390|391|392|393|394|395|396)(6:422|423|(1:425)(1:429)|426|427|428))(13:489|490|491|492|493|494|495|496|497|498|499|500|(0)(0))))(2:112|113)|114)(5:1147|1148|1149|1150|1151))(2:1157|1158))|1160|1161)|29|(0)(0)|32|(0)(0)|(0)(0)|41|(0)|(4:65|66|67|68)|76|77|78|79|(0)(0)|82|(0)(0)|85|(12:87|89|90|91|(0)(0)|94|(0)(0)|97|98|(0)|1160|1161)|1166|90|91|(0)(0)|94|(0)(0)|97|98|(0)|1160|1161)|1204|(0)|29|(0)(0)|32|(0)(0)|(0)(0)|41|(0)|(0)|76|77|78|79|(0)(0)|82|(0)(0)|85|(0)|1166|90|91|(0)(0)|94|(0)(0)|97|98|(0)|1160|1161)|21|(0)(0)|(0)|1204|(0)|29|(0)(0)|32|(0)(0)|(0)(0)|41|(0)|(0)|76|77|78|79|(0)(0)|82|(0)(0)|85|(0)|1166|90|91|(0)(0)|94|(0)(0)|97|98|(0)|1160|1161)|1219|1220|(0)(0)|19|(0)|21|(0)(0)|(0)|1204|(0)|29|(0)(0)|32|(0)(0)|(0)(0)|41|(0)|(0)|76|77|78|79|(0)(0)|82|(0)(0)|85|(0)|1166|90|91|(0)(0)|94|(0)(0)|97|98|(0)|1160|1161|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:1170:0x03db, code lost:
    
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0456, code lost:
    
        if (r2 != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x15ad, code lost:
    
        if (r6 != 7) goto L717;
     */
    /* JADX WARN: Code restructure failed: missing block: B:432:0x15e7, code lost:
    
        r6 = r2[134(0x86, float:1.88E-43)];
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x15f0, code lost:
    
        r4.getDeclaredMethod($$c(r6, (short) (r6 | 744), r2[11]), new java.lang.Class[0]).invoke(r3, new java.lang.Object[0]);
        r15 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:438:0x1605, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x1606, code lost:
    
        r2 = r0;
        r15 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x1639, code lost:
    
        r15 = r15;
        r40 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x161c, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:464:0x15e2, code lost:
    
        if (r6 != '\"') goto L717;
     */
    /* JADX WARN: Code restructure failed: missing block: B:997:0x0842, code lost:
    
        if (r2.nextBoolean() != false) goto L298;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:1022:0x060e A[Catch: all -> 0x089c, TryCatch #51 {all -> 0x089c, blocks: (B:1020:0x0608, B:1022:0x060e, B:1023:0x060f, B:1039:0x0661, B:1041:0x0667, B:1042:0x0668, B:1051:0x06b1, B:1053:0x06b7, B:1054:0x06b8, B:1067:0x077a, B:1069:0x0780, B:1070:0x0781, B:1080:0x078e, B:1089:0x07f6, B:1091:0x07fc, B:1092:0x07fd, B:1073:0x0783, B:1075:0x078a, B:1076:0x078b, B:1094:0x07ff, B:1096:0x0806, B:1097:0x0807, B:986:0x082d, B:989:0x0844, B:990:0x0849, B:992:0x0860, B:993:0x0847, B:996:0x0838, B:998:0x084f, B:1123:0x088b, B:1125:0x089a, B:1126:0x089b, B:1034:0x0617, B:1061:0x073b, B:1046:0x066c, B:1083:0x07bf, B:1084:0x07f3, B:1057:0x06ba), top: B:985:0x082d, inners: #22, #36, #59, #73, #97, #104 }] */
    /* JADX WARN: Removed duplicated region for block: B:1023:0x060f A[Catch: all -> 0x089c, TRY_LEAVE, TryCatch #51 {all -> 0x089c, blocks: (B:1020:0x0608, B:1022:0x060e, B:1023:0x060f, B:1039:0x0661, B:1041:0x0667, B:1042:0x0668, B:1051:0x06b1, B:1053:0x06b7, B:1054:0x06b8, B:1067:0x077a, B:1069:0x0780, B:1070:0x0781, B:1080:0x078e, B:1089:0x07f6, B:1091:0x07fc, B:1092:0x07fd, B:1073:0x0783, B:1075:0x078a, B:1076:0x078b, B:1094:0x07ff, B:1096:0x0806, B:1097:0x0807, B:986:0x082d, B:989:0x0844, B:990:0x0849, B:992:0x0860, B:993:0x0847, B:996:0x0838, B:998:0x084f, B:1123:0x088b, B:1125:0x089a, B:1126:0x089b, B:1034:0x0617, B:1061:0x073b, B:1046:0x066c, B:1083:0x07bf, B:1084:0x07f3, B:1057:0x06ba), top: B:985:0x082d, inners: #22, #36, #59, #73, #97, #104 }] */
    /* JADX WARN: Removed duplicated region for block: B:1163:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:1164:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:1167:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:1168:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:1171:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:1188:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:1197:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:1198:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1205:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:1206:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1210:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x1a99  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x1aa4 A[Catch: Exception -> 0x1b91, TRY_ENTER, TryCatch #81 {Exception -> 0x1b91, blocks: (B:3:0x002b, B:43:0x0234, B:52:0x1b7a, B:54:0x1b80, B:56:0x1b81, B:59:0x1b83, B:61:0x1b8a, B:62:0x1b8b, B:65:0x02aa, B:72:0x02fc, B:74:0x0302, B:75:0x0303, B:76:0x0304, B:79:0x0378, B:91:0x03c6, B:94:0x03cf, B:97:0x03d8, B:110:0x040e, B:154:0x1abc, B:114:0x1b4a, B:157:0x1ac9, B:165:0x1b21, B:167:0x1b27, B:168:0x1b28, B:146:0x1aa4, B:150:0x1aad, B:1148:0x1b6c, B:1151:0x1b73, B:1172:0x0195, B:1183:0x0214, B:1185:0x021a, B:1186:0x021b, B:1192:0x1b8d, B:1226:0x0052, B:48:0x0282, B:46:0x024a, B:68:0x02c1, B:1177:0x01c3, B:1180:0x01ec, B:160:0x1aeb, B:161:0x1b1e), top: B:2:0x002b, inners: #37, #40, #100, #120, #127 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x1abc A[Catch: Exception -> 0x1b91, TryCatch #81 {Exception -> 0x1b91, blocks: (B:3:0x002b, B:43:0x0234, B:52:0x1b7a, B:54:0x1b80, B:56:0x1b81, B:59:0x1b83, B:61:0x1b8a, B:62:0x1b8b, B:65:0x02aa, B:72:0x02fc, B:74:0x0302, B:75:0x0303, B:76:0x0304, B:79:0x0378, B:91:0x03c6, B:94:0x03cf, B:97:0x03d8, B:110:0x040e, B:154:0x1abc, B:114:0x1b4a, B:157:0x1ac9, B:165:0x1b21, B:167:0x1b27, B:168:0x1b28, B:146:0x1aa4, B:150:0x1aad, B:1148:0x1b6c, B:1151:0x1b73, B:1172:0x0195, B:1183:0x0214, B:1185:0x021a, B:1186:0x021b, B:1192:0x1b8d, B:1226:0x0052, B:48:0x0282, B:46:0x024a, B:68:0x02c1, B:1177:0x01c3, B:1180:0x01ec, B:160:0x1aeb, B:161:0x1b1e), top: B:2:0x002b, inners: #37, #40, #100, #120, #127 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x1ac9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x1aa2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x1a9c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x169a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x17ea A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0234 A[Catch: Exception -> 0x1b91, TRY_ENTER, TRY_LEAVE, TryCatch #81 {Exception -> 0x1b91, blocks: (B:3:0x002b, B:43:0x0234, B:52:0x1b7a, B:54:0x1b80, B:56:0x1b81, B:59:0x1b83, B:61:0x1b8a, B:62:0x1b8b, B:65:0x02aa, B:72:0x02fc, B:74:0x0302, B:75:0x0303, B:76:0x0304, B:79:0x0378, B:91:0x03c6, B:94:0x03cf, B:97:0x03d8, B:110:0x040e, B:154:0x1abc, B:114:0x1b4a, B:157:0x1ac9, B:165:0x1b21, B:167:0x1b27, B:168:0x1b28, B:146:0x1aa4, B:150:0x1aad, B:1148:0x1b6c, B:1151:0x1b73, B:1172:0x0195, B:1183:0x0214, B:1185:0x021a, B:1186:0x021b, B:1192:0x1b8d, B:1226:0x0052, B:48:0x0282, B:46:0x024a, B:68:0x02c1, B:1177:0x01c3, B:1180:0x01ec, B:160:0x1aeb, B:161:0x1b1e), top: B:2:0x002b, inners: #37, #40, #100, #120, #127 }] */
    /* JADX WARN: Removed duplicated region for block: B:451:0x161a A[Catch: all -> 0x161c, TryCatch #124 {all -> 0x161c, blocks: (B:431:0x15e5, B:436:0x15f0, B:449:0x1614, B:451:0x161a, B:452:0x161b), top: B:430:0x15e5 }] */
    /* JADX WARN: Removed duplicated region for block: B:452:0x161b A[Catch: all -> 0x161c, TRY_LEAVE, TryCatch #124 {all -> 0x161c, blocks: (B:431:0x15e5, B:436:0x15f0, B:449:0x1614, B:451:0x161a, B:452:0x161b), top: B:430:0x15e5 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02a8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:899:0x19da A[Catch: all -> 0x1a69, TryCatch #26 {all -> 0x1a69, blocks: (B:320:0x1877, B:329:0x18e2, B:331:0x18e8, B:332:0x18e9, B:528:0x18eb, B:530:0x18fa, B:531:0x18fb, B:542:0x190d, B:544:0x191a, B:545:0x191b, B:569:0x1929, B:571:0x1934, B:572:0x1935, B:580:0x193b, B:582:0x194a, B:583:0x194b, B:589:0x194d, B:591:0x195e, B:592:0x195f, B:595:0x1961, B:597:0x1972, B:598:0x1973, B:830:0x1978, B:832:0x198b, B:833:0x198c, B:884:0x19a8, B:886:0x19af, B:887:0x19b0, B:897:0x19d1, B:899:0x19da, B:900:0x19db, B:910:0x19dd, B:912:0x19f0, B:913:0x19f1, B:917:0x19fa, B:919:0x1a0f, B:920:0x1a10, B:924:0x1a30, B:926:0x1a37, B:927:0x1a38, B:933:0x1a3a, B:935:0x1a4f, B:936:0x1a50, B:939:0x1a52, B:941:0x1a67, B:942:0x1a68, B:189:0x0933, B:234:0x1232, B:186:0x08eb, B:231:0x11f4, B:224:0x0cc5, B:856:0x0b3f, B:323:0x18ab, B:324:0x18df, B:303:0x141f, B:203:0x09d9), top: B:188:0x0933, inners: #13, #15, #18, #23, #35, #90, #92, #95, #99 }] */
    /* JADX WARN: Removed duplicated region for block: B:900:0x19db A[Catch: all -> 0x1a69, TryCatch #26 {all -> 0x1a69, blocks: (B:320:0x1877, B:329:0x18e2, B:331:0x18e8, B:332:0x18e9, B:528:0x18eb, B:530:0x18fa, B:531:0x18fb, B:542:0x190d, B:544:0x191a, B:545:0x191b, B:569:0x1929, B:571:0x1934, B:572:0x1935, B:580:0x193b, B:582:0x194a, B:583:0x194b, B:589:0x194d, B:591:0x195e, B:592:0x195f, B:595:0x1961, B:597:0x1972, B:598:0x1973, B:830:0x1978, B:832:0x198b, B:833:0x198c, B:884:0x19a8, B:886:0x19af, B:887:0x19b0, B:897:0x19d1, B:899:0x19da, B:900:0x19db, B:910:0x19dd, B:912:0x19f0, B:913:0x19f1, B:917:0x19fa, B:919:0x1a0f, B:920:0x1a10, B:924:0x1a30, B:926:0x1a37, B:927:0x1a38, B:933:0x1a3a, B:935:0x1a4f, B:936:0x1a50, B:939:0x1a52, B:941:0x1a67, B:942:0x1a68, B:189:0x0933, B:234:0x1232, B:186:0x08eb, B:231:0x11f4, B:224:0x0cc5, B:856:0x0b3f, B:323:0x18ab, B:324:0x18df, B:303:0x141f, B:203:0x09d9), top: B:188:0x0933, inners: #13, #15, #18, #23, #35, #90, #92, #95, #99 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03d5  */
    /* JADX WARN: Type inference failed for: r15v103 */
    /* JADX WARN: Type inference failed for: r15v106 */
    /* JADX WARN: Type inference failed for: r15v109 */
    /* JADX WARN: Type inference failed for: r15v111 */
    /* JADX WARN: Type inference failed for: r15v122 */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v151 */
    /* JADX WARN: Type inference failed for: r15v152 */
    /* JADX WARN: Type inference failed for: r15v153 */
    /* JADX WARN: Type inference failed for: r15v154 */
    /* JADX WARN: Type inference failed for: r15v160 */
    /* JADX WARN: Type inference failed for: r15v171 */
    /* JADX WARN: Type inference failed for: r15v172 */
    /* JADX WARN: Type inference failed for: r15v173 */
    /* JADX WARN: Type inference failed for: r15v174 */
    /* JADX WARN: Type inference failed for: r15v175 */
    /* JADX WARN: Type inference failed for: r15v176 */
    /* JADX WARN: Type inference failed for: r15v35 */
    /* JADX WARN: Type inference failed for: r15v36 */
    /* JADX WARN: Type inference failed for: r15v38, types: [int] */
    /* JADX WARN: Type inference failed for: r15v42, types: [java.lang.Class[]] */
    /* JADX WARN: Type inference failed for: r15v45 */
    /* JADX WARN: Type inference failed for: r15v47, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r15v62 */
    /* JADX WARN: Type inference failed for: r15v64 */
    /* JADX WARN: Type inference failed for: r15v99 */
    /* JADX WARN: Type inference failed for: r2v125, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r40v10 */
    /* JADX WARN: Type inference failed for: r40v8 */
    /* JADX WARN: Type inference failed for: r40v9 */
    /* JADX WARN: Type inference failed for: r4v168 */
    /* JADX WARN: Type inference failed for: r4v81 */
    /* JADX WARN: Type inference failed for: r4v82 */
    /* JADX WARN: Type inference failed for: r5v154, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v155 */
    /* JADX WARN: Type inference failed for: r5v156 */
    /* JADX WARN: Type inference failed for: r5v157, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v68, types: [java.lang.Class] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    static {
        /*
            Method dump skipped, instructions count: 7074
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1ySDK.<clinit>():void");
    }
}
