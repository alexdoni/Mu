package org.spongycastle.util.test;

import org.spongycastle.util.Strings;

/* loaded from: classes3.dex */
public class SimpleTestResult implements TestResult {
    private static final String SEPARATOR = Strings.lineSeparator();
    private Throwable exception;
    private String message;
    private boolean success;

    public SimpleTestResult(boolean z, String str) {
        this.success = z;
        this.message = str;
    }

    public SimpleTestResult(boolean z, String str, Throwable th) {
        this.success = z;
        this.message = str;
        this.exception = th;
    }

    public static TestResult successful(Test test, String str) {
        return new SimpleTestResult(true, test.getName() + ": " + str);
    }

    public static TestResult failed(Test test, String str) {
        return new SimpleTestResult(false, test.getName() + ": " + str);
    }

    public static TestResult failed(Test test, String str, Throwable th) {
        return new SimpleTestResult(false, test.getName() + ": " + str, th);
    }

    public static TestResult failed(Test test, String str, Object obj, Object obj2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str2 = SEPARATOR;
        sb.append(str2);
        sb.append("Expected: ");
        sb.append(obj);
        sb.append(str2);
        sb.append("Found   : ");
        sb.append(obj2);
        return failed(test, sb.toString());
    }

    public static String failedMessage(String str, String str2, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append(" failing ").append(str2);
        String str5 = SEPARATOR;
        stringBuffer.append(str5).append("    expected: ").append(str3);
        stringBuffer.append(str5).append("    got     : ").append(str4);
        return stringBuffer.toString();
    }

    @Override // org.spongycastle.util.test.TestResult
    public boolean isSuccessful() {
        return this.success;
    }

    @Override // org.spongycastle.util.test.TestResult
    public String toString() {
        return this.message;
    }

    @Override // org.spongycastle.util.test.TestResult
    public Throwable getException() {
        return this.exception;
    }
}
