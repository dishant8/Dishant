package edu.neu.cs5200.entity;

public class Cast {
	private String actorid;
	private String id;
	private String characterName;

public Cast (){	

}

public String getActorid() {
	return actorid;
}

public void setActorid(String actorid) {
	this.actorid = actorid;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public Cast (String actorid, String id, String characterName)
{
	this.actorid = actorid;
	this.id = id;
	this.characterName = characterName; 
}

public String getCharacterName() {
	return characterName;
}

public void setCharacterName(String characterName) {
	this.characterName = characterName;
}
 
}