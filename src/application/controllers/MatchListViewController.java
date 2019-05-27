package application.controllers;

import application.views.MatchListViewClass;
import application.views.MatchViewClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.soap.Node;
import java.io.IOException;

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
    }



}
