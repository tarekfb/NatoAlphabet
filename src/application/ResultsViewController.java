package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResultsViewController implements Initializable {
	
	@FXML
	private Button btnRestart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void btnRestart_Click(ActionEvent event) {
		try {
			Stage stage = null;
			Parent root = null;

			if (event.getSource() == btnRestart) {
				stage = (Stage) btnRestart.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
			}
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
