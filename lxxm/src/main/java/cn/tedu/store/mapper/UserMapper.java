package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
	
	/**
	 * �����û�����
	 * @param user �û����ݶ���
	 * @return ��Ӱ���������������ӳɹ����򷵻�1�����򣬷���0
	 */
	Integer insert(User user);

	/**
	 * �����û�����ѯ�û�����
	 * @param username �û���
	 * @return ������û���ƥ����û����ݣ����û��ƥ������ݣ��򷵻�null
	 */
	User findUserByUsername(String username);
	
	/**
	 * ����id��ѯ�û�����
	 * @param id �û�id
	 * @return ������û�idƥ����û����ݣ����û��ƥ������ݣ��򷵻�null
	 */
	User findUserById(Integer id);

	/**
	 * �޸�����
	 * @param uid �û�id
	 * @param password ������
	 * @return ��Ӱ�����������ȷ��������·���1�����򣬷���0
	 */
	Integer changePassword(
	    @Param("uid") Integer uid, 
	    @Param("password") String password,
	    @Param("modifiedUser") String modifiedUser,
	    @Param("modifiedTime") Date modifiedTime);

	/**
	 * �޸ĸ�����Ϣ
	 * @param user ��װ�˱��޸ĵ��û�id����ѡ�������û�������ѡ�������Ա𣨿�ѡ�������ֻ��ţ���ѡ�����µ����ʼ�����ѡ���Ķ���
	 * @return ��Ӱ�����������ȷ��������·���1�����򣬷���0
	 */
	Integer changeInfo(User user);
	
}
