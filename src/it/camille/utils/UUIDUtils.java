package it.camille.utils;

import java.util.UUID;

/**
 * 获取随机码类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 16:44:12
 */
public class UUIDUtils {

	/** 工具类私有构造方法 */
	private UUIDUtils() {}
	
	/**
	 * 获取随机码方法
	 * 
	 * @return String 生成的UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
