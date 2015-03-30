package edu.neuCS5200.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;






import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.entity.User;

public class UserManager {
	DataSource ds;
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());

	public UserManager(){

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

	public void createUser(User newUser) throws SQLException {
		Connection connection = null;
		String sql = "insert into user values (?,?,?,?,?,?)";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			statement.setDate(6, userdate(newUser.getDateofBirth()));
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	};

	public List<User> readAllUsers() throws SQLException {
		List<User> Users = new ArrayList<User>();
		Connection connection = null;
		String sql = "select * from user";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				User user = new User();
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setFirstName(result.getString("FirstName"));
				user.setLastName(result.getString("LastName"));
				user.setEmail(result.getString("email"));
				user.setDateofBirth(result.getDate("dateOfBirth"));
				Users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return Users;
	};

	public User readUser(String userId) throws SQLException {
		User user = new User();
		Connection connection = null;
		String sql = "select * from user where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);
			user.setUsername(result.getString("username"));
			user.setPassword(result.getString("password"));
			user.setFirstName(result.getString("FirstName"));
			user.setLastName(result.getString("LastName"));
			user.setEmail(result.getString("email"));
			user.setDateofBirth(result.getDate("dateOfBirth"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return user;

	};

	public void updateUser(String userId, User user) throws SQLException {
		Connection connection = null;
		String sql = "update User set password = ?, firstName = ?, lastName = ?, email = ?, dateOfBirth=? where username = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setDate(5, (java.sql.Date) user.getDateofBirth());
			statement.setString(6, user.getUsername());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	};

	public void deleteUser(String username) throws SQLException {
		Connection connection = null;
		String sql = "delete from User where username = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	};
}
