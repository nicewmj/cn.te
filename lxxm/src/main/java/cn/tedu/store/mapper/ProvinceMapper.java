package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Province;

public interface ProvinceMapper {

	/**
	 * ��ȡ����ʡ���б�
	 * 
	 * @return ʡ���б�
	 */
	List<Province> getProvinceList();

	/**
	 * ����ʡ�Ĵ��Ż�ȡʡ����Ϣ
	 * 
	 * @param provinceCode
	 *            ʡ�Ĵ���
	 * @return ʡ����Ϣ
	 */
	Province getProvinceByCode(String provinceCode);

}



