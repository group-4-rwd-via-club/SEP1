package application.classes;

import java.util.Arrays;

public class Player
{
   private String id;
   private String firstname;
   private String Lastname;
   private int number;
   private String shirtName;
   private String preferredPosition;
   private int matchesInRow;
 
 
 
   
  
 
   
   public boolean copy(Object obj)
   {
      if(!(obj instanceof Player))
      {
         return false;
      }
      Player other = (Player)obj;
      return this.name.equalsIgnoreCase(other.name)
            && this.number == other.number
            && this.shirt.equalsIgnoreCase(other.shirt)
            && this.position.equalsIgnoreCase(other.position)
            // to fix array
            && this.status.equals(other.status)
            && this.matches == other.matches;
   }


   public String toString()
   {
      return "Player [name=" + name + ", number=" + number + ", shirt=" + shirt
            + ", position=" + position + ", status=" + Arrays.toString(status)
            + ", matches=" + matches + "]";
   }
}
