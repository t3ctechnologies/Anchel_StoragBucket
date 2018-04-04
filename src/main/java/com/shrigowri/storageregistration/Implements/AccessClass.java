package com.shrigowri.storageregistration.Implements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.shrigowri.common.DbConfiguration;

public class AccessClass {

	Properties properties = null;
	String url = null;
	String user = null;
	String password = null;

	public AccessClass() {
		properties = new DbConfiguration().getDbProperties();
		url = properties.getProperty("com.sftp.s3.url");
		user = properties.getProperty("com.sftp.s3.username");
		password = properties.getProperty("com.sftp.s3.password");
	}

	public void insert(String fileName, long specialKey, String s3url) throws SQLException {

		try {
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			String query = " insert into sftpstoragebucketmapping (fileName, specialKey, s3fileurl)"
					+ " values (?, ?, ?)";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, fileName);
			preparedStmt.setLong(2, specialKey);
			preparedStmt.setString(3, s3url);

			preparedStmt.execute();

			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String TakeSpecialId(String file) {
		String splId = null;
		try {
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			String query = " select specialKey from sftpstoragebucketmapping where fileName =?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setNString(1, file);

			ResultSet rs = preparedStmt.executeQuery();
			rs.next();
			splId = rs.getString(1);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return splId;
	}

}
