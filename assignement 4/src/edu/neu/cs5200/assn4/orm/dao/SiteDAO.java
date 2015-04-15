package edu.neu.cs5200.assn4.orm.dao;

import java.util.List; 

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.assn4.orm.models.Site;

@Path("/api")
public class SiteDAO {	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("assignement 4");
	EntityManager em = factory.createEntityManager();
	
	@GET
	@Path("/site")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	private List<Site> findAllSites()
	{
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
	}
	
			
	@GET
	@Path("/site/(Id)")
	@Produces(MediaType.APPLICATION_JSON)
	
	private Site findSite(@PathParam("id") int siteid)
	{
		Site site = null;		
		Query query = em.createQuery("select site from Site site");
		query.setParameter("id", siteid);
		site = (Site) query.getSingleResult();
		return site;
	}
	
	
	@POST
	@Path("/site")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	private List<Site> createsite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
	}
	
	
	
	// updateSite
	@PUT
	@Path("/site")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	private List<Site> updateSite(@QueryParam("id") int siteId, Site site)
	{
		em.getTransaction().begin();
		site.setId(siteId);
		em.merge(site);
		em.getTransaction().commit();
		
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
	}
	
	
	//deleteSite
	@DELETE
	@Path("/site")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	private List<Site> removeSite(@QueryParam("id") int id)
	{
		Query query = em.createQuery("select site from Site site");
		query.setParameter("id", id);
		Site site = (Site) query.getSingleResult();
		em.getTransaction().begin();
		em.remove(site);
		em.getTransaction().commit();
		Query query1 = em.createQuery("select site from Site site");
		return (List<Site>)query1.getResultList();
				
	}

	
		
}
