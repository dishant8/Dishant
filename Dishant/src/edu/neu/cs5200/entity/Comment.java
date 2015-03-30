package edu.neu.cs5200.entity;

import java.util.Date;

public class Comment {
	private String id;
	private String username;
	private String comment;
	private Date date;
	

public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

public Comment(){
	
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public Comment (String id, String username, String comment, Date date) {
	this.id = id;
	this.username = username;
	this.comment = comment;
	this.date = date;
	
	
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

}

