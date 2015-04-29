package edu.neu.cs5200.models;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SiteList {
	@XmlElement(name="site")
	private List<Site> sitelist;

	public List<Site> getSitelist() {
		return sitelist;
	}

	public void setSitelist(List<Site> sitelist) {
		this.sitelist = sitelist;
	}

	public SiteList(List<Site> sitelist) {
		super();
		this.sitelist = sitelist;
	}

	public SiteList() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}