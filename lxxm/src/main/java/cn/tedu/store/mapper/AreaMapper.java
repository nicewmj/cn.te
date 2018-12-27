package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Area;

public interface AreaMapper {

	/**
	 * ��ȡĳ���е����������б�
	 * 
	 * @param cityCode
	 *            �еĴ���
	 * @return �����б�
	 */
	List<Area> getAreaListByCityCode(
			String cityCode);

	/**
	 * �������Ĵ��Ż�ȡ������Ϣ
	 * 
	 * @param areaCode
	 *            ���Ĵ���
	 * @return ������Ϣ
	 */
	Area getAreaByCode(String areaCode);

}
