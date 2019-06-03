package application.classes;


import java.io.Serializable;
import java.util.Objects;

/**
 *  Class which contains all match information regarding a match.
 *
 *  serialVersionUID is added to each serializable class to make sure different machines generate
 *  the same UID. https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 *
 * @author Group-4
 * @version 4
 */
public class Match implements Serializable
{
    private static final long serialVersionUID = 6529685098267757690L;

      private final String id;
      private String opponent;
      private String location;
      private String score;
      private String viaScore;
      private String oppScore;
      
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
         this.id = StringUUID.generateId();
         
      }
      
      /**
       * Empty constructor which initialize:
       * Match Roster, Date, match type as none.
       */
      public Match()
      {
         this.matchRoster = new PlayerList();
         this.date = new Date();
         this.matchType = MatchType.none;
         this.id = StringUUID.generateId();
      }

      /**
       * Gets the ID for the current match.
       * @return private field ID.
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
       * @return opponent as a string.
       */
      
      public String getOpponent()
      {
         return this.opponent;
      }
      
      /**
       * Sets location as a string and takes string as argument.
       * @param location as a string input.
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
       * @param viaScore and oppScore as a string input.
       */
      public void setScore(String viaScore, String oppScore)
      {
          this.viaScore = viaScore;
          this.oppScore = oppScore;
          this.score = viaScore + " / " + oppScore;
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
     * Method to get the viaClub score.
     * @return the score as a string.
     */
      public String getViaScore() { return this.viaScore; }

      /**
     * Method to get the opponent score.
     * @return the score as a string.
     */
      public String getOppScore() { return this.oppScore; }

      /**
       * Method to set the date of the match.
       * @param other as Date class as input.
       */
      public void setDate(Date other)
      {

          this.date = other;
      }
      
      /**
       * Gets the date of the match if any.
       * @return the date of the match as Date object.
       */
      
      public Date getDate()
      {
         return this.date;
      }

        /**
        * Returns match type
        * @return match type as enum
        */
        public MatchType getMatchType()
        {
        return this.matchType;
        }

        /**
         * Sets the match type
         * @param matchType to be set
         */
        public void setMatchType(MatchType matchType) { this.matchType = matchType; }
        /**
        * Sets roster of players in the match.
        * @param playerList as a list of players
        */
        public void setRoster(PlayerList playerList)
        {
            this.matchRoster = playerList;
        }

        /**
        * Gets a playerlist of all playerrs associated with the match
        * @return match roster as PlayerList Object
        */
        public PlayerList getRoster()
        {
            return this.matchRoster;
        }


        /**
         * ToString method which returns values of all fields as String.
         * @return string with all fields
         */
        public String toString() {
            return "Match{" +
                    "id='" + id + '\'' +
                    ", opponent='" + opponent + '\'' +
                    ", location='" + location + '\'' +
                    ", score='" + score + '\'' +
                    ", matchRoster=" + matchRoster +
                    ", date=" + date +
                    ", matchType=" + matchType +
                    '}';
        }

    /**
   * Method to check if input argument and current object is the same.
   * @param o as a Match Object
   * @return boolean value based on if the object is equal to this.
   */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match other = (Match) o;

        if (!Objects.equals(id, other.id)) return false;
        if (!Objects.equals(opponent, other.opponent)) return false;
        if (!Objects.equals(location, other.location)) return false;
        if (!Objects.equals(score, other.score)) return false;
        if (!Objects.equals(matchRoster, other.matchRoster)) return false;
        if (!Objects.equals(date, other.date)) return false;
        return matchType == other.matchType;
    }


}
