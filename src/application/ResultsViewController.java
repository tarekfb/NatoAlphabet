package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ResultsViewController implements Initializable {
	
	Result result = new Result();
	NatoAlphabet natoAlphabet = new NatoAlphabet();
	
	@FXML
	private Button btnRestart;
	@FXML
	private TableView<Result> tvResults;
	@FXML
	private TableColumn<Result, String> colInput;
	@FXML
	private TableColumn<Result, String> colSolution;
	@FXML
	private TableColumn<Result, String> colEqualValue;
	@FXML
	private TableColumn<Result, Double> colSeconds;
	@FXML
	private Label lblTime;
	@FXML
	private Label lblQuestionLimit;
	@FXML
	private Label lblAvgTime;
	@FXML
	private Label lblSkill;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Result> resultList = FXCollections.observableArrayList(result.getResultList());		
		
		colInput.setCellValueFactory(new PropertyValueFactory<Result, String>("userInput"));
		colSolution.setCellValueFactory(new PropertyValueFactory<Result, String>("solution"));
		colSeconds.setCellValueFactory(new PropertyValueFactory<Result, Double>("seconds"));
		colEqualValue.setCellValueFactory(new PropertyValueFactory<Result, String>("equalValue"));
		
		tvResults.setItems(resultList);
		
		lblTime.setText(String.valueOf(natoAlphabet.getTimeLimit()));
		
 		lblQuestionLimit.setText(String.valueOf(natoAlphabet.getMaxQuestions()));
		
		DecimalFormat df = new DecimalFormat("0.0");
		lblAvgTime.setText(String.valueOf(df.format(calcAvgTime()) + "s"));
		
		lblSkill.setText(calcSkill());
		result.getResultList().clear();
	}
	
	public void btnRestart_Click(ActionEvent event) {
		try {
			Stage stage = null;
			Parent root = null;

			if (event.getSource() == btnRestart) {
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
	
	public double calcAvgTime() {
		double sum = 0;
		double avgTime = 0;
		
		for (Result tmpResult : result.getResultList()) {
			sum += tmpResult.getSeconds();
		}
		
		avgTime = sum / natoAlphabet.getMaxQuestions();
		return avgTime;

	}
	
	public String calcSkill() {
		String skill = null;
		double correctAnswers = 0;
		double accuratelyQuantified = 0;
		String percentageSubString = null;
		
		for (Result tmpResult : result.getResultList()) {
			
			if (tmpResult.getEqualValue().equals("Yes")) {
				correctAnswers++;
			}
		}
		
		accuratelyQuantified = correctAnswers / natoAlphabet.getMaxQuestions();
		
		if (accuratelyQuantified > 0.95)
			skill = "Perfectionist";
		else if (accuratelyQuantified > 0.8)
			skill = "Veteran";
		else if (accuratelyQuantified > 0.6)
			skill = "Did you serve?";
		else if (accuratelyQuantified > 0.4) 
			skill = "Passable, but not pretty";
		else if (accuratelyQuantified > 0.2)
			skill = "Absolute crap";
		else if (accuratelyQuantified > 0.05)
			skill = "Why are you even here?";
		else if (accuratelyQuantified < 0.05) 
			skill = "Leave. Now.";
	
		System.out.println(accuratelyQuantified);
		System.out.println(correctAnswers);
		System.out.println(natoAlphabet.getMaxQuestions());

		System.out.println(correctAnswers / natoAlphabet.getMaxQuestions());

		if (String.valueOf(accuratelyQuantified).substring(0, 1).equals("1"))
			percentageSubString = " (100%)";
		else if (String.valueOf(accuratelyQuantified).substring(2, 3).equals("0"))
			percentageSubString = " (0%)";
		else 
			percentageSubString = " (" + String.valueOf(accuratelyQuantified).substring(2, 4) + "%)";
		
			
		


		return skill + percentageSubString;
	}
	
}
