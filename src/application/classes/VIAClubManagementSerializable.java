package application.classes;

import java.io.Serializable;

/**
 * Class used to serialize and store matchlist and playerlist.
 *
 * serialVersionUID is added to each serializable class to make sure different machines generate
 * the same UID. https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 *
 * @author Group-4
 * @version 1
 */

public class VIAClubManagementSerializable implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private PlayerList playerList;
    private MatchList matchList;

    /**
     * public constructor with playerlist and matchlist as arguments. Saved to private fields.
     * @param playerList from VIAClubManagement
     * @param matchList from VIAClubManagement
     */
    public VIAClubManagementSerializable(PlayerList playerList, MatchList matchList)
    {
        this.playerList = playerList;
        this.matchList = matchList;
    }

    /**
     * public getter for playerList private field
     * @return returns playerlist as PlayerList type
     */
    public PlayerList getPlayerList() {
        return playerList;
    }

    /**
     * public getter for matchList private field
     * @return returns matchlist as MatchList type
     */
    public MatchList getMatchList() {
        return matchList;
    }


}
