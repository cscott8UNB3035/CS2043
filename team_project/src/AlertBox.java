package team_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox
{
	
	static boolean answer;
	
	public static void displayAlert(String title, String message)
	{
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		
		
		//Window Components
		Text text = new Text();
		text.setText(message);
		
		Button closeButton = new Button("OK");
		closeButton.setOnAction( e-> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(text, closeButton);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10,10,10,10));
		
		
		
		//Set the window scene
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}