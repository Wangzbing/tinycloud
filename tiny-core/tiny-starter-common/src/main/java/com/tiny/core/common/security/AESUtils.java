package com.tiny.core.common.security;



import com.tiny.core.common.constant.StringConstant;
import com.tiny.core.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


public final class AESUtils {

    private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);


    public static String encrypt(String sSrc, String sKey) throws Exception {
        return encrypt(sSrc, sKey, null);
    }

    public static String encrypt(String sSrc, String sKey, String ivParameter) throws Exception {

        if (!checkKey(sKey)) {
            return null;
        }

        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, SecurityConstants.AesAlgorithm.ALGORITHM_AES.getModel());
        Cipher cipher;

        if (checkKey(ivParameter)) {
            //"算法/模式/补码方式"
            cipher = Cipher.getInstance(cbcModel());
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        } else {
            cipher = Cipher.getInstance(ecbModel());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        }

        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return Base64Utils.encodeToString(encrypted);
    }

    public static String decrypt(String sSrc, String sKey) throws Exception {
        return decrypt(sSrc, sKey, null);
    }

    // 解密
    public static String decrypt(String sSrc, String sKey, String ivParameter) throws Exception {

        try {

            if (!checkKey(sKey)) {
                return null;
            }

            byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, SecurityConstants.AesAlgorithm.ALGORITHM_AES.getModel());
            Cipher cipher ;
            if (checkKey(ivParameter)) {
                cipher = Cipher.getInstance(cbcModel());
                IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            } else {
                cipher = Cipher.getInstance(ecbModel());
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            }

            byte[] encrypted1 = Base64Utils.decode(sSrc);//先用base64解密

            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original);
            } catch (Exception e) {
                logger.error("", e);

            }

        } catch (Exception ex) {
            logger.error("", ex);
        }

        return null;
    }

    private AESUtils() {
    }


    private static boolean checkKey(String sKey) {
        if (StringUtils.isEmpty(sKey)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Key为空null");
            }
            return false;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {

            if (logger.isDebugEnabled()) {
                logger.debug("Key长度不是16位");
            }

            return false;
        }
        return true;
    }

    private static String cbcModel() {
        return SecurityConstants.AesAlgorithm.ALGORITHM_AES.getModel() + StringConstant.SLASH + SecurityConstants.AesAlgorithm.ENCRYPT_MODE_CBC.getModel() + StringConstant.SLASH + SecurityConstants.AesAlgorithm.COMPLEMENT_WAY_PKCS5PADDING.getModel();
    }

    private static String ecbModel() {
        return SecurityConstants.AesAlgorithm.ALGORITHM_AES.getModel() + StringConstant.SLASH + SecurityConstants.AesAlgorithm.ENCRYPT_MODE_ECB.getModel() + StringConstant.SLASH + SecurityConstants.AesAlgorithm.COMPLEMENT_WAY_PKCS5PADDING.getModel();
    }

    public static void main(String[] args) throws Exception {
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
         * 此处使用AES-128-CBC加密模式，key需要为16位。
         */

        // 需要加密的字串
        // String cSrc = "{data:[{'name':'你好','age':20},{'name':'zd','age':18}]}";
        // String cSrc = "8daJ5c940sWvu51d4klXq40q82eal34A";
        String cSrc = "secret";
        // 加密
        long lStart = System.currentTimeMillis();
        // String enString = AESUtils.encrypt(cSrc, "0123456789abcdef", "0123456789abcdef");
        String enString = AESUtils.encrypt(cSrc, "0123456789abcdef");
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        // String deString = AESUtils.decrypt(enString, "0123456789abcdef", "0123456789abcdef");
        String deString = AESUtils.decrypt(enString, "0123456789abcdef");
        System.out.println("解密后的字串是：" + deString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }


}
