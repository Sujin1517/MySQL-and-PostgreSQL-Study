package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MysqlThreadInsert extends Thread {
	public void run() {
		while (Count.mysqlInsertStart) {}
		try {
			Connection c = DriverManager.getConnection(MySQLInit.url, MySQLInit.user, MySQLInit.passwd);
			for (int i = 0; i < 100; i++) {
				c.setAutoCommit(false);

				String sql = "insert into speed values (null, '123', '123', '123', '123')";
				PreparedStatement ps = c.prepareStatement(sql);
				int rs = ps.executeUpdate();
				c.commit();
			}
			c.close();
			Count.addMysqlInsert();
		} catch (Exception e) {
		}
	}
}
