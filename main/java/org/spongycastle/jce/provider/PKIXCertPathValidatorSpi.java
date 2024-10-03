package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.TBSCertificate;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.x509.ExtendedPKIXParameters;

/* loaded from: classes3.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.spongycastle.asn1.x509.AlgorithmIdentifier] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        List<? extends Certificate> list;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        PKIXNameConstraintValidator pKIXNameConstraintValidator;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi = this;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else if (certPathParameters instanceof PKIXExtendedParameters) {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (pKIXExtendedParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        int i = -1;
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
        try {
            TrustAnchor findTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
            if (findTrustAnchor == null) {
                list = certificates;
                try {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                } catch (AnnotatedException e) {
                    e = e;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - 1);
                }
            }
            checkCertificate(findTrustAnchor.getTrustedCert());
            PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(findTrustAnchor).build();
            int i2 = size + 1;
            ArrayList[] arrayListArr2 = new ArrayList[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                arrayListArr2[i3] = new ArrayList();
            }
            HashSet hashSet3 = new HashSet();
            hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
            PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
            arrayListArr2[0].add(pKIXPolicyNode);
            PKIXNameConstraintValidator pKIXNameConstraintValidator2 = new PKIXNameConstraintValidator();
            HashSet hashSet4 = new HashSet();
            int i4 = build.isExplicitPolicyRequired() ? 0 : i2;
            int i5 = build.isAnyPolicyInhibited() ? 0 : i2;
            if (build.isPolicyMappingInhibited()) {
                i2 = 0;
            }
            X509Certificate trustedCert = findTrustAnchor.getTrustedCert();
            try {
                if (trustedCert != null) {
                    ca = PrincipalUtils.getSubjectPrincipal(trustedCert);
                    cAPublicKey = trustedCert.getPublicKey();
                } else {
                    ca = PrincipalUtils.getCA(findTrustAnchor);
                    cAPublicKey = findTrustAnchor.getCAPublicKey();
                }
                try {
                    i = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                    i.getAlgorithm();
                    i.getParameters();
                    if (build.getTargetConstraints() != null && !build.getTargetConstraints().match((Certificate) certificates.get(0))) {
                        throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                    }
                    List certPathCheckers = build.getCertPathCheckers();
                    Iterator it = certPathCheckers.iterator();
                    while (it.hasNext()) {
                        ((PKIXCertPathChecker) it.next()).init(false);
                    }
                    int i6 = size;
                    X509Certificate x509Certificate = null;
                    int i7 = i2;
                    int i8 = i5;
                    PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                    int i9 = i4;
                    int size2 = certificates.size() - 1;
                    int i10 = i9;
                    while (size2 >= 0) {
                        int i11 = size - size2;
                        Set set = initialPolicies;
                        X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                        boolean z = size2 == certificates.size() + (-1);
                        try {
                            checkCertificate(x509Certificate2);
                            TrustAnchor trustAnchor = findTrustAnchor;
                            JcaJceHelper jcaJceHelper = pKIXCertPathValidatorSpi.helper;
                            int i12 = i8;
                            List<? extends Certificate> list2 = certificates;
                            int i13 = i10;
                            PKIXExtendedParameters pKIXExtendedParameters2 = build;
                            int i14 = size2;
                            PKIXExtendedParameters pKIXExtendedParameters3 = build;
                            int i15 = i7;
                            PKIXNameConstraintValidator pKIXNameConstraintValidator3 = pKIXNameConstraintValidator2;
                            ArrayList[] arrayListArr3 = arrayListArr2;
                            RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters2, size2, cAPublicKey, z, ca, trustedCert, jcaJceHelper);
                            RFC3280CertPathUtilities.processCertBC(certPath, i14, pKIXNameConstraintValidator3);
                            PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath, i14, RFC3280CertPathUtilities.processCertD(certPath, i14, hashSet4, pKIXPolicyNode2, arrayListArr3, i12));
                            RFC3280CertPathUtilities.processCertF(certPath, i14, processCertE, i13);
                            if (i11 != size) {
                                if (x509Certificate2 != null && x509Certificate2.getVersion() == 1) {
                                    if (i11 != 1 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i14);
                                    }
                                } else {
                                    RFC3280CertPathUtilities.prepareNextCertA(certPath, i14);
                                    arrayListArr = arrayListArr3;
                                    PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i14, arrayListArr, processCertE, i15);
                                    RFC3280CertPathUtilities.prepareNextCertG(certPath, i14, pKIXNameConstraintValidator3);
                                    int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i14, i13);
                                    int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i14, i15);
                                    int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i14, i12);
                                    i10 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i14, prepareNextCertH1);
                                    i15 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i14, prepareNextCertH2);
                                    int prepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i14, prepareNextCertH3);
                                    RFC3280CertPathUtilities.prepareNextCertK(certPath, i14);
                                    i6 = RFC3280CertPathUtilities.prepareNextCertM(certPath, i14, RFC3280CertPathUtilities.prepareNextCertL(certPath, i14, i6));
                                    RFC3280CertPathUtilities.prepareNextCertN(certPath, i14);
                                    Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                    if (criticalExtensionOIDs != null) {
                                        hashSet2 = new HashSet(criticalExtensionOIDs);
                                        hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                        hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                        hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                        hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                        hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                        hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    } else {
                                        hashSet2 = new HashSet();
                                    }
                                    RFC3280CertPathUtilities.prepareNextCertO(certPath, i14, hashSet2, certPathCheckers);
                                    X500Name subjectPrincipal = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                    try {
                                        pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                        pKIXCertPathValidatorSpi = this;
                                    } catch (CertPathValidatorException e2) {
                                        e = e2;
                                    }
                                    try {
                                        PublicKey nextWorkingKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i14, pKIXCertPathValidatorSpi.helper);
                                        AlgorithmIdentifier algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(nextWorkingKey);
                                        algorithmIdentifier.getAlgorithm();
                                        algorithmIdentifier.getParameters();
                                        pKIXPolicyNode2 = prepareCertB;
                                        i8 = prepareNextCertJ;
                                        ca = subjectPrincipal;
                                        cAPublicKey = nextWorkingKey;
                                        trustedCert = x509Certificate2;
                                        i7 = i15;
                                        size2 = i14 - 1;
                                        x509Certificate = x509Certificate2;
                                        initialPolicies = set;
                                        certificates = list2;
                                        build = pKIXExtendedParameters3;
                                        findTrustAnchor = trustAnchor;
                                        PKIXNameConstraintValidator pKIXNameConstraintValidator4 = pKIXNameConstraintValidator;
                                        arrayListArr2 = arrayListArr;
                                        pKIXNameConstraintValidator2 = pKIXNameConstraintValidator4;
                                    } catch (CertPathValidatorException e3) {
                                        e = e3;
                                        throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, i14);
                                    }
                                }
                            }
                            pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                            arrayListArr = arrayListArr3;
                            pKIXCertPathValidatorSpi = this;
                            pKIXPolicyNode2 = processCertE;
                            i8 = i12;
                            i6 = i6;
                            i10 = i13;
                            i7 = i15;
                            size2 = i14 - 1;
                            x509Certificate = x509Certificate2;
                            initialPolicies = set;
                            certificates = list2;
                            build = pKIXExtendedParameters3;
                            findTrustAnchor = trustAnchor;
                            PKIXNameConstraintValidator pKIXNameConstraintValidator42 = pKIXNameConstraintValidator;
                            arrayListArr2 = arrayListArr;
                            pKIXNameConstraintValidator2 = pKIXNameConstraintValidator42;
                        } catch (AnnotatedException e4) {
                            throw new CertPathValidatorException(e4.getMessage(), e4.getUnderlyingException(), certPath, size2);
                        }
                    }
                    PKIXExtendedParameters pKIXExtendedParameters4 = build;
                    ArrayList[] arrayListArr4 = arrayListArr2;
                    TrustAnchor trustAnchor2 = findTrustAnchor;
                    Set set2 = initialPolicies;
                    X509Certificate x509Certificate3 = x509Certificate;
                    int i16 = size2;
                    int i17 = i16 + 1;
                    int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i17, RFC3280CertPathUtilities.wrapupCertA(i10, x509Certificate3));
                    Set<String> criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                    if (criticalExtensionOIDs2 != null) {
                        hashSet = new HashSet(criticalExtensionOIDs2);
                        hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                        hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                        hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                        hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                        hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                        hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                        hashSet.remove(Extension.extendedKeyUsage.getId());
                    } else {
                        hashSet = new HashSet();
                    }
                    RFC3280CertPathUtilities.wrapupCertF(certPath, i17, certPathCheckers, hashSet);
                    PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters4, set2, i17, arrayListArr4, pKIXPolicyNode2, hashSet4);
                    if (wrapupCertB > 0 || wrapupCertG != null) {
                        return new PKIXCertPathValidatorResult(trustAnchor2, wrapupCertG, x509Certificate3.getPublicKey());
                    }
                    throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i16);
                } catch (CertPathValidatorException e5) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e5, certPath, -1);
                }
            } catch (IllegalArgumentException e6) {
                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e6, certPath, i);
            }
        } catch (AnnotatedException e7) {
            e = e7;
            list = certificates;
        }
    }

    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e) {
            throw new AnnotatedException(e.getMessage());
        } catch (CertificateEncodingException unused) {
            throw new AnnotatedException("unable to process TBSCertificate");
        }
    }
}
