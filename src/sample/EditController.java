package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

import javax.swing.*;
import java.util.List;

/**
 * Created by bwells on 3/27/2017.
 */
public class EditController {
    @FXML
    private ListView lst_descriptors;

    @FXML
    private TextField txt_value;

    @FXML
    protected void initialize()
    {

        if(lst_descriptors != null)
        {
            lst_descriptors.setCellFactory(TextFieldListCell.forListView());
        }
    }


    @FXML
    private void handleNewDescriptorButtonAction(ActionEvent event)
    {
        Util ut = new Util();
        ut.addItemToListView("New", lst_descriptors);
    }

    @FXML
    private void handleCloneDescriptorSelected(ActionEvent event)
    {
        String selected = lst_descriptors.getSelectionModel().getSelectedItem().toString();

        Util ut = new Util();
        ut.addItemToListView(selected, lst_descriptors);
    }

    @FXML
    private void handleDeleteDescriptorSelected(ActionEvent event)
    {
        int selected = lst_descriptors.getSelectionModel().getSelectedIndex();

        Util ut = new Util();
        ut.removeItemFromListView(selected, lst_descriptors);
    }

    @FXML
    private void handleDoneEditButtonAction(ActionEvent event)
    {
        //write all descriptors and value to the file in correct format
        List<String> values = (List<String>) lst_descriptors.getItems();
        String value = txt_value.getText();
        String toWrite = "default";

        for(String v : values)
        {
            toWrite += "['" + v + "']";
        }

        toWrite += " = " + value;

        try{
            //should really write to line that was being edited only
            //writeToFile(toWrite, currentFile);
            FileManager fm = new FileManager();
            fm.writeToFile(MainController.lineEditing, toWrite, MainController.currentFile);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }


}
