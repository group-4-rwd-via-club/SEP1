package application.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MatchViewClass is default class for the MatchListView.fxml
 * @author Group-4
 * @version 2
 */
public class MatchViewClass extends Application {


    /**
     * Empty constructor which sets matchId as null
     */
    public MatchViewClass(){
        matchId = null;
    }
    /**
     * Constructor with one argument to set match id.
     * @param id as a string.
     */
    public MatchViewClass(String id)
    {
        matchId = id;
    }

    private static String matchId;

    /**
     * Getter method to get matchId if any. Returns null if no match id is selected
     * @return match id as string or null if empty
     */
    public static String getMatchId()
    {
        if (matchId != null && !matchId.isEmpty())
            return matchId;
        return null;
    }

    /**
     * Method to generate and show primary stage. Takes a stage as argument.
     * @param primaryStage argument stage as input
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            VBox root = FXMLLoader.load(getClass().getResource("MatchView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("application/logo_clear.png"));
            primaryStage.setTitle("VIAClub - Manage match");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }


    }



}
