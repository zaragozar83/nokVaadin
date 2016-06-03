package com.coffeenok.nok.domain;

public class User {

	private int id;
	private String name;
	private String authority;
	
	public User(){
		
	}

	public User(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public User(int id, String name, String authority){
		this.id = id;
		this.name = name;
		this.authority = authority;
	}
	
	public User(String name, String authority){
		this.name = name;
		this.authority = authority;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
