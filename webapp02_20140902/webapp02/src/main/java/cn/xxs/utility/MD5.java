package cn.xxs.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {
	public static String md5(String str) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			if(str==null || str.length()==0)
				return "";
			md.update(str.getBytes());
			byte[] encodedPassword = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < encodedPassword.length; i++) {
				if ((encodedPassword[i] & 0xff) < 0x10) {
					sb.append("0");
				}
				sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5.md5("123456"));
		if (new MD5().md5("a") == new MD5().md5("b"))
			System.out.println("true");
		else
			System.out.println("false");
	}

}