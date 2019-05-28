package application.classes;

public class VIAClubManagement {

    private MatchList matchList;
    private PlayerList playerList;


    /**
     * Empty constructor which initialize matchlist and playerlist.
     * Runs load method to get all saved data
     */
    public VIAClubManagement()
    {
        matchList = new MatchList();
        playerList = new PlayerList();
        load();
    }


    public MatchList getMatchList()
    {
        return this.matchList;
    }

    public PlayerList getPlayerList()
    {
        return this.playerList;
    }

    public void save()
    {
        // TODO: Logic for saving to fileIO
    }

    public void load()
    {
        // TODO: Logic for loading from fileIO

        // Test data loaded into the lists
        // Load test Data simulates data from file:




        // TODO: autocomplete virker ikke
        // Players
        Player player1 = new Player("firstname1", "lastname1", 1, "Player1","Bench");
        player1.getAvailability().setUnavailableType(UnavailableType.injured);
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
