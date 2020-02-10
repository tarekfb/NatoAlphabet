package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class StartViewController implements Initializable {
	
	NatoAlphabet natoAlphabet = new NatoAlphabet();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtMaxQuestions.setTooltip(new Tooltip("Leave blank to play without limit")); //TO-DO
		lblHighscore.setText("HIGH SCORE: " + NatoAlphabet.getHighscore());
		lblInfo.setText("Welcome Back! This game helps you memorize the Nato Alphabet."
				+ "\nLeave the fields blank for unlimited time and questions."
				+ "\nHigh score is only kept track of if time and maximum score is entered."
				+ "\nPress start if you're ready - the timer, if selected, will start right away."
				+ "\n"
				+ "\nGood luck!"
				);
		if (natoAlphabet.getTimeLimit() != 0) {
			txtTimeLimit.setText(String.valueOf(natoAlphabet.getTimeLimit()));
		}
		if (natoAlphabet.getMaxQuestions() != 0) {
			txtMaxQuestions.setText(String.valueOf(natoAlphabet.getMaxQuestions()));
		}
	}
	
	
	
	@FXML
    private void btnStart_Click (ActionEvent event) {
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
		
        try {
        	Stage stage = null;
        	Parent root = null;
       
        	if (event.getSource()==btnStart){
        		stage = (Stage) btnStart.getScene().getWindow();
        		root = FXMLLoader.load(getClass().getResource("MainView.fxml"));           
        	}
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
}
