package cn.em.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * �Դ�����ı�����md5���ܵķ���
	 * @param plainText �ı�
	 * @return ���ܺ������
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("û��md5����㷨��");
		}
		// �������Ƶ���������ת����16���Ƶ��ַ�����ʾ
		String md5code = new BigInteger(1, secretBytes).toString(16);
		// ���ת�����16���Ʊ�ʾ����32λ������ǰ�油0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

}
