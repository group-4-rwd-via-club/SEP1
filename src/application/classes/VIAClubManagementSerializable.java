package application.classes;

import java.io.Serializable;

public class VIAClubManagementSerializable implements Serializable {

    private PlayerList playerList;
    private MatchList matchList;

    public VIAClubManagementSerializable(PlayerList playerList, MatchList matchList)
    {
        this.playerList = playerList;
        this.matchList = matchList;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public MatchList getMatchList() {
        return matchList;
    }


}
