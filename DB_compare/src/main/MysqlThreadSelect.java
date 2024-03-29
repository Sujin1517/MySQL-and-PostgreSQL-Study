package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlThreadSelect extends Thread {
	public void run() {
		while (Count.mysqlSelectStart) {}
		try {
			Connection c = DriverManager.getConnection(MySQLInit.url, MySQLInit.user, MySQLInit.passwd);
			for (int i = 0; i < 100; i++) {
				String sql = "select * from speed order by id desc limit 1";
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql);
				while (rs.next()) {
				}
			}
			c.close();
			Count.addMysqlSelect();
		} catch (Exception e) {
		}
	}
}
