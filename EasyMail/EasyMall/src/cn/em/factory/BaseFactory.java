package cn.em.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ���ڽ���Ĺ�����
 */
public class BaseFactory {
	//����
	//1.˽�еĹ�����
	private BaseFactory(){
		// ��ȡ�����ļ�������
		// 1.��ȡ�����ļ��ľ���·��
		ClassLoader loader=BaseFactory.class.getClassLoader();
		String path=loader.getResource("config.properties").getPath();
		// 2.ʹ�ù������ȡ�������ļ�
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//2.˽�еľ�̬�ı����Ψһʵ��
	private static BaseFactory factory=new BaseFactory();
	//3.���еľ�̬�ķ��ر���Ψһʵ���ķ���
	public static BaseFactory getFactory(){
		return factory;
	}
	// ���ڶ�ȡ�����ļ��Ĺ�����
	private Properties prop=new Properties();
	
	
	/**
	 * ���ݴ���Ľӿ����ʹ��������ļ���
	 * �����õĸýӿڵ�ʵ����
	 * 
	 * @param infc �ӿڵ�class����
	 * @return �ýӿڵ�ʵ�����ʵ�� �� null
	 */
	public <T> T getInstance(Class<T> infc){
		T t=null;
		//��ȡ�����ļ������õ�ʵ����İ���.����
		String value=prop.getProperty(infc.getSimpleName());
		// ���ظ�ʵ����
		try {
			Class c=Class.forName(value);
			if(c!=null){
				t=(T) c.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
	

}
