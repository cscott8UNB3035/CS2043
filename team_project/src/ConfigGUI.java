package team_project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.*;
import javafx.stage.*;

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
			return prop.getProperty("course_equiv_path");
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Course Equivalence path.");
			return null;
		}
		
	}
	
	
	protected static String getCourseAreasPath()
	{
		try
		{
			return prop.getProperty("course_areas_path");
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Course Areas path.");
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
	
	
	protected static String getLevel(String level)
	{
		try
		{
			return prop.getProperty("level" + level);
		}
		catch(Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find specified level.");
			return null;
		}
	}
	
	
	protected static String getTranscriptName()
	{
		try
		{
			return prop.getProperty("transcript_name");
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Transcript Name.");
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
	
	
	protected static void createConfig()
	{
		try
		{
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	//Universal set-property method
	protected static void updateProperty(String property, String newValue)
	{
		
	}
	
	
	//Universal get-property method
	protected static String getProperty(String property)
	{
		try
		{
			return prop.getProperty(property);
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find requested property.");
			return null;
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
		
		
		Text configText = new Text();
		
		
		/*Button changeOutputPath = new Button("Change Output Path");
		changeOutputPath.setMinWidth(130);
		changeOutputPath.setMaxWidth(130);
		changeOutputPath.setOnAction(e -> 
		{
			//changeOutputPath()
			updateConfigText(configText);
		});
		
		
		Button changeCourseEquivSrc = new Button("Change Course Equivalence Source Folder");
		changeCourseEquivSrc.setWrapText(true);
		changeCourseEquivSrc.textAlignmentProperty().set(TextAlignment.CENTER);
		changeCourseEquivSrc.setMinWidth(130);
		changeCourseEquivSrc.setMaxWidth(130);
		changeCourseEquivSrc.setOnAction(e -> 
		{
			//changeCourseEquivSrc()
			updateConfigText(configText);
		});
		
		
		Button changeTranscriptSrc = new Button("Change Transcript Source Folder");
		changeTranscriptSrc.setWrapText(true);
		changeTranscriptSrc.textAlignmentProperty().set(TextAlignment.CENTER);
		changeTranscriptSrc.setMinWidth(130);
		changeTranscriptSrc.setMaxWidth(130);
		changeTranscriptSrc.setOnAction(e -> 
		{
			//changeTranscriptSrc()
			updateConfigText(configText);
		});*/
		
		
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
		
		
		VBox content = new VBox();
		content.setPadding(new Insets(10,10,10,10));
		content.setSpacing(10);
		content.setAlignment(Pos.CENTER);
		content.getChildren().addAll(configText);
		
		
		VBox bottomButtons = new VBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(back);
		//bottomButtons.getChildren().addAll(changeOutputPath, changeCourseEquivSrc, changeTranscriptSrc, back);
		
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setCenter(content);
		layout.setBottom(bottomButtons);
		
		
		
		scene = new Scene(layout, 600, 500);
		
		// -------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			updateConfigText(configText);
		});
		
		window.setScene(scene);
		window.setTitle("Configuration");
		window.show();
		
		// ------------------------------------
	}
	
	
	private static void updateConfigText(Text text)
	{	
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(configFileName));
			String fileText = "";
			String line = br.readLine();
			
			while(line != null)
			{
				fileText += line + "\n";
				line = br.readLine();
			}
			
			br.close();
			text.setText(fileText);
		}
		catch (Exception e) 
		{
			AlertBox.displayAlert("Error", "Could not display config file.");
		}
				
	}

	
}