package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressMapper {

	/**
	 * �����ջ���ַ
	 * @param address �ջ���ַ����
	 * @return ��Ӱ�������
	 */
	Integer insert(Address address);
	
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
	 * @param isDefault �Ƿ�Ĭ�ϣ�0��ʾ��Ĭ��  1��ʾĬ�ϣ�
	 * @return ��Ӱ�������
	 */
	Integer setDefault(
			@Param("uid") Integer uid, 
			@Param("id") Integer id, 
			@Param("isDefault") Integer isDefault);
	
	/**
	 * ����id��ȡ�ջ���ַ��Ϣ
	 * @param id �ջ���ַid
	 * @return �ջ���ַ��Ϣ�����û��ƥ������ݣ��򷵻�null
	 */
	Address getAddressById(Integer id);

}





