package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Province;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IProvinceService;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.InsertDataException;

@Controller
@RequestMapping("/address")
public class AddressController 
	extends BaseController {
	
	@Autowired
	private IAddressService addressService;
	@Autowired
	private IProvinceService provinceService;
	
	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap,
			HttpSession session) {
		// ��ȡ���е�ʡ���б�
		List<Province> provinces
			= provinceService.getProvinceList();
		// ��װ���е�ʡ���б���ת��
		modelMap.addAttribute("provinces", provinces);
		
		// ��ȡ��ǰ��¼���û���id
		Integer uid = getUidFromSession(session);
		// ��ȡ��ǰ��¼���û����ջ���ַ�б�
		List<Address> addresses
			= addressService.getAddressList(uid);
		// ��װ�ջ���ַ�б���ת��
		modelMap.addAttribute("addresses", addresses);
		
		// ִ��ת��
		return "address_list";
	}
	
	@RequestMapping(value="/handle_add.do",
		method=RequestMethod.POST)
	public String handleAdd(Address address,
			HttpSession session, ModelMap modelMap) {
		// ���ԣ�������ݵ���Ч��
		// ��Session�л�ȡuid
		Integer uid = getUidFromSession(session);
		// ��Session�л�ȡusername
		String username 
			= session.getAttribute("username")
				.toString();
		// ��uid��װ��address��
		address.setUid(uid);
		
		try {
			// ����ҵ�������add(Address address)����ִ������
			addressService.add(username, address);
			// ���ӳɹ������ض���
			return "redirect:list.do";
		} catch(InsertDataException e) {
			// ��װ������Ϣ
			modelMap.addAttribute(
				"message", e.getMessage());
			// ת������ʾ�����ҳ��
			return "error";
		}
	}
	
	@RequestMapping("/set_default.do")
	public String handleSetDefault(
		@RequestParam("id") Integer id,
		HttpSession session, ModelMap modelMap) {
		// ��session�л�ȡuid
		Integer uid = getUidFromSession(session);
		
		try {
			// ����ҵ�������setDefault(uid, id)����
			addressService.setDefault(uid, id);
			// �ض���list.do
			return "redirect:list.do";
		} catch(DataNotFoundException e) {
			// ��������Ϣ��װ
			modelMap.addAttribute("message", e.getMessage());
			// ת����error
			return "error";
		}
	}

}









