package cn.em.dao;

import java.sql.Connection;
import java.util.List;

import cn.em.domain.Prod;
import cn.em.domain.ProdCategory;
import cn.em.exception.MsgException;

public interface ProdDao {
	
	/**
	 * 根据商品种类名称查询商品种类的方法
	 * @param cname 商品种类名称
	 * @return 封装了商品种类信息的JavaBean 或 null
	 * @throws 封装了错误信息的异常对象
	 */
	ProdCategory getPCByCname(String cname) throws MsgException;
	
	/**
	 * 向数据库添加商品种类的方法
	 * @param pc 封装了商品种类信息的JavaBean
	 * @return true-添加成功 false-添加失败
	 */
	boolean insertProdCategory(ProdCategory pc);

	/**
	 * 添加商品的方法
	 * @param prod 封装了商品信息的JavaBean
	 * @return true-添加成功  false-添加失败
	 */
	boolean insertProd(Prod prod);

	/**
	 * 查询全部商品信息的方法
	 * @return 封装了商品数据的JavaBean的集合 或 null
	 */
	List<Prod> listAllProd();

	/**
	 * 更新商品数量的方法
	 * @param pid 商品id
	 * @param pnum 商品的数量
	 * @return true-更新成功 false-更新失败
	 */
	boolean updatePnumByPid(Integer pid, Integer pnum);

	/**
	 * 根据商品id查询对应的商品种类id的方法
	 * @param pid 商品的id
	 * @return 商品种类id 或 -1
	 */
	int getCidByPid(Integer pid);

	/**
	 * 根据商品种类id，查询该商品种类对应的商品的数量
	 * @param cid 商品种类的id
	 * @return 商品的数量
	 */
	int getCountByCid(int cid);

	/**
	 * 删除商品数据的方法
	 * @param pid 商品的id
	 * @return true-删除成功  false-删除失败
	 */
	boolean deleteProdByPid(Integer pid);

	/**
	 * 删除商品种类的方法
	 * @param cid 商品种类的id
	 * @return true-删除成功 false-删除失败
	 */
	boolean detetePCByCid(int cid);
}
