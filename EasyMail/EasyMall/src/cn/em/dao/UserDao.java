package cn.em.dao;

import cn.em.domain.User;
import cn.em.exception.MsgException;

public interface UserDao {

	
	/**
	 * 通过用户名查询用户的方法
	 * @param username 用户名
	 * @return true-用户存在 false-用户不存在
	 */
	boolean getUserByUsername(String username);

	/**
	 * 添加用户的方法
	 * @param user 封装了用户数据的JavaBean
	 * @return true-添加成功 false-添加失败
	 */
	boolean saveUser(User user);

	/**
	 * 根据用户名和密码查询用户数据的方法
	 * @param username 用户名
	 * @param password 密码
	 * @return null-数据不存在，User对象-封装了用户信息
	 * @throws MsgException 封装错误提示信息的异常对象
	 */
	User getUserByUaP(String username, String password) throws MsgException;
	
}

/* 增  insert/save   insertUser/savaUser
 * 删  delete			deleteUserById
 * 改  update			updateUserById
 * 查 查1个	get		getUserByUsername
 * 	  查多个    list	listUserByAge
*/
