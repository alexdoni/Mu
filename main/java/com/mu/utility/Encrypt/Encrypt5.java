package com.mu.utility.Encrypt;

import io.jsonwebtoken.JwtParser;
import java.util.HashMap;
import kotlin.text.Typography;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class Encrypt5 {
    public static HashMap<Object, Object> deencryptDic;
    public static HashMap<Object, Object> encryptDic;

    public static void EncryptInit5() {
    }

    public static void EncryptInit6() {
    }

    public static void Init() {
        encryptDic = new HashMap<>();
        deencryptDic = new HashMap<>();
        EncryptInit1();
        EncryptInit3();
        EncryptInit4();
        EncryptInit5();
    }

    public static void EncryptInit1() {
        encryptDic.put('7', "Τυみ");
        deencryptDic.put((char) 12483, '8');
        deencryptDic.put((char) 921, '8');
        deencryptDic.put((char) 12584, '8');
        encryptDic.put('8', "ッΙㄨ");
        deencryptDic.put((char) 12399, '9');
        deencryptDic.put((char) 919, '9');
        deencryptDic.put((char) 12578, '9');
        encryptDic.put('9', "はΗㄢ");
        deencryptDic.put((char) 934, ':');
        deencryptDic.put((char) 12567, ':');
        deencryptDic.put((char) 65090, ':');
        encryptDic.put(':', "Φㄗ﹂");
        deencryptDic.put((char) 12493, 'A');
        deencryptDic.put((char) 937, 'A');
        deencryptDic.put((char) 967, 'A');
        encryptDic.put('A', "ネΩχ");
        deencryptDic.put((char) 12512, 'B');
        deencryptDic.put((char) 946, 'B');
        deencryptDic.put((char) 12561, 'B');
        encryptDic.put('B', "ムβㄑ");
        deencryptDic.put((char) 1054, 'C');
        deencryptDic.put((char) 1062, 'C');
        deencryptDic.put((char) 12581, 'C');
        encryptDic.put('C', "ОЦㄥ");
        deencryptDic.put((char) 8743, 'D');
        deencryptDic.put((char) 952, 'D');
        deencryptDic.put((char) 926, 'D');
        encryptDic.put('D', "∧θΞ");
        deencryptDic.put((char) 12566, 'E');
        deencryptDic.put((char) 947, 'E');
        deencryptDic.put((char) 963, 'E');
        encryptDic.put('E', "ㄖγσ");
        deencryptDic.put((char) 65079, 'F');
        deencryptDic.put((char) 960, 'F');
        deencryptDic.put((char) 12469, 'F');
    }

    public static void EncryptInit2() {
        encryptDic.put('F', "︷πサ");
        deencryptDic.put((char) 1044, 'G');
        deencryptDic.put((char) 12575, 'G');
        deencryptDic.put((char) 12559, 'G');
        encryptDic.put('G', "Дㄟㄏ");
        deencryptDic.put((char) 12552, 'H');
        deencryptDic.put((char) 1065, 'H');
        deencryptDic.put((char) 969, 'H');
        encryptDic.put('H', "ㄈЩω");
        deencryptDic.put((char) 65092, 'I');
        deencryptDic.put((char) 951, 'I');
        deencryptDic.put((char) 1045, 'I');
        encryptDic.put('I', "﹄ηЕ");
        deencryptDic.put((char) 1025, 'J');
        deencryptDic.put((char) 948, 'J');
        deencryptDic.put((char) 922, 'J');
        encryptDic.put('J', "ЁδΚ");
        deencryptDic.put((char) 12417, 'K');
        deencryptDic.put((char) 917, 'K');
        deencryptDic.put((char) 12484, 'K');
        encryptDic.put('K', "めΕツ");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT);
        hashMap.put((char) 12435, valueOf);
        deencryptDic.put((char) 1071, valueOf);
        deencryptDic.put((char) 1055, valueOf);
        encryptDic.put(valueOf, "んЯП");
        deencryptDic.put((char) 955, 'M');
        deencryptDic.put((char) 1051, 'M');
        deencryptDic.put((char) 954, 'M');
        encryptDic.put('M', "λЛκ");
        deencryptDic.put((char) 1058, 'N');
        deencryptDic.put((char) 918, 'N');
        deencryptDic.put((char) 957, 'N');
        encryptDic.put('N', "ТΖν");
        deencryptDic.put((char) 1046, 'O');
        deencryptDic.put((char) 12529, 'O');
        deencryptDic.put((char) 65077, 'O');
        encryptDic.put('O', "Жヱ︵");
    }

    public static void EncryptInit3() {
        deencryptDic.put((char) 12515, 'P');
        deencryptDic.put((char) 964, 'P');
        deencryptDic.put((char) 961, 'P');
        encryptDic.put('P', "ャτρ");
        deencryptDic.put((char) 12519, 'Q');
        deencryptDic.put((char) 12491, 'Q');
        deencryptDic.put((char) 12414, 'Q');
        encryptDic.put('Q', "ョニま");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        hashMap.put((char) 12377, valueOf);
        deencryptDic.put((char) 915, valueOf);
        deencryptDic.put((char) 12585, valueOf);
        encryptDic.put(valueOf, "すΓㄩ");
        deencryptDic.put((char) 12562, 'S');
        deencryptDic.put((char) 1049, 'S');
        deencryptDic.put((char) 958, 'S');
        encryptDic.put('S', "ㄒЙξ");
        deencryptDic.put((char) 914, 'T');
        deencryptDic.put((char) 12471, 'T');
        deencryptDic.put((char) 12432, 'T');
        encryptDic.put('T', "Βシゐ");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT);
        hashMap2.put((char) 959, valueOf2);
        deencryptDic.put((char) 12411, valueOf2);
        deencryptDic.put((char) 12549, valueOf2);
        encryptDic.put(valueOf2, "οほㄅ");
        deencryptDic.put((char) 12396, 'V');
        deencryptDic.put((char) 12557, 'V');
        deencryptDic.put((char) 12494, 'V');
        encryptDic.put('V', "ぬㄍノ");
        deencryptDic.put((char) 12513, 'W');
        deencryptDic.put((char) 12365, 'W');
        deencryptDic.put((char) 12554, 'W');
        encryptDic.put('W', "メきㄊ");
        deencryptDic.put((char) 916, 'X');
        deencryptDic.put((char) 12490, 'X');
        deencryptDic.put((char) 12574, 'X');
        encryptDic.put('X', "Δナㄞ");
        deencryptDic.put((char) 12465, 'Y');
        deencryptDic.put((char) 1066, 'Y');
        deencryptDic.put((char) 968, 'Y');
        encryptDic.put('Y', "ケЪψ");
        HashMap<Object, Object> hashMap3 = deencryptDic;
        Character valueOf3 = Character.valueOf(Matrix.MATRIX_TYPE_ZERO);
        hashMap3.put((char) 12457, valueOf3);
        deencryptDic.put((char) 949, valueOf3);
        deencryptDic.put((char) 1069, valueOf3);
        encryptDic.put(valueOf3, "ォεЭ");
        deencryptDic.put((char) 12402, '[');
        deencryptDic.put((char) 927, '[');
        deencryptDic.put((char) 12394, '[');
        encryptDic.put('[', "ひΟな");
        deencryptDic.put((char) 1053, ']');
    }

    public static void EncryptInit4() {
        deencryptDic.put((char) 12580, 'x');
        deencryptDic.put((char) 12511, 'x');
        encryptDic.put('x', "Νㄤミ");
        deencryptDic.put((char) 12388, 'y');
        deencryptDic.put((char) 1067, 'y');
        deencryptDic.put((char) 12430, 'y');
        encryptDic.put('y', "つЫゎ");
        deencryptDic.put((char) 12477, 'z');
        deencryptDic.put((char) 12353, 'z');
        deencryptDic.put((char) 1048, 'z');
        encryptDic.put('z', "ソぁИ");
        deencryptDic.put((char) 12392, '{');
        deencryptDic.put((char) 12564, '{');
        deencryptDic.put((char) 12563, '{');
        encryptDic.put('{', "とㄔㄓ");
        deencryptDic.put((char) 12501, '}');
        deencryptDic.put((char) 12531, '}');
        deencryptDic.put((char) 12418, '}');
        encryptDic.put('}', "フンも");
    }

    public static void EncryptInit7() {
        deencryptDic.put((char) 1056, 'l');
        encryptDic.put('l', "マぉР");
        deencryptDic.put((char) 65082, 'm');
        deencryptDic.put((char) 12550, 'm');
        deencryptDic.put((char) 12555, 'm');
        encryptDic.put('m', "︺ㄆㄋ");
        deencryptDic.put((char) 65088, 'n');
        deencryptDic.put((char) 12530, 'n');
        deencryptDic.put((char) 12398, 'n');
        encryptDic.put('n', "﹀ヲの");
        deencryptDic.put((char) 12408, 'o');
        deencryptDic.put((char) 12488, 'o');
        deencryptDic.put((char) 12473, 'o');
        encryptDic.put('o', "へトス");
        deencryptDic.put((char) 12405, 'p');
        deencryptDic.put((char) 12395, 'p');
        deencryptDic.put((char) 12479, 'p');
        encryptDic.put('p', "ふにタ");
        deencryptDic.put((char) 12556, 'q');
        deencryptDic.put((char) 12357, 'q');
        deencryptDic.put((char) 12558, 'q');
        encryptDic.put('q', "ㄌぅㄎ");
        deencryptDic.put((char) 65085, 'r');
        deencryptDic.put((char) 12373, 'r');
        deencryptDic.put((char) 12571, 'r');
        encryptDic.put('r', "︽さㄛ");
        deencryptDic.put((char) 12390, 's');
        deencryptDic.put((char) 12553, 's');
        deencryptDic.put((char) 12560, 's');
        encryptDic.put('s', "てㄉㄐ");
        deencryptDic.put((char) 12381, 't');
        deencryptDic.put((char) 65081, 't');
        deencryptDic.put((char) 12517, 't');
        encryptDic.put('t', "そ︹ュ");
        deencryptDic.put((char) 65080, 'u');
        deencryptDic.put((char) 12577, 'u');
        deencryptDic.put((char) 12359, 'u');
        encryptDic.put('u', "︸ㄡぇ");
        deencryptDic.put((char) 65089, 'v');
        deencryptDic.put((char) 12570, 'v');
        deencryptDic.put((char) 65086, 'v');
        encryptDic.put('v', "﹁ㄚ︾");
        deencryptDic.put((char) 12475, 'w');
        deencryptDic.put((char) 12532, 'w');
        deencryptDic.put((char) 12534, 'w');
        encryptDic.put('w', "セヴヶ");
        deencryptDic.put((char) 925, 'x');
    }

    public static void EncryptInit8() {
        deencryptDic.put((char) 12397, ']');
        deencryptDic.put((char) 12481, ']');
        encryptDic.put(']', "Нねチ");
        deencryptDic.put((char) 12528, '_');
        deencryptDic.put((char) 12583, '_');
        deencryptDic.put((char) 12579, '_');
        encryptDic.put('_', "ヰㄧㄣ");
        deencryptDic.put((char) 12451, 'a');
        deencryptDic.put((char) 12355, 'a');
        deencryptDic.put((char) 12385, 'a');
        encryptDic.put('a', "ィぃち");
        deencryptDic.put((char) 936, 'b');
        deencryptDic.put((char) 12455, 'b');
        deencryptDic.put((char) 1040, 'b');
        encryptDic.put('b', "ΨェА");
        deencryptDic.put((char) 12492, 'c');
        deencryptDic.put((char) 12375, 'c');
        deencryptDic.put((char) 920, 'c');
        encryptDic.put('c', "ヌしΘ");
        deencryptDic.put((char) 12526, 'd');
        deencryptDic.put((char) 12419, 'd');
        deencryptDic.put((char) 12387, 'd');
        encryptDic.put('d', "ヮゃっ");
        deencryptDic.put((char) 12369, 'e');
        deencryptDic.put((char) 12363, 'e');
        deencryptDic.put((char) 1068, 'e');
        encryptDic.put('e', "けかЬ");
        deencryptDic.put((char) 12568, 'f');
        deencryptDic.put((char) 12379, 'f');
        deencryptDic.put((char) 933, 'f');
        encryptDic.put('f', "ㄘせΥ");
        deencryptDic.put((char) 12507, 'g');
        deencryptDic.put((char) 12461, 'g');
        deencryptDic.put((char) 12433, 'g');
        encryptDic.put('g', "ホキゑ");
        deencryptDic.put((char) 12495, 'h');
        deencryptDic.put((char) 12371, 'h');
        deencryptDic.put((char) 65084, 'h');
        encryptDic.put('h', "ハこ︼");
        deencryptDic.put((char) 12551, 'i');
        deencryptDic.put((char) 935, 'i');
        deencryptDic.put((char) 12486, 'i');
        encryptDic.put('i', "ㄇΧテ");
        deencryptDic.put((char) 12572, 'j');
        deencryptDic.put((char) 12449, 'j');
        deencryptDic.put((char) 945, 'j');
        encryptDic.put('j', "ㄜァα");
        deencryptDic.put((char) 12463, 'k');
        deencryptDic.put((char) 65091, 'k');
        deencryptDic.put((char) 12504, 'k');
        encryptDic.put('k', "ク﹃ヘ");
        deencryptDic.put((char) 12510, 'l');
        deencryptDic.put((char) 12361, 'l');
    }

    public static void EncryptInit9() {
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Typography.quote);
        hashMap.put((char) 950, valueOf);
        deencryptDic.put((char) 1060, valueOf);
        deencryptDic.put((char) 12383, valueOf);
        encryptDic.put(valueOf, "ζФた");
        deencryptDic.put((char) 12434, ',');
        deencryptDic.put((char) 65083, ',');
        deencryptDic.put((char) 12367, ',');
        encryptDic.put(',', "を︻く");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(JwtParser.SEPARATOR_CHAR);
        hashMap2.put((char) 1041, valueOf2);
        deencryptDic.put((char) 1064, valueOf2);
        deencryptDic.put((char) 929, valueOf2);
        encryptDic.put(valueOf2, "БШΡ");
        deencryptDic.put((char) 953, '/');
        deencryptDic.put((char) 12582, '/');
        deencryptDic.put((char) 12421, '/');
        encryptDic.put('/', "ιㄦゅ");
        deencryptDic.put((char) 12416, '0');
        deencryptDic.put((char) 12459, '0');
        deencryptDic.put((char) 12533, '0');
        encryptDic.put('0', "むカヵ");
        deencryptDic.put((char) 1063, '1');
        deencryptDic.put((char) 1047, '1');
        deencryptDic.put((char) 1050, '1');
        encryptDic.put('1', "ЧЗК");
        deencryptDic.put((char) 12514, '2');
        deencryptDic.put((char) 1052, '2');
        deencryptDic.put((char) 8719, '2');
        encryptDic.put('2', "モМ∏");
        deencryptDic.put((char) 12573, '3');
        deencryptDic.put((char) 1043, '3');
        deencryptDic.put((char) 928, '3');
        encryptDic.put('3', "ㄝГΠ");
        deencryptDic.put((char) 1057, '4');
        deencryptDic.put((char) 1061, '4');
        deencryptDic.put((char) 956, '4');
        encryptDic.put('4', "СХμ");
        deencryptDic.put((char) 924, '5');
        deencryptDic.put((char) 65087, '5');
        deencryptDic.put((char) 1059, '5');
        encryptDic.put('5', "Μ︿У");
        deencryptDic.put((char) 913, '6');
        deencryptDic.put((char) 12453, '6');
        deencryptDic.put((char) 8721, '6');
        encryptDic.put('6', "Αゥ∑");
        deencryptDic.put((char) 932, '7');
        deencryptDic.put((char) 965, '7');
        deencryptDic.put((char) 12415, '7');
    }

    public static String DeCode(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = deencryptDic.containsKey(Character.valueOf(str.charAt(i))) ? str2 + deencryptDic.get(Character.valueOf(str.charAt(i))).toString() : str2 + str.charAt(i);
        }
        return str2;
    }
}
