package player;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
/**
 * 
 * @author hmci
 * @version 0.1
 */
public class Player
{
   private int id;
   private String firstName;
   private String middleName;
   private String lastName;
   private int number;
   private String shirtText;
   private String position;
   private String[] status = {"Available", "Injured", "Suspended", "Other"};
   private Status status;
   private int matchesInARow;
   private String idFile = "C:\\Users\\hmci\\Documents\\GitHub\\SEP1\\Hugh\\player\\playerId.bin";
   
   public Player(String firstName, String middleName, String lastName,
         int number, String shirtText, String position, int matchesInARow)
   {
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.number = number;
      this.shirtText = shirtText;
      this.position = position;
      availability = status[0];
      this.matchesInARow = matchesInARow;
      id = 0;
      
      
      Status.Injured.toString();
      
      if(playerList.size() > 0)
      {
         id = playerList.get(playerList.size() - 1).getId() + 1;
      }
      else
      {
         id = 01;
      }
      
   }
   public int setId()
   {
      if(!(id<=0))
      {
         
      }
   }
   
}
