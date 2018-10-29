package cn.em.dao;

import cn.em.domain.User;
import cn.em.exception.MsgException;

public interface UserDao {

	
	/**
	 * ͨ���û�����ѯ�û��ķ���
	 * @param username �û���
	 * @return true-�û����� false-�û�������
	 */
	boolean getUserByUsername(String username);

	/**
	 * ����û��ķ���
	 * @param user ��װ���û����ݵ�JavaBean
	 * @return true-��ӳɹ� false-���ʧ��
	 */
	boolean saveUser(User user);

	/**
	 * �����û����������ѯ�û����ݵķ���
	 * @param username �û���
	 * @param password ����
	 * @return null-���ݲ����ڣ�User����-��װ���û���Ϣ
	 * @throws MsgException ��װ������ʾ��Ϣ���쳣����
	 */
	User getUserByUaP(String username, String password) throws MsgException;
	
}

/* ��  insert/save   insertUser/savaUser
 * ɾ  delete			deleteUserById
 * ��  update			updateUserById
 * �� ��1��	get		getUserByUsername
 * 	  ����    list	listUserByAge
*/
