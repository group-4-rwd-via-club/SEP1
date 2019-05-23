package application.classes;

import java.util.Arrays;
/**
 * Class which contains all player information
 * @author Group-4
 *@version 1
 */
public class Player
{
   private String id;
   private String firstname;
   private String lastname;
   private int number;
   private String shirtName;
   private String preferredPosition;
   private int matchesInRow;
   private Availability availability;
/**
 * 2arg constructor to initialize fields:firstname, lastname
 * @param firstname
 * @param lastname
 */
   public Player(String firstname, String lastname)
   {
      this.firstname = firstname;
      this.lastname = lastname;
   }

   public Player(String firstname, String lastname, int number,
         String shirtName, String preferredPosition)
   {

      this.firstname = firstname;
      this.lastname = lastname;
      this.number = number;
      this.shirtName = shirtName;
      this.preferredPosition = preferredPosition;

   }

   public Player()
   {
      // TODO Auto-generated constructor stub
   }

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public int getNumber()
   {
      return number;
   }

   public void setNumber(int number)
   {
      this.number = number;
   }

   public String getShirtName()
   {
      return shirtName;
   }

   public void setShirtName(String shirtName)
   {
      this.shirtName = shirtName;
   }

   public String getPreferredPosition()
   {
      return preferredPosition;
   }

   public void setPreferredPosition(String preferredPosition)
   {
      this.preferredPosition = preferredPosition;
   }

   public int getMatchesInRow()
   {
      return matchesInRow;
   }

   public Availability getAvailability()
   {
      return availability;
   }

 

   @Override
   public String toString()
   {
      return "Player [id=" + id + ", firstname=" + firstname + ", lastname="
            + lastname + ", number=" + number + ", shirtName=" + shirtName
            + ", preferredPosition=" + preferredPosition + ", matchesInRow="
            + matchesInRow + "]";
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Player))
      {
         return false;
      }
      Player other = (Player) obj;

      return id.equals(other.id) && firstname.equals(other.firstname)
            && lastname.equals(other.lastname)
            && shirtName.equals(other.shirtName) && number == other.number
            && preferredPosition.equals(other.preferredPosition)
            && matchesInRow == other.matchesInRow;
   }

}
