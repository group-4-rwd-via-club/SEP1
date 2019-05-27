package application.classes;
/**
 * Availability class containing the different types of injury and
 * @author Group-4
 * @version 1
 *
 */
public class Availability
{
   private UnavailableType injuryType;
   private Date availability;
   /**
    * No-argument constructor initializing the Availability.
    */
   public Availability()
   {
      this.injuryType = injuryType.none;
   }
   /**
    *
    * @param injurytype
    * @param date
    */
   public Availability(UnavailableType injurytype, Date date)
   {
      this.injuryType = injurytype;
      this.availability = date;
   }
/**
 * Public enumerable used to indicate a Unavailable type
 * @return return true
 */
   public boolean isPlayerAvailable() {
      return injuryType == UnavailableType.none;
   }

   public UnavailableType getUnavailableType() {
      return  injuryType;
   }
   // TODO: Needs to be rewritten
   public String getUnavailableTimeframe() {
      return "";
   }
}
