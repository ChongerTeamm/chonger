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
		// ����һ��flag����Ϊ�����ķ���ֵ
		boolean flag1=false;
		try {
			// ��������
			TransactionManager.getInstance().startTransaction();
			
			//1. ��ʹ��cname��prod_category�����Ƿ�������
			ProdCategory pc=null;
			try {
				pc=prodDao.getPCByCname(prod.getCname());
			} catch (MsgException e) {
				e.printStackTrace();
				// ��������false��֪ͨController�����Ʒʧ��
				return false;
			}
			//  1)û�����ݣ�����prod_category�������һ������
			if(pc==null){
				// ����һ��ProdCategory�Ķ��󣬷�װ�����ݿ��в������Ϣ
				ProdCategory pc2=new ProdCategory(-1, prod.getCname());
				boolean flag2=prodDao.insertProdCategory(pc2);
				if(!flag2){
					// ��Ʒ�������ʧ�ܣ�����ƷҲ�޷��������
					return false;
				}
				//�ٽ���һ�β�ѯ����ȡcid
				try {
					pc=prodDao.getPCByCname(prod.getCname());
				} catch (MsgException e) {
					e.printStackTrace();
				}
			}
			//	2)�����ݣ�����cid
			
			// int i=10/0;
			
			//2. ʹ��cid��prod��ֵ
			prod.setCid(pc.getId());
			//3. �����Ʒ��Ϣ��prod����
			flag1=prodDao.insertProd(prod);
			
			// �ύ����
			TransactionManager.getInstance().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// ����ع�
			TransactionManager.getInstance().rollback();
		}finally{
			// �ر�����
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
		// ��������ִ�н����flag
		boolean flag=false;
		//���������ǰ��Ʒ�Ǹ���Ʒ�����Ψһ��Ʒ����
		// ɾ����Ʒ��ͬʱɾ������Ʒ����
		// �������Ψһ��Ʒ�����ɾ������Ʒ
		
		try {
			// ��������
			TransactionManager.getInstance().startTransaction();
			
			// 1. ������Ʒid����ѯ��Ʒ�����cid
			int cid=prodDao.getCidByPid(pid);
			if(cid==-1){
				// û���ҵ���Ʒ�������ݣ��޷�ִ��ɾ��
				return false;
			}
			// 2. ������Ʒcid����ѯ����Ʒ�����Ӧ����Ʒ������
			int count=prodDao.getCountByCid(cid);
			if(count>1){
				// 3. �����Ʒ�����Ӧ����������1����ɾ������Ʒ
				flag=prodDao.deleteProdByPid(pid);
			}else if(count==1){
				// 3. �����Ʒ�����Ӧ����������1��ɾ����Ʒ��ɾ����Ʒ����
				flag=prodDao.deleteProdByPid(pid);
				flag=prodDao.detetePCByCid(cid)&&flag;
			}
			
			// �ύ����
			TransactionManager.getInstance().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// �ع�����
			TransactionManager.getInstance().rollback();
		}finally{
			TransactionManager.getInstance().closeConn();
		}
		

		return flag;
	}

}
