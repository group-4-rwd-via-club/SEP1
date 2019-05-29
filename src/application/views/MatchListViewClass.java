package application.views;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Class to lead stage and scene.
 * @author Group-4
 * @version 2
 */
public class MatchListViewClass extends Application {

    /**
     * Default start method to start the stage.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("MatchListView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
