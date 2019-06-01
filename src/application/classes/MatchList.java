package application.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * MatchList class represent a class with contains
 * all the matches included in the program.
 *
 * serialVersionUID is added to each serializable class to make sure different machines generate
 * the same UID. https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 * 
 * @author Group-4
 * @version 3
 */


public class MatchList implements Serializable
{
   private static final long serialVersionUID = 6529685098267757690L;

   private  ArrayList<Match> matches;
   
   /**
    * Empty constructor which initialize matches array list.
    */
   public MatchList()
   {
      matches = new ArrayList<>();
   }

   /**
    * returns a list of all matches in the system
    * @return returns an array list of all matches
    */
   public ArrayList<Match> getAllMatches()
   {
      return matches;
   }

   /**
    * Method to update matchList. Loops through all matches and find the match with equal ID
    * before updating the match with input object.
    * @param match as input
    */
   public void updateMatch(Match match)
   {
      for (int i = 0; i < matches.size(); i++)
      {
         if (matches.get(i).getId().equals(match.getId()))
         {
            matches.set(i, match);
         }
      }
   }

   /**
    * Removes input match from matchlist if it exists.
    * @param match to remove
    */
   public void removeMatch(Match match)
   {
      matches.remove(match);
   }

   /**
    * Looks for match with input ID, if it finds the match with the ID it
    * returns the match object. Else it returns null value.
    * @param id parameter string id which is the uuid generated when the object is created.
    * @return returns match object or null if it cant find the object based on the id.
    */
   public Match getMatchById(String id)
   {
      for (Match match : matches)
      {
         if (Objects.equals(match.getId(), id)){
            return match;
         }

      }
      return null;
   }
   
   /**
    * Add a match to the array list. Takes a match as input.
    * @param match from Class Match as input
    */
   public void addMatch(Match match)
   {
      matches.add(match);
   }
   
}
