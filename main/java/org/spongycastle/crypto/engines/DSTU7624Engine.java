package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.io.encoding.Base64;
import okio.Utf8;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class DSTU7624Engine implements BlockCipher {
    private static final int BITS_IN_BYTE = 8;
    private static final int BITS_IN_LONG = 64;
    private static final int BITS_IN_WORD = 64;
    private static final int ROUNDS_128 = 10;
    private static final int ROUNDS_256 = 14;
    private static final int ROUNDS_512 = 18;
    private boolean forEncryption;
    private long[] internalState;
    private byte[] internalStateBytes;
    private long[][] roundKeys;
    private int roundsAmount;
    private byte[] tempInternalStateBytes;
    private int wordsInBlock;
    private int wordsInKey;
    private long[] workingKey;
    private byte[][] mdsMatrix = {new byte[]{1, 1, 5, 1, 8, 6, 7, 4}, new byte[]{4, 1, 1, 5, 1, 8, 6, 7}, new byte[]{7, 4, 1, 1, 5, 1, 8, 6}, new byte[]{6, 7, 4, 1, 1, 5, 1, 8}, new byte[]{8, 6, 7, 4, 1, 1, 5, 1}, new byte[]{1, 8, 6, 7, 4, 1, 1, 5}, new byte[]{5, 1, 8, 6, 7, 4, 1, 1}, new byte[]{1, 5, 1, 8, 6, 7, 4, 1}};
    private byte[][] mdsInvMatrix = {new byte[]{-83, -107, 118, -88, 47, 73, -41, -54}, new byte[]{-54, -83, -107, 118, -88, 47, 73, -41}, new byte[]{-41, -54, -83, -107, 118, -88, 47, 73}, new byte[]{73, -41, -54, -83, -107, 118, -88, 47}, new byte[]{47, 73, -41, -54, -83, -107, 118, -88}, new byte[]{-88, 47, 73, -41, -54, -83, -107, 118}, new byte[]{118, -88, 47, 73, -41, -54, -83, -107}, new byte[]{-107, 118, -88, 47, 73, -41, -54, -83}};
    private byte[][] sboxesForEncryption = {new byte[]{-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, Ascii.ETB, -16, -40, 9, 109, -13, Ascii.f550GS, -53, -55, 77, 44, -81, 121, -32, -105, -3, 111, 75, 69, 57, 62, -35, -93, 79, -76, -74, -102, Ascii.f556SO, Ascii.f558US, -65, Ascii.NAK, -31, 73, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, Ascii.f547EM, -43, -83, 88, -92, -69, -95, -36, -14, -125, 55, 66, -28, 122, 50, -100, -52, -85, 74, -113, 110, 4, 39, 46, -25, -30, 90, -106, Ascii.SYN, 35, 43, -62, 101, 102, Ascii.f555SI, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, 52, 72, -4, -73, 106, -120, -91, 83, -122, -7, 91, -37, 56, 123, -61, Ascii.f554RS, 34, 51, 36, 40, 54, -57, -78, 59, -114, 119, -70, -11, Ascii.DC4, -97, 8, 85, -101, 76, -2, 96, 92, -38, Ascii.CAN, 70, -51, 125, 33, -80, Utf8.REPLACEMENT_BYTE, Ascii.ESC, -119, -1, -21, -124, 105, 58, -99, -41, -45, 112, 103, SignedBytes.MAX_POWER_OF_TWO, -75, -34, 93, 48, -111, -79, 120, 17, 1, -27, 0, 104, -104, -96, -59, 2, -90, 116, 45, 11, -94, 118, -77, -66, -50, -67, -82, -23, -118, 49, Ascii.f549FS, -20, -15, -103, -108, -86, -10, 38, 47, -17, -24, -116, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, -112, 32, Base64.padSymbol, -126, -9, -22, 10, Ascii.f546CR, 126, -8, 80, Ascii.SUB, -60, 7, 87, -72, 60, 98, -29, -56, -84, 82, 100, 16, -48, -39, 19, Ascii.f548FF, Ascii.DC2, 41, 81, -71, -49, -42, 115, -115, -127, 84, -64, -19, 78, 68, -89, 42, -123, 37, -26, -54, 124, -117, 86, Byte.MIN_VALUE}, new byte[]{-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, -42, -78, -46, -112, Ascii.ETB, -8, 66, Ascii.NAK, 86, -76, 101, Ascii.f549FS, -120, 67, -59, 92, 54, -70, -11, 87, 103, -115, 49, -10, 100, 88, -98, -12, 34, -86, 117, Ascii.f555SI, 2, -79, -33, 109, 115, 77, 124, 38, 46, -9, 8, 93, 68, 62, -97, Ascii.DC4, -56, -82, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, Ascii.SUB, 107, 105, -13, -67, 51, -85, -6, -47, -101, 104, 78, Ascii.SYN, -107, -111, -18, 76, 99, -114, 91, -52, 60, Ascii.f547EM, -95, -127, 73, 123, -39, 111, 55, 96, -54, -25, 43, 72, -3, -106, 69, -4, 65, Ascii.DC2, Ascii.f546CR, 121, -27, -119, -116, -29, 32, 48, -36, -73, 108, 74, -75, Utf8.REPLACEMENT_BYTE, -105, -44, 98, 45, 6, -92, -91, -125, 95, 42, -38, -55, 0, 126, -94, 85, -65, 17, -43, -100, -49, Ascii.f556SO, 10, Base64.padSymbol, 81, 125, -109, Ascii.ESC, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, -80, -104, Ascii.CAN, 50, 113, 75, -17, 59, 112, -96, -28, SignedBytes.MAX_POWER_OF_TWO, -1, -61, -87, -26, 120, -7, -117, 70, Byte.MIN_VALUE, Ascii.f554RS, 56, -31, -72, -88, -32, Ascii.f548FF, 35, 118, Ascii.f550GS, 37, 36, 5, -15, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, -123, -30, 82, -14, -126, 80, 122, 47, 116, 83, -77, 97, -81, 57, 53, -34, -51, Ascii.f558US, -103, -84, -83, 114, 44, -35, -48, -121, -66, 94, -90, -20, 4, -58, 3, 52, -5, -37, 89, -74, -62, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, -118, 39, -57, -64, 41, -41}, new byte[]{-109, -39, -102, -75, -104, 34, 69, -4, -70, 106, -33, 2, -97, -36, 81, 89, 74, Ascii.ETB, 43, -62, -108, -12, -69, -93, 98, -28, 113, -44, -51, 112, Ascii.SYN, -31, 73, 60, -64, -40, 92, -101, -83, -123, 83, -95, 122, -56, 45, -32, -47, 114, -90, 44, -60, -29, 118, 120, -73, -76, 9, 59, Ascii.f556SO, 65, 76, -34, -78, -112, 37, -91, -41, 3, 17, 0, -61, 46, -110, -17, 78, Ascii.DC2, -99, 125, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, -48, 123, Ascii.CAN, -105, -45, 54, -26, 72, 86, -127, -113, 119, -52, -100, -71, -30, -84, -72, 47, Ascii.NAK, -92, 124, -38, 56, Ascii.f554RS, 11, 5, -42, Ascii.DC4, 110, 108, 126, 102, -3, -79, -27, 96, -81, 94, 51, -121, -55, -16, 93, 109, Utf8.REPLACEMENT_BYTE, -120, -115, -57, -9, Ascii.f550GS, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, 80, Ascii.f555SI, 55, 36, 40, 48, -107, -46, 62, 91, SignedBytes.MAX_POWER_OF_TWO, -125, -77, 105, 87, Ascii.f558US, 7, Ascii.f549FS, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, -18, 49, -94, 115, -7, -54, 58, Ascii.SUB, -5, Ascii.f546CR, -63, -2, -6, -14, 111, -67, -106, -35, 67, 82, -74, 8, -13, -82, -66, Ascii.f547EM, -119, 50, 38, -80, -22, 75, 100, -124, -126, 107, -11, 121, -65, 1, 95, 117, 99, Ascii.ESC, 35, Base64.padSymbol, 104, 42, 101, -24, -111, -10, -1, 19, 88, -15, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, 70, 68, 66, 4, -96, -37, 57, -122, 84, -86, -116, 52, 33, -117, -8, Ascii.f548FF, 116, 103}, new byte[]{104, -115, -54, 77, 115, 75, 78, 42, -44, 82, 38, -77, 84, Ascii.f554RS, Ascii.f547EM, Ascii.f558US, 34, 3, 70, Base64.padSymbol, 45, 74, 83, -125, 19, -118, -73, -43, 37, 121, -11, -67, 88, 47, Ascii.f546CR, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, Ascii.SYN, 60, 102, 112, 93, -13, 69, SignedBytes.MAX_POWER_OF_TWO, -52, -24, -108, 86, 8, -50, Ascii.SUB, 58, -46, -31, -33, -75, 56, 110, Ascii.f556SO, -27, -12, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, Ascii.DC4, -82, -18, -56, 72, -45, 48, -95, -110, 65, -79, Ascii.CAN, -60, 44, 113, 114, 68, Ascii.NAK, -3, 55, -66, 95, -86, -101, -120, -40, -85, -119, -100, -6, 96, -22, PSSSigner.TRAILER_IMPLICIT, 98, Ascii.f548FF, 36, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, 91, 52, 126, 16, -15, 123, -113, 99, -96, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, -74, -41, 41, -62, -21, -64, -92, -117, -116, Ascii.f550GS, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, 108, -112, 0, -114, 111, 80, 1, -59, -38, 71, Utf8.REPLACEMENT_BYTE, -51, 105, -94, -30, 122, -89, -58, -109, Ascii.f555SI, 10, 6, -26, 43, -106, -93, Ascii.f549FS, -81, 106, Ascii.DC2, -124, 57, -25, -80, -126, -9, -2, -99, -121, 92, -127, 53, -34, -76, -91, -4, Byte.MIN_VALUE, -17, -53, -69, 107, 118, -70, 90, 125, 120, 11, -107, -29, -83, 116, -104, 59, 54, 100, 109, -36, -16, 89, -87, 76, Ascii.ETB, Byte.MAX_VALUE, -111, -72, -55, 87, Ascii.ESC, -32, 97}};
    private byte[][] sboxesForDecryption = {new byte[]{-92, -94, -87, -59, 78, -55, 3, -39, 126, Ascii.f555SI, -46, -83, -25, -45, 39, 91, -29, -95, -24, -26, 124, 42, 85, Ascii.f548FF, -122, 57, -41, -115, -72, Ascii.DC2, 111, 40, -51, -118, 112, 86, 114, -7, -65, 79, 115, -23, -9, 87, Ascii.SYN, -84, 80, -64, -99, -73, 71, 113, 96, -60, 116, 67, 108, Ascii.f558US, -109, 119, -36, -50, 32, -116, -103, 95, 68, 1, -11, Ascii.f554RS, -121, 94, 97, 44, 75, Ascii.f550GS, -127, Ascii.NAK, -12, 35, -42, -22, -31, 103, -15, Byte.MAX_VALUE, -2, -38, 60, 7, 83, 106, -124, -100, -53, 2, -125, 51, -35, 53, -30, 89, 90, -104, -91, -110, 100, 4, 6, 16, 77, Ascii.f549FS, -105, 8, 49, -18, -85, 5, -81, 121, -96, Ascii.CAN, 70, 109, -4, -119, -44, -57, -1, -16, -49, 66, -111, -8, 104, 10, 101, -114, -74, -3, -61, -17, 120, 76, -52, -98, 48, 46, PSSSigner.TRAILER_IMPLICIT, 11, 84, Ascii.SUB, -90, -69, 38, Byte.MIN_VALUE, 72, -108, 50, 125, -89, Utf8.REPLACEMENT_BYTE, -82, 34, Base64.padSymbol, 102, -86, -10, 0, 93, -67, 74, -32, 59, -76, Ascii.ETB, -117, -97, 118, -80, 36, -102, 37, 99, -37, -21, 122, 62, 92, -77, -79, 41, -14, -54, 88, 110, -40, -88, 47, 117, -33, Ascii.DC4, -5, 19, 73, -120, -78, -20, -28, 52, 45, -106, -58, 58, -19, -107, Ascii.f556SO, -27, -123, 107, SignedBytes.MAX_POWER_OF_TWO, 33, -101, 9, Ascii.f547EM, 43, 82, -34, 69, -93, -6, 81, -62, -75, -47, -112, -71, -13, 55, -63, Ascii.f546CR, -70, 65, 17, 56, 123, -66, -48, -43, 105, 54, -56, 98, Ascii.ESC, -126, -113}, new byte[]{-125, -14, 42, -21, -23, -65, 123, -100, 52, -106, -115, -104, -71, 105, -116, 41, Base64.padSymbol, -120, 104, 6, 57, 17, 76, Ascii.f556SO, -96, 86, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.NAK, PSSSigner.TRAILER_IMPLICIT, -77, -36, 111, -8, 38, -70, -66, -67, 49, -5, -61, -2, Byte.MIN_VALUE, 97, -31, 122, 50, -46, 112, 32, -95, 69, -20, -39, Ascii.SUB, 93, -76, -40, 9, -91, 85, -114, 55, 118, -87, 103, 16, Ascii.ETB, 54, 101, -79, -107, 98, 89, 116, -93, 80, 47, 75, -56, -48, -113, -51, -44, 60, -122, Ascii.DC2, Ascii.f550GS, 35, -17, -12, 83, Ascii.f547EM, 53, -26, Byte.MAX_VALUE, 94, -42, 121, 81, 34, Ascii.DC4, -9, Ascii.f554RS, 74, 66, -101, 65, 115, 45, -63, 92, -90, -94, -32, 46, -45, 40, -69, -55, -82, 106, -47, 90, 48, -112, -124, -7, -78, 88, -49, 126, -59, -53, -105, -28, Ascii.SYN, 108, -6, -80, 109, Ascii.f558US, 82, -103, Ascii.f546CR, 78, 3, -111, -62, 77, 100, 119, -97, -35, -60, 73, -118, -102, 36, 56, -89, 87, -123, -57, 124, 125, -25, -10, -73, -84, 39, 70, -34, -33, 59, -41, -98, 43, 11, -43, 19, 117, -16, 114, -74, -99, Ascii.ESC, 1, Utf8.REPLACEMENT_BYTE, 68, -27, -121, -3, 7, -15, -85, -108, Ascii.CAN, -22, -4, 58, -126, 95, 5, 84, -37, 0, -117, -29, 72, Ascii.f548FF, -54, 120, -119, 10, -1, 62, 91, -127, -18, 113, -30, -38, 44, -72, -75, -52, 110, -88, 107, -83, 96, -58, 8, 4, 2, -24, -11, 79, -92, -13, -64, -50, 67, 37, Ascii.f549FS, 33, 51, Ascii.f555SI, -81, 71, -19, 102, 99, -109, -86}, new byte[]{69, -44, 11, 67, -15, 114, -19, -92, -62, 56, -26, 113, -3, -74, 58, -107, 80, 68, 75, -30, 116, 107, Ascii.f554RS, 17, 90, -58, -76, -40, -91, -118, 112, -93, -88, -6, 5, -39, -105, SignedBytes.MAX_POWER_OF_TWO, -55, -112, -104, -113, -36, Ascii.DC2, 49, 44, 71, 106, -103, -82, -56, Byte.MAX_VALUE, -7, 79, 93, -106, 111, -12, -77, 57, 33, -38, -100, -123, -98, 59, -16, -65, -17, 6, -18, -27, 95, 32, 16, -52, 60, 84, 74, 82, -108, Ascii.f556SO, -64, 40, -10, 86, 96, -94, -29, Ascii.f555SI, -20, -99, 36, -125, 126, -43, 124, -21, Ascii.CAN, -41, -51, -35, 120, -1, -37, -95, 9, -48, 118, -124, 117, -69, Ascii.f550GS, Ascii.SUB, 47, -80, -2, -42, 52, 99, 53, -46, 42, 89, 109, 77, 119, -25, -114, 97, -49, -97, -50, 39, -11, Byte.MIN_VALUE, -122, -57, -90, -5, -8, -121, -85, 98, Utf8.REPLACEMENT_BYTE, -33, 72, 0, Ascii.DC4, -102, -67, 91, 4, -110, 2, 37, 101, 76, 83, Ascii.f548FF, -14, 41, -81, Ascii.ETB, 108, 65, 48, -23, -109, 85, -9, -84, 104, 38, -60, 125, -54, 122, 62, -96, 55, 3, -63, 54, 105, 102, 8, Ascii.SYN, -89, PSSSigner.TRAILER_IMPLICIT, -59, -45, 34, -73, 19, 70, 50, -24, 87, -120, 43, -127, -78, 78, 100, Ascii.f549FS, -86, -111, 88, 46, -101, 92, Ascii.ESC, 81, 115, 66, 35, 1, 110, -13, Ascii.f546CR, -66, Base64.padSymbol, 10, 45, Ascii.f558US, 103, 51, Ascii.f547EM, 123, 94, -22, -34, -117, -53, -87, -116, -115, -83, 73, -126, -28, -70, -61, Ascii.NAK, -47, -32, -119, -4, -79, -71, -75, 7, 121, -72, -31}, new byte[]{-78, -74, 35, 17, -89, -120, -59, -90, 57, -113, -60, -24, 115, 34, 67, -61, -126, 39, -51, Ascii.CAN, 81, 98, 45, -9, 92, Ascii.f556SO, 59, -3, -54, -101, Ascii.f546CR, Ascii.f555SI, 121, -116, 16, 76, 116, Ascii.f549FS, 10, -114, 124, -108, 7, -57, 94, Ascii.DC4, -95, 33, 87, 80, 78, -87, Byte.MIN_VALUE, -39, -17, 100, 65, -49, 60, -18, 46, 19, 41, -70, 52, 90, -82, -118, 97, 51, Ascii.DC2, -71, 85, -88, Ascii.NAK, 5, -10, 3, 6, 73, -75, 37, 9, Ascii.SYN, Ascii.f548FF, 42, 56, -4, 32, -12, -27, Byte.MAX_VALUE, -41, 49, 43, 102, 111, -1, 114, -122, -16, -93, 47, 120, 0, PSSSigner.TRAILER_IMPLICIT, -52, -30, -80, -15, 66, -76, 48, 95, 96, 4, -20, -91, -29, -117, -25, Ascii.f550GS, -65, -124, 123, -26, -127, -8, -34, -40, -46, Ascii.ETB, -50, 75, 71, -42, 105, 108, Ascii.f547EM, -103, -102, 1, -77, -123, -79, -7, 89, -62, 55, -23, -56, -96, -19, 79, -119, 104, 109, -43, 38, -111, -121, 88, -67, -55, -104, -36, 117, -64, 118, -11, 103, 107, 126, -21, 82, -53, -47, 91, -97, 11, -37, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.SUB, -6, -84, -28, -31, 113, Ascii.f558US, 101, -115, -105, -98, -107, -112, 93, -73, -63, -81, 84, -5, 2, -32, 53, -69, 58, 77, -83, 44, Base64.padSymbol, 86, 8, Ascii.ESC, 74, -109, 106, -85, -72, 122, -14, 125, -38, Utf8.REPLACEMENT_BYTE, -2, 62, -66, -22, -86, 68, -58, -48, 54, 72, 112, -106, 119, 36, 83, -33, -13, -125, 40, 50, 69, Ascii.f554RS, -92, -45, -94, 70, 110, -100, -35, 99, -44, -99}};

    private byte MultiplyGF(byte b, byte b2) {
        int i = b & 255;
        int i2 = b2 & 255;
        int i3 = (-(i2 & 1)) & i;
        for (int i4 = 1; i4 < 8; i4++) {
            i <<= 1;
            i2 >>>= 1;
            i3 ^= (-(i2 & 1)) & i;
        }
        int i5 = 65280 & i3;
        int i6 = ((i5 >>> 8) ^ ((((i5 >>> 4) ^ i5) ^ (i5 >>> 5)) ^ (i5 >>> 6))) ^ i3;
        int i7 = i6 & 3840;
        return (byte) (i6 ^ ((i7 >>> 8) ^ ((((i7 >>> 4) ^ i7) ^ (i7 >>> 5)) ^ (i7 >>> 6))));
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    public DSTU7624Engine(int i) throws IllegalArgumentException {
        if (i != 128 && i != 256 && i != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        int i2 = i / 64;
        this.wordsInBlock = i2;
        long[] jArr = new long[i2];
        this.internalState = jArr;
        this.internalStateBytes = new byte[(jArr.length * 64) / 8];
        this.tempInternalStateBytes = new byte[(jArr.length * 64) / 8];
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            int length = key.length * 8;
            int i = this.wordsInBlock * 64;
            if (length != 128 && length != 256 && length != 512) {
                throw new IllegalArgumentException("unsupported key length: only 128/256/512 are allowed");
            }
            if (i == 128 && length == 512) {
                throw new IllegalArgumentException("Unsupported key length");
            }
            if (i == 256 && length == 128) {
                throw new IllegalArgumentException("Unsupported key length");
            }
            if (i == 512 && length != 512) {
                throw new IllegalArgumentException("Unsupported key length");
            }
            if (length == 128) {
                this.roundsAmount = 10;
            } else if (length == 256) {
                this.roundsAmount = 14;
            } else if (length == 512) {
                this.roundsAmount = 18;
            }
            this.wordsInKey = length / 64;
            this.roundKeys = new long[this.roundsAmount + 1];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.roundKeys;
                if (i2 >= jArr.length) {
                    break;
                }
                jArr[i2] = new long[this.wordsInBlock];
                i2++;
            }
            int i3 = this.wordsInKey;
            long[] jArr2 = new long[i3];
            this.workingKey = jArr2;
            if (key.length != (i3 * 64) / 8) {
                throw new IllegalArgumentException("Invalid key parameter passed to DSTU7624Engine init");
            }
            Pack.littleEndianToLong(key, 0, jArr2);
            long[] jArr3 = new long[this.wordsInBlock];
            workingKeyExpandKT(this.workingKey, jArr3);
            workingKeyExpandEven(this.workingKey, jArr3);
            workingKeyExpandOdd();
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to DSTU7624Engine init");
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return (this.wordsInBlock * 64) / 8;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.workingKey == null) {
            throw new IllegalStateException("DSTU7624 engine not initialised");
        }
        if (getBlockSize() + i > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (getBlockSize() + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            Pack.littleEndianToLong(bArr, i, this.internalState);
            for (int i3 = 0; i3 < this.wordsInBlock; i3++) {
                long[] jArr = this.internalState;
                jArr[i3] = jArr[i3] + this.roundKeys[0][i3];
            }
            for (int i4 = 1; i4 < this.roundsAmount; i4++) {
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                for (int i5 = 0; i5 < this.wordsInBlock; i5++) {
                    long[] jArr2 = this.internalState;
                    jArr2[i5] = jArr2[i5] ^ this.roundKeys[i4][i5];
                }
            }
            SubBytes();
            ShiftRows();
            MixColumns(this.mdsMatrix);
            for (int i6 = 0; i6 < this.wordsInBlock; i6++) {
                long[] jArr3 = this.internalState;
                jArr3[i6] = jArr3[i6] + this.roundKeys[this.roundsAmount][i6];
            }
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        } else {
            int i7 = this.roundsAmount;
            Pack.littleEndianToLong(bArr, i, this.internalState);
            for (int i8 = 0; i8 < this.wordsInBlock; i8++) {
                long[] jArr4 = this.internalState;
                jArr4[i8] = jArr4[i8] - this.roundKeys[i7][i8];
            }
            for (int i9 = this.roundsAmount - 1; i9 > 0; i9--) {
                MixColumns(this.mdsInvMatrix);
                InvShiftRows();
                InvSubBytes();
                for (int i10 = 0; i10 < this.wordsInBlock; i10++) {
                    long[] jArr5 = this.internalState;
                    jArr5[i10] = jArr5[i10] ^ this.roundKeys[i9][i10];
                }
            }
            MixColumns(this.mdsInvMatrix);
            InvShiftRows();
            InvSubBytes();
            for (int i11 = 0; i11 < this.wordsInBlock; i11++) {
                long[] jArr6 = this.internalState;
                jArr6[i11] = jArr6[i11] - this.roundKeys[0][i11];
            }
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        }
        return getBlockSize();
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.internalState, 0L);
        Arrays.fill(this.internalStateBytes, (byte) 0);
        Arrays.fill(this.tempInternalStateBytes, (byte) 0);
    }

    private void workingKeyExpandKT(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        long[] jArr3 = new long[i];
        long[] jArr4 = new long[i];
        long[] jArr5 = new long[i];
        this.internalState = jArr5;
        long j = jArr5[0];
        int i2 = this.wordsInKey;
        jArr5[0] = j + i + i2 + 1;
        if (i == i2) {
            System.arraycopy(jArr, 0, jArr3, 0, i);
            System.arraycopy(jArr, 0, jArr4, 0, i);
        } else {
            System.arraycopy(jArr, 0, jArr3, 0, i);
            int i3 = this.wordsInBlock;
            System.arraycopy(jArr, i3, jArr4, 0, i3);
        }
        int i4 = 0;
        while (true) {
            long[] jArr6 = this.internalState;
            if (i4 >= jArr6.length) {
                break;
            }
            jArr6[i4] = jArr6[i4] + jArr3[i4];
            i4++;
        }
        SubBytes();
        ShiftRows();
        MixColumns(this.mdsMatrix);
        int i5 = 0;
        while (true) {
            long[] jArr7 = this.internalState;
            if (i5 >= jArr7.length) {
                break;
            }
            jArr7[i5] = jArr7[i5] ^ jArr4[i5];
            i5++;
        }
        SubBytes();
        ShiftRows();
        MixColumns(this.mdsMatrix);
        int i6 = 0;
        while (true) {
            long[] jArr8 = this.internalState;
            if (i6 < jArr8.length) {
                jArr8[i6] = jArr8[i6] + jArr3[i6];
                i6++;
            } else {
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                System.arraycopy(this.internalState, 0, jArr2, 0, this.wordsInBlock);
                return;
            }
        }
    }

    private void workingKeyExpandEven(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = this.wordsInKey;
        long[] jArr3 = new long[i5];
        int i6 = this.wordsInBlock;
        long[] jArr4 = new long[i6];
        long[] jArr5 = new long[i6];
        System.arraycopy(jArr, 0, jArr3, 0, i5);
        for (int i7 = 0; i7 < this.wordsInBlock; i7++) {
            jArr5[i7] = 281479271743489L;
        }
        int i8 = 0;
        while (true) {
            System.arraycopy(jArr2, 0, this.internalState, 0, this.wordsInBlock);
            int i9 = 0;
            while (true) {
                i = this.wordsInBlock;
                if (i9 >= i) {
                    break;
                }
                long[] jArr6 = this.internalState;
                jArr6[i9] = jArr6[i9] + jArr5[i9];
                i9++;
            }
            System.arraycopy(this.internalState, 0, jArr4, 0, i);
            System.arraycopy(jArr3, 0, this.internalState, 0, this.wordsInBlock);
            for (int i10 = 0; i10 < this.wordsInBlock; i10++) {
                long[] jArr7 = this.internalState;
                jArr7[i10] = jArr7[i10] + jArr4[i10];
            }
            SubBytes();
            ShiftRows();
            MixColumns(this.mdsMatrix);
            for (int i11 = 0; i11 < this.wordsInBlock; i11++) {
                long[] jArr8 = this.internalState;
                jArr8[i11] = jArr8[i11] ^ jArr4[i11];
            }
            SubBytes();
            ShiftRows();
            MixColumns(this.mdsMatrix);
            int i12 = 0;
            while (true) {
                i2 = this.wordsInBlock;
                if (i12 >= i2) {
                    break;
                }
                long[] jArr9 = this.internalState;
                jArr9[i12] = jArr9[i12] + jArr4[i12];
                i12++;
            }
            System.arraycopy(this.internalState, 0, this.roundKeys[i8], 0, i2);
            if (this.roundsAmount == i8) {
                return;
            }
            if (this.wordsInBlock != this.wordsInKey) {
                i8 += 2;
                ShiftLeft(jArr5);
                System.arraycopy(jArr2, 0, this.internalState, 0, this.wordsInBlock);
                int i13 = 0;
                while (true) {
                    i3 = this.wordsInBlock;
                    if (i13 >= i3) {
                        break;
                    }
                    long[] jArr10 = this.internalState;
                    jArr10[i13] = jArr10[i13] + jArr5[i13];
                    i13++;
                }
                System.arraycopy(this.internalState, 0, jArr4, 0, i3);
                int i14 = this.wordsInBlock;
                System.arraycopy(jArr3, i14, this.internalState, 0, i14);
                for (int i15 = 0; i15 < this.wordsInBlock; i15++) {
                    long[] jArr11 = this.internalState;
                    jArr11[i15] = jArr11[i15] + jArr4[i15];
                }
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                for (int i16 = 0; i16 < this.wordsInBlock; i16++) {
                    long[] jArr12 = this.internalState;
                    jArr12[i16] = jArr12[i16] ^ jArr4[i16];
                }
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                int i17 = 0;
                while (true) {
                    i4 = this.wordsInBlock;
                    if (i17 >= i4) {
                        break;
                    }
                    long[] jArr13 = this.internalState;
                    jArr13[i17] = jArr13[i17] + jArr4[i17];
                    i17++;
                }
                System.arraycopy(this.internalState, 0, this.roundKeys[i8], 0, i4);
                if (this.roundsAmount == i8) {
                    return;
                }
            }
            i8 += 2;
            ShiftLeft(jArr5);
            long j = jArr3[0];
            int i18 = i5 - 1;
            System.arraycopy(jArr3, 1, jArr3, 0, i18);
            jArr3[i18] = j;
        }
    }

    private void workingKeyExpandOdd() {
        for (int i = 1; i < this.roundsAmount; i += 2) {
            long[][] jArr = this.roundKeys;
            System.arraycopy(jArr[i - 1], 0, jArr[i], 0, this.wordsInBlock);
            RotateLeft(this.roundKeys[i]);
        }
    }

    private void SubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            byte[][] bArr = this.sboxesForEncryption;
            byte[] bArr2 = bArr[0];
            long j = jArr[i];
            byte[] bArr3 = bArr[1];
            byte[] bArr4 = bArr[2];
            byte[] bArr5 = bArr[3];
            jArr[i] = ((bArr5[(int) ((j & (-72057594037927936L)) >>> 56)] << 56) & (-72057594037927936L)) | (255 & bArr2[(int) (j & 255)]) | (65280 & (bArr3[(int) ((j & 65280) >>> 8)] << 8)) | (16711680 & (bArr4[(int) ((j & 16711680) >>> 16)] << 16)) | (4278190080L & (bArr5[(int) ((j & 4278190080L) >>> 24)] << 24)) | (1095216660480L & (bArr2[(int) ((j & 1095216660480L) >>> 32)] << 32)) | (280375465082880L & (bArr3[(int) ((j & 280375465082880L) >>> 40)] << 40)) | ((bArr4[(int) ((j & 71776119061217280L) >>> 48)] << 48) & 71776119061217280L);
        }
    }

    private void InvSubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            byte[][] bArr = this.sboxesForDecryption;
            byte[] bArr2 = bArr[0];
            long j = jArr[i];
            byte[] bArr3 = bArr[1];
            byte[] bArr4 = bArr[2];
            byte[] bArr5 = bArr[3];
            jArr[i] = ((bArr5[(int) ((j & (-72057594037927936L)) >>> 56)] << 56) & (-72057594037927936L)) | (255 & bArr2[(int) (j & 255)]) | (65280 & (bArr3[(int) ((j & 65280) >>> 8)] << 8)) | (16711680 & (bArr4[(int) ((j & 16711680) >>> 16)] << 16)) | (4278190080L & (bArr5[(int) ((j & 4278190080L) >>> 24)] << 24)) | (1095216660480L & (bArr2[(int) ((j & 1095216660480L) >>> 32)] << 32)) | (280375465082880L & (bArr3[(int) ((j & 280375465082880L) >>> 40)] << 40)) | ((bArr4[(int) ((j & 71776119061217280L) >>> 48)] << 48) & 71776119061217280L);
        }
    }

    private void ShiftRows() {
        Pack.longToLittleEndian(this.internalState, this.internalStateBytes, 0);
        int i = -1;
        for (int i2 = 0; i2 < 8; i2++) {
            if (i2 % (8 / this.wordsInBlock) == 0) {
                i++;
            }
            int i3 = 0;
            while (true) {
                int i4 = this.wordsInBlock;
                if (i3 < i4) {
                    this.tempInternalStateBytes[((((i3 + i) % i4) * 64) / 8) + i2] = this.internalStateBytes[((i3 * 64) / 8) + i2];
                    i3++;
                }
            }
        }
        Pack.littleEndianToLong(this.tempInternalStateBytes, 0, this.internalState);
    }

    private void InvShiftRows() {
        Pack.longToLittleEndian(this.internalState, this.internalStateBytes, 0);
        int i = -1;
        for (int i2 = 0; i2 < 8; i2++) {
            if (i2 % (8 / this.wordsInBlock) == 0) {
                i++;
            }
            int i3 = 0;
            while (true) {
                int i4 = this.wordsInBlock;
                if (i3 < i4) {
                    this.tempInternalStateBytes[((i3 * 64) / 8) + i2] = this.internalStateBytes[((((i3 + i) % i4) * 64) / 8) + i2];
                    i3++;
                }
            }
        }
        Pack.littleEndianToLong(this.tempInternalStateBytes, 0, this.internalState);
    }

    private void MixColumns(byte[][] bArr) {
        Pack.longToLittleEndian(this.internalState, this.internalStateBytes, 0);
        for (int i = 0; i < this.wordsInBlock; i++) {
            long j = 0;
            long j2 = -72057594037927936L;
            for (int i2 = 7; i2 >= 0; i2--) {
                byte b = 0;
                for (int i3 = 7; i3 >= 0; i3--) {
                    b = (byte) (b ^ MultiplyGF(this.internalStateBytes[((i * 64) / 8) + i3], bArr[i2][i3]));
                }
                j |= (b << ((i2 * 64) / 8)) & j2;
                j2 >>>= 8;
            }
            this.internalState[i] = j;
        }
    }

    private void ShiftLeft(long[] jArr) {
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = jArr[i] << 1;
        }
        for (int i2 = 0; i2 < jArr.length / 2; i2++) {
            long j = jArr[i2];
            jArr[i2] = jArr[(jArr.length - i2) - 1];
            jArr[(jArr.length - i2) - 1] = j;
        }
    }

    private void RotateLeft(long[] jArr) {
        int length = (jArr.length * 2) + 3;
        int length2 = jArr.length * 8;
        byte[] bArr = new byte[(jArr.length * 64) / 8];
        Pack.longToLittleEndian(jArr, bArr, 0);
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int i = length2 - length;
        System.arraycopy(bArr, length, bArr, 0, i);
        System.arraycopy(bArr2, 0, bArr, i, length);
        Pack.littleEndianToLong(bArr, 0, jArr);
    }
}
