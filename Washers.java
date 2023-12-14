package com.washer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection ="Washerdb")
public class Washers {
	
	@Id
	private int id;
	private String name;
	private String location;
	private String password;
	private String email;
	private int contanct;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getContanct() {
		return contanct;
	}

	public void setContanct(int contanct) {
		this.contanct = contanct;
	}

	
	public Washers() {
		super();
	}
	
	public Washers(int id, String name, String location, String password, String email ,int contanct ) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.password = password;
		this.email = email;
		this.contanct=contanct;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Washers [id=" + id + ", name=" + name + ", location=" + location + ", password=" + password + ",email="+email+",contanct="+contanct+"]";
	}

	

	

}
