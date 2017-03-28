package sample;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bwells on 3/27/2017.
 */
public class FileManager {


    //write to a file by passing the file URL
    public void writeToFile(String content, String fileURL) throws Exception
    {
        PrintWriter writer = new PrintWriter(fileURL, "UTF-8");
        writer.println(content);
        writer.close();
    }

    //write to a file by passing a file object (has issue where file isn't being cleared!)
    public void writeToFile(String content, File file) throws Exception
    {
        String fileURL = file.getAbsolutePath();
        file.delete();
        File newFile = new File(fileURL);
        FileWriter fw = new FileWriter(newFile);
        fw.append(content);
        fw.close();
    }

    //write to a specific line in a file by passing the file, content and what line to write to
    public void writeToFile(int line, String content, File file) throws Exception
    {
        //get everything that's in the file
        List<String> fileLines = readFile(file);

        //replace the line specified
        fileLines.remove(line);
        fileLines.add(line, content);

        FileWriter fw = new FileWriter(file);
        for(String l : fileLines)
        {
            fw.write(l);
        }
        fw.close();
    }

    //appends the content provided to the end of the file given.
    public void appendToFile(String content, File file) throws Exception
    {
        Files.write(file.toPath(), content.getBytes(), StandardOpenOption.APPEND);
    }

    //reads a file from the file's name and returns a list of strings, one string for each line
    public List<String> readFile(String fileName) {

        try{
            Scanner s = new Scanner(new File(fileName));
            ArrayList<String> list = new ArrayList<String>();
            while (s.hasNextLine()){
                list.add(s.nextLine());
            }
            s.close();

            return list;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return null;
    }

    //reads a file from the file object and returns a list of strings, one string for each line
    public List<String> readFile(File file) {

        try{
            Scanner s = new Scanner(file);
            ArrayList<String> list = new ArrayList<String>();
            while (s.hasNextLine()){
                list.add(s.nextLine());
            }
            s.close();

            return list;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return null;
    }

    //counts the number of lines in the given file (untested!!)
    public int countFileLines(File file)
    {
        int count = 0;
        try{
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                s.nextLine();
                count++;
            }
            s.close();

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return count;
    }

    public List<String> getDescriptorsFromFileLine(int lineNumber, File file)
    {
        List<String> lines = readFile(file);
        List<String> descriptors = new ArrayList<>();
        boolean done = false;

        String lineToParse = lines.get(lineNumber);

        //default['<>']['<>'}... = <>
        lineToParse = lineToParse.replace("default['", "");
        String firstDesc = lineToParse.substring(0, lineToParse.indexOf("'"));
        descriptors.add(firstDesc);
        lineToParse = lineToParse.replace(firstDesc + "']", "");

        while(!done)
        {

            if(lineToParse.substring(0,1).equals(" ") || lineToParse.substring(0,1).equals("="))
            {
                done = true;
            }
            else
            {
                lineToParse = lineToParse.replace("['", "");
                descriptors.add(lineToParse.substring(0, lineToParse.indexOf("'")));
                lineToParse = lineToParse.replace("']", "");
            }
        }

        while(lineToParse.substring(0,1).equals(" ") || lineToParse.substring(0,1).equals("="))
        {
            lineToParse = lineToParse.replace(lineToParse.substring(0, 1), "");
        }

        descriptors.add(lineToParse);

        return descriptors;



    }
}
