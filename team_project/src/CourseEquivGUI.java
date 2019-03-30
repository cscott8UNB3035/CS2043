package team_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.stage.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * TODO: Implement a method to shift all columns to the right after a column deletion.
 */

public class CourseEquivGUI
{	
	// ----- Excel File Variables -----
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	
	// ----- Course Equivalency Methods -----
	
	protected static void openCourseEquiv(String filepath)
	{
		
		try 
		{
			fis = new FileInputStream(ConfigGUI.getCourseEquivPath());
			
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Course Equivalence file.");
		}
		
	}
	
	
	protected static void closeCourseEquiv()
	{
		
		try
		{	
			fis.close();
			//fos.close();
			workbook.close();
		}
		catch(Exception e)
		{
			AlertBox.displayAlert("Error", "Error while closing Course Equivalence file.");
		}
		
	}
	
	
	// ----- GUI Methods -----
	
	
	protected static void showCourseEquiv()
	{
		ConfigGUI.openConfig();
		
		// GUI Components
		Stage window = new Stage();
		Scene scene;
		
	
		//top
		Text equivTitle = new Text("Course Equivalencies");
		equivTitle.setFont(Font.font(16));
		
		HBox topMenu = new HBox();
		topMenu.setPadding(new Insets(10,10,10,10));
		topMenu.setSpacing(10);
		topMenu.setAlignment(Pos.CENTER);
		topMenu.getChildren().add(equivTitle);
		
		
		
		//mid
		ScrollPane data = new ScrollPane();
		
		
		
		//bottom
		Button addEquivalency = new Button("Add Equivalency");
		addEquivalency.setOnAction(e ->
		{
			window.close();
			addCourseEquiv();
			window.show();
		});
		
		Button back = new Button("Back");
		back.setOnAction(e ->
		{
			window.close();
		});
		
		VBox bottomMenu = new VBox();
		bottomMenu.setPadding(new Insets(10,10,10,10));
		bottomMenu.setSpacing(10);
		bottomMenu.setAlignment(Pos.CENTER);
		bottomMenu.getChildren().addAll(addEquivalency, back);
		
		
		
		//entire layout
		BorderPane courseEquivMenu = new BorderPane();
		courseEquivMenu.setTop(topMenu);
		courseEquivMenu.setCenter(data);
		courseEquivMenu.setBottom(bottomMenu);
		
		
		
		scene = new Scene(courseEquivMenu, 350, 300);
		
		// ----------------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			ConfigGUI.closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			updateCourseEquivList(data);
		});
		
		window.setScene(scene);
		window.setTitle("Course Equivalences");
		window.show();
		
		// ------------------------------------
	}
	
	
	//GridPane will be updated with newest course-equiv data, and inserted into data scrollpane in scene3.
	private static void updateCourseEquivList(ScrollPane sp)
	{
		openCourseEquiv(ConfigGUI.getCourseEquivPath());
		
		row = spreadsheet.getRow(0);
		int maxRow = spreadsheet.getLastRowNum();
		int maxCell = row.getLastCellNum();
		
		
		
		GridPane excel = new GridPane();
		excel.setPadding(new Insets(10, 10, 10, 10));
		excel.setVgap(10);
		excel.setHgap(20);
		
		Text delete = new Text("Delete");
		delete.setFont(Font.font(14));
		GridPane.setConstraints(delete, 0, 0);
		
		Text title = new Text("Equivalencies");
		title.setFont(Font.font(14));
		GridPane.setConstraints(title, 1, 0);
		
		excel.getChildren().addAll(delete, title);
		
		
		//button to remove current row's data needed
		//get info column by column, then row by row.
		//once the end of a column has been reached (nullPointer), go back to row 0, and go to the next column.
		//do this until the last column has been reached (maxCell)
		
		for(int i=0; i<maxCell; i++)
		{
			final int x = i;
			
			
			
			for(int j=0; j<=maxRow+1; j++)
			{
				
				try
				{	
					if(j==0)
					{
						
						Button temp = new Button("X");
						temp.setOnAction(e -> 
						{
							int currentColumn = x;
							openCourseEquiv(ConfigGUI.getCourseEquivPath());
							
							
							for(int k=0; k<=maxRow; k++)
							{
								
								try
								{
									XSSFCell cellToRemove = spreadsheet.getRow(k).getCell(currentColumn);
									spreadsheet.getRow(k).removeCell(cellToRemove);
								}
								catch(NullPointerException np)
								{
									break;
								}
										
							}
							
							
							try
							{
								fos = new FileOutputStream(ConfigGUI.getCourseEquivPath());
								workbook.write(fos);
								fos.close();
							}
							catch(Exception exc)
							{
								AlertBox.displayAlert("Error", "Cannot remove Equivalence definition.");
							}
							
							
							closeCourseEquiv();
							
							updateCourseEquivList(sp);
						});		//end button event handler definition
						
						
						GridPane.setConstraints(temp, j, i+1);
						excel.getChildren().add(temp);
					}
					else
					{
						Label temp = new Label(spreadsheet.getRow(j-1).getCell(i).getStringCellValue());
						
						GridPane.setConstraints(temp, j, i+1);
						excel.getChildren().add(temp);
					}
					
				}
				catch (NullPointerException e)
				{
					break;
				}
				
			}	//end inner for-loop
				
				
			
		}	//end outer for-loop
		
		
		sp.setContent(excel);
		
		closeCourseEquiv();
	}
	
	
	private static void addCourseEquiv()
	{
		Stage window2 = new Stage();
		Scene sceneA;
		
		
		
		Text addCourseEquivTitle = new Text("Enter # of Courses: ");
		addCourseEquivTitle.setFont(Font.font(14));
		
		TextField numCoursesField = new TextField();
		numCoursesField.setMinWidth(75);
		numCoursesField.setMaxWidth(75);
		
		Button go = new Button("Go");
		go.setOnAction(e ->
		{	
			openCourseEquiv(ConfigGUI.getCourseEquivPath());
			
			int currentCell = row.getLastCellNum();
				
			for(int i=0; i<Integer.parseInt(numCoursesField.getText()); i++)
			{
				row = spreadsheet.getRow(i);
				cell = row.createCell(currentCell);
				
				addCell(cell);
			}
			
			try
			{
				fos = new FileOutputStream(ConfigGUI.getCourseEquivPath());
				workbook.write(fos);
				fos.close();
			}
			catch(Exception exc)
			{
				AlertBox.displayAlert("Error", "Cannot save Course Equivalence file.");
			}
			
			closeCourseEquiv();
			window2.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window2.close();
			}
		});
		
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(addCourseEquivTitle, numCoursesField, go, cancel);
		
		
		
		//define scene
		sceneA = new Scene(contents, 225, 310);
		
		
		
		// ----- Window Properties -----
		
		window2.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window2.close();
			}
		});
		
		window2.setScene(sceneA);
		window2.setTitle("Configuration");
		window2.showAndWait();
		
	}
	
	
	private static void addCell(XSSFCell cell)
		{
			Stage window2 = new Stage();
			Scene sceneA;
			
			
			
			Text enterCourse = new Text("Enter Course: ");
			enterCourse.setFont(Font.font(14));
			
			TextField course = new TextField();
			course.setMinWidth(75);
			course.setMaxWidth(75);
			
			Button ok = new Button("OK");
			ok.setOnAction(e ->
			{
				cell.setCellValue(course.getText());
				window2.close();
			});
			
			Button cancel = new Button("Cancel");
			cancel.setOnAction(e ->
			{
				boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
				if(answer)
				{
					window2.close();
				}
			});
			
			
			
			VBox contents = new VBox();
			contents.setPadding(new Insets(10,10,10,10));
			contents.setSpacing(10);
			contents.setAlignment(Pos.CENTER);
			contents.getChildren().addAll(enterCourse, course, ok, cancel);
			
			
			
			//define scene
			sceneA = new Scene(contents, 225, 310);
			
			
			
			// ----- Window Properties -----
			
			window2.setOnCloseRequest(e -> 
			{
				e.consume();
				boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
				if(answer)
				{
					window2.close();
				}
			});
			
			window2.setScene(sceneA);
			window2.setTitle("Configuration");
			window2.showAndWait();
		}
	
	
}