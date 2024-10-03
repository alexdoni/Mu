package com.xsdk.ab_firstbase.statisics.util.json;

import androidx.exifinterface.media.ExifInterface;
import com.xsdk.ab_firstbase.statisics.util.DateTool;
import com.xsdk.ab_firstbase.statisics.util.json.JsonReader;
import java.io.IOException;

/* loaded from: classes3.dex */
public class JsonTextReader extends JsonReader {
    private StringBuffer buffer;
    private char currentChar;
    private StringTextReader reader;

    public JsonTextReader(StringTextReader stringTextReader) {
        if (stringTextReader == null) {
            throw new NullPointerException("reader不能为空");
        }
        this.reader = stringTextReader;
        this.buffer = new StringBuffer(4096);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ba, code lost:
    
        if (r1 == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00bc, code lost:
    
        clearCurrentChar();
        r1 = r7.buffer.toString();
        r7.buffer.setLength(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d0, code lost:
    
        if (r1.startsWith("/Date(") == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00d8, code lost:
    
        if (r1.endsWith(")/") == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00da, code lost:
    
        parseDate(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00de, code lost:
    
        setToken(com.xsdk.ab_firstbase.statisics.util.json.JsonToken.String, r1);
        setQuoteChar(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00e6, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00fa, code lost:
    
        throw new java.lang.Exception("字符未正确终止: " + r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseString(char r8) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.ab_firstbase.statisics.util.json.JsonTextReader.parseString(char):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xsdk.ab_firstbase.statisics.util.json.JsonReader
    public void setToken(JsonToken jsonToken, Object obj) throws Exception {
        super.setToken(jsonToken, obj);
        if (jsonToken == JsonToken.StartObject) {
            clearCurrentChar();
            return;
        }
        if (jsonToken == JsonToken.StartArray) {
            clearCurrentChar();
            return;
        }
        if (jsonToken == JsonToken.StartConstructor) {
            clearCurrentChar();
            return;
        }
        if (jsonToken == JsonToken.EndObject) {
            clearCurrentChar();
            return;
        }
        if (jsonToken == JsonToken.EndArray) {
            clearCurrentChar();
        } else if (jsonToken == JsonToken.EndConstructor) {
            clearCurrentChar();
        } else if (jsonToken == JsonToken.PropertyName) {
            clearCurrentChar();
        }
    }

    private void parseDate(String str) throws Exception {
        setToken(JsonToken.Date, DateTool.getDateFormat(str.substring(6, str.length() - 8), DateTool.yyyy_MM_dd_HH_mm_ss));
    }

    private boolean moveNext() throws IOException {
        int read = this.reader.read();
        if (read == -1) {
            return false;
        }
        this.currentChar = (char) read;
        return true;
    }

    private boolean hasNext() throws IOException {
        return this.reader.peek() != -1;
    }

    private char peekNext() throws IOException {
        return (char) this.reader.peek();
    }

    private void clearCurrentChar() {
        this.currentChar = (char) 0;
    }

    private boolean moveTo(char c) throws IOException {
        while (moveNext()) {
            if (this.currentChar == c) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0068, code lost:
    
        return parseObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x006d, code lost:
    
        return parseValue();
     */
    @Override // com.xsdk.ab_firstbase.statisics.util.json.JsonReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean read() throws java.lang.Exception {
        /*
            r3 = this;
        L0:
            char r0 = r3.currentChar
            if (r0 != 0) goto Lc
            boolean r0 = r3.moveNext()
            if (r0 != 0) goto Lc
            r0 = 0
            return r0
        Lc:
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r0 = r3.getCurrentState()
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Start
            if (r0 == r1) goto L69
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Property
            if (r0 == r1) goto L69
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Array
            if (r0 == r1) goto L69
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.ArrayStart
            if (r0 == r1) goto L69
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Constructor
            if (r0 == r1) goto L69
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.ConstructorStart
            if (r0 != r1) goto L29
            goto L69
        L29:
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Complete
            if (r1 != r0) goto L2e
            goto L0
        L2e:
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Object
            if (r1 == r0) goto L64
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.ObjectStart
            if (r1 != r0) goto L37
            goto L64
        L37:
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.PostValue
            if (r1 != r0) goto L43
            boolean r0 = r3.parsePostValue()
            if (r0 == 0) goto L0
            r0 = 1
            return r0
        L43:
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Closed
            if (r1 == r0) goto L0
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r1 = com.xsdk.ab_firstbase.statisics.util.json.JsonReader.State.Error
            if (r1 != r0) goto L4c
            goto L0
        L4c:
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "错误的状态: "
            r1.<init>(r2)
            com.xsdk.ab_firstbase.statisics.util.json.JsonReader$State r2 = r3.getCurrentState()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L64:
            boolean r0 = r3.parseObject()
            return r0
        L69:
            boolean r0 = r3.parseValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.ab_firstbase.statisics.util.json.JsonTextReader.read():boolean");
    }

    private boolean parsePostValue() throws Exception {
        do {
            char c = this.currentChar;
            if (c == ')') {
                setToken(JsonToken.EndConstructor);
                clearCurrentChar();
                return true;
            }
            if (c == ',') {
                setCurrentState();
                clearCurrentChar();
                return false;
            }
            if (c == '/') {
                parseComment();
                return true;
            }
            if (c == ']') {
                setToken(JsonToken.EndArray);
                clearCurrentChar();
                return true;
            }
            if (c == '}') {
                setToken(JsonToken.EndObject);
                clearCurrentChar();
                return true;
            }
            if (Character.isWhitespace(c)) {
                clearCurrentChar();
            } else {
                throw new Exception("在解释[" + this.currentChar + "]后发生了错误");
            }
        } while (moveNext());
        return false;
    }

    private boolean parseObject() throws Exception {
        do {
            char c = this.currentChar;
            if (c == ',') {
                setToken(JsonToken.Undefined);
                return true;
            }
            if (c == '/') {
                parseComment();
                return true;
            }
            if (c == '}') {
                setToken(JsonToken.EndObject);
                return true;
            }
            if (!Character.isWhitespace(c)) {
                return parseProperty();
            }
        } while (moveNext());
        return false;
    }

    private boolean parseProperty() throws Exception {
        if (validIdentifierChar(this.currentChar)) {
            parseUnquotedProperty();
        } else {
            char c = this.currentChar;
            if (c == '\"' || c == '\'') {
                parseQuotedProperty(c);
            } else {
                throw new Exception("属性不可采用此字符: " + this.currentChar);
            }
        }
        if (this.currentChar != ':') {
            moveTo(':');
        }
        setToken(JsonToken.PropertyName, this.buffer.toString());
        this.buffer.setLength(0);
        return true;
    }

    private void parseQuotedProperty(char c) throws Exception {
        while (moveNext()) {
            char c2 = this.currentChar;
            if (c2 == c) {
                return;
            } else {
                this.buffer.append(c2);
            }
        }
        throw new Exception("未关闭的引号: " + c);
    }

    private boolean validIdentifierChar(char c) {
        char c2;
        return Character.isLetterOrDigit(this.currentChar) || (c2 = this.currentChar) == '_' || c2 == '$';
    }

    private void parseUnquotedProperty() throws Exception {
        char c;
        this.buffer.append(this.currentChar);
        while (moveNext() && !Character.isWhitespace(this.currentChar) && (c = this.currentChar) != ':') {
            if (validIdentifierChar(c)) {
                this.buffer.append(this.currentChar);
            } else {
                throw new Exception("错误的属性字符，不能使用此字符: " + this.currentChar);
            }
        }
    }

    private boolean parseValue() throws Exception {
        char c;
        do {
            char c2 = this.currentChar;
            switch (c2) {
                case '\"':
                case '\'':
                    parseString(c2);
                    return true;
                case ')':
                    setToken(JsonToken.EndConstructor);
                    return true;
                case ',':
                    setToken(JsonToken.Undefined);
                    return true;
                case '-':
                    if (peekNext() == 'I') {
                        parseNumberNegativeInfinity();
                    } else {
                        parseNumber();
                    }
                    return true;
                case '/':
                    parseComment();
                    return true;
                case 'I':
                    parseNumberPositiveInfinity();
                    return true;
                case 'N':
                    parseNumberNaN();
                    return true;
                case '[':
                    setToken(JsonToken.StartArray);
                    return true;
                case ']':
                    setToken(JsonToken.EndArray);
                    return true;
                case 'f':
                    parseFalse();
                    return true;
                case 'n':
                    if (hasNext()) {
                        char peekNext = peekNext();
                        if (peekNext == 'u') {
                            parseNull();
                        } else if (peekNext == 'e') {
                            parseConstructor();
                        } else {
                            throw new Exception("错误的字符: " + this.currentChar);
                        }
                        return true;
                    }
                    throw new Exception("解释时错误的地结束");
                case 't':
                    parseTrue();
                    return true;
                case 'u':
                    parseUndefined();
                    return true;
                case '{':
                    setToken(JsonToken.StartObject);
                    return true;
                case '}':
                    setToken(JsonToken.EndObject);
                    return true;
                default:
                    if (!Character.isWhitespace(c2)) {
                        if (Character.isDigit(this.currentChar) || (c = this.currentChar) == '-' || c == '.') {
                            parseNumber();
                            return true;
                        }
                        throw new Exception("解释时遇到错误的字符: " + this.currentChar);
                    }
                    break;
                    break;
            }
        } while (moveNext());
        return false;
    }

    private boolean removeWhitespace(boolean z) throws IOException {
        boolean z2 = false;
        while (Character.isWhitespace(this.currentChar)) {
            moveNext();
            z2 = true;
        }
        return !z || z2;
    }

    private void parseConstructor() throws Exception {
        if (matchValue("new", true) && removeWhitespace(true)) {
            while (Character.isLetter(this.currentChar)) {
                this.buffer.append(this.currentChar);
                moveNext();
            }
            removeWhitespace(false);
            if (this.currentChar != '(') {
                throw new Exception("解释构造函数时出错: " + this.currentChar);
            }
            String stringBuffer = this.buffer.toString();
            this.buffer.setLength(0);
            setToken(JsonToken.StartConstructor, stringBuffer);
        }
    }

    private void parseNumber() throws Exception {
        Object valueOf;
        JsonToken jsonToken;
        boolean z = false;
        do {
            if (isSeperator()) {
                z = true;
            } else {
                this.buffer.append(this.currentChar);
            }
            if (z) {
                break;
            }
        } while (moveNext());
        String stringBuffer = this.buffer.toString();
        if (stringBuffer.indexOf(".") == -1 && stringBuffer.indexOf("e") == -1 && stringBuffer.indexOf(ExifInterface.LONGITUDE_EAST) == -1) {
            valueOf = Integer.valueOf(Integer.parseInt(this.buffer.toString()));
            jsonToken = JsonToken.Integer;
        } else {
            valueOf = Double.valueOf(Double.parseDouble(this.buffer.toString()));
            jsonToken = JsonToken.Float;
        }
        this.buffer.setLength(0);
        setToken(jsonToken, valueOf);
    }

    private void parseComment() throws Exception {
        moveNext();
        if (this.currentChar == '*') {
            while (moveNext()) {
                char c = this.currentChar;
                if (c == '*') {
                    if (!moveNext()) {
                        continue;
                    } else {
                        if (this.currentChar == '/') {
                            break;
                        }
                        this.buffer.append('*');
                        this.buffer.append(this.currentChar);
                    }
                } else {
                    this.buffer.append(c);
                }
            }
            setToken(JsonToken.Comment, this.buffer.toString());
            this.buffer.setLength(0);
            clearCurrentChar();
            return;
        }
        throw new Exception("解释注释时出错 :" + this.currentChar);
    }

    private boolean matchValue(String str) throws IOException {
        int i = 0;
        while (this.currentChar == str.charAt(i) && (i = i + 1) < str.length() && moveNext()) {
        }
        return i == str.length();
    }

    private boolean matchValue(String str, boolean z) throws IOException {
        boolean matchValue = matchValue(str);
        return !z ? matchValue : matchValue && (!moveNext() || isSeperator());
    }

    private boolean isSeperator() throws IOException {
        char c = this.currentChar;
        if (c != ')') {
            if (c != ',') {
                if (c == '/') {
                    return hasNext() && peekNext() == '*';
                }
                if (c == ']' || c == '}' || Character.isWhitespace(c)) {
                    return true;
                }
            }
            return true;
        }
        if (getCurrentState() == JsonReader.State.Constructor || getCurrentState() == JsonReader.State.ConstructorStart) {
            return true;
        }
        return false;
    }

    private void parseTrue() throws Exception {
        if (matchValue("true", true)) {
            setToken(JsonToken.Boolean, true);
            return;
        }
        throw new Exception("解释布尔型时出错");
    }

    private void parseNull() throws Exception {
        if (matchValue(JsonSerializer.Null, true)) {
            setToken(JsonToken.Null);
            return;
        }
        throw new Exception("解释null时出错");
    }

    private void parseUndefined() throws Exception {
        if (matchValue(JsonSerializer.Undefined, true)) {
            setToken(JsonToken.Undefined);
            return;
        }
        throw new Exception("解释undefined时出错");
    }

    private void parseFalse() throws Exception {
        if (matchValue(JsonSerializer.False, true)) {
            setToken(JsonToken.Boolean, false);
            return;
        }
        throw new Exception("解释false时出错");
    }

    private void parseNumberNegativeInfinity() throws Exception {
        if (matchValue(JsonSerializer.NegativeInfinity, true)) {
            setToken(JsonToken.Float, Double.valueOf(Double.NEGATIVE_INFINITY));
            return;
        }
        throw new Exception("解释负无限大时出错");
    }

    private void parseNumberPositiveInfinity() throws Exception {
        if (matchValue(JsonSerializer.PositiveInfinity, true)) {
            setToken(JsonToken.Float, Double.valueOf(Double.POSITIVE_INFINITY));
            return;
        }
        throw new Exception("解释正无限大时出错");
    }

    private void parseNumberNaN() throws Exception {
        if (matchValue(JsonSerializer.NaN, true)) {
            setToken(JsonToken.Float, Double.valueOf(Double.NaN));
            return;
        }
        throw new Exception("解释Nan时出错");
    }

    @Override // com.xsdk.ab_firstbase.statisics.util.json.JsonReader
    public void close() {
        super.close();
        StringTextReader stringTextReader = this.reader;
        if (stringTextReader != null) {
            stringTextReader.close();
        }
        StringBuffer stringBuffer = this.buffer;
        if (stringBuffer != null) {
            stringBuffer.setLength(0);
        }
    }
}
