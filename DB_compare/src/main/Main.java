package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;

public class Main {

	public static void main(String[] args) throws SQLException {
		Connection c = null;
		String sql = null;
		PreparedStatement ps  = null;
		Statement st = null;
		int rs = 0;
		Instant time = null;
		
		
		System.out.print("DB Table 초기화중... ");
		c = DriverManager.getConnection(MySQLInit.url, MySQLInit.user, MySQLInit.passwd);
		sql = "delete from speed where 1=1";
		ps = c.prepareStatement(sql);
		rs = ps.executeUpdate();
		c.close();
		System.out.println("완료");
		
		
		
		System.out.println("\nMySQL - 40 Thread, 100 INSERT");
		System.out.print("Thead 생성중... ");
		for (int i = 0; i < 40; i++) {
			MysqlThreadInsert mysql = new MysqlThreadInsert();
			mysql.start();
		}
		System.out.println("완료");
		time = Instant.now();
		Count.mysqlInsertStart = false;
		System.out.println("INSERT 시작...");
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			if (Count.getMysqlInsert() > 39) break;
		}
		System.out.println("완료. 소모시간: " + (Duration.between(time, Instant.now()).toMillis()) + "ms");

		
		
		System.out.println("\nMySQL - 100 Thread, 100 SELECT");
		System.out.print("Thead 생성중... ");
		for (int i = 0; i < 100; i++) {
			MysqlThreadSelect mysql = new MysqlThreadSelect();
			mysql.start();
		}
		System.out.println("완료");
		time = Instant.now();
		Count.mysqlSelectStart = false;
		System.out.println("SELECT 시작...");
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			if (Count.getMysqlSelect() > 99) break;
		}
		System.out.println("완료. 소모시간: " + (Duration.between(time, Instant.now()).toMillis()) + "ms");
		
		
		
		
		
		c = null;
		System.out.print("\n\nDB Table 초기화중... ");
		c = DriverManager.getConnection(PostgresInit.url, PostgresInit.user, PostgresInit.passwd);
		sql = "delete from speed where 1=1";
		st = c.createStatement();
		st.executeUpdate(sql);
		st.close();
		c.close();
		System.out.println("완료");
		
		
		
		System.out.println("\nPostgreSQL - 40 Thread, 100 INSERT");
		System.out.print("Thead 생성중... ");
		for (int i = 0; i < 40; i++) {
			PostgresThreadInsert psql1 = new PostgresThreadInsert();
			psql1.start();
		}
		System.out.println("완료");
		time = Instant.now();
		Count.psqlInsertStart = false;
		System.out.println("INSERT 시작...");
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			if (Count.getPsqlInsert() > 39) break;
		}
		System.out.println("완료. 소모시간: " + (Duration.between(time, Instant.now()).toMillis()) + "ms");

		
		
		System.out.println("\nPostgreSQL - 100 Thread, 100 SELECT");
		System.out.print("Thead 생성중... ");
		for (int i = 0; i < 100; i++) {
			PostgresThreadSelect psql2 = new PostgresThreadSelect();
			psql2.start();
		}
		System.out.println("완료");
		time = Instant.now();
		Count.psqlSelectStart = false;
		System.out.println("SELECT 시작...");
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			if (Count.getPsqlSelect() > 99) break;
		}
		System.out.println("완료. 소모시간: " + (Duration.between(time, Instant.now()).toMillis()) + "ms");
		
	}

}
