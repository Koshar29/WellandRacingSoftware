package com.wcmc.network;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	public static String md5(String plainText){
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plainText.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}
}
