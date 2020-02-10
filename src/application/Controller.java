package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.awt.List;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class Controller implements Initializable {
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtMaxQuestions.setTooltip(new Tooltip("Leave blank to play without limit")); //TO-DO
	}
	
	@FXML
    private void btnStart_Click (ActionEvent event) {
		if (!txtTimeLimit.getText().isBlank()) {
			natoAlphabet.setTimeLimit(Integer.valueOf(txtTimeLimit.getText())); 
		}
		if (!txtMaxQuestions.getText().isBlank()) {
			natoAlphabet.setMaxQuestions(Integer.valueOf(txtMaxQuestions.getText()));
		}
		
        try {
        	Stage stage = null;
        	Parent root = null;
       
        	if (event.getSource()==btnStart){
        		stage = (Stage) btnStart.getScene().getWindow();
        		root = FXMLLoader.load(getClass().getResource("View.fxml"));           
        	}
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
}
