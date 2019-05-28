package application.controllers;

import application.Main;
import application.classes.MatchType;
import application.classes.Player;
import application.classes.PlayerList;
import application.classes.VIAClubManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static application.classes.UnavailableType.*;
// TODO: All classes and methods needs JavaDoc
public class MatchViewController
{
   private DecimalFormat x2digits = new DecimalFormat("00");

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

   // The reference of pitchButton will be injected by the FXML loader
   @FXML
   private Button pitchButton;

   // The reference of benchButton will be injected by the FXML loader
   @FXML
   private Button benchButton;

   // The reference of dateField will be injected by the FXML loader
   @FXML
   private DatePicker dateField;

   // The reference of opponentField will be injected by the FXML loader
   @FXML
   private TextField opponentField;

   // The reference of typeField will be injected by the FXML loader
   @FXML
   private ComboBox<MatchType> typeField;

   // The reference of locationField will be injected by the FXML loader
   @FXML
   private TextField locationField;

// The reference of viaScoreField will be injected by the FXML loader
   @FXML
   private ComboBox<String> viaScoreField;

// The reference of oppScoreField will be injected by the FXML loader
   @FXML
   private ComboBox<String> oppScoreField;

   // The reference of meetHourField will be injected by the FXML loader
   @FXML
   private ComboBox<String> meetHourField;

   // The reference of meetMinuteField will be injected by the FXML loader
   @FXML
   private ComboBox<String> meetMinuteField;

// The reference of startHourField will be injected by the FXML loader
   @FXML
   private ComboBox<String> startHourField;

   // The reference of meetMinuteField will be injected by the FXML loader
   @FXML
   private ComboBox<String> startMinuteField;

   // The reference of assignedField will be injected by the FXML loader
   @FXML
   private TableView<Player> assignedField;

// The reference of availableField will be injected by the FXML loader
   @FXML
   private TableView<Player> availableField;

   @FXML
   private void initialize()
   {

      //New match
      typeField.getItems().addAll(MatchType.none, MatchType.friendly, MatchType.cup, MatchType.league);
      typeField.getSelectionModel().selectFirst();

      viaScoreField.setDisable(true);
      oppScoreField.setDisable(true);

      createComboBox(meetHourField, 24);
      createComboBox(meetMinuteField, 60);
      createComboBox(startHourField, 24);
      createComboBox(startMinuteField, 60);
      createComboBox(viaScoreField, 100);
      createComboBox(oppScoreField, 100);
   }

   private VIAClubManagement viaClubManagement;
   /**
    * No args constructor to initialise local playerList
    *
    */
   public MatchViewController()
   {
      viaClubManagement = new VIAClubManagement();
   }


   private void createComboBox(ComboBox<String> field, int number) {
      for (int i = 0; i < number; i++) {
         field.getItems().add(x2digits.format(i));
      }
   }

   public void typeSelect(ActionEvent e) {
      if (!(typeField.getValue().toString().equals("none")))
         getPlayers();
   }

   public void getPlayers() {
      ArrayList<Player> allPlayers = viaClubManagement.getPlayerList().getAllPlayers();
      ArrayList<Player> availablePlayers = new ArrayList<Player>();
      if (typeField.getValue().toString().equals("friendly")) {
         for (Player player : allPlayers) {
            /*if (player.getAvailability() == null)
               continue;*/
            if (player.getAvailability().getUnavailableType().equals(none) ||
                    player.getAvailability().getUnavailableType().equals(suspended)){
               availablePlayers.add(player);
            }
         }
      } else {
         for (Player player : allPlayers) {
            if (player.getAvailability().getUnavailableType().equals(none)){
               availablePlayers.add(player);
            }
         }
      }
      //Run build table code here!
      System.out.println(availablePlayers.size());  //Delete this line
   }

}
