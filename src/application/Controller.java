package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Controller implements Initializable {
	
	NatoAlphabet natoAlphabet = new NatoAlphabet();

	@FXML
	private Button btnResponseInput;
	@FXML
	private TextField txtLetterResponse;
	@FXML
	private Label lblRandomLetter;
	@FXML 
	private Label lblResponse;
	@FXML
	private Label lblTitle;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar()));
		
		lblResponse.setText(" ");
		txtLetterResponse.textProperty().addListener((observable) -> {
			 if (!lblResponse.getText().equals(" ")) {
				lblResponse.setText(" "); 
			}
		}); //FIX SO CORRECT WORKS
		
		btnResponseInput.setStyle("-fx-font: 18 arial; -fx-base: #023618; ");
		lblTitle.setStyle(
			"-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: #000000;"
			+ " -letter-spacing: 5.5; -fx-background-color: #9cb8b3;"
		);
		
	}
	
	
	@FXML
	public void btnResponseInput_Click(ActionEvent event) {
	if (!Arrays.asList(natoAlphabet.getNatoTelephony()).contains(txtLetterResponse.getText())) {
		lblResponse.setText("Incorrect. Try again!");
	} else if (Arrays.asList(natoAlphabet.getNatoTelephony()).contains(txtLetterResponse.getText())) {
			lblResponse.setText("Correct!");
			txtLetterResponse.setText(" ");
			lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar()));
	}
		
		/*long time = System.currentTimeMillis();
		if (time > time + 2000) {
			lblResponse.setText(null);
		}*/
	} //asd
	
	
}
