package com.tiny.core.common.security;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public final class SHAUtils {


	/**
	 *
	 * @param source
	 * @param algorithm 摘要算法枚举
	 * @return
	 */
	public static String encode(String source, SecurityConstants.MessageDigest algorithm){
		return encode(source,algorithm.getAlgorithm());
	}
	/**
	 * 利用java原生的类实现SHA256加密
	 *
	 * @param source 原值
	 * @return
	 */
	public static String encode(String source,String algorithm) {
		MessageDigest messageDigest;
		String encodestr = "";
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(source.getBytes(StandardCharsets.UTF_8));
			encodestr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			log.error("",e.getMessage());
		}
		return encodestr;
	}

	/**
	 * 将byte转为16进制
	 *
	 * @param bytes
	 * @return
	 */
	private static String byte2Hex(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				// 1得到一位的进行补0操作
				stringBuilder.append("0");
			}
			stringBuilder.append(temp);
		}
		return stringBuilder.toString();
	}

	private SHAUtils(){}

}
