package studentManagementSystem;

import java.io.*;
import java.util.*;

public class FileHandler 
{
	private static final File file = new File("students.dat");
	static List<Student> students = new ArrayList<>();
	
	public static void saveToFile() 
	{
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));)
		{
			out.writeObject(students);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadFromFile() 
	{
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));)
		{
			students.addAll((List<Student>) in.readObject());
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("No file detected, creating new file\n");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}