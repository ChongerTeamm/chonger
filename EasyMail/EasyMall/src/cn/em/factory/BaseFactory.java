package cn.em.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 用于解耦的工厂类
 */
public class BaseFactory {
	//单例
	//1.私有的构造器
	private BaseFactory(){
		// 读取配置文件的内容
		// 1.获取配置文件的绝对路径
		ClassLoader loader=BaseFactory.class.getClassLoader();
		String path=loader.getResource("config.properties").getPath();
		// 2.使用工具类读取该配置文件
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//2.私有的静态的本类的唯一实例
	private static BaseFactory factory=new BaseFactory();
	//3.公有的静态的返回本类唯一实例的方法
	public static BaseFactory getFactory(){
		return factory;
	}
	// 用于读取配置文件的工具类
	private Properties prop=new Properties();
	
	
	/**
	 * 根据传入的接口类型创建配置文件中
	 * 所配置的该接口的实现类
	 * 
	 * @param infc 接口的class对象
	 * @return 该接口的实现类的实例 或 null
	 */
	public <T> T getInstance(Class<T> infc){
		T t=null;
		//获取配置文件中配置的实现类的包名.类名
		String value=prop.getProperty(infc.getSimpleName());
		// 加载该实现类
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
