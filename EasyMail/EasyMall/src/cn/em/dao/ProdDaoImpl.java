package cn.em.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.em.domain.Prod;
import cn.em.domain.ProdCategory;
import cn.em.exception.MsgException;
import cn.em.util.JDBCUtils;
import cn.em.util.TransactionManager;

public class ProdDaoImpl implements ProdDao {

	@Override
	public ProdCategory getPCByCname(String cname) throws MsgException {
		String sql="select * from product_category where cname=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//conn=JDBCUtils.getConn();
			conn=TransactionManager.getInstance().getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs=ps.executeQuery();
			if(rs.next()){
				//如果查询到数据，则封装成pc对象,返回给Service
				ProdCategory pc=new ProdCategory();
				pc.setId(rs.getInt("id"));
				pc.setCname(rs.getString("cname"));
				return pc;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MsgException("添加商品种类出现异常");
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
		return null;
	}

	@Override
	public boolean insertProdCategory(ProdCategory pc) {
		String sql="insert into product_category values(null,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//conn=JDBCUtils.getConn();
			// 获取当前线程开启事务时使用的连接对象
			conn=TransactionManager.getInstance().getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, pc.getCname());
			int i=ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public boolean insertProd(Prod prod) {
		String sql="insert into product values(null,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//conn=JDBCUtils.getConn();
			conn=TransactionManager.getInstance().getConn();
			ps=conn.prepareStatement(sql);
			// String double  int  int String String
			ps.setString(1, prod.getName());
			ps.setDouble(2, prod.getPrice());
			ps.setInt(3, prod.getCid());
			ps.setInt(4, prod.getPnum());
			ps.setString(5, prod.getImgurl());
			ps.setString(6, prod.getDescription());
			int i=ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public List<Prod> listAllProd() {
		// 不只需要查询商品的信息，还需要查询商品种类的名称
		String sql="select p.*,c.cname from prod p join product_category c on p.cid=c.id";
		List<Prod> list=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=JDBCUtils.getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			list=new ArrayList<Prod>();
			while(rs.next()){
				Prod prod=new Prod();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setCname(rs.getString("cname"));
				prod.setCid(rs.getInt("cid"));
				prod.setPrice(rs.getDouble("price"));
				prod.setPnum(rs.getInt("pnum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				list.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public boolean updatePnumByPid(Integer pid, Integer pnum) {
		String sql="update product set pnum=? where id=?";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JDBCUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pnum);
			ps.setInt(2, pid);
			int i=ps.executeUpdate();
			if(i>0){
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
	public int getCidByPid(Integer pid) {
		String sql="select cid from product where id=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=TransactionManager.getInstance().getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pid);
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getInt("cid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
		return -1;
	}

	@Override
	public int getCountByCid(int cid) {
		// 添加for update，使用的是悲观锁解决方案来解决更新丢失
		String sql="select count(*) from product where cid=? for update";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=TransactionManager.getInstance().getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cid);
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
		return 0;
	}

	@Override
	public boolean deleteProdByPid(Integer pid) {
		String sql="delete from product where id=?";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=TransactionManager.getInstance().getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pid);
			int id=ps.executeUpdate();
			if(id>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public boolean detetePCByCid(int cid) {
		String sql="delete from product_category where id=?";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=TransactionManager.getInstance().getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cid);
			int id=ps.executeUpdate();
			if(id>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

}
