package sample;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bwells on 3/27/2017.
 */
public class FileManager {


    public void writeToFile(String content, String fileURL) throws Exception
    {
        PrintWriter writer = new PrintWriter(fileURL, "UTF-8");
        writer.println(content);
        writer.close();
    }

    public void writeToFile(String content, File file) throws Exception
    {
        FileWriter fw = new FileWriter(file);
        fw.write("");
        fw.append(content);
        fw.close();
    }

    public void writeToFile(int line, String content, File file) throws Exception
    {
        List<String> fileLines = readFile(file);

        fileLines.remove(line);
        fileLines.add(line, content);

        writeToFile(fileLines.toString(), file);
    }

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

    public int countFileLines(File file)
    {
        int count = 0;
        try{
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
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
}
