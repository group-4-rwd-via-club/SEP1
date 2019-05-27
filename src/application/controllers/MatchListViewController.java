package application.controllers;

import application.classes.Date;
import application.classes.Match;
import application.classes.MatchList;
import application.classes.MatchType;
import application.views.MatchViewClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

        Match match1 = new Match("opponent", MatchType.cup, "Horsens");
        match1.setDate(new Date().getToday());
        match1.setScore("WE WON");




        ArrayList<Match> na = new ArrayList<>();
        na.add(match1);
        updateTableContent(na);

    }

    private void updateTableContent(ArrayList<Match> matchList)
    {

        ObservableList<Match> data = FXCollections.observableArrayList();
        data.addAll(matchList);

        tableView.setItems(data);
    }


}
