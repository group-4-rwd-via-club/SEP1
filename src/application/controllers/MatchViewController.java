package application.controllers;

import application.classes.*;
import application.views.MatchViewClass;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static application.classes.UnavailableType.*;

/**
 * Controller for Match view
 * @author Group-4
 * @version 1
 *
 */
public class MatchViewController
{
   private DecimalFormat x2digits = new DecimalFormat("00");

   private int numberOfPitchPlayers = 11;
   private int numberOfBenchPlayers = 100;

   private Match existingMatch;

   private boolean isNewMatch;

   private Date today;

   private VIAClubManagement viaClubManagement;

   private ArrayList<Player> availablePlayers;
   private ArrayList<Player> assignedPlayers;

   private ObservableList<Player> availableData = FXCollections.observableArrayList();
   private ObservableList<Player> assignedData = FXCollections.observableArrayList();


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
   private void initialize() {
      typeField.getItems().addAll(MatchType.none, MatchType.friendly, MatchType.cup, MatchType.league);

      assignComboBox(startHourField, 24);
      assignComboBox(startMinuteField, 60);
      assignComboBox(viaScoreField, 100);
      assignComboBox(oppScoreField, 100);

      if (isNewMatch){
         typeField.getSelectionModel().selectFirst();
         viaScoreField.setDisable(true);
         oppScoreField.setDisable(true);
         deleteButton.setDisable(true);
         printButton.setDisable(true);
         startHourField.setDisable(true);
         startMinuteField.setDisable(true);
         assignedPlayers = new ArrayList<Player>();
      } else {
         deleteButton.setDisable(false);
         printButton.setDisable(false);
         // Add exixting match data here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      }

      /**
       * Enables match start fields when a date is chosen
       */
      dateField.valueProperty().addListener((observable)->{
         if (dateField.getValue() != null){
            startHourField.setDisable(false);
            startMinuteField.setDisable(false);
         } });

      /**
       * Set the start minute selector to 00 when an start hour is chosen
       */
      startHourField.valueProperty().addListener((observable)->{
         if (startHourField.getValue() != null){
            startMinuteField.getSelectionModel().selectFirst();
         } });


      /**
       * Event handler for mouse clicks in the available player field
       */
      availableField.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            if (availableField.getSelectionModel().getSelectedItem() != null)
               assignPlayer(availableField.getSelectionModel().getSelectedItem());
         }
         if (e.isSecondaryButtonDown()) {
            //  Insert code for context menu - needs code!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("RightClick"); // delete this
         }
      });

      /**
       * Event handler for mouse clicks in the assigned player field
       */
      assignedField.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            if (assignedField.getSelectionModel().getSelectedItem() != null)
               unAssignPlayer(assignedField.getSelectionModel().getSelectedItem());
         }
         if (e.isSecondaryButtonDown()) {

            // Insert code for context menu - needs code!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("RightClick"); // delete this
         }
      });

      /**
       * If cancelButton is clicked the user is prompted with an alert before closing the window
       */
      cancelButton.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Do you really want to cancel and exit? \n" +
                            "Any unsaved changes will be discarded.",
                    ButtonType.YES, ButtonType.NO);
            alert.setTitle("Exit");
            alert.setHeaderText(null);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
               Stage stage = (Stage) cancelButton.getScene().getWindow();
               stage.close();
            }
         }
      });

      /**
       * Saves the current data in as a Match in MatchList
       */
      saveButton.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown()) {
           if (isNewMatch){

              Match newMatch = new Match();
              newMatch.setMatchType(typeField.getValue());
              if (dateField.getValue() != null){
                 Date matchDate;
                 if (startHourField.getValue() != null && startMinuteField.getValue() != null){
                    matchDate = (new Date(dateField.getValue().getDayOfMonth(), dateField.getValue().getMonthValue(), dateField.getValue().getYear(), Integer.parseInt(startHourField.getValue()), Integer.parseInt(startMinuteField.getValue())));
                 } else {
                    matchDate = (new Date(dateField.getValue().getDayOfMonth(), dateField.getValue().getMonthValue(), dateField.getValue().getYear()));
                 }
                 if (matchDate.isBefore(today.getToday())){
                    Alert alert = new Alert(Alert.AlertType.WARNING,
                            "You have chosen a date that is before today \n" +
                                    "Do you want to save the date?",
                            ButtonType.YES, ButtonType.NO);
                    alert.setTitle("Date confirmation");
                    alert.setHeaderText(null);

                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.YES) {
                       newMatch.setDate(matchDate);
                    } else {
                       return;
                    }
                 }
              }
              if (!(opponentField.getText().equals(""))){
                 newMatch.setOpponent(opponentField.getText());
              }
              if (!(locationField.getText().equals(""))){
                 newMatch.setLocation(locationField.getText());
              }
              if (assignedPlayers.size() > 0){
                 PlayerList toRoster = new PlayerList();
                 for (Player player : assignedPlayers){
                    toRoster.addPlayer(player);
                 }
                 newMatch.setRoster(toRoster);
              }
              viaClubManagement.getMatchList().addMatch(newMatch);
              Alert alert = new Alert(Alert.AlertType.INFORMATION,
                      "You have successfully saved the match details",
                      ButtonType.CLOSE);
              alert.setTitle("Saved");
              alert.setHeaderText(null);

              alert.showAndWait();

              if (alert.getResult() == ButtonType.CLOSE) {
                 Stage stage = (Stage) saveButton.getScene().getWindow();
                 stage.close();
              }
           }

         }
      });
   }

   /**
    * No args constructor to initialise local playerList
    *
    */
   public MatchViewController()
   {
      viaClubManagement = new VIAClubManagement();
      today = new Date();
      if (MatchViewClass.getMatchId() == null || MatchViewClass.getMatchId().equals("")){
         isNewMatch = true;
      } else {
         isNewMatch = false;
         this.existingMatch = viaClubManagement.getMatchList().getMatchById(MatchViewClass.getMatchId());
      }
   }

   /**
    * Assigns values to a ComboBox
    * @param field to select the individual combobox
    * @param number of values to contain in two-digit format
    */
   private void assignComboBox(ComboBox<String> field, int number) {
      for (int i = 0; i < number; i++) {
         field.getItems().add(x2digits.format(i));
      }
   }

   /**
    * Action event listener to get the selected match type - Removes players if no type is selected
    * @param e
    */
   public void typeSelect(ActionEvent e) {
      if (!(typeField.getValue().toString().equals("none"))){
         if (typeField.getValue().toString().equals("cup") ||
                 typeField.getValue().toString().equals("league")) {
            removeSuspendedPlayers();
            if (typeField.getValue().toString().equals("cup"))
               numberOfBenchPlayers = 5;
            numberOfBenchPlayers = 4;
         }
         getAvailablePlayers();
      } else {
         if (assignedPlayers.size() > 0){
            availablePlayers.addAll(assignedPlayers);
            assignedPlayers.removeAll(assignedPlayers);
         }
         assignedData.clear();
         availableData.clear();
      }
   }

   /**
    * gets available players dependent on the match type
    */
   private void getAvailablePlayers() {
      ArrayList<Player> allPlayers = viaClubManagement.getPlayerList().getAllPlayers();
      availablePlayers = new ArrayList<Player>();
      if (typeField.getValue().toString().equals("friendly")) {
         for (Player player : allPlayers) {
            /*if (player.getAvailability() == null)
               continue;*/
            if ((player.getAvailability().getUnavailableType().equals(available) ||
                    player.getAvailability().getUnavailableType().equals(suspended)) &&
                    (!(assignedPlayers.contains(player)))){
               availablePlayers.add(player);
            }
         }
      } else {
         for (Player player : allPlayers) {
            if (player.getAvailability().getUnavailableType().equals(available) &&
                    (!(assignedPlayers.contains(player)))){
               availablePlayers.add(player);
            }
         }
      }
      initializeAvailableView();
   }

   /**
    * Initializes table view of available players
    */
   private void initializeAvailableView()
   {
      updateAvailableTableContent();

      availableNumber.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNumber()));
      availableName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtName()));
      availablePrefPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition().toString()));
      playedInRow.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNumber()));

      availableField.setItems(availableData);
   }

   /**
    * Updates the table content of available players
    */
   private void updateAvailableTableContent()
   {
      if (viaClubManagement.getPlayerList() != null) {

         availableData.clear();
         availableData.addAll(availablePlayers);

      }
   }

   /**
    * Initializes table view of assigned players
    */
   private void initializeAssignedView()
   {
      updateAssignedTableContent();

      assignedNumber.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNumber()));
      assignedName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtName()));
      assignedPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition().toString()));

      assignedField.setItems(assignedData);
   }

   /**
    * Updates the table content of assigned players
    */
   private void updateAssignedTableContent()
   {
      if (availablePlayers != null) {

         assignedData.clear();
         assignedData.addAll(assignedPlayers);

      }
   }

   /**
    * Assigns a player to a match
    * @param player to be assigned
    */
   private void assignPlayer(Player player){
      assignedPlayers.add(player);
      availablePlayers.remove(player);
      updateAvailableTableContent();
      initializeAssignedView();
   }

   /**
    * Unassigns a player from a match
    * @param player to be unassigned
    */
   private void unAssignPlayer(Player player){
      assignedPlayers.remove(player);
      availablePlayers.add(player);
      updateAvailableTableContent();
      initializeAssignedView();
   }

   /**
    * Unassigns an unavailable player from a match
    */
   private void removeSuspendedPlayers(){
      if (assignedPlayers.size() > 0){
         for (int i = 0; i < assignedPlayers.size(); i++){
            Player player = assignedPlayers.get(i);
            if (!(player.getAvailability().isPlayerAvailable())){
               unAssignPlayer(player);
            }
         }
      }
   }

   public void checkAssignedList(Player player){
      int fieldCount = 0;
      int benchCount = 0;
      int goalCount = 0;
      for (Player playerInList: assignedPlayers){
         if (playerInList.getPreferredPosition().equals(PositionType.bench)){
            benchCount ++;
         } else {
            fieldCount ++;
            if (playerInList.getPreferredPosition().equals(PositionType.goalkeeper)){
               goalCount ++;
            }
         }
      }
      if (assignedPlayers.size() < numberOfPitchPlayers){
         if (player.getPreferredPosition().equals(PositionType.goalkeeper)){
            if (goalCount == 0){
               assignPlayer(player);
            } else {
               if (benchCount < numberOfBenchPlayers){
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                          "You have already assigned one goalkeeper to the pitch \n" +
                                  "Do you want to assign the player to the bench?",
                          ButtonType.YES, ButtonType.NO);
                  alert.setTitle("Position confirmation");
                  alert.setHeaderText(null);

                  alert.showAndWait();

                  if (alert.getResult() == ButtonType.YES) {
                     player.setPreferredPosition(PositionType.bench);
                     assignPlayer(player);
                  } else {
                     return;
                  }
               } else {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION,
                          "All positions are taken for the chosen player",
                          ButtonType.CANCEL);
                  alert.setTitle("Full list");
                  alert.setHeaderText(null);

                  alert.showAndWait();

                  if (alert.getResult() == ButtonType.CANCEL) {
                     return;
                  }
               }

            }
         }
      }

   }



}
