package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class JPAKEParticipant {
    public static final int STATE_INITIALIZED = 0;
    public static final int STATE_KEY_CALCULATED = 50;
    public static final int STATE_ROUND_1_CREATED = 10;
    public static final int STATE_ROUND_1_VALIDATED = 20;
    public static final int STATE_ROUND_2_CREATED = 30;
    public static final int STATE_ROUND_2_VALIDATED = 40;
    public static final int STATE_ROUND_3_CREATED = 60;
    public static final int STATE_ROUND_3_VALIDATED = 70;

    /* renamed from: b */
    private BigInteger f2087b;
    private final Digest digest;

    /* renamed from: g */
    private final BigInteger f2088g;
    private BigInteger gx1;
    private BigInteger gx2;
    private BigInteger gx3;
    private BigInteger gx4;

    /* renamed from: p */
    private final BigInteger f2089p;
    private final String participantId;
    private String partnerParticipantId;
    private char[] password;

    /* renamed from: q */
    private final BigInteger f2090q;
    private final SecureRandom random;
    private int state;

    /* renamed from: x1 */
    private BigInteger f2091x1;

    /* renamed from: x2 */
    private BigInteger f2092x2;

    public JPAKEParticipant(String str, char[] cArr) {
        this(str, cArr, JPAKEPrimeOrderGroups.NIST_3072);
    }

    public JPAKEParticipant(String str, char[] cArr, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup) {
        this(str, cArr, jPAKEPrimeOrderGroup, new SHA256Digest(), new SecureRandom());
    }

    public JPAKEParticipant(String str, char[] cArr, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup, Digest digest, SecureRandom secureRandom) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(cArr, "password");
        JPAKEUtil.validateNotNull(jPAKEPrimeOrderGroup, "p");
        JPAKEUtil.validateNotNull(digest, "digest");
        JPAKEUtil.validateNotNull(secureRandom, "random");
        if (cArr.length == 0) {
            throw new IllegalArgumentException("Password must not be empty.");
        }
        this.participantId = str;
        this.password = Arrays.copyOf(cArr, cArr.length);
        this.f2089p = jPAKEPrimeOrderGroup.getP();
        this.f2090q = jPAKEPrimeOrderGroup.getQ();
        this.f2088g = jPAKEPrimeOrderGroup.getG();
        this.digest = digest;
        this.random = secureRandom;
        this.state = 0;
    }

    public int getState() {
        return this.state;
    }

    public JPAKERound1Payload createRound1PayloadToSend() {
        if (this.state >= 10) {
            throw new IllegalStateException("Round1 payload already created for " + this.participantId);
        }
        this.f2091x1 = JPAKEUtil.generateX1(this.f2090q, this.random);
        this.f2092x2 = JPAKEUtil.generateX2(this.f2090q, this.random);
        this.gx1 = JPAKEUtil.calculateGx(this.f2089p, this.f2088g, this.f2091x1);
        this.gx2 = JPAKEUtil.calculateGx(this.f2089p, this.f2088g, this.f2092x2);
        BigInteger[] calculateZeroKnowledgeProof = JPAKEUtil.calculateZeroKnowledgeProof(this.f2089p, this.f2090q, this.f2088g, this.gx1, this.f2091x1, this.participantId, this.digest, this.random);
        BigInteger[] calculateZeroKnowledgeProof2 = JPAKEUtil.calculateZeroKnowledgeProof(this.f2089p, this.f2090q, this.f2088g, this.gx2, this.f2092x2, this.participantId, this.digest, this.random);
        this.state = 10;
        return new JPAKERound1Payload(this.participantId, this.gx1, this.gx2, calculateZeroKnowledgeProof, calculateZeroKnowledgeProof2);
    }

    public void validateRound1PayloadReceived(JPAKERound1Payload jPAKERound1Payload) throws CryptoException {
        if (this.state >= 20) {
            throw new IllegalStateException("Validation already attempted for round1 payload for" + this.participantId);
        }
        this.partnerParticipantId = jPAKERound1Payload.getParticipantId();
        this.gx3 = jPAKERound1Payload.getGx1();
        this.gx4 = jPAKERound1Payload.getGx2();
        BigInteger[] knowledgeProofForX1 = jPAKERound1Payload.getKnowledgeProofForX1();
        BigInteger[] knowledgeProofForX2 = jPAKERound1Payload.getKnowledgeProofForX2();
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound1Payload.getParticipantId());
        JPAKEUtil.validateGx4(this.gx4);
        JPAKEUtil.validateZeroKnowledgeProof(this.f2089p, this.f2090q, this.f2088g, this.gx3, knowledgeProofForX1, jPAKERound1Payload.getParticipantId(), this.digest);
        JPAKEUtil.validateZeroKnowledgeProof(this.f2089p, this.f2090q, this.f2088g, this.gx4, knowledgeProofForX2, jPAKERound1Payload.getParticipantId(), this.digest);
        this.state = 20;
    }

    public JPAKERound2Payload createRound2PayloadToSend() {
        int i = this.state;
        if (i >= 30) {
            throw new IllegalStateException("Round2 payload already created for " + this.participantId);
        }
        if (i < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to creating Round2 payload for " + this.participantId);
        }
        BigInteger calculateGA = JPAKEUtil.calculateGA(this.f2089p, this.gx1, this.gx3, this.gx4);
        BigInteger calculateX2s = JPAKEUtil.calculateX2s(this.f2090q, this.f2092x2, JPAKEUtil.calculateS(this.password));
        BigInteger calculateA = JPAKEUtil.calculateA(this.f2089p, this.f2090q, calculateGA, calculateX2s);
        BigInteger[] calculateZeroKnowledgeProof = JPAKEUtil.calculateZeroKnowledgeProof(this.f2089p, this.f2090q, calculateGA, calculateA, calculateX2s, this.participantId, this.digest, this.random);
        this.state = 30;
        return new JPAKERound2Payload(this.participantId, calculateA, calculateZeroKnowledgeProof);
    }

    public void validateRound2PayloadReceived(JPAKERound2Payload jPAKERound2Payload) throws CryptoException {
        int i = this.state;
        if (i >= 40) {
            throw new IllegalStateException("Validation already attempted for round2 payload for" + this.participantId);
        }
        if (i < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to validating Round2 payload for " + this.participantId);
        }
        BigInteger calculateGA = JPAKEUtil.calculateGA(this.f2089p, this.gx3, this.gx1, this.gx2);
        this.f2087b = jPAKERound2Payload.getA();
        BigInteger[] knowledgeProofForX2s = jPAKERound2Payload.getKnowledgeProofForX2s();
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound2Payload.getParticipantId());
        JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, jPAKERound2Payload.getParticipantId());
        JPAKEUtil.validateGa(calculateGA);
        JPAKEUtil.validateZeroKnowledgeProof(this.f2089p, this.f2090q, calculateGA, this.f2087b, knowledgeProofForX2s, jPAKERound2Payload.getParticipantId(), this.digest);
        this.state = 40;
    }

    public BigInteger calculateKeyingMaterial() {
        int i = this.state;
        if (i >= 50) {
            throw new IllegalStateException("Key already calculated for " + this.participantId);
        }
        if (i < 40) {
            throw new IllegalStateException("Round2 payload must be validated prior to creating key for " + this.participantId);
        }
        BigInteger calculateS = JPAKEUtil.calculateS(this.password);
        Arrays.fill(this.password, (char) 0);
        this.password = null;
        BigInteger calculateKeyingMaterial = JPAKEUtil.calculateKeyingMaterial(this.f2089p, this.f2090q, this.gx4, this.f2092x2, calculateS, this.f2087b);
        this.f2091x1 = null;
        this.f2092x2 = null;
        this.f2087b = null;
        this.state = 50;
        return calculateKeyingMaterial;
    }

    public JPAKERound3Payload createRound3PayloadToSend(BigInteger bigInteger) {
        int i = this.state;
        if (i >= 60) {
            throw new IllegalStateException("Round3 payload already created for " + this.participantId);
        }
        if (i < 50) {
            throw new IllegalStateException("Keying material must be calculated prior to creating Round3 payload for " + this.participantId);
        }
        BigInteger calculateMacTag = JPAKEUtil.calculateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, bigInteger, this.digest);
        this.state = 60;
        return new JPAKERound3Payload(this.participantId, calculateMacTag);
    }

    public void validateRound3PayloadReceived(JPAKERound3Payload jPAKERound3Payload, BigInteger bigInteger) throws CryptoException {
        int i = this.state;
        if (i >= 70) {
            throw new IllegalStateException("Validation already attempted for round3 payload for" + this.participantId);
        }
        if (i < 50) {
            throw new IllegalStateException("Keying material must be calculated validated prior to validating Round3 payload for " + this.participantId);
        }
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound3Payload.getParticipantId());
        JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, jPAKERound3Payload.getParticipantId());
        JPAKEUtil.validateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, bigInteger, this.digest, jPAKERound3Payload.getMacTag());
        this.gx1 = null;
        this.gx2 = null;
        this.gx3 = null;
        this.gx4 = null;
        this.state = 70;
    }
}
