package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

/**
 * ��Ŀ�����п�������Ļ���
 * @author chengheng
 */
public abstract class BaseController {

	/**
	 * ��Session�л�ȡ�û���id
	 * @param session HttpSession����
	 * @return �û���id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(
				session.getAttribute("uid").toString());
					
	}
	
}
