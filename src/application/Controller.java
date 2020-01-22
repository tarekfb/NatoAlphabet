package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Controller implements Initializable {
	
	NatoAlphabet natoAlphabet = new NatoAlphabet();

	@FXML
	private Button btnUserInput;
	@FXML
	private TextField txtUserInput;
	private StringProperty stringProperty; /*= new SimpleStringProperty();*/ //used for ChangeListener
	@FXML
	private Label lblRandomLetter;
	@FXML 
	private Label lblResponse;
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblTimer;
	Timeline timeLine = new Timeline(new KeyFrame(Duration.millis(3500)));
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar())); //just to avoid npe at init
		this.rndLetterGenerator();
		lblTimer.setText(String.valueOf(timeLine.getTotalDuration().toSeconds()) + "s");
		
		btnUserInput.setStyle(
				"-fx-base: #0000ff; -fx-font-weight: bold;");
			lblTitle.setStyle(
				"-fx-font: 24 arial; -fx-font-weight:"
				+ "bold; -fx-text-fill: #000000;"
				+ "-letter-spacing: 5.5; -fx-background-color: #9cb8b3;"
			);
		
		stringProperty = new SimpleStringProperty(txtUserInput.getText()); //used for ChangeListener
		stringProperty.addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
		    	txtUserInput.setText(newValue);
		    }
		});
		
		txtUserInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
		        if (! newValue.equals(stringProperty.get())) { // textField's text was changed directly (i.e. by user)
		        	lblResponse.setText("");
					btnUserInput.setText("Enter");
					btnUserInput.setDisable(false);
					
		        	stringProperty.set(newValue);
		        }
		    }
		});

		timeLine.play(); 
		timeLine.pause();
		timeLine.setOnFinished(event -> {
			lblResponse.setText("Time ran out. Try again!");
        	btnUserInput.setText("Next");
			stringProperty.set("");
			txtUserInput.setEditable(false);
		}); 
	}
	public void rndLetterGenerator() {
		char c = lblRandomLetter.getText().charAt(0); //will this throw npe if null? at start of app?
		do {
			lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar()));
		} while (c == lblRandomLetter.getText().charAt(0));
	
	}//ensures coincidental repetition of getRandomChar never occurs
	
	@FXML
	public void btnUserInput_Click(ActionEvent event) {
		if (btnUserInput.getText().equals("Next")) {
			this.rndLetterGenerator();
			txtUserInput.setEditable(true);
			txtUserInput.clear();
			txtUserInput.requestFocus();
			stringProperty.set("");
			lblResponse.setText("");
			btnUserInput.setText("Enter");
			//make method for this if calling many times?
			
			timeLine.stop();
			timeLine.play();
		} else if (btnUserInput.getText().equals("Enter")) {
					
		if (!natoAlphabet.equalCheck(txtUserInput.getText(), lblRandomLetter.getText().charAt(0))){
			lblResponse.setText("Incorrect. Try again!");
			//btnUserInput.setDisable(true);
        	btnUserInput.setText("Next");
        	stringProperty.set("");
			txtUserInput.setEditable(false);

			timeLine.stop();
		} else if (natoAlphabet.equalCheck(txtUserInput.getText(), lblRandomLetter.getText().charAt(0))){
			lblResponse.setText("Correct!");
			this.rndLetterGenerator();
			stringProperty.set("");

			timeLine.stop();
			timeLine.play();
		} 
		
			
			
		}
	}

}
