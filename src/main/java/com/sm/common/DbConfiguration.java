package com.sm.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfiguration {
	public Properties getDbProperties() {
		Properties properties = new Properties();
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
