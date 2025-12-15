package student_management_system;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		try (Scanner scanner = new Scanner(System.in)) 
		{
			StudentManager manager = new StudentManager();
			
			System.out.println("Welcome to Minimal Student Management System!");
			
			while (true) 
			{
				System.out.println("\n--- Menu ---");
				System.out.println("1. Add New Student");
				System.out.println("2. View All Students");
				System.out.println("3. Search Student by ID");
				System.out.println("4. Exit Program");
				System.out.print("Enter choice (1-4): ");
				
				if (!scanner.hasNextInt()) 
				{
			        System.out.println("Invalid input. Please enter a number.");
			        scanner.next(); 
			        continue;
			    }
				
				int choice = scanner.nextInt();
			    scanner.nextLine();
			    
			    try 
			    {
					switch (choice) 
					{
						case 1: 
						    int grade = -1;
						
								System.out.print("Enter Name: ");
						        String name = scanner.nextLine();
						        
						do 
						{
							System.out.print("Enter Grade (1-4): ");
							if (!scanner.hasNextInt()) 
							{
								System.out.println("❌ Invalid input type. Grade must be a number (1-4).");
								scanner.nextLine();
								continue;
							}
							grade = scanner.nextInt();
							scanner.nextLine();
							
							if (grade < 1 || grade > 4) 
							{
								System.out.println("❌ Invalid grade value. Grade must be between 1 and 4.");
								continue;
							}
							break;
						} while (true);
						
						manager.addStudent(name, grade);
						        break;
						case 2: List<Student> students = manager.viewAllStudents();
								if (students.isEmpty()) 
								{
							        System.out.println("📋 The student list is currently empty.");
							    } 
								else 
								{
							        System.out.println("\n--- All Recorded Students ---");
							        for (Student s : students) 
							        {
							            System.out.println(s); 
							        }
							        System.out.println("-----------------------------");
							    }
							    break;
						case 3: if (manager.isEmpty()) 
								{
							        System.out.println("❌ Cannot search. No students have been recorded yet.");
							        break; 
							    }
						
								int searchId = -1;
							
								do 
								{
									System.out.print("Enter Student ID to search: ");
									
									if (!scanner.hasNextInt()) 
									{
										System.out.println("❌ Invalid input type. Student ID must be a number.");
										scanner.nextLine();
										continue;
									}
									
									searchId = scanner.nextInt();
									scanner.nextLine();
									break;
								} while (true);
								
								Student found = manager.findStudent(searchId); 
						        
						        if (found == null) 
						        {
						            System.out.println("Student ID " + searchId + " not found.");
						        } 
						        else 
						        {
						            System.out.println("Search Result: " + found);
						        }
						        break;
						case 4: System.out.println("Exiting program. Goodbye!");
								return;
						default:
						    System.out.println("Invalid choice. Please choose 1, 2, 3, or 4.");
					}
				} 
			    catch (InputMismatchException e) 
			    {
					System.out.println("Error: Input mismatch. Please ensure you enter numbers for ID and Grade.");
			        scanner.nextLine(); 
				}
			}
		}
	}
}