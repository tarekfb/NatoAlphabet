package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class StartViewController implements Initializable {

	NatoAlphabet natoAlphabet = new NatoAlphabet();
	Main main = new Main();
	//TimerManager timerClass = new TimerManager(); if timer works delete this

	@FXML
	private Button btnStart;
	@FXML
	private TextField txtMaxQuestions;
	@FXML
	private TextField txtTimeLimit;
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblHighscore;
	@FXML
	private Label lblInfo;
	@FXML
	private Label lblMaxQuestions;
	@FXML
	private Label lblTimeLimit;
	@FXML
	private GridPane gpInput;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.stylesheet();
		System.out.println(Main.getPrimaryStage());
		
		txtMaxQuestions.setTooltip(new Tooltip("Leave blank to play without a maximum question amount."));
		txtTimeLimit.setTooltip(new Tooltip("Leave blank to play without a time limit."));
		lblHighscore.setText("HIGH SCORE: " + NatoAlphabet.getHighscore());
		/*lblInfo.setText("Welcome back! This game helps you memorize the Nato Alphabet."
				+ "\nLeave the fields blank for unlimited time and/or questions."
				+ "\nHigh score is only kept track of if time and maximum score is entered."
				+ "\nPress start if you're ready - the timer, if enabled, will start right away."
				+ "\n"
				+ "\nGood luck!"
				);*/
		
		/*Welcome back! 

This game helps you memorize the Nato-alphabet. 
Leave the fields blank for unlmited time and/or questions.
High score is only ept track of if time and maximium score is entered. 
Press start if you're ready - the timer, if enabled, will start right away. 
Good luck!*/

		if (natoAlphabet.getTimeLimit() != 0) {
			txtTimeLimit.setText(String.valueOf(natoAlphabet.getTimeLimit()));
		}
		if (natoAlphabet.getMaxQuestions() != 0) {
			txtMaxQuestions.setText(String.valueOf(natoAlphabet.getMaxQuestions()));
		}
	}
	@FXML
	private void btnStart_Click (ActionEvent event) {
		boolean nfeCheck = false;

		if (txtTimeLimit.getText().length() > 1) {
			Alert alert = new Alert(AlertType.WARNING);
			nfeCheck = true;
			
			Label alertLabel = new Label("The time limit field can only take numerical values consisting of a single-digit number. "
					+ "\nPlease enter a single-digit number.");
			alertLabel.setWrapText(true);
			alert.setTitle("Invalid input");
			alert.setHeaderText("Invalid input");
			alert.getDialogPane().setContent(alertLabel);

			alert.showAndWait();
		} else {
			try {
				if (txtTimeLimit.getText().isBlank()) {
					natoAlphabet.setTimeLimit(0); 
				} else if (!txtTimeLimit.getText().isBlank()) {
					natoAlphabet.setTimeLimit(Integer.valueOf(txtTimeLimit.getText())); 
				}
				if (txtMaxQuestions.getText().isBlank()) {
					natoAlphabet.setMaxQuestions(0);
				}
				else if (!txtMaxQuestions.getText().isBlank()) {
					natoAlphabet.setMaxQuestions(Integer.valueOf(txtMaxQuestions.getText()));
				}
			} catch (NumberFormatException nfe) {
				Alert alert = new Alert(AlertType.WARNING);
				nfeCheck = true;
				alert.setTitle("Invalid input");
				alert.setHeaderText("Invalid input");
				Label alertLabel = new Label("The fields can only take numerical values consisting of a single-digit number."
						+ "\nPlease enter a number.");
				alertLabel.setWrapText(true);
				alert.getDialogPane().setContent(alertLabel);
				
				alert.showAndWait();
			}
			try {
				if (nfeCheck == false) {
					/*Parent newPage = FXMLLoader.load(getClass().getResource("MainView.fxml"));
					((Node) event.getSource()).getScene().setRoot(newPage);*/
					
					Parent newPage = FXMLLoader.load(getClass().getResource("MainView.fxml"));
					Scene newScene = new Scene(newPage, main.getScene().getWidth(), main.getScene().getHeight());
					newScene.getStylesheets().add(getClass().getResource("/stylesheets/stylesheet.css").toExternalForm());

					Main.getPrimaryStage().setScene(newScene);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void stylesheet() {
		lblMaxQuestions.setId("label");
		lblTimeLimit.setId("label");
		lblHighscore.setId("label");
		lblTitle.setId("lblTitle");
		lblInfo.setId("label");;
	}

}
