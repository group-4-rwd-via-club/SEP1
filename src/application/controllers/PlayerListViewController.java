package application.controllers;

import application.classes.*;
import application.views.PlayerViewClass;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Controller class for fxml gui controller.
 * @author Group-4
 * @version 2
 */
public class PlayerListViewController
{
   @FXML
   private TextField searchplayer;
   @FXML
   private Button add;
   @FXML
   private TableView table;
   @FXML
   private TableColumn<Player, String> textFieldFirstname;
   @FXML
   private TableColumn<Player, String> textFieldLastname;
   @FXML
   private TableColumn<Player, Integer> textFieldNumber;
   @FXML
   private TableColumn<Player, String> textFieldShirt;
   @FXML
   private TableColumn<Player, String> textFieldPosition;
   @FXML
   private TableColumn<Player, String> textFieldAvailable;
   @FXML
   private TableColumn<Player, Integer > textFieldMatches;

   private VIAClubManagement viaClubManagement;
   private ObservableList<Player> filteredData = FXCollections.observableArrayList();
   private ObservableList<Player> masterData = FXCollections.observableArrayList();

    /**
     * No arg public constructor. Initialize viaclub management
     */
   public PlayerListViewController()
   {
      viaClubManagement = new VIAClubManagement();
   }

    /**
     * Default initialization method. Used to add events to UI Elements
     * and update table.
     */
   @FXML
   private void initialize()
   {
       updatePlayerAvailability();
      
     /**
      *  event handler for mouse click the add button to open player window to add a new player
      */
      add.setOnAction(event -> {
         PlayerViewClass mt = new PlayerViewClass();
         Stage stage = new Stage();
          stage.setOnHidden(o -> updateTableContent());
         mt.start(stage);
      });

    /**
     * event handler for typing the keyword to search player(s) in the playerList
     */
      searchplayer.textProperty().addListener((obs, oldText, newText) -> {
         if (!oldText.equals(newText))
             setFilteredData(newText);
     });
      /**
       * event handler for double click the row of the player in the table to update player's detail
       */
      table.setOnMousePressed(e -> {
         if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            try {
             String id = ((Player) table.getSelectionModel().getSelectedItem()).getId();
             PlayerViewClass mt = new PlayerViewClass(id);
             Stage stage = new Stage();
             stage.setOnHidden(event -> updateTableContent());

             mt.start(stage);
            }
            catch (NullPointerException e1) {
               
            }
         }
     });


      initializeTableView();
   }
   
   
   /**
    * Initializes table view of players 
    */
   private void initializeTableView()
   {
        updateTableContent();

        textFieldFirstname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        textFieldLastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        textFieldNumber.setCellValueFactory(cellData ->  new SimpleObjectProperty<>(cellData.getValue().getNumber()));
        textFieldShirt.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtName()));
        textFieldPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition().toString() ));
        textFieldAvailable.setCellValueFactory(cellDate -> new SimpleStringProperty(cellDate.getValue().getAvailability().getUnavailableType().toString()));
        textFieldMatches.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getMatchesInRow(viaClubManagement.getMatchList())));

        table.setItems(masterData);
   }

    /**
     * Whenever the playerListView is initialised the player list is checked.
     * If a players unavailable period has ended the player is set as available.
     */
   private void updatePlayerAvailability(){
       Date now = new Date().getNow();
       ArrayList<Player> allplayers = viaClubManagement.getPlayerList().getAllPlayers();
       if (allplayers != null){
           for (Player playerInList : allplayers){
               if (!playerInList.getAvailability().isPlayerAvailable()){
                   if (playerInList.getAvailability().getUnavailableEnd() != null){
                       if (!now.isBefore(playerInList.getAvailability().getUnavailableEnd())){
                           Availability available = new Availability();
                           available.setUnavailableType(UnavailableType.available);
                           playerInList.setAvailability(available);
                       }
                   }

               }
           }
       }
   }
   
   /**
    * Updates the table content of available players
    */
   private void updateTableContent()
   {
       if (viaClubManagement.getPlayerList()!=null) {

           masterData.clear();
           masterData.addAll(viaClubManagement.getPlayerList().getAllPlayers());

       }
   }


   /**
    * search player by keyword
    * @param keyword Read keyword from textField: search
    */

   private void setFilteredData(String keyword)
   {
       // if keyword is empty, display the entire dataset
       if (keyword.isEmpty())
       {
           // set table data to the master data set.
           table.setItems(masterData);
       }
       // Clear after each keyword is being typed to ensure empty list with new keyword information.
       filteredData.clear();

       // loop through dataset to find keyword
       for (Player player : viaClubManagement.getPlayerList().getAllPlayers())
       {
           // Java String indexOf() The java string indexOf() method returns index of given character value or substring. If it is not found, it returns -1. The index counter starts from zero.
           if (player.toString().toLowerCase().contains(keyword.toLowerCase()))
           {
               // added to filtered match list
               filteredData.add(player);
           }
       }
       // set table data to filtered data.
       table.setItems(filteredData);
   }


}
