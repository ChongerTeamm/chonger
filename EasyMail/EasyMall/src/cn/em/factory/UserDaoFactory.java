package cn.em.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import cn.em.dao.UserDao;
import cn.em.service.UserService;

public class UserDaoFactory {
// ����ģʽ��ȷ��һ�����ڳ�����ֻ����һ��ʵ��
// 1.˽�еĹ���������ֹ������ⲿͨ��new��䴴��ʵ��
	private UserDaoFactory(){
		// ��ȡClassLoader����
		ClassLoader loader=UserDaoFactory.class.getClassLoader();
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
	private static UserDaoFactory factory=new UserDaoFactory();
// 3.���еľ�̬�ķ��ر���Ψһʵ���ķ���
	public static UserDaoFactory getFactory(){
		return factory;
	}
	
	// ��ȡ�����ļ��Ĺ�����
	private Properties prop=new Properties();
	
	public UserDao getInstance(){
		UserDao dao=null;
		//��ȡ�����ļ������õ�ʵ����İ���.����
		String value=prop.getProperty("UserDao");
		try {
			//���ظ�ʵ����
			Class c=Class.forName(value);
			//ͨ�����䴴�������ʵ��
			if(c!=null){
				dao=(UserDao) c.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//����ģʽ  ����-����
	//�������ڴ��н����ڱ����Ψһ��һ��ʵ��
	//�������ڴ��д��ڱ���Ķ��ʵ��
	//���������Ϊ�˵����������ķ�������ε���
	//��ִ�е��߼�����ͬ�ģ������͹���

	//������������Ҫʹ�ö��ʵ����װ��ͬ������
	
	
	
}
