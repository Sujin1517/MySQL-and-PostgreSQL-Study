package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgresThreadSelect extends Thread {
	public void run() {
		while (Count.psqlSelectStart) {}
		try {
			Connection c = DriverManager.getConnection(PostgresInit.url, PostgresInit.user, PostgresInit.passwd);
			Statement st = c.createStatement();
			for (int i = 0; i < 100; i++) {
				String sql = "select * from speed order by id desc limit 1";
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
				}
			}
			st.close();
			c.close();
			Count.addPsqlSelect();
		} catch (Exception e) {
		}
	}
}
