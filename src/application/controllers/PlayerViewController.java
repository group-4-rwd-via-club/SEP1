package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PlayerViewController {

    @FXML
    private TextField exampleText;

    @FXML
    private Button exampleButton;

    // Add a public no-args constructor
    public PlayerViewController()
    {
    }

    // initialize will be run on first run. This can be used to initialise text or load data
    // for the specific view.
    @FXML
    private void initialize()
    {

        exampleText.setText("TEST");
    }
}
