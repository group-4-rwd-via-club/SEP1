package application.controllers;

import application.classes.*;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *  Controller for Player view
 * @author Group 4
 * @version 1
 *
 */
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
        setAvailabilitySelectionMode();

        buttonSave.setOnAction(event -> addPlayer());
        buttonCancel.setOnAction(event -> { Platform.exit(); });
        buttonDelete.setOnAction(event -> deletePlayer());
    }

    /**
     * No arg constructor to initialise viaclub management and set player as null.
     */
    public PlayerViewController()
    {
        viaClubManagement = new VIAClubManagement();
        player = null;
    }

    private Player player;

    public PlayerViewController(String playerId)
    {
        viaClubManagement = new VIAClubManagement();
        player = viaClubManagement.getPlayerList().getPlayerById(playerId);
        setPlayerInformation();
    }

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


    private void setAvailabilitySelectionMode()
    {
        boolean visibility = (comboBoxAvailability.getValue() != UnavailableType.available);
        datePickerEndDate.setVisible(visibility);
        labelEndDate.setVisible(visibility);
    }
    private VIAClubManagement viaClubManagement;

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

        Platform.exit();
    }


    private void deletePlayer()
    {
        // alert dialog confirmation

        // delete logic

        viaClubManagement.getPlayerList().removePlayer(player);
    }




}
