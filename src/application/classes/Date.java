package application.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class containing date with methods to get current date
 * and current time.
 * @author GROUP-4
 * @version 1
 *
 */

public class Date
{

   private int day, month, year, hour, minute, second;
   
   /**
    * Empty date constructor. This is needed because
    * there already are an Date class in the java.util
    * library.
    */
   public Date()
   {
   }
   
   
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
   public Date(int day, int month, int year, int hour, int minute, int second)
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
    * Gets the date as a new object based on the fields in the class.
    * @returns day month year hour minute second as a new date.
    */
   public Date getDate()
   {
      return new Date(this.day, this.month, this.year, this.hour, this.minute, this.second);
   }
   
   /**
    * Gets current date from the imported lib LocalDateTime.now.
    * Returns only day month year.
    * @returns Day, month and year.
    */
   public Date getToday()
   {
      LocalDateTime now = LocalDateTime.now();
      int year = now.getYear();
      int month = now.getMonthValue();
      int day = now.getDayOfMonth();
      
      return new Date(day, month, year);
      
   }
   
   /**
    * Gets current date and time as a new Date object with fields:
    * Day month year hour minute second
    * @returns Day month year hour minute seconds
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
   
   /**
    * Check if the argument date is before input object date
    * @param other date as a date object
    * @return boolean value based on if the input object is before this object
    */
   public boolean isBefore(Date other)
   {
        if (other.year <= this.year) 
         {         
            if (other.month <= this.month)
            {            
               if (other.day < this.day || other.month < this.month )
               {               
                  return true;
               }
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
         String dateAsString = String.format("%s/%s/%s", this.day,this.month,this.year);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDate localDate = LocalDate.parse(dateAsString, formatter);
         return localDate;
      }

   
   /**
    * Checks if value stored in dateclass is today
    * @returns boolean value based on if values represents today.
    */
   public boolean isToday()
   {
      LocalDateTime now = LocalDateTime.now();
      return this.day == now.getDayOfMonth() && this.month == now.getMonthValue() && this.year == now.getYear();
   }

   /**
    * Returns current fields, except hour minute second, as norminal string in format:
    * yyyy-mm-dd
    * @return
    */
   public String toStringShort()
   {
      return String.format("%d-%02d-%02d", year, month, day);
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
