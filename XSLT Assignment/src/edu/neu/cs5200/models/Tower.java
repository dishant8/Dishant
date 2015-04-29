package edu.neu.cs5200.models;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Tower {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private int height;
	@XmlAttribute
	private int sides;
	@OneToMany(mappedBy="tower", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="equipment")
	private List<Equipment> equipment;
	@ManyToOne
	@JoinColumn(name="siteId")
	@XmlTransient
	private Site site;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSides() {
		return sides;
	}
	public void setSides(int sides) {
		this.sides = sides;
	}
	public List<Equipment> getEquipment() {
		return equipment;
	}
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public Tower(int id, int height, int sides, List<Equipment> equipment,
			Site site) {
		super();
		this.id = id;
		this.height = height;
		this.sides = sides;
		this.equipment = equipment;
		this.site = site;
	}
	public Tower() {
		super();
	}
	
}