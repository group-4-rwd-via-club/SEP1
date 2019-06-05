package application.classes;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Availability class containing the different types of injury.
 *
 * serialVersionUID is added to each serializable class to make sure different machines generate
 * the same UID. https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 *
 * @author Group-4
 * @version 3
 *
 */
public class Availability implements Serializable
{
   private static final long serialVersionUID = 6529685098267757690L;

   private UnavailableType injuryType;
   private Date availabilityDate;

   /**
    * No-argument constructor initializing the Availability.
    */
   public Availability()
   {
      this.injuryType = UnavailableType.available;
   }


   /**
    * Public enumerable used to indicate a Unavailable type
    * @return return true
    */
   public boolean isPlayerAvailable() {
      return injuryType == UnavailableType.available;
   }

   /**
    * Get type of injury as Enum UnavailableType
    * @return returns injurytype enum
    */
   public UnavailableType getUnavailableType()
   {
      return injuryType;
   }

   /**
    * Set unavailable type by setting the enum UnavailableType as argument
    * @param injuryType as UnavilableType enum
    */
   public void setUnavailableType(UnavailableType injuryType)
   {
      this.injuryType = injuryType;
   }

   /**
    * Get the date the player is available again.
    * @return returns the date the player is available again as a Date object
    */

   public Date getUnavailableEnd()
   {
      return this.availabilityDate;
   }

   /**
    * Set the date the unavailability ends.
    * @param date parameter is being added to the private field date.
    */

   public void setUnavailableEnd(Date date)
   {
      this.availabilityDate = date;
   }

   /**
    * Public tostring to print out availability to a string. Which later can be used by search function.
    * @return tostring with all set fields in availability.
    */
   public String toString() {
      return "Availability{" +
              "injuryType=" + injuryType +
              ", availabilityDate=" + availabilityDate +
              '}';
   }
}
