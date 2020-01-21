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
	private Button btnResponseInput;
	@FXML
	private TextField txtLetterResponse;
	final StringProperty stringProperty = new SimpleStringProperty(txtLetterResponse.getText()); //used for changelistener 
	//final or not?
	@FXML
	private Label lblRandomLetter;
	@FXML 
	private Label lblResponse;
	@FXML
	private Label lblTitle;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//txtLetterResponse.setText(" ");
		lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar()));
		
		stringProperty.addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
		    	txtLetterResponse.setText(newValue);
		    }
		});
		
		txtLetterResponse.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
		        if (! newValue.equals(stringProperty.get())) { // textField's text was changed directly (i.e. by user)
		            // perform whatever action you need...

		            // update the text property so the two remain equal:
		        	stringProperty.set(newValue);
		        }
		    }
		});
		
		/*txtLetterResponse.textProperty().addListener((observable) -> {
			lblResponse.setText(" "); //is this needed?
			btnResponseInput.setText("Enter"); //is this needed?
			btnResponseInput.setDisable(false); //this is needed
		}); //reset after a change to txtLetterResponse */
		
		btnResponseInput.setStyle(
			"-fx-base: #0000ff; -fx-font-weight: bold;");
		lblTitle.setStyle(
			"-fx-font: 24 arial; -fx-font-weight:"
			+ "bold; -fx-text-fill: #000000;"
			+ "-letter-spacing: 5.5; -fx-background-color: #9cb8b3;"
		);
		
	}
	@FXML
	public void btnResponseInput_Click(ActionEvent event) {
		this.btnResponseInput.setText("Enter");
		//this.startTimer(2000);

		if (!Arrays.asList(natoAlphabet.getNatoTelephony()).contains(txtLetterResponse.getText())) {
			lblResponse.setText("Incorrect. Try again!");
			btnResponseInput.setDisable(true);
		} else if (Arrays.asList(natoAlphabet.getNatoTelephony()).contains(txtLetterResponse.getText())) {
			lblResponse.setText("Correct!");
			lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar()));
		 //txtLetterResponse.clear(); //this seems to trigger listener
			 stringProperty.set("asd");

			
			new Timeline(new KeyFrame(
			        Duration.millis(2500), ae -> {
			        	lblResponse.setText("Time ran out. Try again");
			        	btnResponseInput.setText("Next");
			        	//txtLetterResponse.clear();
			        }))
			    .play();
		}	
		
	}
	
	/*public void startTimer(int seconds) {
		Timer timer = new Timer();
		
		Platform.runLater(new Runnable() {
		    public void run() {
		        //textField.requestFocus();
		    }
		});
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				//txtLetterResponse.setText("false");

				//btnResponseInput.setText("Next");	
			//	lblResponse.setText("Time ran out. Try again!");
		
			}
		};
		timer.schedule(task, seconds);
	//	timer.schedule
		
	}*/

}
