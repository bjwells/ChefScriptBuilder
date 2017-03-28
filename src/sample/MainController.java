package sample;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javax.swing.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyEvent;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class MainController {

    public static File currentFile;
    public static int lineEditing;

    @FXML
    private ListView lst_attributes;

    @FXML
    protected void initialize()
    {
        if(lst_attributes != null)
        {
            populateAttributes();
            lst_attributes.setCellFactory(TextFieldListCell.forListView());
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
            FileManager fm = new FileManager();
            //fm.writeToFile("one" + "\n" + "two" + "\n" + "three", "default.rb");
            Util ut = new Util();
            ut.openScene("AttributeList.fxml", "Attribute List", 600, 500);

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
            Util ut = new Util();
            lineEditing = -1;
            ut.openScene("EditAttribute.fxml", "Edit Attribute", 600, 500);

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }

    @FXML
    private void handleEditAttributeButtonAction(ActionEvent event)
    {
        try
        {
            Util ut = new Util();
            lineEditing = lst_attributes.getSelectionModel().getSelectedIndex();
            ut.openScene("EditAttribute.fxml", "Edit Attribute", 600, 500);

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void createDefaultFile() throws Exception
    {
        deleteDefaultFile();
        PrintWriter writer = new PrintWriter("default.rb", "UTF-8");
        writer.close();

        currentFile = new File("default.rb");
    }

    private void deleteDefaultFile()
    {
        File newFile = new File("default.rb");
        if(newFile.exists())
        {
            newFile.delete();
        }
    }

    //not setting multi as multi lines? not sure why
    private void populateAttributes()
    {
        FileManager fm = new FileManager();
        List<String> values = fm.readFile("default.rb");
        lst_attributes.setItems(FXCollections.observableArrayList(values));
    }





}
