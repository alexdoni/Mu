package com.mu.utility.Encrypt;

import io.jsonwebtoken.JwtParser;
import java.util.HashMap;
import kotlin.text.Typography;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class Encrypt {
    public static HashMap<Object, Object> deencryptDic;

    public static void Init() {
        deencryptDic = new HashMap<>();
        EncryptInit1();
        EncryptInit2();
        EncryptInit3();
        EncryptInit4();
        EncryptInit5();
        EncryptInit6();
        EncryptInit7();
        EncryptInit8();
        EncryptInit9();
    }

    public static void EncryptInit1() {
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Typography.quote);
        hashMap.put((char) 12555, valueOf);
        deencryptDic.put((char) 948, valueOf);
        deencryptDic.put((char) 12402, valueOf);
        deencryptDic.put((char) 914, ',');
        deencryptDic.put((char) 952, ',');
        deencryptDic.put((char) 12419, ',');
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(JwtParser.SEPARATOR_CHAR);
        hashMap2.put((char) 12517, valueOf2);
        deencryptDic.put((char) 1045, valueOf2);
        deencryptDic.put((char) 12369, valueOf2);
        deencryptDic.put((char) 913, '/');
        deencryptDic.put((char) 12557, '/');
        deencryptDic.put((char) 12390, '/');
        deencryptDic.put((char) 1057, '0');
        deencryptDic.put((char) 921, '0');
        deencryptDic.put((char) 936, '0');
        deencryptDic.put((char) 12399, '1');
        deencryptDic.put((char) 12473, '1');
        deencryptDic.put((char) 955, '1');
        deencryptDic.put((char) 12488, '2');
        deencryptDic.put((char) 12583, '2');
        deencryptDic.put((char) 951, '2');
        deencryptDic.put((char) 12379, '3');
        deencryptDic.put((char) 954, '3');
        deencryptDic.put((char) 935, '3');
        deencryptDic.put((char) 65092, '4');
        deencryptDic.put((char) 1048, '4');
        deencryptDic.put((char) 12451, '4');
        deencryptDic.put((char) 12367, '5');
        deencryptDic.put((char) 12511, '5');
        deencryptDic.put((char) 1055, '5');
        deencryptDic.put((char) 918, '6');
    }

    public static void EncryptInit2() {
        deencryptDic.put((char) 953, '6');
        deencryptDic.put((char) 12567, '6');
        deencryptDic.put((char) 12573, '7');
        deencryptDic.put((char) 12363, '7');
        deencryptDic.put((char) 946, '7');
        deencryptDic.put((char) 956, '8');
        deencryptDic.put((char) 1047, '8');
        deencryptDic.put((char) 12433, '8');
        deencryptDic.put((char) 945, '9');
        deencryptDic.put((char) 925, '9');
        deencryptDic.put((char) 1064, '9');
        deencryptDic.put((char) 1059, ':');
        deencryptDic.put((char) 12397, ':');
        deencryptDic.put((char) 12486, ':');
        deencryptDic.put((char) 12375, 'A');
        deencryptDic.put((char) 1040, 'A');
        deencryptDic.put((char) 1071, 'A');
        deencryptDic.put((char) 1060, 'B');
        deencryptDic.put((char) 12432, 'B');
        deencryptDic.put((char) 12469, 'B');
        deencryptDic.put((char) 65087, 'C');
        deencryptDic.put((char) 12477, 'C');
        deencryptDic.put((char) 12365, 'C');
    }

    public static void EncryptInit3() {
        deencryptDic.put((char) 12449, 'D');
        deencryptDic.put((char) 958, 'D');
        deencryptDic.put((char) 917, 'D');
        deencryptDic.put((char) 933, 'E');
        deencryptDic.put((char) 12564, 'E');
        deencryptDic.put((char) 915, 'E');
        deencryptDic.put((char) 12515, 'F');
        deencryptDic.put((char) 1051, 'F');
        deencryptDic.put((char) 8721, 'F');
        deencryptDic.put((char) 12562, 'G');
        deencryptDic.put((char) 12552, 'G');
        deencryptDic.put((char) 12430, 'G');
        deencryptDic.put((char) 959, 'H');
        deencryptDic.put((char) 12501, 'H');
        deencryptDic.put((char) 12580, 'H');
    }

    public static void EncryptInit4() {
        deencryptDic.put((char) 924, 'I');
        deencryptDic.put((char) 947, 'I');
        deencryptDic.put((char) 12459, 'I');
        deencryptDic.put((char) 965, 'J');
        deencryptDic.put((char) 1058, 'J');
        deencryptDic.put((char) 12484, 'J');
        deencryptDic.put((char) 12519, 'K');
        deencryptDic.put((char) 65089, 'K');
        deencryptDic.put((char) 967, 'K');
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT);
        hashMap.put((char) 1041, valueOf);
        deencryptDic.put((char) 961, valueOf);
        deencryptDic.put((char) 1063, valueOf);
        deencryptDic.put((char) 12355, 'M');
        deencryptDic.put((char) 1044, 'M');
        deencryptDic.put((char) 1043, 'M');
        deencryptDic.put((char) 963, 'N');
        deencryptDic.put((char) 968, 'N');
        deencryptDic.put((char) 12418, 'N');
        deencryptDic.put((char) 12568, 'O');
        deencryptDic.put((char) 12465, 'O');
        deencryptDic.put((char) 929, 'O');
        deencryptDic.put((char) 12571, 'P');
        deencryptDic.put((char) 65082, 'P');
        deencryptDic.put((char) 12463, 'P');
        deencryptDic.put((char) 12483, 'Q');
    }

    public static void EncryptInit5() {
        deencryptDic.put((char) 950, 'Q');
        deencryptDic.put((char) 1049, 'Q');
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        hashMap.put((char) 12434, valueOf);
        deencryptDic.put((char) 12559, valueOf);
        deencryptDic.put((char) 12490, valueOf);
        deencryptDic.put((char) 969, 'S');
        deencryptDic.put((char) 12584, 'S');
        deencryptDic.put((char) 12411, 'S');
        deencryptDic.put((char) 12504, 'T');
        deencryptDic.put((char) 1042, 'T');
        deencryptDic.put((char) 12471, 'T');
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT);
        hashMap2.put((char) 937, valueOf2);
        deencryptDic.put((char) 12526, valueOf2);
        deencryptDic.put((char) 1061, valueOf2);
        deencryptDic.put((char) 12357, 'V');
        deencryptDic.put((char) 12398, 'V');
        deencryptDic.put((char) 65078, 'V');
        deencryptDic.put((char) 1046, 'W');
        deencryptDic.put((char) 12495, 'W');
    }

    public static void EncryptInit6() {
        deencryptDic.put((char) 949, 'W');
        deencryptDic.put((char) 1067, 'X');
        deencryptDic.put((char) 1069, 'X');
        deencryptDic.put((char) 931, 'X');
        deencryptDic.put((char) 12579, 'Y');
        deencryptDic.put((char) 926, 'Y');
        deencryptDic.put((char) 65086, 'Y');
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_ZERO);
        hashMap.put((char) 12467, valueOf);
        deencryptDic.put((char) 12577, valueOf);
        deencryptDic.put((char) 65079, valueOf);
        deencryptDic.put((char) 932, '[');
        deencryptDic.put((char) 65081, '[');
        deencryptDic.put((char) 12371, '[');
        deencryptDic.put((char) 12475, ']');
        deencryptDic.put((char) 1066, ']');
        deencryptDic.put((char) 12381, ']');
        deencryptDic.put((char) 65080, '_');
        deencryptDic.put((char) 1052, '_');
        deencryptDic.put((char) 12507, '_');
        deencryptDic.put((char) 12556, 'a');
    }

    public static void EncryptInit7() {
        deencryptDic.put((char) 12565, 'a');
        deencryptDic.put((char) 12417, 'a');
        deencryptDic.put((char) 12551, 'b');
        deencryptDic.put((char) 65090, 'b');
        deencryptDic.put((char) 12572, 'b');
        deencryptDic.put((char) 12373, 'c');
        deencryptDic.put((char) 12385, 'c');
        deencryptDic.put((char) 1025, 'c');
        deencryptDic.put((char) 8719, 'd');
        deencryptDic.put((char) 12353, 'd');
        deencryptDic.put((char) 12585, 'd');
        deencryptDic.put((char) 12461, 'e');
        deencryptDic.put((char) 966, 'e');
        deencryptDic.put((char) 12574, 'e');
        deencryptDic.put((char) 12513, 'f');
        deencryptDic.put((char) 12479, 'f');
        deencryptDic.put((char) 12530, 'f');
        deencryptDic.put((char) 12392, 'g');
        deencryptDic.put((char) 12561, 'g');
        deencryptDic.put((char) 922, 'g');
        deencryptDic.put((char) 12566, 'h');
        deencryptDic.put((char) 12528, 'h');
        deencryptDic.put((char) 927, 'h');
        deencryptDic.put((char) 65085, 'i');
        deencryptDic.put((char) 12408, 'i');
        deencryptDic.put((char) 1050, 'i');
        deencryptDic.put((char) 934, 'j');
        deencryptDic.put((char) 1054, 'j');
        deencryptDic.put((char) 12582, 'j');
    }

    public static void EncryptInit8() {
        deencryptDic.put((char) 1056, 'k');
        deencryptDic.put((char) 12421, 'k');
        deencryptDic.put((char) 1053, 'k');
        deencryptDic.put((char) 12453, 'l');
        deencryptDic.put((char) 960, 'l');
        deencryptDic.put((char) 12569, 'l');
        deencryptDic.put((char) 1068, 'm');
        deencryptDic.put((char) 12492, 'm');
        deencryptDic.put((char) 957, 'm');
        deencryptDic.put((char) 12532, 'n');
        deencryptDic.put((char) 12512, 'n');
        deencryptDic.put((char) 12383, 'n');
        deencryptDic.put((char) 12481, 'o');
        deencryptDic.put((char) 12416, 'o');
        deencryptDic.put((char) 928, 'o');
        deencryptDic.put((char) 12405, 'p');
        deencryptDic.put((char) 12498, 'p');
        deencryptDic.put((char) 916, 'p');
        deencryptDic.put((char) 12493, 'q');
        deencryptDic.put((char) 12387, 'q');
        deencryptDic.put((char) 12514, 'q');
        deencryptDic.put((char) 12388, 'r');
        deencryptDic.put((char) 12491, 'r');
        deencryptDic.put((char) 12558, 'r');
    }

    public static void EncryptInit9() {
        deencryptDic.put((char) 1062, 's');
        deencryptDic.put((char) 1065, 's');
        deencryptDic.put((char) 12377, 's');
        deencryptDic.put((char) 12394, 't');
        deencryptDic.put((char) 65091, 't');
        deencryptDic.put((char) 12435, 't');
        deencryptDic.put((char) 12395, 'u');
        deencryptDic.put((char) 65077, 'u');
        deencryptDic.put((char) 923, 'u');
        deencryptDic.put((char) 12361, 'v');
        deencryptDic.put((char) 12534, 'v');
        deencryptDic.put((char) 1070, 'v');
        deencryptDic.put((char) 12553, 'w');
        deencryptDic.put((char) 8743, 'w');
        deencryptDic.put((char) 12529, 'w');
        deencryptDic.put((char) 12414, 'x');
        deencryptDic.put((char) 964, 'x');
        deencryptDic.put((char) 919, 'x');
        deencryptDic.put((char) 12415, 'y');
        deencryptDic.put((char) 12550, 'y');
        deencryptDic.put((char) 12457, 'y');
        deencryptDic.put((char) 12578, 'z');
        deencryptDic.put((char) 12531, 'z');
        deencryptDic.put((char) 12570, 'z');
        deencryptDic.put((char) 12359, '{');
        deencryptDic.put((char) 65088, '{');
        deencryptDic.put((char) 920, '{');
        deencryptDic.put((char) 12576, '}');
        deencryptDic.put((char) 12554, '}');
        deencryptDic.put((char) 12423, '}');
    }

    public static String DeCode(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = deencryptDic.containsKey(Character.valueOf(str.charAt(i))) ? str2 + deencryptDic.get(Character.valueOf(str.charAt(i))).toString() : str2 + str.charAt(i);
        }
        return str2;
    }
}
