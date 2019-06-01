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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
   private int numberOfBenchPlayers;
   private Match match;
   private boolean isNewMatch;
   private Date now;
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
      assignedPlayers = new ArrayList<>();

      if (isNewMatch){
         typeField.getSelectionModel().selectFirst();
         viaScoreField.setDisable(true);
         oppScoreField.setDisable(true);
         deleteButton.setDisable(true);
         printButton.setDisable(true);
         startHourField.setDisable(true);
         startMinuteField.setDisable(true);
      } else {
         deleteButton.setDisable(false);
         printButton.setDisable(false);
         if (match != null){
            if (match.getDate().isBefore(now)){
               viaScoreField.setDisable(false);
               oppScoreField.setDisable(false);
            } else {
               viaScoreField.setDisable(true);
               oppScoreField.setDisable(true);
            }
            if (!(match.getDate().toStringShort().equals("0-00-00"))){
               dateField.setValue(match.getDate().getAsLocalDate());
            }
            opponentField.setText(match.getOpponent());
            typeField.getSelectionModel().select(match.getMatchType());
            locationField.setText(match.getLocation());
            startHourField.getSelectionModel().select(match.getDate().getHour());
            startMinuteField.getSelectionModel().select(match.getDate().getMinute());
            viaScoreField.setValue(match.getViaScore());
            oppScoreField.setValue(match.getOppScore());
            assignedPlayers.addAll(match.getRoster().getAllPlayers());
            updateAvailablePlayers();
            updateAssignedPlayers();
         }
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

      viaScoreField.valueProperty().addListener((observable)->{
         if (viaScoreField.getValue() != null){
            oppScoreField.getSelectionModel().selectFirst();
         } });


      /**
       * Event handler for mouse clicks in the available player field
       */
      availableField.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            if (availableField.getSelectionModel().getSelectedItem() != null)
               checkAssignedList(availableField.getSelectionModel().getSelectedItem());
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
       * Event handler for save button - Saves the current data in as a Match in MatchList
       */
      saveButton.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown()) {
            saveOptionalFields(match);
         }
      });

      /**
       * Event handler for delete button - Deletes chosen match from the match list
       */
      deleteButton.setOnAction(event -> {
         deleteMatch();
      });

      /**
       * Event handler for print button - prints chosen match.
       */
      printButton.setOnAction(event -> {
         // Code for print goes here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      });
   }

   /**
    * No args constructor to initialise local playerList
    *
    */
   public MatchViewController()
   {
      viaClubManagement = new VIAClubManagement();
      now = new Date().getNow();
      if (MatchViewClass.getMatchId() == null || MatchViewClass.getMatchId().equals("")){
         isNewMatch = true;
         this.match = new Match();
      } else {
         isNewMatch = false;
         this.match = viaClubManagement.getMatchList().getMatchById(MatchViewClass.getMatchId());
      }
   }

   /**
    * Saves match details if entered
    * @param match to get details from
    */
   public void saveOptionalFields(Match match){
      match.setMatchType(typeField.getValue());
      if (dateField.getValue() != null){
         Date matchDate;
         if (startHourField.getValue() != null && startMinuteField.getValue() != null){
            matchDate = (new Date(dateField.getValue().getDayOfMonth(), dateField.getValue().getMonthValue(), dateField.getValue().getYear(), Integer.parseInt(startHourField.getValue()), Integer.parseInt(startMinuteField.getValue())));
         } else {
            matchDate = (new Date(dateField.getValue().getDayOfMonth(), dateField.getValue().getMonthValue(), dateField.getValue().getYear()));
         }
         if (matchDate.isBefore(now)){
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "You have chosen a date that is before today \n" +
                            "Do you want to save the date?",
                    ButtonType.YES, ButtonType.NO);
            alert.setTitle("Date confirmation");
            alert.setHeaderText(null);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
               match.setDate(matchDate);
            } else {
               return;
            }
         } else {
            match.setDate(matchDate);
         }
      }
       if (opponentField.getText().equals("") || opponentField.getText() == null){
         match.setOpponent("");
      } else{
         match.setOpponent(opponentField.getText());
      }
      if (locationField.getText().equals("") || locationField.getText() == null){
         match.setLocation("");
      } else {
         match.setLocation(locationField.getText());
      }
      if (viaScoreField.getValue() != null && oppScoreField.getValue() != null){
         match.setScore(viaScoreField.getValue(), oppScoreField.getValue());
      }
      if (assignedPlayers.size() > 0){
         PlayerList toRoster = new PlayerList();
         for (Player player : assignedPlayers){
            toRoster.addPlayer(player);
         }
         match.setRoster(toRoster);
      }
      if (isNewMatch){
         viaClubManagement.getMatchList().addMatch(match);
      } else {
         viaClubManagement.getMatchList().updateMatch(match);
      }
      viaClubManagement.save();
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

   /**
    * Deletes a chosen match form the match list
    */
   private void deleteMatch()
   {
      Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete this match? \n" +
              "This can not be undone", ButtonType.YES, ButtonType.NO);
      deleteAlert.setTitle("Position Information");
      deleteAlert.setHeaderText(null);

      deleteAlert.showAndWait();

      if (deleteAlert.getResult() == ButtonType.YES) {
         viaClubManagement.getMatchList().removeMatch(match);
         viaClubManagement.save();
         Stage stage = (Stage) deleteButton.getScene().getWindow();
         stage.close();
      } else {
         return;
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
      getTypeSelected();
      updateAvailablePlayers();
      updateAssignedPlayers();
   }


   /**
    * Checks the value of match type and sets the number of bench players accordingly (max 100)
    * @return the selected match type
    */
   private MatchType getTypeSelected(){
      if (typeField.getValue().equals(MatchType.friendly)){
         numberOfBenchPlayers = 100;
         return MatchType.friendly;
      } else if (typeField.getValue().equals(MatchType.cup)){
         numberOfBenchPlayers = 5;
         return MatchType.cup;
      } else if (typeField.getValue().equals(MatchType.league)){
         numberOfBenchPlayers = 4;
         return MatchType.league;
      } else {
         numberOfBenchPlayers = 0;
         return MatchType.none;
      }
   }

   /**
    * Updates and initializes the available player table
    */
   private void updateAvailablePlayers(){
      ArrayList<Player> allPlayers = viaClubManagement.getPlayerList().getAllPlayers();
      availablePlayers = new ArrayList<>();
      for (Player player : allPlayers){
         if (getTypeSelected().equals(MatchType.friendly)){
            if ((player.getAvailability().getUnavailableType().equals(available) || player.getAvailability().getUnavailableType().equals(suspended)) ){
               if (assignedPlayers.size() == 0){
                  availablePlayers.add(player);
               } else if (!(assignedPlayers.contains(player))){
                  availablePlayers.add(player);
               }
            }
         } else if (getTypeSelected().equals(MatchType.cup) || getTypeSelected().equals(MatchType.league)){
            removeUnallowedPlayers();
            if (player.getAvailability().getUnavailableType().equals(available)){
               if (assignedPlayers.size() == 0){
                  availablePlayers.add(player);
               } else if (!(assignedPlayers.contains(player))){
                  availablePlayers.add(player);
               }
            }
         }
      }
      for (Player playerInList : assignedPlayers){
         if (playerInList.idEquals(viaClubManagement.getPlayerList().getPlayerById(playerInList.getId()))){
            availablePlayers.remove(viaClubManagement.getPlayerList().getPlayerById(playerInList.getId()));
         }
      }
      initializeAvailableView();
   }

   /**
    * Updates and initializes the assigned player table
    */
   private void updateAssignedPlayers(){
      if (assignedPlayers.size() != 0){
         if (getTypeSelected().equals(MatchType.none))
            assignedPlayers.removeAll(assignedPlayers);

         updateAvailableTableContent();
         initializeAssignedView();
      }
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
      playedInRow.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getMatchesInRow(viaClubManagement.getMatchList())));

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
      assignedPlayers.add(player.copy());
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
      availablePlayers.add(viaClubManagement.getPlayerList().getPlayerById(player.getId()));
      updateAvailableTableContent();
      initializeAssignedView();
   }

   /**
    * Unassigns an suspended player or too many bench players from a cup or league match
    */
   private void removeUnallowedPlayers(){
      int benchCount = 0;
      if (assignedPlayers.size() > 0){
         for (int i = 0; i < assignedPlayers.size(); i++){
            Player player = assignedPlayers.get(i);
            if (!(viaClubManagement.getPlayerList().getPlayerById(player.getId()).getAvailability().isPlayerAvailable()))
               assignedPlayers.remove(player);
            if (player.getPreferredPosition().equals(PositionType.bench)){
               benchCount++;
               if (benchCount > numberOfBenchPlayers){
                  unAssignPlayer(player);
               }

            }

         }
      }
   }

   /**
    * Checks til players preferred position to determine if the position is available in the current lineup
    * @param player to be checked
    */
   public void checkAssignedList(Player player){
      int numberOfPitchPlayers = 11;
      int fieldCount = 0;
      int benchCount = 0;
      int goalCount = 0;
      for (Player playerInList: assignedPlayers){
         if (playerInList.getPreferredPosition().equals(PositionType.bench)){
            benchCount ++;
         } else {
            fieldCount ++;
            if (playerInList.getPreferredPosition().equals(PositionType.goalkeeper)) goalCount++;
         }
      }
      if (player.getPreferredPosition().equals(PositionType.bench) ||
              player.getPreferredPosition().equals(PositionType.none)){
         if (benchCount < numberOfBenchPlayers){
            if (player.getPreferredPosition().equals(PositionType.none)) {
               Alert noneAlert = new Alert(Alert.AlertType.INFORMATION,
                       "The player is not assgned a position + \n" +
                               "The player will be assigned to the bench",
                       ButtonType.OK);
               noneAlert.setTitle("Position Information");
               noneAlert.setHeaderText(null);

               noneAlert.showAndWait();

               if (noneAlert.getResult() == ButtonType.OK) {
                  if (benchCount < numberOfBenchPlayers) {
                     assignPlayer(player);
                     for (Player playerInList : assignedPlayers){
                        if (playerInList.equals(player)){
                           playerInList.setPreferredPosition(PositionType.bench);
                        }
                     }
                  } else return;
               }
            } else assignPlayer(player);
         } else {
            Alert benchOccAlert = new Alert(Alert.AlertType.INFORMATION,
                    "All bench positions are occupied",
                    ButtonType.OK);
            benchOccAlert.setTitle("Position Information");
            benchOccAlert.setHeaderText(null);

            benchOccAlert.showAndWait();

            if (benchOccAlert.getResult() == ButtonType.OK) return;
         }
      } else {
         if (fieldCount < numberOfPitchPlayers){
            if (player.getPreferredPosition().equals(PositionType.goalkeeper)){
               if (goalCount == 0) assignPlayer(player);
               else {
                  if (benchCount < numberOfBenchPlayers){
                     Alert goalAlert = new Alert(Alert.AlertType.CONFIRMATION,
                             "You have already assigned one goalkeeper to the pitch \n" +
                                     "Do you want to assign the player to the bench?",
                             ButtonType.YES, ButtonType.NO);
                     goalAlert.setTitle("Position confirmation");
                     goalAlert.setHeaderText(null);

                     goalAlert.showAndWait();

                     if (goalAlert.getResult() == ButtonType.YES) {
                        assignPlayer(player);
                        for (Player playerInList : assignedPlayers){
                           if (playerInList.equals(player)){
                              playerInList.setPreferredPosition(PositionType.bench);
                           }
                        }
                     } else return;
                  } else {
                     Alert occAlert = new Alert(Alert.AlertType.INFORMATION,
                             "All positions are occupied for the chosen player",
                             ButtonType.CANCEL);
                     occAlert.setTitle("Full list");
                     occAlert.setHeaderText(null);

                     occAlert.showAndWait();

                     if (occAlert.getResult() == ButtonType.CANCEL) return;
                  }
               }
            } else assignPlayer(player);
         } else {
            if (benchCount < numberOfBenchPlayers) {
               Alert pitchAlert = new Alert(Alert.AlertType.INFORMATION,
                       "All pitch positions are occupied \n" +
                               "The player will be assigned to the bench",
                       ButtonType.OK);
               pitchAlert.setTitle("Position Information");
               pitchAlert.setHeaderText(null);

               pitchAlert.showAndWait();

               if (pitchAlert.getResult() == ButtonType.OK) {
                  if (benchCount < numberOfBenchPlayers) {
                     assignPlayer(player);
                     for (Player playerInList : assignedPlayers){
                        if (playerInList.equals(player)){
                           playerInList.setPreferredPosition(PositionType.bench);
                        }
                     }
                  }
               } else {
                  Alert allOccAlert = new Alert(Alert.AlertType.INFORMATION,
                          "All positions are occupied \n" +
                                  "You can not assign any more players",
                          ButtonType.OK);
                  allOccAlert.setTitle("Position Information");
                  allOccAlert.setHeaderText(null);

                  allOccAlert.showAndWait();

                  if (allOccAlert.getResult() == ButtonType.OK) return;
               }
            }
         }
      }
   }

}
