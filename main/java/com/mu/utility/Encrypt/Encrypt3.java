package com.mu.utility.Encrypt;

import io.jsonwebtoken.JwtParser;
import java.util.HashMap;
import kotlin.text.Typography;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class Encrypt3 {
    public static HashMap<Object, Object> deencryptDic;
    public static HashMap<Object, Object> encryptDic;

    public static void EncryptInit3() {
    }

    public static void Init() {
        encryptDic = new HashMap<>();
        deencryptDic = new HashMap<>();
        EncryptInit3();
        EncryptInit4();
        EncryptInit5();
        EncryptInit6();
    }

    public static void EncryptInit1() {
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Typography.quote);
        hashMap.put((char) 955, valueOf);
        deencryptDic.put((char) 65083, valueOf);
        deencryptDic.put((char) 1052, valueOf);
        encryptDic.put(valueOf, "λ︻М");
        deencryptDic.put((char) 12583, ',');
        deencryptDic.put((char) 12579, ',');
        deencryptDic.put((char) 1064, ',');
        encryptDic.put(',', "ㄧㄣШ");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(JwtParser.SEPARATOR_CHAR);
        hashMap2.put((char) 12451, valueOf2);
        deencryptDic.put((char) 12418, valueOf2);
        deencryptDic.put((char) 921, valueOf2);
        encryptDic.put(valueOf2, "ィもΙ");
        deencryptDic.put((char) 12532, '/');
        deencryptDic.put((char) 969, '/');
        deencryptDic.put((char) 12494, '/');
        encryptDic.put('/', "ヴωノ");
        deencryptDic.put((char) 12397, '0');
        deencryptDic.put((char) 12514, '0');
        deencryptDic.put((char) 12558, '0');
        encryptDic.put('0', "ねモㄎ");
        deencryptDic.put((char) 924, '1');
        deencryptDic.put((char) 65085, '1');
        deencryptDic.put((char) 1043, '1');
        encryptDic.put('1', "Μ︽Г");
        deencryptDic.put((char) 958, '2');
        deencryptDic.put((char) 12449, '2');
        deencryptDic.put((char) 12565, '2');
        encryptDic.put('2', "ξァㄕ");
        deencryptDic.put((char) 12564, '3');
        deencryptDic.put((char) 12414, '3');
        deencryptDic.put((char) 950, '3');
        encryptDic.put('3', "ㄔまζ");
        deencryptDic.put((char) 929, '4');
        deencryptDic.put((char) 12469, '4');
        deencryptDic.put((char) 1044, '4');
        encryptDic.put('4', "ΡサД");
        deencryptDic.put((char) 12371, '5');
        deencryptDic.put((char) 919, '5');
        deencryptDic.put((char) 12559, '5');
        encryptDic.put('5', "こΗㄏ");
        deencryptDic.put((char) 12387, '6');
        deencryptDic.put((char) 12471, '6');
    }

    public static void EncryptInit2() {
        deencryptDic.put((char) 931, '6');
        encryptDic.put('6', "っシΣ");
        deencryptDic.put((char) 12572, '7');
        deencryptDic.put((char) 12560, '7');
        deencryptDic.put((char) 12570, '7');
        encryptDic.put('7', "ㄜㄐㄚ");
        deencryptDic.put((char) 12492, '8');
    }

    public static void EncryptInit4() {
        encryptDic.put('q', "コЙ︾");
        deencryptDic.put((char) 12455, 'r');
        deencryptDic.put((char) 8721, 'r');
        deencryptDic.put((char) 1058, 'r');
        encryptDic.put('r', "ェ∑Т");
        deencryptDic.put((char) 12483, 's');
        deencryptDic.put((char) 12435, 's');
        deencryptDic.put((char) 65082, 's');
        encryptDic.put('s', "ッん︺");
        deencryptDic.put((char) 12517, 't');
        deencryptDic.put((char) 12488, 't');
        deencryptDic.put((char) 945, 't');
        encryptDic.put('t', "ュトα");
        deencryptDic.put((char) 12390, 'u');
        deencryptDic.put((char) 1053, 'u');
        deencryptDic.put((char) 12556, 'u');
        encryptDic.put('u', "てНㄌ");
        deencryptDic.put((char) 1068, 'v');
        deencryptDic.put((char) 12395, 'v');
        deencryptDic.put((char) 65080, 'v');
        encryptDic.put('v', "Ьに︸");
        deencryptDic.put((char) 12563, 'w');
        deencryptDic.put((char) 918, 'w');
        deencryptDic.put((char) 12511, 'w');
        encryptDic.put('w', "ㄓΖミ");
        deencryptDic.put((char) 1071, 'x');
        deencryptDic.put((char) 12408, 'x');
        deencryptDic.put((char) 12491, 'x');
        encryptDic.put('x', "Яへニ");
        deencryptDic.put((char) 65092, 'y');
        deencryptDic.put((char) 12581, 'y');
        deencryptDic.put((char) 12421, 'y');
        encryptDic.put('y', "﹄ㄥゅ");
        deencryptDic.put((char) 933, 'z');
        deencryptDic.put((char) 12453, 'z');
        deencryptDic.put((char) 12504, 'z');
        encryptDic.put('z', "Υゥヘ");
        deencryptDic.put((char) 12365, '{');
        deencryptDic.put((char) 65090, '{');
        deencryptDic.put((char) 1040, '{');
        encryptDic.put('{', "き﹂А");
    }

    public static void EncryptInit5() {
        deencryptDic.put((char) 1045, 'T');
        deencryptDic.put((char) 12392, 'T');
        encryptDic.put('T', "ゎЕと");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT);
        hashMap.put((char) 1070, valueOf);
        deencryptDic.put((char) 949, valueOf);
        deencryptDic.put((char) 1025, valueOf);
        encryptDic.put(valueOf, "ЮεЁ");
        deencryptDic.put((char) 920, 'V');
        deencryptDic.put((char) 12552, 'V');
        deencryptDic.put((char) 1067, 'V');
        encryptDic.put('V', "ΘㄈЫ");
        deencryptDic.put((char) 12512, 'W');
        deencryptDic.put((char) 1051, 'W');
        deencryptDic.put((char) 12519, 'W');
        encryptDic.put('W', "ムЛョ");
        deencryptDic.put((char) 12411, 'X');
        deencryptDic.put((char) 12457, 'X');
        deencryptDic.put((char) 12394, 'X');
        encryptDic.put('X', "ほォな");
        deencryptDic.put((char) 968, 'Y');
        deencryptDic.put((char) 12574, 'Y');
        deencryptDic.put((char) 12526, 'Y');
        encryptDic.put('Y', "ψㄞヮ");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(Matrix.MATRIX_TYPE_ZERO);
        hashMap2.put((char) 1048, valueOf2);
        deencryptDic.put((char) 65078, valueOf2);
        deencryptDic.put((char) 12377, valueOf2);
        encryptDic.put(valueOf2, "И︶す");
        deencryptDic.put((char) 12361, '[');
        deencryptDic.put((char) 963, '[');
        deencryptDic.put((char) 1055, '[');
        encryptDic.put('[', "ぉσП");
        deencryptDic.put((char) 937, ']');
        deencryptDic.put((char) 12461, ']');
        deencryptDic.put((char) 1069, ']');
        encryptDic.put(']', "ΩキЭ");
        deencryptDic.put((char) 922, '_');
        deencryptDic.put((char) 65084, '_');
        deencryptDic.put((char) 12419, '_');
        encryptDic.put('_', "Κ︼ゃ");
        deencryptDic.put((char) 12367, 'a');
        deencryptDic.put((char) 12571, 'a');
        deencryptDic.put((char) 12567, 'a');
        encryptDic.put('a', "くㄛㄗ");
        deencryptDic.put((char) 1047, 'b');
        deencryptDic.put((char) 12493, 'b');
        deencryptDic.put((char) 12373, 'b');
        encryptDic.put('b', "Зネさ");
        deencryptDic.put((char) 1046, 'c');
        deencryptDic.put((char) 12475, 'c');
        deencryptDic.put((char) 65081, 'c');
        encryptDic.put('c', "Жセ︹");
        deencryptDic.put((char) 12467, 'q');
        deencryptDic.put((char) 1049, 'q');
        deencryptDic.put((char) 65086, 'q');
        deencryptDic.put((char) 12568, '}');
    }

    public static void EncryptInit6() {
        deencryptDic.put((char) 12530, 'K');
        encryptDic.put('K', "ㄒυヲ");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT);
        hashMap.put((char) 12465, valueOf);
        deencryptDic.put((char) 12573, valueOf);
        deencryptDic.put((char) 65088, valueOf);
        encryptDic.put(valueOf, "ケㄝ﹀");
        deencryptDic.put((char) 925, 'M');
        deencryptDic.put((char) 12402, 'M');
        deencryptDic.put((char) 12379, 'M');
        encryptDic.put('M', "Νひせ");
        deencryptDic.put((char) 12513, 'N');
        deencryptDic.put((char) 947, 'N');
        deencryptDic.put((char) 8719, 'N');
        encryptDic.put('N', "メγ∏");
        deencryptDic.put((char) 1060, 'O');
        deencryptDic.put((char) 964, 'O');
        deencryptDic.put((char) 952, 'O');
        encryptDic.put('O', "Фτθ");
        deencryptDic.put((char) 12575, 'P');
        deencryptDic.put((char) 12359, 'P');
        deencryptDic.put((char) 926, 'P');
        encryptDic.put('P', "ㄟぇΞ");
        deencryptDic.put((char) 12369, 'Q');
        deencryptDic.put((char) 1050, 'Q');
        deencryptDic.put((char) 65087, 'Q');
        encryptDic.put('Q', "けК︿");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        hashMap2.put((char) 12396, valueOf2);
        deencryptDic.put((char) 65089, valueOf2);
        deencryptDic.put((char) 1041, valueOf2);
        encryptDic.put(valueOf2, "ぬ﹁Б");
        deencryptDic.put((char) 1057, 'S');
        deencryptDic.put((char) 12585, 'S');
        deencryptDic.put((char) 936, 'S');
        encryptDic.put('S', "СㄩΨ");
        deencryptDic.put((char) 12430, 'T');
        deencryptDic.put((char) 12566, '}');
        deencryptDic.put((char) 12486, '}');
        encryptDic.put('}', "ㄘㄖテ");
    }

    public static void EncryptInit7() {
        deencryptDic.put((char) 1054, '8');
        deencryptDic.put((char) 12433, '8');
        encryptDic.put('8', "ヌОゑ");
        deencryptDic.put((char) 12550, '9');
        deencryptDic.put((char) 934, '9');
        deencryptDic.put((char) 12388, '9');
        encryptDic.put('9', "ㄆΦつ");
        deencryptDic.put((char) 946, ':');
        deencryptDic.put((char) 1061, ':');
        deencryptDic.put((char) 12459, ':');
        encryptDic.put(':', "βХカ");
        deencryptDic.put((char) 1066, 'A');
        deencryptDic.put((char) 12533, 'A');
        deencryptDic.put((char) 12405, 'A');
        encryptDic.put('A', "Ъヵふ");
        deencryptDic.put((char) 12531, 'B');
        deencryptDic.put((char) 12490, 'B');
        deencryptDic.put((char) 12416, 'B');
        encryptDic.put('B', "ンナむ");
        deencryptDic.put((char) 12561, 'C');
        deencryptDic.put((char) 12381, 'C');
        deencryptDic.put((char) 967, 'C');
        encryptDic.put('C', "ㄑそχ");
        deencryptDic.put((char) 12534, 'D');
        deencryptDic.put((char) 951, 'D');
        deencryptDic.put((char) 948, 'D');
        encryptDic.put('D', "ヶηδ");
        deencryptDic.put((char) 913, 'E');
        deencryptDic.put((char) 1063, 'E');
        deencryptDic.put((char) 65079, 'E');
        encryptDic.put('E', "ΑЧ︷");
        deencryptDic.put((char) 959, 'F');
        deencryptDic.put((char) 12528, 'F');
        deencryptDic.put((char) 12507, 'F');
        encryptDic.put('F', "οヰホ");
        deencryptDic.put((char) 12576, 'G');
        deencryptDic.put((char) 1059, 'G');
        deencryptDic.put((char) 1042, 'G');
        encryptDic.put('G', "ㄠУВ");
        deencryptDic.put((char) 12555, 'H');
        deencryptDic.put((char) 960, 'H');
        deencryptDic.put((char) 12580, 'H');
        encryptDic.put('H', "ㄋπㄤ");
        deencryptDic.put((char) 8743, 'I');
        deencryptDic.put((char) 954, 'I');
        deencryptDic.put((char) 12501, 'I');
        encryptDic.put('I', "∧κフ");
        deencryptDic.put((char) 12569, 'J');
        deencryptDic.put((char) 12510, 'J');
        deencryptDic.put((char) 957, 'J');
        encryptDic.put('J', "ㄙマν");
        deencryptDic.put((char) 12562, 'K');
        deencryptDic.put((char) 965, 'K');
    }

    public static void EncryptInit8() {
        deencryptDic.put((char) 12434, 'g');
        deencryptDic.put((char) 12417, 'g');
        encryptDic.put('g', "ツをめ");
        deencryptDic.put((char) 961, 'h');
        deencryptDic.put((char) 12363, 'h');
        deencryptDic.put((char) 12549, 'h');
        encryptDic.put('h', "ρかㄅ");
        deencryptDic.put((char) 935, 'i');
        deencryptDic.put((char) 953, 'i');
        deencryptDic.put((char) 12385, 'i');
        encryptDic.put('i', "Χιち");
        deencryptDic.put((char) 12399, 'j');
        deencryptDic.put((char) 12481, 'j');
        deencryptDic.put((char) 12584, 'j');
        encryptDic.put('j', "はチㄨ");
        deencryptDic.put((char) 12477, 'k');
        deencryptDic.put((char) 927, 'k');
        deencryptDic.put((char) 12554, 'k');
        encryptDic.put('k', "ソΟㄊ");
        deencryptDic.put((char) 966, 'l');
        deencryptDic.put((char) 12498, 'l');
    }

    public static void EncryptInit9() {
        deencryptDic.put((char) 932, 'd');
        deencryptDic.put((char) 914, 'd');
        deencryptDic.put((char) 1065, 'd');
        encryptDic.put('d', "ΤΒЩ");
        deencryptDic.put((char) 12463, 'e');
        deencryptDic.put((char) 12551, 'e');
        deencryptDic.put((char) 12515, 'e');
        encryptDic.put('e', "クㄇャ");
        deencryptDic.put((char) 12357, 'f');
        deencryptDic.put((char) 1056, 'f');
        deencryptDic.put((char) 12415, 'f');
        encryptDic.put('f', "ぅРみ");
        deencryptDic.put((char) 12484, 'g');
        deencryptDic.put((char) 928, 'l');
        encryptDic.put('l', "φヒΠ");
        deencryptDic.put((char) 956, 'm');
        deencryptDic.put((char) 923, 'm');
        deencryptDic.put((char) 12557, 'm');
        encryptDic.put('m', "μΛㄍ");
        deencryptDic.put((char) 12375, 'n');
        deencryptDic.put((char) 12423, 'n');
        deencryptDic.put((char) 12578, 'n');
        encryptDic.put('n', "しょㄢ");
        deencryptDic.put((char) 915, 'o');
        deencryptDic.put((char) 916, 'o');
        deencryptDic.put((char) 917, 'o');
        encryptDic.put('o', "ΓΔΕ");
        deencryptDic.put((char) 12479, 'p');
        deencryptDic.put((char) 12473, 'p');
        deencryptDic.put((char) 12577, 'p');
        encryptDic.put('p', "タスㄡ");
    }

    public static String DeCode(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = deencryptDic.containsKey(Character.valueOf(str.charAt(i))) ? str2 + deencryptDic.get(Character.valueOf(str.charAt(i))).toString() : str2 + str.charAt(i);
        }
        return str2;
    }
}
