package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
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
	TimerManager timerClass = new TimerManager();

	@FXML
	private Button btnUserInput;
	@FXML
	private Button btnStart;
	@FXML
	private TextField txtUserInput;
	private StringProperty stringProperty; /*= new SimpleStringProperty();*/ //used for ChangeListener
	@FXML
	private TextField txtMaxQuestions;
	private int maxQuestions = 50; //change back to 0
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
	Timeline timeline = new Timeline(); //(new KeyFrame(Duration.millis(3500)));
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
		timeline.getKeyFrames().add(keyframe);
		timeline.setCycleCount(20);
		timeline.setOnFinished(event -> {
			lblResponse.setText("Time ran out. Try again!");
        	btnUserInput.setText("Next");
			stringProperty.set("");
			txtUserInput.setEditable(false);
		});
		
		txtMaxQuestions.setTooltip(new Tooltip("Leave blank to play without limit"));
		lblRandomLetter.setText(String.valueOf(natoAlphabet.getRandomChar())); //just to avoid npe at init
		this.rndLetterGenerator();
		lblTimer.setText(String.valueOf(timeline.getTotalDuration().toSeconds()) + "s");
		
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
		
		if (s == 2 && ms == 0) {
			s--;
			ms = 9;
		} else if (s == 1 && ms == 0){
			s--;
			ms = 9;
		} else if (s == 1){
			ms--;
		} else if (s == 0 && ms != 0) {
			ms--;
		}
		lblTimer.setText(s + "." + ms + "s");
		//doesn't work because its doing the calculations faster than 100ms
		//meaning after completing the calc's it displays 0.0s for the rest of the cycles
		//nvm, it prints 80 times (60 zeros?)
		//SOLVED: by instansiating keyframe in initialize()
	
	}
	public void timer(String string, int i) {//i should be used to select time [TO-DO]
		//Timeline timeline = new Timeline();
		//timeline.pause();

		/*timeline.setCycleCount(20);
		timeline.setOnFinished(event -> {
			lblResponse.setText("Time ran out. Try again!");
        	btnUserInput.setText("Next");
			stringProperty.set("");
			txtUserInput.setEditable(false);
		});*/
		
		if (string.equals("start")) {
			
			timeline.stop();
			lblTimer.setText("2.0s"); //should use int i here, in future, and then update displaycounter to work with any number
			/*timeline.getKeyFrames().add(new KeyFrame(
					 Duration.millis(100),
				        event -> {
				        	this.countDown();
				        }
				    ));*/
			timeline.playFromStart();
		} else if (string.equals("stop")) {
			 timeline.stop();
		} else if (string.equals("pause")) {
			timeline.pause();
		} else if (string.equals("play")) {
			timeline.play();
		}
	
		/*if (string.equals("start")) {
		
		timeline.stop();
		lblTimer.setText("2.0s"); //should use int i here, in future, and then adapt displaycounter
		timeline.getKeyFrames().add(new KeyFrame(
				 Duration.millis(100),
			        event -> {
			        	this.countDown();
			        }
			    ));
		timeline.playFromStart();*/
	}
	
	@FXML
	public void btnUserInput_Click(ActionEvent event) {
		//if (maxQuestions != 0) { //redesign
			if (maxQuestions == natoAlphabet.getTotalCounter()){
				btnUserInput.setDisable(true);
				String score = this.lblProgressCounter.getText();
				lblResponse.setText("Game over!\n" + score);
			} else if (btnUserInput.getText().equals("Next")) {
			this.rndLetterGenerator();
			txtUserInput.setEditable(true);
			txtUserInput.clear();
			txtUserInput.requestFocus();
			stringProperty.set("");
			lblResponse.setText("");
			btnUserInput.setText("Enter");
			//make method for this if calling many times?
			
			this.timer("stop", 2); //seems as if stop here doesn't stop the current active thread. 
			// Does it have no effect because its invoking the method, creating a new timeline, rendering 'stop' useless?
			this.timer("start", 2);
		} else if (btnUserInput.getText().equals("Enter")) {		
			if (!natoAlphabet.equalCheck(txtUserInput.getText(), lblRandomLetter.getText().charAt(0))){
				lblResponse.setText("Incorrect. Try again!");
				//btnUserInput.setDisable(true);
				btnUserInput.setText("Next");
				stringProperty.set("");
				txtUserInput.setEditable(false);
				this.scoreCounter(false);
				timer("stop", 2);
			} else if (natoAlphabet.equalCheck(txtUserInput.getText(), lblRandomLetter.getText().charAt(0))){
				lblResponse.setText("Correct!");
				this.scoreCounter(true);
				this.rndLetterGenerator();
				stringProperty.set("");
				
				this.timer("stop", 2);
				this.timer("start", 2);
			} 
	
		}
	}
	
	public void btnStart_Click(ActionEvent event) {
		if (!txtMaxQuestions.getText().isBlank()) {
			maxQuestions = Integer.valueOf(txtMaxQuestions.getText());
		}
		//this.timer("start", 2);
		txtMaxQuestions.clear();
		btnStart.setDisable(true);
		txtMaxQuestions.setEditable(false);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//btnUserInput.setText("test");
				lblResponse.setText("test");
			}
		}, 5, 5);
		https://stackoverflow.com/questions/9413656/how-to-use-timer-class-to-call-a-method-do-something-reset-timer-repeat
		https://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ScheduledExecutorService.html
		Do you specifically want a Timer? If not you're probably better off with a ScheduledExecutorService and calling scheduleAtFixedRate or scheduleWithFixedDelay; quoting the Javadocs:
		Verkar som att de finns bättre lösningar än timeline
		
		/*
		KeyFrame keyframe = new KeyFrame(Duration.s(1), null, null);
		KeyFrame keyframe = new KeyFrame(
				 Duration.millis(100),
			        event -> {
			        	this.countDown();
		KeyFrame kf = new KeyFrame(
				 Duration.millis(100),
			        event -> {
						lblResponse.setText("test");
			        });
		Timeline startTimer = new Timeline(new KeyFrame(
				Duration.seconds(1),
					event -> {
						lblResponse.setText("test");
					}

		Timeline timeline = new Timeline(); //(new KeyFrame(Duration.millis(3500)));
		KeyFrame keyframe = new KeyFrame(
				 Duration.millis(100),
			        event -> {
			        	this.countDown();
			        	*/
	}
	public void scoreCounter(boolean b){
		natoAlphabet.setTotalCounter(natoAlphabet.getTotalCounter() + 1);
		if (b) {
			natoAlphabet.setProgressCounter(natoAlphabet.getProgressCounter() + 1);
		}
		lblProgressCounter.setText("Score: " + String.valueOf((natoAlphabet.getProgressCounter())) + "/" + String.valueOf(natoAlphabet.getTotalCounter()) );
		

	}
	
	//a bunch of notes from timeline experiments
	/*timeLine.getKeyFrames().add(new KeyFrame(Duration.millis(0), 
    event -> {
    	new KeyFrame(Duration.millis(100), 
        event -> {
        	this.countDown();
        });
    	timeLine.setCycleCount(20);
    	timeLine.play();
		//lblTimer.setText(String.valueOf(timeLine.getCurrentTime().toSeconds()) + "s");
		//timerValueProperty = new SimpleStringProperty(String.valueOf(timeLine.getCurrentTime()));
		//lblTimer.textProperty().bind(timerValueProperty);
    }
));*/


/*timeLine.getKeyFrames().add(new KeyFrame(
 Duration.millis(100),
    event -> {
    	this.countDown();
		//lblTimer.setText(String.valueOf(timeLine.getCurrentTime().toSeconds()) + "s");
		//timerValueProperty = new SimpleStringProperty(String.valueOf(timeLine.getCurrentTime()));
		//lblTimer.textProperty().bind(timerValueProperty);
    }
));*/



//timerValueProperty = new SimpleStringProperty(String.valueOf(timeLine.getCurrentTime()));
//lblTimer.textProperty().bind(timerValueProperty);

/*
timeLine.getKeyFrames().add(new KeyFrame(
 Duration.millis( 1500 ),
    event -> {
		//lblTimer.setText(String.valueOf(timeLine.getTotalDuration().toSeconds()) + "s");
		System.out.println(String.valueOf(timeLine.getTotalDuration().toSeconds()) + "s");
		timerValueProperty = new SimpleStringProperty(String.valueOf(timeLine.getCurrentTime()));
		lblTimer.textProperty().bind(timerValueProperty);
    }
));*/

/*
timeLine = new Timeline(new KeyFrame(Duration.seconds(0),
event -> timerValueProperty = new SimpleStringProperty(String.valueOf(timeLine.getCurrentTime()))), 
new KeyFrame(Duration.millis( 1500 ),
    event -> {
		//lblTimer.setText(String.valueOf(timeLine.getTotalDuration().toSeconds()) + "s");
		System.out.println(String.valueOf(timeLine.getTotalDuration().toSeconds()) + "s");
    }
));

timeLine.play(); */

}
