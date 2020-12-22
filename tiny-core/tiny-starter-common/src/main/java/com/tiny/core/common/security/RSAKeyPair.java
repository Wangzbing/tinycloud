package com.tiny.core.common.security;


/**
 * @author Amin
 */
public class RSAKeyPair {

    private  final String publicKey;
    private  final String privateKey;

    public RSAKeyPair(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
