package student_management_system;

import java.util.HashMap;

public class StudentManager 
{
	private final HashMap<Integer, Student> students;
	private int nextId = 1001;
	
	public StudentManager() 
	{
        this.students = new HashMap<>();
    }
	
	public void addStudent(String name, int grade) 
	{
		int newId = nextId;
		
		nextId++;
		
		Student student = new Student(newId, name, grade);
		
        students.put(newId, student);
        System.out.println("✅ Successfully added: " + student.getName() + 
                " (Assigned ID: " + newId + ")");
    }
	
	public void viewAllStudents() 
	{
        if (students.isEmpty()) 
        {
            System.out.println("No students recorded.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student student : students.values()) 
        {
            System.out.println(student);
        }
        System.out.println("--------------------");
    }
	
	public Student findStudent(int id) 
	{
        return students.get(id);
    }
	
	public boolean isEmpty() 
	{
	    return students.isEmpty();
	}
}