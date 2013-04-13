package com.shin.login.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Bicycle {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Key key;
	
	@Persistent
	private String bicycle;
	
	@Persistent
	private String geopoint;
	
	@Persistent
	/*
	 * 'a' : empty
	 * 'b' : used
	 * 'c' : reserved
	 */
	private char used;
	
	public Bicycle(String bicycle,String geopoint){
		this.bicycle=bicycle;
		this.geopoint = geopoint;
		setUsed('a');
	}
	
	public Key getKey(){
		return key;
	}
	public void setKey(Key key){
		this.key = key;
	}
	
	public String getBicycle(){
		return bicycle;
	}
	public void setBicycle(String bicycle){
		this.bicycle=bicycle;
	}
	
	public String getGeopoint(){
		return geopoint;
	}
	public void setGeopoint(String geopoint){
		this.geopoint = geopoint;
	}

	public char getUsed() {
		return used;
	}

	public void setUsed(char used) {
		this.used = used;
	}

}