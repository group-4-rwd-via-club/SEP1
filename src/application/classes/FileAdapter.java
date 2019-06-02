package application.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to save and load files in the system
 * @author Group-4
 * @version 1
 */
public class FileAdapter
{
    private static final String FILE_NAME = "database.bin";

    /**
     * File writer, to write to the bin file
     * @param obj takes object and writes it to the file
     * @throws FileNotFoundException if file is not found, FileNotFoundException will be thrown.
     * @throws IOException if problems writing to file occurs, this exception will be thrown.
     */
    public void writeToFile(Object obj) throws FileNotFoundException, IOException
    {
        ObjectOutputStream writeToFile = null;

        try
        {
            FileOutputStream fileOutStream = new FileOutputStream(FILE_NAME);
            writeToFile = new ObjectOutputStream(fileOutStream);

            writeToFile.writeObject(obj);
        }
        finally
        {
            if (writeToFile != null)
            {
                try
                {
                    writeToFile.close();
                }
                catch (IOException e)
                {
                    System.out.println("IO Error closing file " + FILE_NAME);
                }
            }
        }
    }

    public String getFileName()
    {
        return this.FILE_NAME;
    }

    /**
     * File reader, to read files from the bin file
     * @return Object from the file (should be cast to VIAClubManagement).
     * @throws FileNotFoundException if file is not found, FileNotFoundException will be thrown.
     * @throws IOException if problems writing to file occurs, this exception will be thrown.
     */
    public Object readObjectFromFile() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Object obj = null;
        ObjectInputStream readFromFile = null;
        try
        {
            FileInputStream fileInStream = new FileInputStream(FILE_NAME);
            readFromFile = new ObjectInputStream(fileInStream);
            try
            {
                obj = readFromFile.readObject();
            }
            catch (EOFException eof)
            {
                //Done reading
            }
        }
        finally
        {
            if (readFromFile != null)
            {
                try
                {
                    readFromFile.close();
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File not found: " + FILE_NAME);
                }
                catch (IOException e)
                {
                    System.out.println("IO Error closing file " + FILE_NAME);
                }
            }
        }
        return obj;
    }

    public void writeToHTML(Match match) throws FileNotFoundException, IOException
    {
       ArrayList<String> st = new ArrayList<>();
       ArrayList<String> html = new ArrayList<>();
       ArrayList<Player> roster = new ArrayList<>();
       roster.addAll(match.getRoster().getAllPlayers());
       
       Scanner read = null;
       String fileTemplate = "template.html";
       String filePrint = match.getDate().toStringShort() 
             + "_" + match.getOpponent() + ".html";
       
       try
       {
          FileInputStream fileIn = new FileInputStream(fileTemplate);
          read = new Scanner(fileIn);
       }
       catch(FileNotFoundException e)
       {
          System.out.println("File not found, or " 
                + "could not be opened");
          System.exit(1);
       }
       while(read.hasNext())
       {
          st.add(read.nextLine());
       }
       read.close();
       
       String line = "";
       
       for(int i=0; i<st.size(); i++)
       {
          line = st.get(i);
          
          if(line.contains("$match")) {
             line = line.replace("$match",
                   match.getDate().toStringShort() 
                   + " - " + match.getOpponent());
             html.add(line);
          } else if(line.contains("$start")) {
             line = line.replace("$start",
                   match.getDate().toStringTime());
             html.add(line);
          } else if(line.contains("$location")) {
             line = line.replace("$start",
                   match.getLocation());
             html.add(line);
          } else if(line.contains("$type")) {
             line = line.replace("$type",
                   match.getMatchType().name());
             html.add(line);
          } else if(line.contains("$number")) {
             for(int j=0; j<roster.size(); j++) {
                String temp = line;
                
                Player player = roster.get(j);
                String name = player.getLastname() + ", " +
                      player.getFirstname().charAt(0) +".";
                
                temp = temp.replace("$number",
                      Integer.toString(player.getNumber()));
                temp = temp.replace("$name", name);
                temp = temp.replace("$position",
                      player.getPreferredPosition().name());
                
                html.add(temp);
             }           
          } else {
             html.add(line);
          }
       }
    }
}
