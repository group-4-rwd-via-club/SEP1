package application.controllers;

import application.classes.Availability;
import application.classes.Match;
import application.classes.Player;
import application.classes.PlayerList;
import application.classes.VIAClubManagement;
import application.views.MatchViewClass;
import application.views.PlayerViewClass;
import javafx.beans.property.SimpleIntegerProperty;
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
   private TableView table;

   // table
   @FXML
   private TableColumn<Player, String> firstname;

   @FXML
   private TableColumn<Player, String> lastname;

   @FXML
   private TableColumn<Player, Number> number;

   @FXML
   private TableColumn<Player, String> shirt;

   @FXML
   private TableColumn<Player, String> position;

   @FXML
   private TableColumn<Availability, String> status;

   private PlayerList p;

   private VIAClubManagement viaClubManagement;


   // TODO How many matches a player were played

   @FXML
   private final ObservableList<Player> data = FXCollections.observableArrayList(
               new Player("Haocheng", "Zhang", 10, "HZ", "RF"),
               new Player("F1", "L1",1, "Player1", "RF")   ,
               new Player("F2", "L2", 2, "Player2", "RM")     ,
               new Player("F3", "l3", 3, "Player3", "CF")

         );


   public PlayerListViewController()
   {
      viaClubManagement = new VIAClubManagement();
   }

   @FXML
   private void initialize()

   {
      PlayerList p = new PlayerList();



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



      //list
//      table = new TableView<>();
//      firstname = new TableColumn<Player, String>();
//      lastname = new TableColumn<Player, String>();
//
//      shirt = new TableColumn<Player, String>();
//      position = new TableColumn<Player, String>();
//      status = new TableColumn<Availability, String>();
//
//      table.setEditable(true);


//      table.setOnMousePressed(e -> {
//         if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
//             String id = ((Player) table.getSelectionModel().getSelectedItem()).getId();
//             PlayerViewClass mt = new PlayerViewClass(id);
//             mt.start(new Stage());
//         }
//     });


//      TODO implement table with players list
//      initializeTableView();



       }

   //TODO lot of shit here
   private ObservableList<Player> masterData = FXCollections.observableArrayList();




   private void initializeTableView()
   {
       updateTableContent();

       firstname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname().toString()));
       lastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname().toString()));
       number.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumber()));
       shirt.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtName().toString()));
       position.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPreferredPosition().toString() ));
       status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));

       table.setItems(masterData);
   }
   private void updateTableContent()
   {
       if (viaClubManagement.getMatchList() != null) {

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
           table.setItems(data);
       }
       // Clear after each keyword is being typed
       filteredData.clear();



       // loop through dataset to find keyword
       for (Player match : p.getAllPlayers())
       {
           // Java String indexOf() The java string indexOf() method returns index of given character value or substring. If it is not found, it returns -1. The index counter starts from zero.
           if (match.toString().toLowerCase().indexOf(keyword.toLowerCase()) != -1)
           {
               // added to filtered match list
               filteredData.add(match);
           }
       }
       // set table data to filtered data.
       table.setItems(filteredData);
   }

}
