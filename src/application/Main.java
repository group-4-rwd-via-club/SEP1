package application;
	
import application.classes.VIAClubManagement;
import javafx.application.Application;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;

/**
 * Main method for the application. First view is being openened from here.
 * @author Group-4
 * @version 1
 *
 */
public class Main extends Application {

	/**
	 * default start method which loads MainView.fxml
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {

		try
		{
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
			root.setFillWidth(true);
			Scene scene = new Scene(root);
			primaryStage.setTitle("VIA Club Management");
			primaryStage.getIcons().add(new Image("application/logo_clear.png"));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false); // Disallow resize of window

			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{

		launch(args);
	}
}
