/**
 * Project Name:***
 * File Name:PropertyReadUtil.java
 * Package Name:com.wenxing.util
 * Date:Jul 1, 20154:58:23 PM
*/

package com.gc.system.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ClassName:PropertyReadUtil <br/>
 * Date:     Jul 1, 2015 4:58:23 PM <br/>
 * @author   zhuangwentao
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class PropertyReadUtil {
	private static final Log log = LogFactory.getLog(PropertyReadUtil.class);
	
	/**
	 * 读取指定资源文件，指定key值
	 * @param propertiesName 资源文件名称
	 * @param keyName key值
	 * @return
	 */
	public static String getPropertiesByKeyName(String propertiesName,String keyName) {
		Properties p = loadStream(propertiesName);
		if(p==null){
			return null;
		}
		return p.getProperty(keyName);
	}
	
	/**
	 * 读取指定的资源文件当中的参数
	 * 
	 * @param propertiesName
	 *            要读取的资源文件名称
	 * @param params
	 *            要读取的KEY值
	 * @return 包含有已传入的key值为Map的key值的MAP <code>
	 * 	Map<String,String> map = PropertyReadUtil.readProperties("vehicle.properties","startDateX","endDateY");
	 *  map.get("startDateX");	
	 * </code>
	 */
	public static Map<String, String> readProperties(String propertiesName, Object... params) {
		PropertyReadUtil propertyReadUtil = new PropertyReadUtil();
		if (propertiesName == null) {
			propertiesName = "vehicle.properties";
		}

		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = propertyReadUtil.getClass().getClassLoader().getResourceAsStream(propertiesName);
		if (inputStream != null) {
			Properties p = new Properties();
			try {
				p.load(inputStream);
			} catch (IOException e1) {
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			for (Object o : params) {
				map.put(o.toString(), p.getProperty(o.toString()));
			}
		}

		return map;
	}
	// public static void main(String[] args){
	// PropertyReadUtil.readProperties("vehicle.properties","startDateX","endDateY");
	// }
	
	private static Properties loadStream(String propertiesName) {
		Properties p = null;
		InputStream inputStream = PropertyReadUtil.class.getClassLoader().getResourceAsStream(propertiesName);
		if (inputStream != null) {
			try {
				p = new Properties();
				p.load(inputStream);
			} catch (IOException e1) {
				log.error("读取资源文件时发生异常："+e1);
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error("读取资源文件时发生异常："+e);
				}
			}
		}else{
			log.error("读取资源文件时发生异常，未找到对应的资源文件名："+propertiesName);
		}
		return p;
	}
}

