package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PostgresThreadInsert extends Thread {
	public void run() {
		while (Count.psqlInsertStart) {}
		try {
			Connection c = DriverManager.getConnection(PostgresInit.url, PostgresInit.user, PostgresInit.passwd);
			Statement st = c.createStatement();
			for (int i = 0; i < 100; i++) {
				c.setAutoCommit(false);
				String sql = "insert into speed(data1, data2, data3, data4) values ('123', '123', '123', '123')";
				st.executeUpdate(sql);
				c.commit();
			}
			st.close();
			c.close();
			Count.addPsqlInsert();
		} catch (Exception e) {
		}
	}
}
