package Kiosk;

import java.sql.Connection;

public class ex {
	public static int stan_number = 1;
	public  static String db_url = "jdbc:mysql://113.198.233.244:13306/java_kiosk";
	public static String db_user = "root";
	public static String db_pw = "1306";
	public static int number = 0;

	
	public static void main(String[] args) {
		new OrderCom_Screen();
	}
}