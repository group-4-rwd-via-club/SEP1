package application.classes;

import java.io.Serializable;

/**
 * Class which contains all player information
 *
 * serialVersionUID is added to each serializable class to make sure different machines generate
 * the same UID. https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 *
 * @author Group-4
 * @version 5
 */
public class Player implements Serializable
{
   private static final long serialVersionUID = 6529685098267757690L;

   private String id;
   private String firstname;
   private String lastname;
   private int number;
   private String shirtName;
   private PositionType preferredPosition;
   private Availability availability;

   /**
    * 2arg constructor to initialize fields:firstname, lastname
    *
    * @param firstname
    * @param lastname
    */
   public Player(String firstname, String lastname)
   {
      this.firstname = firstname;
      this.lastname = lastname;
      this.availability = new Availability();
      this.id = StringUUID.generateId();
   }

   /**
    * 5arg constructor to initialize
    * :firstname,lastname,shirtName,preferredPosition
    */

   public Player(String firstname, String lastname, int number,
         String shirtName, PositionType preferredPosition)
   {

      this.firstname = firstname;
      this.lastname = lastname;
      this.number = number;
      this.shirtName = shirtName;
      this.preferredPosition = preferredPosition;
      this.availability = new Availability();
      this.id = StringUUID.generateId();
   }
   /**
    * 1agr constructor which initialize:
    * Player firstname,lastname ,shirtName ,preferredPosition ,availability
    */
   public Player(Player player)
   {
      this.firstname = player.firstname;
      this.lastname = player.lastname;
      this.number = player.number;
      this.shirtName=player.shirtName;
      this.preferredPosition=player.preferredPosition;
      this.availability=player.availability;

      this.id = StringUUID.generateId();
   }
   /**
    * 7 agr constructor which initialize :
    * id firstname lastname number shirtname preferredPosition matchInRow
    * @param id
    * @param firstname
    * @param lastname
    * @param number
    * @param shirtName
    * @param preferredPosition
    */
    
   public Player(String id, String firstname, String lastname, int number,
         String shirtName, PositionType preferredPosition)
   {
      
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.number = number;
      this.shirtName = shirtName;
      this.preferredPosition = preferredPosition;
   }

   /**
    * Empty constructor which initialize:
    * Player id
    */
   public Player()
   {
      this.id = StringUUID.generateId();
   }
   /**
    * Gets the ID for the current player.
    * @returns private field ID.
    */
   public String getId()
   {
      return id;
   }
 /**
  * get firstname
  * @return firstname as a string 
  */
   public String getFirstname()
   {
      return firstname;
   }
/**
 * sets the firstname 
 * @param firstname as a string
 */
   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }
/**
 * get the  lastname 
 * @return lastname as string 
 */
   public String getLastname()
   {
      return lastname;
   }
/**
 * sets the lastname
 * @param lastname as string
 */
   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }
/**
 * get players number
 * @return number as int
 */
   public int getNumber()
   {
      return number;
   }
/** set players number
 * 
 * @param number as int
 */
   public void setNumber(int number)
   {
      this.number = number;
   }
/**
 * get ShirtName
 * @return shirtName as string
 */
   public String getShirtName()
   {
      return shirtName;
   }
/**
 * set shirtName 
 * @param shirtName 
 */
   public void setShirtName(String shirtName)
   {
      this.shirtName = shirtName;
   }
/**
 * get PreferredPosition
 * @return preferredPosition as string
 */
   public PositionType getPreferredPosition()
   {
      return preferredPosition;
   }
/**
 * set preferredPosition
 * @param preferredPosition as String
 */
   public void setPreferredPosition(PositionType preferredPosition)
   {
      this.preferredPosition = preferredPosition;
   }
/**
 * get MatchesInRow
 * @return matchesInRow as int
 */
   public int getMatchesInRow(MatchList matchList)
   {
      int played = 0;

      for (Match match : matchList.getAllMatches())
      {
         if (match.getRoster().getPlayerById(this.id) != null)
         {
            played++;
         }
         else
         {
            played = 0;
         }
      }

      return played;

   }

   public Player copy(){
      return new Player (this.id, this.firstname, this.lastname, this.number,
              this.shirtName, this.preferredPosition);
   }

   public boolean idEquals(Object obj)
   {
      if (!(obj instanceof Player))
      {
         return false;
      }
      Player other = (Player) obj;

      return id.equals(other.id);
   }


   /**
    * get Availability from Availability class
    * @return availability
    */
   public Availability getAvailability()
   {
      return availability;
   }

   /**
    * Sets availability through method
    * @param availability as a new object
    */
   public void setAvailability(Availability availability)
   {
      this.availability = availability;
   }


   /**
    * output data as String
    */
   @Override
   public String toString()
   {
      return     firstname +" "+ lastname + "  number:" + number + ", shirtName:" + shirtName
            + ", Position:" + preferredPosition + ", matchesInRow="
            + "\n";
   }

/**
 * Method to check if input argument and current object is the same.
 * @param takes Player as input object
 * @return boolean valus based  if the object is equal to this.
 * 
 */
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
            && preferredPosition.equals(other.preferredPosition);
   }

}
