//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.enterprise.interceptor;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurationManager {
	public static final String MAPPING_FILE = "/hibernate.cfg.xml";
	private static Properties props = new Properties();
	private static Log log = LogFactory.getLog(ConfigurationManager.class);

	public ConfigurationManager() {
	}

	private static int getSysConfigureAsInt(String key, int defaultValue) {
		String cfg = props.getProperty(key);
		return cfg == null ? defaultValue : Integer.parseInt(cfg.trim());
	}

	public static int getCacheTime() {
		return getSysConfigureAsInt("app.cacher.time", 60) * 1000;
	}

	public static int getPageSize() {
		return getSysConfigureAsInt("app.pager.pagesize", 10);
	}

	static {
		try {
			props.load(ConfigurationManager.class.getResourceAsStream("/appconfig.properties"));
		} catch (Exception var1) {
			log.error("装载系统配置文件【appconfig.properties】失败", var1);
		}

	}
}
