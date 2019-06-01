package application.controllers;

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
    @FXML
    private Tab matchListTab;
    @FXML
    private MatchListViewController matchListViewController;
    @FXML
    private Tab playerListTab;
    @FXML
    private PlayerListViewController playerListViewController;

}
