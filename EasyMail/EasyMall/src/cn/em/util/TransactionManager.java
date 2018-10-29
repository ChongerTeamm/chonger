package cn.em.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	
	//单例
	//1.私有的构造器
	private TransactionManager(){}
	//2.私有的静态的本类的唯一实例
	private static TransactionManager tm=new TransactionManager();
	//3.公有的静态的返回本类唯一实例的方法
	public static TransactionManager getInstance(){
		return tm;
	}
	// ThreadLocal是操作线程用来保存本地变量的map集合的工具类
	// 每个tl代表了map集合中的一个key
	// 对操作者隐藏了线程操作的具体代码
	private ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	
	public void startTransaction(){
		// 每个线程获取一个属于自己的连接对象
		Connection conn=JDBCUtils.getConn();
		try {
			// 基于该连接对象开启事务
			conn.setAutoCommit(false);
			// 将conn保存到当前线程的map集合中，供该线程后续使用
			tl.set(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit(){
		// 从当前线程对象的map集合中获取之前保存的连接对象
		Connection conn=tl.get();
		if(conn!=null){
			try {
				// 使用该连接对象提交事务
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void rollback(){
		// 从当前线程对象的map集合中获取之前保存的连接对象
		Connection conn=tl.get();
		if(conn!=null){
			try {
				// 基于该连接对象回滚事务
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConn(){
		// 返回当前线程对象保存的conn对象
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
		// 从tl中删除连接对象
		tl.remove();
	}
	
	
	

}
