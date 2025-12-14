package student_management_system;

public class Student 
{
	private final int studentId;
    private final String name;
    private final int grade;
    
    public Student(int studentId, String name, int grade) 
    {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }
    
	public int getStudentId() 
	{
		return studentId;
	}

	public String getName() 
	{
		return name;
	}

	public int getGrade() 
	{
		return grade;
	}
	
	@Override
    public String toString() 
	{
        return "ID: " + studentId + ", Name: " + name + ", Grade: " + grade;
    }
    
}