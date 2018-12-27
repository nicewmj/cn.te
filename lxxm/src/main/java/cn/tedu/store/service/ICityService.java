package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.City;

public interface ICityService {

	/**
	 * ��ȡĳ��ʡ�������е��б�
	 * 
	 * @param provinceCode
	 *            ʡ�Ĵ���
	 * @return �е��б�
	 */
	List<City> getCityListByProvinceCode(
			String provinceCode);

	/**
	 * �����еĴ��Ż�ȡ�е���Ϣ
	 * 
	 * @param provinceCode
	 *            �еĴ���
	 * @return �е���Ϣ
	 */
	City getCityByCode(String cityCode);
	
}
