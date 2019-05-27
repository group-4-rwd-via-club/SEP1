package application.controllers;

import application.Main;
import application.classes.*;
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

import java.util.ArrayList;

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


    // Add a public no-args constructor
    public MatchListViewController()
    {
    }


    @FXML
    private void initialize()
    {
        addButton.setOnAction(o -> {
                MatchViewClass mt = new MatchViewClass();
                mt.start(new Stage());
        });

        TestMethod();
    }

    private void TestMethod()
    {
        matchDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toStringShort()));
        matchType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatchType().toString()));
        matchLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        matchOpponent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpponent()));
        matchRoster.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoster().getAllPlayers().size()));
        matchResult.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getScore()));


        updateTableContent();

    }

    private void updateTableContent()
    {
        if (VIAClubManagement.system.matchList != null) {
            ObservableList<Match> data = FXCollections.observableArrayList();
            data.addAll(VIAClubManagement.system.matchList.getAllMatches());


            tableView.setItems(data);
        }
    }


}
