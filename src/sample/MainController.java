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
import java.util.Arrays;
import java.util.List;

public class MainController {

    @FXML
    private ListView lst_attributes;

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
            openScene("AttributeList.fxml", "Attribute List");

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

    private void openScene(String windowName, String title) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(windowName));
        Main.thePrimaryStage.setTitle(title);
        Main.thePrimaryStage.setScene(new Scene(root, 600, 275));
        Main.thePrimaryStage.show();
    }

    private void createDefaultFile() throws Exception
    {
        deleteDefaultFile();
        PrintWriter writer = new PrintWriter("default.rb", "UTF-8");
        writer.close();
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
        List<String> values = Arrays.asList("one", "two", "three");
        lst_attributes.setItems(FXCollections.observableArrayList(values));
    }
}
