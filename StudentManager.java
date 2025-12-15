package student_management_system;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentManager 
{
	private static final String JDBC_URL = "jdbc:mysql://localhost/student_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root1234";
	
	
	public StudentManager() 
	{
		try (Connection conn = this.getConnection()) 
		{
            if (conn != null) 
            {
                System.out.println("✅ Database connection established successfully.");
            }
        } 
		catch (SQLException e) 
		{
            System.out.println("❌ Error connecting to the database: " + e.getMessage());
        }
    }
	
	private Connection getConnection() throws SQLException 
	{
        // The DriverManager handles loading the driver and establishing the connection.
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }
	
	public void addStudent(String name, int grade) 
	{
		String sql = "INSERT INTO students (name, grade) VALUES (?, ?)";
		
		try (Connection conn = getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        // 1. Set the values for the placeholders (?)
		        // Parameter indexes start at 1
		        pstmt.setString(1, name);
		        pstmt.setInt(2, grade);

		        // 2. Execute the update (INSERT, UPDATE, DELETE use executeUpdate)
		        int rowsAffected = pstmt.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("✅ Student added successfully!");
		        } else {
		            System.out.println("❌ Failed to add student.");
		        }

		    } catch (SQLException e) {
		        // Handle database specific errors (e.g., connection lost, data too long)
		        System.out.println("❌ Database Error while adding student: " + e.getMessage());
		    }
    }
	
	public List<Student> viewAllStudents() 
	{
		List<Student> studentList = new ArrayList<>();
	    String sql = "SELECT id, name, grade FROM students ORDER BY id ASC";

	    // Try-With-Resources closes Connection, Statement, and ResultSet automatically
	    try (Connection conn = getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) { // executeQuery for SELECT statements

	        // 1. Iterate through the result set
	        // The ResultSet (rs) holds the data retrieved from the database table.
	        while (rs.next()) {
	            // 2. Extract data from the current row
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            int grade = rs.getInt("grade");

	            // 3. Create a new Student object and add it to the list
	            studentList.add(new Student(id, name, grade));
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Database Error while viewing students: " + e.getMessage());
	    }
	    
	    return studentList;
    }
	
	public Student findStudent(int id) 
	{
        return null;
    }
	
	public boolean isEmpty() 
	{
	    return true;
	}
}