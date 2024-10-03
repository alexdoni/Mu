package com.mu.utility.Encrypt;

import io.jsonwebtoken.JwtParser;
import java.util.HashMap;
import kotlin.text.Typography;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class Encrypt4 {
    public static HashMap<Object, Object> deencryptDic;
    public static HashMap<Object, Object> encryptDic;

    public static void EncryptInit5() {
    }

    public static void EncryptInit6() {
    }

    public static void Init() {
        encryptDic = new HashMap<>();
        deencryptDic = new HashMap<>();
        EncryptInit3();
        EncryptInit4();
        EncryptInit5();
        EncryptInit7();
        EncryptInit8();
        EncryptInit9();
    }

    public static void EncryptInit1() {
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Typography.quote);
        hashMap.put((char) 957, valueOf);
        deencryptDic.put((char) 12567, valueOf);
        deencryptDic.put((char) 946, valueOf);
        encryptDic.put(valueOf, "νㄗβ");
        deencryptDic.put((char) 958, ',');
        deencryptDic.put((char) 12383, ',');
        deencryptDic.put((char) 12433, ',');
        encryptDic.put(',', "ξたゑ");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(JwtParser.SEPARATOR_CHAR);
        hashMap2.put((char) 1052, valueOf2);
        deencryptDic.put((char) 1066, valueOf2);
        deencryptDic.put((char) 12375, valueOf2);
        encryptDic.put(valueOf2, "МЪし");
        deencryptDic.put((char) 12562, '/');
        deencryptDic.put((char) 12559, '/');
        deencryptDic.put((char) 965, '/');
        encryptDic.put('/', "ㄒㄏυ");
        deencryptDic.put((char) 1056, '0');
        deencryptDic.put((char) 12421, '0');
        deencryptDic.put((char) 1050, '0');
        encryptDic.put('0', "РゅК");
        deencryptDic.put((char) 1046, '1');
        deencryptDic.put((char) 12551, '1');
        deencryptDic.put((char) 12467, '1');
        encryptDic.put('1', "Жㄇコ");
        deencryptDic.put((char) 12583, '2');
        deencryptDic.put((char) 948, '2');
        deencryptDic.put((char) 12493, '2');
        encryptDic.put('2', "ㄧδネ");
        deencryptDic.put((char) 65079, '3');
        deencryptDic.put((char) 924, '3');
        deencryptDic.put((char) 65078, '3');
        encryptDic.put('3', "︷Μ︶");
        deencryptDic.put((char) 969, '4');
        deencryptDic.put((char) 12556, '4');
        deencryptDic.put((char) 1069, '4');
        encryptDic.put('4', "ωㄌЭ");
        deencryptDic.put((char) 922, '5');
        deencryptDic.put((char) 955, '5');
        deencryptDic.put((char) 966, '5');
        encryptDic.put('5', "Κλφ");
        deencryptDic.put((char) 12408, '6');
        deencryptDic.put((char) 12473, '6');
        deencryptDic.put((char) 1025, '6');
        encryptDic.put('6', "へスЁ");
        deencryptDic.put((char) 12434, '7');
        deencryptDic.put((char) 1059, '7');
        deencryptDic.put((char) 12568, '7');
        encryptDic.put('7', "をУㄘ");
        deencryptDic.put((char) 1041, '8');
        deencryptDic.put((char) 1058, '8');
        deencryptDic.put((char) 12515, '8');
        encryptDic.put('8', "БТャ");
    }

    public static void EncryptInit2() {
        deencryptDic.put((char) 12488, 'a');
        encryptDic.put('a', "ッ︹ト");
        deencryptDic.put((char) 923, 'b');
        deencryptDic.put((char) 932, 'b');
        deencryptDic.put((char) 65077, 'b');
        encryptDic.put('b', "ΛΤ︵");
        deencryptDic.put((char) 951, 'c');
        deencryptDic.put((char) 1042, 'c');
        deencryptDic.put((char) 961, 'c');
        encryptDic.put('c', "ηВρ");
        deencryptDic.put((char) 936, 'd');
        deencryptDic.put((char) 1044, 'd');
        deencryptDic.put((char) 65090, 'd');
        encryptDic.put('d', "ΨД﹂");
        deencryptDic.put((char) 12361, 'e');
        deencryptDic.put((char) 12565, 'e');
        deencryptDic.put((char) 12580, 'e');
        encryptDic.put('e', "ぉㄕㄤ");
        deencryptDic.put((char) 1051, 'f');
        deencryptDic.put((char) 65083, 'f');
        deencryptDic.put((char) 1047, 'f');
        encryptDic.put('f', "Л︻З");
        deencryptDic.put((char) 12514, 'g');
        deencryptDic.put((char) 12394, 'g');
        deencryptDic.put((char) 12492, 'g');
        encryptDic.put('g', "モなヌ");
        deencryptDic.put((char) 12365, 'h');
        deencryptDic.put((char) 12363, 'h');
        deencryptDic.put((char) 920, 'h');
        encryptDic.put('h', "きかΘ");
        deencryptDic.put((char) 65089, 'i');
        deencryptDic.put((char) 12367, 'i');
        deencryptDic.put((char) 12457, 'i');
        encryptDic.put('i', "﹁くォ");
        deencryptDic.put((char) 12387, 'j');
        deencryptDic.put((char) 12579, 'j');
        deencryptDic.put((char) 952, 'j');
        encryptDic.put('j', "っㄣθ");
        deencryptDic.put((char) 12577, 'k');
        deencryptDic.put((char) 12570, 'k');
        deencryptDic.put((char) 964, 'k');
        encryptDic.put('k', "ㄡㄚτ");
        deencryptDic.put((char) 12399, 'l');
        deencryptDic.put((char) 8721, 'l');
        deencryptDic.put((char) 968, 'l');
        encryptDic.put('l', "は∑ψ");
        deencryptDic.put((char) 65086, 'm');
        deencryptDic.put((char) 12526, 'm');
        deencryptDic.put((char) 12396, 'm');
        encryptDic.put('m', "︾ヮぬ");
        deencryptDic.put((char) 933, 'n');
        deencryptDic.put((char) 937, 'n');
        deencryptDic.put((char) 12582, 'n');
        encryptDic.put('n', "ΥΩㄦ");
    }

    public static void EncryptInit3() {
        deencryptDic.put((char) 12479, 'o');
        deencryptDic.put((char) 12453, 'o');
        deencryptDic.put((char) 956, 'o');
        encryptDic.put('o', "タゥμ");
        deencryptDic.put((char) 12555, 'p');
        deencryptDic.put((char) 914, 'p');
        deencryptDic.put((char) 12359, 'p');
        encryptDic.put('p', "ㄋΒぇ");
        deencryptDic.put((char) 12517, 'q');
        deencryptDic.put((char) 1071, 'q');
        deencryptDic.put((char) 65087, 'q');
        encryptDic.put('q', "ュЯ︿");
        deencryptDic.put((char) 12557, 'r');
        deencryptDic.put((char) 12495, 'r');
        deencryptDic.put((char) 926, 'r');
        encryptDic.put('r', "ㄍハΞ");
        deencryptDic.put((char) 12475, 's');
        deencryptDic.put((char) 12560, 's');
        deencryptDic.put((char) 12486, 's');
        encryptDic.put('s', "セㄐテ");
        deencryptDic.put((char) 12477, 't');
        deencryptDic.put((char) 12491, 't');
        deencryptDic.put((char) 12417, 't');
        encryptDic.put('t', "ソニめ");
        deencryptDic.put((char) 12571, 'u');
        deencryptDic.put((char) 949, 'u');
        deencryptDic.put((char) 65085, 'u');
        encryptDic.put('u', "ㄛε︽");
        deencryptDic.put((char) 12574, 'v');
        deencryptDic.put((char) 959, 'v');
        deencryptDic.put((char) 12419, 'v');
        encryptDic.put('v', "ㄞοゃ");
        deencryptDic.put((char) 12501, 'w');
        deencryptDic.put((char) 65084, 'w');
        deencryptDic.put((char) 1068, 'w');
        encryptDic.put('w', "フ︼Ь");
        deencryptDic.put((char) 917, 'x');
        deencryptDic.put((char) 12558, 'x');
        deencryptDic.put((char) 12463, 'x');
        encryptDic.put('x', "Εㄎク");
    }

    public static void EncryptInit4() {
        deencryptDic.put((char) 12575, 'y');
        deencryptDic.put((char) 8719, 'y');
        deencryptDic.put((char) 12554, 'y');
        encryptDic.put('y', "ㄟ∏ㄊ");
        deencryptDic.put((char) 12550, 'z');
        deencryptDic.put((char) 12379, 'z');
        deencryptDic.put((char) 12513, 'z');
        encryptDic.put('z', "ㄆせメ");
        deencryptDic.put((char) 12490, '{');
        deencryptDic.put((char) 12510, '{');
        deencryptDic.put((char) 12373, '{');
        encryptDic.put('{', "ナマさ");
        deencryptDic.put((char) 12415, '}');
        deencryptDic.put((char) 1070, '}');
        deencryptDic.put((char) 12392, '}');
        encryptDic.put('}', "みЮと");
    }

    public static void EncryptInit7() {
        deencryptDic.put((char) 950, 'T');
        deencryptDic.put((char) 929, 'T');
        deencryptDic.put((char) 12552, 'T');
        encryptDic.put('T', "ζΡㄈ");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT);
        hashMap.put((char) 12395, valueOf);
        deencryptDic.put((char) 12451, valueOf);
        deencryptDic.put((char) 12519, valueOf);
        encryptDic.put(valueOf, "にィョ");
        deencryptDic.put((char) 12353, 'V');
        deencryptDic.put((char) 1061, 'V');
        deencryptDic.put((char) 12553, 'V');
        encryptDic.put('V', "ぁХㄉ");
        deencryptDic.put((char) 12432, 'W');
        deencryptDic.put((char) 945, 'W');
        deencryptDic.put((char) 12569, 'W');
        encryptDic.put('W', "ゐαㄙ");
        deencryptDic.put((char) 1060, 'X');
        deencryptDic.put((char) 12528, 'X');
        deencryptDic.put((char) 928, 'X');
        encryptDic.put('X', "ФヰΠ");
        deencryptDic.put((char) 921, 'Y');
        deencryptDic.put((char) 65082, 'Y');
        deencryptDic.put((char) 12414, 'Y');
        encryptDic.put('Y', "Ι︺ま");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(Matrix.MATRIX_TYPE_ZERO);
        hashMap2.put((char) 12572, valueOf2);
        deencryptDic.put((char) 12512, valueOf2);
        deencryptDic.put((char) 947, valueOf2);
        encryptDic.put(valueOf2, "ㄜムγ");
        deencryptDic.put((char) 918, '[');
        deencryptDic.put((char) 1057, '[');
        deencryptDic.put((char) 12584, '[');
        encryptDic.put('[', "ΖСㄨ");
        deencryptDic.put((char) 12563, ']');
        deencryptDic.put((char) 12402, ']');
        deencryptDic.put((char) 12397, ']');
        encryptDic.put(']', "ㄓひね");
        deencryptDic.put((char) 12388, '_');
        deencryptDic.put((char) 1045, '_');
        deencryptDic.put((char) 12357, '_');
        encryptDic.put('_', "つЕぅ");
        deencryptDic.put((char) 12483, 'a');
        deencryptDic.put((char) 65081, 'a');
    }

    public static void EncryptInit8() {
        deencryptDic.put((char) 12484, 'I');
        deencryptDic.put((char) 919, 'I');
        encryptDic.put('I', "シツΗ");
        deencryptDic.put((char) 12576, 'J');
        deencryptDic.put((char) 12481, 'J');
        deencryptDic.put((char) 1049, 'J');
        encryptDic.put('J', "ㄠチЙ");
        deencryptDic.put((char) 963, 'K');
        deencryptDic.put((char) 935, 'K');
        deencryptDic.put((char) 12423, 'K');
        encryptDic.put('K', "σΧょ");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT);
        hashMap.put((char) 1054, valueOf);
        deencryptDic.put((char) 12430, valueOf);
        deencryptDic.put((char) 1055, valueOf);
        encryptDic.put(valueOf, "ОゎП");
        deencryptDic.put((char) 12511, 'M');
        deencryptDic.put((char) 916, 'M');
        deencryptDic.put((char) 12534, 'M');
        encryptDic.put('M', "ミΔヶ");
        deencryptDic.put((char) 953, 'N');
        deencryptDic.put((char) 8743, 'N');
        deencryptDic.put((char) 1065, 'N');
        encryptDic.put('N', "ι∧Щ");
        deencryptDic.put((char) 12371, 'O');
        deencryptDic.put((char) 12585, 'O');
        deencryptDic.put((char) 12435, 'O');
        encryptDic.put('O', "こㄩん");
        deencryptDic.put((char) 1063, 'P');
        deencryptDic.put((char) 12504, 'P');
        deencryptDic.put((char) 12566, 'P');
        encryptDic.put('P', "Чヘㄖ");
        deencryptDic.put((char) 12530, 'Q');
        deencryptDic.put((char) 65088, 'Q');
        deencryptDic.put((char) 12533, 'Q');
        encryptDic.put('Q', "ヲ﹀ヵ");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        hashMap2.put((char) 1043, valueOf2);
        deencryptDic.put((char) 12494, valueOf2);
        deencryptDic.put((char) 915, valueOf2);
        encryptDic.put(valueOf2, "ГノΓ");
        deencryptDic.put((char) 1053, 'S');
        deencryptDic.put((char) 12405, 'S');
        deencryptDic.put((char) 1067, 'S');
        encryptDic.put('S', "НふЫ");
    }

    public static void EncryptInit9() {
        deencryptDic.put((char) 967, '9');
        deencryptDic.put((char) 960, '9');
        deencryptDic.put((char) 65080, '9');
        encryptDic.put('9', "χπ︸");
        deencryptDic.put((char) 1048, ':');
        deencryptDic.put((char) 954, ':');
        deencryptDic.put((char) 12377, ':');
        encryptDic.put(':', "Иκす");
        deencryptDic.put((char) 934, 'A');
        deencryptDic.put((char) 12461, 'A');
        deencryptDic.put((char) 12455, 'A');
        encryptDic.put('A', "Φキェ");
        deencryptDic.put((char) 12418, 'B');
        deencryptDic.put((char) 931, 'B');
        deencryptDic.put((char) 12532, 'B');
        encryptDic.put('B', "もΣヴ");
        deencryptDic.put((char) 12390, 'C');
        deencryptDic.put((char) 1040, 'C');
        deencryptDic.put((char) 12416, 'C');
        encryptDic.put('C', "てАむ");
        deencryptDic.put((char) 65091, 'D');
        deencryptDic.put((char) 1064, 'D');
        deencryptDic.put((char) 12411, 'D');
        encryptDic.put('D', "﹃Шほ");
        deencryptDic.put((char) 12529, 'E');
        deencryptDic.put((char) 1062, 'E');
        deencryptDic.put((char) 927, 'E');
        encryptDic.put('E', "ヱЦΟ");
        deencryptDic.put((char) 12459, 'F');
        deencryptDic.put((char) 12385, 'F');
        deencryptDic.put((char) 12498, 'F');
        encryptDic.put('F', "カちヒ");
        deencryptDic.put((char) 12469, 'G');
        deencryptDic.put((char) 12449, 'G');
        deencryptDic.put((char) 913, 'G');
        encryptDic.put('G', "サァΑ");
        deencryptDic.put((char) 12381, 'H');
        deencryptDic.put((char) 925, 'H');
        deencryptDic.put((char) 12465, 'H');
        encryptDic.put('H', "そΝケ");
        deencryptDic.put((char) 12471, 'I');
    }

    public static String DeCode(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = deencryptDic.containsKey(Character.valueOf(str.charAt(i))) ? str2 + deencryptDic.get(Character.valueOf(str.charAt(i))).toString() : str2 + str.charAt(i);
        }
        return str2;
    }
}
