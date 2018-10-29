package cn.em.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.em.dao.ProdDao;
import cn.em.domain.Prod;
import cn.em.domain.ProdCategory;
import cn.em.exception.MsgException;
import cn.em.factory.BaseFactory;
import cn.em.util.JDBCUtils;
import cn.em.util.TransactionManager;

public class ProdServiceImpl implements ProdService {
	
	private ProdDao prodDao=BaseFactory.getFactory().getInstance(ProdDao.class);

	@Override
	public boolean addProd(Prod prod) {
		// 声明一个flag，作为方法的返回值
		boolean flag1=false;
		try {
			// 开启事务
			TransactionManager.getInstance().startTransaction();
			
			//1. 先使用cname查prod_category表，查是否有数据
			ProdCategory pc=null;
			try {
				pc=prodDao.getPCByCname(prod.getCname());
			} catch (MsgException e) {
				e.printStackTrace();
				// 方法返回false，通知Controller添加商品失败
				return false;
			}
			//  1)没有数据，先在prod_category表中添加一行数据
			if(pc==null){
				// 创建一个ProdCategory的对象，封装向数据库中插入的信息
				ProdCategory pc2=new ProdCategory(-1, prod.getCname());
				boolean flag2=prodDao.insertProdCategory(pc2);
				if(!flag2){
					// 商品种类添加失败，则商品也无法继续添加
					return false;
				}
				//再进行一次查询，获取cid
				try {
					pc=prodDao.getPCByCname(prod.getCname());
				} catch (MsgException e) {
					e.printStackTrace();
				}
			}
			//	2)有数据，返回cid
			
			// int i=10/0;
			
			//2. 使用cid给prod赋值
			prod.setCid(pc.getId());
			//3. 添加商品信息到prod表中
			flag1=prodDao.insertProd(prod);
			
			// 提交事务
			TransactionManager.getInstance().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 事务回滚
			TransactionManager.getInstance().rollback();
		}finally{
			// 关闭连接
			TransactionManager.getInstance().closeConn();
		}
		
		return flag1;
	}

	@Override
	public List<Prod> listAllProd() {
		return prodDao.listAllProd();
	}

	@Override
	public boolean changePnum(Integer pid, Integer pnum) {
		return prodDao.updatePnumByPid(pid,pnum);
	}

	@Override
	public boolean deleteProd(Integer pid) {
		// 表明方法执行结果的flag
		boolean flag=false;
		//需求：如果当前商品是该商品种类的唯一商品，则
		// 删除商品的同时删除该商品种类
		// 如果不是唯一商品，则仅删除该商品
		
		try {
			// 开启事务
			TransactionManager.getInstance().startTransaction();
			
			// 1. 根据商品id，查询商品种类的cid
			int cid=prodDao.getCidByPid(pid);
			if(cid==-1){
				// 没有找到商品种类数据，无法执行删除
				return false;
			}
			// 2. 根据商品cid，查询该商品种类对应的商品的数量
			int count=prodDao.getCountByCid(cid);
			if(count>1){
				// 3. 如果商品种类对应的数量大于1，仅删除该商品
				flag=prodDao.deleteProdByPid(pid);
			}else if(count==1){
				// 3. 如果商品种类对应的数量等于1，删除商品，删除商品种类
				flag=prodDao.deleteProdByPid(pid);
				flag=prodDao.detetePCByCid(cid)&&flag;
			}
			
			// 提交事务
			TransactionManager.getInstance().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			TransactionManager.getInstance().rollback();
		}finally{
			TransactionManager.getInstance().closeConn();
		}
		

		return flag;
	}

}
