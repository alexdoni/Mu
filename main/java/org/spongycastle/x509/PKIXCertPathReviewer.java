package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.x509.AccessDescription;
import org.spongycastle.asn1.x509.AuthorityInformationAccess;
import org.spongycastle.asn1.x509.AuthorityKeyIdentifier;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.asn1.x509.NameConstraints;
import org.spongycastle.asn1.x509.qualified.MonetaryValue;
import org.spongycastle.asn1.x509.qualified.QCStatement;
import org.spongycastle.i18n.ErrorBundle;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.jce.provider.AnnotatedException;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.PKIXNameConstraintValidator;
import org.spongycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.spongycastle.util.Integers;

/* loaded from: classes3.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.spongycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected List[] errors;
    private boolean initialized;

    /* renamed from: n */
    protected int f2883n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath == null) {
            throw new NullPointerException("certPath was null");
        }
        this.certPath = certPath;
        List<? extends Certificate> certificates = certPath.getCertificates();
        this.certs = certificates;
        this.f2883n = certificates.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        PKIXParameters pKIXParameters2 = (PKIXParameters) pKIXParameters.clone();
        this.pkixParams = pKIXParameters2;
        this.validDate = getValidDate(pKIXParameters2);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    public PKIXCertPathReviewer() {
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.f2883n;
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.f2883n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.f2883n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i = this.f2883n;
        this.notifications = new List[i + 1];
        this.errors = new List[i + 1];
        int i2 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i2 < listArr.length) {
                listArr[i2] = new ArrayList();
                this.errors[i2] = new ArrayList();
                i2++;
            } else {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i = 0; i < aSN1Sequence2.size(); i++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i2 = 0; i2 != excludedSubtrees.length; i2++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i2]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i = this.f2883n;
        int i2 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i--;
                i2++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < i) {
                i = intValue;
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i2)}));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:(2:86|87)|(3:(15:89|90|91|(11:93|94|(2:97|95)|98|99|(2:102|100)|103|104|105|106|107)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107)|106|107)|117|90|91|(0)|114|94|(1:95)|98|99|(1:100)|103|104|105) */
    /* JADX WARN: Can't wrap try/catch for region: R(17:(2:86|87)|(15:89|90|91|(11:93|94|(2:97|95)|98|99|(2:102|100)|103|104|105|106|107)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107)|117|90|91|(0)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107) */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0330, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0331, code lost:
    
        r18 = r4;
        r13 = r6;
        r19 = r7;
        r12 = r8;
        r11 = r9;
        r15 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02b6, code lost:
    
        addError(new org.spongycastle.i18n.ErrorBundle(org.spongycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), r6);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02fc A[LOOP:2: B:100:0x02f6->B:102:0x02fc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02b1 A[Catch: AnnotatedException -> 0x02b6, TRY_LEAVE, TryCatch #13 {AnnotatedException -> 0x02b6, blocks: (B:91:0x02a9, B:93:0x02b1), top: B:90:0x02a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02d3 A[LOOP:1: B:95:0x02cd->B:97:0x02d3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkSignatures() {
        /*
            Method dump skipped, instructions count: 1037
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkSignatures():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:226:0x013d A[Catch: CertPathReviewerException -> 0x0609, TRY_LEAVE, TryCatch #0 {CertPathReviewerException -> 0x0609, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d4, B:201:0x03e5, B:203:0x03ef, B:107:0x0431, B:210:0x03fb, B:211:0x0409, B:213:0x040a, B:214:0x0418, B:221:0x041a, B:222:0x0428, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0440, B:279:0x044d, B:281:0x044e, B:286:0x045f, B:288:0x0469, B:289:0x046e, B:291:0x0474, B:294:0x0482, B:308:0x049b, B:315:0x05ef, B:316:0x05fb, B:318:0x04a6, B:319:0x04b2, B:320:0x04b3, B:322:0x04b9, B:324:0x04c1, B:326:0x04c7, B:329:0x04d1, B:330:0x04d4, B:332:0x04da, B:334:0x04ea, B:335:0x04ee, B:337:0x04f4, B:339:0x04fc, B:342:0x04ff, B:344:0x0504, B:345:0x0508, B:347:0x050e, B:350:0x051e, B:352:0x0526, B:353:0x0529, B:355:0x052f, B:357:0x053b, B:359:0x053f, B:362:0x0542, B:364:0x0545, B:365:0x0551, B:367:0x0556, B:369:0x0560, B:370:0x0563, B:372:0x0569, B:374:0x0579, B:375:0x057d, B:377:0x0583, B:380:0x0593, B:385:0x0597, B:388:0x059a, B:390:0x059d, B:391:0x05a3, B:393:0x05a9, B:395:0x05bb, B:401:0x05c5, B:403:0x05cb, B:404:0x05ce, B:406:0x05d4, B:408:0x05e0, B:410:0x05e4, B:413:0x05e7, B:417:0x05fc, B:418:0x0608), top: B:14:0x006f, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0120 A[Catch: CertPathReviewerException -> 0x0609, TryCatch #0 {CertPathReviewerException -> 0x0609, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d4, B:201:0x03e5, B:203:0x03ef, B:107:0x0431, B:210:0x03fb, B:211:0x0409, B:213:0x040a, B:214:0x0418, B:221:0x041a, B:222:0x0428, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0440, B:279:0x044d, B:281:0x044e, B:286:0x045f, B:288:0x0469, B:289:0x046e, B:291:0x0474, B:294:0x0482, B:308:0x049b, B:315:0x05ef, B:316:0x05fb, B:318:0x04a6, B:319:0x04b2, B:320:0x04b3, B:322:0x04b9, B:324:0x04c1, B:326:0x04c7, B:329:0x04d1, B:330:0x04d4, B:332:0x04da, B:334:0x04ea, B:335:0x04ee, B:337:0x04f4, B:339:0x04fc, B:342:0x04ff, B:344:0x0504, B:345:0x0508, B:347:0x050e, B:350:0x051e, B:352:0x0526, B:353:0x0529, B:355:0x052f, B:357:0x053b, B:359:0x053f, B:362:0x0542, B:364:0x0545, B:365:0x0551, B:367:0x0556, B:369:0x0560, B:370:0x0563, B:372:0x0569, B:374:0x0579, B:375:0x057d, B:377:0x0583, B:380:0x0593, B:385:0x0597, B:388:0x059a, B:390:0x059d, B:391:0x05a3, B:393:0x05a9, B:395:0x05bb, B:401:0x05c5, B:403:0x05cb, B:404:0x05ce, B:406:0x05d4, B:408:0x05e0, B:410:0x05e4, B:413:0x05e7, B:417:0x05fc, B:418:0x0608), top: B:14:0x006f, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0216 A[Catch: CertPathReviewerException -> 0x0609, TryCatch #0 {CertPathReviewerException -> 0x0609, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d4, B:201:0x03e5, B:203:0x03ef, B:107:0x0431, B:210:0x03fb, B:211:0x0409, B:213:0x040a, B:214:0x0418, B:221:0x041a, B:222:0x0428, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0440, B:279:0x044d, B:281:0x044e, B:286:0x045f, B:288:0x0469, B:289:0x046e, B:291:0x0474, B:294:0x0482, B:308:0x049b, B:315:0x05ef, B:316:0x05fb, B:318:0x04a6, B:319:0x04b2, B:320:0x04b3, B:322:0x04b9, B:324:0x04c1, B:326:0x04c7, B:329:0x04d1, B:330:0x04d4, B:332:0x04da, B:334:0x04ea, B:335:0x04ee, B:337:0x04f4, B:339:0x04fc, B:342:0x04ff, B:344:0x0504, B:345:0x0508, B:347:0x050e, B:350:0x051e, B:352:0x0526, B:353:0x0529, B:355:0x052f, B:357:0x053b, B:359:0x053f, B:362:0x0542, B:364:0x0545, B:365:0x0551, B:367:0x0556, B:369:0x0560, B:370:0x0563, B:372:0x0569, B:374:0x0579, B:375:0x057d, B:377:0x0583, B:380:0x0593, B:385:0x0597, B:388:0x059a, B:390:0x059d, B:391:0x05a3, B:393:0x05a9, B:395:0x05bb, B:401:0x05c5, B:403:0x05cb, B:404:0x05ce, B:406:0x05d4, B:408:0x05e0, B:410:0x05e4, B:413:0x05e7, B:417:0x05fc, B:418:0x0608), top: B:14:0x006f, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x023f A[Catch: CertPathReviewerException -> 0x0609, TryCatch #0 {CertPathReviewerException -> 0x0609, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d4, B:201:0x03e5, B:203:0x03ef, B:107:0x0431, B:210:0x03fb, B:211:0x0409, B:213:0x040a, B:214:0x0418, B:221:0x041a, B:222:0x0428, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0440, B:279:0x044d, B:281:0x044e, B:286:0x045f, B:288:0x0469, B:289:0x046e, B:291:0x0474, B:294:0x0482, B:308:0x049b, B:315:0x05ef, B:316:0x05fb, B:318:0x04a6, B:319:0x04b2, B:320:0x04b3, B:322:0x04b9, B:324:0x04c1, B:326:0x04c7, B:329:0x04d1, B:330:0x04d4, B:332:0x04da, B:334:0x04ea, B:335:0x04ee, B:337:0x04f4, B:339:0x04fc, B:342:0x04ff, B:344:0x0504, B:345:0x0508, B:347:0x050e, B:350:0x051e, B:352:0x0526, B:353:0x0529, B:355:0x052f, B:357:0x053b, B:359:0x053f, B:362:0x0542, B:364:0x0545, B:365:0x0551, B:367:0x0556, B:369:0x0560, B:370:0x0563, B:372:0x0569, B:374:0x0579, B:375:0x057d, B:377:0x0583, B:380:0x0593, B:385:0x0597, B:388:0x059a, B:390:0x059d, B:391:0x05a3, B:393:0x05a9, B:395:0x05bb, B:401:0x05c5, B:403:0x05cb, B:404:0x05ce, B:406:0x05d4, B:408:0x05e0, B:410:0x05e4, B:413:0x05e7, B:417:0x05fc, B:418:0x0608), top: B:14:0x006f, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkPolicy() {
        /*
            Method dump skipped, instructions count: 1558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkPolicy():void");
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        Iterator<PKIXCertPathChecker> it = certPathCheckers.iterator();
        while (it.hasNext()) {
            try {
                try {
                    it.next().init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(KEY_USAGE);
                criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                String str = QC_STATEMENT;
                if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(str);
                }
                Iterator<PKIXCertPathChecker> it2 = certPathCheckers.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it3 = criticalExtensionOIDs.iterator();
                    while (it3.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it3.next())}), size);
                    }
                }
            }
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals(qCStatement.getStatementId())) {
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance"), i);
                } else if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals(qCStatement.getStatementId())) {
                    if (QCStatement.id_etsi_qcs_QcSSCD.equals(qCStatement.getStatementId())) {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD"), i);
                    } else if (QCStatement.id_etsi_qcs_LimiteValue.equals(qCStatement.getStatementId())) {
                        MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                        monetaryValue.getCurrency();
                        double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                        if (monetaryValue.getCurrency().isAlphabetic()) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), monetaryValue});
                        } else {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue});
                        }
                        addNotification(errorBundle, i);
                    } else {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                        z = true;
                    }
                }
            }
            return true ^ z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:176:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void checkCRLs(java.security.cert.PKIXParameters r22, java.security.cert.X509Certificate r23, java.util.Date r24, java.security.cert.X509Certificate r25, java.security.PublicKey r26, java.util.Vector r27, int r28) throws org.spongycastle.x509.CertPathReviewerException {
        /*
            Method dump skipped, instructions count: 1200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((DERIA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals(AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((DERIA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                if (keyIdentifier != null) {
                    x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }
}
