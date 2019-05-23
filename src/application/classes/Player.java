package application.classes;

import java.util.Arrays;

public class Player
{
   private String id;
   private String name;
   private int number;
   private String shirt;
   private String position;
   // Jesper says there is another way to do "Status"
   // calling it status to reduce confusion with value "available"
   // name can be changed if unacceptable
   private String[] status = {"Available", "Injured"
         , "Suspended", "Other"};
   private int matches;
   
   public Player(String name, int number, String shirt
         , String position)
   {
      this.name = name;
      this.number = number;
      this.shirt = shirt;
      this.position = position;
      // to fix array
      status = new String[0];
      this.matches = 0;
   }
   
   public Player()
   {
      this.name = "";
      this.number = 0;
      this.shirt = "";
      this.position = "";
      // to fix array
      this.status = null;
      this.matches = 0;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getNumber()
   {
      return number;
   }

   public void setNumber(int number)
   {
      this.number = number;
   }

   public String getShirt()
   {
      return shirt;
   }

   public void setShirt(String shirt)
   {
      this.shirt = shirt;
   }

   public String getPosition()
   {
      return position;
   }

   public void setPosition(String position)
   {
      this.position = position;
   }

   public String[] getStatus()
   {
      return status;
   }

   public void setStatus(String[] status)
   {
      this.status = status;
   }

   public int getMatches()
   {
      return matches;
   }

   public void setMatches(int matches)
   {
      this.matches = matches;
   }
   public String getId() {
      return this.id;
   }
   
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
