package application.controllers;

import application.classes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;


/** PlayerViewController is controller for player view. All FXML fields are being initialised in this class
 * event handleres are also being set in this class.
 * @author Group-4
 * @version 2
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
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private DatePicker datePickerEndDate;
    @FXML
    private Label labelEndDate;



    /**
     * default initialize method
     * initialize all fields and sets onAction events on buttons and fields.
     */
    @FXML
    private void initialize()
    {
        comboBoxPosition.getItems().setAll((Object[]) PositionType.values());
        comboBoxPosition.setValue(PositionType.none);
        comboBoxAvailability.getItems().setAll((Object[]) UnavailableType.values());
        comboBoxAvailability.setValue(UnavailableType.available);
        comboBoxAvailability.setOnAction(event -> setAvailabilitySelectionMode());


        buttonSave.setOnAction(event -> {
            if (player != null)
            {
                updatePlayer();
            }
            else
            {
                addPlayer();
            }


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
     * No arg constructor to initialise viaclub management in this class
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
            if (player.getNumber() == -1)
            {
                textFieldNumber.setText("");
            }
            else
            {
                textFieldNumber.setText(Integer.toString(player.getNumber()));
            }
            textFieldShirt.setText(player.getShirtName());
            comboBoxPosition.setValue(player.getPreferredPosition());
            comboBoxAvailability.setValue(player.getAvailability().getUnavailableType());
            if (player.getAvailability() != null && player.getAvailability().getUnavailableEnd() != null)
            {
                System.out.println(player.getAvailability().getUnavailableEnd().getAsLocalDate());
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
     * and saves it to disk through viaClubManagement save method.
     */
    private void addPlayer()
    {
        
        Player newPlayer = new Player();
        setPlayerDetails(newPlayer);
        viaClubManagement.getPlayerList().addPlayer(newPlayer);
        viaClubManagement.save();

//TODO         
        if(isTheSameNumber(newPlayer)) {
        
           Alert alert = new Alert(Alert.AlertType.WARNING,"Error: This number has already been assigned.Please delete");
           alert.setTitle("Alert");
           alert.setHeaderText(null);
           alert.showAndWait();
           
           
        }
    }

    /**
     * setPlayerDetails sets all the details in the view fields on the input object.
     * @param newPlayer which is a Player object
     */
    private void setPlayerDetails(Player newPlayer) {
        newPlayer.setFirstname(textFieldFirstName.getText());
        newPlayer.setLastname(textFieldLastName.getText());

        if (textFieldNumber.getText().isEmpty())
        {
            newPlayer.setNumber(-1);
        }
        else
        {
            newPlayer.setNumber(Integer.parseInt(textFieldNumber.getText()));
        }
         
         
           
          
            
        

        newPlayer.setShirtName(textFieldShirt.getText());
        newPlayer.setPreferredPosition((PositionType)comboBoxPosition.getValue());
        Availability availability = new Availability();
        availability.setUnavailableType((UnavailableType)comboBoxAvailability.getValue());
        LocalDate pickedDate = datePickerEndDate.getValue();

        if (pickedDate == null)
        {
            availability.setUnavailableEnd(null);
        }
        else
        {
            availability.setUnavailableEnd(new Date(pickedDate.getDayOfMonth(), pickedDate.getMonthValue(), pickedDate.getYear()));
        }


        newPlayer.setAvailability(availability);
    }

    /**
     * updatePlayer method update a player based on details changed in fields of the view as an entire player object.
     * Saves the data to disk through viaClubManagement save method.
     */
    private void updatePlayer()
    {
        setPlayerDetails(player);

        viaClubManagement.getPlayerList().updatePlayer(player);
        viaClubManagement.save();
    }

    /**
     * Delete player shows a alert dialog which prompts the user if the
     * user wants to continue. If yes is pressed, the method continues
     * to remove the player from the playerList in viaClubManagement class.
     */
    private void deletePlayer()
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the player?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            viaClubManagement.getPlayerList().removePlayer(player);
            viaClubManagement.save();
        }


    }
    
    private boolean isTheSameNumber(Player newPlayer) {
       PlayerList p = viaClubManagement.getPlayerList();
       for (int i = 0; i < p.size()-1; i++)
      {
         if (newPlayer.getNumber()==p.getAllPlayers().get(i).getNumber())
         {
            return true;
         }
      }
       return false;
    }




}
