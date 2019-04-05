package team_project;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.stage.*;

public class SystemGUI extends Application
{
	private static Stage window;
	private Scene scene1;
	
	protected static TranscriptHandler tScriptList;
	protected static MasterList masterCourseList;
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	public void start(Stage primaryStage)
	{
		window = new Stage();
		
		// ----- Main Menu (scene1) -----
		
		Text mainMenu = new Text("Main Menu");
		mainMenu.setFont(Font.font(20));
		
		
		Button config = new Button("Configuration");
		config.setMinWidth(130);
		config.setMaxWidth(130);
		config.setOnAction(e ->
		{
			ConfigGUI.showConfig();
		});
		
		
		Button equiv = new Button("Course Equivalences");
		equiv.setMinWidth(130);
		equiv.setMaxWidth(130);
		equiv.setOnAction(e ->
		{
			CourseEquivGUI.showCourseEquiv();
		});
		
		
		Button process = new Button("Process Data");
		process.setMinWidth(130);
		process.setMaxWidth(130);
		process.setOnAction(e ->
		{
			tScriptList = ProcessInfoGUI.showDataProcessor(tScriptList);
		});
		
		
		Button stats = new Button("Generate Statistics");
		stats.setMinWidth(130);
		stats.setMaxWidth(130);
		stats.setOnAction(e ->
		{
			StatsAndAnalysis.showStatistics(tScriptList);
		});
		
		
		Button masterList = new Button("Master Course List");
		masterList.setMinWidth(130);
		masterList.setMaxWidth(130);
		masterList.setOnAction(e ->
		{
			masterCourseList = MasterList.showMasterList(masterCourseList, tScriptList);
		});
		
		
		Button exit = new Button("Exit");
		exit.setStyle("-fx-font-size: 16; ");
		exit.setMinWidth(90);
		exit.setMinHeight(40);
		exit.setMaxWidth(90);
		exit.setMaxHeight(40);
		exit.setOnAction(e ->
		{
			closeProgram();
		});
		
		
		
		HBox topText = new HBox();
		topText.setPadding(new Insets(15, 15, 15, 15));
		topText.setSpacing(15);
		topText.setAlignment(Pos.CENTER);
		topText.getChildren().addAll(mainMenu);
		
		
		VBox buttons = new VBox();
		buttons.setPadding(new Insets(15, 15, 15, 15));
		buttons.setSpacing(15);
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(config, equiv, process, stats, masterList);
		
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(exit);
		
		
		
		BorderPane menu = new BorderPane();
		menu.setTop(topText);
		menu.setCenter(buttons);
		menu.setBottom(bottomButtons);
		
		
		
		scene1 = new Scene(menu, 280, 400);
		
		// ------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			e.consume();
			
			//make sure changes are saved to all files
			//save tScriptList
			
			closeProgram();
		});
		
		window.setOnShowing(e -> 
		{
			//check if config file is present, if not create one.
			
			//ask if user wants to re-use tScriptList
			
			initializeTScriptList();
			initializeMasterList();
		});
		
		window.setScene(scene1);
		window.setTitle("Transcript Analyser");
		window.show();
		
		// ------------------------------------
		
	}
	
	
	//ConfirmBox will be opened that confirms if the user really wants to close the program.
	private void closeProgram()
	{
		boolean answer = ConfirmBox.display("Exit", "Are you sure you want to exit?");
		if(answer)
		{
			window.close();
		}
	}
	
	
	private static void initializeTScriptList()
	{
		tScriptList = new TranscriptHandler();
	}
	
	
	private static TranscriptHandler getTScriptList()
	{
		return tScriptList;
	}
	
	
	private static void initializeMasterList()
	{
		masterCourseList = new MasterList();
	}
	
	
	private static MasterList getMasterList()
	{
		return masterCourseList;
	}

	
}