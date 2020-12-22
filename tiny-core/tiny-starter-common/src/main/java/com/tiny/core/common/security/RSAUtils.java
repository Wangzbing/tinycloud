package com.tiny.core.common.security;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author zlf240
 *
 */
@Slf4j
public final class RSAUtils {

	private static final String RSA_ALGORITHM = "RSA";

	private static final int KEY_SIZE = 2048;

	private static final int ENCODE_PART_SIZE = KEY_SIZE / 8;

	private RSAUtils(){}

	public static RSAKeyPair createKeys(){
		return createKeys(KEY_SIZE);
	}

	public static RSAKeyPair createKeys(int keySize) {
		RSAKeyPair rsaKeyPair = null;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
			keyPairGenerator.initialize(keySize, new SecureRandom());
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			// 获取公钥秘钥
			String publicKey = Base64Utils.encodeToString(keyPair.getPublic().getEncoded());
			String privateKey = Base64Utils.encodeToString(keyPair.getPrivate().getEncoded());
			rsaKeyPair = new RSAKeyPair(publicKey,privateKey);
			// 存入公钥秘钥，以便以后获取
		} catch (NoSuchAlgorithmException e) {
			log.error("当前JDK版本没找到RSA加密算法！", e);
		}
		return rsaKeyPair;
	}

	/**
	 * 得到公钥
	 * @param publicKey  密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 通过X509编码的Key指令获得公钥对象
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64Utils.decode(publicKey));
		return (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
	}

	/**
	 * 得到私钥
	 * @param privateKey  密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 通过PKCS#8编码的Key指令获得私钥对象
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64Utils.decode(privateKey));
		return  (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
	}

	/**
	 * 公钥加密
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String publicEncrypt(String data, RSAPublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return Base64Utils.decodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE,
					data.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			throw new SecurityException("加密字符串[" + data + "]时遇到异常", e);
		}
	}

	/**
	 * 公钥解密
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String publicDecrypt(String data, RSAPublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64Utils.decode(data)),
					StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new SecurityException("解密字符串[" + data + "]时遇到异常", e);
		}
	}


	/**
	 * rsa切割解码  , ENCRYPT_MODE,   ,DECRYPT_MODE,解密数据
	 * @param cipher 解析器
	 * @param opmode 模式
	 * @param datas 数据
	 * @return byte[]
	 */
	private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas) {
		//最大块
		int maxBlock = 0;
		if (opmode == Cipher.DECRYPT_MODE) {
			maxBlock = ENCODE_PART_SIZE;
		} else {
			maxBlock = ENCODE_PART_SIZE - 11;
		}

		int offSet = 0;
		byte[] buff;
		int i = 0;
		byte[] resultDatas = null;
		try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			while (datas.length > offSet) {
				if (datas.length - offSet > maxBlock) {
					//可以调用以下的doFinal（）方法完成加密或解密数据：
					buff = cipher.doFinal(datas, offSet, maxBlock);
				} else {
					buff = cipher.doFinal(datas, offSet, datas.length - offSet);
				}
				out.write(buff, 0, buff.length);
				i++;
				offSet = i * maxBlock;
			}
			resultDatas = out.toByteArray();
		} catch (Exception e) {
			throw new SecurityException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
		}
		return resultDatas;
	}

}
