package com.shin.login.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	
	public Encrypt(){

	}
	public static String encrypt(String strData) { // ��ȣȭ ��ų ������
		  try {
			  String strSRCData = "";
			  String strENCData = "";
			  MessageDigest md = MessageDigest.getInstance("MD5"); // "MD5 �������� ��ȣȭ"
			  byte[] bytData = strData.getBytes();  
			  md.update(bytData);
			  byte[] digest = md.digest();  //�迭�� ������ �Ѵ�.
			  for (int i = 0; i < digest.length; i++) {
				  strENCData = strENCData + Integer.toHexString(digest[i] & 0xFF).toUpperCase();
			  }
			  return strENCData;
		  } catch (NoSuchAlgorithmException e) {
			  System.out.print("��ȣȭ ����" + e.toString());
		  }
		  return strData;
		}
}
