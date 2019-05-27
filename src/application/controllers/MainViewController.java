package application.controllers;

import application.classes.VIAClubManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainViewController {

    @FXML
    private TabPane tabPane;
    // Inject tab content.

    @FXML
    private Tab matchListTab;
    // Inject controller
    @FXML
    private MatchListViewController matchListViewController;

    // Inject tab content.
    @FXML
    private Tab playerListTab;
    // Inject controller
    @FXML
    private PlayerListViewController playerListViewController;




}
