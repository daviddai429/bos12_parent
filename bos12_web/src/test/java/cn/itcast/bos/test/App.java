package cn.itcast.bos.test;

import org.apache.commons.lang3.StringUtils;

import cn.itcast.bos.utils.PinYin4jUtils;

public class App {

	public static void main(String[] args) {
		String province = "湖南省";
		String city = "长沙市";
		String district = "高雄区";
		province = province.substring(0,province.length()-1);
		city = city.substring(0,city.length()-1);
		district = district.substring(0,district.length()-1);
		
		String[] headByString = PinYin4jUtils.getHeadByString(province+city+district);
		System.out.println(StringUtils.join(headByString, ""));
		
		
		String hanziToPinyin = PinYin4jUtils.hanziToPinyin(city,"");
		System.out.println(hanziToPinyin);
		String shortcode = "";//简码  HNCSGX
		String citycode = "";//城市编码 changsha
	}

}
