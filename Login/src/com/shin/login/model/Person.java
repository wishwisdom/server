package com.shin.login.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable(detachable="true")
public class Person {
	 @PrimaryKey
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	 private Key key;
	 
	 @Persistent
	 private String id;
	 
	 @Persistent
	 private String password;
	 
	 @Persistent
	 private String gmail;
	 
	 @Persistent
	 private String name;
	 
	 @Persistent
	 private String birth;
	 
	 @Persistent
	 private String phone;
	 
	 public Person(){
		 id="";
		 password="";
		 gmail="";
	 }
	 
	 public Person(String id, String password,String email,String name, String birth, String phone){
		 this.id=id;
		 this.password=password;
		 this.gmail=email;
		 this.phone = phone;
		 this.setBirth(birth);
		 this.name = name;
	 }

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

}
