package main;

public class Count {
	public static boolean mysqlInsertStart = true;
	private static int mysqlInsert = 0;
	public static boolean mysqlSelectStart = true;
	private static int mysqlSelect = 0;
	public static boolean psqlInsertStart = true;
	private static int psqlInsert = 0;
	public static boolean psqlSelectStart = true;
	private static int psqlSelect = 0;

	public static int getMysqlInsert() {
		return mysqlInsert;
	}

	synchronized public static void addMysqlInsert() {
		Count.mysqlInsert++;
	}

	public static int getMysqlSelect() {
		return mysqlSelect;
	}

	synchronized public static void addMysqlSelect() {
		Count.mysqlSelect++;
	}

	public static int getPsqlSelect() {
		return psqlSelect;
	}

	synchronized public static void addPsqlSelect() {
		Count.psqlSelect++;
	}

	public static int getPsqlInsert() {
		return psqlInsert;
	}

	synchronized public static void addPsqlInsert() {
		Count.psqlInsert++;
	}
}
