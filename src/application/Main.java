package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("StartView.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("NatoAlphabet - The Game");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
