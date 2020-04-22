package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage primaryStage;
	private static Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("StartView.fxml"));
			scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/stylesheets/stylesheet.css").toExternalForm());
	        root.setId("pane");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("NatoAlphabet - The Game");
			primaryStage.setMaximized(true);
			
			Main.primaryStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Stage getPrimaryStage() {
	    return primaryStage;
	}
	
	public Scene getScene() {
		return scene;
	}
	
}
/*@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("StartView.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/stylesheets/stylesheet.css").toExternalForm());
	        root.setId("pane");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("NatoAlphabet - The Game");
			primaryStage.setMaximized(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
