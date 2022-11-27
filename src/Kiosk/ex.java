package Kiosk;

import java.sql.Connection;

public class ex {
	
	public  static String db_url = "jdbc:mysql://localhost:*****/java_kiosk";
	public static String db_user = "root";
	public static String db_pw = "****";

	
	public static void main(String[] args) {
		new User_Screen();
	}
}