package cn.em.util;

/**
 * ��Servletʹ�õĹ�����
 */
public class WebUtils {
	
	/**
	 * �����ж��ַ����Ƿ�Ϊnull����trim֮���ǿմ��ķ���
	 * @param str ���жϵ��ַ���
	 * @return true-�ǿյ� false-��Ϊnull���߿մ�
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

}
