package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


// Remember to add the controller to the view as controller. See ExampleView.fxml code.
public class ExampleViewController {

    // Add every element added in scenebuilder and call it by their ID. example here:


    @FXML
    private TextField exampleText;

    @FXML
    private Button exampleButton;

    // Add a public no-args constructor
    public ExampleViewController()
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


