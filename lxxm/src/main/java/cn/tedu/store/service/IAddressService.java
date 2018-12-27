package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.InsertDataException;

public interface IAddressService {

	/**
	 * �����µ��ջ���ַ
	 * @param username ��ǰ��¼���û���
	 * @param address �ջ���ַ����
	 * @return �ɹ����ӵ��ջ���ַ���ݣ��Ұ������ݵ�id
	 * @throws InsertDataException
	 */
	Address add(String username, Address address) throws InsertDataException;
	
	/**
	 * ��ȡĳ���û����ջ���ַ�б�
	 * @param uid �û���id
	 * @return �û����ջ���ַ�б�
	 */
	List<Address> getAddressList(Integer uid);
	
	/**
	 * ��ȡĳ�û����ջ���ַ����
	 * @param uid �û���id
	 * @return �û����ջ���ַ����
	 */
	Integer getAddressCountByUid(Integer uid);
	
	/**
	 * �����ջ���ַ�Ƿ�ΪĬ��
	 * @param uid �û���id
	 * @param id ���޸ĵ����ݵ�id�����Ϊnull������û��������ջ���ַ��ȫ�����޸�
	 * @return ��Ӱ�������
	 */
	Integer setDefault(Integer uid, Integer id);
	
	/**
	 * ����id��ȡ�ջ���ַ��Ϣ
	 * @param id �ջ���ַid
	 * @return �ջ���ַ��Ϣ�����û��ƥ������ݣ��򷵻�null
	 */
	Address getAddressById(Integer id);
}
