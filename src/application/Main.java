package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	//Stage window;
	//Scene startScene, mainScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("StartView.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("NatoAlphabet - The Game");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		
		Parent root = FXMLLoader.load(this.getClass().getResource("StartView.fxml"));
		startScene = new Scene(root);
		window.show();
		window.setScene(startScene);
		
	}
	*/
	
	/*public void changeScene(String fxml) throws Exception{
		Parent root = FXMLLoader.load(this.getClass().getResource(fxml));
		mainScene = new Scene(root);
		window.show();
		window.setScene(mainScene);
	}*/
	
	/*
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("View.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("NatoAlphabet - The Game");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}*/
	
}
