package application;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ResultsViewController implements Initializable {
	
	Result result = new Result();
	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<Result> resultList = FXCollections.observableArrayList(result.getResultList());		
		
		colInput.setCellValueFactory(new PropertyValueFactory<Result, String>("userInput"));
		colSolution.setCellValueFactory(new PropertyValueFactory<Result, String>("solution"));
		colSeconds.setCellValueFactory(new PropertyValueFactory<Result, Double>("seconds"));
		colEqualValue.setCellValueFactory(new PropertyValueFactory<Result, String>("equalValue"));
		
		tvResults.setItems(resultList);
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
	

}
