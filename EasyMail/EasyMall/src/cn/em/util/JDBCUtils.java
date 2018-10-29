package cn.em.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	// ����c3p0�����ݿ����ӳض���
	// �ö��󴴽��󣬻�Ĭ����classpath����һ�������ļ�c3p0-config.xml
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	
	/**
	 * ͨ�����ݿ����ӳػ�ȡ���ӵķ���
	 * @return
	 */
	public static Connection getConn(){
		// ����һ�����ӵ�����
		Connection conn=null;
		try {
			// ͨ�����ݿ����ӳض��󣬻�ȡһ�����е�����
			conn=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����ȡ�������ӷ��ظ��û�
		return conn;
	}
	
	/**
	 * �ر����ݿ����ӵķ���
	 * @param conn ���Ӷ���
	 * @param ps Ԥ����SQLִ��������
	 * @param rs ���������
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
