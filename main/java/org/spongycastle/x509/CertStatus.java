package org.spongycastle.x509;

import java.util.Date;

/* loaded from: classes3.dex */
class CertStatus {
    public static final int UNDETERMINED = 12;
    public static final int UNREVOKED = 11;
    int certStatus = 11;
    Date revocationDate = null;

    CertStatus() {
    }

    public Date getRevocationDate() {
        return this.revocationDate;
    }

    public void setRevocationDate(Date date) {
        this.revocationDate = date;
    }

    public int getCertStatus() {
        return this.certStatus;
    }

    public void setCertStatus(int i) {
        this.certStatus = i;
    }
}
