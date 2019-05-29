package application.classes;
/**
 * Availability class containing the different types of injury and
 * @author Group-4
 * @version 2
 *
 */
public class Availability
{
   private UnavailableType injuryType;
   private Date availabilityDate;

   /**
    * No-argument constructor initializing the Availability.
    */
   public Availability()
   {
      this.injuryType = injuryType.available;
   }

   /**
    * Constructor taking injury type and a date
    * @param injurytype
    * @param date
    */
   public Availability(UnavailableType injurytype, Date date)
   {
      this.injuryType = injurytype;
      this.availabilityDate = date;
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


}
