package studentManagementSystem;

import java.util.*;

public class Controller 
{
	
//	THE LOOP
	public static void loop() 
	{
			Scanner scanner = new Scanner(System.in);
			
			int choice = 0;
			while (choice != 6) 
			{
				showMenu();
				choice = getChoice(scanner);
				
				switch (choice) 
				{
					case 1:
						addNewStudent(scanner);
						break;
					case 2:
						editStudentInformation(scanner);
						break;
					case 3:
						removeStudent(scanner);
						break;
					case 4:
						searchStudent(scanner);
						break;
					case 5:
						listStudent();
						break;
					case 6:
						System.out.println("End of System");
						System.exit(1);
					default:
						System.out.println("\nEnter one of the options\n(1, 2, 3, 4, 5)\n");
				}
			}
	}
	
//	SHOW MENU
	public static void showMenu() 
	{
		System.out.println("1. Add new Student");
		System.out.println("2. Edit Student Information");
		System.out.println("3. Remove a Student");
		System.out.println("4. Search for a Student");
		System.out.println("5. List all Students");
		System.out.println("6. Exit");
		System.out.println("Choose Option\n");
	}
	
//	ADD NEW STUDENT
	public static void addNewStudent(Scanner scanner) 
	{
		System.out.println("Enter Student Name");
		String name = scanner.nextLine();
		
		int age = getAge(scanner);
		
		int grade = getGrade(scanner);
		
		FileHandler.students.add(new Student(name, age, grade));
		
		FileHandler.saveToFile();
		
		System.out.println("Student added\n");
	}
	
	public static void editStudentInformation(Scanner scanner) 
	{
//		COPY LIST
		List<Student> students = copyList();
		
//		IF LIST EMPTY
		if (students.isEmpty()) 
		{
			System.out.println("\nNo Students exist\n");
		}
		else 
		{
//			ASK ID
			int idToEdit = askIDInput(scanner);
			
			for (int i = 0; i < students.size(); i ++) 
			{
				Student student = students.get(i);
				
				editOperation(student, idToEdit, scanner);
			}
			
//			CLEAR THE LIST
			FileHandler.students.clear();
			
//			UPDATE THE LIST
			FileHandler.students.addAll(students);
			
//			SAVE TO FILE
			FileHandler.saveToFile();
		}
	}
	
	public static void removeStudent(Scanner scanner) 
	{
//		COPY LIST
		List<Student> students = copyList();
		
//		IF LIST EMPTY
		if (students.isEmpty()) 
		{
			System.out.println("\nNo Students exist\n");
		}
		else 
		{
//			ASK ID
			int idToRemove = askIDInput(scanner);
			
			for (int i = 0; i < students.size(); i ++) 
			{
				Student student = students.get(i);
				
				removeOperation(student, idToRemove, students);
			}
			
//			CLEAR THE LIST
			FileHandler.students.clear();
			
//			UPDATE THE LIST
			FileHandler.students.addAll(students);
			
//			SAVE TO FILE
			FileHandler.saveToFile();
		}
	}
	
	public static void searchStudent(Scanner scanner) 
	{
//		COPY LIST
		List<Student> students = copyList();
		
//		IF LIST EMPTY
		if (students.isEmpty()) 
		{
			System.out.println("\nNo Students exist\n");
		}
		else 
		{
//			ASK ID
			int idToSearch = askIDInput(scanner);
			
			for (int i = 0; i < students.size(); i ++) 
			{
				Student student = students.get(i);
				
				searchOperation(student, idToSearch);
			}
		}
	}

	public static void listStudent()
	{
		
//		IF LIST EMPTY
		if (FileHandler.students.isEmpty()) 
		{
			System.out.println("\nNo Students exist\n");
		}
		else 
		{
			System.out.println();
			for (Student s : FileHandler.students) 
			{
				System.out.println(s);
			}
			System.out.println();
		}
	}
	
	public static int askIDInput(Scanner scanner) 
	{
		int id;
		while (true) 
		{
			id = 0;
			try 
			{
				System.out.println("\nEnter Student ID\n");
				id = scanner.nextInt();
				scanner.nextLine();
				break;
			}
			catch (InputMismatchException e) 
			{
				scanner.nextLine();
				System.out.println("\nCannot accept Alphabet, enter number\n");
				continue;
			} 
		}
		return id;
	}
	
	public static int chooseEditOption(Scanner scanner) 
	{
		System.out.println("\n1. Name");
		System.out.println("2. Age");
		System.out.println("3. Grade");
		System.out.println("4. Remarks");
		System.out.println("Choose an option to edit\n");
		int option = scanner.nextInt();
		scanner.nextLine();
		return option;
	}
	
	public static void setNewName(Scanner scanner, Student student) 
	{
		System.out.println("Enter New Name");
		String newName = scanner.nextLine();
		student.setName(newName); 
		System.out.println("\nName updated\n");
	}
	
	public static void setNewAge(Scanner scanner, Student student) 
	{
		System.out.println("Enter New Age");
		int newAge = scanner.nextInt();
		scanner.nextLine();
		student.setAge(newAge); 
		System.out.println("\nAge updated\n");
	}
	
	public static void setNewGrade(Scanner scanner, Student student) 
	{
		System.out.println("Enter new Grade");
		int newGrade = scanner.nextInt();
		scanner.nextLine();
		student.setGrade(newGrade); 
		System.out.println("\nGrade updated\n");
	}
	
	public static void setNewRemarks(Scanner scanner, Student student) 
	{
		System.out.println("Enter remarks");
		String remarks = scanner.nextLine();
		student.setRemarks(remarks);
		System.out.println("\nRemarks updated\n");
	}
	
	public static void editOperation(Student student, int idToEdit, Scanner scanner) 
	{
		if (student.getId() == idToEdit) 
		{
			int editOption = chooseEditOption(scanner);
			
			switch (editOption) 
			{
				case 1: setNewName(scanner, student);
						break;
				case 2: setNewAge(scanner, student);
						break;
				case 3:	setNewGrade(scanner, student);
						break;
				case 4: setNewRemarks(scanner, student);
						break;
				default: System.out.println("\nEnter One of the options\n(1, 2, 3, 4)\n");
			}
		}
		else 
		{
			System.out.println("\nWrong ID or student does not exist\n");
		}
	}
	
	public static void removeOperation(Student student, int idToRemove, List<Student> students) 
	{
		if (student.getId() == idToRemove) 
		{
			students.remove(student);
			System.out.println("\nStudent removed\n");
		}
		else 
		{
			System.out.println("\nWrong ID or student does not exist\n");
		}
	}
	
	public static void searchOperation(Student student, int idToSearch) 
	{
		if (student.getId() == idToSearch) 
		{
			System.out.println("Student found\n" + student);
		}
		else 
		{
			System.out.println("\nWrong ID or student does not exist\n");
		}
	}
	
	public static List<Student> copyList() 
	{
		List<Student> students = new ArrayList<>();
		students.addAll(FileHandler.students);
		
		return students;
	}
	
	public static int getChoice(Scanner scanner) 
	{
		int choice;
		while (true) 
		{
			choice = 0;
			try 
			{
				choice = scanner.nextInt();
				scanner.nextLine();
				break;
			} 
			catch (InputMismatchException e) 
			{
				scanner.nextLine();
				System.out.println("\nEnter one of the options\n(1, 2, 3, 4, 5, 6)\n");
				continue;
			} 
		}
		return choice;
	}
	
	public static int getAge(Scanner scanner) 
	{
		int age = 0;
		while (true)
		{
			try 
			{
				System.out.println("\nEnter Student Age\n");
				age = scanner.nextInt();
				scanner.nextLine();
			}
			catch (InputMismatchException e) 
			{
				scanner.nextLine();
				System.out.println("\nCannot accept Alphabet, enter number\n");
				continue;
			}
			
			if (age<5 || age>15) 
			{
				System.out.println("\nEnter valid age\n");
				continue;
			}
			else 
			{
				break;
			}
		}
		return age;
	}
	
	public static int getGrade(Scanner scanner) 
	{
		int grade = 0;
		
		while (true) 
		{
			try 
			{
				System.out.println("\nEnter Student Grade\n");
				grade = scanner.nextInt();
				scanner.nextLine();
			} 
			catch (InputMismatchException e) 
			{
				scanner.nextLine();
				System.out.println("\nEnter valid Grade\n(1, 2, 3, 4)\n");
				continue;
			}
			
			if (grade < 1 || grade > 4) 
			{
				System.out.println("\nEnter valid Grade\n(1, 2, 3, 4)\n");
				scanner.nextLine();
				continue;
			}
			else 
			{
				break;
			}
		}
		return grade;
	}
}