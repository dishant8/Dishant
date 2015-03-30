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

import edu.neu.cs5200.entity.Comment;

public class CommentManager {
	DataSource ds;
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	public CommentManager(){

		try {

		Context ctx = new InitialContext();

		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Dishantassignment4");

		System.out.println(ds);

		} catch (NamingException e) {

		
		e.printStackTrace();

		}

		}	
	private java.sql.Date userdate(java.util.Date dateOfBirth) {

		return (new java.sql.Date(dateOfBirth.getTime()));

	}
	
	public void createComment(Comment newComment) throws SQLException{
		Connection connection = null;
		String sql = "INSERT INTO COMMENT VALUES (?,?,?,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getId());
			statement.setString(2, newComment.getUsername());
			statement.setString(3, newComment.getComment());
			statement.setDate(4, userdate(newComment.getDate()));			 
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public List<Comment> readAllComment() throws SQLException{
		List<Comment> commentList = new ArrayList<Comment>();
		Connection connection = null;
		String sql = "SELECT * FROM COMMENT";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		     
		    while (result.next()){ 
		    	Comment commentdetails = new Comment();
		    	commentdetails.setId(result.getString("actorid"));
		    	commentdetails.setUsername(result.getString("id"));
		    	commentdetails.setComment(result.getString("characterName"));
		    	commentList.add(commentdetails);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return commentList;
	};
	
	public Comment readCast(String commentId) throws SQLException{
		Comment commentdetails = new Comment();
		Connection connection = null;
		String sql = "SELECT * FROM CAST WHERE ID = ?";
		try{ 
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet result = statement.executeQuery(sql);
		    commentdetails.setId(result.getString("id"));
		    commentdetails.setUsername(result.getString("username"));
		    commentdetails.setComment(result.getString("comment"));
		    
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return commentdetails;
		
	};
	public void updateComment(String commentId, Comment comment) throws SQLException{
		Connection connection = null;
		String sql = "UPDATE COMMENT SET USERNAME = ?, COMMENT = ? WHERE ID = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(3, comment.getId());
			statement.setString(1, comment.getUsername());
			statement.setString(2, comment.getId());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	};
	public void deleteComment(String commentId) throws SQLException{
		Connection connection = null;
		String sql = "DELETE FROM COMMENT WHERE ID=?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	
	public List<Comment> readAllCommentsForUsername(String username) throws SQLException{
        List<Comment> comments = new ArrayList<Comment>();
        Connection connection = null;
        String sql = "select * from comment where userid = ?";
        try{
                connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()){
                    Comment comment = new Comment();
                    comment.setComment(result.getString("comment")); 
                    comment.setId(result.getString("id"));
                    comment.setId(result.getString("username"));
                    comment.setDate(result.getDate("date"));
                    comments.add(comment);
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }finally{
                connection.close();
        }
        return comments;
};
public List<Comment> readAllCommentsForMovie(String movieId) throws SQLException{
        List<Comment> comments = new ArrayList<Comment>();
        Connection connection = null;
        String sql = "select * from comment where movieid = ?";
        try{
                connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, movieId);
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()){
                    Comment comment = new Comment();
                    comment.setComment(rs.getString("comment")); 
                    comment.setId(rs.getString("movieid"));
                    comment.setId(rs.getString("userid"));
                    comment.setDate(rs.getDate("date"));
                    comments.add(comment);
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }finally{
                connection.close();
        }
        return comments;
};
	
}
