package cn.em.dao;

import java.io.IOException;

import cn.em.exception.MsgException;
import cn.em.factory.BaseFactory;
import cn.em.factory.UserServiceFactory;
import cn.em.service.UserService;
import cn.em.util.MD5Util;

public class Test {

	public static void main(String[] args){
		String password="123";
		String ps=MD5Util.md5(password);
		System.out.println(ps);
		
		
//		UserService service=BaseFactory.getFactory().getInstance(UserService.class);
//		
//		System.out.println(service);
//		
//		UserDao dao=BaseFactory.getFactory().getInstance(UserDao.class);
//		System.out.println(dao);
		//UserServiceFactory.factory=null;
		
//		UserServiceFactory factory=UserServiceFactory.getFactory();
//		System.out.println(factory);
//		Class c=UserServiceFactory.class;
//		System.out.println(c.getSimpleName());
		
//		try {
//			method1();
//		} catch (MsgException e) {
//			String msg=e.getMessage();
//			System.out.println("msg="+msg);
//		}
	}
	
	public static void method1() throws MsgException{
		throw new MsgException("数据库连接异常，请稍后重试");
	}
	

}
