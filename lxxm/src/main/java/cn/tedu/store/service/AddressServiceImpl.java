package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.InsertDataException;

@Service("addressService")
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private IProvinceService provinceService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IAreaService areaService;

	public Address add(String username, Address address) {
		// ���address�е�uid��recvName�Ƿ�Ϊnull
		// ������ǰ�ջ���ַ�Ƿ�ΪĬ�ϣ�
		// ������û���ǰû���κ��ջ���ַ���˴μ������ӵĽ��ǵ�1��������Ĭ�ϣ�
		// ���򣬲���Ĭ��
		// address.setIsDefault(getAddressCountByUid(address.getUid()) == 0 ? 1 : 0);
		if (getAddressCountByUid(address.getUid()) == 0) {
			address.setIsDefault(1);
		} else {
			address.setIsDefault(0);
		}
		// ��װʡ����������������recvDistrict
		String recvDistrict = getRecvDistrict(
				address.getRecvProvince(),
				address.getRecvCity(),
				address.getRecvArea());
		address.setRecvDistrict(recvDistrict);
		// ��װ��־��Ϣ
		Date now = new Date();
		address.setCreatedUser(username);
		address.setModifiedUser(username);
		address.setCreatedTime(now);
		address.setModifiedTime(now);
		// ִ������
		Integer rows = addressMapper.insert(address);
		// �жϳ־ò�����ķ���ֵ
		if (rows == 1) {
			// ���ӳɹ�������
			return address;
		} else {
			// ���ӳ����׳��쳣
			throw new InsertDataException("�����ջ���ַ��Ϣʧ�ܣ�" + address);
		}
	}

	/**
	 * ��ȡʡ��������������
	 * @param recvProvince ʡ�Ĵ���
	 * @param recvCity �еĴ���
	 * @param recvArea ���Ĵ���
	 * @return ʡ�������������ơ�
	 */
	private String getRecvDistrict(
			String recvProvince, 
			String recvCity, 
			String recvArea) {
		String provinceName
			= provinceService
				.getProvinceByCode(recvProvince)
				.getName();
		String cityName
			= cityService.getCityByCode(recvCity).getName();
		String areaName
			= areaService.getAreaByCode(recvArea).getName();
		String district
			= provinceName + "," + cityName + "," + areaName;
		return district;
	}

	public List<Address> getAddressList(Integer uid) {
		return addressMapper.getAddressList(uid);
	}

	public Integer getAddressCountByUid(Integer uid) {
		return addressMapper.getAddressCountByUid(uid);
	}

	@Transactional
	public Integer setDefault(Integer uid, Integer id) {
		// �Ƚ����û��������ջ���ַ����Ϊ��Ĭ��
		addressMapper.setDefault(uid, null, 0);
		// �ٽ�ָ�����ջ���ַ����ΪĬ��
		Integer rows 
			= addressMapper.setDefault(uid, id, 1);
		// �жϲ����Ƿ�ɹ�
		if (rows == 1) {
			return 2;
		} else {
			throw new DataNotFoundException("���Խ�id=" + id + "��������ΪĬ���ջ���ַ������ʧ�ܣ�");
		}
	}

	public Address getAddressById(Integer id) {
		return addressMapper.getAddressById(id);
	}

}






