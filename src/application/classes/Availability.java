package application.classes;

public class Availability
{
   private UnavailableType injuryType;
   private Date availability;

   public boolean isPlayerAvailable() {
      return true;
   }
   public UnavailableType getUnavailableType() {
      return  injuryType;
   }
   // TODO: Needs to be rewritten
   public String getUnavailableTimeframe() {
      return "";
   }
}
