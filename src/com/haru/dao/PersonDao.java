package com.haru.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haru.entities.Person;

public class PersonDao {
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=Users;";
	private static String username = "sa";
	private static String password = "1234567890";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public boolean savePerson(Person p) {
		boolean status = false;
		try {
			Connection con = PersonDao.getConnection();
			String sql = "insert into person (first_name, last_name,email,password,birthday,gender) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getEmail());
			ps.setString(5, p.getPassword());
			ps.setString(4, p.getBirthday());
			ps.setString(6, p.getGender());
			status = ps.executeUpdate() > 0;
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	public List<Person> getAllPersons() {
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person = null;
		String sql = "select * from person";
		try {
			Connection con = PersonDao.getConnection();
//			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			Statement ps = con.createStatement();
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {

				person = new Person();
				person.setId(rs.getInt(1));
				person.setFirstName(rs.getString(2));
				person.setLastName(rs.getString(3));
				person.setEmail(rs.getString(4));
				person.setPassword(rs.getString(5));
				person.setBirthday(rs.getString(6));
				person.setGender(rs.getString(7));
				persons.add(person);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persons;
	}
}
