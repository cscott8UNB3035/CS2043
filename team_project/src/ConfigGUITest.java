package team_project;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConfigGUITest {

	@Test
    protected static void getCourseEquivPathTest(){
		ConfigGUI.openConfig();
		String result = ConfigGUI.getCourseEquivPath();
		ConfigGUI.closeConfig();
		assertEquals("C:\\Users\\coby\\Java Workspace\\CS2043\\src\\team_project\\course_equiv\\course_equivalency_list.xlsx", result);
	}
	
	@Test
    protected static void getTranscriptFolderPathTest(){
		ConfigGUI.openConfig();
		String result = ConfigGUI.getTranscriptFolderPath();
		ConfigGUI.closeConfig();
		assertEquals("C:\\Users\\coby\\Java Workspace\\CS2043\\src\\team_project\\transcripts\\", result);
	}
	
	@Test
    protected static void getOutputFolderPathTest(){
		openConfig();
		String result = ConfigGUI.getOutputFolderPath();
		ConfigGUI.closeConfig();
		assertEquals("C:\\Users\\coby\\Java Workspace\\CS2043\\src\\team_project\\output\\", result);
	}
	
	
}