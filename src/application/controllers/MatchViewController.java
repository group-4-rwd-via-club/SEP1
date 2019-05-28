package application.controllers;

import application.Main;
import application.classes.MatchType;
import application.classes.Player;
import application.classes.PlayerList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

   private ArrayList<Player> availablePlayers;

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

   // The reference of availableNumberField will be injected by the FXML loader
   @FXML
   private TableColumn<Player, Integer> availableNumber;

   // The reference of availableNameField will be injected by the FXML loader
   @FXML
   private TableColumn<Player, String> availableName;

   // The reference of playedInRowField will be injected by the FXML loader
   @FXML
   private TableColumn<Player, Integer> playedInRow;

   // The reference of availablePerfPositionField will be injected by the FXML loader
   @FXML
   private TableColumn<Player, String> availablePrefPosition;

   // The reference of assignedNumberField will be injected by the FXML loader
   @FXML
   private TableColumn<Player, Integer> assignedNumber;

   // The reference of assignednameField will be injected by the FXML loader
   @FXML
   private TableColumn<Player, String> assignedName;

   // The reference of assignedNumberField will be injected by the FXML loader
   @FXML
   private TableColumn<Player, String> assignedPosition;

   @FXML
   private void initialize()
   {

      //New match
      typeField.getItems().addAll(MatchType.none, MatchType.friendly, MatchType.cup, MatchType.league);
      typeField.getSelectionModel().selectFirst();

      viaScoreField.setDisable(true);
      oppScoreField.setDisable(true);
      pitchButton.setDisable(true);
      benchButton.setDisable(true);

      createComboBox(meetHourField, 24);
      createComboBox(meetMinuteField, 60);
      createComboBox(startHourField, 24);
      createComboBox(startMinuteField, 60);
      createComboBox(viaScoreField, 100);
      createComboBox(oppScoreField, 100);
      //new match end
   }

   private PlayerList playerList;
   /**
    * No args constructor to initialise local playerList
    *
    */
   public MatchViewController()
   {
      playerList = Main.VIAClubManagement.getPlayerList();
   }


   private void createComboBox(ComboBox<String> field, int number) {
      for (int i = 0; i < number; i++) {
         field.getItems().add(x2digits.format(i));
      }
   }

   public void typeSelect(ActionEvent e) {
      if (!(typeField.getValue().toString().equals("none"))){
         getAvailablePlayers();
         pitchButton.setDisable(false);
         benchButton.setDisable(false);
      } else {
         availableData.clear();
         pitchButton.setDisable(true);
         benchButton.setDisable(true);
      }
   }

   public void getAvailablePlayers() {
      ArrayList<Player> allPlayers = playerList.getAllPlayers();
      availablePlayers = new ArrayList<Player>();
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
      initializeAvailableView();
   }

   private ObservableList<Player> availableData = FXCollections.observableArrayList();

   private void initializeAvailableView()
   {
      updateAvailableTableContent();

      availableNumber.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNumber()));
      availableName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtName()));
      availablePrefPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition()));
      playedInRow.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNumber()));

      availableField.setItems(availableData);
   }


   private void updateAvailableTableContent()
   {
      if (playerList != null) {

         availableData.clear();
         availableData.addAll(availablePlayers);

      }
   }
}
