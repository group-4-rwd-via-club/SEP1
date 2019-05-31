package application.views;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

/**
 * Default Class for PlayerListView.fxml
 * @author Group-4
 * @version 1
 */

public class PlayerListViewClass extends Application
{
   /**
    * Default start method, which takes stage as argument.
    * @param primaryStage
    */
   @Override
   public void start(Stage primaryStage)
   {
      try
      {
         VBox root = (VBox) FXMLLoader.load(getClass().getResource("PlayerListView.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setScene(scene);
         primaryStage.show();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   
}
