package application.controllers;

import application.Main;
import application.classes.Match;
import application.classes.MatchList;
import application.classes.VIAClubManagement;
import application.views.MatchViewClass;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MatchListViewController {
    @FXML
    // The reference of searchText will be injected by the FXML loader
    private TextField searchText;

    // The reference of addButton will be injected by the FXML loader
    @FXML
    private Button addButton;

    // The reference of printButton will be injected by the FXML loader
    @FXML
    private Button printButton;

    // The reference of tableView will be injected by the FXML loader
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Match, String> matchDate;

    @FXML
    private TableColumn<Match, String> matchType;

    @FXML
    private TableColumn<Match, String> matchLocation;

    @FXML
    private TableColumn<Match, String> matchOpponent;

    @FXML
    private TableColumn<Match, Number> matchRoster;

    @FXML
    private TableColumn<Match, String> matchResult;


    private VIAClubManagement viaClubManagement;

    // Add a public no-args constructor
    public MatchListViewController()
    {
        viaClubManagement = new VIAClubManagement();
    }


    @FXML
    private void initialize()
    {
        addButton.setOnAction(o -> {
                MatchViewClass mt = new MatchViewClass();
                mt.start(new Stage());
        });

        searchText.textProperty().addListener((obs, oldText, newText) -> {
            if (!oldText.equals(newText))
                setFilteredData(newText);
        });

        tableView.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                String id = ((Match) tableView.getSelectionModel().getSelectedItem()).getId();
                MatchViewClass mt = new MatchViewClass(id);
                mt.start(new Stage());
            }
        });

        initializeTableView();
    }


    private ObservableList<Match> masterData = FXCollections.observableArrayList();

    private void initializeTableView()
    {
        updateTableContent();

        matchDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toStringShort()));
        matchType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatchType().toString()));
        matchLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        matchOpponent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpponent()));
        matchRoster.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoster().getAllPlayers().size()));
        matchResult.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getScore()));

        tableView.setItems(masterData);
    }


    private void updateTableContent()
    {
        if (viaClubManagement.getMatchList() != null) {

            masterData.clear();
            masterData.addAll(viaClubManagement.getMatchList().getAllMatches());

        }
    }




    private ObservableList<Match> filteredData = FXCollections.observableArrayList();
    private void setFilteredData(String keyword)
    {
        // if keyword is empty, display the entire dataset
        if (keyword.isEmpty())
        {
            // set table data to the master data set.
            tableView.setItems(masterData);
        }
        // Clear after each keyword is being typed
        filteredData.clear();



        // loop through dataset to find keyword
        for (Match match : viaClubManagement.getMatchList().getAllMatches())
        {
            // Java String indexOf() The java string indexOf() method returns index of given character value or substring. If it is not found, it returns -1. The index counter starts from zero.
            if (match.toString().toLowerCase().indexOf(keyword.toLowerCase()) != -1)
            {
                // added to filtered match list
                filteredData.add(match);
            }
        }
        // set table data to filtered data.
        tableView.setItems(filteredData);
    }


}
