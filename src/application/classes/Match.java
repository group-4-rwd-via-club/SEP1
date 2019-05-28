package application.classes;


import java.util.Objects;
import java.util.UUID;

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
         this.id = StringUUID.getUUID();
         
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
         this.id = StringUUID.getUUID();
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
       * @param inputs location as a string input.
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
       * Method to set the date of the match.
       * @param takes Date class as input.
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
        * Sets roster of players in the match.
        * @param takes list of players
        */
        public void setRoster(PlayerList playerList)
        {
            this.matchRoster = playerList;
        }

        /**
        * Gets a playerlist of all playerrs associated with the match
        * @return
        */
        public PlayerList getRoster()
        {
            return this.matchRoster;
        }


        /**
         * ToString method which returns values of all fields as String.
         * @returns string with all fields
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
       * @param takes Match as input object
       * @returns boolean value based on if the object is equal to this.
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
