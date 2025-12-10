package studentManagementSystem;

import java.io.Serializable;

public class Student implements Serializable
{
	private int id;
	private static int idCounter = 1;
	private String name;
	private int age;
	private int grade;
	private String remarks;
	
//	CONSTRUCTOR
	public Student(String name, int age, int grade) 
	{
		this.id = idCounter++;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.remarks = "";
	}
	
	public String toString() 
	{
		return "ID: "+id+
			 "\nName: "+name+
			 "\nAge: "+age+
			 "\nGrade: "+grade+
			 "\nRemarks: "+remarks+"\n";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the idCounter
	 */
	public static int getIdCounter() {
		return idCounter;
	}

	/**
	 * @param idCounter the idCounter to set
	 */
	public static void setIdCounter(int idCounter) {
		Student.idCounter = idCounter;
	}	
}