package cn.em.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	
	//����
	//1.˽�еĹ�����
	private TransactionManager(){}
	//2.˽�еľ�̬�ı����Ψһʵ��
	private static TransactionManager tm=new TransactionManager();
	//3.���еľ�̬�ķ��ر���Ψһʵ���ķ���
	public static TransactionManager getInstance(){
		return tm;
	}
	// ThreadLocal�ǲ����߳��������汾�ر�����map���ϵĹ�����
	// ÿ��tl������map�����е�һ��key
	// �Բ������������̲߳����ľ������
	private ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	
	public void startTransaction(){
		// ÿ���̻߳�ȡһ�������Լ������Ӷ���
		Connection conn=JDBCUtils.getConn();
		try {
			// ���ڸ����Ӷ���������
			conn.setAutoCommit(false);
			// ��conn���浽��ǰ�̵߳�map�����У������̺߳���ʹ��
			tl.set(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit(){
		// �ӵ�ǰ�̶߳����map�����л�ȡ֮ǰ��������Ӷ���
		Connection conn=tl.get();
		if(conn!=null){
			try {
				// ʹ�ø����Ӷ����ύ����
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void rollback(){
		// �ӵ�ǰ�̶߳����map�����л�ȡ֮ǰ��������Ӷ���
		Connection conn=tl.get();
		if(conn!=null){
			try {
				// ���ڸ����Ӷ���ع�����
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConn(){
		// ���ص�ǰ�̶߳��󱣴��conn����
		return tl.get();
	}
	
	public void closeConn(){
		Connection conn=tl.get();
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ��tl��ɾ�����Ӷ���
		tl.remove();
	}
	
	
	

}
