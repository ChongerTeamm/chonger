package com.chonger.sso.utils;

import java.util.Random;
/**
 * 随机生成一个四位数
 * @author hzy
 *
 */
public class RandomCode {
	/**
	 * 随机生成一个四位数
	 * @return 生成的数
	 */
	public static String getValidateCode(){
		Random random=new Random();
		int number=random.nextInt(8999)+1000;
		return number+"";
		
	}
}
