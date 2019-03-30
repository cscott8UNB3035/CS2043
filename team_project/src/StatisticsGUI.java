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

/*
 * TODO: Add methods for generating statistics.
 */

public class StatisticsGUI
{

	protected static void showStatistics()
	{
		ConfigGUI.openConfig();
		
		Stage window = new Stage();
		Scene scene;
		
		// ----- Config Menu -----
		
		Text genStats = new Text("Generate Statistics");
		genStats.setFont(Font.font(20));
		
		
		Button back = new Button("Back");
		back.setOnAction(e ->
		{
			window.close();
		});
		
		
		Button stat1 = new Button("Stat 1");
		stat1.setMinWidth(100);
		stat1.setMaxWidth(100);
		stat1.setOnAction(e ->
		{
			//perform stat 1, store in output
		});
		
		
		Button stat2 = new Button("Stat 2");
		stat2.setMinWidth(100);
		stat2.setMaxWidth(100);
		stat2.setOnAction(e ->
		{
			//perform stat 2, store in output
		});
		
		
		Button stat3 = new Button("Stat 3");
		stat3.setMinWidth(100);
		stat3.setMaxWidth(100);
		stat3.setOnAction(e ->
		{
			//perform stat 3, store in output
		});
		
		
		Button stat4 = new Button("Stat 4");
		stat4.setMinWidth(100);
		stat4.setMaxWidth(100);
		stat4.setOnAction(e ->
		{
			//perform stat 4, store in output
		});
		
		
		Button stat5 = new Button("Stat 5");
		stat5.setMinWidth(100);
		stat5.setMaxWidth(100);
		stat5.setOnAction(e ->
		{
			//perform stat 5, store in output
		});
		
		
		
		HBox title = new HBox();
		title.setPadding(new Insets(15, 15, 15, 15));
		title.setSpacing(15);
		title.setAlignment(Pos.CENTER);
		title.getChildren().addAll(genStats);
		
		
		VBox stats = new VBox();
		stats.setPadding(new Insets(15, 15, 15, 15));
		stats.setSpacing(15);
		stats.setAlignment(Pos.CENTER);
		stats.getChildren().addAll(stat1, stat2, stat3, stat4, stat5);
		
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(back);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setCenter(stats);
		layout.setBottom(bottomButtons);
		
		scene = new Scene(layout, 250, 350);
		
		// -------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			ConfigGUI.closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			
		});
		
		window.setScene(scene);
		window.setTitle("Statistics");
		window.show();
		
		// ------------------------------------
	}
	
	
}