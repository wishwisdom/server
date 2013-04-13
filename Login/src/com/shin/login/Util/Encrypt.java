package com.shin.login.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	
	public Encrypt(){

	}
	public static String encrypt(String strData) { // 암호화 시킬 데이터
		  try {
			  String strSRCData = "";
			  String strENCData = "";
			  MessageDigest md = MessageDigest.getInstance("MD5"); // "MD5 형식으로 암호화"
			  byte[] bytData = strData.getBytes();  
			  md.update(bytData);
			  byte[] digest = md.digest();  //배열로 저장을 한다.
			  for (int i = 0; i < digest.length; i++) {
				  strENCData = strENCData + Integer.toHexString(digest[i] & 0xFF).toUpperCase();
			  }
			  return strENCData;
		  } catch (NoSuchAlgorithmException e) {
			  System.out.print("암호화 에러" + e.toString());
		  }
		  return strData;
		}
}
