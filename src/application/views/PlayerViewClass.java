package application.views;

import application.controllers.PlayerViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerViewClass extends Application
{
   public static String playerId;

   /**
    * Empty constructor which sets matchId as null
    */
   public PlayerViewClass()
   {

   }

   /**
    * Constructor with one argument to set match id.
    * @param id as a string.
    */
   public PlayerViewClass(String id)
   {
      this.playerId = id;
   }

   @Override
   public void start(Stage primaryStage)
   {
      try
      {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerView.fxml"));
         PlayerViewController playerViewController = new PlayerViewController();
         playerViewController.setPlayer(playerId);
         fxmlLoader.setController(playerViewController);

         VBox root = fxmlLoader.load();



         Scene scene = new Scene(root);
         primaryStage.setScene(scene);
         primaryStage.setResizable(false);
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
