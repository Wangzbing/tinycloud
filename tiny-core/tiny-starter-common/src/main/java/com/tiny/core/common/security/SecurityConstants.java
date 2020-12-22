package com.tiny.core.common.security;


import com.tiny.core.common.util.StringUtils;

/**
 * @author Amin
 */
public final class SecurityConstants {


    private SecurityConstants() {

    }

    public enum AesAlgorithm {
        /**aes*/
        ALGORITHM_AES("AES"),
        /**cbc*/
        ENCRYPT_MODE_CBC("CBC"),
        /**ecb*/
        ENCRYPT_MODE_ECB("ECB"),
        /**PKCS5Padding*/
        COMPLEMENT_WAY_PKCS5PADDING("PKCS5Padding");

        private String model;

        AesAlgorithm(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }
    }

    public enum MessageDigest {
        /**SHA*/
        SHA("SHA-1"),
        /**SHA*/
        SHA_256("SHA-256"),
        /**SHA*/
        SHA_384("SHA-384"),
        /**SHA*/
        SHA_512("SHA-512"),
        /**SHA*/
        MD5("MD5");

        private String algorithm;

        MessageDigest(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public static MessageDigest of(String algorithm) {

            if(StringUtils.isEmpty(algorithm)) {
                return SHA_256;
            }

            switch (algorithm) {
                case "SHA-1":
                    return SHA;
                case "SHA_384":
                    return SHA_384;
                case "SHA-512":
                    return SHA_512;
                default:
                    return SHA_256;
            }
        }

    }
}
