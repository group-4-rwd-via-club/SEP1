package player;



public class test
{
   public static void main(String[] args)
   {
      int test = (int) Math.pow(4, 19);
      
      while(test>4)
      {
         test/=4;
      }
      System.out.println(test);
   }
      
}
