package team_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProcessInfoGUI
{

	
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
			
			ExcelReader.transcriptCollector();
			System.out.println("done");
			ExcelReader.closeExcel();
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
		
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(back);
		
		
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
			
		});
		
		window.setScene(scene);
		window.setTitle("Data Processor");
		window.show();
		
		// ------------------------------------
	}
	
	
}