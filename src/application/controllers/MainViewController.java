package application.controllers;

import application.classes.VIAClubManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * Main view Controller is the class controller for main class
 * which is the first class to be run upon program start.
 *
 * @author Group-4
 * @version 1
 */

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
