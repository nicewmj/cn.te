package cn.tedu.store.util;

/**
 * ��֤����������֤�ַ����ĸ�ʽ�Ƿ���ȷ
 */
public final class Validator {
	
	private Validator() {
		super();
	}
	
	/**
	 * ��֤�û�����������ʽ
	 */
	public static final String REGEX_USERNAME 
		= "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}$";
	
	/**
	 * ��֤�����������ʽ
	 */
	public static final String REGEX_PASSWORD 
		= "^\\d{6,18}$";
	
	/**
	 * ��֤�û���
	 * @param username ��Ҫ����֤��ʽ���û���
	 * @return ������ϸ�ʽҪ���򷵻�true�����򷵻�false
	 */
	public static boolean checkUsername(String username) {
		return username.matches(REGEX_USERNAME);
	}
	
	/**
	 * ��֤����
	 * @param password ��Ҫ����֤��ʽ������
	 * @return ������ϸ�ʽҪ���򷵻�true�����򷵻�false
	 */
	public static boolean checkPassword(String password) {
		return password.matches(REGEX_PASSWORD);
	}
	
}
