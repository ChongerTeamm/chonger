package cn.em.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import cn.em.dao.UserDao;
import cn.em.service.UserService;

public class UserDaoFactory {
// 单例模式：确保一个类在程序中只能有一个实例
// 1.私有的构造器，禁止在类的外部通过new语句创建实例
	private UserDaoFactory(){
		// 获取ClassLoader对象
		ClassLoader loader=UserDaoFactory.class.getClassLoader();
		// 获取classpath下的配置文件的绝对路径
		String path=loader.getResource("config.properties").getPath();
		// 使用prop读取配置文件的内容
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
// 2.私有的静态的本类的唯一实例
	private static UserDaoFactory factory=new UserDaoFactory();
// 3.公有的静态的返回本类唯一实例的方法
	public static UserDaoFactory getFactory(){
		return factory;
	}
	
	// 读取配置文件的工具类
	private Properties prop=new Properties();
	
	public UserDao getInstance(){
		UserDao dao=null;
		//获取配置文件中配置的实现类的包名.类名
		String value=prop.getProperty("UserDao");
		try {
			//加载该实现类
			Class c=Class.forName(value);
			//通过反射创建该类的实例
			if(c!=null){
				dao=(UserDao) c.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//单例模式  单例-多例
	//单例：内存中仅存在本类的唯一的一个实例
	//多例：内存中存在本类的多个实例
	//如果仅仅是为了调用这个对象的方法，多次调用
	//所执行的逻辑是相同的，则单例就够用

	//多例往往是需要使用多个实例封装不同的数据
	
	
	
}
