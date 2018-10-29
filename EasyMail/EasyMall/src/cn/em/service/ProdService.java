package cn.em.service;

import java.util.List;

import cn.em.domain.Prod;

public interface ProdService {
	
	/**
	 * 添加商品的方法
	 * @param prod 封装了商品信息的JavaBean
	 * @return true-添加成功 false-添加失败
	 */
	boolean addProd(Prod prod);

	/**
	 * 获取全部商品信息的方法
	 * @return 封装了商品数据的JavaBean的集合 或 null
	 */
	List<Prod> listAllProd();
	
	/**
	 * 修改商品数量的方法
	 * @param pid 商品id
	 * @param pnum 修改后的商品数量
	 * @return true-修改成功 false-修改失败
	 */
	boolean changePnum(Integer pid, Integer pnum);

	/**
	 * 删除商品的方法
	 * @param pid 商品的id
	 * @return true-删除成功 false-删除失败
	 */
	boolean deleteProd(Integer pid);

}
