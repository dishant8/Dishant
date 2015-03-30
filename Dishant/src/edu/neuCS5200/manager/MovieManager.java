package edu.neuCS5200.manager;

	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.entity.Movie;

public class MovieManager {
	DataSource ds;
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	public MovieManager(){

		try {

		Context ctx = new InitialContext();

		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Dishantassignment4");

		System.out.println(ds);

		} catch (NamingException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		}

		}
	public void createMovie(Movie newMovie) throws SQLException{
		Connection connection = null;
		String sql = "insert into movie values (?,?,?,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMovie.getId());
			statement.setDate(4, date);
			statement.setString(3, newMovie.getPosterImage());
			statement.setString(2, newMovie.getTitle());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public List<Movie> readAllMovies() throws SQLException{
		List<Movie> ListOfMovies = new ArrayList<Movie>();
		Connection connection = null;
		String sql = "select * from movie";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		     
		    while (result.next()){
		    	Movie moviedesc = new Movie();
		    	moviedesc.setId(result.getString("id"));
		    	moviedesc.setPosterImage(result.getString("posterImage"));
		    	moviedesc.setReleaseDate(result.getDate("releaseDate"));
		    	moviedesc.setTitle(result.getString("title"));
		    	ListOfMovies.add(moviedesc);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return ListOfMovies;
	};
	public Movie readMovie(String movieId) throws SQLException{
		Movie moviedetails = new Movie();
		Connection connection = null;
		String sql = "select * from movie where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		    moviedetails.setId(result.getString("id"));
		    moviedetails.setPosterImage(result.getString("posterImage"));
		    moviedetails.setReleaseDate(result.getDate("releaseDate"));
		    moviedetails.setTitle(result.getString("title"));
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return moviedetails;
		
	};
	public void updateMovie(String movieId, Movie movie) throws SQLException{
		Connection connection = null;
		String sql = "update Movie set title = ?, posterImage = ?, releaseDate = ? where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, movie.getId());
			statement.setString(2, movie.getPosterImage());
			statement.setDate (3, (java.sql.Date)movie.getReleaseDate());
			statement.setString(1, movie.getTitle());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	};
	public void deleteMovie(String movieId) throws SQLException{
		Connection connection = null;
		String sql = "delete from Movie where id = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	
	public static void main(String[] args) {
		MovieManager manager = new MovieManager();
		Movie movie = new Movie();
		List<Movie> movies = new ArrayList<Movie>();
		movie.setId("130");
		movie.setTitle("batman");
		movie.setReleaseDate(new Date());
		movie.setPosterImage("ThrillerMovie");
		try {
			manager.createMovie(movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			movies = manager.readAllMovies();
			for(int j = 0; j < movies.size(); j++){
				System.out.println(movies.get(j).getId());
				System.out.println(movies.get(j).getTitle());
				System.out.println(movies.get(j).getPosterImage());
				System.out.println(movies.get(j).getReleaseDate());
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}