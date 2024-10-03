package com.linecorp.linesdk;

import android.os.Parcel;
import android.os.Parcelable;
import com.linecorp.linesdk.utils.ParcelUtils;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class LineIdToken implements Parcelable {
    public static final Parcelable.Creator<LineIdToken> CREATOR = new Parcelable.Creator<LineIdToken>() { // from class: com.linecorp.linesdk.LineIdToken.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineIdToken createFromParcel(Parcel parcel) {
            return new LineIdToken(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineIdToken[] newArray(int i) {
            return new LineIdToken[i];
        }
    };
    private final Address address;
    private final List<String> amr;
    private final String audience;
    private final Date authTime;
    private final String birthdate;
    private final String email;
    private final Date expiresAt;
    private final String familyName;
    private final String familyNamePronunciation;
    private final String gender;
    private final String givenName;
    private final String givenNamePronunciation;
    private final Date issuedAt;
    private final String issuer;
    private final String middleName;
    private final String name;
    private final String nonce;
    private final String phoneNumber;
    private final String picture;
    private final String rawString;
    private final String subject;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LineIdToken(Builder builder) {
        this.rawString = builder.rawString;
        this.issuer = builder.issuer;
        this.subject = builder.subject;
        this.audience = builder.audience;
        this.expiresAt = builder.expiresAt;
        this.issuedAt = builder.issuedAt;
        this.authTime = builder.authTime;
        this.nonce = builder.nonce;
        this.amr = builder.amr;
        this.name = builder.name;
        this.picture = builder.picture;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.gender = builder.gender;
        this.birthdate = builder.birthdate;
        this.address = builder.address;
        this.givenName = builder.givenName;
        this.givenNamePronunciation = builder.givenNamePronunciation;
        this.middleName = builder.middleName;
        this.familyName = builder.familyName;
        this.familyNamePronunciation = builder.familyNamePronunciation;
    }

    private LineIdToken(Parcel parcel) {
        this.rawString = parcel.readString();
        this.issuer = parcel.readString();
        this.subject = parcel.readString();
        this.audience = parcel.readString();
        this.expiresAt = ParcelUtils.readDate(parcel);
        this.issuedAt = ParcelUtils.readDate(parcel);
        this.authTime = ParcelUtils.readDate(parcel);
        this.nonce = parcel.readString();
        this.amr = parcel.createStringArrayList();
        this.name = parcel.readString();
        this.picture = parcel.readString();
        this.phoneNumber = parcel.readString();
        this.email = parcel.readString();
        this.gender = parcel.readString();
        this.birthdate = parcel.readString();
        this.address = (Address) parcel.readParcelable(Address.class.getClassLoader());
        this.givenName = parcel.readString();
        this.givenNamePronunciation = parcel.readString();
        this.middleName = parcel.readString();
        this.familyName = parcel.readString();
        this.familyNamePronunciation = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.rawString);
        parcel.writeString(this.issuer);
        parcel.writeString(this.subject);
        parcel.writeString(this.audience);
        ParcelUtils.writeDate(parcel, this.expiresAt);
        ParcelUtils.writeDate(parcel, this.issuedAt);
        ParcelUtils.writeDate(parcel, this.authTime);
        parcel.writeString(this.nonce);
        parcel.writeStringList(this.amr);
        parcel.writeString(this.name);
        parcel.writeString(this.picture);
        parcel.writeString(this.phoneNumber);
        parcel.writeString(this.email);
        parcel.writeString(this.gender);
        parcel.writeString(this.birthdate);
        parcel.writeParcelable(this.address, i);
        parcel.writeString(this.givenName);
        parcel.writeString(this.givenNamePronunciation);
        parcel.writeString(this.middleName);
        parcel.writeString(this.familyName);
        parcel.writeString(this.familyNamePronunciation);
    }

    public String getRawString() {
        return this.rawString;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getAudience() {
        return this.audience;
    }

    public Date getExpiresAt() {
        return this.expiresAt;
    }

    public Date getIssuedAt() {
        return this.issuedAt;
    }

    public Date getAuthTime() {
        return this.authTime;
    }

    public String getNonce() {
        return this.nonce;
    }

    public List<String> getAmr() {
        return this.amr;
    }

    public String getName() {
        return this.name;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGender() {
        return this.gender;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public String getGivenNamePronunciation() {
        return this.givenNamePronunciation;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getFamilyNamePronunciation() {
        return this.familyNamePronunciation;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineIdToken lineIdToken = (LineIdToken) obj;
        if (!this.rawString.equals(lineIdToken.rawString) || !this.issuer.equals(lineIdToken.issuer) || !this.subject.equals(lineIdToken.subject) || !this.audience.equals(lineIdToken.audience) || !this.expiresAt.equals(lineIdToken.expiresAt) || !this.issuedAt.equals(lineIdToken.issuedAt)) {
            return false;
        }
        Date date = this.authTime;
        if (date == null ? lineIdToken.authTime != null : !date.equals(lineIdToken.authTime)) {
            return false;
        }
        String str = this.nonce;
        if (str == null ? lineIdToken.nonce != null : !str.equals(lineIdToken.nonce)) {
            return false;
        }
        List<String> list = this.amr;
        if (list == null ? lineIdToken.amr != null : !list.equals(lineIdToken.amr)) {
            return false;
        }
        String str2 = this.name;
        if (str2 == null ? lineIdToken.name != null : !str2.equals(lineIdToken.name)) {
            return false;
        }
        String str3 = this.picture;
        if (str3 == null ? lineIdToken.picture != null : !str3.equals(lineIdToken.picture)) {
            return false;
        }
        String str4 = this.phoneNumber;
        if (str4 == null ? lineIdToken.phoneNumber != null : !str4.equals(lineIdToken.phoneNumber)) {
            return false;
        }
        String str5 = this.email;
        if (str5 == null ? lineIdToken.email != null : !str5.equals(lineIdToken.email)) {
            return false;
        }
        String str6 = this.gender;
        if (str6 == null ? lineIdToken.gender != null : !str6.equals(lineIdToken.gender)) {
            return false;
        }
        String str7 = this.birthdate;
        if (str7 == null ? lineIdToken.birthdate != null : !str7.equals(lineIdToken.birthdate)) {
            return false;
        }
        Address address = this.address;
        if (address == null ? lineIdToken.address != null : !address.equals(lineIdToken.address)) {
            return false;
        }
        String str8 = this.givenName;
        if (str8 == null ? lineIdToken.givenName != null : !str8.equals(lineIdToken.givenName)) {
            return false;
        }
        String str9 = this.givenNamePronunciation;
        if (str9 == null ? lineIdToken.givenNamePronunciation != null : !str9.equals(lineIdToken.givenNamePronunciation)) {
            return false;
        }
        String str10 = this.middleName;
        if (str10 == null ? lineIdToken.middleName != null : !str10.equals(lineIdToken.middleName)) {
            return false;
        }
        String str11 = this.familyName;
        if (str11 == null ? lineIdToken.familyName != null : !str11.equals(lineIdToken.familyName)) {
            return false;
        }
        String str12 = this.familyNamePronunciation;
        String str13 = lineIdToken.familyNamePronunciation;
        return str12 != null ? str12.equals(str13) : str13 == null;
    }

    public int hashCode() {
        int hashCode = ((((((((((this.rawString.hashCode() * 31) + this.issuer.hashCode()) * 31) + this.subject.hashCode()) * 31) + this.audience.hashCode()) * 31) + this.expiresAt.hashCode()) * 31) + this.issuedAt.hashCode()) * 31;
        Date date = this.authTime;
        int hashCode2 = (hashCode + (date != null ? date.hashCode() : 0)) * 31;
        String str = this.nonce;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        List<String> list = this.amr;
        int hashCode4 = (hashCode3 + (list != null ? list.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.picture;
        int hashCode6 = (hashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.phoneNumber;
        int hashCode7 = (hashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.email;
        int hashCode8 = (hashCode7 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.gender;
        int hashCode9 = (hashCode8 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.birthdate;
        int hashCode10 = (hashCode9 + (str7 != null ? str7.hashCode() : 0)) * 31;
        Address address = this.address;
        int hashCode11 = (hashCode10 + (address != null ? address.hashCode() : 0)) * 31;
        String str8 = this.givenName;
        int hashCode12 = (hashCode11 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.givenNamePronunciation;
        int hashCode13 = (hashCode12 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.middleName;
        int hashCode14 = (hashCode13 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.familyName;
        int hashCode15 = (hashCode14 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.familyNamePronunciation;
        return hashCode15 + (str12 != null ? str12.hashCode() : 0);
    }

    public String toString() {
        return "LineIdToken{rawString='" + this.rawString + "', issuer='" + this.issuer + "', subject='" + this.subject + "', audience='" + this.audience + "', expiresAt=" + this.expiresAt + ", issuedAt=" + this.issuedAt + ", authTime=" + this.authTime + ", nonce='" + this.nonce + "', amr=" + this.amr + ", name='" + this.name + "', picture='" + this.picture + "', phoneNumber='" + this.phoneNumber + "', email='" + this.email + "', gender='" + this.gender + "', birthdate='" + this.birthdate + "', address=" + this.address + ", givenName='" + this.givenName + "', givenNamePronunciation='" + this.givenNamePronunciation + "', middleName='" + this.middleName + "', familyName='" + this.familyName + "', familyNamePronunciation='" + this.familyNamePronunciation + "'}";
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Address address;
        private List<String> amr;
        private String audience;
        private Date authTime;
        private String birthdate;
        private String email;
        private Date expiresAt;
        private String familyName;
        private String familyNamePronunciation;
        private String gender;
        private String givenName;
        private String givenNamePronunciation;
        private Date issuedAt;
        private String issuer;
        private String middleName;
        private String name;
        private String nonce;
        private String phoneNumber;
        private String picture;
        private String rawString;
        private String subject;

        public Builder rawString(String str) {
            this.rawString = str;
            return this;
        }

        public Builder issuer(String str) {
            this.issuer = str;
            return this;
        }

        public Builder subject(String str) {
            this.subject = str;
            return this;
        }

        public Builder audience(String str) {
            this.audience = str;
            return this;
        }

        public Builder expiresAt(Date date) {
            this.expiresAt = date;
            return this;
        }

        public Builder issuedAt(Date date) {
            this.issuedAt = date;
            return this;
        }

        public Builder authTime(Date date) {
            this.authTime = date;
            return this;
        }

        public Builder nonce(String str) {
            this.nonce = str;
            return this;
        }

        public Builder amr(List<String> list) {
            this.amr = list;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder picture(String str) {
            this.picture = str;
            return this;
        }

        public Builder phoneNumber(String str) {
            this.phoneNumber = str;
            return this;
        }

        public Builder email(String str) {
            this.email = str;
            return this;
        }

        public Builder gender(String str) {
            this.gender = str;
            return this;
        }

        public Builder birthdate(String str) {
            this.birthdate = str;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder givenName(String str) {
            this.givenName = str;
            return this;
        }

        public Builder givenNamePronunciation(String str) {
            this.givenNamePronunciation = str;
            return this;
        }

        public Builder middleName(String str) {
            this.middleName = str;
            return this;
        }

        public Builder familyName(String str) {
            this.familyName = str;
            return this;
        }

        public Builder familyNamePronunciation(String str) {
            this.familyNamePronunciation = str;
            return this;
        }

        public LineIdToken build() {
            return new LineIdToken(this);
        }
    }

    /* loaded from: classes2.dex */
    public static class Address implements Parcelable {
        public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() { // from class: com.linecorp.linesdk.LineIdToken.Address.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Address createFromParcel(Parcel parcel) {
                return new Address(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Address[] newArray(int i) {
                return new Address[i];
            }
        };
        private final String country;
        private final String locality;
        private final String postalCode;
        private final String region;
        private final String streetAddress;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private Address(Builder builder) {
            this.streetAddress = builder.streetAddress;
            this.locality = builder.locality;
            this.region = builder.region;
            this.postalCode = builder.postalCode;
            this.country = builder.country;
        }

        private Address(Parcel parcel) {
            this.streetAddress = parcel.readString();
            this.locality = parcel.readString();
            this.region = parcel.readString();
            this.postalCode = parcel.readString();
            this.country = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.streetAddress);
            parcel.writeString(this.locality);
            parcel.writeString(this.region);
            parcel.writeString(this.postalCode);
            parcel.writeString(this.country);
        }

        public String getStreetAddress() {
            return this.streetAddress;
        }

        public String getLocality() {
            return this.locality;
        }

        public String getRegion() {
            return this.region;
        }

        public String getPostalCode() {
            return this.postalCode;
        }

        public String getCountry() {
            return this.country;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Address address = (Address) obj;
            String str = this.streetAddress;
            if (str == null ? address.streetAddress != null : !str.equals(address.streetAddress)) {
                return false;
            }
            String str2 = this.locality;
            if (str2 == null ? address.locality != null : !str2.equals(address.locality)) {
                return false;
            }
            String str3 = this.region;
            if (str3 == null ? address.region != null : !str3.equals(address.region)) {
                return false;
            }
            String str4 = this.postalCode;
            if (str4 == null ? address.postalCode != null : !str4.equals(address.postalCode)) {
                return false;
            }
            String str5 = this.country;
            String str6 = address.country;
            return str5 != null ? str5.equals(str6) : str6 == null;
        }

        public int hashCode() {
            String str = this.streetAddress;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.locality;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.region;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.postalCode;
            int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.country;
            return hashCode4 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "Address{streetAddress='" + this.streetAddress + "', locality='" + this.locality + "', region='" + this.region + "', postalCode='" + this.postalCode + "', country='" + this.country + "'}";
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private String country;
            private String locality;
            private String postalCode;
            private String region;
            private String streetAddress;

            public Builder streetAddress(String str) {
                this.streetAddress = str;
                return this;
            }

            public Builder locality(String str) {
                this.locality = str;
                return this;
            }

            public Builder region(String str) {
                this.region = str;
                return this;
            }

            public Builder postalCode(String str) {
                this.postalCode = str;
                return this;
            }

            public Builder country(String str) {
                this.country = str;
                return this;
            }

            public Address build() {
                return new Address(this);
            }
        }
    }
}
