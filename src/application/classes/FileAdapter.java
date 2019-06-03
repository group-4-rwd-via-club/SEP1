package application.classes;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * private class to save and load files in the system
 * @author Group-4
 * @version 1
 */
class FileAdapter
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
                    displayErrorMessage("IO File error on", FILE_NAME);
                }
            }
        }
    }

    public String getFileName()
    {
        return FILE_NAME;
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
                    displayErrorMessage("File not found", FILE_NAME);
                }
                catch (IOException e)
                {
                    displayErrorMessage("IO File error on", FILE_NAME);
                }
            }
        }
        return obj;
    }

    /**
     * Generic method to display error message while doing file operations.
     * @param message as a string to be displayed without ':'
     * @param fileName to be displayed in the error message.
     */
    private void displayErrorMessage(String message, String fileName)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File error");
        alert.setHeaderText(null);
        alert.setContentText("Error: " + message + ": " + fileName);

        alert.showAndWait();
    }

    /**
     * Writes match information to a HTML file
     * @param match to be printed
     */
    public void writeToHTML(Match match) {
       ArrayList<String> template = new ArrayList<>();
       ArrayList<String> html = new ArrayList<>();
       ArrayList<Player> roster = new ArrayList<>();
       roster.addAll(match.getRoster().getAllPlayers());
       
       Scanner read = null;
       String fileTemplate = "template.html";
       String filePrint = match.getDate().toStringShort() 
             + "_" + match.getOpponent() + ".html";
       
       try {
          FileInputStream fileIn = new FileInputStream(fileTemplate);
          read = new Scanner(fileIn);
       }
       catch(FileNotFoundException e) {
           displayErrorMessage("File not found", filePrint);
       }
       while(read.hasNext()) {
          template.add(read.nextLine());
       }
       read.close();
       
       String line = "";
       
       for(int i=0; i<template.size(); i++) {
          line = template.get(i);
          
       // Check match for null values
          String date, opponent, start, location, type;
          if(match.getDate().toStringShort().equals("0-00-00")) {
             date = ""; 
          } else {
             date = match.getDate().toStringShort();
          }
          if(match.getOpponent() == null) {
             opponent = "";
          } else {
             opponent = match.getOpponent();
          }
          if(match.getDate().toStringTime().equals("00:00")) {
             start = "";
          } else {
             start = match.getDate().toStringTime();
          }
          if(match.getLocation() == null) {
             location = "";
          } else {
             location = match.getLocation();
          }
          if(match.getMatchType() == MatchType.none) {
             type = "";
          } else {
             type = match.getMatchType().toString();
          }
          
          // Replace values
          if(line.contains("$match")) {
             line = line.replace("$match",
                   date + " - " + opponent);
             html.add(line);
          } else if(line.contains("$start")) {
             line = line.replace("$start", start);
             html.add(line);
          } else if(line.contains("$location")) {
             line = line.replace("$location", location);
             html.add(line);
          } else if(line.contains("$type")) {
             line = line.replace("$type", type);
             html.add(line);
          } else if(line.contains("$number")) {
             
             // Replace values for each player
             for(int j=0; j<roster.size(); j++) {
                String temp = line;
                Player player = roster.get(j);
                String number, firstName=null, lastName, name="", position;
                
                // Check player for null values
                if(player.getNumber() < 0) {
                   number = "";
                } else {
                   number = Integer.toString(player.getNumber());
                }
                if(player.getLastname() == null) {
                   lastName = "";   
                } else {
                   lastName = player.getLastname();
                   name += lastName;
                }
                if(player.getFirstname() == null) {
                   firstName = "";
                } else {
                   firstName = String.valueOf(player.getFirstname().charAt(0));
                   if(!(lastName.equals(""))) {
                      name += ", ";
                   }
                   name += firstName + ".";
                }
                if(player.getPreferredPosition() == PositionType.none) {
                   position = "";
                } else {
                   position = player.getPreferredPosition().toString();
                }
                
                // Replace values in temp line
                temp = temp.replace("$number", number);
                temp = temp.replace("$name", name);
                temp = temp.replace("$position",position);
                
                html.add(temp);
             }           
          } else {
             html.add(line);
          }
       }
       PrintWriter write = null;
       try {
          FileOutputStream fileOut = new FileOutputStream(filePrint);
          write = new PrintWriter(fileOut);
          
          for(int i=0; i < html.size(); i++) {
             write.println(html.get(i));
          }
       }
       catch(FileNotFoundException e)
       {
           displayErrorMessage("File not found", filePrint);
       }
       write.close();
       
       Alert printAlert = new Alert(Alert.AlertType.INFORMATION, filePrint + " is ready for printing", ButtonType.OK);
       printAlert.setTitle("Print Information");
       printAlert.setHeaderText(null);
       printAlert.showAndWait();
       if(printAlert.getResult() == ButtonType.OK);
    }
}
