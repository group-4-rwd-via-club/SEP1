package application.controllers;

import application.classes.*;
//import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlayerViewController {

    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private TextField textFieldNumber;
    @FXML
    private TextField textFieldShirt;

    @FXML
    private ComboBox comboBoxPosition;
    @FXML
    private ComboBox comboBoxAvailability;

    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonPrint;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private DatePicker datePickerEndDate;
    @FXML
    private Label labelEndDate;

    @FXML
    private void initialize()
    {
        comboBoxPosition.getItems().setAll((Object[]) PositionType.values());
        comboBoxPosition.setValue(PositionType.none);
        comboBoxAvailability.getItems().setAll((Object[]) UnavailableType.values());
        comboBoxAvailability.setValue(UnavailableType.available);
        comboBoxAvailability.setOnAction(event -> setAvailabilitySelectionMode());


        buttonSave.setOnAction(event -> {
            addPlayer();

            Stage stage = (Stage) buttonSave.getScene().getWindow();
            stage.close();
        });
        buttonCancel.setOnAction(event -> {
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
        });
        buttonDelete.setOnAction(event -> {
            deletePlayer();

            Stage stage = (Stage) buttonDelete.getScene().getWindow();
            stage.close();
        });

        // force only numeric input
        textFieldNumber.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        setPlayerInformation();
        setAvailabilitySelectionMode();
    }

    /**
     * No arg constructor to initialise viaclub management and set player as null.
     */

    public PlayerViewController()
    {
        viaClubManagement = new VIAClubManagement();

    }

    private Player player;

    /**
     * Sets player for edit window. If id is provided as argument
     * the player id will be searched for in playerlist
     * if found, sets player object.
     * @param id as player id.
     */
    public void setPlayer(String id)
    {
        player = viaClubManagement.getPlayerList().getPlayerById(id);

    }

    /**
     * Sets information if player object is available
     * is being used to display editable data.
     */
    private void setPlayerInformation()
    {
        if (player != null)
        {
            textFieldFirstName.setText(player.getFirstname());
            textFieldLastName.setText(player.getLastname());
            textFieldNumber.setText(Integer.toString(player.getNumber()));
            textFieldShirt.setText(player.getShirtName());
            comboBoxPosition.setValue(player.getPreferredPosition());
            comboBoxAvailability.setValue(player.getAvailability().getUnavailableType());
            if (player.getAvailability() != null && player.getAvailability().getUnavailableEnd() != null)
            {
                datePickerEndDate.setValue(player.getAvailability().getUnavailableEnd().getAsLocalDate());
            }
        }
    }


    /**
     * Sets availability combobox based on availability status
     */
    private void setAvailabilitySelectionMode()
    {
        boolean visibility = (comboBoxAvailability.getValue() != UnavailableType.available);
        datePickerEndDate.setVisible(visibility);
        labelEndDate.setVisible(visibility);
    }
    private VIAClubManagement viaClubManagement;

    /**
     * adds a player to playerList in viaclub
     */
    private void addPlayer()
    {
        Player player = new Player();
        player.setFirstname(textFieldFirstName.getText());
        player.setLastname(textFieldLastName.getText());
        player.setNumber(Integer.parseInt(textFieldNumber.getText()));
        player.setShirtName(textFieldShirt.getText());
        player.setPreferredPosition((PositionType)comboBoxPosition.getValue());
        Availability availability = new Availability();
        availability.setUnavailableType((UnavailableType)comboBoxAvailability.getValue());
        LocalDate pickedDate = datePickerEndDate.getValue();

        if (pickedDate == null)
        {
            availability.setUnavailableEnd(null);
        }
        else
        {
            availability.setUnavailableEnd(new Date(pickedDate.getDayOfMonth(), pickedDate.getMonthValue(), pickedDate.getDayOfYear()));
        }


        player.setAvailability(availability);


        viaClubManagement.getPlayerList().addPlayer(player);


    }


    private void deletePlayer()
    {
        // alert dialog confirmation

        // delete logic

        viaClubManagement.getPlayerList().removePlayer(player);


    }




}
