package application.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class containing date with methods to get current date
 * and current time.
 *
 * serialVersionUID is added to each serializable class to make sure different machines generate
 * the same UID. https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 *
 * @author GROUP-4
 * @version 3
 *
 */

public class Date implements Serializable
{
   private static final long serialVersionUID = 6529685098267757690L;

   private int day, month, year, hour, minute, second;
   
   /**
    * Empty date constructor. This is needed because
    * there already are an Date class in the java.util
    * library.
    */
   public Date(){}
   
   
   /**
    * Constructor to initialize fields: day month year.
    * @param day Sets the day with an integer
    * @param month Sets the month with an integer
    * @param year Sets the year with an integer
    */
   public Date(int day, int month, int year)
   {
      this.day = day;
      this.month = month;
      this.year = year;      
   }
   
   /**
    * Constructor to initialize fields: day month year hour second
    * @param day Sets the day with an integer
    * @param month Sets the month with an integer
    * @param year Sets the month with an integer
    * @param hour Sets the hour with an integer
    * @param minute Sets the minute with an integer
    * @param second Sets the second with an integer
    */
   private Date(int day, int month, int year, int hour, int minute, int second)
   {
      this.day = day;
      this.month = month;
      this.year = year;
      this.hour = hour;
      this.minute = minute;
      this.second = second;
   }

   public Date(int day, int month, int year, int hour, int minute)
   {
      this.day = day;
      this.month = month;
      this.year = year;
      this.hour = hour;
      this.minute = minute;
   }

   
   /**
    * Gets current date and time as a new Date object with fields:
    * Day month year hour minute second
    * @return Day month year hour minute seconds
    */
   public Date getNow()
   {
      LocalDateTime now = LocalDateTime.now();
      int year = now.getYear();
      int month = now.getMonthValue();
      int day = now.getDayOfMonth();
      int hour = now.getHour();
      int minute = now.getMinute();
      int second = now.getSecond();
      
      return new Date(day, month, year, hour, minute, second);
   }

   public int getHour(){
      return this.hour;
   }

   public int getMinute(){
      return this.minute;
   }
   
   /**
    * Check if the argument date is before input object date
    * @param other date as a date object
    * @return boolean value based on if the input object is before this object
    */
   public boolean isBefore(Date other)
   {
        if (this.year <= other.year)
         {         
            if (this.month <= other.month)
            {
               return this.day < other.day || this.month < other.month;
            }
         }
         return false;
   }


   /**
    * Converts integer values of day month year to localDate object format.
    * @return returns LocalDate object from private fields.
    */
   public LocalDate getAsLocalDate()
      {
         return LocalDate.of(this.year, this.month, this.day);
      }


   /**
    * Returns current fields, except hour minute second, as norminal string in format:
    * yyyy-mm-dd
    * @return tostring in format yyyy-MM-dd
    */
   public String toStringShort()
   {
      return String.format("%d-%02d-%02d", year, month, day);
   }

   /**
    * 
    * @return
    */
   public String toStringTime()
   {
      return String.format("%02d:%02d", hour, minute);
   }
   
   /**
    * returns currents fields as a norminal string format:
    * yyyy-mm-dd HH:mm:ss
    */
   public String toString()
   {
      return String.format("%d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
   }

}
