package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main method for the application. First view is being openened from here.
 * @author Group-4
 * @version 1
 *
 */
public class Main extends Application {

	/**
	 * default start method which loads MainView.fxml
	 * @param primaryStage object type
	 */
	@Override
	public void start(Stage primaryStage) {
		try
		{
			VBox root = FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
			root.setFillWidth(true);
			Scene scene = new Scene(root);
			primaryStage.setTitle("VIA Club Management");
			primaryStage.getIcons().add(new Image("application/logo_clear.png"));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);

			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
