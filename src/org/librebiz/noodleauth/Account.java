package org.librebiz.noodleauth;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Account {
    private String name;
    private byte[] key;
    private int otpLength;
    private int validity;

    public Account(String name, byte[] key, int otpLength, int validity) {
        this.name = name;
        this.key = key.clone();
        this.otpLength = otpLength;
        this.validity = validity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public int getOtpLength() {
        return otpLength;
    }

    public void setOtpLength(int otpLength) {
        this.otpLength = otpLength;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public OTP generateOTP(long time) {
        long stime = time/1000;
        int ttl = (int)(stime%validity);
        String code = generate(key, stime/validity, otpLength);
        return new OTP(code, ttl);
    }

    private static String generate(byte[] key, long time, int digits) {
        byte[] msg = Bytes.longToBytes(time);
        byte[] hash = hmac_sha("HmacSHA1", key, msg);

        int offset = hash[hash.length - 1] & 0xf;
        int otp = Bytes.bytesToInt(hash, offset, 4) & 0x7FFFFFFF;

        char[] chars = new char[digits];
        while (digits > 0) {
            chars[--digits] = (char)('0' + (otp%10));
            otp /= 10;
        }
        return new String(chars);
    }

    private static byte[] hmac_sha(String alg, byte[] key, byte[] data) {
        try {
            Mac mac = Mac.getInstance(alg);
            SecretKeySpec spec = new SecretKeySpec(key, "RAW");
            mac.init(spec);
            return mac.doFinal(data);
        } catch (GeneralSecurityException gse) {
            throw new RuntimeException(gse.getMessage());
        }
    }
}
