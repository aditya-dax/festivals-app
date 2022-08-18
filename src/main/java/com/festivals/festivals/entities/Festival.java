package com.festivals.festivals.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Festival {
	@Id
	private long id;
	
	@NotBlank(message = "Name should not be blank")
	private String name;
	
	@NotBlank(message = "Month should not be blank")
	@Size(min = 3, max = 9, message = "min 3 and max 9 characters are allowed!")
	private String month;
	
	public Festival(long id, String name, String month) {
		super();
		this.id = id;
		this.name = name;
		this.month = month;
	}

	public Festival() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Festival [id=" + id + ", name=" + name + ", month=" + month + "]";
	}
	
	
	
}


