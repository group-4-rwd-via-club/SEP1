package application.classes;

import java.io.File;
import java.io.IOException;

/**
 * VIAClubManagement class holds player lists and match lists.
 * It is also the class which contains file logic
 * @author Group-4
 * @version 5
 *
 */

public class VIAClubManagement {

    private static MatchList matchList;
    private static PlayerList playerList;

    private static boolean isInitialized;

    private FileAdapter fileAdapter;

    /**
     * Empty constructor which initialize matchlist and playerlist.
     * Runs load method to get all saved data
     * Also check if isInitialized is false,
     * if it is false, then run Init() and load()
     */
    public VIAClubManagement()
    {
        fileAdapter = new FileAdapter();

        if (!isInitialized)
        {
            Init();
            load();
        }

    }

    /**
     * Method to initialise matchlist and playerlist
     * sets boolean isInitialized to true to prevent
     * the two lists from being initialized and loaded multiple times.
     */
    private void Init()
    {
        isInitialized = true;
        matchList = new MatchList();
        playerList = new PlayerList();
    }
    /**
     * Method to get the private field matchList.
     * @return returns object MatchList
     */
    public MatchList getMatchList()
    {
        return matchList;
    }

    /**
     * Method to get the private field playerList
     * @return returns object PlayerList
     */
    public PlayerList getPlayerList()
    {
        return playerList;
    }

    /**
     * Saves VIAClubManagement class onto a file
     * Exception if theres an IO error.
     */
    public void save()
    {
        try
        {

            fileAdapter.writeToFile(new VIAClubManagementSerializable(playerList, matchList));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Loads VIAClubManagementSerializable from database.bin file through FileAdapter.
     * And converts the loaded data into matchlist and playerlist
     * if file does not exist it returns without trying
     */
    private void load()
    {
        if (!(new File(fileAdapter.getFileName()).exists()))
        {
            System.out.println("File does not exists");
            return;
        }


        try
        {
            VIAClubManagementSerializable vcm = (VIAClubManagementSerializable)fileAdapter.readObjectFromFile();
            matchList = vcm.getMatchList();
            playerList = vcm.getPlayerList();

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }




}
