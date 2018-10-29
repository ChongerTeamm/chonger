package cn.em.service;

import javax.servlet.ServletContext;

import cn.em.dao.UserDao;
import cn.em.dao.UserDaoImpl;
import cn.em.domain.User;
import cn.em.exception.MsgException;
import cn.em.factory.BaseFactory;

public class UserServiceImpl implements UserService {
	private UserDao dao=BaseFactory.getFactory().getInstance(UserDao.class);
	
	public void test(ServletContext context){
		// �÷������յĲ���ServletContext��web�����еĶ���
		// ��˸÷�����web�㹹����ǿ���
		// һ��service��ǰ��Ĳ���web�㣬��÷�����ʧЧ
	}
	
	@Override
	public boolean hasUsername(String username) {
		boolean flag=dao.getUserByUsername(username);
		return flag;
	}

	@Override
	public boolean registUser(User user) {
		boolean flag=dao.saveUser(user);
		return flag;
	}

	@Override
	public User login(String username, String password) throws MsgException {
		User user=dao.getUserByUaP(username,password);
		return user;
	}

}
