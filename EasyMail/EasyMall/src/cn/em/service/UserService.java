package cn.em.service;

import cn.em.domain.User;
import cn.em.exception.MsgException;

public interface UserService {
	
	/**
	 * �����ж��û����Ƿ���ڵķ���
	 * @param username �û���
	 * @return true-�û�������  false-�û���������
	 */
	boolean hasUsername(String username);
	
	/**
	 * ע���û��ķ���
	 * @param user ��װ���û���Ϣ��JavaBean
	 * @return true-ע��ɹ� false-ע��ʧ��
	 */
	boolean registUser(User user);
	
	/**
	 * �û���¼�ķ���
	 * ���ص�User�����з�װ���û���ȫ������
	 * @param username �û���
	 * @param password ����
	 * @return null->��¼ʧ��  User����->��¼�ɹ�
	 * @throws MsgException ��װ������ʾ��Ϣ���쳣����
	 */
	User login(String username,String password) throws MsgException;

}
