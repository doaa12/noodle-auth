package org.librebiz.noodleauth;

public class OTP {
    private final String code;
    private final int ttl; // in secs

    public OTP(String code, int ttl) {
        this.code = code;
        this.ttl = ttl;
    }

    public String getCode() {
        return code;
    }

    public int getTTL() {
        return ttl;
    }
}
