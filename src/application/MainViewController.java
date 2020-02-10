package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Condition;


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

	@FXML
	private Button btnUserInput;
	@FXML
	private Button btnStart;
	@FXML
	private Button btnRestart;
	@FXML
	private Button btnQuit;
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
		btnRestart.setManaged(false);
		natoAlphabet.setTotalCounter(0);
		natoAlphabet.setProgressCounter(0);
		if (natoAlphabet.getTimeLimit() != 0) {
			lblTimer.setText(String.valueOf(timeline.getTotalDuration().toSeconds()) + "s");
		}

		if (natoAlphabet.getTimeLimit() != 0) {
			timeline.getKeyFrames().add(keyframe);
			timeline.setCycleCount(natoAlphabet.getTimeLimit() * 10);
			timeline.setOnFinished(event -> {
				lblResponse.setText("Time ran out. Try again!");
				btnUserInput.setText("Next");
				stringProperty.set("");
				txtUserInput.setEditable(false);
			});
		}
			
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
		this.timer("start");
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
	
	}
	public void timer(String string) {

		if (natoAlphabet.getTimeLimit() != 0){
			if (string.equals("start")) {
				timeline.stop();
				lblTimer.setText(String.valueOf(natoAlphabet.getTimeLimit()) + ".0s"); //is this nececssary?
		
				timeline.playFromStart();
			} else if (string.equals("stop")) {
				timeline.stop();
			} else if (string.equals("pause")) {
				timeline.pause();
			} else if (string.equals("play")) {
				timeline.play();
			} else if (string.substring(0, 4).equals("wait")) {
				try {
					synchronized(timeline) {
						timeline.wait(Integer.valueOf(string.substring(4)));
						this.timer("play");
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		this.gameOverCheckExe();
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
		if (natoAlphabet.getMaxQuestions() == 0) {
			return false;
		}
		if (natoAlphabet.getMaxQuestions() == natoAlphabet.getTotalCounter()){
			btnUserInput.setDisable(true);
			String score = this.lblProgressCounter.getText();
			lblResponse.setText("Game over!\n" + score);
			btnRestart.setManaged(true);
			btnRestart.requestFocus();
			this.timer("stop");

			return true;
		}
		return false;
	}
	public void btnRestart_Click(ActionEvent event) {
		if (NatoAlphabet.getHighscore() < natoAlphabet.getProgressCounter()) {
			NatoAlphabet.setHighscore(natoAlphabet.getProgressCounter());
		}
		try {
        	Stage stage = null;
        	Parent root = null;
       
        	if (event.getSource()==btnRestart){
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
	public void btnQuit_Click(ActionEvent event) {
		timer("pause");
		lblRandomLetter.setVisible(false);
		
		if (natoAlphabet.getTimeLimit() != 0 || natoAlphabet.getMaxQuestions() != 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Quit without saving progress?");
			alert.setHeaderText("Score will not be recorded");
			alert.setContentText("If you quit now your score will not be recorded. Do you wish to proceed?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					Stage stage = null;
		        	Parent root = null;
		       
		        	if (event.getSource()==btnQuit){
		        		stage = (Stage) btnRestart.getScene().getWindow();
		        		root = FXMLLoader.load(getClass().getResource("StartView.fxml"));           
		        	}
		        	timer("stop");
		        	Scene scene = new Scene(root);
		        	stage.setScene(scene);
		        	stage.show();
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }	
			} else if (result.get() == ButtonType.CANCEL || result.get() == ButtonType.NO || result.get() == ButtonType.CLOSE) {
				timer("wait1000");
				lblRandomLetter.setVisible(true);
			}
		} else if (natoAlphabet.getTimeLimit() == 0 || natoAlphabet.getMaxQuestions() == 0){
			try {
				Stage stage = null;
	        	Parent root = null;
	       
	        	if (event.getSource()==btnQuit){
	        		stage = (Stage) btnRestart.getScene().getWindow();
	        		root = FXMLLoader.load(getClass().getResource("StartView.fxml"));           
	        	}
	        	timer("stop");
	        	Scene scene = new Scene(root);
	        	stage.setScene(scene);
	        	stage.show();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }	
		}
        	
	}
	public void setStyle() {
		/*btnUserInput.setStyle(
			"-fx-base: #0000ff; -fx-font-weight: bold;");
		lblTitle.setStyle(
			"-fx-font: 24 arial; -fx-font-weight:"
			+ "bold; -fx-text-fill: #000000;"
			+ "-letter-spacing: 5.5; -fx-background-color: #9cb8b3;"
		);*/
	}

}
