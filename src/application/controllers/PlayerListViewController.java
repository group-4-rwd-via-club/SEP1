package application.controllers;

import application.views.MatchListViewClass;
import application.views.MatchViewClass;
import application.views.PlayerViewClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class PlayerListViewController
{
   @FXML
   // The reference of searchText will be injected by the FXML loader
   private TextField searchplayer;

   // The reference of addButton will be injected by the FXML loader
   @FXML
   private Button add ;

   // The reference of printButton will be injected by the FXML loader
   @FXML
   private Button print ;

   // The reference of tableView will be injected by the FXML loader
   @FXML
   private TableView tableView;

 public  PlayerListViewController()
{
    
}


   @FXML
   private void initialize()
   {
       searchplayer.setText("TEST");
       
       
       add.setOnAction(event ->{
          PlayerViewClass  open = new  PlayerViewClass ();
          open.start(new Stage());
       });
       
       
   }
}
