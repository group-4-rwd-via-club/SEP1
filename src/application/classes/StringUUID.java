package application.classes;

import java.util.UUID;


/**
 * Class to create a random UUID string to be used as ID
 * for other classes. Main purpose is to ensure that same
 * ID is not being generated twice and there for keeping
 * the it unique.
 * 
 * @author Group-4
 * @version 1
 *
 */
public class StringUUID
{

   public String getUUID()
   {
      UUID uuid = UUID.randomUUID();
      return uuid.toString();
   }
}
