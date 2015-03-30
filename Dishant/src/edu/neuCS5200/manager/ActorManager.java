package edu.neuCS5200.manager;

	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.entity.Actor;


public class ActorManager {
	DataSource ds;
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	public ActorManager(){

		try {

		Context ctx = new InitialContext();

		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Dishantassignment4");

		System.out.println(ds);

		} catch (NamingException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		}

		}
	
	private java.sql.Date userdate(java.util.Date dateOfBirth) {

		return (new java.sql.Date(dateOfBirth.getTime()));

	}
	
	public void createActor(Actor newActor) throws SQLException{
		Connection connection = null;
		String sql = "insert into actor values (?,?,?,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newActor.getId());
			statement.setString(2, newActor.getFirstName());
			statement.setString(3, newActor.getLastName());
			statement.setDate(4, userdate(newActor.getDateofBirth()));
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};	
	public List<Actor> readAllActors() throws SQLException{
		List<Actor> actors = new ArrayList<Actor>();
		Connection connection = null;
		String sql = "select * from actor";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		     
		    while (result.next()){
		    	Actor actor = new Actor();
		    	actor.setId(result.getString("id"));
		    	actor.setFirstName(result.getString("firstName"));
		    	actor.setLastName(result.getString("lastName"));
		    	actor.setDateofBirth(result.getDate("dateofBirth"));
		    	actors.add(actor);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return actors;
	};
	
	public Actor readActor(String actorId) throws SQLException{
		Actor actor = new Actor();
		Connection connection = null;
		String sql = "select * from actor where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		  	actor.setId(result.getString("id"));
		  	actor.setFirstName(result.getString("firstname"));
		  	actor.setLastName(result.getString("lastName"));
		  	actor.setDateofBirth(result.getDate("dateOfBirth"));
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return actor;
		
	};
	
	public void updateActor(String actorId, Actor actor) throws SQLException{
		Connection connection = null;
		String sql = "update actor set firstName = ?, lastName = ?, dateOfBirth where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate (3, (java.sql.Date)actor.getDateofBirth());
			statement.setString(4, actor.getId());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	};
	
	public void deleteActor(String actorId) throws SQLException{
		Connection connection = null;
		String sql = "delete from Actor where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	
}
	
	