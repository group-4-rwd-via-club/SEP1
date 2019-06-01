package application.classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PlayerList class respresent a class with contains all the players included
 * in the program.
 * serialVersionUID is added to each serializable class to make sure different machines generate
 * the same UID. https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 * @author Group 4
 * @version 2
 */

public class PlayerList implements Serializable
{

   private static final long serialVersionUID = 6529685098267757690L;

   private ArrayList<Player> players;

   /**
    * No-argument constructor initializing the PlayerList.
    */
   public PlayerList()
   {
      players = new ArrayList<>();
   }

   /**
    * Adds a player to the list
    *
    * @param player
    *           the player to add to the list
    */
   public void addPlayer(Player player)
   {
      players.add(player);
   }

   /**
    * remove individual player
    *
    * @param player
    *           the player to remove to the list
    */
   public void removePlayer(Player player)
   {
      players.remove(player);
   }

   /**
    * update and change individual player's detail
    * for update player
    *
    * @param player
    *           from Player class as input
    */
   public void updatePlayer(Player player)
   {

      try
      {
         for (int i = 0; i < players.size(); i++)
         {
            if (players.get(i).getId().equals(player.getId()))
            {
               players.set(i, player);
            }
         }
      }
      catch (NullPointerException e)
      {
         System.out.println("Error closing file ");
      }

   }


   @Override
   public String toString()
   {if(players.size()==0) {
      return "No Player in the list";
   }else {
      return "PlayerList [" + players + "]";}
   }

   /**
    * returns a list with all players in playerlist
    * @return returns an arraylist with all players
    */
   public ArrayList<Player> getAllPlayers()
   {
      return this.players;
   }

   /**
    * Finds a player with a matching ID and returns the object
    * @param id as a UUID String
    * @return Player object if found or null if not found
    */
   public Player getPlayerById(String id)
   {
      for (Player player : players)
      {
         if (player.getId().equals(id))
            return player;
      }
      return null;
   }

   /**
    * Check if input player object number already exists in players list.
    * @param player object
    * @return false if a player number already exists in players. True if it does not exist.
    */
   public boolean isPlayerNumberUnique(Player player)
   {
      for (Player p : players)
      {
         if (player.getNumber() == p.getNumber())
         {
            return false;
         }
      }
      return true;
   }
   /**
    * 
    * @return size length  of players
    */
   
   public int size() {
      return players.size();
   }

}
