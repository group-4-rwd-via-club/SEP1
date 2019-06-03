package application.controllers;

import application.classes.Match;
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

/**
 * Controller for MatchListView.
 * @author Group-4
 * @version 1
 */
public class MatchListViewController {
    @FXML
    private TextField searchText;
    @FXML
    private Button addButton;
    @FXML
    private TableView<Match> tableView;
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

    private final VIAClubManagement viaClubManagement;

    /**
     * public constructor for MatchListViewController
     * sets viaclubmanagement field.
     */
    public MatchListViewController()
    {
        viaClubManagement = new VIAClubManagement();
    }


    /**
     * FXML method which initialises upon load. Method is used to set event handlers
     * for buttons and properties like search text and mouse press.
     */
    @FXML
    private void initialize()
    {
        addButton.setOnAction(o -> {
                MatchViewClass mt = new MatchViewClass();
                Stage stage = new Stage();
                stage.setOnHidden(event -> updateTableContent());
                mt.start(stage);
        });

        searchText.textProperty().addListener((obs, oldText, newText) -> {
            if (!oldText.equals(newText))
                setFilteredData(newText);
        });

        tableView.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                if (tableView.getSelectionModel().getSelectedItem() != null){
                    String id = (tableView.getSelectionModel().getSelectedItem()).getId();
                    MatchViewClass mt = new MatchViewClass(id);
                    Stage stage = new Stage();
                    stage.setOnHidden(event -> updateTableContent());
                    mt.start(stage);
                }
            }
        });

        initializeTableView();
    }
    private final ObservableList<Match> masterData = FXCollections.observableArrayList();
    /**
     * Method to initialise Tableview by setting cellvaluefactory to the correct type
     * The method also sets the observable list as item and updates the table content.
     */
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
    /**
     * Method to update tableview content.
     * It replace the data in observable list with the updated list.
     */
    private void updateTableContent()
    {
        if (viaClubManagement.getMatchList() != null) {
            masterData.clear();
            masterData.addAll(viaClubManagement.getMatchList().getAllMatches());
        }
    }
    private final ObservableList<Match> filteredData = FXCollections.observableArrayList();
    /**
     * Method to find matches that contains string keywords and
     * add them to a new list which is presented for the user.
     * @param keyword as a string the method should search for.
     */
    private void setFilteredData(String keyword)
    {
        if (keyword.isEmpty())
        {
            tableView.setItems(masterData);
        }
        filteredData.clear();
        for (Match match : viaClubManagement.getMatchList().getAllMatches())
        {
            if (match.toString().toLowerCase().contains(keyword.toLowerCase()))
            {
                filteredData.add(match);
            }
        }
        tableView.setItems(filteredData);
    }


}
