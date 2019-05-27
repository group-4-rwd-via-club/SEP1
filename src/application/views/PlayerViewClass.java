package application.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerViewClass extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("PlayerView.fxml"));
            Scene scene = new Scene(root, 400, 400);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
