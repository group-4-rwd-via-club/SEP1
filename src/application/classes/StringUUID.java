package application.classes;

import java.util.UUID;


/**
 * Class to create a random UUID string to be used as ID
 * for other classes. Main purpose is to ensure that same
 * ID is not being generated twice and there for keeping
 * the it unique.
 * 
 * @author Group-4
 * @version 2
 *
 */


public class StringUUID
{
    /**
    * Static string that returns the random unique id.
    * The method is static so it can be reached
    * from all methods
    * @return a random id as a String Objects
    */

   public static String generateId()
   {
      return UUID.randomUUID().toString();
   }
}
