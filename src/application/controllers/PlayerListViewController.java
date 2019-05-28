package application.controllers;

import application.classes.Availability;
import application.classes.Match;
import application.classes.Player;
import application.classes.PlayerList;
import application.views.PlayerViewClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PlayerListViewController
{
   @FXML
   // The reference of searchText will be injected by the FXML loader
   private TextField searchplayer;

   // The reference of addButton will be injected by the FXML loader
   @FXML
   private Button add;

   // The reference of printButton will be injected by the FXML loader
   @FXML
   private Button print;

   // The reference of tableView will be injected by the FXML loader
   @FXML
   private TableView tableView;
   
   // table 
   @FXML 
   private TableColumn<Player, String> firstname;
   
   @FXML
   private TableColumn<Player, String> lastname;
   
   @FXML
   private TableColumn<Player, String> shirt;
   
   @FXML
   private TableColumn<Player, String> position;
   
   @FXML
   private TableColumn<Availability, String> status;
   
 //TODO How many matches a player were played
   
   

   public PlayerListViewController()
   {

   }

   @FXML
   private void initialize()

   {
      PlayerList p = new PlayerList();
      firstname = new TableColumn<Player, String>();
      lastname= new TableColumn<Player, String>();
      shirt = new TableColumn<Player, String>();
      position = new TableColumn<Player, String>();
      status = new TableColumn<Availability, String>();

      // add
      add.setOnAction(event -> {
         PlayerViewClass open = new PlayerViewClass();
         open.start(new Stage());
      });

      // print

      print.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>()
            {

               public void handle(MouseEvent e)
               {

                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Players");
                  alert.setHeaderText(null);
               
                     alert.setContentText(p.toString());
                  
                  alert.showAndWait();

               }
            }

      );
      
      // search by keyword
      
      
      
      

   }
}
