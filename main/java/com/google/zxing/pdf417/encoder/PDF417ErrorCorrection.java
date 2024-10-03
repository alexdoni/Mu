package com.google.zxing.pdf417.encoder;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.muglobal.p011ld.BuildConfig;
import com.xsdk.ab_firstbase.net.okhttp3.CallCode;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.Primes;

/* loaded from: classes2.dex */
final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = {new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{237, 308, 436, 284, 646, 653, 428, 379}, new int[]{274, 562, 232, 755, 599, 524, 801, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 295, 116, 442, 428, 295, 42, CipherSuite.TLS_PSK_WITH_NULL_SHA256, 65}, new int[]{361, 575, 922, 525, CipherSuite.TLS_PSK_WITH_NULL_SHA256, 586, 640, 321, 536, 742, 677, 742, 687, 284, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 517, 273, 494, 263, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, 593, 800, 571, 320, 803, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, 231, 390, 685, 330, 63, CallCode.HTTP_GONE}, new int[]{539, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 6, 93, 862, 771, 453, 106, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 287, 107, 505, 733, 877, 381, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, 723, 476, 462, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, 430, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 858, 822, 543, 376, FrameMetricsAggregator.EVERY_DURATION, CallCode.HTTP_BAD_REQUEST, 672, 762, 283, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 440, 35, 519, 31, 460, 594, 225, 535, 517, 352, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, 651, CallCode.HTTP_CREATED, 488, 502, 648, 733, 717, 83, CallCode.HTTP_NOT_FOUND, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, CallCode.HTTP_BAD_REQUEST, 925, 749, CallCode.HTTP_UNSUPPORTED_TYPE, 822, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA, 447, 631, 292, 908, 490, TypedValues.TransitionType.TYPE_AUTO_TRANSITION, 516, 258, 457, 907, 594, 723, 674, 292, 272, 96, 684, 432, 686, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, 860, 569, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 219, BuildConfig.VERSION_CODE, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 236, 287, 192, 775, 278, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, 40, 379, 712, 463, 646, 776, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 491, 297, 763, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 732, 95, 270, 447, 90, TypedValues.PositionType.TYPE_PERCENT_Y, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 754, 336, 89, 614, 87, 432, 670, 616, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 374, 242, 726, 600, 269, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, Primes.SMALL_FACTOR_LIMIT, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, CallCode.HTTP_NO_CONTENT, 82, 586, 708, 250, TypedValues.Custom.TYPE_DIMENSION, 786, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, 720, 858, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 311, 913, 275, 190, 375, 850, 438, 733, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 280, CallCode.HTTP_CREATED, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, CallCode.HTTP_NO_CONTENT, 796, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 540, 913, 801, TypedValues.TransitionType.TYPE_DURATION, 799, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, 439, 418, 592, 668, 353, 859, 370, 694, 325, 240, 216, 257, 284, 549, 209, 884, 315, 70, 329, 793, 490, 274, 877, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 749, 812, 684, 461, 334, 376, 849, 521, 307, 291, 803, 712, 19, 358, 399, 908, 103, FrameMetricsAggregator.EVERY_DURATION, 51, 8, 517, 225, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, 538, TypedValues.Custom.TYPE_REFERENCE, 90, 2, 290, 743, 199, 655, TypedValues.Custom.TYPE_STRING, 329, 49, 802, 580, 355, 588, 188, 462, 10, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, 628, 320, 479, 130, 739, 71, 263, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 374, 601, 192, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, 673, 687, 234, 722, 384, CipherSuite.TLS_PSK_WITH_NULL_SHA384, 752, TypedValues.MotionType.TYPE_PATHMOTION_ARC, 640, 455, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 689, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, 503, 118, 49, 795, 32, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 500, 238, 836, 394, 280, 566, 319, 9, 647, 550, 73, 914, 342, 126, 32, 681, 331, 792, 620, 60, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 441, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, 791, 893, 754, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 383, 228, 749, 760, 213, 54, 297, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, 54, 834, 299, 922, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 910, 532, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 829, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 20, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, 29, 872, 449, 83, 402, 41, 656, 505, 579, 481, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, CallCode.HTTP_NOT_FOUND, 251, 688, 95, 497, CallCode.CALLRESULT_SERVER_ERROR, 642, 543, 307, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 924, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, CallCode.HTTP_CONFLICT, 574, 118, 498, 285, 380, 350, 492, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 265, 920, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, 914, 299, 229, 643, 294, 871, 306, 88, 87, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 352, 781, 846, 75, 327, 520, 435, 543, CallCode.HTTP_NOT_AUTHORITATIVE, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, CallCode.HTTP_CLIENT_TIMEOUT, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, 290, CallCode.HTTP_NO_CONTENT, 681, CallCode.HTTP_PROXY_AUTH, 855, 85, 99, 62, 482, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, 20, 297, 451, 593, 913, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, InputDeviceCompat.SOURCE_DPAD, 192, 516, 258, 240, 518, 794, 395, 768, 848, 51, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 384, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256, 190, 826, 328, 596, 786, 303, 570, 381, CallCode.HTTP_UNSUPPORTED_TYPE, 641, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 237, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 429, 531, 207, 676, 710, 89, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256, 304, 402, 40, 708, 575, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 864, 229, 65, 861, 841, 512, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 94, 8, 494, 114, 521, 2, 499, 851, 543, CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, TypedValues.Custom.TYPE_COLOR, 452, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, 342, 244, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, TypedValues.CycleType.TYPE_EASING, 245, 288, 594, 394, FrameMetricsAggregator.EVERY_DURATION, 327, 589, 777, 699, 688, 43, CallCode.HTTP_CLIENT_TIMEOUT, 842, 383, 721, 521, 560, 644, 714, 559, 62, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 873, 663, 713, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 672, 729, 624, 59, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 417, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, 209, 563, 564, 343, 693, 109, TypedValues.MotionType.TYPE_DRAW_PATH, 563, 365, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, 772, 677, 310, 248, 353, 708, CallCode.HTTP_GONE, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, TypedValues.CycleType.TYPE_WAVE_OFFSET, 833, 77, 597, 346, 269, 757, 632, 695, 751, 331, 247, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 45, 787, 680, 18, 66, CallCode.HTTP_PROXY_AUTH, 369, 54, 492, 228, 613, 830, 922, 437, 519, 644, TypedValues.Custom.TYPE_DIMENSION, 789, TypedValues.CycleType.TYPE_EASING, 305, 441, 207, CallCode.HTTP_MULT_CHOICE, 892, 827, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA, 537, 381, 662, InputDeviceCompat.SOURCE_DPAD, 56, 252, FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, 242, 797, 838, 837, 720, 224, 307, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, TypedValues.CycleType.TYPE_WAVE_PHASE, 216, 548, 249, 321, 881, 699, 535, 673, 782, 210, 815, TypedValues.Custom.TYPE_DIMENSION, 303, 843, 922, 281, 73, 469, 791, 660, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 498, 308, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 907, 817, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, 62, 16, TypedValues.CycleType.TYPE_WAVE_PHASE, 535, 336, 286, 437, 375, 273, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 296, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, TypedValues.CycleType.TYPE_WAVE_OFFSET, 242, 749, 321, 54, 669, TypedValues.AttributesType.TYPE_PATH_ROTATE, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, TypedValues.AttributesType.TYPE_PATH_ROTATE, 486, 721, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 46, 656, 447, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 616, 464, 190, 531, 297, 321, 762, 752, 533, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, 14, 381, 433, 717, 45, 111, 20, 596, 284, 736, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, 646, CallCode.HTTP_LENGTH_REQUIRED, 877, 669, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA, 919, 45, 780, CallCode.HTTP_PROXY_AUTH, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, 332, 899, CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384, 726, 600, 325, 498, 655, 357, 752, 768, 223, 849, 647, 63, 310, 863, 251, 366, 304, 282, 738, 675, CallCode.HTTP_GONE, 389, 244, 31, 121, 303, 263}};

    private PDF417ErrorCorrection() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getErrorCorrectionCodewordCount(int i) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
        }
        return 1 << (i + 1);
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int i) throws WriterException {
        if (i <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }
        if (i <= 40) {
            return 2;
        }
        if (i <= 160) {
            return 3;
        }
        if (i <= 320) {
            return 4;
        }
        if (i <= 863) {
            return 5;
        }
        throw new WriterException("No recommendation possible");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String generateErrorCorrection(CharSequence charSequence, int i) {
        int errorCorrectionCodewordCount = getErrorCorrectionCodewordCount(i);
        char[] cArr = new char[errorCorrectionCodewordCount];
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = errorCorrectionCodewordCount - 1;
            int charAt = (charSequence.charAt(i2) + cArr[i3]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i3 > 0) {
                cArr[i3] = (char) ((cArr[i3 - 1] + (929 - ((EC_COEFFICIENTS[i][i3] * charAt) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
                i3--;
            }
            cArr[0] = (char) ((929 - ((charAt * EC_COEFFICIENTS[i][0]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(errorCorrectionCodewordCount);
        for (int i4 = errorCorrectionCodewordCount - 1; i4 >= 0; i4--) {
            char c = cArr[i4];
            if (c != 0) {
                cArr[i4] = (char) (929 - c);
            }
            sb.append(cArr[i4]);
        }
        return sb.toString();
    }
}
