package studentManagementSystem;

public class Main 
{

	public static void main(String[] args) 
	{
		FileHandler.loadFromFile();
		Controller.loop();
	}

}