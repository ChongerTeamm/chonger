package cn.em.service;

import java.util.List;

import cn.em.domain.Prod;

public interface ProdService {
	
	/**
	 * �����Ʒ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ��JavaBean
	 * @return true-��ӳɹ� false-���ʧ��
	 */
	boolean addProd(Prod prod);

	/**
	 * ��ȡȫ����Ʒ��Ϣ�ķ���
	 * @return ��װ����Ʒ���ݵ�JavaBean�ļ��� �� null
	 */
	List<Prod> listAllProd();
	
	/**
	 * �޸���Ʒ�����ķ���
	 * @param pid ��Ʒid
	 * @param pnum �޸ĺ����Ʒ����
	 * @return true-�޸ĳɹ� false-�޸�ʧ��
	 */
	boolean changePnum(Integer pid, Integer pnum);

	/**
	 * ɾ����Ʒ�ķ���
	 * @param pid ��Ʒ��id
	 * @return true-ɾ���ɹ� false-ɾ��ʧ��
	 */
	boolean deleteProd(Integer pid);

}
