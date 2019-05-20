package player;

import java.util.ArrayList;

public class PlayerList
{
   protected ArrayList<Player> players;
   private int temp;

   public PlayerList()
   {  
      players = new ArrayList<Player>();
   }

   public void fillPlayerList()
   {
      temp = 0;
      for(int i=0; i<temp; i++)
      {
         players.add(null);
      }     
   }
}
