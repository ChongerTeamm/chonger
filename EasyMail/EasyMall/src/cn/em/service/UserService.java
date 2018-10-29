package cn.em.service;

import cn.em.domain.User;
import cn.em.exception.MsgException;

public interface UserService {
	
	/**
	 * 用来判断用户名是否存在的方法
	 * @param username 用户名
	 * @return true-用户名存在  false-用户名不存在
	 */
	boolean hasUsername(String username);
	
	/**
	 * 注册用户的方法
	 * @param user 封装了用户信息的JavaBean
	 * @return true-注册成功 false-注册失败
	 */
	boolean registUser(User user);
	
	/**
	 * 用户登录的方法
	 * 返回的User对象中封装了用户的全部数据
	 * @param username 用户名
	 * @param password 密码
	 * @return null->登录失败  User对象->登录成功
	 * @throws MsgException 封装错误提示信息的异常对象
	 */
	User login(String username,String password) throws MsgException;

}
