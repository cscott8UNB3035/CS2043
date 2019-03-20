package team_project;

public class CourseEquivDriver
{

	public static void main(String[] args)
	{
		
		ImportConfig.openConfig();
		
		ImportCourseEquiv.openCourseEquiv(ImportConfig.getCourseEquivPath());
		
		ImportCourseEquiv.printCourseEquiv();
		
		ImportCourseEquiv.closeCourseEquiv();
		ImportConfig.closeConfig();
		
	}
	
}