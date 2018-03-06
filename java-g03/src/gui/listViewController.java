package gui;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class listViewController<E> {
    private ObservableList<E> lijstLeft;
    private ObservableList<E> lijstRight;

    @FXML
    private ListView<E> left;

    @FXML
    private JFXButton toRight;

    @FXML
    private JFXButton toLeft;

    @FXML
    private ListView<E> right;



    public listViewController(ObservableList<E> lijstLeft, ObservableList<E> lijstRight) {

        this.lijstLeft = lijstLeft;
        this.lijstRight = lijstRight;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("listView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }



    @FXML
    void toLeft(ActionEvent event) {
        E obj = right.getSelectionModel().getSelectedItem();
        if (obj != null){
            lijstLeft.add(obj);
            lijstRight.remove(obj);
            if (lijstRight.isEmpty())
                toLeft.setDisable(true);
            if (!lijstLeft.isEmpty())
                toRight.setDisable(false);
        }


    }

    @FXML
    void toRight(ActionEvent event) {
        E obj = left.getSelectionModel().getSelectedItem();
        if (obj != null){
            lijstRight.add(obj);
            lijstLeft.remove(obj);
            if (lijstLeft.isEmpty())
                toRight.setDisable(true);
            if (!lijstRight.isEmpty())
                toLeft.setDisable(false);
        }

    }

    public List<E> getLijstRight(){
        return lijstRight.stream().collect(Collectors.toList());
    }
}
