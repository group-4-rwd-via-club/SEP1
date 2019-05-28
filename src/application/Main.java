package application;
	
import application.classes.VIAClubManagement;
import javafx.application.Application;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;


public class Main extends Application {

	public static VIAClubManagement VIAClubManagement;

	@Override
	public void start(Stage primaryStage) {
		VIAClubManagement = new VIAClubManagement();
		try {

			VBox root = (VBox) FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
			root.setFillWidth(true);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false); // Disallow resize of window

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{

		launch(args);
	}
}
