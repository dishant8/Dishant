package edu.neu.cs5200.JPA.dao;

import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import edu.neu.cs5200.models.Site;
import edu.neu.cs5200.models.SiteList;



public class SiteDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("XSLT Assignment");
	EntityManager em = null;
	
	public Site findsite(int siteId) {
		Site site = null;
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		site = em.find(Site.class, siteId);
		
		em.getTransaction().commit();
		em.close();
		
		return site;
	}
	
	public List<Site> findAllSites() {
		List<Site> sites = new ArrayList<Site>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("findAllSites");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();

		return sites;
	}
	
	public void exportSitesToXmlFile(SiteList sites, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		
		try {
			//FileWriter writer = new FileWriter(xmlFile);
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sites, xmlFile);
		} catch (JAXBException e) {
			
			e.printStackTrace();
		} 
	}
	
	public void convertXmlFileToOutputFile(
			String sitesXmlFileName,
			String outputFileName,
			String xsltFileName)
	{
		File inputXmlFile = new File(sitesXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
		
		
//		SiteList site = dao.findSite(1);
		
		List<Site> sites = dao.findAllSites();
		for(Site s:sites) {
			System.out.println(s.getName());
		}
		
		SiteList theSiteList = new SiteList();
		theSiteList.setSitelist(sites);
		
		dao.exportSitesToXmlFile(theSiteList, "xml/sites.xml");
		
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
	}
	
	
}