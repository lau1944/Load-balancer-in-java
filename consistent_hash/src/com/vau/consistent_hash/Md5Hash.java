package com.vau.consistent_hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Md5Hash implements IHash {
    private MessageDigest instance;

    Md5Hash() {
        try {
            this.instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long doHash(String key) {
        if (Objects.isNull(instance)) {
            throw new IllegalStateException("Digest is not instantiated");
        }

        instance.reset();
        instance.update(key.getBytes());
        byte[] digest = instance.digest();

        // padding & clip
        long h = 0;
        for (int i = 0; i < 4; i++) {
            h <<= 8;
            h |= ((int) digest[i]) & 0xFF;
        }
        return h;
    }
}
