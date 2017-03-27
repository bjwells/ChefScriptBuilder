package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Created by bwells on 3/27/2017.
 */
public class Util {
    public void openScene(String windowName, String title, int width, int height) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(windowName));
        Main.thePrimaryStage.setTitle(title);
        Main.thePrimaryStage.setScene(new Scene(root, width, height));
        Main.thePrimaryStage.show();
    }

    public void addItemToListView(String value, ListView listView)
    {
        if(listView != null)
        {
            List<String> values = (List<String>) FXCollections.observableArrayList(listView.getItems());
            values.add(value);
            listView.setItems(FXCollections.observableArrayList(values));
        }
    }

    public void removeItemFromListView(int indexSelected, ListView listView)
    {
        List<String> values = (List<String>) FXCollections.observableArrayList(listView.getItems());
        values.remove(indexSelected);
        listView.setItems(FXCollections.observableArrayList(values));
    }
}
