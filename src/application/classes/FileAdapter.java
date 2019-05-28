package application.classes;

import java.io.*;

public class FileAdapter {


    private static final String FILE_NAME = "database.bin";

    // Writes the given object to a file with the given file name
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


    // Reads the first object from the file with the given file name and returns it.
    // Whoever calls the method will need to cast it from type Object to its real type
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


}
