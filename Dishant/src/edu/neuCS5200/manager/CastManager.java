package edu.neuCS5200.manager;

	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.entity.Cast;

public class CastManager {
	DataSource ds;
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	public CastManager(){

		try {

		Context ctx = new InitialContext();

		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Dishantassignment4");

		System.out.println(ds);

		} catch (NamingException e) {

		

		e.printStackTrace();

		}

		}
	public void createCast(Cast newCast) throws SQLException{
		Connection connection = null;
		String sql = "insert into cast values (?,?,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getActorid());
			statement.setString(2, newCast.getId());
			statement.setString(3, newCast.getCharacterName());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public List<Cast> readAllCast() throws SQLException{
		List<Cast> castList = new ArrayList<Cast>();
		Connection connection = null;
		String sql = "select * from movie";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		     
		    while (result.next()){
		    	Cast castdetails = new Cast();
		    	castdetails.setActorid(result.getString("actorid"));
		    	castdetails.setId(result.getString("id"));
		    	castdetails.setCharacterName(result.getString("characterName"));
		    	castList.add(castdetails);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return castList;
	};
	
	public Cast readCast(String castId) throws SQLException{
		Cast castdetails = new Cast();
		Connection connection = null;
		String sql = "select * from cast where id = ?";
		try{ 
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		    castdetails.setActorid(result.getString("actorid"));
		    castdetails.setId(result.getString("id"));
		    castdetails.setCharacterName(result.getString("characterName"));
		    
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return castdetails;
		
	};
	public void updateCast(String castId, Cast cast) throws SQLException{
		Connection connection = null;
		String sql = "update cast set actorid = ?, characterName = ?, releaseDate = ? where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cast.getActorid());
			statement.setString(2, cast.getCharacterName());
			statement.setString(3, cast.getId());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	};
	public void deleteCast(String castId) throws SQLException{
		Connection connection = null;
		String sql = "delete from Cast where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	
	public List<Cast> readAllCastForUsername(String username) throws SQLException{
        List<Cast> ListOfCasts = new ArrayList<Cast>();
        Connection connection = null;
        String sql = "select * from comment where userid = ?";
        try{
                connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()){
                    Cast cast = new Cast();
                    cast.setActorid(result.getString("actorid")); 
                    cast.setId(result.getString("id"));
                    cast.setCharacterName(result.getString("characterName"));
                    ListOfCasts.add(cast);
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }finally{
                connection.close();
        }
        return ListOfCasts;
};
public List<Cast> readAllCastsForMovie(String movieId) throws SQLException{
        List<Cast> ListOfCasts = new ArrayList<Cast>();
        Connection connection = null;
        String sql = "select * from comment where movieid = ?";
        try{
                connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, movieId);
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()){
                    Cast cast = new Cast();
                    cast.setActorid(result.getString("actorid")); 
                    cast.setId(result.getString("id"));
                    cast.setCharacterName (result.getString("characterName"));
                    ListOfCasts.add(cast);
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }finally{
                connection.close();
        }
        return ListOfCasts;
};

}
