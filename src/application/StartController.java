package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
	
	@FXML
    private void sceneSwitch (ActionEvent event) throws Exception {
        Stage stage = null;
        Parent root = null;
       
        if (event.getSource()==btnSwitch){
            stage = (Stage) btnSwitch.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("View.fxml"));           
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
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
