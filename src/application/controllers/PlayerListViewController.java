package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PlayerListViewController
{
   @FXML
   // The reference of searchText will be injected by the FXML loader
   private TextField searchText;

   // The reference of addButton will be injected by the FXML loader
   @FXML
   private Button addButton;

   // The reference of printButton will be injected by the FXML loader
   @FXML
   private Button printButton;

   // The reference of tableView will be injected by the FXML loader
   @FXML
   private TableView tableView;

 public  PlayerListViewController()
{
    
}


   @FXML
   private void initialize()
   {
       searchText.setText("TEST");
   }
}
