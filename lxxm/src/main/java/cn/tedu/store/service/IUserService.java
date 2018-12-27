package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;

public interface IUserService {
	
	/**
	 * �û�ע�� 
	 * @param user ע����û����ݶ���
	 * @return �ɹ�ע����û����ݶ��󣬰����û���id
	 * @throws UsernameConflictException �û����Ѿ���ռ��
	 */
	User reg(User user) 
		throws UsernameConflictException;
	
	/**
	 * ��¼
	 * @param username �û���
	 * @param password ����
	 * @return ��¼�ɹ����û�����
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String password) 
		throws UserNotFoundException, 
				PasswordNotMatchException;
	
	/**
	 * �޸�����
	 * @param uid �û�id
	 * @param oldPassword ԭ����
	 * @param newPassword ������
	 * @return ��Ӱ�������
	 * @return UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	Integer changePassword(
		Integer uid, String oldPassword, String newPassword) 
			throws UserNotFoundException,
				PasswordNotMatchException;
	
	/**
	 * �޸��û�������Ϣ
	 * @param uid �û�id
	 * @param avatar ��ͷ��·����������޸ģ���ʹ��nullֵ
	 * @param username ���û�����������޸ģ���ʹ��nullֵ
	 * @param gender ���Ա�������޸ģ���ʹ��nullֵ
	 * @param phone ���ֻ����룬������޸ģ���ʹ��nullֵ
	 * @param email �µ������䣬������޸ģ���ʹ��nullֵ
	 * @return ��Ӱ�������
	 * @throws UserNotFoundException
	 * @throws UsernameConflictException
	 */
	Integer changeInfo(
			Integer uid, 
			String avatar,
			String username, 
			Integer gender, 
			String phone, 
			String email) throws UserNotFoundException,
					UsernameConflictException;
	
	/**
	 * �����û�����ѯ�û�����
	 * @param username �û���
	 * @return ������û���ƥ����û����ݣ����û��ƥ������ݣ��򷵻�null
	 */
	User findUserByUsername(String username);

	/**
	 * �����û�id��ѯ�û�����
	 * @param id �û�id
	 * @return ������û�idƥ����û����ݣ����û��ƥ������ݣ��򷵻�null
	 */
	User findUserById(Integer id);
	
	/**
	 * ��ȡ���ܺ������
	 * @param password ԭ����
	 * @param salt ��
	 * @return ���ܺ������
	 */
	String getMd5Password(
			String password, String salt);

}




