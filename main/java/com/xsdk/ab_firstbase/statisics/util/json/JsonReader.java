package com.xsdk.ab_firstbase.statisics.util.json;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class JsonReader {
    private char quoteChar;
    private JsonToken token;
    private Object value;
    private Class<?> valueType;
    private State currentState = State.Start;
    private List<JsonTokenType> stack = new ArrayList();

    /* renamed from: top, reason: collision with root package name */
    private int f2941top = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public enum State {
        Start,
        Complete,
        Property,
        ObjectStart,
        Object,
        ArrayStart,
        Array,
        Closed,
        PostValue,
        ConstructorStart,
        Constructor,
        Error,
        Finished
    }

    public abstract boolean read() throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public State getCurrentState() {
        return this.currentState;
    }

    public char getQuoteChar() {
        return this.quoteChar;
    }

    public void setQuoteChar(char c) {
        this.quoteChar = c;
    }

    public JsonToken getTokenType() {
        return this.token;
    }

    public Object getValue() {
        return this.value;
    }

    public Class<?> getValueType() {
        return this.valueType;
    }

    public int getDepth() {
        int i = this.f2941top - 1;
        return isStartToken(getTokenType()) ? i - 1 : i;
    }

    public JsonReader() {
        push(JsonTokenType.None);
    }

    private void push(JsonTokenType jsonTokenType) {
        this.stack.add(jsonTokenType);
        this.f2941top++;
    }

    private JsonTokenType pop() {
        JsonTokenType peek = peek();
        this.stack.remove(r1.size() - 1);
        this.f2941top--;
        return peek;
    }

    private JsonTokenType peek() {
        return this.stack.get(this.f2941top - 1);
    }

    public void skip() throws Exception {
        if (isStartToken(getTokenType())) {
            int depth = getDepth();
            while (read() && depth < getDepth()) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setToken(JsonToken jsonToken) throws Exception {
        setToken(jsonToken, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setToken(JsonToken jsonToken, Object obj) throws Exception {
        this.token = jsonToken;
        if (jsonToken == JsonToken.StartObject) {
            this.currentState = State.ObjectStart;
            push(JsonTokenType.Object);
        } else if (jsonToken == JsonToken.StartArray) {
            this.currentState = State.ArrayStart;
            push(JsonTokenType.Array);
        } else if (jsonToken == JsonToken.StartConstructor) {
            this.currentState = State.ConstructorStart;
            push(JsonTokenType.Constructor);
        } else if (jsonToken == JsonToken.EndObject) {
            validateEnd(JsonToken.EndObject);
            this.currentState = State.PostValue;
        } else if (jsonToken == JsonToken.EndArray) {
            validateEnd(JsonToken.EndArray);
            this.currentState = State.PostValue;
        } else if (jsonToken == JsonToken.EndConstructor) {
            validateEnd(JsonToken.EndConstructor);
            this.currentState = State.PostValue;
        } else if (jsonToken == JsonToken.PropertyName) {
            this.currentState = State.Property;
            push(JsonTokenType.Property);
        } else if (jsonToken == JsonToken.Undefined || jsonToken == JsonToken.Integer || jsonToken == JsonToken.Float || jsonToken == JsonToken.Boolean || jsonToken == JsonToken.Null || jsonToken == JsonToken.Date || jsonToken == JsonToken.String) {
            this.currentState = State.PostValue;
        }
        if (peek() == JsonTokenType.Property && this.currentState == State.PostValue) {
            pop();
        }
        if (obj != null) {
            this.value = obj;
            this.valueType = obj.getClass();
        } else {
            this.value = null;
            this.valueType = null;
        }
    }

    private void validateEnd(JsonToken jsonToken) throws Exception {
        JsonTokenType pop = pop();
        if (getTypeForCloseToken(jsonToken) == pop) {
            return;
        }
        throw new Exception("JsonToken " + jsonToken + " 没有正确地结束： " + pop);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCurrentState() throws Exception {
        JsonTokenType peek = peek();
        if (peek == JsonTokenType.Object) {
            this.currentState = State.Object;
            return;
        }
        if (peek == JsonTokenType.Array) {
            this.currentState = State.Array;
            return;
        }
        if (peek == JsonTokenType.Constructor) {
            this.currentState = State.Constructor;
        } else if (peek == JsonTokenType.None) {
            this.currentState = State.Finished;
        } else {
            throw new Exception("此token有误：" + peek);
        }
    }

    private boolean isStartToken(JsonToken jsonToken) {
        if (jsonToken == JsonToken.StartObject || jsonToken == JsonToken.StartArray || jsonToken == JsonToken.StartConstructor || jsonToken == JsonToken.PropertyName) {
            return true;
        }
        if (jsonToken == JsonToken.None || jsonToken == JsonToken.Comment || jsonToken == JsonToken.Integer || jsonToken == JsonToken.Float || jsonToken == JsonToken.String || jsonToken == JsonToken.Boolean || jsonToken == JsonToken.Null || jsonToken == JsonToken.Undefined || jsonToken == JsonToken.EndObject || jsonToken == JsonToken.EndArray || jsonToken == JsonToken.EndConstructor || jsonToken == JsonToken.Date) {
            return false;
        }
        throw new IndexOutOfBoundsException("token：" + jsonToken + "有误，不是正确的开始token");
    }

    private JsonTokenType getTypeForCloseToken(JsonToken jsonToken) throws Exception {
        if (jsonToken == JsonToken.EndObject) {
            return JsonTokenType.Object;
        }
        if (jsonToken == JsonToken.EndArray) {
            return JsonTokenType.Array;
        }
        if (jsonToken == JsonToken.EndConstructor) {
            return JsonTokenType.Constructor;
        }
        throw new Exception("token：" + jsonToken + "有误，不是正确的关闭token");
    }

    public void close() {
        if (this.currentState != State.Closed) {
            this.currentState = State.Closed;
            this.token = JsonToken.None;
            this.value = null;
            this.valueType = null;
        }
    }
}
