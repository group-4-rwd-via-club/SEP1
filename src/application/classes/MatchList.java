package application.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * MatchList class represent a class with contains
 * all the matches included in the program.
 * 
 * @author Group-4
 * @version 2
 */


public class MatchList implements Serializable
{
   private ArrayList<Match> matches;
   
   /**
    * Empty constructor which initialize matches array list.
    */
   public MatchList()
   {
      matches = new ArrayList<Match>();
   }
   
   /**
    * Constructor that takes Array list with matches
    * and add it to the matches field.
    * @param matches as input.
    */
   public MatchList(ArrayList<Match> matches)
   {
      this.matches = matches;
   }
   
   /**
    * Get a match at a certain index and return as Match.
    * @param index of the match
    * @return the match at the index. If index is too large, it will return null.
    */
   public Match getMatch(int index)
   {
      if (index < matches.size())
         return matches.get(index);
      return null;
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
    * Get a array list of all previous matches from today.
    * @return a array list with all previous matches.
    */
   public ArrayList<Match> getPreviousMatches()
   {
      ArrayList<Match> beforeMatches = new ArrayList<Match>();
      for (Match match : matches)
      {
         Date nowDate = new Date().getToday();
         if (match.getDate().isBefore(nowDate))
         {
            beforeMatches.add(match);
         }
      }
      return beforeMatches;
   }
   
   /**
    * Gets a array list with all matches which has date today
    * @return an array list with all matches which has today as date.
    */
   public ArrayList<Match> getTodayMatches()
   {
      ArrayList<Match> todayMatches = new ArrayList<Match>();
      for(Match match : matches)
      {
         if(match.getDate().isToday())
         {
            todayMatches.add(match);
         }
      }
      return todayMatches;
   
   }

   public void updateMatch(Match match)
   {

      try
      {
         for (int i = 0; i < matches.size(); i++)
         {
            if (matches.get(i).getId().equals(match.getId()))
            {
               matches.set(i, match);
            }
         }
      }
      catch (NullPointerException e)
      {
         System.out.println("Error closing file ");
      }

   }

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
