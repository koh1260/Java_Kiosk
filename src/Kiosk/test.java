package Kiosk;

import java.text.SimpleDateFormat;

public class test {

	public static void main(String[] args) {
		java.util.Date nowDate = new  java.util.Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(nowDate);
		
		System.out.print(date);
	}

}
