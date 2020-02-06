package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
	
	Main main = new Main();

	@FXML
	private Button btnSwitch;
	public void btnSwitch_Click(ActionEvent event) throws Exception{
		System.out.println("ASD");
		Scene mainScene;
		System.out.println("asd");
		Parent root = FXMLLoader.load(this.getClass().getResource("View.fxml"));
		mainScene = new Scene(root);
		main.window.show();
		main.window.setScene(mainScene);
	}
	

}
