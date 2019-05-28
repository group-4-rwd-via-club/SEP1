package application.classes;

import java.io.IOException;

/**
 * VIAClubManagement class holds player lists and match lists.
 * It is also the class which contains file logic
 * @author Group-4
 * @version 3
 *
 */

public class VIAClubManagement {

    private static MatchList matchList;
    private static PlayerList playerList;

    private FileAdapter fileAdapter;

    /**
     * Empty constructor which initialize matchlist and playerlist.
     * Runs load method to get all saved data
     */
    public VIAClubManagement()
    {
        matchList = new MatchList();
        playerList = new PlayerList();

        fileAdapter = new FileAdapter();

        load();
    }

    /**
     * Method to get the private field matchList.
     * @return returns object MatchList
     */
    public MatchList getMatchList()
    {
        return this.matchList;
    }

    /**
     * Method to get the private field playerList
     * @return returns object PlayerList
     */
    public PlayerList getPlayerList()
    {
        return this.playerList;
    }

    /**
     * Saves VIAClubManagement class onto a file
     * Exception if theres an IO error.
     */
    public void save()
    {
        //TODO: Uncommment the following part when adding and removing player / matches have been done.
        // meanwhile just system.out.println to indicate the code have been run.
        System.out.println("DATA HAVE BEEN SAVED.... not rly... lets pretend");
       /* try
        {
            fileAdapter.writeToFile(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
    }

    /**
     * Loads VIAClubManagement from database.bin file through FileAdapter.
     * adds the loaded data into matchlist and playerlist
     */
    public void load()
    {
        // TODO: uncomment this part when adding and removing player / matches have been done
/*
        try
        {
            VIAClubManagement vcm = (VIAClubManagement)fileAdapter.readObjectFromFile();
            this.matchList = vcm.getMatchList();
            this.playerList = vcm.getPlayerList();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
*/



        // Test data loaded into the lists
        // Load test Data simulates data from file:




        // TODO: autocomplete virker ikke
        // Players
        Player player1 = new Player("firstname1", "lastname1", 1, "Player1","Bench");
        player1.getAvailability().setUnavailableType(UnavailableType.suspended);
        Player player2 = new Player("firstname2", "lastname2", 2, "Player2","Field");
        Player player3 = new Player("firstname3", "lastname3", 3, "Player3","Field");
        Player player4 = new Player("firstname4", "lastname4", 4, "Player4","Field");
        Player player5 = new Player("firstname5", "lastname5", 5, "Player5","Field");
        Player player6 = new Player("firstname6", "lastname6", 6, "Player6","Bench");

        // adding to playerList
        playerList.addPlayer(player1);
        playerList.addPlayer(player2);
        playerList.addPlayer(player3);
        playerList.addPlayer(player4);
        playerList.addPlayer(player5);
        playerList.addPlayer(player6);
        



        // Matches
        Match match1 = new Match("Opponent1", MatchType.cup, "City1");
        match1.setDate(new Date(1,6,2019));
        match1.setScore("1:4");
        match1.setRoster(playerList);

        Match match2 = new Match("Opponent2", MatchType.league, "City2");
        match2.setDate(new Date(1,7,2019));

        Match match3 = new Match("Opponent3", MatchType.friendly, "City3");
        match3.setDate(new Date(1,8,2019));
        match3.setRoster(playerList);

        Match match4 = new Match("Opponent1", MatchType.friendly, "City4");
        match4.setDate(new Date(1,9,2019));
        match4.setScore("3:1");

        Match match5 = new Match("Opponent2", MatchType.cup, "City5");
        match5.setDate(new Date(1,10,2019));

        // adding to matchlist
        matchList.addMatch(match1);
        matchList.addMatch(match2);
        matchList.addMatch(match3);
        matchList.addMatch(match4);
        matchList.addMatch(match5);
    }




}
