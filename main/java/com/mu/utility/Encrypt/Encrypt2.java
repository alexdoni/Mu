package com.mu.utility.Encrypt;

import io.jsonwebtoken.JwtParser;
import java.util.HashMap;
import kotlin.text.Typography;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class Encrypt2 {
    public static HashMap<Object, Object> deencryptDic;
    public static HashMap<Object, Object> encryptDic;

    public static void EncryptInit5() {
    }

    public static void Init() {
        encryptDic = new HashMap<>();
        deencryptDic = new HashMap<>();
        EncryptInit1();
        EncryptInit2();
        EncryptInit3();
        EncryptInit6();
        EncryptInit7();
    }

    public static void EncryptInit1() {
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Typography.quote);
        hashMap.put((char) 65090, valueOf);
        deencryptDic.put((char) 1065, valueOf);
        deencryptDic.put((char) 12484, valueOf);
        encryptDic.put(valueOf, "﹂Щツ");
        deencryptDic.put((char) 967, ',');
        deencryptDic.put((char) 935, ',');
        deencryptDic.put((char) 12451, ',');
        encryptDic.put(',', "χΧィ");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(JwtParser.SEPARATOR_CHAR);
        hashMap2.put((char) 934, valueOf2);
        deencryptDic.put((char) 65085, valueOf2);
        deencryptDic.put((char) 12465, valueOf2);
        encryptDic.put(valueOf2, "Φ︽ケ");
        deencryptDic.put((char) 65077, '/');
        deencryptDic.put((char) 1054, '/');
        deencryptDic.put((char) 12418, '/');
        encryptDic.put('/', "︵Оも");
        deencryptDic.put((char) 1052, '0');
        deencryptDic.put((char) 926, '0');
        deencryptDic.put((char) 12385, '0');
        encryptDic.put('0', "МΞち");
        deencryptDic.put((char) 12479, '1');
        deencryptDic.put((char) 1056, '1');
        deencryptDic.put((char) 12398, '1');
        encryptDic.put('1', "タРの");
        deencryptDic.put((char) 12430, '2');
        deencryptDic.put((char) 1053, '2');
        deencryptDic.put((char) 1060, '2');
        encryptDic.put('2', "ゎНФ");
        deencryptDic.put((char) 965, '3');
        deencryptDic.put((char) 12552, '3');
        deencryptDic.put((char) 12528, '3');
        encryptDic.put('3', "υㄈヰ");
        deencryptDic.put((char) 8721, '4');
        deencryptDic.put((char) 922, '4');
        deencryptDic.put((char) 1047, '4');
        encryptDic.put('4', "∑ΚЗ");
        deencryptDic.put((char) 969, '5');
        deencryptDic.put((char) 12455, '5');
        deencryptDic.put((char) 1041, '5');
        encryptDic.put('5', "ωェБ");
        deencryptDic.put((char) 12504, '6');
        deencryptDic.put((char) 12377, '6');
        deencryptDic.put((char) 1057, '6');
        encryptDic.put('6', "ヘすС");
        deencryptDic.put((char) 12580, '7');
        deencryptDic.put((char) 12379, '7');
        deencryptDic.put((char) 12396, '7');
        encryptDic.put('7', "ㄤせぬ");
        deencryptDic.put((char) 914, '8');
        deencryptDic.put((char) 1048, '8');
        deencryptDic.put((char) 953, '8');
        encryptDic.put('8', "ΒИι");
        deencryptDic.put((char) 1067, '9');
    }

    public static void EncryptInit2() {
        deencryptDic.put((char) 12577, '9');
        deencryptDic.put((char) 12515, '9');
        encryptDic.put('9', "Ыㄡャ");
        deencryptDic.put((char) 952, ':');
        deencryptDic.put((char) 12357, ':');
        deencryptDic.put((char) 921, ':');
        encryptDic.put(':', "θぅΙ");
        deencryptDic.put((char) 924, 'A');
        deencryptDic.put((char) 950, 'A');
        deencryptDic.put((char) 1069, 'A');
        encryptDic.put('A', "ΜζЭ");
        deencryptDic.put((char) 12534, 'B');
        deencryptDic.put((char) 954, 'B');
        deencryptDic.put((char) 12574, 'B');
        encryptDic.put('B', "ヶκㄞ");
        deencryptDic.put((char) 1059, 'C');
        deencryptDic.put((char) 12511, 'C');
        deencryptDic.put((char) 65082, 'C');
        encryptDic.put('C', "Уミ︺");
        deencryptDic.put((char) 1068, 'D');
        deencryptDic.put((char) 12512, 'D');
        deencryptDic.put((char) 920, 'D');
        encryptDic.put('D', "ЬムΘ");
        deencryptDic.put((char) 8743, 'E');
        deencryptDic.put((char) 968, 'E');
        deencryptDic.put((char) 1049, 'E');
        encryptDic.put('E', "∧ψЙ");
        deencryptDic.put((char) 1064, 'F');
        deencryptDic.put((char) 966, 'F');
        deencryptDic.put((char) 12416, 'F');
        encryptDic.put('F', "Шφむ");
        deencryptDic.put((char) 927, 'G');
        deencryptDic.put((char) 925, 'G');
    }

    public static void EncryptInit3() {
        deencryptDic.put((char) 1061, 'G');
        encryptDic.put('G', "ΟΝХ");
        deencryptDic.put((char) 12555, 'H');
        deencryptDic.put((char) 1066, 'H');
        deencryptDic.put((char) 12554, 'H');
        encryptDic.put('H', "ㄋЪㄊ");
        deencryptDic.put((char) 947, 'I');
        deencryptDic.put((char) 945, 'I');
        deencryptDic.put((char) 12486, 'I');
        encryptDic.put('I', "γαテ");
        deencryptDic.put((char) 12475, 'J');
        deencryptDic.put((char) 949, 'J');
        deencryptDic.put((char) 12361, 'J');
        encryptDic.put('J', "セεぉ");
        deencryptDic.put((char) 12402, 'K');
        deencryptDic.put((char) 12501, 'K');
        deencryptDic.put((char) 946, 'K');
        encryptDic.put('K', "ひフβ");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT);
        hashMap.put((char) 1058, valueOf);
        deencryptDic.put((char) 12449, valueOf);
        deencryptDic.put((char) 12532, valueOf);
        encryptDic.put(valueOf, "Тァヴ");
        deencryptDic.put((char) 12395, 'M');
        deencryptDic.put((char) 65091, 'M');
        deencryptDic.put((char) 1062, 'M');
        encryptDic.put('M', "に﹃Ц");
        deencryptDic.put((char) 961, 'N');
        deencryptDic.put((char) 12415, 'N');
        deencryptDic.put((char) 65081, 'N');
        encryptDic.put('N', "ρみ︹");
        deencryptDic.put((char) 12585, 'O');
        deencryptDic.put((char) 12557, 'O');
        deencryptDic.put((char) 12513, 'O');
        encryptDic.put('O', "ㄩㄍメ");
        deencryptDic.put((char) 12421, 'P');
        deencryptDic.put((char) 1063, 'P');
        deencryptDic.put((char) 12473, 'P');
        encryptDic.put('P', "ゅЧス");
        deencryptDic.put((char) 12571, 'Q');
    }

    public static void EncryptInit4() {
        deencryptDic.put((char) 12514, 'y');
        deencryptDic.put((char) 12492, 'y');
        deencryptDic.put((char) 12560, 'y');
        encryptDic.put('y', "モヌㄐ");
        deencryptDic.put((char) 12387, 'z');
        deencryptDic.put((char) 12363, 'z');
        deencryptDic.put((char) 12549, 'z');
        encryptDic.put('z', "っかㄅ");
        deencryptDic.put((char) 12369, '{');
        deencryptDic.put((char) 12566, '{');
        deencryptDic.put((char) 12507, '{');
        encryptDic.put('{', "けㄖホ");
        deencryptDic.put((char) 12529, '}');
        deencryptDic.put((char) 12563, '}');
        deencryptDic.put((char) 65092, '}');
        encryptDic.put('}', "ヱㄓ﹄");
    }

    public static void EncryptInit6() {
        deencryptDic.put((char) 12565, 'm');
        encryptDic.put('m', "めㄘㄕ");
        deencryptDic.put((char) 12570, 'n');
        deencryptDic.put((char) 12519, 'n');
        deencryptDic.put((char) 12531, 'n');
        encryptDic.put('n', "ㄚョン");
        deencryptDic.put((char) 12495, 'o');
        deencryptDic.put((char) 12399, 'o');
        deencryptDic.put((char) 1071, 'o');
        encryptDic.put('o', "ハはЯ");
        deencryptDic.put((char) 12471, 'p');
        deencryptDic.put((char) 65087, 'p');
        deencryptDic.put((char) 12435, 'p');
        encryptDic.put('p', "シ︿ん");
        deencryptDic.put((char) 12569, 'q');
        deencryptDic.put((char) 963, 'q');
        deencryptDic.put((char) 12405, 'q');
        encryptDic.put('q', "ㄙσふ");
        deencryptDic.put((char) 956, 'r');
        deencryptDic.put((char) 12561, 'r');
        deencryptDic.put((char) 12533, 'r');
        encryptDic.put('r', "μㄑヵ");
        deencryptDic.put((char) 12390, 's');
        deencryptDic.put((char) 12553, 's');
        deencryptDic.put((char) 12481, 's');
        encryptDic.put('s', "てㄉチ");
        deencryptDic.put((char) 12367, 't');
        deencryptDic.put((char) 1046, 't');
        deencryptDic.put((char) 12488, 't');
        encryptDic.put('t', "くЖト");
        deencryptDic.put((char) 957, 'u');
        deencryptDic.put((char) 12491, 'u');
        deencryptDic.put((char) 12576, 'u');
        encryptDic.put('u', "νニㄠ");
        deencryptDic.put((char) 12353, 'v');
        deencryptDic.put((char) 12434, 'v');
        deencryptDic.put((char) 1040, 'v');
        encryptDic.put('v', "ぁをА");
        deencryptDic.put((char) 12556, 'w');
        deencryptDic.put((char) 12397, 'w');
        deencryptDic.put((char) 12371, 'w');
        encryptDic.put('w', "ㄌねこ");
        deencryptDic.put((char) 12578, 'x');
        deencryptDic.put((char) 12419, 'x');
        deencryptDic.put((char) 12494, 'x');
        encryptDic.put('x', "ㄢゃノ");
    }

    public static void EncryptInit7() {
        deencryptDic.put((char) 12423, 'Q');
        deencryptDic.put((char) 65079, 'Q');
        encryptDic.put('Q', "ㄛょ︷");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        hashMap.put((char) 951, valueOf);
        deencryptDic.put((char) 12375, valueOf);
        deencryptDic.put((char) 12411, valueOf);
        encryptDic.put(valueOf, "ηしほ");
        deencryptDic.put((char) 12463, 'S');
        deencryptDic.put((char) 960, 'S');
        deencryptDic.put((char) 964, 'S');
        encryptDic.put('S', "クπτ");
        deencryptDic.put((char) 955, 'T');
        deencryptDic.put((char) 12365, 'T');
        deencryptDic.put((char) 12457, 'T');
        encryptDic.put('T', "λきォ");
        HashMap<Object, Object> hashMap2 = deencryptDic;
        Character valueOf2 = Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT);
        hashMap2.put((char) 12582, valueOf2);
        deencryptDic.put((char) 1044, valueOf2);
        deencryptDic.put((char) 933, valueOf2);
        encryptDic.put(valueOf2, "ㄦДΥ");
        deencryptDic.put((char) 937, 'V');
        deencryptDic.put((char) 958, 'V');
        deencryptDic.put((char) 12392, 'V');
        encryptDic.put('V', "Ωξと");
        deencryptDic.put((char) 65086, 'W');
        deencryptDic.put((char) 959, 'W');
        deencryptDic.put((char) 65080, 'W');
        encryptDic.put('W', "︾ο︸");
        deencryptDic.put((char) 12510, 'X');
        deencryptDic.put((char) 8719, 'X');
        deencryptDic.put((char) 1045, 'X');
        encryptDic.put('X', "マ∏Е");
        deencryptDic.put((char) 913, 'Y');
    }

    public static void EncryptInit8() {
        encryptDic.put('d', "﹁ΖЛ");
        deencryptDic.put((char) 12373, 'e');
        deencryptDic.put((char) 12469, 'e');
        deencryptDic.put((char) 12490, 'e');
        encryptDic.put('e', "さサナ");
        deencryptDic.put((char) 12559, 'f');
        deencryptDic.put((char) 12459, 'f');
        deencryptDic.put((char) 12517, 'f');
        encryptDic.put('f', "ㄏカュ");
        deencryptDic.put((char) 932, 'g');
        deencryptDic.put((char) 65083, 'g');
        deencryptDic.put((char) 12567, 'g');
        encryptDic.put('g', "Τ︻ㄗ");
        deencryptDic.put((char) 12584, 'h');
        deencryptDic.put((char) 948, 'h');
        deencryptDic.put((char) 65084, 'h');
        encryptDic.put('h', "ㄨδ︼");
        deencryptDic.put((char) 12562, 'i');
        deencryptDic.put((char) 936, 'i');
        deencryptDic.put((char) 12564, 'i');
        encryptDic.put('i', "ㄒΨㄔ");
        deencryptDic.put((char) 12558, 'j');
        deencryptDic.put((char) 12483, 'j');
        deencryptDic.put((char) 931, 'j');
        encryptDic.put('j', "ㄎッΣ");
        deencryptDic.put((char) 1043, 'k');
        deencryptDic.put((char) 1025, 'k');
        deencryptDic.put((char) 12579, 'k');
        encryptDic.put('k', "ГЁㄣ");
        deencryptDic.put((char) 65088, 'l');
        deencryptDic.put((char) 12581, 'l');
        deencryptDic.put((char) 12573, 'l');
        encryptDic.put('l', "﹀ㄥㄝ");
        deencryptDic.put((char) 12417, 'm');
        deencryptDic.put((char) 12568, 'm');
    }

    public static void EncryptInit9() {
        deencryptDic.put((char) 12383, 'Y');
        deencryptDic.put((char) 12453, 'Y');
        encryptDic.put('Y', "Αたゥ");
        HashMap<Object, Object> hashMap = deencryptDic;
        Character valueOf = Character.valueOf(Matrix.MATRIX_TYPE_ZERO);
        hashMap.put((char) 915, valueOf);
        deencryptDic.put((char) 12467, valueOf);
        deencryptDic.put((char) 12408, valueOf);
        encryptDic.put(valueOf, "Γコへ");
        deencryptDic.put((char) 919, '[');
        deencryptDic.put((char) 1042, '[');
        deencryptDic.put((char) 12414, '[');
        encryptDic.put('[', "ΗВま");
        deencryptDic.put((char) 12394, ']');
        deencryptDic.put((char) 12583, ']');
        deencryptDic.put((char) 12572, ']');
        encryptDic.put(']', "なㄧㄜ");
        deencryptDic.put((char) 65078, '_');
        deencryptDic.put((char) 917, '_');
        deencryptDic.put((char) 916, '_');
        encryptDic.put('_', "︶ΕΔ");
        deencryptDic.put((char) 12433, 'a');
        deencryptDic.put((char) 12498, 'a');
        deencryptDic.put((char) 12530, 'a');
        encryptDic.put('a', "ゑヒヲ");
        deencryptDic.put((char) 1050, 'b');
        deencryptDic.put((char) 12388, 'b');
        deencryptDic.put((char) 929, 'b');
        encryptDic.put('b', "КつΡ");
        deencryptDic.put((char) 12477, 'c');
        deencryptDic.put((char) 12355, 'c');
        deencryptDic.put((char) 923, 'c');
        encryptDic.put('c', "ソぃΛ");
        deencryptDic.put((char) 65089, 'd');
        deencryptDic.put((char) 918, 'd');
        deencryptDic.put((char) 1051, 'd');
    }

    public static String DeCode(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = deencryptDic.containsKey(Character.valueOf(str.charAt(i))) ? str2 + deencryptDic.get(Character.valueOf(str.charAt(i))).toString() : str2 + str.charAt(i);
        }
        return str2;
    }
}
