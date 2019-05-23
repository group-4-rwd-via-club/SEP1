package application.classes;

import java.util.ArrayList;

public class MatchList
{
   public ArrayList<Match> matches;
   
   public MatchList()
   {
      matches = new ArrayList<Match> matches;
   }
   
   public MatchList(ArrayList<Match> matches)
   {
      this.matches = matches;
   }
   
   public Match getMatch(int index)
   {
      if (index < matches.size())
         return matches.get(index);
      return null;
   }
   
   public Match[] getPreviousMatches()
   {
      
   }
   
   
   
   
   
}
