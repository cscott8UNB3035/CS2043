package team_project;

public class Driver 
{
	public static void main(String[] args)
	{
		
		ImportConfig.loadConfig();
		
		ImportConfig.printConfig();
		
		ImportConfig.closeConfig();
		
	}
}