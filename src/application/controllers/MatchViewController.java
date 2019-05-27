package application.controllers;

import application.classes.Player;
import application.views.MatchViewClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MatchViewController
{  
   // The reference of saveButton will be injected by the FXML loader
   @FXML
   private Button saveButton;
   
   // The reference of cancelButton will be injected by the FXML loader
   @FXML
   private Button cancelButton;
   
   // The reference of printButton will be injected by the FXML loader
   @FXML
   private Button printButton;
   
   // The reference of deleteButton will be injected by the FXML loader
   @FXML
   private Button deleteButton;
   
   // The reference of dateField will be injected by the FXML loader
   @FXML
   private DatePicker dateField;
   
   // The reference of opponentField will be injected by the FXML loader
   @FXML
   private TextField opponentField;
   
   // The reference of typeField will be injected by the FXML loader
   @FXML
   private ComboBox<String> typeField;
   
   // The reference of locationField will be injected by the FXML loader
   @FXML
   private TextField locationField;
   
   // The reference of meetHourField will be injected by the FXML loader
   @FXML
   private ComboBox<int[]> meetHourField;
   
   // The reference of meetMinuteField will be injected by the FXML loader
   @FXML
   private ComboBox<int[]> meetMinuteField;
   
// The reference of startHourField will be injected by the FXML loader
   @FXML
   private ComboBox<int[]> startHourField;
   
   // The reference of meetMinuteField will be injected by the FXML loader
   @FXML
   private ComboBox<int[]> startMinuteField;
   
   // The reference of pitchField will be injected by the FXML loader
   @FXML
   private ListView<Player> pitchField;
   
// The reference of benchField will be injected by the FXML loader
   @FXML
   private ListView<Player> benchField;
   
// The reference of availableField will be injected by the FXML loader
   @FXML
   private ListView<Player> availableField;


   @FXML
   private void initialize()
   {

   }
}
