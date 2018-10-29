package cn.em.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	// 创建c3p0的数据库连接池对象，
	// 该对象创建后，会默认在classpath搜索一个配置文件c3p0-config.xml
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	
	/**
	 * 通过数据库连接池获取连接的方法
	 * @return
	 */
	public static Connection getConn(){
		// 声明一个连接的引用
		Connection conn=null;
		try {
			// 通过数据库连接池对象，获取一个空闲的连接
			conn=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将获取到的连接返回给用户
		return conn;
	}
	
	/**
	 * 关闭数据库连接的方法
	 * @param conn 连接对象
	 * @param ps 预编译SQL执行器对象
	 * @param rs 结果集对象
	 */
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ps=null;
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
	}

}
