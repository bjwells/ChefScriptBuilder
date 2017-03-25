package sample;

import javafx.fxml.FXML;

import javax.swing.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainController {

    @FXML
    private void handleOpenButtonAction(ActionEvent event)
    {
        //button was clicked do something
        //JOptionPane.showMessageDialog(null, "clicked open button");
    }

    @FXML
    private void handleNewAttributeButtonAction(ActionEvent event)
    {
        //not opening for some reason
        //button was clicked
        try{
            openScene("AttributeList.fxml", "Attribute List");
        }
        catch (Exception e)
        {

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
}
