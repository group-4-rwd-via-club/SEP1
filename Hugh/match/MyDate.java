package match;

public class MyDate
{
   private int day,month,year;

   public MyDate(int day, int month, int year)
   {
      this.day = day;
      this.month = month;
      this.year = year;
   }
   
   public MyDate()
   {
      this.day = 0;
      this.month = 0;
      this.year = 0;
   }

   public int getDay()
   {
      return day;
   }

   public void setDay(int day)
   {
      this.day = day;
   }

   public int getMonth()
   {
      return month;
   }

   public void setMonth(int month)
   {
      this.month = month;
   }

   public int getYear()
   {
      return year;
   }

   public void setYear(int year)
   {
      this.year = year;
   }
   
   public boolean copy(Object obj)
   {
      if(!(obj instanceof MyDate))
      {
         return false;
      }
      MyDate other = (MyDate)obj;
      return this.day == other.day 
            && this.month == other.month 
            && this.year == other.year;
   }

   public String toString()
   {
      return day + "/" + month + "/" + year;
   }
}
