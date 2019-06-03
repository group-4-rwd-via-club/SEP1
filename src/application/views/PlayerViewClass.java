package application.views;

import application.controllers.PlayerViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * PlayerViewClass is default class for PlayerView.fxml ui.
 * Added functionality includes public constructor with arguments as id for a specific player.
 * @author Group-4
 * @version 1
 */

public class PlayerViewClass extends Application
{
   private static String playerId;

   /**
    * Public constructor without argument for initialization of the class
    */
   public PlayerViewClass(){
      playerId = null;
   }
   /**
    * Public constructor which takes a player ID as argument and sets a private field
    * @param id of a player
    */
   public PlayerViewClass(String id)
   {

      playerId = id;
   }

   @Override
   public void start(Stage primaryStage)
   {
      try
      {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerView.fxml"));
         PlayerViewController playerViewController = new PlayerViewController();
         if (playerId != null) {
            playerViewController.setPlayer(playerId);
         }
         fxmlLoader.setController(playerViewController);

         VBox root = fxmlLoader.load();


         Scene scene = new Scene(root);
         primaryStage.getIcons().add(new Image("application/logo_clear.png"));
         primaryStage.setTitle("VIAClub - Manage player");
         primaryStage.setScene(scene);
         primaryStage.setResizable(false);
         primaryStage.show();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   
}
