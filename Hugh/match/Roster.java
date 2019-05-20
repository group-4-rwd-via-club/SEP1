package match;

import java.util.ArrayList;

import player.Player;
import player.PlayerList;

public class Roster extends PlayerList
{
   private ArrayList<Player> roster;
   
   public Roster()
   {
      roster = new ArrayList<Player>();
   }
   
   public void generateList()
   {
      ArrayList<Player> canPlay = new ArrayList<Player>();
      /*if(Player[i].getStatus().equals("Available"))
      {
         canPlay.add(null);
      }*/
   }
}
