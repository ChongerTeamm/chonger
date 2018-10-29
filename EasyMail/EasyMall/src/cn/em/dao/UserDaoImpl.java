package cn.em.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.em.domain.User;
import cn.em.exception.MsgException;
import cn.em.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean getUserByUsername(String username) {
		String sql="select * from users where username=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		return false;
	}

	@Override
	public boolean saveUser(User user) {
		String sql="insert into users values(null,?,?,?,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JDBCUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getEmail());
			int i=ps.executeUpdate();
			if(i>0){// 插入成功
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
		return false;
	}

	@Override
	public User getUserByUaP(String username, String password) throws MsgException {
		String sql="select * from users where username=? and password=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try {
			conn=JDBCUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){//用户名和密码正确
				user=new User();
				//将查询到的数据封装到user对象中
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 说明查询时数据库出现异常
			// 方法可能会返回null，但是这个null不一定代表密码错误
			// 这种情况下，本方法应该给前面一个提示，
			// 但是返回值窗口已经被占用
			throw new MsgException("登录异常，请稍后重试");
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		return user;
	}

}
