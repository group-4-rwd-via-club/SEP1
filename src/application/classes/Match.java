package application.classes;


/**
 *  Class which contains all match information regarding a match.
 * @author Group-4
 * @version 1
 */
public class Match
{
      private String id;
      private String opponent;
      private String location;
      private String score;
      
      private PlayerList matchRoster;
      private Date date;
      private MatchType matchType;
      
      /**
       * Constructor to initialize fields: Opponent, Match type and the location
       * for the match.
       * Score, match roster and date are initialized with empty constructor .
       * @param opponent Sets private field with opponent as a string.
       * @param matchType Sets private enum field with the type of the match.
       * @param location Sets private field with location as a string.
       */
      public Match(String opponent, MatchType matchType, String location)
      {
         this.opponent = opponent;
         this.matchType = matchType;
         this.location = location;
         
         this.matchRoster = new PlayerList();
         this.date = new Date();
         
      }
      
      /**
       * Empty constructor which initialize:
       * Match Roster, Date, match type as none.
       */
      public Match()
      {
         this.matchRoster = new PlayerList();
         this.date = new Date();
         this.matchType = MatchType.None;
      }
      
      /**
       * Gets the ID for the current match.
       * @returns private field ID.
       */
      public String getId()
      {
         return this.id;
      }
      
      /**
       * Sets the opponent field as a string.
       * @param opponent as a string
       */
      public void setOpponent(String opponent)
      {
         this.opponent = opponent;
      }
      
      /**
       * Gets opponent set in the private field.
       * @returns opponent as a string.
       */
      
      public String getOpponent()
      {
         return this.opponent;
      }
      
      /**
       * Sets location as a string and takes string as argument.
       * @param Takes location as a string input.
       */
      
      public void setLocation(String location)
      {
         this.location = location;
      }
      
      /**
       * Gets the private field location as a string
       * @return location string.
       */
      public String getLocation()
      {
         return this.location;
      }
      
      /**
       * Method for setting the score.
       * @param taking score as a string input.
       */
      public void setScore(String score)
      {
         this.score = score;
      }
      
      /**
       * Method to get the score.
       * @return the score as a string.
       */
      
      public String getScore()
      {
         return this.score;
      }
      
      /**
       * Method to check if input argument and current object is the same.
       * @param takes Match as input object
       * @returns boolean value based on if the object is equal
       */
      public boolean equals(Object obj)
      {
         if (!(obj instanceof Match))
            return false;
         
         Match other = (Match) obj;
         return this.id.equals(other.id) && this.opponent.equals(other.opponent)
               && this.location.equals(other.location) && this.score.equals(other.score);
         
         
      }
      
}
