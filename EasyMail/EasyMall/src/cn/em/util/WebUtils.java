package cn.em.util;

/**
 * 供Servlet使用的工具类
 */
public class WebUtils {
	
	/**
	 * 用来判断字符串是否为null或者trim之后是空串的方法
	 * @param str 被判断的字符串
	 * @return true-是空的 false-不为null或者空串
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

}
