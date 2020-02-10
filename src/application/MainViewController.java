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

public class MainViewController implements Initializable {
	
	NatoAlphabet natoAlphabet = new NatoAlphabet();
	//TimerManager timerClass = new TimerManager(); if timer works delete this

	@FXML
	private Button btnUserInput;
	@FXML
	private Button btnStart;
	@FXML
	private TextField txtUserInput;
	private StringProperty stringProperty; //used for ChangeListener
	@FXML
	private Label lblRandomLetter;
	@FXML
	private Label lblProgressCounter;
	@FXML 
	private Label lblResponse;
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblTimer;
	Timeline timeline = new Timeline();
	KeyFrame keyframe = new KeyFrame(
			Duration.millis(100),
		        event -> {
		        	this.countDown();
		        }
			);
	private int s = 0; //mvc?
	private int ms = 0; //mcv?	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setStyle();
		lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar())); //just to avoid npe at init
		this.rndLetterGenerator();
		lblTimer.setText(String.valueOf(timeline.getTotalDuration().toSeconds()) + "s");
		//timeline.setCycleCount(natoAlphabet.getTimeLimit() * 10); //init sets cycle count, meaning I need to update cycle count here //can prob remove

		
			
			timeline.getKeyFrames().add(keyframe);
			timeline.setCycleCount(natoAlphabet.getTimeLimit() * 10);
			timeline.setOnFinished(event -> {
				lblResponse.setText("Time ran out. Try again!");
	        	btnUserInput.setText("Next");
				stringProperty.set("");
				txtUserInput.setEditable(false);
			});
			
			stringProperty = new SimpleStringProperty(txtUserInput.getText()); //used for ChangeListener //getTEXT WON't WORK
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
			
			this.timer("start");
		
		

	
		/*
		 * does init run on every scene launch?
		 * need to try this using sceneSwitchInt
		 * if YES, can do if check on current stage and then only perform the ifblock for the relevant scene 
		 * meaning it no NPE on scene switch
		 */
	}
	public void rndLetterGenerator() {
		char c = lblRandomLetter.getText().charAt(0);
		do {
			lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar()));
		} while (c == lblRandomLetter.getText().charAt(0));
	
	}//ensures coincidental labelling repetition using getRandomChar never occurs
	public void countDown() {
		s = Integer.valueOf(lblTimer.getText().substring(0, 1));
		ms = Integer.valueOf(lblTimer.getText().substring(2, 3));
		
		if (s != 0 && ms == 0) {
			s--;
			ms = 9;
		} else if (ms != 0) {
			ms--;
		}
	
		lblTimer.setText(s + "." + ms + "s");
		//doesn't work because its doing the calculations faster than 100ms
		//meaning after completing the calc's it displays 0.0s for the rest of the cycles
		//nvm, it prints 80 times (60 zeros?)
		//SOLVED: by instansiating keyframe in initialize()
	
	}
	public void timer(String string) {
		if (string.equals("start")) {
			timeline.stop();
			lblTimer.setText(String.valueOf(natoAlphabet.getTimeLimit()) + ".0s");
			
			timeline.playFromStart();
		} else if (string.equals("stop")) {
			 timeline.stop();
		} else if (string.equals("pause")) {
			timeline.pause();
		} else if (string.equals("play")) {
			timeline.play();
		}
	
	}
	
	@FXML
	public void btnUserInput_Click(ActionEvent event) {
		if (this.gameOverCheckExe());
		else if (btnUserInput.getText().equals("Next")) {
			this.rndLetterGenerator();
			txtUserInput.setEditable(true);
			txtUserInput.clear();
			txtUserInput.requestFocus();
			stringProperty.set("");
			lblResponse.setText("");
			btnUserInput.setText("Enter");
			//make method for this if calling many times?
			
			this.timer("stop"); //seems as if stop here doesn't stop the current active thread. 
			// Does it have no effect because its invoking the method, creating a new timeline, rendering 'stop' useless?
			this.timer("start");
		} else if (btnUserInput.getText().equals("Enter")) {		
			if (!natoAlphabet.equalCheck(txtUserInput.getText(), lblRandomLetter.getText().charAt(0))){
				lblResponse.setText("Incorrect. Try again!");
				//btnUserInput.setDisable(true);
				btnUserInput.setText("Next");
				stringProperty.set("");
				txtUserInput.setEditable(false);
				this.scoreCounter(false);
				timer("stop");
			} else if (natoAlphabet.equalCheck(txtUserInput.getText(), lblRandomLetter.getText().charAt(0))){
				lblResponse.setText("Correct!");
				this.scoreCounter(true);
				this.rndLetterGenerator();
				stringProperty.set("");
				
				this.timer("stop");
				this.timer("start");
			}
		}
	}
	public void scoreCounter(boolean b){
		natoAlphabet.setTotalCounter(natoAlphabet.getTotalCounter() + 1);
		if (b) {
			natoAlphabet.setProgressCounter(natoAlphabet.getProgressCounter() + 1);
		}
		lblProgressCounter.setText(
				"Score: " + String.valueOf((natoAlphabet.getProgressCounter())) + "/" + String.valueOf(natoAlphabet.getTotalCounter())
		);
	}
	public boolean gameOverCheckExe() {
		if (natoAlphabet.getMaxQuestions() == natoAlphabet.getTotalCounter()){
			btnUserInput.setDisable(true);
			String score = this.lblProgressCounter.getText();
			lblResponse.setText("Game over!\n" + score);
			return true;
		}
		return false;
	}
	public void setStyle() {
		btnUserInput.setStyle(
			"-fx-base: #0000ff; -fx-font-weight: bold;");
		lblTitle.setStyle(
			"-fx-font: 24 arial; -fx-font-weight:"
			+ "bold; -fx-text-fill: #000000;"
			+ "-letter-spacing: 5.5; -fx-background-color: #9cb8b3;"
		);
	}

}
