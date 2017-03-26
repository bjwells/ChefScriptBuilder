package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javax.swing.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController {

    private File currentFile;

    @FXML
    private ListView lst_attributes;

    @FXML
    private ListView lst_descriptors;

    @FXML
    protected void initialize()
    {
        if(lst_attributes != null)
        {
            populateAttributes();
        }
    }

    @FXML
    private void handleOpenButtonAction(ActionEvent event)
    {
        //button was clicked do something
        //JOptionPane.showMessageDialog(null, "clicked open button");
    }

    @FXML
    private void handleNewAttributeButtonAction(ActionEvent event)
    {

        //prompt for name of the file
        /*String user_input = "";
        Scanner in = new Scanner(System.in);
        System.out.println("");
        user_input = in.nextLine();*/

        try{
            createDefaultFile();
            writeToFile("one" + "\n" + "two" + "\n" + "three", "default.rb");
            openScene("AttributeList.fxml", "Attribute List", 600, 500);

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @FXML
    private void handleNewDefaultButtonAction(ActionEvent event)
    {

    }

    @FXML
    private void handleAddNewAttributeButtonAction(ActionEvent event)
    {
        //open edit screen
        try
        {
            openScene("EditAttribute.fxml", "Edit Attribute", 600, 500);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }

    @FXML
    private void handleNewDescriptorButtonAction(ActionEvent event)
    {
        
    }

    private void openScene(String windowName, String title, int width, int height) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(windowName));
        Main.thePrimaryStage.setTitle(title);
        Main.thePrimaryStage.setScene(new Scene(root, width, height));
        Main.thePrimaryStage.show();
    }

    private void createDefaultFile() throws Exception
    {
        deleteDefaultFile();
        PrintWriter writer = new PrintWriter("default.rb", "UTF-8");
        writer.close();

        this.currentFile = new File("default.rb");
    }

    private void deleteDefaultFile()
    {
        File newFile = new File("default.rb");
        if(newFile.exists())
        {
            newFile.delete();
        }
    }

    private void writeToFile(String content, String fileURL) throws Exception
    {
        PrintWriter writer = new PrintWriter(fileURL, "UTF-8");
        writer.println(content);
        writer.close();
    }

    private void populateAttributes()
    {
        List<String> values = readFile("default.rb");
        lst_attributes.setItems(FXCollections.observableArrayList(values));
    }

    private List<String> readFile(String fileName) {

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
}
