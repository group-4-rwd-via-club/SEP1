package application.controllers;

import application.classes.*;
import application.views.MatchViewClass;
import application.views.PlayerViewClass;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PlayerListViewController
{
   @FXML
   // The reference of searchText will be injected by the FXML loader
   private TextField searchplayer;

   // The reference of addButton will be injected by the FXML loader
   @FXML
   private Button add;

   // The reference of tableView will be injected by the FXML loader
   @FXML
   private TableView table;

   // table
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
   private TableColumn<Match, Integer > textFieldMatches;


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

   @FXML
   private void initialize()
   {
      
 /**
  *  event handler for mouse click the add button to open player window to add a new player
  */
      add.setOnAction(event -> {
         PlayerViewClass open = new PlayerViewClass();
         Stage stage = new Stage();
          stage.setOnHidden(o -> {
              updateTableContent();
          });
         open.start(stage);
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
             stage.setOnHidden(event -> {
                 updateTableContent();
             });

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
        textFieldNumber.setCellValueFactory(cellData ->  new SimpleObjectProperty<Integer>(cellData.getValue().getNumber()));
        textFieldShirt.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtName()));
        textFieldPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition().toString() ));
        textFieldAvailable.setCellValueFactory(cellDate -> new SimpleStringProperty(cellDate.getValue().getAvailability().getUnavailableType().toString()));

        table.setItems(masterData);
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
       // Clear after each keyword is being typed
       filteredData.clear();

       // loop through dataset to find keyword
       for (Player player : viaClubManagement.getPlayerList().getAllPlayers())
       {
           // Java String indexOf() The java string indexOf() method returns index of given character value or substring. If it is not found, it returns -1. The index counter starts from zero.
           if (player.toString().toLowerCase().indexOf(keyword.toLowerCase()) != -1)
           {
               // added to filtered match list
               filteredData.add(player);
           }
       }
       // set table data to filtered data.
       table.setItems(filteredData);
   }


}
