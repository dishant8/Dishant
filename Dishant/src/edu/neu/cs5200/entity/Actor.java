package edu.neu.cs5200.entity;

import java.util.Date;

public class Actor {
	private String id;
	private String firstName;
	private String lastName;
	private Date dateofBirth;

public Actor() {
	
}

public Actor (String id, String firstName, String lastName, Date dateofBirth) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateofBirth = dateofBirth;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public Date getDateofBirth() {
	return dateofBirth;
}

public void setDateofBirth(Date dateofBirth) {
	this.dateofBirth = dateofBirth;
}

}
