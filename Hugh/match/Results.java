package match;

public class Results
{
   private int viaScore, opponentScore;

   public Results(int viaScore, int opponentScore)
   {
      this.viaScore = viaScore;
      this.opponentScore = opponentScore;
   }

   public int getViaScore()
   {
      return viaScore;
   }

   public void setViaScore(int viaScore)
   {
      this.viaScore = viaScore;
   }

   public int getOpponentScore()
   {
      return opponentScore;
   }

   public void setOpponentScore(int opponentScore)
   {
      this.opponentScore = opponentScore;
   }


   public String toString()
   {
      return viaScore + " : " + opponentScore;
   }
}
