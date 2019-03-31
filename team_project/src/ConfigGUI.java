package team_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.stage.*;

/*	
 * TODO: Implement method to get data from config file and print into window.
 * TODO: Implement methods to modify data
 */

public class ConfigGUI
{	
	// ----- Configuration File Variables -----
	private static InputStream is = null;
	private static Properties prop = new Properties();
	private static String configFileName = "C:\\Users\\coby\\Java Workspace\\CS2043\\src\\team_project\\transcript_analyser.config";
	
	
	// ----- Configuration File Methods -----
	
	protected static void openConfig()
	{
		
		try
		{
			is = new FileInputStream(configFileName);
		}
		catch (FileNotFoundException e)
		{
			AlertBox.displayAlert("Error", "Configuration file does not exist.");
		}
		
		try
		{
			prop.load(is);
		}
		catch (IOException e)
		{
			AlertBox.displayAlert("Error", "Cannot load Configuration file.");
		}
		
	}
	
	
	protected static String getCourseEquivPath()
	{
		
		try
		{
			return prop.getProperty("course_equiv_path") + "course_equivalency_list.xlsx";
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Course Equivalence path.");
			return null;
		}
		
	}
	
	
	protected static String getTranscriptFolderPath()
	{
		
		try
		{
			return prop.getProperty("transcript_path");
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Transcript folder path.");
			return null;
		}
		
	}
	
	
	protected static String getOutputFolderPath()
	{
		try
		{
			return prop.getProperty("output_path");
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Output folder path.");
			return null;
		}
	}
	
	
	protected static void closeConfig()
	{
		
		try
		{	
			is.close();
		}
		catch(Exception e)
		{
			AlertBox.displayAlert("Error", "Error while closing configuration file.");
		}
		
	}
	
	
	// ----- GUI Methods -----
	
	protected static void showConfig()
	{
		openConfig();
		
		Stage window = new Stage();
		Scene scene;
		
		// ----- Config Menu -----
		
		Text config = new Text("Configuration");
		config.setFont(Font.font(20));
		
		Button back = new Button("Back");
		back.setOnAction(e ->
		{
			window.close();
		});
		
		
		HBox title = new HBox();
		title.setPadding(new Insets(10,10,10,10));
		title.setSpacing(10);
		title.setAlignment(Pos.CENTER);
		title.getChildren().addAll(config);
		
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(back);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setBottom(bottomButtons);
		
		
		scene = new Scene(layout, 250, 200);
		
		// -------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			
		});
		
		window.setScene(scene);
		window.setTitle("Configuration");
		window.show();
		
		// ------------------------------------
	}

	
}