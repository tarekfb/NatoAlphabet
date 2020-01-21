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
	private Button btnTryAgain;
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
		
		txtLetterResponse.textProperty().addListener((observable) -> {
			lblResponse.setText(" "); 
			btnResponseInput.setText("Enter");
			txtLetterResponse.setEditable(true);

		});
		
		btnResponseInput.setStyle("-fx-font: 18 arial; -fx-base: #023618; ");
		lblTitle.setStyle(
			"-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: #000000;"
			+ " -letter-spacing: 5.5; -fx-background-color: #9cb8b3;"
		);
		
		if (txtLetterResponse.getText().equals("asd")){
			txtLetterResponse.setEditable(false);

			btnResponseInput.setText("Next");
		}
		
	}
	@FXML
	public void btnResponseInput_Click(ActionEvent event) {
	if (!Arrays.asList(natoAlphabet.getNatoTelephony()).contains(txtLetterResponse.getText())) {
		lblResponse.setText("Incorrect. Try again!");
	} else if (Arrays.asList(natoAlphabet.getNatoTelephony()).contains(txtLetterResponse.getText())) {
		txtLetterResponse.setText(" ");
		
		lblResponse.setText("Correct!");
		lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar()));
	}
		
		/*long time = System.currentTimeMillis();
		if (time > time + 2000) {
			lblResponse.setText(null);
		}*/
	}
	
	@FXML
	public void btnTryAgain_Click(ActionEvent event) {
		
	}
	
	
}
