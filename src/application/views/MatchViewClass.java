package application.views;

import application.classes.Match;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MatchViewClass extends Application {


    public static String matchId;

    public MatchViewClass(){}

    public MatchViewClass(String id)
    {
        this.matchId = id;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("MatchView.fxml"));
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
