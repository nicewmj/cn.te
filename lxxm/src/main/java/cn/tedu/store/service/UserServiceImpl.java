package cn.tedu.store.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.util.Validator;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	public User reg(User user) throws UsernameConflictException {
		System.out.println("UserServiceImpl.reg() > start.");
		User data = findUserByUsername(user.getUsername());
		if (data == null) {
			String uuid = UUID.randomUUID().toString();
			String md5Password
				= getMd5Password(user.getPassword(), uuid);
			user.setPassword(md5Password);
			user.setUuid(uuid);
			
			Date now = new Date();
			user.setCreatedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
			
			userMapper.insert(user);
			System.out.println("UserServiceImpl.reg() > end.");
			return user;
		} else {
			System.out.println("UserServiceImpl.reg() > end.");
			throw new UsernameConflictException(
				"�û���(" + user.getUsername() + ")�Ѿ���ע�ᣡ");
		}
	}

	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		System.out.println("UserServiceImpl.login() > start.");
		// ���������findUserByUsername()��ѯ�û���ƥ�������
		User data = findUserByUsername(username);
		// �ж��Ƿ��ѯ��ƥ�������
		if (data != null) {
			// ���ڣ��û������ڣ��õ������û���ƥ�����������
			// �Ӳ�ѯ����������ȡ��uuid
			String salt = data.getUuid();
			// ����getMd5Password()��password��������
			String md5Password
				= getMd5Password(password, salt);
			// �ж������Ƿ�ƥ��
			if (data.getPassword().equals(md5Password)) {
				// ƥ�䣺��¼�ɹ������ز�ѯ����User����
				data.setPassword(null);
				data.setUuid(null);
				System.out.println("UserServiceImpl.login() > end.");
				return data;
			} else {
				// ��ƥ�䣺��������׳��쳣��PasswordNotMatchException
				System.out.println("UserServiceImpl.login() > end.");
				throw new PasswordNotMatchException("�������");
			}
		} else {
			// �����ڣ��û��������ڣ����û�������
			// �׳��쳣��UserNotFoundException
			System.out.println("UserServiceImpl.login() > end.");
			throw new UserNotFoundException("�û��������ڣ�");
		}
	}
	
	public Integer changePassword(Integer uid, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException {
		// ����uid��ȡ�û�����
		User data = findUserById(uid);
		// �ж��Ƿ��ȡ�����ݣ����û�У����׳��쳣��UserNotFoundException
		if (data == null) {
			throw new UserNotFoundException("���Բ������û����ݲ����ڣ�");
		}
		
		// ���û������л�ȡuuid
		String salt = data.getUuid();
		// ����getMd5Password(oldPassword, uuid)���û��ṩ��ԭ������м���
		String oldMd5Password = 
				getMd5Password(oldPassword, salt);
		// �жϼ��ܺ��ԭ���룬���û������е����룬�Ƿ���ȷ
		if (data.getPassword().equals(oldMd5Password)) {
			// ԭ������ȷ��
			// ����getMd5Password(newPassword, uuid)���û��ṩ����������м���
			String newMd5Password = 
					getMd5Password(newPassword, salt);
			// ִ���޸ģ����ó־ò�����Integer changePassword(9, newMd5Password)������ȡ����ֵ
			Integer rows = 
					userMapper.changePassword(
						uid, newMd5Password,
						data.getUsername(), new Date());
			// �жϷ���ֵ�Ƿ�Ϊ1
			if (rows == 1) {
				// ֵΪ1��������ȷ������1
				return 1;
			} else {
				// ֵ��Ϊ1������ʧ�ܣ��׳��쳣��UserNotFoundException
				throw new UserNotFoundException("���Բ������û����ݲ����ڣ�");
			}
		} else {
			// ԭ��������׳��쳣��PasswordNotMatchException
			throw new PasswordNotMatchException("�������");
		}
	}

	public Integer changeInfo(
			Integer uid, String avatar, 
			String username, Integer gender, 
			String phone, String email)
			throws UserNotFoundException {
		// ������ݵ���Ч��
		// ����û�����ʽ������Ϊnull
		if (!Validator.checkUsername(username)) {
			username = null;
		}
		// ���ԣ�����绰�����ʽ������Ϊnull��ͬ��
		// ���ԣ�������������ʽ������Ϊnull��ͬ��
		
		// ����޸��û��������û��������������û��Ѿ�ע���
		// �ж��û����Ƿ�Ϊnull��������Ϊnullʱִ�д�����
		if (username != null) {
			// ���������findUserByUsername()�����û�����ѯ
			User data = findUserByUsername(username);
			if (data == null) {
				// ���Ϊnull���û���û��ռ�ã�����ʹ�ã��û������ֲ��䣬�ȴ��������뽫����µ����ݱ���
			} else {
				// �����Ϊnull�������û����ҵ����ݣ������id����Ҫ�ж��ǲ��ǵ�ǰ�û�
				if (data.getId().equals(uid)) {
					// ���idƥ�䣺�û������Լ��ģ�û��Ҫ�޸ģ�����Ϊnull
					username = null;
				} else {
					// ���id��ƥ�䣺�û����Ǳ��ˣ��׳��쳣UsernameConflictException
					throw new UsernameConflictException(
							"�û���(" + username + ")�Ѿ���ע�ᣡ");
				}
			}
		}

		// ������������װ��User���͵Ķ�����
		User user = new User(
			uid, username, gender, phone, email, avatar);
		// ��װ��־����
		User data = findUserById(uid);
		user.setModifiedUser(data.getUsername());
		user.setModifiedTime(new Date());
		// ���ó־ò�����changeInfo(User)����������ȡ����ֵ
		Integer rows = userMapper.changeInfo(user);
		// �жϷ���ֵ�Ƿ�Ϊ��1
		if (rows == 1) {
			// ���Ϊ1������1
			return 1;
		} else {
			// �����Ϊ1���׳��쳣UserNotFoundException
			throw new UserNotFoundException("���Բ������û����ݲ����ڣ�");
		}
	}

	public User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}
	
	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	public String getMd5Password(
			String password, String salt) {
		// ----------------------------------------
		// -------- ���¼��ܹ�����������Ƶ� --------
		// ----------------------------------------
		// �Ȱ�ԭ����ʹ��MD5���ܣ����Ұ�����ת��д
		String str1 = DigestUtils.md5DigestAsHex(
				password.getBytes()).toUpperCase();
		// Ȼ��ѽ������ֵƴ��
		String str2 = str1 + salt.toUpperCase();
		// �ٴμ��ܣ��õ���������
		String str3 = DigestUtils.md5DigestAsHex(
				str2.getBytes()).toUpperCase();
		// ����
		return str3;
	}

}





