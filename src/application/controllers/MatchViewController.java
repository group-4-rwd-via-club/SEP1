package application.controllers;

import application.Main;
import application.classes.MatchType;
import application.classes.Player;
import application.classes.PlayerList;
import application.classes.VIAClubManagement;
import javafx.beans.property.SimpleIntegerProperty;
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
// TODO: All classes and methods needs JavaDoc
public class MatchViewController
{
   private DecimalFormat x2digits = new DecimalFormat("00");

   private VIAClubManagement viaClubManagement;

   private ArrayList<Player> availablePlayers;
   private ArrayList<Player> assignedPlayers;

   private ObservableList<Player> availableData = FXCollections.observableArrayList();
   private ObservableList<Player> assignedData = FXCollections.observableArrayList();

   private ContextMenu assignMenu = new ContextMenu();
   private MenuItem assignGoalkeeper = new MenuItem("Assign as goalkeeper");
   private MenuItem assignDefence = new MenuItem("Assign as defence");
   private MenuItem assignMidfield = new MenuItem("Assign as midfield");
   private MenuItem assignForward = new MenuItem("Assign as forward");
   private MenuItem assignBench = new MenuItem("Assign to bench");


   // The reference of saveButton will be injected by the FXML loader
   @FXML
   private Button saveButton;

   // The reference of cancelButton will be injected by the FXML loader
   @FXML
   private Button cancelButton;

   // The reference of printButton will be injected by the FXML loader
   @FXML
   private Button printButton;

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

      assignComboBox(meetHourField, 24);
      assignComboBox(meetMinuteField, 60);
      assignComboBox(startHourField, 24);
      assignComboBox(startMinuteField, 60);
      assignComboBox(viaScoreField, 100);
      assignComboBox(oppScoreField, 100);

      assignedPlayers = new ArrayList<Player>();

      availableField.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            if (availableField.getSelectionModel().getSelectedItem() != null)
            assignPlayer(availableField.getSelectionModel().getSelectedItem());
         }
      });

      assignedField.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            if (assignedField.getSelectionModel().getSelectedItem() != null)
            unAssignPlayer(assignedField.getSelectionModel().getSelectedItem());
         }
      });
      //new match end
   }

   /**
    * No args constructor to initialise local playerList
    *
    */
   public MatchViewController()
   {
      viaClubManagement = new VIAClubManagement();

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
         }
         getAvailablePlayers();
      } else {
         availableData.clear();
         if (assignedPlayers.size() > 0){
            for (int i = 0; i < assignedPlayers.size(); i++){
               availablePlayers.add(assignedPlayers.get(i));
               assignedPlayers.remove(assignedPlayers.get(i));
            }
         }
         assignedData.clear();
      }
   }

   /**
    * gets available players dependent on the match type
    */
   public void getAvailablePlayers() {
      ArrayList<Player> allPlayers = viaClubManagement.getPlayerList().getAllPlayers();
      availablePlayers = new ArrayList<Player>();
      if (typeField.getValue().toString().equals("friendly")) {
         for (Player player : allPlayers) {
            /*if (player.getAvailability() == null)
               continue;*/
            if ((player.getAvailability().getUnavailableType().equals(none) ||
                    player.getAvailability().getUnavailableType().equals(suspended)) &&
                    (!(assignedPlayers.contains(player)))){
               availablePlayers.add(player);
            }
         }
      } else {
         for (Player player : allPlayers) {
            if (player.getAvailability().getUnavailableType().equals(none) &&
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
      availablePrefPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition()));
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
      assignedPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition()));

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
    * @param player
    */
   private void assignPlayer(Player player){
      // More Code Goes Here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      assignedPlayers.add(player);
      availablePlayers.remove(player);
      updateAvailableTableContent();
      initializeAssignedView();
   }

   /**
    * Unassigns a player from a match
    * @param player
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
         for (Player player : assignedPlayers){
            if (!(player.getAvailability().isPlayerAvailable())){
               unAssignPlayer(player);
            }
         }
      }
   }




}
