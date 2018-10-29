package cn.em.dao;

import java.sql.Connection;
import java.util.List;

import cn.em.domain.Prod;
import cn.em.domain.ProdCategory;
import cn.em.exception.MsgException;

public interface ProdDao {
	
	/**
	 * ������Ʒ�������Ʋ�ѯ��Ʒ����ķ���
	 * @param cname ��Ʒ��������
	 * @return ��װ����Ʒ������Ϣ��JavaBean �� null
	 * @throws ��װ�˴�����Ϣ���쳣����
	 */
	ProdCategory getPCByCname(String cname) throws MsgException;
	
	/**
	 * �����ݿ������Ʒ����ķ���
	 * @param pc ��װ����Ʒ������Ϣ��JavaBean
	 * @return true-��ӳɹ� false-���ʧ��
	 */
	boolean insertProdCategory(ProdCategory pc);

	/**
	 * �����Ʒ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ��JavaBean
	 * @return true-��ӳɹ�  false-���ʧ��
	 */
	boolean insertProd(Prod prod);

	/**
	 * ��ѯȫ����Ʒ��Ϣ�ķ���
	 * @return ��װ����Ʒ���ݵ�JavaBean�ļ��� �� null
	 */
	List<Prod> listAllProd();

	/**
	 * ������Ʒ�����ķ���
	 * @param pid ��Ʒid
	 * @param pnum ��Ʒ������
	 * @return true-���³ɹ� false-����ʧ��
	 */
	boolean updatePnumByPid(Integer pid, Integer pnum);

	/**
	 * ������Ʒid��ѯ��Ӧ����Ʒ����id�ķ���
	 * @param pid ��Ʒ��id
	 * @return ��Ʒ����id �� -1
	 */
	int getCidByPid(Integer pid);

	/**
	 * ������Ʒ����id����ѯ����Ʒ�����Ӧ����Ʒ������
	 * @param cid ��Ʒ�����id
	 * @return ��Ʒ������
	 */
	int getCountByCid(int cid);

	/**
	 * ɾ����Ʒ���ݵķ���
	 * @param pid ��Ʒ��id
	 * @return true-ɾ���ɹ�  false-ɾ��ʧ��
	 */
	boolean deleteProdByPid(Integer pid);

	/**
	 * ɾ����Ʒ����ķ���
	 * @param cid ��Ʒ�����id
	 * @return true-ɾ���ɹ� false-ɾ��ʧ��
	 */
	boolean detetePCByCid(int cid);
}
