package application.controllers;

import application.classes.*;
import application.views.MatchViewClass;
import application.views.PlayerViewClass;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
   private TableView<Player> table;

   // table
   @FXML
   private TableColumn<Player, String> firstname;

   @FXML
   private TableColumn<Player, String> lastname;

   @FXML
   private TableColumn<Player, Integer> number;

   @FXML
   private TableColumn<Player, String> shirt;

   @FXML
   private TableColumn<Player, String> position;

   @FXML
   private TableColumn<Availability, String> status;

   private PlayerList p;

   private VIAClubManagement viaClubManagement;


   // TODO How many matches a player were played

   
 
    


   public PlayerListViewController()
   {
      
      viaClubManagement = new VIAClubManagement();
   }

   @FXML
   private void initialize()

   {
      PlayerList p = viaClubManagement.getPlayerList();
      table=new TableView<Player>();



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
      //TODO search player with exception
      searchplayer.textProperty().addListener((obs, oldText, newText) -> {
         if (!oldText.equals(newText))
             setFilteredData(newText);
     });



  


//      TODO implement table with players list
      initializeTableView();
      //test
      System.out.println(table.getItems().size());



       }

   //TODO lot of shit here
   private ObservableList<Player> masterData = FXCollections.observableArrayList();




   private void initializeTableView()
   {
       updateTableContent();

       firstname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname().toString()));
       lastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname().toString()));
       number.setCellValueFactory(cellData ->  new SimpleObjectProperty<Integer>(cellData.getValue().getNumber()));
       shirt.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtName().toString()));
       position.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition().toString() ));
       status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));

       table.setItems(masterData);
   }
   private void updateTableContent()
   {
     
       if (viaClubManagement.getPlayerList()!=null) {

           masterData.clear();
           masterData.addAll(viaClubManagement.getPlayerList().getAllPlayers());

       }
   }






   //search keyword from JH
   private ObservableList<Player> filteredData = FXCollections.observableArrayList();



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
       for (Player player : p.getAllPlayers())
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
