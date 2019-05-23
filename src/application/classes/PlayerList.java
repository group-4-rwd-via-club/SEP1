package application.classes;

import java.util.ArrayList;

public class PlayerList
{
   /**
    * @author Group 4
    * @Version 1
    */
   private ArrayList<Player> players;
/**
 * No-argument constructor initializing the PlayerList.
 */
   public PlayerList()
   {
      players = new ArrayList<Player>();
   }
/**
 * Adds a player to the list
 * @param player the player to add to the list
 */
   public void addPlayer(Player player)
   {
      players.add(player);
   }

   /**
    * remove individual player
    * @param player the player to remove to the list
    */
   public void removePlayer(Player player)
   {
      players.remove(player);
   }

   /**
    * update and change individual player's detail
    *  TODO: make an exception class for update player
    *  
    */
   public void updatePlayer(Player player)
   {
      for (int i = 0; i < players.size(); i++)
      {
         if (players.get(i).getId().equals(player.getId()))
         {
            players.set(i, player);
         }
      }

   }

    /**
     * 
     * @param keyword
     * @return
     */
   public Player getPlayer(String keyword)
   {
      Player result = new Player();
      for (int i = 0; i < players.size(); i++)
      {
         if ((keyword.equals(players)))
         {
            result = players.get(i);
         }

      }
      return result;
   }

}
