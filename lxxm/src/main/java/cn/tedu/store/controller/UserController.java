package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.util.Validator;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * �ϴ���ͷ������ߴ磬��λ��KB
	 */
	public static final long MAX_AVATAR_SIZE = 50;
	
	@RequestMapping("/reg.do")
	public String showReg() {
		return "user_reg";
	}
	
	@RequestMapping("/login.do")
	public String showLogin() {
		return "user_login";
	}
	
	@RequestMapping("/change_password.do")
	public String showChangePassword() {
	    return "user_change_password";
	}
	
	@RequestMapping("/change_info.do")
	public String showChangeInfo(
			HttpSession session,
			ModelMap modelMap) {
		// ��ȡ��ǰ��¼���û���id
		Integer uid 
			= getUidFromSession(session);
		// ��ѯ��ǰ��¼���û�������
		User user 
			= userService.findUserById(uid);
		// ���û����ݷ�װ����׼��ת��
		modelMap.addAttribute("user", user);
		// ִ��ת��
		return "user_change_info";
	}

	@RequestMapping(value="/handle_reg.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleReg(
		@RequestParam("username") String username, 
		@RequestParam("password") String password, 
		String email, 
		String phone, 
		Integer gender) {
		// ��������ֵ
		ResponseResult<Void> rr;
		
		// ��֤���ݵ���Ч�ԣ���������ʽ,������ϸ�ʽҪ���򷵻�true�����򷵻�false
		boolean result = Validator.checkUsername(username);
		if (!result) {
			rr = new ResponseResult<Void>(
					ResponseResult.STATE_ERR, 
					"��������û�������");
			return rr;
		}
		result = Validator.checkPassword(password);
		if (!result) {
			rr = new ResponseResult<Void>(
					ResponseResult.STATE_ERR, 
					"������������ʽ����");
			return rr;
		}
		
		try {
			// �Ѹ�������װ��User������
			User user = new User(
				username, password, gender, phone, email);
			// ����ҵ���Ķ����reg()����ʵ��ע��
			userService.reg(user);
			// ��װ���ؽ��
			rr = new ResponseResult<Void>(
					ResponseResult.STATE_OK);
		} catch(UsernameConflictException e) {
			// ��װ���ؽ��
			rr = new ResponseResult<Void>(e);
		}
		return rr;
	}
	
	@RequestMapping(value="/handle_login.do", 
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(
		@RequestParam("username") String username,
		@RequestParam("password") String password,
		HttpSession session) {
		// ��������ֵ
		ResponseResult<Void> rr;
		
		// ���ԣ�������ݵ���Ч��

		try {
			// ִ�е�¼
			User user
				= userService.login(username, password);
			// ��session�з�װuid��username
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			// ���أ��ɹ�
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UserNotFoundException e) {
			// �û���������
			rr = new ResponseResult<Void>(-1, e.getMessage());
		} catch (PasswordNotMatchException e) {
			// ���벻����
			rr = new ResponseResult<Void>(-2, e.getMessage());
		}

		// ִ�з���
		return rr;
	}
	
	@RequestMapping(value="/handle_change_password.do",
		method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(
		@RequestParam("old_password") String oldPassword,
		@RequestParam("new_password") String newPassword,
		HttpSession session) {
		// ��������ֵ
		ResponseResult<Void> rr;

		try {
			// ͨ��session��ȡuid
			Integer uid 
				= getUidFromSession(session);
			// ����ҵ�������changePassword(uid, oldPassword, newPassword)����
			userService.changePassword(uid, oldPassword, newPassword);
			// ����rr���󣬱�ʾ�ɹ�
			rr = new ResponseResult<Void>(
					ResponseResult.STATE_OK);
		} catch (UserNotFoundException e) {
			// ����rr����-1��e.getMessage()
			rr = new ResponseResult<Void>(
					-1, e.getMessage());
		} catch (PasswordNotMatchException e) {
			rr = new ResponseResult<Void>(
					-2, e.getMessage());
		}

		// ִ�з���
		return rr;
	}
	
	@RequestMapping(value="/handle_change_info.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> handleChangeInfo(
		MultipartHttpServletRequest	request,
		MultipartFile avatar,
		String username,
		Integer gender,
		String phone,
		String email,
		HttpSession session) {
		// ����ResponseResult<Void> rr����ֵ
		ResponseResult<String> rr;
		// �û��ϴ���ͷ���ڷ������˵�·��
		String avatarPath = null;
		// ��session�л�ȡuid
		Integer uid 
			= getUidFromSession(session);
		
		// ======== �ϴ�ͷ�� ========
		// �ж��û��Ƿ�ѡ�����ϴ��µ�ͷ��
		if(!avatar.isEmpty()) {
			// ��֤�ļ�����
			String contentType = avatar.getContentType();
			if (!"image/png".equals(contentType)
					&& !"image/jpeg".equals(contentType)
						&& !"image/bmp".equals(contentType)) {
				rr = new ResponseResult<String>();
				rr.setState(ResponseResult.STATE_ERR); 
				rr.setMessage("��֧���ϴ�" + contentType + "���͵��ļ���");
				return rr;
			}
			// ��֤�ļ���С
			long size = avatar.getSize();
			if (size > MAX_AVATAR_SIZE * 1024) {
				rr = new ResponseResult<String>(
					ResponseResult.STATE_ERR, 
					"�ϴ���ͷ���ļ���������" 
							+ MAX_AVATAR_SIZE + "KB��");
				return rr;
			}
			
			// ����ͷ����ļ��У������û�ͷ��������ļ�����
			String avatarDirPath
				= request.getServletContext()
					.getRealPath("/upload/image");
			File avatarDir = new File(avatarDirPath);
			System.out.println("�����ԡ�avatarDirPath=" + avatarDirPath);
		
			// �������ͷ����ļ��в����ڣ��򴴽�
			// if (!avatarDir.exists()) {
			//	avatarDir.mkdirs();
			// }
	
			// ��ȡ�ͻ��ϴ���ԭʼ���ļ����ļ���
			String originalFilename 
				= avatar.getOriginalFilename();
			// ��ȡ�ͻ��ϴ���ԭʼ���ļ�����չ��
			String suffix 
				= originalFilename.substring(
					originalFilename.lastIndexOf("."));
			
			// ͷ���ļ����ļ�����ÿ���û���ͷ���ļ�����Ӧ�ò���ͬ
			Date date = new Date();
			String filename 
				= getDateString(date) + uid + suffix; // 20181009094512.png
			
			// Ŀ���ļ������ڷ���˱�����û�ͷ���ļ�
			File dest = new File(avatarDir, filename);
			
			try {
				// ���û��ϴ���ͷ�����ݱ��浽�ļ�
				avatar.transferTo(dest);
				// ȷ���û�ͷ���ڷ������˵�·��
				avatarPath = "upload/image/" + filename;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// ======== ִ���޸ĸ������� ========
		try {
		    // ����userService�е�changeInfo()����
			userService.changeInfo(
				uid, 
				avatarPath, 
				username, gender, phone, email);
		    // �����ɹ�ʱ��ResponseResult
			rr = new ResponseResult<String>(
				ResponseResult.STATE_OK);
			// ��ͷ��·����װ�ڷ��ض����data������
			rr.setData(avatarPath);
		} catch (UserNotFoundException e) {
		    // ����ʧ�ܣ��û������ڣ�ʱ��ResponseResult
			rr = new ResponseResult<String>(-1, e.getMessage());
		} catch (UsernameConflictException e) {
		    // -- ����ʧ�ܣ��û�����ͻ��ʱ��ResponseResult
			rr = new ResponseResult<String>(-2, e.getMessage());
		}
		
		// ִ�з���
		return rr;
	}
	
	private final String pattern = "yyyyMMddHHmmss";
	
	private final SimpleDateFormat sdf
		= new SimpleDateFormat(
			pattern, Locale.CHINA);
	
	private String getDateString(Date date) {
		return sdf.format(date);
	}
	
}









