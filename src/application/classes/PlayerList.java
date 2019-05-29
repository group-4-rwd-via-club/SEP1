package application.classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PlayerList class respresent a class with contains all the players included
 * in the program.
 *
 * @author Group 4
 * @Version 2
 */

public class PlayerList implements Serializable
{

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
    * update and change individual player's detail TODO: make an exception class
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
    * Get a player
    * 
    * @param keyword
    *           search a player by keyword
    * @return result with player's detail
    */
   public Player getPlayer(String keyword)
   {
      Player result = new Player();

      for (int i = 0; i < players.size(); i++)
      {
         if ((keyword.equals(players)))
         {
            result = players.get(i);
         }else {
            System.out.println("Type the right name");
         }

      }
      return result;
   }

   /**
    * Finds a player with a matching ID and returns the object
    * @param id as a UUID String
    * @returns Player object if found or null if not found
    */
   public Player getPlayerById(String id)
   {
      for (Player player : players)
      {
         if (player.getId() == id)
            return player;
      }
      return null;
   }

   @Override
   public boolean equals(Object obj)
   {
       
      return super.equals(obj);
   }
   

}
