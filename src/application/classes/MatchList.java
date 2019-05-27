package application.classes;

import java.util.ArrayList;

/**
 * MatchList class respresent a class with contains
 * all the matches included in the program.
 * 
 * @author Group-4
 * @version 1
 */


public class MatchList
{
   private ArrayList<Match> matches;
   
   /**
    * Empty constructor which initialize matches arraylist.
    */
   public MatchList()
   {
      matches = new ArrayList<Match>();
   }
   
   /**
    * Constructor that takes Arraylist with matches
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
    * @returns the match at the index. If index is too large, it will return null.
    */
   public Match getMatch(int index)
   {
      if (index < matches.size())
         return matches.get(index);
      return null;
   }

   /**
    * returns a list of all matches in the system
    * @return returns an arraylist of all matches
    */
   public ArrayList<Match> getAllMatches()
   {
      return matches;
   }
   
   /**
    * Get a array list of all previous matches from today.
    * @returns a arraylist with all previous matches.
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
    * Gets a arraylist with all matches which has date today
    * @returns an array list with all matches which has today as date.
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
   
   /**
    * Add a match to the array list. Takes a match as input.
    * TODO: Add check that it doesnt already exist
    * @param match from Class Match as input
    */
   public void addMatch(Match match)
   {
      matches.add(match);
   }
   
}
