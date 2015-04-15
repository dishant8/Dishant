package edu.neu.cs5200.assn4.orm.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Tower {
	@Id
	private Integer id;
	private Integer height;
	private Integer sides;
	private Integer siteId;
	
	 @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="id")
		private Site site;
		@OneToMany(mappedBy= "tower")
		private List<Equipment> equipments;
		
	public Tower() {
		super();
	}
	public Tower(Integer id, String name, Integer height, Integer sides,
			Integer siteId) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.sides = sides;
		this.siteId = siteId;
	}
	
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getSides() {
		return sides;
	}
	public void setSides(Integer sides) {
		this.sides = sides;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}
