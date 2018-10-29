package cn.em.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import cn.em.service.UserService;

public class UserServiceFactory {
// ����ģʽ��ȷ��һ�����ڳ�����ֻ����һ��ʵ��
// 1.˽�еĹ���������ֹ������ⲿͨ��new��䴴��ʵ��
	private UserServiceFactory(){
		// ��ȡClassLoader����
		ClassLoader loader=UserServiceFactory.class.getClassLoader();
		// ��ȡclasspath�µ������ļ��ľ���·��
		String path=loader.getResource("config.properties").getPath();
		// ʹ��prop��ȡ�����ļ�������
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
// 2.˽�еľ�̬�ı����Ψһʵ��
	private static UserServiceFactory factory=new UserServiceFactory();
// 3.���еľ�̬�ķ��ر���Ψһʵ���ķ���
	public static UserServiceFactory getFactory(){
		return factory;
	}
	
	// ��ȡ�����ļ��Ĺ�����
	private Properties prop=new Properties();
	
	public UserService getInstance(){
		UserService service=null;
		//��ȡ�����ļ������õ�ʵ����İ���.����
		String value=prop.getProperty("UserService");
		try {
			//���ظ�ʵ����
			Class c=Class.forName(value);
			//ͨ�����䴴�������ʵ��
			if(c!=null){
				service=(UserService) c.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//����ģʽ  ����-����
	//�������ڴ��н����ڱ����Ψһ��һ��ʵ��
	//�������ڴ��д��ڱ���Ķ��ʵ��
	//���������Ϊ�˵����������ķ�������ε���
	//��ִ�е��߼�����ͬ�ģ������͹���

	//������������Ҫʹ�ö��ʵ����װ��ͬ������
	
	
	
}
