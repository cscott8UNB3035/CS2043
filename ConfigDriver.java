package team_project;

public class ConfigDriver 
{
	
	public static void main(String[] args)
	{
		
		Configuration.openConfig();
		
		Configuration.printConfig();
		
		
		Configuration.openCourseEquiv(Configuration.getCourseEquivPath());
		
		Configuration.printCourseEquiv();
		
		
		Configuration.closeCourseEquiv();
		Configuration.closeConfig();
		
	}
	
}