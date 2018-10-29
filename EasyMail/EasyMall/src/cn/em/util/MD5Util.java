package cn.em.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * 对传入的文本进行md5加密的方法
	 * @param plainText 文本
	 * @return 加密后的密文
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		// 将二进制的密文数组转换成16进制的字符串表示
		String md5code = new BigInteger(1, secretBytes).toString(16);
		// 如果转换后的16进制表示不足32位，则在前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

}
