package team_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox
{
	static boolean answer;
	
	public static boolean display(String title, String message)
	{
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Text text = new Text();
		text.setText(message);
		text.setTextAlignment(TextAlignment.CENTER);
		
		
		
		Button yes = new Button("Yes");
		Button no = new Button("No");
		
		yes.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		no.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		
		
		VBox layout = new VBox(10);
		
		layout.getChildren().addAll(text, yes, no);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
	
}