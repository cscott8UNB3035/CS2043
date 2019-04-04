package team_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProcessInfoGUI
{
	private static TranscriptHandler tScriptList = SystemGUI.tScriptList;
	private static String tScriptName = ConfigGUI.getTranscriptName();
	private static FileInputStream fis;
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	
	
	protected static void openExcel(File file) throws FileNotFoundException, IOException 
	{
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
	}
	
	
	protected static void readExcelFile() {
		
		Transcript t = new Transcript();
		
		Iterator < Row > rowIterator = sheet.iterator();
		row = (XSSFRow) rowIterator.next();
			
			while(rowIterator.hasNext())
			{
				try
				{	
					String courseCode = row.getCell(1).getStringCellValue();
					String sectionCode = row.getCell(2).getStringCellValue();
					String title = row.getCell(3).getStringCellValue();
					String letterGrade = row.getCell(4).getStringCellValue();
					double creditHour = row.getCell(5).getNumericCellValue();
					String term = row.getCell(6).getStringCellValue();
					
					Course c = new Course(courseCode, sectionCode, title, letterGrade, creditHour, term);
					t.add(c);
					
					row = (XSSFRow) rowIterator.next();
				}
				catch(NullPointerException e)
				{
					continue;
				}
						
			}
			
			tScriptList.add(t);
		}
	
	
	protected static void closeExcel() {
		
		try{
			
			fis.close();
			
		}catch(Exception e){
			
			AlertBox.displayAlert("Error", "Could not close transcript file.");
		}
	}
	

	
	//GUI Functionality
	
	protected static void showDataProcessor()
	{
		ConfigGUI.openConfig();
		
		Stage window = new Stage();
		Scene scene;
		
		// ----- Info Processor Menu -----
		
		Text processData = new Text("Process Data");
		processData.setFont(Font.font(20));
		
		
		Button start = new Button("Process Transcripts");
		start.setMinWidth(130);
		start.setMinHeight(40);
		start.setMaxWidth(130);
		start.setMaxHeight(40);
		start.setOnAction(e ->
		{
			if(ConfirmBox.display("Process Transcripts", "Process transcripts in this folder?\n\n" 
					+ ConfigGUI.getTranscriptFolderPath() + "\n\nWith this file name?\n\n" + tScriptName + "\n"))
			{
				processTranscripts();
			}
		});
		
		Button changeName = new Button("Change File Name");
		changeName.setOnAction(e ->
		{
			setTScriptName();
		});
		
		
		Button back = new Button("Back");
		back.setOnAction(e ->
		{
			window.close();
		});
		
		
		
		VBox buttons = new VBox();
		buttons.setPadding(new Insets(10,10,10,10));
		buttons.setSpacing(10);
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(start);
		
		
		HBox title = new HBox();
		title.setPadding(new Insets(10,10,10,10));
		title.setSpacing(10);
		title.setAlignment(Pos.CENTER);
		title.getChildren().addAll(processData);
		
		
		VBox bottomButtons = new VBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(changeName, back);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setCenter(buttons);
		layout.setBottom(bottomButtons);
		
		scene = new Scene(layout, 250, 200);
		
		// -------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			ConfigGUI.closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			setTScriptName();
		});
		
		window.setScene(scene);
		window.setTitle("Data Processor");
		window.show();
		
		// ------------------------------------
	}
	
	
	
	private static void processTranscripts()
	{
		int i = 1;
		boolean done = false;
		
		while(!done)
		{
			try
			{
				openExcel(new File(ConfigGUI.getTranscriptFolderPath() + tScriptName + "_" + i + ".txt"));
				
				readExcelFile();
				
				closeExcel();
			}
			catch (FileNotFoundException fnf)
			{
				AlertBox.displayAlert("Done", "All transcripts in the folder have been processed.");
				done = true;
				
			}
			catch (IOException e) 
			{
				AlertBox.displayAlert("Error", "Could not open file.");
			}
		}	
		
	}
	
	
	
	private static void setTScriptName()
	{
		Stage window = new Stage();
		Scene scene;
		
		
		
		Text enterFileName = new Text("Enter Transcript File Name (exit to leave as default):");
		enterFileName.setFont(Font.font(14));
		
		Text help = new Text("(Make sure to omit numbers, symbols, and the file extension)");
		help.setFont(Font.font(11));
		
		TextField fileNameField = new TextField();
		fileNameField.setMinWidth(125);
		fileNameField.setMaxWidth(125);
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			tScriptName = fileNameField.getText();
			window.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Continue with current Transcript Name?");
			if(answer)
			{
				window.close();
			}
		});
		
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(enterFileName, help, fileNameField);
		
		VBox bottomButtons = new VBox();
		bottomButtons.setPadding(new Insets(10,10,10,10));
		bottomButtons.setSpacing(10);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(ok, cancel);
		
		BorderPane layout = new BorderPane();
		layout.setCenter(contents);
		layout.setBottom(bottomButtons);
		
		
		
		
		//define scene
		scene = new Scene(layout, 500, 325);
		
		
		
		// ----- Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Leave file name as default?");
			if(answer)
			{
				window.close();
			}
		});
		
		window.setScene(scene);
		window.setTitle("Students By Location");
		window.showAndWait();
	}
	
}