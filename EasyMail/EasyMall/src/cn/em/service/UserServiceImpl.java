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
		// 该方法接收的参数ServletContext是web层特有的对象
		// 因此该方法和web层构成了强耦合
		// 一旦service层前面的不是web层，则该方法将失效
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
