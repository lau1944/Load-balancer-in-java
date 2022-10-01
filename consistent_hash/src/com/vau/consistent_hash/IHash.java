package com.vau.consistent_hash;

/**
 * Hashing function
 */
public interface IHash {
    long doHash(String key);
}
