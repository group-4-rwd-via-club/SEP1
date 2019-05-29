package application.views;

import javafx.application.Application;
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
      playerId = null;
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
         VBox root = (VBox) FXMLLoader
               .load(getClass().getResource("PlayerView.fxml"));
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
