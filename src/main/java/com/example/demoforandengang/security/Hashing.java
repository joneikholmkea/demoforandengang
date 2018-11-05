package com.example.demoforandengang.security;

import org.apache.commons.codec.digest.DigestUtils;

public class Hashing {

    public static String getHash(String plainText){

        return DigestUtils.sha256Hex(plainText);
    }
}
