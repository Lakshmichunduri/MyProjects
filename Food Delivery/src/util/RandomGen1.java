package util;

import java.util.Random;

public class RandomGen1 {
	
	public static String getRandomOTP(String bookingId) {
		int num = Integer.parseInt(bookingId);
		int otp = num%9999;
		return Integer.toString(otp);
	}
}
