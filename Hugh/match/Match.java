package match;

public class Match
{
   private MyDate date;
   private double meetup;
   private double start;
   private String opponent;
   private String[] matchType;
   // maybe match as parent and types as child classes?
   // benefit: coding different rosters may be easier
   // risk: could create a lot more work elsewhere
   private Roster[] roster;
   private String location;
   private Results result; // called score in mockup
   
   // blah
   // constructors
   public Match(MyDate date, double meetup, double start, String opponent,
         String[] matchType, Roster[] roster, String location, Results result)
   {
      this.date = date;
      this.meetup = meetup;
      this.start = start;
      this.opponent = opponent;
      this.matchType = matchType;
      this.roster = roster;
      this.location = location;
      this.result = result;
   }

   public Match(MyDate date, String opponent,
         String[] matchType, String location)
   {
      this.date = date;
      this.opponent = opponent;
      this.matchType = matchType;
      this.location = location;
      meetup = 0;
      start = 0;
      roster = null;
      result = null;
   }

   public Match(MyDate date)
   {
      this.date = date;
      meetup = 0;
      start = 0;
      opponent = "";
      matchType = null;
      roster = null;
      location = "";
      result = null;
   }

   // getters and setters
   public MyDate getDate()
   {
      return date;
   }

   public void setDate(MyDate date)
   {
      this.date = date;
   }

   public double getMeetup()
   {
      return meetup;
   }

   public void setMeetup(double meetup)
   {
      this.meetup = meetup;
   }

   public double getStart()
   {
      return start;
   }

   public void setStart(double start)
   {
      this.start = start;
   }

   public String getOpponent()
   {
      return opponent;
   }

   public void setOpponent(String opponent)
   {
      this.opponent = opponent;
   }

   public String[] getMatchType()
   {
      return matchType;
   }

   public void setMatchType(String[] matchType)
   {
      this.matchType = matchType;
   }

   public Roster[] getRoster()
   {
      return roster;
   }

   public void setRoster(Roster[] roster)
   {
      this.roster = roster;
   }

   public String getLocation()
   {
      return location;
   }

   public void setLocation(String location)
   {
      this.location = location;
   }

   public Results getResult()
   {
      return result;
   }

   public void setResult(Results result)
   {
      this.result = result;
   }
   
   // copy + equals + toString
   
}
