package application.classes;

import java.io.*;

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
                    System.out.println("IO Error closing file " + FILE_NAME);
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
