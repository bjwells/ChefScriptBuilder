package sample;

import javafx.collections.FXCollections;
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

            if(MainController.lineEditing != -1)
            {
                //populate the descriptor list from the line of the file given
                FileManager fm = new FileManager();
                List<String> descs = fm.getDescriptorsFromFileLine(MainController.lineEditing, MainController.currentFile);

                String value = descs.get(descs.size() - 1);

                descs.remove(descs.size() - 1);

                lst_descriptors.setItems(FXCollections.observableArrayList(descs));
                txt_value.setText(value);
            }
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
            //writeToFile(toWrite, currentFile);
            FileManager fm = new FileManager();
            if(MainController.lineEditing != -1)
            {
                fm.writeToFile(MainController.lineEditing, toWrite, MainController.currentFile);
            }
            else
            {
                //write to the end of file
                fm.appendToFile(toWrite, MainController.currentFile);
            }

            Util ut = new Util();
            ut.openScene("AttributeList.fxml", "Attribute List", 600, 500);

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }


}
