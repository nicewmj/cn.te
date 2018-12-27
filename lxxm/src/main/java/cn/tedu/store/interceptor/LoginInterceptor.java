package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor 
	implements HandlerInterceptor {

	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		System.out.println("LoginInterceptor.preHandle()");
		System.out.println("\trequest=" + request);
		System.out.println("\tresponse=" + response);
		System.out.println("\thandler=" + handler);
		
		// ��¼��������Ŀ�꣺
		// �����¼�ˣ���������
		// ���û��¼�����أ��ض��򵽵�¼ҳ
		
		// �ж��Ƿ��¼
		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			// Session��û�е�¼���û���Ϣ
			// �����û�û�е�¼
			// ���ض��򵽵�¼ҳ
			response.sendRedirect("../user/login.do");
			// ����
			return false;
		} else {
			// Session�д��ڵ�¼���û���Ϣ
			// �����û��Ѿ���¼
			// �򣺷���
			return true;
		}
	}

	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) 
			throws Exception {
		System.out.println("LoginInterceptor.postHandle()");
		System.out.println("\trequest=" + request);
		System.out.println("\tresponse=" + response);
		System.out.println("\thandler=" + handler);
		System.out.println("\tmodelAndView=" + modelAndView);
	}

	public void afterCompletion(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			Exception ex)
			throws Exception {
		System.out.println("LoginInterceptor.afterCompletion()");
		System.out.println("\trequest=" + request);
		System.out.println("\tresponse=" + response);
		System.out.println("\thandler=" + handler);
		System.out.println("\tex=" + ex);
	}

}
